package ar.com.compumundohipermegared.simulador.cicloInstruccion;

public class Parametros {
	
	private byte primerParametro;
	private byte segundoParametro;
	private byte tercerParametro;
	
	public Parametros (String instruccion) {
		primerParametro = instruccion.substring(1, 2).getBytes()[0];
		segundoParametro = instruccion.substring(2, 3).getBytes()[0];
		tercerParametro = instruccion.substring(3, 4).getBytes()[0];
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
