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
import ar.com.compumundohipermegared.interfacesUsuario.MemoryTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.interfacesUsuario.PipelineTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.ProgramCounterTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.RegistryTableModel;
import ar.com.compumundohipermegared.simulador.Modelo;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class EjecutarContoller {
	private static void actualizarMemoria(IMemoria memoria, MemoryTableModel memoryTableModel){

		for (int fila = 0; fila < memoria.getTamanio(); ++fila) {
			for (int columna = 0; columna < memoria.getTamanio(); ++columna) {
				try {
					memoryTableModel.setValueAt(Byte.toString(memoria.getDatoMemoria(fila, columna)), fila, columna);
				} catch (LimiteExcedidoMemoriaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private static void actualizarRegistros(AreaRegistro registros, RegistryTableModel registryTableModel){
		for (int numReg=0; numReg < registros.getTamanio(); numReg++){
			try {
				registryTableModel.setValueAt(Byte.toString(registros.getDatoRegistro(numReg)), numReg);
			} catch (LimiteExcedidoAreaRegistroException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void actualizarPipeline(ArrayList<DireccionMasInstruccion> pipeline, PipelineTableModel pipelineTableModel){
		for (int numReg=0; numReg < pipeline.size(); numReg++){
			pipelineTableModel.setValueAt(pipeline.get(numReg).getInstruccion(), numReg);
		}
	}
	
	private static void actualizarPC(AreaRegistroCpu registros, ProgramCounterTableModel pcTableModel){
		pcTableModel.setValueAt(Integer.toString(registros.getPC().getDato()));
	} 
	
	public static void ejecutar(String Ruta, ModelosInterfaz interfaz) throws FileNotFoundException, ProgramaMalFormadoException{
		Modelo.crearModelo(Ruta);
		Modelo modelo = Modelo.getModelo();
		modelo.ejecutar();
		while (!(modelo.getCpu().terminoEjecucion())) {
			try {
				if (modelo.getCpu().necesitaDatoEntrada()) {
					byte dato = pedirEntradaUsuario();
					modelo.getMemoria().escribirDispositivoEntrada(dato);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IMemoria memoria = modelo.getMemoria();
		MemoryTableModel memoryTableModel = interfaz.getMemory();
		actualizarMemoria(memoria, memoryTableModel);
		
		AreaRegistro registros = modelo.getRegistros();
		RegistryTableModel registryTableModel =interfaz.getRegistry(); 
		actualizarRegistros(registros, registryTableModel);
		
		ArrayList<DireccionMasInstruccion> pipeline = modelo.getPipeline();
		PipelineTableModel pipelineModel = interfaz.getPipeline();
		actualizarPipeline(pipeline, pipelineModel);
		
		AreaRegistroCpu registrosControl = modelo.getRegistrosControl();
		ProgramCounterTableModel pcModel = interfaz.gettPC();
		actualizarPC(registrosControl, pcModel);
	}
	
	private static byte pedirEntradaUsuario() {
		Scanner ins = new Scanner(System.in);
        String respuesta = ins.nextLine();
        int respuestaNum = Integer.parseInt(respuesta,10);
		return (byte) respuestaNum;
	}
}
