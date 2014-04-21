package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionSumarFP extends InstruccionAlu {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de sumar FP");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de sumar FP");		
	}
}
