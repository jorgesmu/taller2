package almacenamiento;

public class Registro {
	
	private Celda[] vcelda;
	private int tamanio;
	
	public Registro(int tam){
		tamanio = tam;
		vcelda = new Celda[tamanio];
		this.crearVacio();
	}		
	
	private void crearVacio(){
		
		for( int i = 0 ; i < tamanio; i++ ){
			Celda celda = new Celda();
			vcelda[i] = celda;
		}		
	}
	
	public int getTamanio(){
		return tamanio;
	}
	
	public void cargarPos(int pos, int dato) throws LimiteExcedidoVectorException{
		if( pos >= tamanio || pos < 0 ) throw new LimiteExcedidoVectorException();
		Celda celda = vcelda[pos];
		celda.setCelda(dato);
		
	}
	
	public int getDatoPos(int pos) throws LimiteExcedidoVectorException{
		if( pos >= tamanio || pos < 0 ) throw new LimiteExcedidoVectorException();
		Celda celda = vcelda[pos];
		return celda.getCelda();
		
	}

}
