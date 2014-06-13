package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionStm extends FactoryInstruccion {

	public FactoryInstruccionStm(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 1;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		if( misOperandos.get(lineaParseada[1]) == null ){
			throw new InstruccionAssemblyInvalidaException();
		}
		String inmediato = validarInmediato(lineaParseada[2]);
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = "3" + misOperandos.get(lineaParseada[1]) + inmediato;
		
		return instrucciones;
	}

}
