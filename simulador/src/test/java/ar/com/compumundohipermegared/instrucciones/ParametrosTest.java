package ar.com.compumundohipermegared.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;
import junit.framework.TestCase;

public class ParametrosTest extends TestCase {

	public void testObtenerParametros() {
		String instruccion = "abcd";
		byte valorEsperado = Byte.parseByte("b", 16);
		Parametros par = new Parametros(instruccion);
		assertEquals(valorEsperado, par.getPrimerParametro());
		valorEsperado = Byte.parseByte("c", 16);
		assertEquals(valorEsperado, par.getSegundoParametro());
		valorEsperado = Byte.parseByte("d", 16);
		assertEquals(valorEsperado, par.getTercerParametro());
	}
}
