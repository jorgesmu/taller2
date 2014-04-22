package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionXor extends InstruccionAlu {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de Xor");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de Xor");		
	}
}
