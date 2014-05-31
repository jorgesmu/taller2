package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;

import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.interfacesUsuario.MenuVentana;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main (String[] args) {
    	try {
    		//String ruta = crearPrograma();
    		String ruta = crearPrograma1();
			IInputStream programa = new FileReader (ruta);
			IMemoria memoriaPrincipal = new MemoriaRam(16); // 16 * 16 celdas
			Cpu cpu = new Cpu(programa, memoriaPrincipal);
			
			Thread hiloEjecucion = new Thread (cpu);
	        hiloEjecucion.start();
	        
	        memoriaPrincipal.escribirDispositivoEntrada((byte)5);
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        System.out.println ("fin de app");
        //MenuVentana menu = new MenuVentana();
       // menu.setBounds(0, 0, 300, 300);
        //menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //menu.setVisible(true);
    }

    private static String crearPrograma1() throws FileNotFoundException {
  		String ruta = new String ("prueba.cod");
  		FileOutputStream programa = new FileOutputStream (ruta);
  		
  		cargarInstruccion(programa, "0000", "280A");	//cargo el registro 8 con 0A
        cargarInstruccion(programa, "0002", "2F1E");	//cargo el registro F con 1E = 30
        cargarInstruccion(programa, "0004", "508F");	//sumo el registro 8 con F y en resultado lo pongo en el registro 0
        cargarInstruccion(programa, "0006", "4001");	//copio reg 0 a reg 1
        cargarInstruccion(programa, "0008", "A103");	//roto reg 1 3 posiciones 
        cargarInstruccion(programa, "000A", "7B10");	//or entre reg 1 y reg 0 da 45
        cargarInstruccion(programa, "000C", "8C10");	//and da 0
        cargarInstruccion(programa, "000E","9F10");		//xor entre 40 y 5
        cargarInstruccion(programa,"0010","6510");      //suma flotante
        cargarInstruccion(programa,"0012","301E");      //
        cargarInstruccion(programa,"0014","121E");      //
        cargarInstruccion(programa,"0016","5820");      //
        cargarInstruccion(programa,"0018","B100");
        cargarInstruccion(programa,"001A","C000");
        cargarInstruccion(programa,"001C","B200");
        
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
