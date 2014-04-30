package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

abstract class InstruccionFlujox extends Instruccion {

	Cpu cpu = null;
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		cpu = cpuRecibida;
	}
}
