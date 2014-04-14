package ar.com.compumundohipermegared.almacenamiento;

import junit.framework.TestCase;

public class MemoriaTest extends TestCase{
	
	public void testTamanioMemoria(){
		Memoria memoria = new Memoria();
		assertEquals( 16 , memoria.getTamanio());
	}
	
	public void testCargarMemoria() throws LimiteExcedidoMemoriaException{
		Memoria memoria = new Memoria();
		memoria.cargarMemoria(5, 3 , 'a' );
		assertEquals('a' , memoria.getDatoMemoria(5, 3));		
		
	}
	
	public void testCargarMemoriaExcedidoEnTamanio(){
		Memoria memoria = new Memoria();
		try{
			memoria.cargarMemoria(25, 3 , 'a' );
			assert(false);
		}
		catch ( LimiteExcedidoMemoriaException error){
			assert(true);
		}
	}
	

}
