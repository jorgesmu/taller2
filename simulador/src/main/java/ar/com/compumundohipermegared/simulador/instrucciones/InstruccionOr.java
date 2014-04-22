package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionOr extends InstruccionAlu {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de or");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de or");		
	}
}
