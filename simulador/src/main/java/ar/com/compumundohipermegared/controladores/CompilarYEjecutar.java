package ar.com.compumundohipermegared.controladores;

import java.io.FileNotFoundException;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.ExtensionInvalidaException;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.compilacion.ProgramaMuyLargoException;
import ar.com.compumundohipermegared.compilacion.ProgramaYaCompiladoException;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class CompilarYEjecutar {
	
	public static void compilarYEjecutar(String ruta, ModelosInterfaz interfaz) throws FileNotFoundException, ExtensionInvalidaException, ProgramaMuyLargoException, ProgramaYaCompiladoException, InstruccionAssemblyInvalidaException, ProgramaMalFormadoException {
		Compilador comp = new Compilador(ruta);
		String rutaCompilado = comp.compilar();
		EjecutarContoller.ejecutar(rutaCompilado, interfaz);
	}
	
}
