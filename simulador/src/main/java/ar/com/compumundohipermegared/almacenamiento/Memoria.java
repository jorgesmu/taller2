package ar.com.compumundohipermegared.almacenamiento;

import java.util.Vector;



public class Memoria {
	
	private Vector< Vector <Celda> > mCelda;
	static private int TAMANIO = 16;
	
	public Memoria(){
		mCelda = new Vector<Vector<Celda>>(TAMANIO);
		this.crearVacio();	
		
	}
	
	private void crearVacio(){
		
		for( int i = 0 ; i < TAMANIO; i++ ){
			
			Vector<Celda> vCelda = new Vector<Celda>(TAMANIO);
			
			for ( int j = 0 ; j < TAMANIO ; j++ ){
				Celda celda = new Celda();
				vCelda.add(celda);
								
			}
			mCelda.add(vCelda);
;		}				
	}
	
	public int getTamanio(){
		return TAMANIO;
		
	}
	
	public void cargarMemoria(int fila, int columna, char dato){
		Vector<Celda> vCelda = mCelda.get(fila);
		Celda celda = vCelda.get(columna);
		celda.setDato(dato);
			
	}

	public char getDatoMemoria(int fila, int columna) {
		Vector<Celda> vCelda = mCelda.get(fila);
		
		Celda celda = vCelda.get(columna);
		
		return celda.getDato();
	}

}
