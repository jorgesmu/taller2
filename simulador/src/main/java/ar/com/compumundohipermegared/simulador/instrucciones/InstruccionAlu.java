package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.Casteador;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

abstract class InstruccionAlu extends Instruccion{

	public InstruccionAlu(Parametros parametros) {
		super(parametros);
		casteador = new Casteador();
	}

	protected Cpu cpu = null;
	protected Casteador casteador = null;
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		cpu = cpuRecibida;
	}
}
