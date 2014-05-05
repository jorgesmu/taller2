package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionAnd extends InstruccionAlu {
	int operando1, operando2, idRegistroDestino;
	
	public InstruccionAnd(Parametros parametros) {
		super(parametros);
		System.out.print("AND\n");
	}

	@Override
	public void ejecutar() {
		int resultado = Alu.and(operando1, operando2);
		cpu.escribirRegistro(idRegistroDestino, (byte) resultado);
		
		System.out.print("Registro: " + idRegistroDestino + " = " + resultado + "\n");
		//System.out.println("Ejecutando una instruccion de And");
		
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		
		int dirPrimerOperando, dirSegundoOperando;
		idRegistroDestino = _parametros.getPrimerParametro();
		dirPrimerOperando = _parametros.getSegundoParametro();
		dirSegundoOperando = _parametros.getTercerParametro();
		operando1 = cpu.obtenerDatoRegistro(dirPrimerOperando);
		operando2 = cpu.obtenerDatoRegistro(dirSegundoOperando);
		
		System.out.print("Registro: " + dirPrimerOperando + " = " + operando1 + "\n");
		System.out.print("Registro: " + dirSegundoOperando + " = " + operando2 + "\n");
		//System.out.println("Cargando operandos una instruccion de And");		
	}
}
