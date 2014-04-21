package ar.com.compumundohipermegared.simulador.cicloInstruccion;

import ar.com.compumundohipermegared.simulador.DivisionByZeroException;


public class Alu {
	
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
