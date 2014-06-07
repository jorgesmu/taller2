package ar.com.compumundohipermegared.compilacion;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
			String[] lineaParseada = _parsearLinea(lineaActual);
			
			boolean tieneLabel = actualizarLabel (lineaParseada);
			if (tieneLabel) return filtrarLabel(lineaParseada);
			return lineaParseada;
		} catch (EndOfStreamException e) {
			return new String[0];
		}
	}
	
	private String[] _parsearLinea(String linea) {
		String delims = "[ ]+"; // separo por espacios (pueden haber varios seguidos)
		String[] tokens = linea.split(delims);
		
		delims = ","; // separo por comas (no pueden haber varias seguidas)
		ArrayList<String> allTokens = new ArrayList<String>();
		for (int i = 0; i < tokens.length; ++i) {
			String[] tokenParseado = tokens[i].split(delims); // sin comas ni espacios
			for (int j = 0; j < tokenParseado.length; ++j) {
				String token = tokenParseado[j];
				if (!(token.equals(""))) allTokens.add(token);
			}
		}
		String[] parseoFinal = (String[]) allTokens.toArray(new String[1]);
		return filtrarComentario(parseoFinal);
	}
	
	private String[] filtrarComentario(String[] lineaParseada) {
		// busco comentarios en la instruccion
		int i = 0;
		boolean encontrado = false;
		while ((i < lineaParseada.length) && (!encontrado)) {
			encontrado = lineaParseada[i].startsWith("//");
			++i;
		}
		if (!encontrado) return lineaParseada; // no hay comentarios, finaliza parseo
		
		String[] lineaFinal = new String[i];
		for (int j = 0; j < i; ++j) {
			lineaFinal[j] = lineaParseada[j]; // tiene comentarios, se los saco
		}
		return lineaFinal;
	}
	
	private String[] filtrarLabel(String[] lineaParseada) {
		int largo = lineaParseada.length;
		String[] lineaParseadaFinal = new String[largo-1];
		
		for (int i = 1; i < largo; ++i) {
			lineaParseadaFinal[i-1] = lineaParseada[i];
		}
		return lineaParseadaFinal;
	}

	private boolean actualizarLabel (String[] lineaParseada) {
		if (lineaParseada[0].endsWith(":")) {
			labelActual = lineaParseada[0];
			labelActual = labelActual.substring(0, labelActual.length() - 1);
			return true;
		} else {
			labelActual = null;
			return false;
		}
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
