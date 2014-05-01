package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.OverFlowCasteadorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSumarComplemento extends InstruccionAlu{
	int operando1, operando2, idRegistroDestino;
	public InstruccionSumarComplemento(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		int resultado = operando1 + operando2;
		try {
			casteador.complementoADos(resultado);			
		} catch (OverFlowCasteadorException e) {
			e.printStackTrace();
		}
		cpu.EscribirRegistro(idRegistroDestino, (byte)resultado);
		System.out.println("Ejecutando una instruccion de sumar complemento");
	}

	@Override
	public void cargarOperandos(Cpu cpuRecibida) {
		super.cargarOperandos(cpuRecibida);
		int dirPrimerOperando, dirSegundoOperando;
		idRegistroDestino = _parametros.getPrimerParametro();
		dirPrimerOperando = _parametros.getSegundoParametro();
		dirSegundoOperando = _parametros.getTercerParametro();
		operando1 = cpu.ObtenerRegsitro(dirPrimerOperando);
		operando2 = cpu.ObtenerRegsitro(dirSegundoOperando);
		
		try {
			casteador.complementoADos(operando1);
			casteador.complementoADos(operando2);			
		} catch (OverFlowCasteadorException e) {
			e.printStackTrace();
		}
		
		System.out.println("Cargando operandos una instruccion de sumar complemento");		
	}
}
