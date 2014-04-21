package ar.com.compumundohipermegared.simulador.cicloInstruccion;

public class InstruccionCopiar extends Instruccion {

	@Override
	void ejecutar() {
		System.out.println("Ejecutando una instruccion de copiar");		
	}

	@Override
	void cargarOperandos() {
		System.out.println("Cargando operandos una instruccion de copiar");		
	}

}
