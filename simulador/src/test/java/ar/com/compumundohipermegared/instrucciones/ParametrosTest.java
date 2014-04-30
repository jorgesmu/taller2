package ar.com.compumundohipermegared.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;
import junit.framework.TestCase;

public class ParametrosTest extends TestCase {

	public void testObtenerParametros() {
		String instruccion = "abcd";
		byte valorEsperado = 'b';
		Parametros par = new Parametros(instruccion);
		assertEquals(valorEsperado, par.getPrimerParametro());
		valorEsperado = 'c';
		assertEquals(valorEsperado, par.getSegundoParametro());
		valorEsperado = 'd';
		assertEquals(valorEsperado, par.getTercerParametro());
	}
}
