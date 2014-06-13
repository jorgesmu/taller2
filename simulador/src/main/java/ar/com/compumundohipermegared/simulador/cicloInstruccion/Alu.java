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
	
	public static int rotarDerecha(int number, int veces){
		return (number >> veces);
	}
	
	//Estos quiza estan demas pero los hice igual total no costaba 
	public static int rotarIzquirda(int number, int veces){
		return (number << veces);
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
	
	
}
