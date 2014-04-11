package ar.com.compumundohipermegared.almacenamiento;

import ar.com.compumundohipermegared.almacenamiento.Celda;
import junit.framework.TestCase;

public class UnidadDatoTest extends TestCase {
	
	 public void testCargarCelda(){
	    	char dato = 'a';
	    	
	    	Celda celda = new Celda();
	    	celda.setDato(dato);
	    	
	    	assertEquals( dato , celda.getDato() );	    	
	}
	
	 public void testCargarCeldaConVariosValores(){
		 char primerDato = 'a';
		 char ultimoDato = 'z';
		 
		 Celda celda = new Celda();
		 celda.setDato(primerDato);
		 celda.setDato(ultimoDato);
		 
		 assertEquals( ultimoDato , celda.getDato() );
		 		 
	 }
}
