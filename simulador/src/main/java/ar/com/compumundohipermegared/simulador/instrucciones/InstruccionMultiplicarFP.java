package ar.com.compumundohipermegared.simulador.instrucciones;

import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.conversor.OverFlowCasteadorException;
import ar.com.compumundohipermegared.conversor.UnderFlowCasteadorException;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Parametros;

public class InstruccionMultiplicarFP extends InstruccionAlu {
	double operando1, operando2;
	int idRegistroDestino;
	
	public InstruccionMultiplicarFP(Parametros parametros) {
		super(parametros);
		System.out.print("Multiplicacion Punto Flotante\n");
		operando1 = 0.0;
		operando2 = 0.0;
		idRegistroDestino = 0;
	}

	@Override
	public void ejecutar() {
		int flags = 0;
		double resultado = Alu.multi(operando1, operando2);
		if (resultado == 0) flags = flags | Alu.BIT_FLAG_ZERO;
		// no hay carry para punto flotante
		String a;
		RuntimeException e1 = null;
		try {
			char resultadoCasteado = casteador.puntoFlotante(resultado);
			cpu.escribirRegistro(idRegistroDestino, (byte)resultadoCasteado);
		} catch (OverFlowCasteadorException e) {
			flags = flags | Alu.BIT_FLAG_OV;
			e1 = new RuntimeException(e.getMessage(),e);
			a = new String(e1.getMessage());		
		} catch (UnderFlowCasteadorException e) {
			flags = flags | Alu.BIT_FLAG_UV;
			e1 = new RuntimeException(e.getMessage(),e);
			
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
		byte registro1 = cpu.obtenerDatoRegistro(dirPrimerOperando);
		byte registro2 = cpu.obtenerDatoRegistro(dirSegundoOperando);
		
		try {
			operando1 = Conversor.puntoFlotanteADecimal(registro1);
			operando2 = Conversor.puntoFlotanteADecimal(registro2);
		} catch (LimitesExcedidosConversorException e) {
			throw new RuntimeException(e);
		}
		
		System.out.print("Registro: " + dirPrimerOperando + " = " + operando1 + "\n");
		System.out.print("Registro: " + dirSegundoOperando + " = " + operando2 + "\n");
	}
}
