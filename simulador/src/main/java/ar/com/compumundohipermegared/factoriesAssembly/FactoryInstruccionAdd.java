package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionAdd extends FactoryInstruccion {

	public FactoryInstruccionAdd(Map<String, String> labels,
			Map<String, String> operandos) {
		super(labels, operandos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada)
			throws InstruccionAssemblyInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

}
