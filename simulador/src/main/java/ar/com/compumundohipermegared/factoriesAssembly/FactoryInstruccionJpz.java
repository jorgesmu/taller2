package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;

public class FactoryInstruccionJpz extends FactoryInstruccionJmpIfFlags {

	public FactoryInstruccionJpz(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		bitDeFlagsComparar = Alu.BIT_FLAG_ZERO;
	}

}
	
	
	


