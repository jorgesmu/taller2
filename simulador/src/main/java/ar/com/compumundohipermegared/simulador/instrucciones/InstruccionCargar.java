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
	}

	@Override
	public void ejecutar() {
		cpu.escribirRegistro(idRegistroDestino, operandoInmediato);
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);

		try {
			operandoInmediato = (byte) Conversor.complementoDosADecimal(_parametros.getSegundoYTercerParametro());
		} catch (LimitesExcedidosConversorException e) {
			throw new RuntimeException(e);
		}
		idRegistroDestino = _parametros.getPrimerParametro();
	}
}
