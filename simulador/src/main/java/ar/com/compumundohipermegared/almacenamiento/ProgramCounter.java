package ar.com.compumundohipermegared.almacenamiento;

public class ProgramCounter extends RegistroDoblePrecision {
	
	// Esto es feo pero java no me deja overridear operadores
	// ProgramCounter cuenta de a instrucciones (que ocupan 2 bytes)
	// entonces hacer pc.sumar(1), le suma 2 bytes al registro
	public static ProgramCounter sumar (ProgramCounter otroPC, int valor) {
		ProgramCounter nuevoPC = new ProgramCounter();
		nuevoPC.setDato ((char) (otroPC.getDato() + (valor*2)));
		return nuevoPC;
	}
	
	// incrementa el pc siguiendo el criterio del sumar
	public void inc (int valor) {
		dato += (valor*2);
	}
}
