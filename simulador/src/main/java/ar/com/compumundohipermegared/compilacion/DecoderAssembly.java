package ar.com.compumundohipermegared.compilacion;

import java.util.HashMap;
import java.util.Map;

import ar.com.compumundohipermegared.factoriesAssembly.FactoryInstruccion;
import ar.com.compumundohipermegared.factoriesAssembly.*;

public class DecoderAssembly {
	
	Map<String, String> misLabels;
	Map<String, FactoryInstruccion> instrucciones;
	Map<String, String> operandos;
	
	public DecoderAssembly(Map<String, String> labels) {
		misLabels = labels;
		construirMapaOperandos();
		construirMapaInstrucciones();
	}
	
	private void construirMapaOperandos() {
		operandos = new HashMap<String, String>();
		operandos.put("r0", "0");
		operandos.put("r1", "1");
		operandos.put("r2", "2");
		operandos.put("r3", "3");
		operandos.put("r4", "4");
		operandos.put("r5", "5");
		operandos.put("r6", "6");
		operandos.put("r7", "7");
		operandos.put("r8", "8");
		operandos.put("r9", "9");
		operandos.put("r10", "A");
		operandos.put("r11", "B");
		operandos.put("r12", "C");
		operandos.put("r13", "D");
		operandos.put("r14", "E");
		operandos.put("r15", "F");
	}
	
	private void construirMapaInstrucciones() {
		instrucciones = new HashMap<String, FactoryInstruccion>();
		instrucciones.put("add", new FactoryInstruccionAdd(misLabels,operandos));
		instrucciones.put("adi", new FactoryInstruccionAdi(misLabels,operandos));
		instrucciones.put("sub", new FactoryInstruccionSub(misLabels,operandos));
		instrucciones.put("and", new FactoryInstruccionAnd(misLabels,operandos));
		instrucciones.put("or" , new FactoryInstruccionOr (misLabels,operandos));
		instrucciones.put("xor", new FactoryInstruccionXor(misLabels,operandos));
		instrucciones.put("not", new FactoryInstruccionNot(misLabels,operandos));
		instrucciones.put("jmp", new FactoryInstruccionJmp(misLabels,operandos));
		instrucciones.put("ldi", new FactoryInstruccionLdi(misLabels,operandos));
		instrucciones.put("ldm", new FactoryInstruccionLdm(misLabels,operandos));
		instrucciones.put("stm", new FactoryInstruccionStm(misLabels,operandos));
	}

	public String[] decodificar(String[] lineaParseada) throws InstruccionAssemblyInvalidaException {
		FactoryInstruccion instruccion = instrucciones.get(lineaParseada[0]);
		if (instruccion == null) throw new InstruccionAssemblyInvalidaException();
		return instruccion.getInstrucciones(lineaParseada);
	}

	public int cantidadInstrucciones(String string) throws InstruccionAssemblyInvalidaException {
		FactoryInstruccion instruccion = instrucciones.get(string);
		if (instruccion == null) throw new InstruccionAssemblyInvalidaException();
		return instruccion.getCantidadInstruccionesAEjecutar();
	}

}
