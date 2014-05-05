package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCargar extends InstruccionAlu {
	byte operandoInmediato;
	byte idRegistroDestino;
	
	public InstruccionCargar(Parametros parametros) {
		super(parametros);
		System.out.print("Carga Registro\n");
	}

	@Override
	public void ejecutar() {
		cpu.escribirRegistro(idRegistroDestino, operandoInmediato);
		System.out.print("Registro: " + idRegistroDestino + " = " + operandoInmediato + "\n");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		idRegistroDestino = _parametros.getPrimerParametro();
		try {
			operandoInmediato = (byte) Conversor.complementoDosADecimal(_parametros.getSegundoYTercerParametro());
		} catch (LimitesExcedidosConversorException e) {
			throw new RuntimeException(e);
		}
		
	}
}
