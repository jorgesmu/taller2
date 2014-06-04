package ar.com.compumundohipermegared.compilacion;

import java.io.FileNotFoundException;
import java.util.Map;

public class Compilador {
	
	Map<String,String> labels; /* Hashtable<label(string),direccionHexa(string)> */
	ParserAssembly parser;
	DecoderAssembly decoder;
	
	public Compilador(String rutaArchivo) throws FileNotFoundException {
		// TODO
		parser = new ParserAssembly(rutaArchivo);
		decoder = new DecoderAssembly (labels);
	}
	
	public String compilar() {
		procesarTodosLabels();
		
		while (!(parser.terminado())) {
			String[] lineaParseada = parser.parsearLinea();
			String[] instrucciones = decoder.decodificar(lineaParseada,labels);
			escribirInstrucciones(instrucciones);			
		}
		
		return null;
	}
	
	private void escribirInstrucciones (String[] instrucciones) {
		// TODO
	}
	
	private void procesarTodosLabels() {
		String pcActual = "0000";
		
		while (!(parser.terminado())) {
			String[] lineaParseada = parser.parsearLinea();
			String label = parser.obtenerLabelActual();
			if (label != null) labels.put(label, pcActual);
			
			int cant = 0;
			if (lineaParseada.length > 0) {
				cant = decoder.cantidadInstrucciones(lineaParseada[0]);
			}
			pcActual = actualizarPC(pcActual, cant);
		}
		
		try {
			parser.reset();
		} catch (FileNotFoundException e) {	}
	}
	
	private String actualizarPC (String pcActual, int cant) {
		// TODO
		return "0000";
	}
	
}
