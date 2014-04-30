package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSaltar extends InstruccionFlujo {
	
	public InstruccionSaltar(Parametros parametros) {
		super(parametros);
	}

	char direccion = '\0';
	
	
	/*public InstruccionSaltar ( direccionRecibida ) {
		//direccion = '\0'; // direccionRecibida
	}*/
	
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de salto");
		cpu.ejecutarSaltoA(direccion);
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		System.out.println("Cargando operandos una instruccion de salto");
	}
}
