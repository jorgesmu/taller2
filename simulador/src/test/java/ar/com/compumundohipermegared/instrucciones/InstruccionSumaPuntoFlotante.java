package ar.com.compumundohipermegared.instrucciones;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import junit.framework.TestCase;

public class InstruccionSumaPuntoFlotante extends TestCase {
	private static void cargarInstruccion(FileOutputStream programa, String direccion, String instruccion) {
		writeln (programa, direccion + new String(" ") + instruccion);
	}
	
	private static String crearPrograma() throws FileNotFoundException {
		String ruta = new String ("prueba.cod");
		FileOutputStream programa = new FileOutputStream (ruta);
		cargarInstruccion(programa, "0000", "6ABC");
        try {
			programa.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ruta;
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
	public void testSumarPuntoFlotante() throws LimitesExcedidosConversorException {
		byte operando1;
		operando1 = Byte.parseByte("B",16);
		byte operando2;
		operando2 = Byte.parseByte("C",16);
		byte resultado;
		resultado = Byte.parseByte("17", 16);
		
		MemoriaRam ram = new MemoriaRam(256);
    	try {
    		String ruta = crearPrograma();
			IInputStream programa = new FileReader (ruta);
			Cpu cpu = new Cpu(programa,ram);
			cpu.escribirRegistro(11, operando1);
			cpu.escribirRegistro(12, operando2);
			if (cpu.obtenerRegistro(10) == cpu.obtenerRegistro(11)){
				assert(false);
			}
			if (cpu.obtenerRegistro(11) == cpu.obtenerRegistro(12)){
				assert(false);
			}
			if (cpu.obtenerRegistro(10) == cpu.obtenerRegistro(12)){
				assert(false);
			}
			if (cpu.obtenerRegistro(10) == resultado){
				assert(false);
			}
			cpu.ejecutarPrograma();
			if (resultado != cpu.obtenerRegistro(10)){
				assert(false);
			}		
    	} catch (FileNotFoundException e) {
			assert(false);
		}
	}

}
