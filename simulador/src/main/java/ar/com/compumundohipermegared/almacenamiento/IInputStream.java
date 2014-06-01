package ar.com.compumundohipermegared.almacenamiento;

public interface IInputStream {
	
	public String readln () throws EndOfStreamException;

	public int numeroLineaActual();
	
}
