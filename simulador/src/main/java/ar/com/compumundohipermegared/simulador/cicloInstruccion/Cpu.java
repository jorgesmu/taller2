package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.util.ArrayList;

public class Cpu {
	ArrayList<String> pipeline = new ArrayList<String>();
	public void ejecutarProximaInstruccion() throws Exception{
		if (pipeline.size() == 0) throw new Exception("No hay instrucciones para ejecutar en el pipeline");
		String proximaInstruccionADecodificar = pipeline.get(0);
		
		Instruccion proximaInstruccion = ParserInstrucciones.Decodificar(proximaInstruccionADecodificar);
		proximaInstruccion.cargarOperandos();
		proximaInstruccion.ejecutar();
		
		pipeline.remove(0);
		//hacer fetch aca
	}
	public void ejecutarPrograma() {
		try {
			while (pipeline.size() > 0) {
				ejecutarProximaInstruccion();
			}
		} catch (Exception e) {

		}
	}
	public void cargarInstruccion(String instruccion){
		pipeline.add(instruccion);
	}
	
}
