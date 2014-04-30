package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionXor extends InstruccionAlu {
	public InstruccionXor(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de Xor");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		System.out.println("Cargando operandos una instruccion de Xor");		
	}
}
