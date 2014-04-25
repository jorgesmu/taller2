package ar.com.compumundohipermegared.conversor;

import junit.framework.TestCase;

public class CasteadorTest extends TestCase{
	
	public void testCastearNumerosAComplemtoADos() throws OverFlowCasteadorException{
		int numero = 65;
		int numero3 = 33;
		int numero4 = -1;
		char menosUno = (char) numero4;
		
		Casteador casteador = new Casteador();
		assertEquals('A', casteador.complementoADos(numero) );
		assertEquals('!', casteador.complementoADos(numero3) );
		assertEquals( menosUno , casteador.complementoADos(numero4) );
		
	}
	
	public void testCastearUnNumeroExcedidoAComplemtoADos() throws OverFlowCasteadorException{
		int numero = 128;
		int numero3 = 250;
		int numero4 = -129;
		
		
		Casteador casteador = new Casteador();
		try {
			casteador.complementoADos(numero);		
			assert(false);
		}
		catch ( OverFlowCasteadorException error){
			assert(true);
		}
		
		try {
			casteador.complementoADos(numero3);		
			assert(false);
		}
		catch ( OverFlowCasteadorException error){
			assert(true);
		}
		
		try {
			casteador.complementoADos(numero4);		
			assert(false);
		}
		catch ( OverFlowCasteadorException error){
			assert(true);
		}
		
		
	}
	
	public void testCastearNumerosAPuntoFlotante() throws OverFlowCasteadorException, UnderFlowCasteadorException{
		
		double numero = 3;
		double numero3 = 1.235; 
		
		char tres = (char) 67; //  0 100 0011
		char doce = (char) 60;// numero3= 0 011 1100 = 60 
		
		Casteador casteador = new Casteador();
		assertEquals(tres , casteador.puntoFlotante(numero) );
		assertEquals(doce , casteador.puntoFlotante(numero3) );		
		
	}
	
	public void testCastearNumerosExcedidosAPuntoFlotante() throws OverFlowCasteadorException, UnderFlowCasteadorException{
		
		double numero = 100000;
		double numero3 = 0.00005; 
		
				
		Casteador casteador = new Casteador();
		
		try {
			casteador.puntoFlotante(numero);		
			assert(false);
		}
		catch ( OverFlowCasteadorException error){
			assert(true);
		}
		
		try {
			casteador.puntoFlotante(numero3);		
			assert(false);
		}
		catch ( UnderFlowCasteadorException error){
			assert(true);
		}
		
	}
	

}
