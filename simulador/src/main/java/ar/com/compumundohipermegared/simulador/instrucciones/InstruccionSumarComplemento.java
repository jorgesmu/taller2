package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.OverFlowCasteadorException;
import ar.com.compumundohipermegared.conversor.UnderFlowCasteadorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionSumarComplemento extends InstruccionAlu{
	double operando1, operando2;
	int idRegistroDestino;
	public InstruccionSumarComplemento(Parametros parametros) {
		super(parametros);
	}

	@Override
	public void ejecutar() {
		double resultado = operando1 + operando2;
		try {
			casteador.puntoFlotante(resultado);
		} catch (OverFlowCasteadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnderFlowCasteadorException e) {
			// TODO Auto-generated catch block
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
			casteador.puntoFlotante(operando1);
			casteador.puntoFlotante(operando2);			
		} catch (OverFlowCasteadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnderFlowCasteadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		System.out.println("Cargando operandos una instruccion de sumar complemento");		
	}
}
