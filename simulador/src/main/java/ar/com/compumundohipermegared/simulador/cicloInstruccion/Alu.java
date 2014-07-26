package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import ar.com.compumundohipermegared.simulador.DivisionByZeroException;


public class Alu {
	public static int BIT_FLAG_ZERO = 1;
	public static int BIT_FLAG_CARRY = 2;
	public static int BIT_FLAG_UV = 4; // underflow
	public static int BIT_FLAG_OV = 8; // overflow
	
	public static int plus(int number, int othernumber){
		return (number + othernumber);
	}
	
	public static int minus(int number, int othernumber){
		return (number - othernumber);
	}
	
	public static int multi(int number, int othernumber){
		return (number * othernumber);
	}
	
	public static int div(int number, int othernumber) throws DivisionByZeroException{
		if (othernumber == 0) throw new DivisionByZeroException();
		return (number / othernumber);
	}
	
	
	public static int and(int number, int othernumber){
		return (number & othernumber);
	}
	
	public static int or(int number, int othernumber){
		return (number | othernumber);
	}
	
	public static int xor(int number, int othernumber){
		return (number ^ othernumber);
	}
	
	public static int shiftRightArithmetic(int number, int veces) {
		return (number >> veces);
	}
	
	public static int shiftRightLogic(int number, int veces) {
		return (number >>> veces);
	}
	
	public static int shiftLeftZeroFill(int number, int veces) {
		return (number << veces);
	}
	
	public static int shiftLeftOneFill(int number, int veces) {
		int zeroFill = Alu.shiftLeftZeroFill(number, veces);
		int ones = (Alu.pot(2, veces) - 1);
		return (zeroFill + ones);
	}
	
	public static boolean sonIguales(int number, int othernumber){
		
		if( number == othernumber ) return true;
		return false;
	}
	
	//Operacion de punto flotante
	
	public static double plus(double number, double othernumber){
		return (number + othernumber);
	}
	
	public static double minus(double number, double othernumber){
		return (number - othernumber);
	}
	
	public static double multi(double number, double othernumber){
		return (number * othernumber);
	}
	
	public static double div(double number, double othernumber) throws DivisionByZeroException{
		if (othernumber == 0) throw new DivisionByZeroException();
		return (number / othernumber);
	}
	
	private static int pot (int base, int exponente) {
		if (exponente < 0) return 0;
		
		int numero = 1;
		for (int i = 0; i < exponente; ++i) numero *= base;
		return numero;
	}	
	
}
