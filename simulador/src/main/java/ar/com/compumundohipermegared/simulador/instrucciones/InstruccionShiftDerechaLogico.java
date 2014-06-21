package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionShiftDerechaLogico extends InstruccionShift {
	
	public InstruccionShiftDerechaLogico(Parametros parametros) {
		super(parametros);
		System.out.print("Shift Derecha Logico\n");
	}
	
	protected int getShift() {
		return Alu.shiftRightLogic(valorRegistro, cantVeces);
	}
	
}
