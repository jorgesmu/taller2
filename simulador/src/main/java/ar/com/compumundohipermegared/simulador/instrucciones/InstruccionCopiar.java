package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionCopiar extends InstruccionAlu {
	byte dato;
	int idRegistroDestino;
	public InstruccionCopiar(Parametros parametros) {
		super(parametros);
		
		System.out.print("Copiar\n");
	}

	@Override
	public void ejecutar() {
		if (dato == 0) cpu.escribirRegistro(Cpu.REG_FLAGS_INT, (byte) Alu.BIT_FLAG_ZERO);
		else cpu.escribirRegistro(Cpu.REG_FLAGS_INT, (byte) 0);
		cpu.escribirRegistro(idRegistroDestino, dato);
		//System.out.println("Ejecutando una instruccion de copiar");	
		
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		int idFuente = _parametros.getSegundoParametro();
		dato = cpu.obtenerDatoRegistro(idFuente);
		idRegistroDestino = _parametros.getTercerParametro();
		//System.out.println("Cargando operandos una instruccion de copiar");		
		System.out.print("RegistroFuente: " + idFuente + " RegistroDestino: " + idRegistroDestino + " Dato: " + dato + "\n");
	}

}
