package ar.com.compumundohipermegared.controladores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import ar.com.compumundohipermegared.almacenamiento.AreaRegistro;
import ar.com.compumundohipermegared.almacenamiento.AreaRegistroCpu;
import ar.com.compumundohipermegared.almacenamiento.DireccionMasInstruccion;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoAreaRegistroException;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoMemoriaException;
import ar.com.compumundohipermegared.controladores.ArquitecturaVisualizacionController.Representacion;
import ar.com.compumundohipermegared.interfacesUsuario.MemoryTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.interfacesUsuario.PipelineTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.ProgramCounterTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.RegistryTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.VentanaSimulador;
import ar.com.compumundohipermegared.simulador.Modelo;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class EjecutarContoller {

	public void ejecutar(String Ruta, ModelosInterfaz interfaz, VentanaSimulador ventana) throws FileNotFoundException, ProgramaMalFormadoException{
		Modelo.crearModelo(Ruta);
		Modelo modelo = Modelo.getModelo();
		modelo.ejecutar();
		while (!(modelo.getCpu().terminoEjecucion())) {
			try {
				if (modelo.getCpu().necesitaDatoEntrada()) {
					byte dato = pedirEntradaUsuario(ventana);
					modelo.getMemoria().escribirDispositivoEntrada(dato);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		ArquitecturaVisualizacionController visualizacionController = new ArquitecturaVisualizacionController();
		visualizacionController.actualizacionInterfaz(modelo, interfaz, Representacion.DECIMAL);
	}
	
	protected byte pedirEntradaUsuario(VentanaSimulador ventana) {
		return (byte) ventana.pedirEntradaUsuario();
	}
}
