package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionNot extends FactoryInstruccion {

	public FactoryInstruccionNot(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 2;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		
		String instruccion = new String("2FFF");		
		
		if( misOperandos.get(lineaParseada[1]) == null || misOperandos.get(lineaParseada[2]) == null){
			throw new InstruccionAssemblyInvalidaException();				
		}
		instrucciones[0] = instruccion;
		
	
		instruccion = "9" + misOperandos.get( lineaParseada[1]) + misOperandos.get(lineaParseada[2]) + "F"  ;		
		
		instrucciones[1] = instruccion;
			
		return instrucciones;
	}

}
