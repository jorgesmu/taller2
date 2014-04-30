package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

abstract class InstruccionFlujo extends Instruccion {

	public InstruccionFlujo(Parametros parametros) {
		super(parametros);
	}

	Cpu cpu = null;
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		cpu = cpuRecibida;
	}
}
