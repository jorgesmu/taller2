package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionRotar extends InstruccionAlu {
	int idRegistro, cantVeces, valorRegistro;
	
	public InstruccionRotar(Parametros parametros) {
		super(parametros);
		System.out.print("Rotar\n");
	}

	@Override
	public void ejecutar() {
		int resultado = Alu.rotarDerecha(valorRegistro, cantVeces);
		if (resultado == 0) cpu.escribirRegistro(Cpu.REG_FLAGS_INT, (byte) Alu.BIT_FLAG_ZERO);
		cpu.escribirRegistro(idRegistro, (byte) resultado);
		System.out.print("Resultado Registro: " + idRegistro + " = " + resultado + "\n");
		//System.out.println("Ejecutando una instruccion de rotar");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		idRegistro = _parametros.getPrimerParametro();
		cantVeces = _parametros.getTercerParametro();
		valorRegistro = cpu.obtenerDatoRegistro(idRegistro);
		
		System.out.print("Registro: " + idRegistro + " = " + valorRegistro + "\n");
		System.out.print("Rota a la derecha: " + cantVeces +"\n");
		
		//System.out.println("Cargando operandos una instruccion de rotar");		
	}
}
