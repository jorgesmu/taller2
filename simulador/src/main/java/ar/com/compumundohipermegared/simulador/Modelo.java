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
	
	private static Modelo modelo = null;
	private Cpu cpu = null;
	private IMemoria memoria = null;
	private AreaRegistroCpu registrosControl = null;
	private AreaRegistro registros = null;
	private ArrayList<DireccionMasInstruccion> pipeline = null;

	private Modelo(String ruta) throws FileNotFoundException, ProgramaMalFormadoException{
		if (modelo != null) return;
		this.cpu = new Cpu(ruta);
		this.memoria = cpu.getMemoria();
		this.registros = cpu.obtenerRegistros();
		this.registrosControl = cpu.obtenerRegistrosControl();
		this.pipeline = cpu.obtenerPipeline();
		modelo = this;
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
	public void crearModelo(String rutaEjecutable) throws FileNotFoundException, ProgramaMalFormadoException{
		Modelo.modelo = null;
		Modelo.modelo = new Modelo (rutaEjecutable);
	};
}
