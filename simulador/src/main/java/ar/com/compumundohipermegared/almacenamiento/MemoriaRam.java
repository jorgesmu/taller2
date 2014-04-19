package ar.com.compumundohipermegared.almacenamiento;

import java.util.Vector;



public class MemoriaRam implements IMemoria{
	
	private Vector< Vector <Celda> > mCelda;
	
	private int tamanio;
	
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
	
	public int getTamanio(){
		return tamanio;
		
	}
	
	public void cargarMemoria(int fila, int columna, char dato) throws  LimiteExcedidoMemoriaException  {
		
		if( fila >= tamanio || fila < 0) throw new LimiteExcedidoMemoriaException();
		if( columna >= tamanio || columna < 0) throw new LimiteExcedidoMemoriaException();
		
		Vector<Celda> vCelda = mCelda.get(fila);
		Celda celda = vCelda.get(columna);
		celda.setDato(dato);
			
	}

	public char getDatoMemoria(int fila, int columna) throws  LimiteExcedidoMemoriaException {
		if( fila >= tamanio || fila < 0) throw new LimiteExcedidoMemoriaException();
		if( columna >= tamanio || columna < 0) throw new LimiteExcedidoMemoriaException();
		
		Vector<Celda> vCelda = mCelda.get(fila);
		
		Celda celda = vCelda.get(columna);
		
		return celda.getDato();
	}

}
