package ar.com.compumundohipermegared.factoriesAssembly;


import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionJmp extends FactoryInstruccionJump {

	public FactoryInstruccionJmp(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 2;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
				
		String xy = misLabels.get(lineaParseada[1]);
		if (xy == null) {
			xy = lineaParseada[1];
			if (xy.length() > 2) throw new InstruccionAssemblyInvalidaException();
		}
		
		String inmediato = validarInmediato(xy);
		String xyRelativa = relativizarDireccion(inmediato, pcActual);
		
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = "400" + Compilador.REG_AUX_1;
		instrucciones[1] = "B" + Compilador.REG_AUX_1 + xyRelativa;
		return instrucciones;
	}

	

}
