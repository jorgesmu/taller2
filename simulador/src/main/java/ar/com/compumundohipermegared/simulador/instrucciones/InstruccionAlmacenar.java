package ar.com.compumundohipermegared.simulador.instrucciones;

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
		cpu.EscribirMemoria(fila, columna, datos);
		System.out.println("Ejecutando una instruccion de almacenamiento");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		int idRegistro = _parametros.getPrimerParametro();
		fila = _parametros.getSegundoParametro();
		columna = _parametros.getTercerParametro();
		datos = cpu.ObtenerRegsitro(idRegistro);
		System.out.println("Cargando operandos una instruccion de almacenamiento");		
	}

}
