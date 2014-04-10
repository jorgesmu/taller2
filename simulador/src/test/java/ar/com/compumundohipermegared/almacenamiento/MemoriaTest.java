package ar.com.compumundohipermegared.almacenamiento;

import junit.framework.TestCase;

public class MemoriaTest extends TestCase{
	
	public void testTamanioMemoria(){
		Memoria memoria = new Memoria(10);
		assertEquals(10, memoria.getTamanio());
	}
	
	public void testCargarMemoria(){
		Memoria memoria = new Memoria(10);
		memoria.cargarMemoria(5, 3 , 6 );
		assertEquals(6 , memoria.getDatoMemoria(5, 3));
		
		
	}

}
