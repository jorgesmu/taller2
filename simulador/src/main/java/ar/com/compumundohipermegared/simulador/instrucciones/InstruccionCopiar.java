package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCopiar extends InstruccionAlu {
	byte dato;
	int idRegistroDestino;
	public InstruccionCopiar(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		cpu.EscribirRegistro(idRegistroDestino, dato);
		System.out.println("Ejecutando una instruccion de copiar");		
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		int idFuente = _parametros.getSegundoParametro();
		dato = cpu.ObtenerRegsitro(idFuente);
		idRegistroDestino = _parametros.getTercerParametro();
		System.out.println("Cargando operandos una instruccion de copiar");		
	}

}
