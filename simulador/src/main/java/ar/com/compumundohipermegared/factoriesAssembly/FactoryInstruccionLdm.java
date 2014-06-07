package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionLdm extends FactoryInstruccion {

	public FactoryInstruccionLdm(Map<String, String> labels,Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 1;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		
		String instruccion = new String("1");		
		
		if( misOperandos.get(lineaParseada[1]) == null ){
			throw new InstruccionAssemblyInvalidaException();				
		}
		
		validarInmediato(lineaParseada[2]);
		
		instruccion += misOperandos.get( lineaParseada[1]) + lineaParseada[2];		
	
		instrucciones[0] = instruccion;
		
		return instrucciones;
	}

}
