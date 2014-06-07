package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public abstract class FactoryInstruccion {
	
	Map<String, String> misLabels;
	Map<String, String> misOperandos;
	protected int CANTIDADINSTRUCCIONES;
	
	public FactoryInstruccion (Map<String, String> labels, Map<String, String> operandos) {
		misLabels = labels;
		misOperandos = operandos;
	}
	
	public abstract String[] getInstrucciones(String[] lineaParseada) throws InstruccionAssemblyInvalidaException;
	
	
	public  int getCantidadInstruccionesAEjecutar(){
		return CANTIDADINSTRUCCIONES;
	}
	
	public void validarInmediato(String valor) throws InstruccionAssemblyInvalidaException{
		Pattern pat = Pattern.compile("^[0-9A-F]*$");
		Matcher mat = pat.matcher(valor);
		if (!mat.matches()) {
			  throw new InstruccionAssemblyInvalidaException();
		}		
	}
	
}
