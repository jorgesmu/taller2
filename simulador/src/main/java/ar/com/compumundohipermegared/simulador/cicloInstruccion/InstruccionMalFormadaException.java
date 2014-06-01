package ar.com.compumundohipermegared.simulador.cicloInstruccion;

public class InstruccionMalFormadaException extends Exception {

	public InstruccionMalFormadaException(String string) {
		super(string);
	}

	public InstruccionMalFormadaException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
