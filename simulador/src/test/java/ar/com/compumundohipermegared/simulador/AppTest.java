package ar.com.compumundohipermegared.simulador;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.Alu;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testsumar()
    {
    	int number = 33;
    	int otherNumber=Alu.plus(number, 0);
        assertEquals(number, otherNumber);
        
        otherNumber= Alu.plus(number, number);
        assertEquals(number*2, otherNumber);
        
        otherNumber= Alu.plus(number, -1);
        assertEquals(number-1, otherNumber);
    }
    
    public void testRestar()
    {
    	int number = 33;
    	int otherNumber=Alu.minus(number, 0);
        assertEquals(number, otherNumber);
        
        otherNumber= Alu.minus(number, number);
        assertEquals(0, otherNumber);
        
        otherNumber= Alu.minus(number, -1);
        assertEquals(number+1, otherNumber);
    }
    
    public void testMultiplicar()
    {
    	int number = 33;
    	int otherNumber=Alu.multi(number, number);
        assertEquals(number*number, otherNumber);
        
        otherNumber= Alu.multi(number, 0);
        assertEquals(0, otherNumber);
        
        otherNumber= Alu.multi(number, -1);
        assertEquals(number - number - number, otherNumber);
    }
    
    public void testDividirValido()
    {
    	int number = 33;
    	try {
    		int otherNumber=Alu.div(number, number);
    		assertEquals(1, otherNumber);
    	} catch (DivisionByZeroException error) {
    		assert(false);
    	}
    }
    
    public void testDividirInvalido()
    {
    	int number = 33;
    	int otherNumber = 0;
    	try {
    		Alu.div(number, otherNumber);
    		assert(false);
    	} catch (DivisionByZeroException error) {
    		assert(true);
    	}
    }
}
