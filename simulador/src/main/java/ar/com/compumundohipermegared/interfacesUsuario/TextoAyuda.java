package ar.com.compumundohipermegared.interfacesUsuario;

public class TextoAyuda {
	public static String TEXTO =
	  "Instrucciones Assembly:\n      "
	+ "ldi rd,imm (rd=imm)\n      "
	+ "ldm rd,port_addr (rd=data_in <= mem[port_addr])\n      "
	+ "stm rd,port_addr (rd=data_out => mem[port_addr])\n      "
	+ "add rd,rs,rt (rd=rd+rs+rt)\n      "
	+ "sub rd,rs,rt (rd=rs-rt)\n      "
	+ "and rd,rs,rt (rd=rs and rt)\n      "
	+ "or  rd,rs,rt (rd=rs or rt)\n      "
	+ "xor rd,rs,rt (rd=rs xor rt)\n      "
	+ "jmp rd,inst_addr (if (rd==r0) then pc=inst_addr)\n      "
	+ "adi rd,rs,imm (rd=rs+imm)\n      "
//	+ "sl0 rd (rd <= {rd[6:0],0})\n      "
//	+ "sl1 rd (rd <= {rd[6:0],1})\n      "
//	+ "sr0 rd (rd <= {0,rd[7:1]})\n      "
//	+ "sr1 rd (rd <= {1,rd[7:1])\n      "
//	+ "rrl rd (rd <= {rd[6:0],rd[7]})\n      "
//	+ "rrr rd (rd <= {rd[0],rd[7:1])\n      "
	+ "not rd,rs (rd = not rs)\n"
	+ "\nInstrucciones de máquina:\n      "
	+ "1RXY CARGA el registro R con el patrón de bits que está en la celda de memoria cuya "
	+ "dirección es XY.\n      "
	+ "2RXY CARGA el registro R con el patrón de bits XY.\n      "
	+ "3RXY ALMACENA el patrón de bits que está en el registro R en la celda de memoria cuya "
	+ "dirección es XY.\n      "
	+ "40RS COPIA el patrón de bits que está en el registro R al registro S.\n      "
	+ "5RST SUMA los patrones de bits de los registros S y T en complemento a 2 y deja el "
	+ "resultado en el registro R.\n      "
	+ "6RST SUMA los patrones de bits de los registros S y T en punto flotante y deja el "
	+ "resultado en el registro R.\n      "
	+ "7RST Disyunción lógica (OR) de los patrones de bits de los registros S y T colocando "
	+ "el resultado en el registro R.\n      "
	+ "8RST Conjunción lógica (AND) de los patrones de bits de los registros S y T colocando "
	+ "el resultado en el registro R.\n      "
	+ "9RST Disyunción lógica exclusiva (XOR) de los patrones de bits de los registros S y T "
	+ "colocando el resultado en el registro R.\n      "
	+ "AR0X DESPLAZAMIENTO ARITMÉTICO del patrón de bits del registro R un bit a derecha X "
	+ "veces.\n      "
	+ "BRXY SALTA a la instrucción situada en la celda de memoria cuya dirección es XY si "
	+ "el patrón de bits del registro R es igual al patrón de bits del registro número 0.\n      "
	+ "C000 PARA la ejecución.\n      ";
}
