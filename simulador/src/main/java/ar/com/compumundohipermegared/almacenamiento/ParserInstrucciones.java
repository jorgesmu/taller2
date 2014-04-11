package ar.com.compumundohipermegared.almacenamiento;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserInstrucciones {
	public static String Decodificar(String Instruccion) throws Exception{
		ValidarFormato(Instruccion);
		String opcode = Instruccion.substring(0, 1);
		String resultado = "";
		
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
	
	private static String DecodificarCargarMemoria(String Instruccion){
		return "Ejecutando instruccion de carga/memoria";
	}
	
	private static String DecodificarCargar(String Instruccion){
		return "Ejecutando instruccion de carga";
	}	
	
	private static String DecodificarAlmacenar(String Instruccion){
		return "Ejecutando instruccion de Almacenar";
	}
	
	private static String DecodificarCopiar(String Instruccion){
		return "Ejecutando instruccion de Copiar";
	}
	
	private static String DecodificarSumarComplemento(String Instruccion){
		return "Ejecutando instruccion de suma en complemento";
	}
	
	private static String DecodificarSumarFlotingPoint(String Instruccion){
		return "Ejecutando instruccion de suma en FP";
	}
	
	private static String DecodificarOr(String Instruccion){
		return "Ejecutando instruccion Or";
	}
	
	private static String DecodificarAnd(String Instruccion){
		return "Ejecutando instruccion And";
	}
	
	private static String DecodificarXor(String Instruccion){
		return "Ejecutando instruccion Xor";
	}
	
	private static String DecodificarRotar(String Instruccion){
		return "Ejecutando instruccion Rotar";
	}	
	
	private static String DecodificarSaltar(String Instruccion){
		return "Ejecutando instruccion Saltar";
	}	
	
	private static String DecodificarParar(String Instruccion){
		return "Ejecutando instruccion Parar";
	}	
	
	private static void ValidarFormato(String Instruccion) throws Exception{
		  Pattern pat = Pattern.compile("^[1-9A-C][0-9A-F][0-9A-F][0-9A-F]$");
		  Matcher mat = pat.matcher(Instruccion);
		  if (!mat.matches()) {
			  throw new Exception("Formato de instruccion invalida.");
		  }
	}
}
