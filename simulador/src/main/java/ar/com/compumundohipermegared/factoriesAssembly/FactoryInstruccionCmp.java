package ar.com.compumundohipermegared.factoriesAssembly;

import java.util.Map;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;

public class FactoryInstruccionCmp extends FactoryInstruccion {

	public FactoryInstruccionCmp(Map<String, String> labels, Map<String, String> operandos) {
		super(labels, operandos);
		CANTIDADINSTRUCCIONES = 7;
		
	}
	//cm rs,rt Zero= 1 si rs=rt ,Cary=1 si rs<rt , Flag=0 si rs > rt
	@Override
	public String[] getInstrucciones(String[] lineaParseada, String pcActual) throws InstruccionAssemblyInvalidaException {
	
		String rs = misOperandos.get(lineaParseada[1]);
		String rt = misOperandos.get(lineaParseada[2]);
		if ((rs == null) || (rt == null) ) throw new InstruccionAssemblyInvalidaException();
		
		String[] instrucciones  = new String[CANTIDADINSTRUCCIONES];
		instrucciones[0] = new String("2" + Compilador.REG_AUX_1 + "FF");
		instrucciones[1] = new String("9" + Compilador.REG_AUX_1 + rt + Compilador.REG_AUX_1);
		instrucciones[2] = new String("2" + Compilador.REG_AUX_2 + "01");
		instrucciones[3] = new String("5" + Compilador.REG_AUX_1 + Compilador.REG_AUX_1 + Compilador.REG_AUX_2);
		instrucciones[4] = new String("5" + Compilador.REG_AUX_1 + rs + Compilador.REG_AUX_1);                 //se resta rs - rt si son igual Zero =1
		instrucciones[5] = new String("2" + Compilador.REG_AUX_2 +  "80"); //si son iguales se mantiene lo anterior pero si rs < rt hay un 100000
		instrucciones[6] = new String("8" + Compilador.REG_AUX_2 +  Compilador.REG_AUX_1 + Compilador.REG_AUX_2); //and en Reg2=80 si rs < rt o Reg2=0 si rs > Rt
		instrucciones[6] = new String("5" + Compilador.REG_AUX_2 +  Compilador.REG_AUX_1 + Compilador.REG_AUX_2); //rs = rt Si en reg1=0 => Reg2=0 => Zero=1
																												//rs > rt Si en reg1=0xxxx => reg2=0 => Flags=0
																												//rs < rt Si en reg1=80 => reg2=80 => Carry = 1
		return instrucciones;
	}

}
