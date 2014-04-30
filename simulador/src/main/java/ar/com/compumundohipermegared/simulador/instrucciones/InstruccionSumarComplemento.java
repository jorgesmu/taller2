package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSumarComplemento extends InstruccionAlu{
	public InstruccionSumarComplemento(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de sumar complemento");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		System.out.println("Cargando operandos una instruccion de sumar complemento");		
	}
}
