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
	
		
	
	private static int correrReferencia(int numInt) throws LimitesExcedidosConversorException {
		
		if( numInt > 47 && numInt < 58)
			return numInt - 48 ;
		
		else if( numInt > 64 && numInt < 71)
			return numInt - 55 ;
		
		else if( numInt > 96 && numInt < 103)
			return numInt - 87 ;
		
		else throw new LimitesExcedidosConversorException(); 
	}
	
	
	
	
	public static String decimalToHexa( int numero) throws LimitesExcedidosConversorException {
													
		Casteador casteador = new Casteador();
		try{
			casteador.complementoADos(numero);
		}
		catch( OverFlowCasteadorException error){
			throw new  LimitesExcedidosConversorException();
			
		}
		return convertIntToHexa( (char) numero);		
	} 
	
	public static String decimalToHexaDoblePrecision (int numero) throws LimitesExcedidosConversorException {
		int highByte = numero >> 8;
		int lowByte = numero & 0xff;
		String hB = convertIntToHexa ((char) highByte);
		String lB = convertIntToHexa ((char) lowByte);
		return (hB + lB);
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
		
		if( nHexa.length() > 2) throw new LimitesExcedidosConversorException();
		
		int primerCaracter = correrReferencia( nHexa.charAt(0) );
		int segundoCaracter = correrReferencia( nHexa.charAt(1) );
		
		int highNible = primerCaracter << 4;
		highNible =  highNible & 0xf0;
		segundoCaracter = segundoCaracter & 0x0f;
		
		int numero = highNible | segundoCaracter;
		if( numero > 127 ) numero = numero - 256;
		
		return numero;
				
	}
	
	public static int complementoDosADecimalDoblePrecision (String nHexa) throws LimitesExcedidosConversorException {
		// recibo por ejemplo 0AFF
		// devuelvo 2815
		if( nHexa.length() != 4) throw new LimitesExcedidosConversorException();
		
		int primerByte = complementoDosADecimal(nHexa.substring(2));   //De esto obtengo FF
		int segundoByte = complementoDosADecimal(nHexa.substring(0,2)); //De esto obtengo el 0A
		
		if (primerByte < 0) primerByte = primerByte + 256;  //sumo 256 pq lo q recibo varia entre -128 a 128 
		
		if (segundoByte < 0) segundoByte = segundoByte + 256;
		
		int numero = (segundoByte << 8) | primerByte ;
		
		if( numero > 32767 ) numero = numero - 65536; //El numero varia entre -32768 a 32767
		
		return numero;
	}
	
	
	public static double puntoFlotanteADecimal(String nHexa) throws  LimitesExcedidosConversorException {
		
		if( nHexa.length() != 2) throw new LimitesExcedidosConversorException();
		
		int primerCaracter = correrReferencia( nHexa.charAt(0) ); //signo + Exponente
		int segundoCaracter = correrReferencia( nHexa.charAt(1) );  //mantisa
				
		int signo , exponente;
		
		signo = primerCaracter & 0x8; // se multiplica por 1000 = 8 para obtener el bit de signo
		signo = signo >> 3;
		boolean esPositivo = !(signo == 1);
		
		exponente = primerCaracter & 0x7; //Se multiplica por 7 para recuperar los 3 bits del exponente
		exponente = exponente - 4; //Este reperesentado en Exceso de 4
		
		double nbase = 1;
		nbase += (segundoCaracter & 0x1) * Math.pow(2, -4);
		nbase += ( (segundoCaracter & 0x2) / 2 ) * Math.pow(2, -3);
		nbase += ( (segundoCaracter & 0x4) / 4 ) * Math.pow(2, -2);
		nbase += ( (segundoCaracter & 0x8) / 8 ) * Math.pow(2, -1);
		 
		
		double numero = nbase * Math.pow(2, exponente);
		
		if(!esPositivo) numero = numero * (-1);
		
		return numero;
		
	}
	
	public static double puntoFlotanteADecimal(char flotante) throws  LimitesExcedidosConversorException {
		String hexa = convertIntToHexa(flotante);
		return (puntoFlotanteADecimal(hexa));
	}
	
	public static double puntoFlotanteADecimal(byte flotante) throws  LimitesExcedidosConversorException {
		String hexa = convertIntToHexa((char)flotante);
		return (puntoFlotanteADecimal(hexa));
	}

	public static String decimalToComplemento(byte datoMemoria) {
		// TODO Este metodo deberia recibir un byte en formato decimaly devolver un string que lo represente en complemento
		String bit = String.format("%8s", Integer.toBinaryString(datoMemoria & 0XFF)).replace(' ','0');		
		return bit;
	}
	
	public static String decimalToComplemento(int datoMemoria) {
		// TODO Este metodo deberia recibir un int en formato decimaly devolver un string que lo represente en complemento
		String bit = String.format("%8s", Integer.toBinaryString(datoMemoria & 0XFF)).replace(' ','0');
		return bit;
	}
	
}
