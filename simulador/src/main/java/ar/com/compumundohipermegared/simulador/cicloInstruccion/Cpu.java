package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.util.ArrayList;

import ar.com.compumundohipermegared.almacenamiento.AreaRegistro;
import ar.com.compumundohipermegared.almacenamiento.AreaRegistroCpu;
import ar.com.compumundohipermegared.almacenamiento.DireccionMasInstruccion;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.almacenamiento.ProgramCounter;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.instrucciones.Instruccion;

public class Cpu {
	public static int CANTIDAD_REGISTROS = 16;
	public static int CANTIDAD_CELAS_POR_FILA_Y_COLUMNA = 16;
	public static int TAMANIO_PIPELINE = 3;
	
	ArrayList<DireccionMasInstruccion> pipeline;
	AreaRegistro registrosDatos;
	AreaRegistroCpu registrosCPU;
	IMemoria memoriaDatos;
	Fetcher fetcher;
	
	public Cpu (IInputStream programaAEjecutar) {
		pipeline = new ArrayList<DireccionMasInstruccion>();
		registrosDatos = new AreaRegistro (CANTIDAD_REGISTROS);
		registrosCPU = new AreaRegistroCpu();
		memoriaDatos = new MemoriaRam (CANTIDAD_CELAS_POR_FILA_Y_COLUMNA);
		fetcher = new Fetcher (programaAEjecutar);
		
		String primeraDireccion = fetcher.direccionPrimerInstruccion();
		char primer_pc = 0;
		try {
			primer_pc = (char)Conversor.complementoDosADecimalDoblePrecision(primeraDireccion);
		} catch (LimitesExcedidosConversorException e) {
			e.printStackTrace();
		}
		registrosCPU.setPC (primer_pc);
		llenarPipeline();
	}
	
	public void ejecutarProximaInstruccion() throws Exception {
		if (pipeline.size() == 0) throw new Exception("No hay instrucciones para ejecutar en el pipeline");
		
		String proximaInstruccionADecodificar = leerPipeline();
		fetch();
		
		Instruccion proximaInstruccion = ParserInstrucciones.Decodificar(proximaInstruccionADecodificar);
		proximaInstruccion.cargarOperandos(this);
		proximaInstruccion.ejecutar();
		
		// si se hace el fetch aca, no se puede parar la ejecucion vaciando el pipeline
		// ni tampoco quedaria bien el pipeline en caso de hacer un salto
	}
	
	public void ejecutarPrograma() {
		try {
			while (pipeline.size() > 0) {
				ejecutarProximaInstruccion();
			}
		} catch (Exception e) {

		}
	}
	
	public void cargarInstruccion(DireccionMasInstruccion elemento) {
		pipeline.add(elemento);
	}
	
	private void vaciarPipeline() {
		pipeline.clear();
	}
	
	private void llenarPipeline() {
		ProgramCounter pc = registrosCPU.getPC();
		for (int i = 0 ; i < TAMANIO_PIPELINE ; ++i) {
			agregarAPipeline(ProgramCounter.sumar(pc, i));
		}
	}
	
	private void agregarAPipeline (ProgramCounter direccion) {
		try {
			DireccionMasInstruccion elemento = fetcher.getInstruccion(direccion.getDato());
			cargarInstruccion(elemento);
		} catch (InstruccionNoEncontradaException error) { }
	}
	
	public void pararEjecucion () {
		vaciarPipeline();
	}
	
	public void ejecutarSaltoA (char posicion) {
		vaciarPipeline();
		registrosCPU.setPC(posicion);
		llenarPipeline();
	}
	
	private String leerPipeline () {
		DireccionMasInstruccion elemento = pipeline.get(0);
		pipeline.remove(0);
		registrosCPU.incrementarPC();
		return elemento.instruccion;
	}
	
	private void fetch () {
		ProgramCounter pc = registrosCPU.getPC();
		// quiero cargar la instruccion que le sigue a la ultima en el pipeline
		agregarAPipeline(ProgramCounter.sumar(pc, TAMANIO_PIPELINE));
	}
	
}
