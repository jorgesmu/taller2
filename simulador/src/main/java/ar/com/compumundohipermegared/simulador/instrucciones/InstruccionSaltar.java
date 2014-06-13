package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSaltar extends InstruccionFlujo {
	char direccionRelativa;
	int idRegistro = 0;
	boolean saltar;
	
	public InstruccionSaltar(Parametros parametros) {
		super(parametros);
		idRegistro = parametros.getPrimerParametro();
		String dirHexa = parametros.getSegundoYTercerParametro();
		try {
			direccionRelativa = (char) Conversor.complementoDosADecimal(dirHexa);
		} catch (LimitesExcedidosConversorException e) {
			direccionRelativa = 0;
		}
		saltar = false;
		System.out.println("JUMP");
	}
	
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de salto");
		if (saltar) cpu.ejecutarSaltoRelativoA(direccionRelativa);
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		char dato = (char)cpu.obtenerDatoRegistro(idRegistro);
		char zero = (char)cpu.obtenerDatoRegistro(0);
		saltar = (zero == dato);
		System.out.println("Salto a: " + (int)direccionRelativa);
		System.out.println("Debe saltar: " + saltar);
	}
}
