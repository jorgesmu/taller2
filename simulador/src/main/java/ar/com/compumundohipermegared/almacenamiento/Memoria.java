package ar.com.compumundohipermegared.almacenamiento;



public class Memoria {
	
	private Celda[][] mCelda;
	private int tamanio;
	
	public Memoria(int tam){
		tamanio = tam;
		mCelda = new Celda[tamanio][tamanio];
		this.crearVacio();	
		
	}
	
	private void crearVacio(){
		
		for( int i = 0 ; i < tamanio; i++ ){
			for ( int j = 0 ; j < tamanio ; j++ ){
				Celda celda = new Celda();
				mCelda[i][j] = celda;
			}
		}				
	}
	
	public int getTamanio(){
		return tamanio;
		
	}
	
	public void cargarMemoria(int fila, int columna, int dato){
		Celda celda = mCelda[fila][columna];
		celda.setCelda(dato);
			
	}

	public int getDatoMemoria(int fila, int columna){
		Celda celda = mCelda[fila][columna];
		
		return celda.getCelda();
	}

}
