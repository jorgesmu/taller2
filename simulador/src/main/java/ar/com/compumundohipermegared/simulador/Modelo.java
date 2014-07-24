package ar.com.compumundohipermegared.simulador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ar.com.compumundohipermegared.almacenamiento.AreaRegistro;
import ar.com.compumundohipermegared.almacenamiento.AreaRegistroCpu;
import ar.com.compumundohipermegared.almacenamiento.DireccionMasInstruccion;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class Modelo {
	
	protected static Modelo modelo = null;
	protected Cpu cpu = null;
	protected IMemoria memoria = null;
	protected AreaRegistroCpu registrosControl = null;
	protected AreaRegistro registros = null;
	protected ArrayList<DireccionMasInstruccion> pipeline = null;
	protected Thread hiloEjecucion = null;
	
	protected Modelo(String ruta) throws FileNotFoundException, ProgramaMalFormadoException{
		this.cpu = new Cpu(ruta);
		this.memoria = cpu.getMemoria();
		this.registros = cpu.obtenerRegistros();
		this.registrosControl = cpu.obtenerRegistrosControl();
		this.pipeline = cpu.obtenerPipeline();
		this.hiloEjecucion = new Thread (cpu);
		modelo = this;
	}
	public void ejecutar(){
        this.hiloEjecucion.start();
	}
	
	public static Modelo getModelo () {
		return Modelo.modelo;
	}
	
	public IMemoria getMemoria(){
		return this.memoria;
	}
	
	public AreaRegistroCpu getRegistrosControl(){
		return registrosControl;
	}
	
	public AreaRegistro getRegistros(){
		return registros;
	}
	
	public ArrayList<DireccionMasInstruccion> getPipeline(){
		return pipeline;
	}
	public static void crearModelo(String rutaEjecutable) throws FileNotFoundException, ProgramaMalFormadoException{
		Modelo.modelo = null;
		Modelo.modelo = new Modelo (rutaEjecutable);
	}

	public Cpu getCpu() {
		return this.cpu;
	};
}
