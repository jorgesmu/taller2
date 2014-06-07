package ar.com.compumundohipermegared.compilacion;

import java.util.Map;

import ar.com.compumundohipermegared.factoriesAssembly.FactoryInstruccion;

public class DecoderAssembly {
	
	Map<String, String> misLabels;
	Map<String, FactoryInstruccion> instrucciones;
	Map<String, String> operandos;
	
	public DecoderAssembly(Map<String, String> labels) {
		misLabels = labels;
		// seguir
	}

	public String[] decodificar(String[] lineaParseada) {
		// TODO Auto-generated method stub
		return lineaParseada;
	}

	public int cantidadInstrucciones(String string) {
		// TODO Auto-generated method stub
		return 1;
	}

}
