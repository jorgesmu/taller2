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
		int flags = 0;
		int resultado = Alu.plus(operando1, operando2);
		if (resultado == 0) flags = flags | Alu.BIT_FLAG_ZERO;
		if ((resultado & 0x100) == 0x100) flags = flags | Alu.BIT_FLAG_CARRY;
		
		RuntimeException e1 = null;
		try {
			char resultadoCasteado = casteador.complementoADos(resultado);
			cpu.escribirRegistro(idRegistroDestino, (byte)resultadoCasteado);
		} catch (OverFlowCasteadorException e) {
			flags = flags | Alu.BIT_FLAG_OV;
			e1 = new RuntimeException(e);
		}
		
		cpu.escribirRegistro(Cpu.REG_FLAGS_INT, (byte) flags);
		if (e1 != null) throw e1;
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
