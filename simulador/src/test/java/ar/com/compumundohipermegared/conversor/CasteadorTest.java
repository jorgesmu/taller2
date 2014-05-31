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
		double numero3Neg = -1.235;
		double extremo1 = 15.5;
		double extremo2 = 0.0625;
		double medio = 5.6875;
		
		char tres = (char)88; //  0 101 1000
		char doce = (char) 67;// numero3= 0 100 0011 = 67 
		char otro = (char) 195 ;// numero3= 1 100 0011 = 195
		
		Casteador casteador = new Casteador();
		assertEquals(tres ,casteador.puntoFlotante(numero) );
		assertEquals(doce , casteador.puntoFlotante(numero3) );		
		assertEquals(otro , casteador.puntoFlotante(numero3Neg) );	
		assertEquals(127 , casteador.puntoFlotante(extremo1) );	
		assertEquals( 0 , casteador.puntoFlotante(extremo2) );	
		assertEquals( 102 , casteador.puntoFlotante(medio) );	
		
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
	
	
	public void testExponente(){
		
		double numeroOverFlow = 16;
		double numeroUnderFlow = 0;
		double numero3 = 7; 
		double numero4 = 0.0625;
		double numero5 = 0.5;
		
		Casteador casteador = new Casteador();
		assertEquals(99 , casteador.calcularExponente( numeroOverFlow) );
		assertEquals(-99 , casteador.calcularExponente( numeroUnderFlow) );
		assertEquals(2 , casteador.calcularExponente(numero3) );		
		assertEquals(-4 , casteador.calcularExponente(numero4) );		
		assertEquals(-1 , casteador.calcularExponente(numero5) );		
		
	}
	
	public void testCalcularMantisa(){
		double numero3 = 7; 
		double numero4 = 0.0625;
		double numero5 = 0.5;
		double numero6 = 7.5;
		double numero7 = 0.6640625;
		double numero8 = 1.6640625;
		double numero9 = 5.684;
	
		
		Casteador casteador = new Casteador();
		
		assertEquals(12 , casteador.calcularMantisa(numero3) );		
		assertEquals(0, casteador.calcularMantisa(numero4) );			
		assertEquals(0 , casteador.calcularMantisa(numero5) );		
		assertEquals(14 , casteador.calcularMantisa(numero6) );				
		assertEquals(5 , casteador.calcularMantisa(numero7) );	
		assertEquals(10 , casteador.calcularMantisa(numero8) );	
		assertEquals(6 , casteador.calcularMantisa(numero9) );	
		
	}
	

}
