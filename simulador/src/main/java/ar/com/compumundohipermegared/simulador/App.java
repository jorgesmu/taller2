package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import ar.com.compumundohipermegared.almacenamiento.EndOfStreamException;
import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.ExtensionInvalida;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.compilacion.ProgramaMuyLargoException;
import ar.com.compumundohipermegared.compilacion.ProgramaYaCompiladoException;
import ar.com.compumundohipermegared.interfacesUsuario.MenuVentana;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main (String[] args) {
    	String rutaCompulacion = pruebaCompilacion();
    	pruebaEjecucion(rutaCompulacion);
    	
        MenuVentana menu = new MenuVentana();
        menu.setBounds(0, 0, 300, 325);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    	
    }
    
    private static String pruebaCompilacion() {
    	try {
    		String ruta = crearProgramaAssembly();
			Compilador comp = new Compilador(ruta);
			String rutaCompilado = comp.compilar();
			
			System.out.println("Ruta archivo compilado: " + rutaCompilado);
			FileReader archivoCompilado = new FileReader(rutaCompilado);
			while (!archivoCompilado.isEOF()) {
				try {
					System.out.println(archivoCompilado.readln());
				} catch (EndOfStreamException e) {
					
				}
			}
			archivoCompilado.close();
			return rutaCompilado;
		} catch (FileNotFoundException | ExtensionInvalida | ProgramaMuyLargoException | InstruccionAssemblyInvalidaException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (ProgramaYaCompiladoException | IOException e1) {
			e1.printStackTrace();
		}
    	return null;
    }
    
    private static String crearProgramaAssembly() throws FileNotFoundException {
    	String ruta = new String ("./resources/prueba.asm");
    	PrintWriter programa = new PrintWriter(ruta);
  		
  		programa.println("ldi r1,0A//eso");
  		programa.println("ldi r2,05");
  		programa.println("add r3,r1,r2");
  		
  		
        programa.close();
    	return ruta;
    }
    
    private static void pruebaEjecucion(String ruta) {
    	try {
			IInputStream programa = new FileReader (ruta);
			IMemoria memoriaPrincipal = new MemoriaRam(16); // 16 * 16 celdas
			Cpu cpu = new Cpu(programa, memoriaPrincipal);
			
			Thread hiloEjecucion = new Thread (cpu);
	        hiloEjecucion.start();
	        
	        memoriaPrincipal.escribirDispositivoEntrada((byte)5);
	        
		} catch (FileNotFoundException | ProgramaMalFormadoException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
        System.out.println ("fin de app");
    }
    
    /*
    private static String crearProgramaMaquina() throws FileNotFoundException {
  		String ruta = new String ("prueba.maq");
  		PrintWriter programa = new PrintWriter(ruta);
  		
  		programa.println("0000" + " " + "280A"); //cargo el registro 8 con 0A
  		programa.println("0002" + " " + "2F1E"); //cargo el registro F con 1E = 30
  		programa.println("0004" + " " + "508F"); //sumo el registro 8 con F y en resultado lo pongo en el registro 0
  		programa.println("0006" + " " + "4001"); //copio reg 0 a reg 1
  		programa.println("0008" + " " + "A103"); //roto reg 1 3 posiciones
  		programa.println("000A" + " " + "7B10"); //or entre reg 1 y reg 0 da 45
  		programa.println("000C" + " " + "8C10"); //and da 0
  		programa.println("000E" + " " + "9F10"); //xor entre 40 y 5
  		programa.println("0010" + " " + "6510"); //suma flotante
  		programa.println("0012" + " " + "301E"); //
  		programa.println("0014" + " " + "121E"); //
  		programa.println("0016" + " " + "5820"); //
  		programa.println("0018" + " " + "B100");
  		programa.println("001A" + " " + "C000");
  		programa.println("001C" + " " + "B200");
        
        programa.close();
  		return ruta;
  	}
	*/
}
