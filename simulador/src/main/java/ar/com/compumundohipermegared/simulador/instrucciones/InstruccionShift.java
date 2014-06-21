package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public abstract class InstruccionShift extends InstruccionAlu {
	int idRegistro, cantVeces, valorRegistro;
	
	public InstruccionShift(Parametros parametros) {
		super(parametros);
	}
	
	protected abstract int getShift();
	
	@Override
	public void ejecutar() {
		int resultado = getShift();
		if (resultado == 0) cpu.escribirRegistro(Cpu.REG_FLAGS_INT, (byte) Alu.BIT_FLAG_ZERO);
		else cpu.escribirRegistro(Cpu.REG_FLAGS_INT, (byte) 0);
		
		cpu.escribirRegistro(idRegistro, (byte) resultado);
		
		System.out.print("Cantidad de bits: " + cantVeces +"\n");
		System.out.print("Registro: " + idRegistro + " = " + valorRegistro + "\n");
		System.out.print("Resultado Registro: " + idRegistro + " = " + (byte)resultado + "\n");
	}
	
	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		idRegistro = _parametros.getPrimerParametro();
		cantVeces = _parametros.getTercerParametro();
		valorRegistro = cpu.obtenerDatoRegistro(idRegistro);		
	}
	
}
