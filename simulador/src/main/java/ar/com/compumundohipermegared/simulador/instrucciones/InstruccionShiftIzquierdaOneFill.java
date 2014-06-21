package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionShiftIzquierdaOneFill extends InstruccionShift {
	
	public InstruccionShiftIzquierdaOneFill(Parametros parametros) {
		super(parametros);
		System.out.print("Shift Izquierda One Fill\n");
	}
	
	protected int getShift() {
		return Alu.shiftLeftOneFill(valorRegistro, cantVeces);
	}
	
}
