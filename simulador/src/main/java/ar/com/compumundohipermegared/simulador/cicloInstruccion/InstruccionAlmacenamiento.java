package ar.com.compumundohipermegared.simulador.cicloInstruccion;

public class InstruccionAlmacenamiento extends Instruccion {

	@Override
	void ejecutar() {
		System.out.println("Ejecutando una instruccion de almacenamiento");
	}

	@Override
	void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de almacenamiento");		
	}

}
