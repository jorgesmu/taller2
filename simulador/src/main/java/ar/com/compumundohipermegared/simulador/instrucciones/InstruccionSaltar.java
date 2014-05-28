package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSaltar extends InstruccionFlujo {
	char direccion;
	int idRegistro = 0;
	boolean saltar;
	
	public InstruccionSaltar(Parametros parametros) {
		super(parametros);
		idRegistro = parametros.getPrimerParametro();
		String dirHexa = parametros.getSegundoYTercerParametro();
		direccion = (char)Integer.parseInt(dirHexa,16);
		saltar = false;
		System.out.println("JUMP");
	}
	
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de salto");
		if (saltar) cpu.ejecutarSaltoA(direccion);
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		char dato = (char)cpu.obtenerDatoRegistro(idRegistro);
		char zero = (char)cpu.obtenerDatoRegistro(0);
		saltar = (zero == dato);
		System.out.println("Salto a: " + (int)direccion);
		System.out.println("Debe saltar: " + saltar);
	}
}
