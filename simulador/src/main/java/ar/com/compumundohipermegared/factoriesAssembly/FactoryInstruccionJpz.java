package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionJpz extends FactoryInstruccionJump {

	public FactoryInstruccionJpz(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 6;
	}

	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		bitDeFlagsComparar = new String("01");
		opCodeByTypeJump = new String("9");
		return this.getInstruccionesJump(lineaParseada, pcActual);
		
	}

}
	
	
	


