package ar.com.compumundohipermegared.almacenamiento;

import junit.framework.TestCase;

public class MemoriaTest extends TestCase{
	
	public void testTamanioMemoria(){
		MemoriaRam memoriaRam = new MemoriaRam(16);
		
		assertEquals( 16 , memoriaRam.getTamanio());
	}
	
	public void testCargarMemoria() throws LimiteExcedidoMemoriaException{
		MemoriaRam memoriaRam = new MemoriaRam(16);
		byte dato = 'a';
		memoriaRam.cargarMemoria(5, 3 , dato );
		assertEquals('a' , memoriaRam.getDatoMemoria(5, 3));		
		
	}
	
	public void testCargarMemoriaExcedidoEnTamanio(){
		MemoriaRam memoriaRam = new MemoriaRam(16);
		try{
			byte dato = 'a';
			memoriaRam.cargarMemoria(25, 3 , dato );
			assert(false);
		}
		catch ( LimiteExcedidoMemoriaException error){
			assert(true);
		}
	}
	

}
