package ar.com.compumundohipermegared.almacenamiento;



import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoAreaRegistroException;
import ar.com.compumundohipermegared.almacenamiento.AreaRegistro;
import junit.framework.TestCase;

public class AreaRegistroTest extends TestCase {

	public void testTamanioRegistro(){
		
		AreaRegistro  reg = new AreaRegistro();
		
		assertEquals(16 , reg.getTamanio());		
	}

	public void testCargarRegistroConDatos(){
		AreaRegistro reg = new AreaRegistro();
		
		try {
			reg.cargarRegistro(5,'a');		//Cargo en la posicion 5 del registro el dato int 9
		} catch (LimiteExcedidoAreaRegistroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			assertEquals( 'a' , reg.getDatoRegistro(5) );
		} catch (LimiteExcedidoAreaRegistroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testCargarRegistroConLimiteExcedido(){
		AreaRegistro reg = new AreaRegistro();
		
		try {
			reg.cargarRegistro(20 , 'a'); //Cargo en la posicion invalida del registro el dato int 9
			assert(false);
		}
		catch ( LimiteExcedidoAreaRegistroException error){
			assert(true);
		}
		
	}
	
	
}
