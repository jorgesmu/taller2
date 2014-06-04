package ar.com.compumundohipermegared.compilacion;

import java.io.FileNotFoundException;

import ar.com.compumundohipermegared.almacenamiento.EndOfStreamException;
import ar.com.compumundohipermegared.almacenamiento.FileReader;

public class ParserAssembly {
	
	String rutaArchivo;
	FileReader archivo;
	String labelActual = null;
	
	public ParserAssembly (String rutaArchivoRecibida) throws FileNotFoundException {
		rutaArchivo = rutaArchivoRecibida;
		reset();
	}
	
	public String[] parsearLinea() {
		try {
			String lineaActual = archivo.readln();
			String[] lineaParseada = new String[1]; // TODO: parsear lineaActual
			
			boolean tieneLabel = actualizarLabel (lineaParseada);
			String[] lineaParseadaFinal;
			if (tieneLabel) lineaParseadaFinal = new String[1]; // TODO: saco lineaActual[0]
			else lineaParseadaFinal = lineaParseada;
			return lineaParseadaFinal;
		} catch (EndOfStreamException e) {
			return new String[0];
		}
	}
	
	private boolean actualizarLabel (String[] lineaParseada) {
		// TODO:
		// if (lineaParseada[0] termina en ":") {
		// 	labelActual = lineaParseada[0];
		// 	return true;
		// } else {
		// 	labelActual = null;
		// 	return false;
		// }
		return false;
	}
	
	public String obtenerLabelActual() {
		return labelActual;
	}
	
	public int numeroLineaActual() {
		return archivo.numeroLineaActual();
	}
	
	public boolean terminado() {
		return archivo.isEOF();
	}
	
	public void reset() throws FileNotFoundException {
		archivo = new FileReader(rutaArchivo);
	}
}
