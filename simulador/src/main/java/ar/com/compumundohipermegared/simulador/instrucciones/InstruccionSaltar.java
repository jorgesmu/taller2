package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionSaltar extends Instruccion {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de salto");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de salto");		
	}
}
