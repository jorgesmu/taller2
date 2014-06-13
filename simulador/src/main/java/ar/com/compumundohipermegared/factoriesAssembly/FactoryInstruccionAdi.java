package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionAdi extends FactoryInstruccion {

	public FactoryInstruccionAdi(Map<String, String> labels,Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 2;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		if( misOperandos.get(lineaParseada[1]) == null || misOperandos.get(lineaParseada[2]) == null ){
			throw new InstruccionAssemblyInvalidaException();				
		}
		String inmediato = validarInmediato(lineaParseada[3]);
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = "2F" + inmediato;
		instrucciones[1] = "5" + misOperandos.get(lineaParseada[1]) + misOperandos.get(lineaParseada[2]) + "F";
		
		return instrucciones;	
	}

}
