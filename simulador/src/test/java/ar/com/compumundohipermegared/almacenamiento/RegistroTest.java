package ar.com.compumundohipermegared.almacenamiento;



import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoVectorException;
import ar.com.compumundohipermegared.almacenamiento.Registro;
import junit.framework.TestCase;

public class RegistroTest extends TestCase {

	public void testTamanioRegistro(){
		int tam = 15;
		Registro  reg = new Registro(tam);
		
		assertEquals(tam , reg.getTamanio());		
	}

	public void testCargarRegistroConDatos(){
		Registro reg = new Registro(15);
		
		try {
			reg.cargarRegistro(5,9);		//Cargo en la posicion 5 del registro el dato int 9
		} catch (LimiteExcedidoVectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			assertEquals( 9 , reg.getDatoRegistro(5) );
		} catch (LimiteExcedidoVectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testCargarRegistroConLimiteExcedido(){
		Registro reg = new Registro(15);
		
		try {
			reg.cargarRegistro(20 , 5); //Cargo en la posicion invalida del registro el dato int 9
			assert(false);
		}
		catch ( LimiteExcedidoVectorException error){
			assert(true);
		}
		
	}
	
	
}
