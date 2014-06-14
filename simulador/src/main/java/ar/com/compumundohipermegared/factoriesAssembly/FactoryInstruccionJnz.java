package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionJnz extends FactoryInstruccionJump {

	public FactoryInstruccionJnz(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 6;
		
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		bitDeFlagsComparar = new String("01");
		opCodeByTypeJump = new String("8");
		return this.getInstruccionesJump(lineaParseada, pcActual);
	}

}
