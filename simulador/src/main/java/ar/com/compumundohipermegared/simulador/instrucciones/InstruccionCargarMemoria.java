package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCargarMemoria extends InstruccionLoadStore {
	private byte datoMemoria;
	private byte idRegistro;
	
	public InstruccionCargarMemoria(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		cpu.EscribirRegistro(idRegistro, datoMemoria);
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		int fila = _parametros.getSegundoParametro();
		int columna = _parametros.getTercerParametro();
		datoMemoria = cpu.ObtenerDatoRam(fila, columna);
		idRegistro = _parametros.getPrimerParametro();
	}

}
