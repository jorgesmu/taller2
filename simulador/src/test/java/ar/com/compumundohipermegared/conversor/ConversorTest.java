package ar.com.compumundohipermegared.conversor;


import junit.framework.TestCase;

public class ConversorTest extends TestCase{
	
	public void testConvertirUnNumeroEntroAHexa() throws LimitesExcedidosConversorException{
		int numero = 126;
		int numero3 = 65;
		int numero4 = -1;
		int numero5 = -128;
				
		assertEquals("7E", Conversor.decimalToHexa(numero) );
		assertEquals("41", Conversor.decimalToHexa(numero3) );
		assertEquals("FF", Conversor.decimalToHexa(numero4) );
		assertEquals("80", Conversor.decimalToHexa(numero5) );
	}
	
	public void testConvertirUnNumeroExcedido() throws LimitesExcedidosConversorException{
		int numeroExcedido = -129;
		try {
			Conversor.decimalToHexa(numeroExcedido);			
			assert(false);
		}
		catch ( LimitesExcedidosConversorException error){
			assert(true);
		}
		
	}
	
	public void testConvertirUnNumeroDecimalPositvoAHexa() throws LimitesExcedidosConversorException {
		double numero = 3.2;
		double numero2 = 0.002;
			
		assertEquals("43", Conversor.decimalToHexa(numero) );
		assertEquals("12", Conversor.decimalToHexa(numero2) );
				
	}
	
	public void testConvertirUnNumeroDecimalNegativoAHexa() throws LimitesExcedidosConversorException {
		double numero = -3.2;
		double numero2 = -0.002;
	
		assertEquals("C3", Conversor.decimalToHexa(numero) );
		assertEquals("92", Conversor.decimalToHexa(numero2) );
			
	}
	
	public void testConvertirUnNumeroDecimalExcedidoAHexa() throws LimitesExcedidosConversorException {
		double numeroExcedido = 160000;
		double numeroExcedido2 = 0.00002;
		
		try {
			Conversor.decimalToHexa(numeroExcedido);			
			assert(false);
		}
		catch ( LimitesExcedidosConversorException error){
			assert(true);
		}
		
		
		try {
			Conversor.decimalToHexa(numeroExcedido2);			
			assert(false);
		}
		catch ( LimitesExcedidosConversorException error){
			assert(true);
		}
						
	}
	
	public void testConvertirComplementoADosADecimal() throws LimitesExcedidosConversorException{
		String nhexa = "7E";
		String numero3 = "41";
		String numero4 = "FF";
		String numero5 = "80";					
		
		assertEquals(126 ,Conversor.complementoDosADecimal(nhexa));
		assertEquals(65, Conversor.complementoDosADecimal(numero3) );
		assertEquals(-1, Conversor.complementoDosADecimal(numero4) );
		assertEquals(-128, Conversor.complementoDosADecimal(numero5) );
		
	}
	
	public void testConvertirPuntoFlotanteADecimal() throws LimitesExcedidosConversorException{
		String nhexa = "43";
		String numero3 = "12";
		String numero4 = "c3";
		String numero5 = "92";					
		
		assertEquals(3.0,Conversor.puntoFlotanteADecimal(nhexa));
		assertEquals(0.002, Conversor.puntoFlotanteADecimal(numero3) );
		assertEquals(-3.0, Conversor.puntoFlotanteADecimal(numero4) );
		assertEquals(-0.002, Conversor.puntoFlotanteADecimal(numero5) );
		
	}
}
