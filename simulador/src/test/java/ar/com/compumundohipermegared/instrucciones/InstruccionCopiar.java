package ar.com.compumundohipermegared.instrucciones;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;
import junit.framework.TestCase;

public class InstruccionCopiar extends TestCase {
	private static void cargarInstruccion(FileOutputStream programa, String direccion, String instruccion) {
		writeln (programa, direccion + new String(" ") + instruccion);
	}
	
	private static String crearPrograma() throws FileNotFoundException {
		String ruta = new String ("prueba.cod");
		FileOutputStream programa = new FileOutputStream (ruta);
		cargarInstruccion(programa, "0000", "4AAB");
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
	public void testCopiar() throws LimitesExcedidosConversorException, ProgramaMalFormadoException {
		byte dato;
		dato = (byte) Conversor.complementoDosADecimal("BC");
	
		try {
    		String ruta = crearPrograma();
    		Cpu cpu = new Cpu(ruta);
			IMemoria ram = cpu.getMemoria();
			cpu.escribirRegistro(10, dato);
			if (cpu.obtenerDatoRegistro(10) == cpu.obtenerDatoRegistro(11)){
				assert(false);
			}
	        cpu.ejecutarPrograma();
			if (dato != cpu.obtenerDatoRegistro(10)){
				assert(false);
			}		
    	} catch (FileNotFoundException e) {
			assert(false);
		}
	}

}
