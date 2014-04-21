package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.lang.instrument.Instrumentation;

public class InstruccionCargarMemoria extends Instruccion{
	@Override
	void ejecutar() {
		System.out.println("Ejecutando una instruccion de carga/memoria");		
	}

	@Override
	void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de carga/memoria");		
	}

}
