package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionShiftIzquierdaZeroFill extends InstruccionShift {
	
	public InstruccionShiftIzquierdaZeroFill(Parametros parametros) {
		super(parametros);
		System.out.print("Shift Izquierda Zero Fill\n");
	}
	
	protected int getShift() {
		return Alu.shiftLeftZeroFill(valorRegistro, cantVeces);
	}
	
}
