package ar.com.compumundohipermegared.compilacion;

import java.util.HashMap;
import java.util.Map;

import ar.com.compumundohipermegared.factoriesAssembly.*;

public class DecoderAssembly {
	
	Map<String, String> misLabels;
	Map<String, FactoryInstruccion> instrucciones;
	Map<String, String> operandos;
	
	protected static String ERROR_INSTRUCCION_INEXISTENTE = "La instrucción Assembly es inexistente.";
	
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
		
		// Registro F usado para los flags (USAR LA CONSTANTE DEFINIDA EN CPU)
		// Los otros registros que no aparecen son usados para expandir instrucciones
		// (USAR LAS CONSTANTES DEFINIDAS EN COMPILADOR)
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
		instrucciones.put("jpz", new FactoryInstruccionJpz(misLabels,operandos));
		instrucciones.put("jnz", new FactoryInstruccionJnz(misLabels,operandos));
		instrucciones.put("jpc", new FactoryInstruccionJpc(misLabels,operandos));
		instrucciones.put("jnc", new FactoryInstruccionJnc(misLabels,operandos));
		instrucciones.put("sl0", new FactoryInstruccionSl0(misLabels,operandos));
		instrucciones.put("sl1", new FactoryInstruccionSl1(misLabels,operandos));
		instrucciones.put("sr0", new FactoryInstruccionSr0(misLabels,operandos));
		instrucciones.put("sr1", new FactoryInstruccionSr1(misLabels,operandos));
		instrucciones.put("rrl", new FactoryInstruccionRrl(misLabels,operandos));
		instrucciones.put("rrr", new FactoryInstruccionRrr(misLabels,operandos));
		instrucciones.put("nop", new FactoryInstruccionNop(misLabels,operandos));
		instrucciones.put("cmp", new FactoryInstruccionCmp(misLabels,operandos));
		instrucciones.put("addfp", new FactoryInstruccionAddFp(misLabels,operandos));
		instrucciones.put("multfp", new FactoryInstruccionMultFp(misLabels,operandos));
	}

	public String[] decodificar(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
		FactoryInstruccion instruccion = instrucciones.get(lineaParseada[0]);
		if (instruccion == null) throw new InstruccionAssemblyInvalidaException(ERROR_INSTRUCCION_INEXISTENTE);
		return instruccion.getInstrucciones(lineaParseada, pcActual);
	}

	public int cantidadInstrucciones(String string) throws InstruccionAssemblyInvalidaException {
		FactoryInstruccion instruccion = instrucciones.get(string);
		if (instruccion == null) throw new InstruccionAssemblyInvalidaException(ERROR_INSTRUCCION_INEXISTENTE);
		return instruccion.getCantidadInstruccionesAEjecutar();
	}

}
