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
	
	protected String obtenerDireccionValida (String direccionRecibida, String pcActual) throws InstruccionAssemblyInvalidaException {
		String xy = misLabels.get(direccionRecibida);
		if (xy == null) {
			xy = direccionRecibida;
			if (xy.length() > 2) throw new InstruccionAssemblyInvalidaException();
		}
		
		String inmediato = validarInmediato(xy);
		return relativizarDireccion(inmediato, pcActual);
	}
	
	private String relativizarDireccion(String xy, String pcActual) {
		int xyInt = Integer.parseInt(xy,16);
		int pcInt = Integer.parseInt(pcActual, 16);
		
		pcInt += 2 * (CANTIDADINSTRUCCIONES - 1);
		int dirRelativa = xyInt - pcInt;
		try {
			return (Conversor.decimalToHexa(dirRelativa));
		} catch (LimitesExcedidosConversorException e) {
			return pcActual;
		}
	}
	
	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		String xyRelativa = obtenerDireccionValida(lineaParseada[1], pcActual);
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = "B" + "0" + xyRelativa;
		return instrucciones;
	}

}
