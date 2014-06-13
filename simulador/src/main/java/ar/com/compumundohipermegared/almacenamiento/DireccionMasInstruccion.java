package ar.com.compumundohipermegared.almacenamiento;

public class DireccionMasInstruccion {
	
	// AMBOS EN HEXA
	
	private String direccion = null;
	private String instruccion = null;
	
	public DireccionMasInstruccion (String direccionRecibida, String instruccionRecibida) {
		direccion = direccionRecibida;
		instruccion = instruccionRecibida;
	}
	
	public synchronized String getDireccion() {
		return direccion;
	}
	
	public synchronized String getInstruccion() {
		return instruccion;
	}
}
