package ar.com.compumundohipermegared.compilacion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;

public class Compilador {
	
	public static String REG_AUX_1 = "E";
	public static String REG_AUX_2 = "D";
	
	private static String ERROR_CAPACIDAD = "El procesador no posee la capacidad de direccionamiento suficiente para ejecutar todo el programa.";
	
	String rutaArchivoCompilado;
	PrintWriter archivoCompilado;
	boolean compilado;
	
	Map<String,String> labels; /* HashMap<label(string),direccionHexa(string)> */
	ParserAssembly parser;
	DecoderAssembly decoder;
	
	public Compilador(String rutaArchivo) throws FileNotFoundException, ExtensionInvalida {
		validacionRutaAssembly(rutaArchivo);
		
		labels = new HashMap<String,String>();
		parser = new ParserAssembly(rutaArchivo);
		decoder = new DecoderAssembly(labels);
		
		rutaArchivoCompilado = obtenerRutaArchivoCompilado(rutaArchivo);
		archivoCompilado = new PrintWriter(rutaArchivoCompilado);
		compilado = false;
	}
	
	private void validacionRutaAssembly(String rutaArchivo) throws ExtensionInvalida {
		int indiceFinal = rutaArchivo.length() - 1;
		int indicePunto = indiceFinal - 3; // ultimoIndice - extension(3)
		String extension = rutaArchivo.substring(indicePunto, indiceFinal + 1); // no incluye indiceFinal
		if (!(extension.equals(".asm")))
			throw new ExtensionInvalida("La extension del archivo Assembly debe ser .asm");
	}

	private String obtenerRutaArchivoCompilado (String rutaArchivoAssembly) {
		File f = new File(rutaArchivoAssembly);
		String directorio = f.getParent();
		
		String nombreAssembly = f.getName();
		int indiceFinal = nombreAssembly.length() - 4; // length - extension(3) - punto(1)
		String nombre = nombreAssembly.substring(0, indiceFinal); // no incluye indiceFinal
		
		return (directorio + "/" + nombre + ".maq");
	}
	
	public String compilar() throws ProgramaMuyLargoException, ProgramaYaCompiladoException, InstruccionAssemblyInvalidaException {
		if (compilado) throw new ProgramaYaCompiladoException("Programa ya compilado. Crear otra instancia de Compilador.");
		compilado = true;
		
		procesarTodosLabels(); // primera pasada
		String pcActual = "0000";
		while (!(parser.terminado())) {
			try {
				String[] lineaParseada = parser.parsearLinea();
				if (lineaParseada.length == 0) continue;
				String[] instrucciones = decoder.decodificar(lineaParseada, pcActual);
				pcActual = escribirInstrucciones(instrucciones, pcActual);
			} catch (InstruccionAssemblyInvalidaException e) {
				int numero = parser.numeroLineaActual() - 1; // ya leyo y avanzo el numero de linea
				throw new InstruccionAssemblyInvalidaException("La linea " + numero + " presenta una instruccion invalida.");
			}
		}
		
		archivoCompilado.close();
		return rutaArchivoCompilado;
	}
	
	private String escribirInstrucciones (String[] instrucciones, String pcActual) throws ProgramaMuyLargoException {
		for (int i = 0; i < instrucciones.length; ++i) {
			archivoCompilado.println(pcActual + " " + instrucciones[i]);
			try {
				pcActual = actualizarPC(pcActual,1);
			} catch (LimitesExcedidosConversorException e) {
				throw new ProgramaMuyLargoException(ERROR_CAPACIDAD);
			}
		}
		return pcActual;
	}
	
	private void procesarTodosLabels() throws ProgramaMuyLargoException, InstruccionAssemblyInvalidaException {
		String pcActual = "0000";
		
		while (!(parser.terminado())) {
			try {
				String[] lineaParseada = parser.parsearLinea();
				int cant = 0;
				if (lineaParseada.length > 0) {
					String label = parser.obtenerLabelActual();
					if (label != null) labels.put(label, pcActual);
					cant = decoder.cantidadInstrucciones(lineaParseada[0]);
				}
				pcActual = actualizarPC(pcActual, cant);
				
			} catch (LimitesExcedidosConversorException e1) {
				throw new ProgramaMuyLargoException(ERROR_CAPACIDAD);
			} catch (InstruccionAssemblyInvalidaException e2) {
				int numero = parser.numeroLineaActual() - 1; // ya leyo y avanzo el numero de linea
				throw new InstruccionAssemblyInvalidaException("La linea numero " + numero + " presenta una instruccion invalida.");
			}			
			
		}
		
		try {
			parser.reset();
		} catch (FileNotFoundException e) {	}
	}
	
	private String actualizarPC (String pcActual, int cant) throws LimitesExcedidosConversorException {
		int pc = Integer.parseInt(pcActual,16);
		int pcNuevo = pc + 2*cant;
		return (Conversor.decimalToHexaDoblePrecision(pcNuevo));
	}
	
}
