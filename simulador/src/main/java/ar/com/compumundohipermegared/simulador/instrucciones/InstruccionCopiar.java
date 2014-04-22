package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionCopiar extends InstruccionAlu {

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de copiar");		
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de copiar");		
	}

}
