package ar.com.compumundohipermegared.simulador.instrucciones;

import java.lang.instrument.Instrumentation;

public class InstruccionCargarMemoria extends Instruccion{
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de carga/memoria");		
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de carga/memoria");		
	}

}
