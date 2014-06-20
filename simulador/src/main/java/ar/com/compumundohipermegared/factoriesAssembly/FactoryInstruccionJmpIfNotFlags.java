package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionJmpIfNotFlags extends
		FactoryInstruccionJmpWithFlags {

	public FactoryInstruccionJmpIfNotFlags(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 5;
	}
	
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = getInstruccionesPrincipio ();
		return getInstruccionesFin (lineaParseada, pcActual, instrucciones);
	}
	
}
