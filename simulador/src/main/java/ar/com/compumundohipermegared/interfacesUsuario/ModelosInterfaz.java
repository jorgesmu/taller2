package ar.com.compumundohipermegared.interfacesUsuario;

public class ModelosInterfaz {
	private MemoryTableModel memoryTableModel;
	private PipelineTableModel pipelineTableModel;
	private ProgramCounterTableModel pcModel;
	private RegistryTableModel registryTableModel;
	public ModelosInterfaz(MemoryTableModel memory, PipelineTableModel pipeline, ProgramCounterTableModel pc, RegistryTableModel registry){
		this.memoryTableModel = memory;
		this.pipelineTableModel = pipeline;
		this.pcModel = pc;
		this.registryTableModel = registry;
	}
	
	public MemoryTableModel getMemory(){
		return memoryTableModel;
	}
	
	public PipelineTableModel getPipeline(){
		return pipelineTableModel;
	}
	
	public ProgramCounterTableModel gettPC(){
		return pcModel;
	}
	
	public RegistryTableModel getRegistry(){
		return registryTableModel;
	}
}
