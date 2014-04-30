package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main (String[] args) {
    	try {
    		String ruta = crearPrograma();
			IInputStream programa = new FileReader (ruta);
			Cpu cpu = new Cpu(programa, new MemoriaRam(256));
	        cpu.ejecutarPrograma();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        System.out.println ("fin de app");
    }

	private static String crearPrograma() throws FileNotFoundException {
		String ruta = new String ("prueba.cod");
		FileOutputStream programa = new FileOutputStream (ruta);
		cargarInstruccion(programa, "0000", "1ABC");
        cargarInstruccion(programa, "0002", "2BBA");
        cargarInstruccion(programa, "0004", "3AAA");
        cargarInstruccion(programa, "0006", "4AAA");
        cargarInstruccion(programa, "0008", "5BBA");
        cargarInstruccion(programa, "000A", "6AAA");
        cargarInstruccion(programa, "000C", "7AAA");
        cargarInstruccion(programa, "000E", "8BBA");
        cargarInstruccion(programa, "0010", "9AAA");
        cargarInstruccion(programa, "0012", "AAAA");
        cargarInstruccion(programa, "0014", "CAAA");
        cargarInstruccion(programa, "0016", "BBBA"); // esta es la de salto, como todavia no esta
        											 // programada, salta al 0000 y hace un loop infinito
        											 // pero como CAAA para la ejecucion, el programa 
        											 // no deberia saltar.
        try {
			programa.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ruta;
	}

	private static void cargarInstruccion(FileOutputStream programa, String direccion, String instruccion) {
		writeln (programa, direccion + new String(" ") + instruccion);
	}
	
	private static void writeln (FileOutputStream programa, String linea) {
		for (int i = 0 ; i < linea.length() ; ++i) {
			try {
				programa.write(linea.charAt(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			programa.write('\n');
		} catch (IOException e) { e.printStackTrace(); }
	}
	
}
