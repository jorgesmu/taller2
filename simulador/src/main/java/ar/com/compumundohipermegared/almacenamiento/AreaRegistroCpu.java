package ar.com.compumundohipermegared.almacenamiento;

public class AreaRegistroCpu {
	ProgramCounter pc;
	
	public AreaRegistroCpu () {
		pc = new ProgramCounter();
	}
	
	public void sumarAlPC (char direccionRelativa) {
		pc.sumar(direccionRelativa);
	}
	
	public void setPC(char direccion) {
		pc.setDato (direccion);
	}

	public ProgramCounter getPC() {
		return pc;
	}


	public void incrementarPC() {
		pc.inc(1);
	}

	public void decrementarPC() {
		pc.dec(1);
	}

}
