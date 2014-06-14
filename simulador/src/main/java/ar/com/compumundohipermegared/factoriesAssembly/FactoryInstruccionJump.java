package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

public abstract class FactoryInstruccionJump extends FactoryInstruccion {
	
	
	protected String bitDeFlagsComparar;
	protected String opCodeByTypeJump;
	public FactoryInstruccionJump(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
				
	}
	
	protected String[] getInstruccionesJump(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String xy = misLabels.get(lineaParseada[1]);
		if (xy == null) {
			xy = lineaParseada[1];
			if (xy.length() > 2) throw new InstruccionAssemblyInvalidaException();
		}
		
		String inmediato = validarInmediato(xy);
		String xyRelativa = relativizarDireccion(inmediato, pcActual);
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];		
		
		instrucciones[0] = "40" + Cpu.REG_FLAGS + Compilador.REG_AUX_1;
		instrucciones[1] = "2" + Compilador.REG_AUX_2 +  bitDeFlagsComparar;
		instrucciones[2] = "8" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + Compilador.REG_AUX_2;
		instrucciones[3] = opCodeByTypeJump + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + Compilador.REG_AUX_2;
		instrucciones[4] = "5" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + "0";
		instrucciones[5] = "B" + Compilador.REG_AUX_1 + xyRelativa;
		
		return instrucciones;
	}
	
	protected String relativizarDireccion(String xy, String pcActual) {
		int xyInt = Integer.parseInt(xy,16);
		int pcInt = Integer.parseInt(pcActual, 16);
		
		int dirRelativa = xyInt - pcInt;
		try {
			return (Conversor.decimalToHexa(dirRelativa));
		} catch (LimitesExcedidosConversorException e) {
			return pcActual;
		}
	}
	

}
