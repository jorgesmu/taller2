package ar.com.compumundohipermegared.conversor;

public class Conversor {

	private static String VECHEXA[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	
	
	private static String convertIntToHexa(int numero){
		
		int highNible = numero & 0xf0;
		int lowNible = numero & 0x0f;
	
		int primerNum = highNible >> 4;
		int segundoNum = lowNible;
		
		String nHexa = VECHEXA[primerNum] + VECHEXA[segundoNum];
		
		return nHexa;
		
	}
	
	private static int calcularExponente(double numero){
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
	
	private static int correrReferencia(int numInt){
		
		if( numInt > 47 && numInt < 58)
			return numInt - 48 ;
		
		else if( numInt > 64 && numInt < 71)
			return numInt - 55 ;
		
		else if( numInt > 96 && numInt < 103)
			return numInt - 87 ;
		
		return -1;
	}
	
	
	
	
	public static String decimalToHexa( int numero) throws LimitesExcedidosConversorException {
		
		if( numero > 127 || numero < -128 ) throw new LimitesExcedidosConversorException();
									
		return convertIntToHexa(numero);
		
	} 
	
	
	
	public static String decimalToHexa(double numero ) throws LimitesExcedidosConversorException {
		boolean esPositivo = true;
		
		if( numero < 0 ) esPositivo = false;
		if( !esPositivo) numero = numero * (-1);
		
		int exponente = calcularExponente(numero);
		int mantisa;
		
		if(exponente > 3 || exponente < -4) throw new LimitesExcedidosConversorException();
		
		double factor = Math.pow(10 , exponente*(-1)); //se multiplica por -1 para tener el valor en la parte entera
		mantisa = (int) (numero * factor);
		exponente = exponente + 4;   //Exceso de 4;
		
		int nhexa = 0;
		if(!esPositivo) nhexa++;  // 0000000s   s=signo 
		nhexa = nhexa << 3;   // 0000s000
		nhexa = nhexa & 0x8;  
		nhexa = nhexa + exponente; // 0000sExp  Exp = exponente
		nhexa = nhexa << 4;  // sExp0000
		nhexa = nhexa & 0xf0;   
		nhexa = nhexa + mantisa; //sExpMantisa
		
		return convertIntToHexa(nhexa); 
		
		
	}
	
	
	public static int complementoDosADecimal(String nHexa) throws LimitesExcedidosConversorException {
		int primerCaracter = correrReferencia( nHexa.charAt(0) );
		int segundoCaracter = correrReferencia( nHexa.charAt(1) );
		
		if ( primerCaracter == -1 || segundoCaracter == -1 ) throw new LimitesExcedidosConversorException(); 
		
		int highNible = primerCaracter << 4;
		highNible =  highNible & 0xf0;
		segundoCaracter = segundoCaracter & 0x0f;
		
		int numero = highNible | segundoCaracter;
		if( numero > 127 ) numero = numero - 256;
		
		return numero;
				
	}
	
	
	public static double puntoFlotanteADecimal(String nHexa) throws  LimitesExcedidosConversorException {
		
		int primerCaracter = correrReferencia( nHexa.charAt(0) ); //signo + Exponente
		int segundoCaracter = correrReferencia( nHexa.charAt(1) );  //mantisa
		
		boolean esPositivo = true;
		int signo , exponente;
		
		if ( primerCaracter == -1 || segundoCaracter == -1 ) throw new LimitesExcedidosConversorException(); 
		
		signo = primerCaracter & 0x8; // se multiplica por 1000 = 8 para obtener el bit de signo
		signo = signo >> 3;
		if( signo == 1 ) esPositivo = false;
		
		exponente = primerCaracter & 0x7; //Se multiplica por 7 para recuperar los 3 bits del exponente
		exponente = exponente - 4; //Este reperesentado en Exceso de 4
		
		double numero = segundoCaracter * Math.pow(10, exponente);
		
		if(!esPositivo) numero = numero * (-1);
		
		return numero;
		
	}
	
	
}
