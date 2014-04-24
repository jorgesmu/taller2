package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.util.ArrayList;

public class Pipeline {
	int tamanioMaximo = 1;
	ArrayList<String> direcciones, instrucciones;
	
	public Pipeline (int tamanioMaximoRecibido) {
		tamanioMaximo = tamanioMaximoRecibido;
		direcciones = new ArrayList<String>();
		instrucciones = new ArrayList<String>();
	}
	
	public int cantidadInstrucciones () {
		return instrucciones.size();
	}
}
