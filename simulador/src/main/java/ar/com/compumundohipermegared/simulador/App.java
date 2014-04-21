package ar.com.compumundohipermegared.simulador;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.com.compumundohipermegared.simulador.cicloInstruccion.ParserInstrucciones;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void cargarPrograma(Cpu cpu){
        cpu.cargarInstruccion("1AAA");
        cpu.cargarInstruccion("2BBA");
        cpu.cargarInstruccion("3AAA");
        cpu.cargarInstruccion("4AAA");
        cpu.cargarInstruccion("5BBA");
        cpu.cargarInstruccion("6AAA");
        cpu.cargarInstruccion("7AAA");
        cpu.cargarInstruccion("8BBA");
        cpu.cargarInstruccion("9AAA");
        cpu.cargarInstruccion("AAAA");
        cpu.cargarInstruccion("BBBA");
        cpu.cargarInstruccion("CAAA");
	}
    public static void main( String[] args )
    {
        Cpu cpu = new Cpu();
        cargarPrograma(cpu);
        cpu.ejecutarPrograma();
    }
}
