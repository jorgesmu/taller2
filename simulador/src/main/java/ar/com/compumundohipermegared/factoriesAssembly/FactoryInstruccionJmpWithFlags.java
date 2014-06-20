package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

public abstract class FactoryInstruccionJmpWithFlags extends FactoryInstruccionJmp {
	
	protected int bitDeFlagsComparar;
	
	public FactoryInstruccionJmpWithFlags(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
	}
	
	protected String[] getInstruccionesPrincipio () {
		String bitFlagHexa = "00";
		try {
			bitFlagHexa = Conversor.decimalToHexa(bitDeFlagsComparar);
		} catch (LimitesExcedidosConversorException e) {
			e.printStackTrace();
		}
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		if (CANTIDADINSTRUCCIONES >= 3) { // este if es solo para que no se rompa todo si se definen mal las constantes
			instrucciones[0] = "40" + Cpu.REG_FLAGS + Compilador.REG_AUX_1;
			instrucciones[1] = "2" + Compilador.REG_AUX_2 + bitFlagHexa;
			instrucciones[2] = "8" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + Compilador.REG_AUX_2;
		}
		
		return instrucciones;
	}
	
	protected String [] getInstruccionesFin (String[] lineaParseada, String pcActual, String[] instrucciones) throws InstruccionAssemblyInvalidaException {
		String xyRelativa = obtenerDireccionValida(lineaParseada[1], pcActual);
		instrucciones[CANTIDADINSTRUCCIONES - 2] = "5" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + "0";
		instrucciones[CANTIDADINSTRUCCIONES - 1] = "B" + Compilador.REG_AUX_1 + xyRelativa;
		
		return instrucciones;
	}
	
}
