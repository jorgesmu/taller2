package ar.com.compumundohipermegared.conversor;

public class Conversor {

	private static String VECHEXA[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	
	
	private static String convertIntToHexa(char numero){
		
		int highNible = numero & 0xf0;
		int lowNible = numero & 0x0f;
	
		int primerNum = highNible >> 4;
		int segundoNum = lowNible;
		
		String nHexa = VECHEXA[primerNum] + VECHEXA[segundoNum];
		
		return nHexa;
		
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
	
	
	
	
	public static String decimalToHexa( int numero) throws LimitesExcedidosConversorException, OverFlowCasteadorException {
													
		Casteador casteador = new Casteador();
		try{
			casteador.complementoADos(numero);
		}
		catch( OverFlowCasteadorException error){
			throw new  LimitesExcedidosConversorException();
			
		}
		return convertIntToHexa( (char) numero);		
	} 
	
	
	
	public static String decimalToHexa(double numero ) throws LimitesExcedidosConversorException {
		
		Casteador casteador = new Casteador();
		
		char nHexa;
		try {
			
			nHexa = casteador.puntoFlotante(numero);
		
		} catch (OverFlowCasteadorException | UnderFlowCasteadorException e) {
		
			throw new  LimitesExcedidosConversorException();
		}
		
		return convertIntToHexa(nHexa); 
		
		
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
