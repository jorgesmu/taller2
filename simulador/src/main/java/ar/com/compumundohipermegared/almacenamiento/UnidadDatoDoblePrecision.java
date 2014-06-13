package ar.com.compumundohipermegared.almacenamiento;

public class UnidadDatoDoblePrecision {
	protected char dato;
	
	public synchronized void setDato(char info){
		dato = info;
	}
	
	public synchronized char getDato(){
		return dato;
	}

}
