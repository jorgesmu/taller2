package ar.com.compumundohipermegared.almacenamiento;

import java.util.Vector;

public class AreaRegistro {
	
	private Vector<RegistroSimplePrecision> vRegistro;
	private int tamanio;
	
	public AreaRegistro(int tam){
		tamanio = tam;
		vRegistro = new Vector<RegistroSimplePrecision>(tamanio);
		this.crearVacio();
	}		
	
	private void crearVacio(){
		
		for( int i = 0 ; i < tamanio; i++ ){
			RegistroSimplePrecision registroSimplePrecision = new RegistroSimplePrecision();
			vRegistro.add(registroSimplePrecision);
		}		
	}
	
	public synchronized int getTamanio(){
		return tamanio;
	}
	
	public synchronized void cargarRegistro(int pos, byte dato) throws LimiteExcedidoAreaRegistroException {
		if( pos >= tamanio || pos < 0 ) throw new LimiteExcedidoAreaRegistroException();
		RegistroSimplePrecision registroSimplePrecision = vRegistro.get(pos);
		registroSimplePrecision.setDato(dato);		
	}
	
	public synchronized byte getDatoRegistro(int pos) throws LimiteExcedidoAreaRegistroException{
		if( pos >= tamanio || pos < 0 ) throw new LimiteExcedidoAreaRegistroException();
		RegistroSimplePrecision registroSimplePrecision = vRegistro.get(pos);
		return registroSimplePrecision.getDato();
	}

}
