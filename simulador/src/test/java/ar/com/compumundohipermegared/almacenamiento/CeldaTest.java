package ar.com.compumundohipermegared.almacenamiento;

import ar.com.compumundohipermegared.almacenamiento.Celda;
import junit.framework.TestCase;

public class CeldaTest extends TestCase {
	
	 public void testCargarCelda(){
	    	int dato = 15;
	    	
	    	Celda celda = new Celda();
	    	celda.setCelda(dato);
	    	
	    	assertEquals( dato , celda.getCelda() );	    	
	}
	
	 public void testCargarCeldaConVariosValores(){
		 int primerDato = 10;
		 int ultimoDato = 20;
		 
		 Celda celda = new Celda();
		 celda.setCelda(primerDato);
		 celda.setCelda(ultimoDato);
		 
		 assertEquals( ultimoDato , celda.getCelda() );
		 		 
	 }
}
