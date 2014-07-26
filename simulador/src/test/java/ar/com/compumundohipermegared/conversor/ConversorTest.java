package ar.com.compumundohipermegared.conversor;


import junit.framework.TestCase;

public class ConversorTest extends TestCase{
	
	public void testConvertirUnNumeroEntroAHexa() throws LimitesExcedidosConversorException, OverFlowCasteadorException{
		int numero = 126;
		int numero3 = 65;
		int numero4 = -1;
		int numero5 = -128;
				
		assertEquals("7E", Conversor.decimalToHexa(numero) );
		assertEquals("41", Conversor.decimalToHexa(numero3) );
		assertEquals("FF", Conversor.decimalToHexa(numero4) );
		assertEquals("80", Conversor.decimalToHexa(numero5) );
	}
	
	public void testPasarDeIntABinario() throws LimitesExcedidosConversorException, OverFlowCasteadorException{
		int numeroMenos128 = -128;
		int numeroMenos1 = -1;
		int numero1 = 1;
		int numero127 = 127;
		int numero85 = 85;
		int numeroMenos85 = -85;
		assertEquals("10000000", Conversor.decimalToComplemento(numeroMenos128) );
		assertEquals("11111111", Conversor.decimalToComplemento(numeroMenos1) );
		assertEquals("00000001", Conversor.decimalToComplemento(numero1) );
		assertEquals("01111111", Conversor.decimalToComplemento(numero127) );
		assertEquals("01010101", Conversor.decimalToComplemento(numero85) );
		assertEquals("10101011", Conversor.decimalToComplemento(numeroMenos85) );
		
	}
	
	public void testPasarDeByteABinario() throws LimitesExcedidosConversorException, OverFlowCasteadorException{
		byte numeroMenos128 = -128;
		byte numeroMenos1 = -1;
		byte numero1 = 1;
		byte numero127 = 127;
		byte numero85 = 85;
		byte numeroMenos85 = -85;
		assertEquals("10000000", Conversor.decimalToComplemento(numeroMenos128) );
		assertEquals("11111111", Conversor.decimalToComplemento(numeroMenos1) );
		assertEquals("00000001", Conversor.decimalToComplemento(numero1) );
		assertEquals("01111111", Conversor.decimalToComplemento(numero127) );
		assertEquals("01010101", Conversor.decimalToComplemento(numero85) );
		assertEquals("10101011", Conversor.decimalToComplemento(numeroMenos85) );
		
	}
	
	public void testConvertirUnNumeroExcedido() throws LimitesExcedidosConversorException, OverFlowCasteadorException{
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
		
		double numero3 = 0.125;
			
		assertEquals("59", Conversor.decimalToHexa(numero) );
		
		assertEquals("10", Conversor.decimalToHexa(numero3) );		
	}
	
	public void testConvertirUnNumeroDecimalNegativoAHexa() throws LimitesExcedidosConversorException {
		double numero = -3.2;
		
	
		assertEquals("D9", Conversor.decimalToHexa(numero) );
		
			
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
		String numero6 ="2C";
		
		assertEquals(1.1875 ,Conversor.puntoFlotanteADecimal(nhexa));
		assertEquals(0.140625, Conversor.puntoFlotanteADecimal(numero3) );
		assertEquals(-1.1875, Conversor.puntoFlotanteADecimal(numero4) );
		assertEquals(-0.140625, Conversor.puntoFlotanteADecimal(numero5) );
		assertEquals(0.4375, Conversor.puntoFlotanteADecimal(numero6) );
	}
	
	public void testConvertirAComplementoADosUnNumeroExcedido() throws LimitesExcedidosConversorException{
		String nHexa = "AF0";
		
		try {
			Conversor.complementoDosADecimal(nHexa);		
			assert(false);
		}
		catch ( LimitesExcedidosConversorException error){
			assert(true);
		}		
		
	}
	
	public void testNumeroComplementoADosDoblePrecision() throws LimitesExcedidosConversorException {
		String nHexa = "0AFF";
		String nHexa2 = "FFFF";
		
		assertEquals(2815,Conversor.complementoDosADecimalDoblePrecision(nHexa));		
		assertEquals(-1,Conversor.complementoDosADecimalDoblePrecision(nHexa2));		
	}
	
		
		
}	
	

