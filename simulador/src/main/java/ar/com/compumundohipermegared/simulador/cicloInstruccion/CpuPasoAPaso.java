package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.io.FileNotFoundException;

public class CpuPasoAPaso extends Cpu {
	boolean ejecutarPaso;
	
	public CpuPasoAPaso(String ruta) throws ProgramaMalFormadoException, FileNotFoundException {
		super(ruta);
		ejecutarPaso = false;
	}
	
	public String ejecutarPrograma() {
		try {
			while (pipeline.size() > 0) {
				if (ejecutarPaso) {
					ejecutarProximaInstruccion();
					ejecutarPaso = false;
				}
			}
			return new String("El programa finalizó con éxito");
		} catch (Exception e) {
			return (e.toString());
		}
	}
	
	public void avanzar() {
		ejecutarPaso = true;
	}
}
