package ar.com.compumundohipermegared.almacenamiento;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader extends FileInputStream implements IInputStream {
	
	/* Como usamos web, no sabemos si efectivamente vamos a cargar un archivo
	 * o si nos va a llegar todo el programa como Strings por la red, asi que
	 * usamos la interfaz y por el momento, esta clase */
	
	int numeroLinea = 1;
	boolean endOfFile = false;
	
	public FileReader (FileDescriptor fdObj) {
		super (fdObj);
	}
	
	public FileReader(String name) throws FileNotFoundException {
		super(name);
	}
	
	public int numeroLineaActual() {
		return numeroLinea;
	}
	
	@Override
	/* Omite las lineas vacias */
	public String readln() throws EndOfStreamException {
		String linea = leerLinea();
		while (linea.isEmpty()) {
			linea = leerLinea();
		}
		return linea;
	}
	
	private String leerLinea() throws EndOfStreamException {
		StringBuilder linea = new StringBuilder();
		int c = this.readByte();
		if (c == -1) {
			endOfFile = true;
			throw new EndOfStreamException();
		}
		while ((c != -1) && (c != '\n')) { // EOF = -1
			linea.append((char)c);
			c = this.readByte();
        }
		++numeroLinea;
		return linea.toString();
	}
	
	private int readByte () throws EndOfStreamException {
		try {
			return this.read();
		} catch (IOException e) {
			endOfFile = true;
			throw new EndOfStreamException();
		}
	}
	
	public boolean isEOF() {
		return endOfFile;
	}
	
}
