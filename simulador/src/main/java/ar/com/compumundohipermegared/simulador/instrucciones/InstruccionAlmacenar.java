package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionAlmacenar extends InstruccionLoadStore {

	public InstruccionAlmacenar(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de almacenamiento");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		System.out.println("Cargando operandos una instruccion de almacenamiento");		
	}

}
