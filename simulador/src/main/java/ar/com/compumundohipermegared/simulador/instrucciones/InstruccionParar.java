package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

public class InstruccionParar extends InstruccionFlujox {
	
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de parar");
		cpu.pararEjecucion();
	}
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		System.out.println("Cargando operandos una instruccion de parar");
	}
	
}
