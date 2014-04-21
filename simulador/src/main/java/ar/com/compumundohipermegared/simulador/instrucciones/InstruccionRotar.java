package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionRotar extends InstruccionAlu {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de rotar");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de rotar");		
	}
}
