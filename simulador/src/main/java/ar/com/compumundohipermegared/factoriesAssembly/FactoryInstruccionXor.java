package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionXor extends FactoryInstruccion {

	public FactoryInstruccionXor(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 1;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String rd = misOperandos.get(lineaParseada[1]);
		String rs = misOperandos.get(lineaParseada[2]);
		String rt = misOperandos.get(lineaParseada[3]);
		if ((rd == null) || (rs == null) || (rt == null)) throw new InstruccionAssemblyInvalidaException();
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = new String("9" + rd + rs + rt);
		
		return instrucciones;
	}

}
