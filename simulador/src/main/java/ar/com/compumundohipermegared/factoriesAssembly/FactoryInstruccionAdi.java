package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionAdi extends FactoryInstruccion {

	public FactoryInstruccionAdi(Map<String, String> labels,Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 2;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		
		String instruccion = new String("2F");		
		
		if( misOperandos.get(lineaParseada[1]) == null || misOperandos.get(lineaParseada[2]) == null ){
			throw new InstruccionAssemblyInvalidaException();				
		}
		
		validarInmediato(lineaParseada[3]);	
		 
		instruccion += lineaParseada[3];	
	
		instrucciones[0] = instruccion;
		
		instruccion = "5" + misOperandos.get(lineaParseada[1]) + misOperandos.get(lineaParseada[2]) + "F";
		
		instrucciones[1] = instruccion;
		
		return instrucciones;	
	}

}
