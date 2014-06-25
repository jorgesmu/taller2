package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ar.com.compumundohipermegared.almacenamiento.AreaRegistro;
import ar.com.compumundohipermegared.almacenamiento.AreaRegistroCpu;
import ar.com.compumundohipermegared.almacenamiento.DireccionMasInstruccion;
import ar.com.compumundohipermegared.almacenamiento.FileReader;
import ar.com.compumundohipermegared.almacenamiento.IInputStream;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoAreaRegistroException;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoMemoriaException;
import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.almacenamiento.ProgramCounter;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.instrucciones.Instruccion;

public class Cpu implements Runnable {
	public static int CANTIDAD_REGISTROS = 16;
	public static int CANTIDAD_FILA_COLUMNA_MEMORIA = 16;
	public static int TAMANIO_PIPELINE = 3;
	
	public static String REG_FLAGS = "F";
	public static int REG_FLAGS_INT = Integer.parseInt(REG_FLAGS, 16);
	
	ArrayList<DireccionMasInstruccion> pipeline;
	AreaRegistro registrosDatos;
	AreaRegistroCpu registrosCPU;
	IMemoria memoriaDatos;
	Fetcher fetcher;
	
	public Cpu (String ruta) throws ProgramaMalFormadoException, FileNotFoundException {
		IInputStream programaAEjecutar = new FileReader (ruta);
		pipeline = new ArrayList<DireccionMasInstruccion>();
		registrosDatos = new AreaRegistro (CANTIDAD_REGISTROS);
		registrosCPU = new AreaRegistroCpu();
		memoriaDatos = new MemoriaRam(CANTIDAD_FILA_COLUMNA_MEMORIA);
		try {
			fetcher = new Fetcher (programaAEjecutar);
			String primeraDireccion = fetcher.direccionPrimerInstruccion();
			char primer_pc = 0;
			primer_pc = (char)Conversor.complementoDosADecimalDoblePrecision(primeraDireccion);
			registrosCPU.setPC (primer_pc);
		} catch (InstruccionMalFormadaException | LimitesExcedidosConversorException e) {
			throw new ProgramaMalFormadoException(e.getMessage());
		}
		llenarPipeline();
	}
	
	public void ejecutarProximaInstruccion() throws Exception {
		if (pipeline.size() == 0) throw new Exception("No hay instrucciones para ejecutar en el pipeline");
		
		String proximaInstruccionADecodificar = leerPipeline();
		fetch();
		
		Instruccion proximaInstruccion = ParserInstrucciones.decodificar(proximaInstruccionADecodificar);
		proximaInstruccion.cargarOperandos(this);
		proximaInstruccion.ejecutar();
		
		// si se hace el fetch aca, no se puede parar la ejecucion vaciando el pipeline
		// ni tampoco quedaria bien el pipeline en caso de hacer un salto
	}
	
	public String ejecutarPrograma() {
		try {
			while (pipeline.size() > 0) {
				ejecutarProximaInstruccion();
			}
			return new String("El programa finalizó con éxito");
		} catch (Exception e) {
			return (e.toString());
		}
	}
	
	public void run() {
		String resultado = this.ejecutarPrograma();
		System.out.println(resultado);
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
	
	public void ejecutarSaltoRelativoA (char direccionRelativa) {
		vaciarPipeline();
		registrosCPU.decrementarPC(); // ya había incrementado el pc
		registrosCPU.sumarAlPC(direccionRelativa);
		llenarPipeline();
	}
	
	private String leerPipeline () {
		DireccionMasInstruccion elemento = pipeline.get(0);
		pipeline.remove(0);
		registrosCPU.incrementarPC();
		return elemento.getInstruccion();
	}
	
	private void fetch () {
		ProgramCounter pc = registrosCPU.getPC();
		// quiero cargar la instruccion que le sigue a la ultima en el pipeline
		// siendo que el pc apunta al segundo elemento del mismo (porque ya fue
		// incrementado al consumir el primer elemento).
		agregarAPipeline(ProgramCounter.sumar(pc, TAMANIO_PIPELINE - 1));
	}
	
	public IMemoria getMemoria() {
		return memoriaDatos;
	}
	
	public byte obtenerDatoRegistro(int idRegistro){
		try {
			return registrosDatos.getDatoRegistro(idRegistro);
		} catch (LimiteExcedidoAreaRegistroException e) {
			return 0;
		}
	}
	
	public byte obtenerDatoRam(int fila, int columna){
		try {
			return memoriaDatos.getDatoMemoria(fila, columna);
		} catch (LimiteExcedidoMemoriaException e) {
			return 0;
		}
	}
	public void escribirRegistro(int idRegistro, byte dato){
		try {
			registrosDatos.cargarRegistro(idRegistro, dato);
		} catch (LimiteExcedidoAreaRegistroException e) {
			e.printStackTrace();
		}
	}
	public void escribirMemoria(int fila, int columna, byte dato){
		try {
			memoriaDatos.cargarMemoria(fila, columna, dato);;
		} catch (LimiteExcedidoMemoriaException e) {
			e.printStackTrace();
		}
	}
	
	public byte leerDispositivoEntrada () {
		while (!(memoriaDatos.tieneDatoDispositivoEntrada())) {
			// me pongo a tomar un tecito hasta que haya algo que leer
		}
		// como nadie mas aparte de la cpu puede leer del dispositivo,
		// una vez que hay algo para leer, por mas que alguien sobreescriba
		// el dato justo despues del while, el siguiente return nunca va a
		// devolver algo invalido.
		return memoriaDatos.leerDispositivoEntrada();
	}
	
	public void escribirDispositivoSalida (byte dato) {
		memoriaDatos.escribirDispositivoSalida(dato);
	}
	
}
