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
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionRotar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionSaltar;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionSumarComplemento;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionSumarFP;
import ar.com.compumundohipermegared.simulador.instrucciones.InstruccionXor;

public class ParserInstrucciones {
	public static Instruccion Decodificar(String instruccion) throws Exception{
		ValidarFormato(instruccion);
		String opcode = instruccion.substring(0, 1);
		Instruccion resultado = null;
		
		switch (opcode) {
		case "1":
			resultado = DecodificarCargarMemoria(instruccion);
			break;
		case "2":
			resultado = DecodificarCargar(instruccion);
			break;
		case "3":
			resultado = DecodificarAlmacenar(instruccion);
			break;
		case "4":
			resultado = DecodificarCopiar(instruccion);
			break;
		case "5":
			resultado = DecodificarSumarComplemento(instruccion);
			break;
		case "6":
			resultado = DecodificarSumarFlotingPoint(instruccion);
			break;
		case "7":
			resultado = DecodificarOr(instruccion);
			break;
		case "8":
			resultado = DecodificarAnd(instruccion);
			break;
		case "9":
			resultado = DecodificarXor(instruccion);
			break;
		case "A":
			resultado = DecodificarRotar(instruccion);
			break;
		case "B":
			resultado = DecodificarSaltar(instruccion);
			break;
		case "C":
			resultado = DecodificarParar(instruccion);
			break;
		}
		return resultado;
	}
	
	
	private static Instruccion DecodificarCargarMemoria(String instruccion){
		return new InstruccionCargarMemoria(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarCargar(String instruccion){
		return new InstruccionCargar(new Parametros(instruccion));
	}	
	
	private static Instruccion DecodificarAlmacenar(String instruccion){
		return new InstruccionAlmacenar(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarCopiar(String instruccion){
		return new InstruccionCopiar(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarSumarComplemento(String instruccion){
		return new InstruccionSumarComplemento(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarSumarFlotingPoint(String instruccion){
		return new InstruccionSumarFP(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarOr(String instruccion){
		return new InstruccionOr(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarAnd(String instruccion){
		return new InstruccionAnd(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarXor(String instruccion){
		return new InstruccionXor(new Parametros(instruccion));
	}
	
	private static Instruccion DecodificarRotar(String instruccion){
		return new InstruccionRotar(new Parametros(instruccion));
	}	
	
	private static Instruccion DecodificarSaltar(String instruccion){
		return new InstruccionSaltar(new Parametros(instruccion));
	}	
	
	private static Instruccion DecodificarParar(String instruccion){
		return new InstruccionParar(new Parametros(instruccion));
	}	
	
	private static void ValidarFormato(String Instruccion) throws Exception{
		  Pattern pat = Pattern.compile("^[1-9A-C][0-9A-F][0-9A-F][0-9A-F]$");
		  Matcher mat = pat.matcher(Instruccion);
		  if (!mat.matches()) {
			  throw new Exception("Formato de instruccion invalida.");
		  }
	}
}
