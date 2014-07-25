package ar.com.compumundohipermegared.controladores;

import java.util.ArrayList;

import ar.com.compumundohipermegared.almacenamiento.AreaRegistro;
import ar.com.compumundohipermegared.almacenamiento.AreaRegistroCpu;
import ar.com.compumundohipermegared.almacenamiento.DireccionMasInstruccion;
import ar.com.compumundohipermegared.almacenamiento.IMemoria;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoAreaRegistroException;
import ar.com.compumundohipermegared.almacenamiento.LimiteExcedidoMemoriaException;
import ar.com.compumundohipermegared.conversor.Conversor;
import ar.com.compumundohipermegared.conversor.LimitesExcedidosConversorException;
import ar.com.compumundohipermegared.interfacesUsuario.MemoryTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.ModelosInterfaz;
import ar.com.compumundohipermegared.interfacesUsuario.PipelineTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.ProgramCounterTableModel;
import ar.com.compumundohipermegared.interfacesUsuario.RegistryTableModel;
import ar.com.compumundohipermegared.simulador.Modelo;

public class ArquitecturaVisualizacionController {
	public enum Representacion{DECIMAL, COMPLEMENTO, HEXA}
	public static void actualizarMemoria(IMemoria memoria, MemoryTableModel memoryTableModel, Representacion representacion){
		for (int fila = 0; fila < memoria.getTamanio(); ++fila) {
			for (int columna = 0; columna < memoria.getTamanio(); ++columna) {
				try {
					String data = "";
					switch (representacion) {
					case DECIMAL:
						data = Byte.toString(memoria.getDatoMemoria(fila, columna));
						break;
					case HEXA:
						data = Conversor.decimalToHexa(memoria.getDatoMemoria(fila, columna));
						break;
					case COMPLEMENTO:
						data = Conversor.decimalToComplemento(memoria.getDatoMemoria(fila, columna));
						break;
					}
					memoryTableModel.setValueAt(data, fila, columna);
				}catch (LimiteExcedidoMemoriaException | LimitesExcedidosConversorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void actualizarRegistros(AreaRegistro registros, RegistryTableModel registryTableModel, Representacion representacion){
		for (int numReg=0; numReg < registros.getTamanio(); numReg++){
			try {
				String data = "";
				switch (representacion) {
				case DECIMAL:
					data = Byte.toString(registros.getDatoRegistro(numReg));
					break;
				case HEXA:
					data = Conversor.decimalToHexa(registros.getDatoRegistro(numReg));
					break;
				case COMPLEMENTO:
					data = Conversor.decimalToComplemento(registros.getDatoRegistro(numReg));
					break;
				}
				registryTableModel.setValueAt(data, numReg);
			} catch (LimiteExcedidoAreaRegistroException | LimitesExcedidosConversorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void actualizarPipeline(ArrayList<DireccionMasInstruccion> pipeline, PipelineTableModel pipelineTableModel){
		for (int numReg=0; numReg < pipeline.size(); numReg++){
			pipelineTableModel.setValueAt(pipeline.get(numReg).getInstruccion(), numReg);
		}
	}
	
	public static void actualizarPC(AreaRegistroCpu registros, ProgramCounterTableModel pcTableModel, Representacion representacion){
		try {
			String data = "";
			switch (representacion) {
			case DECIMAL:
				data = Integer.toString(registros.getPC().getDato());
				break;
			case HEXA:
				data = Conversor.decimalToHexa(registros.getPC().getDato());
				break;
			case COMPLEMENTO:
				data = Conversor.decimalToComplemento(registros.getPC().getDato());
				break;
			}
			pcTableModel.setValueAt(data);

		}catch (Exception e){}
	}
	
	public static void actualizacionInterfaz(Modelo modelo, ModelosInterfaz interfaz, Representacion representacion) {
		IMemoria memoria = modelo.getMemoria();
		MemoryTableModel memoryTableModel = interfaz.getMemory();
		actualizarMemoria(memoria, memoryTableModel, representacion);
		
		AreaRegistro registros = modelo.getRegistros();
		RegistryTableModel registryTableModel =interfaz.getRegistry(); 
		actualizarRegistros(registros, registryTableModel, representacion);
		
		ArrayList<DireccionMasInstruccion> pipeline = modelo.getPipeline();
		PipelineTableModel pipelineModel = interfaz.getPipeline();
		actualizarPipeline(pipeline, pipelineModel);
		
		AreaRegistroCpu registrosControl = modelo.getRegistrosControl();
		ProgramCounterTableModel pcModel = interfaz.gettPC();
		actualizarPC(registrosControl, pcModel,representacion);
	}
	
}
