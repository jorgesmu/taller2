package ar.com.compumundohipermegared.almacenamiento;

public class UnidadDatoSimplePrecision {
	
	private byte dato;
	
	public synchronized void setDato(byte info){
		dato = info;
	}
	
	public synchronized byte getDato(){
		return dato;
	}

}
