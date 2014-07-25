package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ar.com.compumundohipermegared.almacenamiento.EndOfStreamException;
import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.ExtensionInvalidaException;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.compilacion.ProgramaMuyLargoException;
import ar.com.compumundohipermegared.compilacion.ProgramaYaCompiladoException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

/**
 * Hello world!
 *
 */
public class App {
	/*
    public static void main (String[] args) {
    	//String rutaCompulacion = pruebaCompilacion();
    	//pruebaEjecucion(rutaCompulacion);
    	pruebaEjecucion("./Programa3.maq");
    }
    */
    private static String pruebaCompilacion() {
    	try {
    		//String ruta = crearProgramaAssembly();
    		String ruta = "./Programa3.asm";
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
		} catch (FileNotFoundException | ExtensionInvalidaException | ProgramaMuyLargoException | InstruccionAssemblyInvalidaException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (ProgramaYaCompiladoException | IOException e1) {
			e1.printStackTrace();
		}
    	return "";
    }
    
    private static String crearProgramaAssembly() throws FileNotFoundException {
    	String ruta = new String ("./resources/prueba.asm");
    	PrintWriter programa = new PrintWriter(ruta);
  		
  		programa.println("ldi r1,0");
  		programa.println("ldi r2,0");
  		programa.println("cmp r1,r2");
  		programa.println("jpz etiqueta2");
  		programa.println("ldi r1,1");
  		programa.println("etiqueta1: ldi r4,8");
  		programa.println("etiqueta2: ldi r5,8");
  		programa.println("ldi r2,10");
  		programa.println("and r8,r1,r2");
  		programa.println("jnz 4");
  		
        programa.close();
    	return ruta;
    }
    
    private static void pruebaEjecucion(String ruta) {
    	try {
    		Cpu cpu = new Cpu(ruta);
			Thread hiloEjecucion = new Thread (cpu);
	        hiloEjecucion.start();
	        IMemoria memoriaPrincipal = cpu.getMemoria();
	        
	        Scanner ins = new Scanner(System.in);
	        ins.nextLine();
	        memoriaPrincipal.escribirDispositivoEntrada((byte)1);
	        ins.nextLine();
	        memoriaPrincipal.escribirDispositivoEntrada((byte)127);
	        ins.nextLine();
	        memoriaPrincipal.escribirDispositivoEntrada((byte)5);
	        ins.nextLine();
	        memoriaPrincipal.escribirDispositivoEntrada((byte)10);
	        ins.nextLine();
	        memoriaPrincipal.escribirDispositivoEntrada((byte)0);
	        
		} catch (FileNotFoundException | ProgramaMalFormadoException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
        System.out.println ("fin de app");
    }
    
    /*
    private static String crearProgramaMaquina() throws FileNotFoundException {
  		String ruta = new String ("./resources/prueba.maq");
  		PrintWriter programa = new PrintWriter(ruta);
  		
  		programa.println("0000" + " " + "28AA"); // cargo el registro 8 con AA
  		programa.println("0002" + " " + "A801"); // shift derecha aritmetico
  		programa.println("0004" + " " + "A832"); // shift izquierda zero fill
  		programa.println("0006" + " " + "A813"); // shift derecha logico
  		programa.println("0008" + " " + "A821"); // shift izquierda one fill
  		
        programa.close();
  		return ruta;
  	}
  	*/
    
}
