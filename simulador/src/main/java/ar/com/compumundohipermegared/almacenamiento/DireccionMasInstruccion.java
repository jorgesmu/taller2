package ar.com.compumundohipermegared.almacenamiento;

public class DireccionMasInstruccion {
	
	// AMBOS EN HEXA
	
	public String direccion = null;
	public String instruccion = null;
	
	public DireccionMasInstruccion (String direccionRecibida, String instruccionRecibida) {
		direccion = direccionRecibida;
		instruccion = instruccionRecibida;
	}

	public DireccionMasInstruccion(String linea) {
		// TODO Auto-generated constructor stub
	}
}
