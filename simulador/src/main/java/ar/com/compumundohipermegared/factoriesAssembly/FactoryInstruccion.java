package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;

public abstract class FactoryInstruccion {
	
	Map<String, String> misLabels;
	Map<String, String> misOperandos;
	protected int CANTIDADINSTRUCCIONES;
	
	protected static int LONGITUDINMEDIATOS = 2;
	protected static String ERROR_BASE_INMEDIATO = "El número no está expresado en base decimal o hexadecimal.";
	protected static String ERROR_FUERA_LIMITE_INMEDIATO = "El número excede los límites permitidos.";
	
	public FactoryInstruccion (Map<String, String> labels, Map<String, String> operandos) {
		misLabels = labels;
		misOperandos = operandos;
	}
	
	public abstract String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException;
	
	
	public  int getCantidadInstruccionesAEjecutar(){
		return CANTIDADINSTRUCCIONES;
	}
	
	public String validarInmediato(String valor) throws InstruccionAssemblyInvalidaException{
		String inmediato = new String (valor);
		Pattern pat = Pattern.compile("^[0-9]*$");
		Matcher mat = pat.matcher(valor);
		Pattern pat2 = Pattern.compile("^0x[0-9A-F]*$");
		Matcher mat2 = pat2.matcher(valor);
		
		if ( !mat.matches() && !mat2.matches() ) {
			  throw new InstruccionAssemblyInvalidaException(ERROR_BASE_INMEDIATO);
		} else if ( mat2.matches() ) { // es hexa
			inmediato = valor.substring(2,valor.length());
		} else { // es decimal
			int valorInt = Integer.parseInt(valor);
			try {
				inmediato = Conversor.decimalToHexa(valorInt);
			} catch (LimitesExcedidosConversorException e) {
				throw new InstruccionAssemblyInvalidaException(ERROR_FUERA_LIMITE_INMEDIATO);
			}
		}
		
		return completarInmediato(inmediato);
	}

	private String completarInmediato(String inmediato) throws InstruccionAssemblyInvalidaException {
		int diferencia = LONGITUDINMEDIATOS - inmediato.length();
		
		String prefijo = "";
		for (int i = 0 ; i < diferencia ; ++i) {
			prefijo += "0";
		}
		
		return prefijo + inmediato;
	}
		
	
}
	
