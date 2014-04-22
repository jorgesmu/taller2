package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionParar extends Instruccion{
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de parar");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de parar");		
	}
}
