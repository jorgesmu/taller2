package ar.com.compumundohipermegared.simulador;


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
	
}
