package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public abstract class Instruccion {
	
	public abstract void ejecutar();
	public abstract void cargarOperandos(Cpu cpuRecibida);
	protected Parametros _parametros;
	public Instruccion(Parametros parametros){
		_parametros = parametros;
	}
}
