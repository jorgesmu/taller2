package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserInstrucciones {
	public static Instruccion Decodificar(String Instruccion) throws Exception{
		ValidarFormato(Instruccion);
		String opcode = Instruccion.substring(0, 1);
		Instruccion resultado = null;
		
		switch (opcode) {
		case "1":
			resultado = DecodificarCargarMemoria(Instruccion);
			break;
		case "2":
			resultado = DecodificarCargar(Instruccion);
			break;
		case "3":
			resultado = DecodificarAlmacenar(Instruccion);
			break;
		case "4":
			resultado = DecodificarCopiar(Instruccion);
			break;
		case "5":
			resultado = DecodificarSumarComplemento(Instruccion);
			break;
		case "6":
			resultado = DecodificarSumarFlotingPoint(Instruccion);
			break;
		case "7":
			resultado = DecodificarOr(Instruccion);
			break;
		case "8":
			resultado = DecodificarAnd(Instruccion);
			break;
		case "9":
			resultado = DecodificarXor(Instruccion);
			break;
		case "A":
			resultado = DecodificarRotar(Instruccion);
			break;
		case "B":
			resultado = DecodificarSaltar(Instruccion);
			break;
		case "C":
			resultado = DecodificarParar(Instruccion);
			break;
		}
		return resultado;
	}
	
	private static Instruccion DecodificarCargarMemoria(String Instruccion){
		return new InstruccionCargarMemoria();
	}
	
	private static Instruccion DecodificarCargar(String Instruccion){
		return new InstruccionCargarMemoria();
	}	
	
	private static Instruccion DecodificarAlmacenar(String Instruccion){
		return new InstruccionAlmacenamiento();
	}
	
	private static Instruccion DecodificarCopiar(String Instruccion){
		return new InstruccionCopiar();
	}
	
	private static Instruccion DecodificarSumarComplemento(String Instruccion){
		return new InstruccionCargarMemoria();
	}
	
	private static Instruccion DecodificarSumarFlotingPoint(String Instruccion){
		return new InstruccionCargarMemoria();
	}
	
	private static Instruccion DecodificarOr(String Instruccion){
		return new InstruccionCargarMemoria();
	}
	
	private static Instruccion DecodificarAnd(String Instruccion){
		return new InstruccionCargarMemoria();
	}
	
	private static Instruccion DecodificarXor(String Instruccion){
		return new InstruccionCargarMemoria();
	}
	
	private static Instruccion DecodificarRotar(String Instruccion){
		return new InstruccionCargarMemoria();
	}	
	
	private static Instruccion DecodificarSaltar(String Instruccion){
		return new InstruccionCargarMemoria();
	}	
	
	private static Instruccion DecodificarParar(String Instruccion){
		return new InstruccionCargarMemoria();
	}	
	
	private static void ValidarFormato(String Instruccion) throws Exception{
		  Pattern pat = Pattern.compile("^[1-9A-C][0-9A-F][0-9A-F][0-9A-F]$");
		  Matcher mat = pat.matcher(Instruccion);
		  if (!mat.matches()) {
			  throw new Exception("Formato de instruccion invalida.");
		  }
	}
}
