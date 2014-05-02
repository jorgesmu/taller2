package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCargarMemoria extends InstruccionLoadStore {
	private byte datoMemoria;
	private byte idRegistro;
	
	public InstruccionCargarMemoria(Parametros parametros) {
		super(parametros);
		datoMemoria = 0;
		idRegistro = 0;
	}

	@Override
	public void ejecutar() {
		cpu.escribirRegistro(idRegistro, datoMemoria);
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		idRegistro = _parametros.getPrimerParametro();
		
		int fila = _parametros.getSegundoParametro();
		int columna = _parametros.getTercerParametro();
		
		if ((fila == MemoriaRam.PUERTO_ENTRADA_DATO_FILA) && (columna == MemoriaRam.PUERTO_ENTRADA_DATO_COLUMNA)) {
			datoMemoria = cpu.leerDispositivoEntrada();
		} else if ((fila == MemoriaRam.PUERTO_SALIDA_DATO_FILA) && (columna == MemoriaRam.PUERTO_SALIDA_DATO_COLUMNA)) {
			throw new RuntimeException("InstruccionCargarMemoria quiso leer de dispositivo de salida");
		} else {
			datoMemoria = cpu.obtenerDatoRam(fila, columna);
		}
	}

}
