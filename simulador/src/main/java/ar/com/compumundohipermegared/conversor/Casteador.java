package ar.com.compumundohipermegared.conversor;

public class Casteador {

	
	private int calcularExponente(double numero){
		int exponente = 0;
		
		if(numero < 16){
			double validar = numero * 10;
			while ( validar < 16 ){
				numero = numero*10;
				exponente--;
				validar = validar * 10;
			}			
		}
		else{
			
			while ( numero >= 16 ){
				numero = numero / 10;
				exponente++;
			}			
		}
		return exponente;
	}
	
		
	public char complementoADos( int numero) throws OverFlowCasteadorException{
		
		if( numero > 127 || numero < -128 ) throw new OverFlowCasteadorException();
		
		char complementoADos = (char) numero;
		return complementoADos;
	} 
	
	
	
	public char puntoFlotante( double numero ) throws OverFlowCasteadorException, UnderFlowCasteadorException{
		boolean esPositivo = !(numero < 0);
		
		if (!esPositivo) numero = numero * (-1);
		
		int exponente = calcularExponente(numero);
		int mantisa;
		
		if( exponente > 3 ) throw new OverFlowCasteadorException();
		if( exponente < -4 ) throw new UnderFlowCasteadorException();
		
		double factor = Math.pow(10 , exponente*(-1)); //se multiplica por -1 para tener el valor en la parte entera
		mantisa = (int) (numero * factor);
		exponente = exponente + 4;   //Exceso de 4;
		
		int nHexa = 0;
		
		
		if(!esPositivo) nHexa++;  // 0000000s   s=signo 
		nHexa = nHexa << 3;   // 0000s000
		nHexa = nHexa & 0x8;  
		nHexa = nHexa + exponente; // 0000sExp  Exp = exponente
		nHexa = nHexa << 4;  // sExp0000
		nHexa = nHexa & 0xf0;   
		nHexa = nHexa + mantisa; //sExpMantisa
		
		char nPuntoFlotante = (char) nHexa;
		return nPuntoFlotante; 
		
	}
		
	
	

}
