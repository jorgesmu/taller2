package ar.com.compumundohipermegared.almacenamiento;

public interface IMemoria {
	
	public void cargarMemoria(int fila, int columna, char dato) throws  LimiteExcedidoMemoriaException;
	
	public char getDatoMemoria(int fila, int columna) throws  LimiteExcedidoMemoriaException;

}
