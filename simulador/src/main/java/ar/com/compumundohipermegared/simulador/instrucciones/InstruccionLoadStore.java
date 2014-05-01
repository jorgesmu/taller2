package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

abstract class InstruccionLoadStore extends Instruccion {

	public InstruccionLoadStore(Parametros parametros) {
		super(parametros);
	}

	protected Cpu cpu = null;
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		cpu = cpuRecibida;
	}
}
