package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionSr1 extends FactoryInstruccion {

	public FactoryInstruccionSr1(Map<String, String> labels, 	Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 3;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];		
		
		if( misOperandos.get(lineaParseada[1]) == null ){
			throw new InstruccionAssemblyInvalidaException();				
		}
					
		instrucciones[0] = "A" + misOperandos.get(lineaParseada[1]) + "11" ;
		instrucciones[1] = "2" + Compilador.REG_AUX_1  + "80" ;
		instrucciones[2] = "7" + misOperandos.get(lineaParseada[1]) + misOperandos.get(lineaParseada[1]) + Compilador.REG_AUX_1  ;
		
		return instrucciones;
	}

}
