package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionAnd extends InstruccionAlu {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de And");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de And");		
	}
}
