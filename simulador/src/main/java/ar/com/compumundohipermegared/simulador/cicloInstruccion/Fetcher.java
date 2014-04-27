package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.util.Hashtable;

import ar.com.compumundohipermegared.almacenamiento.DireccionMasInstruccion;
import ar.com.compumundohipermegared.almacenamiento.EndOfStreamException;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;

public class Fetcher {
	
	Hashtable<String,String> instrucciones;
	String primerDireccion = new String("0000");;
	
	public Fetcher(IInputStream programaAEjecutar) {
		instrucciones = new Hashtable<String,String>();
		
		int i = 0;
		boolean fin = false;
		while (!fin) {
			try {
				String linea = programaAEjecutar.readln();
				String[] instruccion = parsearLinea(linea);
				instrucciones.put(instruccion[0], instruccion[1]);
				if (i == 0) {
					primerDireccion = instruccion[0];
					++i;
				}
				// TODO: chequear que las direcciones sean contiguas y vayan de dos en dos
			} catch (EndOfStreamException e) {
				fin = true;
			}
		}
	}

	private String[] parsearLinea(String linea) {
		String delims = "[ ]+";
		String[] tokens = linea.split(delims);
		String[] resultado = new String[2];
		resultado[0] = new String(tokens[0]);
		resultado[1] = new String(tokens[1]);
		return resultado;
	}

	public String direccionPrimerInstruccion() {
		return primerDireccion;
	}

	public DireccionMasInstruccion getInstruccion(char dato) throws InstruccionNoEncontradaException {
		try {
			String hexaDato = Conversor.decimalToHexaDoblePrecision((int) dato);
			String instruccion = instrucciones.get(hexaDato);
			if (instruccion != null) return new DireccionMasInstruccion(hexaDato,instruccion);
		} catch (LimitesExcedidosConversorException e) { }
		
		throw new InstruccionNoEncontradaException();
	}

}
