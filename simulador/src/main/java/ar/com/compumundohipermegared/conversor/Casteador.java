package ar.com.compumundohipermegared.conversor;

public class Casteador {

	public int calcularMantisa(double numero){
		int mantisa	= 0;
		int factor;
		int parteEntera = (int) (numero);
		int cantBitMantisa = 0;
		
		char binParteEntera = (char) parteEntera;
		
		while (binParteEntera > 1){
			factor = parteEntera & (int) Math.pow(2 , cantBitMantisa );	;
			mantisa = mantisa | factor;			
			binParteEntera = (char) (binParteEntera >> 1);	
			cantBitMantisa++;
		}
		
		double parteFraccionaria = numero - parteEntera;		
		//si no tiene parte entera pq es decimal puro tengo que avanzar hasta el 1 uno		
		if(parteEntera == 0){
			
			while (parteFraccionaria < 1 && parteFraccionaria > 0 && cantBitMantisa == 0 ){			
				parteFraccionaria = parteFraccionaria * 2;		
				
			} 	
			parteFraccionaria = parteFraccionaria - 1;
		}		
		while (parteFraccionaria < 1 && parteFraccionaria > 0 && cantBitMantisa < 4){
			
			parteFraccionaria = parteFraccionaria * 2;							
			factor = (int) parteFraccionaria & 0x1;	
			mantisa = mantisa << 1;
			mantisa = mantisa | factor;			
			if(parteFraccionaria > 1 ) parteFraccionaria = parteFraccionaria - 1;
			cantBitMantisa++;
		} 	
		
		while(cantBitMantisa < 4){
			mantisa = mantisa << 1;
			cantBitMantisa++;
		}		
		return mantisa;			
	}
	
	public int calcularExponente(double numero){
		int exponente = 0;
		if( numero > 15.5 ) return 99; //overFlow
		if(numero < 0.0625 ) return -99;	//underFlow
		
		int parteEntera = (int) (numero);
		char binParteEntera = (char) parteEntera;
		
		if(parteEntera > 0 ){
			while (binParteEntera > 1){
				binParteEntera = (char) (binParteEntera >> 1);
				exponente++;
			} 
			
			return exponente;
		}
		
		double parteFraccionaria = numero - parteEntera;
		
		while (parteFraccionaria < 1 && parteFraccionaria >0){
			parteFraccionaria = parteFraccionaria * 2;
			exponente--;
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
				
		if( exponente > 3 ) throw new OverFlowCasteadorException();
		if( exponente < -4 ) throw new UnderFlowCasteadorException();
		
		int mantisa = calcularMantisa(numero);
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
