package ar.com.compumundohipermegared.compilacion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;

public class Compilador {
	
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
		String extension = rutaArchivo.substring(indicePunto, indiceFinal);
		if (extension != ".asm")
			throw new ExtensionInvalida("La extension del archivo Assembly debe ser .asm");
	}

	private String obtenerRutaArchivoCompilado (String rutaArchivoAssembly) {
		File f = new File(rutaArchivoAssembly);
		String directorio = f.getParent();
		
		String nombreAssembly = f.getName();
		int indiceFinal = nombreAssembly.length() - 5; // length - 1 - extension(3) - punto(1)
		String nombre = nombreAssembly.substring(0, indiceFinal);
		
		return (directorio + nombre + ".maq");
	}
	
	public String compilar() throws ProgramaMuyLargoException, ProgramaYaCompiladoException {
		if (compilado) throw new ProgramaYaCompiladoException("Programa ya compilado. Crear otra instancia de Compilador.");
		compilado = true;
		
		procesarTodosLabels(); // primera pasada
		String pcActual = "0000";
		while (!(parser.terminado())) {
			String[] lineaParseada = parser.parsearLinea();
			String[] instrucciones = decoder.decodificar(lineaParseada,labels);
			escribirInstrucciones(instrucciones, pcActual);			
		}
		
		archivoCompilado.close();
		return rutaArchivoCompilado;
	}
	
	private void escribirInstrucciones (String[] instrucciones, String pcActual) throws ProgramaMuyLargoException {
		for (int i = 0; i < instrucciones.length; ++i) {
			archivoCompilado.println(pcActual + " " + instrucciones[i]);
			try {
				pcActual = actualizarPC(pcActual,1);
			} catch (LimitesExcedidosConversorException e) {
				throw new ProgramaMuyLargoException(ERROR_CAPACIDAD);
			}
		}
	}
	
	private void procesarTodosLabels() throws ProgramaMuyLargoException {
		String pcActual = "0000";
		
		while (!(parser.terminado())) {
			String[] lineaParseada = parser.parsearLinea();
			String label = parser.obtenerLabelActual();
			if (label != null) labels.put(label, pcActual);
			
			int cant = 0;
			if (lineaParseada.length > 0) {
				cant = decoder.cantidadInstrucciones(lineaParseada[0]);
			}
			try {
				pcActual = actualizarPC(pcActual, cant);
			} catch (LimitesExcedidosConversorException e) {
				throw new ProgramaMuyLargoException(ERROR_CAPACIDAD);
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
