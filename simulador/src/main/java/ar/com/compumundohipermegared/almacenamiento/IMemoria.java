package ar.com.compumundohipermegared.almacenamiento;

public interface IMemoria {
	
	public void cargarMemoria(int fila, int columna, byte dato) throws  LimiteExcedidoMemoriaException;
	
	public byte getDatoMemoria(int fila, int columna) throws  LimiteExcedidoMemoriaException;
	
	
	/* ***********************************************************************
	 * IMPORTANTE:
	 * LOS METODOS QUE ESTAN DE ACA PARA ABAJO EXISTEN POR EL USO DE HILOS.
	 * SI SE LLAMA A LOS METODOS cargarMemoria Y getDatoMemoria DESDE AFUERA
	 * POR SEPARADO, PUEDE "METERSE" OTRO HILO EN EL MEDIO Y ROMPER TOD0.
	 * TAMBIEN SON DEFINITIVOS, ES DECIR, NO LES IMPORTA COMO ESTABA EL BIT
	 * DE CONTROL AL MOMENTO DE LEER O ESCRIBIR.
	 * ***********************************************************************/
	
	public void escribirDispositivoEntrada (byte dato);
	public byte leerDispositivoEntrada ();
	public boolean tieneDatoDispositivoEntrada ();
	
	public void escribirDispositivoSalida (byte dato);
	public byte leerDispositivoSalida ();
	public boolean tieneDatoDispositivoSalida ();
	
}
