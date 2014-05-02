package ar.com.compumundohipermegared.almacenamiento;

import java.util.Vector;



public class MemoriaRam implements IMemoria {
	
	private Vector< Vector <Celda> > mCelda;
	private int tamanio;
	
	// como 'tamanio' se define en el constructor, esto esta sujeto con pinzas
	// pero no da tirar numeros magicos por toda la app
	public static int PUERTO_ENTRADA_CONTROL_FILA = 15; // F
	public static int PUERTO_ENTRADA_CONTROL_COLUMNA = 12; // C
	public static int PUERTO_ENTRADA_DATO_FILA = 15; // F
	public static int PUERTO_ENTRADA_DATO_COLUMNA = 13; // D
	
	public static int PUERTO_SALIDA_CONTROL_FILA = 15; // F
	public static int PUERTO_SALIDA_CONTROL_COLUMNA = 14; // E
	public static int PUERTO_SALIDA_DATO_FILA = 15; // F
	public static int PUERTO_SALIDA_DATO_COLUMNA = 15; // F
	
	public MemoriaRam(int tam){
		tamanio = tam;
		mCelda = new Vector<Vector<Celda>>(tamanio);
		this.crearVacio();	
		
	}
	
	private void crearVacio(){
		
		for( int i = 0 ; i < tamanio; i++ ){
			
			Vector<Celda> vCelda = new Vector<Celda>(tamanio);
			
			for ( int j = 0 ; j < tamanio ; j++ ){
				Celda celda = new Celda();
				vCelda.add(celda);
								
			}
			mCelda.add(vCelda);
		}				
	}
	
	public synchronized int getTamanio(){
		return tamanio;
		
	}
	
	public synchronized void cargarMemoria(int fila, int columna, byte dato) throws  LimiteExcedidoMemoriaException  {
		
		if( fila >= tamanio || fila < 0) throw new LimiteExcedidoMemoriaException();
		if( columna >= tamanio || columna < 0) throw new LimiteExcedidoMemoriaException();
		
		Vector<Celda> vCelda = mCelda.get(fila);
		Celda celda = vCelda.get(columna);
		celda.setDato(dato);
			
	}

	public synchronized byte getDatoMemoria(int fila, int columna) throws  LimiteExcedidoMemoriaException {
		if( fila >= tamanio || fila < 0) throw new LimiteExcedidoMemoriaException();
		if( columna >= tamanio || columna < 0) throw new LimiteExcedidoMemoriaException();
		
		Vector<Celda> vCelda = mCelda.get(fila);
		
		Celda celda = vCelda.get(columna);
		
		return celda.getDato();
	}
	
	
	
	/* ***********************************************************************
	 * IMPORTANTE:
	 * LOS METODOS QUE ESTAN DE ACA PARA ABAJO EXISTEN POR EL USO DE HILOS.
	 * SI SE LLAMA A LOS METODOS cargarMemoria Y getDatoMemoria DESDE AFUERA
	 * POR SEPARADO, PUEDE "METERSE" OTRO HILO EN EL MEDIO Y ROMPER TOD0.
	 * TAMBIEN SON DEFINITIVOS, ES DECIR, NO LES IMPORTA COMO ESTABA EL BIT
	 * DE CONTROL AL MOMENTO DE LEER O ESCRIBIR.
	 * ***********************************************************************/
	
	
	public synchronized void escribirDispositivoEntrada (byte dato) {
		try {
			cargarMemoria (PUERTO_ENTRADA_DATO_FILA, PUERTO_ENTRADA_DATO_COLUMNA, dato);
			cargarMemoria (PUERTO_ENTRADA_CONTROL_FILA, PUERTO_ENTRADA_CONTROL_COLUMNA, (byte) 1);
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized byte leerDispositivoEntrada () {
		try {
			byte dato = getDatoMemoria (PUERTO_ENTRADA_DATO_FILA, PUERTO_ENTRADA_DATO_COLUMNA);
			cargarMemoria (PUERTO_ENTRADA_CONTROL_FILA, PUERTO_ENTRADA_CONTROL_COLUMNA, (byte) 0);
			return dato;
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
		return (byte) -500;
	}
	
	public synchronized boolean tieneDatoDispositivoEntrada () {
		try {
			byte bit = getDatoMemoria (PUERTO_ENTRADA_CONTROL_FILA, PUERTO_ENTRADA_CONTROL_COLUMNA);
			return (bit == (byte)1);
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public synchronized void escribirDispositivoSalida (byte dato) {
		try {
			cargarMemoria (PUERTO_SALIDA_DATO_FILA, PUERTO_SALIDA_DATO_COLUMNA, dato);
			cargarMemoria (PUERTO_SALIDA_CONTROL_FILA, PUERTO_SALIDA_CONTROL_COLUMNA, (byte) 1);
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized byte leerDispositivoSalida () {
		try {
			byte dato = getDatoMemoria (PUERTO_SALIDA_DATO_FILA, PUERTO_SALIDA_DATO_COLUMNA);
			cargarMemoria (PUERTO_SALIDA_CONTROL_FILA, PUERTO_SALIDA_CONTROL_COLUMNA, (byte) 0);
			return dato;
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
		return (byte) -500;
	}
	
	public synchronized boolean tieneDatoDispositivoSalida () {
		try {
			byte bit = getDatoMemoria (PUERTO_SALIDA_CONTROL_FILA, PUERTO_SALIDA_CONTROL_COLUMNA);
			return (bit == (byte)1);
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
