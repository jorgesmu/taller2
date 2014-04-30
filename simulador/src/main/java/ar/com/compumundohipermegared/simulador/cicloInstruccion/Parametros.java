package ar.com.compumundohipermegared.simulador.cicloInstruccion;

public class Parametros {
	
	private byte primerParametro;
	private byte segundoParametro;
	private byte tercerParametro;
	
	public Parametros (String instruccion) {
		primerParametro = Byte.valueOf(instruccion.substring(1, 2));
		segundoParametro = Byte.valueOf(instruccion.substring(2, 3));
		tercerParametro = Byte.valueOf(instruccion.substring(3, 4));
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
}
