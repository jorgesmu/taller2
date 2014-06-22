package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionRrl extends FactoryInstruccion {

	public FactoryInstruccionRrl(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 5;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];		
		
		if( misOperandos.get(lineaParseada[1]) == null ){
			throw new InstruccionAssemblyInvalidaException();				
		}
					
		instrucciones[0] = "2" + Compilador.REG_AUX_1 + "80" ;
		instrucciones[1] = "8" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + misOperandos.get(lineaParseada[1]);
		instrucciones[2] = "A" + Compilador.REG_AUX_1 + "16" ;
		instrucciones[3] = "A" + misOperandos.get(lineaParseada[1]) + "31" ;
		instrucciones[4] = "5" + misOperandos.get(lineaParseada[1]) + misOperandos.get(lineaParseada[1]) + Compilador.REG_AUX_1;
		
		return instrucciones;
	}

}
