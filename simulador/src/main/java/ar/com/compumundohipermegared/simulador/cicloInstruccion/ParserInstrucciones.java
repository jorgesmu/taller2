package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.compumundohipermegared.simulador.instrucciones.Instruccion;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionAlmacenar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionAnd;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionCargar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionCargarMemoria;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionCopiar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionOr;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionParar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionShiftDerechaAritmetico;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionSaltar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionShiftDerechaLogico;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionShiftIzquierdaOneFill;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionShiftIzquierdaZeroFill;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionSumarComplemento;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionSumarFP;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionXor;

public class ParserInstrucciones {
	public static Instruccion decodificar(String instruccion) throws Exception {
		validarFormato(instruccion);
		String opcode = instruccion.substring(0, 1);
		Instruccion resultado = null;
		
		switch (opcode) {
		case "1":
			resultado = decodificarCargarMemoria(instruccion);
			break;
		case "2":
			resultado = decodificarCargar(instruccion);
			break;
		case "3":
			resultado = decodificarAlmacenar(instruccion);
			break;
		case "4":
			resultado = decodificarCopiar(instruccion);
			break;
		case "5":
			resultado = decodificarSumarComplemento(instruccion);
			break;
		case "6":
			resultado = decodificarSumarFlotingPoint(instruccion);
			break;
		case "7":
			resultado = decodificarOr(instruccion);
			break;
		case "8":
			resultado = decodificarAnd(instruccion);
			break;
		case "9":
			resultado = decodificarXor(instruccion);
			break;
		case "A":
			resultado = decodificarShift(instruccion);
			break;
		case "B":
			resultado = decodificarSaltar(instruccion);
			break;
		case "C":
			resultado = decodificarParar(instruccion);
			break;
		}
		return resultado;
	}
	
	
	private static Instruccion decodificarCargarMemoria(String instruccion){
		return new InstruccionCargarMemoria(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarCargar(String instruccion){
		return new InstruccionCargar(new Parametros(instruccion));
	}	
	
	private static Instruccion decodificarAlmacenar(String instruccion){
		return new InstruccionAlmacenar(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarCopiar(String instruccion){
		return new InstruccionCopiar(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarSumarComplemento(String instruccion){
		return new InstruccionSumarComplemento(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarSumarFlotingPoint(String instruccion){
		return new InstruccionSumarFP(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarOr(String instruccion){
		return new InstruccionOr(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarAnd(String instruccion){
		return new InstruccionAnd(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarXor(String instruccion){
		return new InstruccionXor(new Parametros(instruccion));
	}
	
	private static Instruccion decodificarShift(String instruccion) throws Exception {
		char tipo = instruccion.charAt(2);
		if (tipo == '0') return new InstruccionShiftDerechaAritmetico(new Parametros(instruccion));
		if (tipo == '1') return new InstruccionShiftDerechaLogico(new Parametros(instruccion));
		if (tipo == '2') return new InstruccionShiftIzquierdaOneFill(new Parametros(instruccion));
		if (tipo == '3') return new InstruccionShiftIzquierdaZeroFill(new Parametros(instruccion));
		throw new Exception("Formato de instrucción inválida.");
	}	
	
	private static Instruccion decodificarSaltar(String instruccion){
		return new InstruccionSaltar(new Parametros(instruccion));
	}	
	
	private static Instruccion decodificarParar(String instruccion){
		return new InstruccionParar(new Parametros(instruccion));
	}	
	
	private static void validarFormato(String instruccion) throws Exception{
		  Pattern pat = Pattern.compile("^[1-9A-C][0-9A-F][0-9A-F][0-9A-F]$");
		  Matcher mat = pat.matcher(instruccion);
		  if (!mat.matches()) {
			  throw new Exception("Formato de instrucción inválida.");
		  }
	}
}
