package ar.com.compumundohipermegared.controladores;

import java.io.FileNotFoundException;

import ar.com.compumundohipermegared.controladores.ArquitecturaVisualizacionController.Representacion;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.interfacesUsuario.VentanaSimulador;
import ar.com.compumundohipermegared.simulador.Modelo;
import ar.com.compumundohipermegared.simulador.ModeloPasoAPaso;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.CpuPasoAPaso;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class EjecutarPasoAPasoController extends EjecutarContoller {
	
	public void avanzarPaso(ModelosInterfaz interfaz, VentanaSimulador ventana) {
		Modelo modelo = ModeloPasoAPaso.getModelo();
		CpuPasoAPaso cpu = (CpuPasoAPaso) modelo.getCpu();
		cpu.avanzar();
		actualizaciones(modelo, interfaz, ventana);
	}
	
	private void actualizaciones(Modelo modelo, ModelosInterfaz interfaz, VentanaSimulador ventana) {
		CpuPasoAPaso cpu = (CpuPasoAPaso) modelo.getCpu();
		while (!(cpu.yaAvanzo()) && !(cpu.terminoEjecucion())) {
			try {
				if (modelo.getCpu().necesitaDatoEntrada()) {
					EjecutarContoller ejecucionController = new EjecutarContoller();
					byte dato = ejecucionController.pedirEntradaUsuario(ventana);
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
	
	public void ejecutar(String Ruta, ModelosInterfaz interfaz, VentanaSimulador ventana) throws FileNotFoundException, ProgramaMalFormadoException{
		ModeloPasoAPaso.crearModelo(Ruta);
		Modelo modelo = Modelo.getModelo();
		modelo.ejecutar();
		actualizaciones(modelo, interfaz, ventana);
	}
	
}
