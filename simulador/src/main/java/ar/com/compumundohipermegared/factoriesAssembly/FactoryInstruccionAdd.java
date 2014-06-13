package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionAdd extends FactoryInstruccion {

	public FactoryInstruccionAdd(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 1;		
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		
		String instruccion = new String("5");		
		
		if( misOperandos.get(lineaParseada[1]) == null || misOperandos.get(lineaParseada[2]) == null  || misOperandos.get(lineaParseada[3]) == null ){
			throw new InstruccionAssemblyInvalidaException();				
		}
					
		instruccion += misOperandos.get( lineaParseada[1]) + misOperandos.get(lineaParseada[2]) + misOperandos.get(lineaParseada[3])  ;		
	
		instrucciones[0] = instruccion;
		
		return instrucciones;
	}

}
