package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCargarMemoria extends InstruccionLoadStore {
	public InstruccionCargarMemoria(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de carga/memoria");		
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		System.out.println("primer parametro:" + _parametros.getPrimerParametro());
		System.out.println("segundo parametro:" + _parametros.getSegundoParametro());
		System.out.println("tercer parametro:" + _parametros.getTercerParametro());
		
		System.out.println("Cargando operandos una instruccion de carga/memoria");		
	}

}
