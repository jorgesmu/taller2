package ar.com.compumundohipermegared.almacenamiento;

public class AreaRegistroCpu {
	ProgramCounter pc;
	
	public AreaRegistroCpu () {
		pc = new ProgramCounter();
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

}
