package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.almacenamiento.MemoriaRam;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionAlmacenar extends InstruccionLoadStore {
	int fila, columna;
	byte datos;
	
	public InstruccionAlmacenar(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		if ((fila == MemoriaRam.PUERTO_SALIDA_DATO_FILA) && (columna == MemoriaRam.PUERTO_SALIDA_DATO_COLUMNA)) {
			cpu.escribirDispositivoSalida(datos);
		} else {
			cpu.escribirMemoria(fila, columna, datos);
		}
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		
		int idRegistro = _parametros.getPrimerParametro();
		datos = cpu.obtenerRegistro(idRegistro);
		
		fila = _parametros.getSegundoParametro();
		columna = _parametros.getTercerParametro();
		
		if ((fila == MemoriaRam.PUERTO_ENTRADA_DATO_FILA) && (columna == MemoriaRam.PUERTO_ENTRADA_DATO_COLUMNA)) {
			throw new RuntimeException("InstruccionAlmacenar quiso escribir en dispositivo de entrada");
		} else if ((fila == MemoriaRam.PUERTO_ENTRADA_CONTROL_FILA) && (columna == MemoriaRam.PUERTO_ENTRADA_CONTROL_COLUMNA)) {
			throw new RuntimeException("InstruccionAlmacenar quiso escribir en el bit de control del dispositivo de entrada");
		} else if ((fila == MemoriaRam.PUERTO_SALIDA_CONTROL_FILA) && (columna == MemoriaRam.PUERTO_SALIDA_CONTROL_COLUMNA)) {
			throw new RuntimeException("InstruccionAlmacenar quiso escribir en el bit de control del dispositivo de salida");
		}
	}

}
