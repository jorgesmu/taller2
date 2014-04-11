package ar.com.compumundohipermegared.almacenamiento;

import java.util.Vector;

public class AreaRegistro {
	
	private Vector<Registro> vRegistro;
	static private int TAMANIO = 16;
	
	public AreaRegistro(){
		vRegistro = new Vector<Registro>(TAMANIO);
		this.crearVacio();
	}		
	
	private void crearVacio(){
		
		for( int i = 0 ; i < TAMANIO; i++ ){
			Registro registro = new Registro();
			vRegistro.add(registro);
			}		
	}
	
	public int getTamanio(){
		return TAMANIO;
	}
	
	public void cargarRegistro(int pos, char dato) throws LimiteExcedidoAreaRegistroException {
		if( pos >= TAMANIO || pos < 0 ) throw new LimiteExcedidoAreaRegistroException();
		Registro registro = vRegistro.get(pos);
		registro.setDato(dato);		
	}
	
	public int getDatoRegistro(int pos) throws LimiteExcedidoAreaRegistroException{
		if( pos >= TAMANIO || pos < 0 ) throw new LimiteExcedidoAreaRegistroException();
		Registro registro = vRegistro.get(pos);
		return registro.getDato();
		
	}

}
