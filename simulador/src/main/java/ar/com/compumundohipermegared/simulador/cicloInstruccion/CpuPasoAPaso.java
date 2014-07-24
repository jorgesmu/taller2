package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.io.FileNotFoundException;

public class CpuPasoAPaso extends Cpu {
	boolean ejecutarPaso;
	boolean pasoAvanzado;
	
	public CpuPasoAPaso(String ruta) throws ProgramaMalFormadoException, FileNotFoundException {
		super(ruta);
		ejecutarPaso = false;
		pasoAvanzado = true;
	}
	
	public String ejecutarPrograma() {
		resultado = "Ejecutando";
		try {
			while (pipeline.size() > 0) {
				if (ejecutarPaso) {
					ejecutarProximaInstruccion();
					ejecutarPaso = false;
					pasoAvanzado = true;
				}
			}
			ejecucionTerminada = true;
			return new String("El programa finalizó con éxito");
		} catch (Exception e) {
			ejecucionTerminada = true;
			return (e.toString());
		}
	}
	
	public void avanzar() {
		ejecutarPaso = true;
		pasoAvanzado = false;
	}

	public boolean yaAvanzo() {
		return pasoAvanzado;
	}
}
