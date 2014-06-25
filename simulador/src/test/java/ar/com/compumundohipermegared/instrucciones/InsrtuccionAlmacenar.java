package ar.com.compumundohipermegared.instrucciones;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoMemoriaException;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;
import junit.framework.TestCase;

public class InsrtuccionAlmacenar extends TestCase {
	private static void cargarInstruccion(FileOutputStream programa, String direccion, String instruccion) {
		writeln (programa, direccion + new String(" ") + instruccion);
	}
	
	private static String crearPrograma() throws FileNotFoundException {
		String ruta = new String ("prueba.cod");
		FileOutputStream programa = new FileOutputStream (ruta);
		cargarInstruccion(programa, "0000", "3ABC");
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
	
	public void testEjecutarCarga() throws LimiteExcedidoMemoriaException, ProgramaMalFormadoException {
		byte dato = 25;
		MemoriaRam ram = new MemoriaRam(256);
    	try {
    		String ruta = crearPrograma();
		
			Cpu cpu = new Cpu(ruta);
			cpu.escribirRegistro(10, dato);
			assertFalse(ram.getDatoMemoria(11, 12) == dato);
			cpu.ejecutarPrograma();
			assertEquals(ram.getDatoMemoria(11, 12), dato);		
    	} catch (FileNotFoundException e) {
			assert(false);
		}
    	
	}

}
