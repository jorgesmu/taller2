package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

public class InstruccionCopiar extends InstruccionAlu {

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de copiar");		
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		System.out.println("Cargando operandos una instruccion de copiar");		
	}

}
