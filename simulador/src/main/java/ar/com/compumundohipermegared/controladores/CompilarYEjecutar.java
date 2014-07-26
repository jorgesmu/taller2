package ar.com.compumundohipermegared.controladores;

import java.io.FileNotFoundException;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.ExtensionInvalidaException;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.compilacion.ProgramaMuyLargoException;
import ar.com.compumundohipermegared.compilacion.ProgramaYaCompiladoException;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.interfacesUsuario.VentanaSimulador;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class CompilarYEjecutar {
	
	public void compilarYEjecutar(String ruta, ModelosInterfaz interfaz, VentanaSimulador ventana) throws FileNotFoundException, ExtensionInvalidaException, ProgramaMuyLargoException, ProgramaYaCompiladoException, InstruccionAssemblyInvalidaException, ProgramaMalFormadoException {
		Compilador comp = new Compilador(ruta);
		String rutaCompilado = comp.compilar();
		EjecutarPasoAPasoController pasoAPasoController = new EjecutarPasoAPasoController();
		pasoAPasoController.ejecutar(rutaCompilado, interfaz, ventana);
	}
	
}
