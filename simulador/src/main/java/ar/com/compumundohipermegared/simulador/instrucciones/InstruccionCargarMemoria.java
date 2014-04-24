package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

public class InstruccionCargarMemoria extends InstruccionLoadStore {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de carga/memoria");		
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		System.out.println("Cargando operandos una instruccion de carga/memoria");		
	}

}
