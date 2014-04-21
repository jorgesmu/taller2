package ar.com.compumundohipermegared.simulador.instrucciones;

public class InstruccionAlmacenar extends Instruccion {

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando una instruccion de almacenamiento");
	}

	@Override
	public void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de almacenamiento");		
	}

}
