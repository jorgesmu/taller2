package ar.com.compumundohipermegared.compilacion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public String[] parsearLinea() throws InstruccionAssemblyInvalidaException {
		try {
			String lineaActual = archivo.readln();
			String[] lineaParseada = _parsearLinea(lineaActual);
			if (lineaParseada.length == 0) return lineaParseada;
			
			boolean tieneLabel = actualizarLabel (lineaParseada);
			if (tieneLabel) return filtrarLabel(lineaParseada);
			return lineaParseada;
		} catch (EndOfStreamException e) {
			return new String[0];
		}
	}
	
	private void validarFormato(String linea) throws InstruccionAssemblyInvalidaException{
		
		  Pattern pat1 = Pattern.compile("^.*, *,.*$");
		  Pattern pat2 = Pattern.compile("^.*, *$");
		  Pattern pat3 = Pattern.compile("^.[^ ,]* *,.*$");
		  Pattern pat4 = Pattern.compile("^.[^ ,]*: *.[^ ,]* ,.*$");
		  Matcher mat1 = pat1.matcher(linea);
		  Matcher mat2 = pat2.matcher(linea);
		  Matcher mat3 = pat3.matcher(linea);
		  Matcher mat4 = pat4.matcher(linea);
		  if (mat1.matches() || mat2.matches() || mat3.matches() || mat4.matches()) {
			  throw new InstruccionAssemblyInvalidaException("Formato inválido:" + linea);
		  }		  
		
	}
	
	private String[] _parsearLinea(String linea) throws InstruccionAssemblyInvalidaException{
		if (linea.startsWith("//")) return new String[0];
		
		validarFormato(linea);
		String delims = "[ ,]+"; // separo por espacios y comas	
		String[] tokens = linea.split(delims);
		if (tokens[0].equals("")) {
			String[] tokens2 = new String[tokens.length-1];
			for (int i = 0; i < tokens.length-1; ++i) tokens2[i] = tokens[i+1];
			tokens = tokens2;
		}
		return filtrarComentario(tokens);
	}
	
	private String[] filtrarComentario(String[] lineaParseada) {
		ArrayList<String> lineaFinal = new ArrayList<String>();
		int i = 0;
		boolean encontrado = false;
		while ((i < lineaParseada.length) && (!encontrado)) {
			String campo = lineaParseada[i];
			encontrado = campo.contains("//");
			if (encontrado) {
				String[] tokens = campo.split("//");
				if (tokens.length > 0) {
					if (!(tokens[0].equals(""))) lineaFinal.add(tokens[0]);
				}
			} else lineaFinal.add(campo);
			++i;
		}
		
		return lineaFinal.toArray(new String[0]);
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
