package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionJmp extends FactoryInstruccion {

	public FactoryInstruccionJmp(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 1;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada) throws InstruccionAssemblyInvalidaException {
		String rd = misOperandos.get(lineaParseada[1]);
		String xy = lineaParseada[2];
		if ((rd == null) || (xy.length() != 2)) throw new InstruccionAssemblyInvalidaException();
		validarInmediato(xy);
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = new String("B" + rd + lineaParseada[2]);
		
		return instrucciones;
	}

}
