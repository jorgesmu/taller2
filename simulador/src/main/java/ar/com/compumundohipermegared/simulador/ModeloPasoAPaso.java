package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.CpuPasoAPaso;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class ModeloPasoAPaso extends Modelo {
	
	protected static Cpu crearCpu(String ruta) throws FileNotFoundException, ProgramaMalFormadoException {
		return new CpuPasoAPaso(ruta);
	}
	
	private ModeloPasoAPaso(String ruta) throws FileNotFoundException, ProgramaMalFormadoException{
		super(ruta);
	}
	
	public static void crearModelo(String rutaEjecutable) throws FileNotFoundException, ProgramaMalFormadoException{
		Modelo.modelo = null;
		Modelo.modelo = new ModeloPasoAPaso (rutaEjecutable);
	};
}
