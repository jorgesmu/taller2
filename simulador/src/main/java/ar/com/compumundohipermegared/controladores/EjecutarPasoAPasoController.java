package ar.com.compumundohipermegared.controladores;

import java.io.FileNotFoundException;

import ar.com.compumundohipermegared.controladores.ArquitecturaVisualizacionController.Representacion;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.interfacesUsuario.VentanaPPal;
import ar.com.compumundohipermegared.simulador.Modelo;
import ar.com.compumundohipermegared.simulador.ModeloPasoAPaso;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.CpuPasoAPaso;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class EjecutarPasoAPasoController extends EjecutarContoller {
	
	public static void avanzarPaso(ModelosInterfaz interfaz, VentanaPPal ventana) {
		Modelo modelo = ModeloPasoAPaso.getModelo();
		CpuPasoAPaso cpu = (CpuPasoAPaso) modelo.getCpu();
		cpu.avanzar();
		actualizaciones(modelo, interfaz, ventana);
	}
	
	private static void actualizaciones(Modelo modelo, ModelosInterfaz interfaz, VentanaPPal ventana) {
		CpuPasoAPaso cpu = (CpuPasoAPaso) modelo.getCpu();
		while (!(cpu.yaAvanzo()) && !(cpu.terminoEjecucion())) {
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
		ArquitecturaVisualizacionController.actualizacionInterfaz(modelo, interfaz, Representacion.DECIMAL);
	}
	
	public static void ejecutar(String Ruta, ModelosInterfaz interfaz, VentanaPPal ventana) throws FileNotFoundException, ProgramaMalFormadoException{
		ModeloPasoAPaso.crearModelo(Ruta);
		Modelo modelo = Modelo.getModelo();
		modelo.ejecutar();
		actualizaciones (modelo, interfaz, ventana);
	}
	
}
