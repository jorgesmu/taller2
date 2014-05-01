package ar.com.compumundohipermegared.simulador.cicloInstruccion;

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
		return Byte.toString(segundoParametro) + Byte.toString(tercerParametro);
	}
}
