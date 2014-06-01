package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;

public class CpuPasoAPaso extends Cpu {
	boolean ejecutarPaso;
	
	public CpuPasoAPaso(IInputStream programaAEjecutar, IMemoria memoria) throws ProgramaMalFormadoException {
		super(programaAEjecutar, memoria);
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
