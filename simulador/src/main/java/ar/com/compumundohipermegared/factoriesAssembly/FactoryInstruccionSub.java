package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionSub extends FactoryInstruccion {

	public FactoryInstruccionSub(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 5;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String rd = misOperandos.get(lineaParseada[1]);
		String rs = misOperandos.get(lineaParseada[2]);
		String rt = misOperandos.get(lineaParseada[3]);
		if ((rd == null) || (rs == null) || (rt == null)) throw new InstruccionAssemblyInvalidaException();
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = new String("2FFF");
		instrucciones[1] = new String("9F" + rt + "F");
		instrucciones[2] = new String("2E01");
		instrucciones[3] = new String("5FFE");
		instrucciones[4] = new String("5" + rd + rs + "F");
		
		return instrucciones;
	}

}
