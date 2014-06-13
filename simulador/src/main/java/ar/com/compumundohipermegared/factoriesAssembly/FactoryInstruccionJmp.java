package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;

public class FactoryInstruccionJmp extends FactoryInstruccion {

	public FactoryInstruccionJmp(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 1;
	}

	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String rd = misOperandos.get(lineaParseada[1]);
		if (rd == null) throw new InstruccionAssemblyInvalidaException();
		
		String xy = misLabels.get(lineaParseada[2]);
		if (xy == null) {
			xy = lineaParseada[2];
			if (xy.length() > 2) throw new InstruccionAssemblyInvalidaException();
		}
		
		String inmediato = validarInmediato(xy);
		String xyRelativa = relativizarDireccion(inmediato, pcActual);
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = new String("B" + rd + xyRelativa);
		return instrucciones;
	}

	private String relativizarDireccion(String xy, String pcActual) {
		int xyInt = Integer.parseInt(xy,16);
		int pcInt = Integer.parseInt(pcActual,16);
		int dirRelativa = xyInt - pcInt;
		try {
			return (Conversor.decimalToHexa(dirRelativa));
		} catch (LimitesExcedidosConversorException e) {
			return pcActual;
		}
	}

}
