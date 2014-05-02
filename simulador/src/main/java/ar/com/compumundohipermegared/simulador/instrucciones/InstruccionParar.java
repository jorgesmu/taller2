package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionParar extends InstruccionFlujo {
	
	public InstruccionParar(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		cpu.pararEjecucion();
		System.out.println("Se para la ejecucion");
	}
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
	}
	
}
