package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionShiftDerechaAritmetico extends InstruccionShift {
	
	public InstruccionShiftDerechaAritmetico(Parametros parametros) {
		super(parametros);
		System.out.print("Shift Derecha Aritmetico\n");
	}
	
	protected int getShift() {
		return Alu.shiftRightArithmetic(valorRegistro, cantVeces);
	}
	
}
