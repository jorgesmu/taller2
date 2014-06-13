package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public abstract class FactoryInstruccion {
	
	Map<String, String> misLabels;
	Map<String, String> misOperandos;
	protected int CANTIDADINSTRUCCIONES;
	
	protected static int LONGITUDINMEDIATOS = 2;
	
	public FactoryInstruccion (Map<String, String> labels, Map<String, String> operandos) {
		misLabels = labels;
		misOperandos = operandos;
	}
	
	public abstract String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException;
	
	
	public  int getCantidadInstruccionesAEjecutar(){
		return CANTIDADINSTRUCCIONES;
	}
	
	public String validarInmediato(String valor) throws InstruccionAssemblyInvalidaException{
		Pattern pat = Pattern.compile("^[0-9A-F]*$");
		Matcher mat = pat.matcher(valor);
		if (!mat.matches()) {
			  throw new InstruccionAssemblyInvalidaException();
		}
		
		String prefijo = "";
		int diferencia = LONGITUDINMEDIATOS - valor.length();
		for (int i = 0 ; i < diferencia ; ++i) {
			prefijo += "0";
		}
		return prefijo + valor;
	}
	
}
