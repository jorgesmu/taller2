package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

public abstract class Instruccion {
	
	public abstract void ejecutar();
	public abstract void cargarOperandos(Cpu cpuRecibida);
}
