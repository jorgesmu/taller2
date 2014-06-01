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
	
	public Fetcher(IInputStream programaAEjecutar) throws InstruccionMalFormadaException {
		instrucciones = new Hashtable<String,String>();
		
		String anteriorDireccion = new String("-0002");
		int i = 0;
		boolean fin = false;
		while (!fin) {
			try {
				String linea = programaAEjecutar.readln();
				int numeroLinea = programaAEjecutar.numeroLineaActual();
				String[] instruccion = parsearLinea(linea, numeroLinea);
				validarInstruccion(instruccion, anteriorDireccion, numeroLinea);
				
				instrucciones.put(instruccion[0], instruccion[1]);
				anteriorDireccion = instruccion[0];
				if (i == 0) {
					primerDireccion = instruccion[0];
					++i;
				}
			} catch (EndOfStreamException e) {
				fin = true;
			}
		}
	}

	private String[] parsearLinea(String linea, int numeroLinea) throws InstruccionMalFormadaException {
		String delims = "[ ]+";
		String[] tokens = linea.split(delims);
		if (tokens.length < 2)
			throw new InstruccionMalFormadaException("Linea " + Integer.toString(numeroLinea) +
													 ": No se encuentran los campos adecuados");
		String[] resultado = new String[2];
		resultado[0] = new String(tokens[0]);
		resultado[1] = new String(tokens[1]);
		return resultado;
	}

	private void validarInstruccion(String[] instruccion, String anteriorDireccion, int numeroLinea) throws InstruccionMalFormadaException {
		int viejaDireccion = Integer.parseInt(anteriorDireccion,16);
		try {
			int nuevaDireccion = Integer.parseInt(instruccion[0],16);
			if (nuevaDireccion != viejaDireccion + 2)
				throw new Exception("Linea " + Integer.toString(numeroLinea) +
									": La dirección " + instruccion[0] +
									" debería ser la siguiente a " + anteriorDireccion);
		} catch (Exception e) {
			throw new InstruccionMalFormadaException(e.getMessage());
		}
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
