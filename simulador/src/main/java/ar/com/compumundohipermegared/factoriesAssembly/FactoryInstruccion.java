package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

public class FactoryInstruccion {
	
	Map<String, String> misLabels;
	Map<String, String> misOperandos;
	
	public FactoryInstruccion (Map<String, String> labels, Map<String, String> operandos) {
		misLabels = labels;
		misOperandos = operandos;
	}
	
}
