package ar.com.compumundohipermegared.controladores;

import ar.com.compumundohipermegared.controladores.ArquitecturaVisualizacionController.Representacion;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.simulador.Modelo;

public class ConversorController {
	public void visualizarDecimal(ModelosInterfaz interfaz){
		Modelo modelo = Modelo.getModelo();
		ArquitecturaVisualizacionController visualizacionController = new ArquitecturaVisualizacionController();
		visualizacionController.actualizacionInterfaz(modelo, interfaz, Representacion.DECIMAL);
	}
	public void visualizarHexa(ModelosInterfaz interfaz){
		Modelo modelo = Modelo.getModelo();
		ArquitecturaVisualizacionController visualizacionController = new ArquitecturaVisualizacionController();
		visualizacionController.actualizacionInterfaz(modelo, interfaz, Representacion.HEXA);
	}
	public void visualizarComplemento(ModelosInterfaz interfaz){
		Modelo modelo = Modelo.getModelo();
		ArquitecturaVisualizacionController visualizacionController = new ArquitecturaVisualizacionController();
		visualizacionController.actualizacionInterfaz(modelo, interfaz, Representacion.COMPLEMENTO);
	}

}
