package ar.com.compumundohipermegared.almacenamiento;

public class AreaRegistroCpu {
	ProgramCounter pc;
	
	public AreaRegistroCpu () {
		pc = new ProgramCounter();
	}
	
	public synchronized void sumarAlPC (char direccionRelativa) {
		pc.sumar(direccionRelativa);
	}
	
	public synchronized void setPC(char direccion) {
		pc.setDato (direccion);
	}

	public synchronized ProgramCounter getPC() {
		return pc;
	}


	public synchronized void incrementarPC() {
		pc.inc(1);
	}

	public synchronized void decrementarPC() {
		pc.dec(1);
	}

}
