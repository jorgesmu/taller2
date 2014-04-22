package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionCargar extends Instruccion {
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de cargar");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de cargar");		
	}
}
