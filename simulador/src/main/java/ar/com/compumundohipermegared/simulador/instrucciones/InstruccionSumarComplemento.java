package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.OverFlowCasteadorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSumarComplemento extends InstruccionAlu{
	int operando1, operando2;
	int idRegistroDestino;
	
	public InstruccionSumarComplemento(Parametros parametros) {
		super(parametros);
		System.out.print("Suma Complemento 2\n");
	}

	@Override
	public void ejecutar() {
		int resultado = Alu.plus(operando1, operando2);
		
		try {
			char resultadoCasteado = casteador.complementoADos(resultado);
			cpu.escribirRegistro(idRegistroDestino, (byte)resultadoCasteado);
		} catch (OverFlowCasteadorException e) {
			throw new RuntimeException(e);
		}
		System.out.print("RegistroDestino: " + idRegistroDestino + " = " + resultado + "\n");
		
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
	
	}
}
