package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCargar extends InstruccionLoadStore {
	public InstruccionCargar(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de cargar");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		System.out.println("Cargando operandos una instruccion de cargar");		
	}
}
