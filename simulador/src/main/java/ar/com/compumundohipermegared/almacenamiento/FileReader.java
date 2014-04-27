package ar.com.compumundohipermegared.almacenamiento;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader extends FileInputStream implements IInputStream {
	
	/* Como usamos web, no sabemos si efectivamente vamos a cargar un archivo
	 * o si nos va a llegar todo el programa como Strings por la red, asi que
	 * usamos la interfaz y por el momento, esta clase */
	
	public FileReader (FileDescriptor fdObj) {
		super (fdObj);
	}
	
	public FileReader(String name) throws FileNotFoundException {
		super(name);
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
		if (c == -1) throw new EndOfStreamException();
		while ((c != -1) && (c != '\n')) { // EOF = -1
			linea.append((char)c);
			c = this.readByte();
        }
		return linea.toString();
	}
	
	private int readByte () throws EndOfStreamException {
		try {
			return this.read();
		} catch (IOException e) {
			throw new EndOfStreamException();
		}
	}

}
