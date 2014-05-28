package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        MenuVentana menu = new MenuVentana();
        menu.setBounds(0, 0, 300, 300);
        menu.setVisible(true);
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
      
        try {
  			programa.close();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		return ruta;
  	}
    
    
    /*
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
        cargarInstruccion(programa, "0014", "12FD");
        cargarInstruccion(programa, "0016", "CAAA");
        cargarInstruccion(programa, "0018", "BBBA"); // esta es la de salto, como todavia no esta
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
*/
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
