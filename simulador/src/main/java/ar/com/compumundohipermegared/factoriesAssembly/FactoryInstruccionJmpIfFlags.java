package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionJmpIfFlags extends FactoryInstruccionJmpWithFlags {

	public FactoryInstruccionJmpIfFlags(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 6;
	}
	
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String[] instrucciones  = getInstruccionesPrincipio ();
		instrucciones[3] = "9" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + Compilador.REG_AUX_2;
		return getInstruccionesFin (lineaParseada, pcActual, instrucciones);
	}
}
