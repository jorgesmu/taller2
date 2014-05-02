package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;

public class Parametros {
	
	private byte primerParametro;
	private byte segundoParametro;
	private byte tercerParametro;
	
	public Parametros (String instruccion) {
		primerParametro = Byte.parseByte(instruccion.substring(1, 2),16);
		segundoParametro = Byte.parseByte(instruccion.substring(2, 3),16);
		tercerParametro = Byte.parseByte(instruccion.substring(3, 4),16);
	}

	public byte getPrimerParametro() {
		return primerParametro;
	}

	public byte getSegundoParametro() {
		return segundoParametro;
	}

	public byte getTercerParametro() {
		return tercerParametro;
	}
	
	public String getSegundoYTercerParametro(){
		try {
			String segundo = (Conversor.decimalToHexa(segundoParametro)).substring(1, 2);
			String tercero = (Conversor.decimalToHexa(tercerParametro)).substring(1, 2);
			return segundo + tercero;
		} catch (LimitesExcedidosConversorException e) {
			throw new RuntimeException(e);
		}
	}
}
