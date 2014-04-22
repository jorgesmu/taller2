package ar.com.compumundohipermegared.almacenamiento;

public interface IMemoria {
	
	public void cargarMemoria(int fila, int columna, byte dato) throws  LimiteExcedidoMemoriaException;
	
	public byte getDatoMemoria(int fila, int columna) throws  LimiteExcedidoMemoriaException;

}
