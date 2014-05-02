package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.OverFlowCasteadorException;
import ar.com.compumundohipermegared.conversor.UnderFlowCasteadorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSumarFP extends InstruccionAlu {
	double operando1, operando2;
	int idRegistroDestino;
	
	public InstruccionSumarFP(Parametros parametros) {
		super(parametros);
		operando1 = 0.0;
		operando2 = 0.0;
		idRegistroDestino = 0;
	}

	@Override
	public void ejecutar() {
		double resultado = Alu.plus(operando1, operando2);
		try {
			char resultadoCasteado = casteador.puntoFlotante(resultado);
			cpu.escribirRegistro(idRegistroDestino, (byte)resultadoCasteado);
		} catch (OverFlowCasteadorException e) {
			throw new RuntimeException(e);
		} catch (UnderFlowCasteadorException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		
		int dirPrimerOperando, dirSegundoOperando;
		idRegistroDestino = _parametros.getPrimerParametro();
		dirPrimerOperando = _parametros.getSegundoParametro();
		dirSegundoOperando = _parametros.getTercerParametro();
		byte registro1 = cpu.obtenerRegistro(dirPrimerOperando);
		byte registro2 = cpu.obtenerRegistro(dirSegundoOperando);
		
		try {
			operando1 = casteador.puntoFlotante(registro1);
			operando2 = casteador.puntoFlotante(registro2);
		} catch (OverFlowCasteadorException e) {
			throw new RuntimeException(e);
		} catch (UnderFlowCasteadorException e) {
			throw new RuntimeException(e);
		}
	}
}
