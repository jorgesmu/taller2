package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import ar.com.compumundohipermegared.compilacion.ExtensionInvalidaException;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.compilacion.ProgramaMuyLargoException;
import ar.com.compumundohipermegared.compilacion.ProgramaYaCompiladoException;
import ar.com.compumundohipermegared.controladores.CompilarYEjecutar;
import ar.com.compumundohipermegared.controladores.CompilarYEjecutarPasoAPaso;
import ar.com.compumundohipermegared.controladores.ConversorController;
import ar.com.compumundohipermegared.controladores.EjecutarContoller;
import ar.com.compumundohipermegared.controladores.EjecutarPasoAPasoController;
import ar.com.compumundohipermegared.simulador.Modelo;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

public class VentanaSimulador implements ActionListener, MouseListener {
	public JFrame frame;
	public JButton btnAbrirAssembly;
	public JButton btnEjecutarAssembly;
	public JButton btnGuardarAssembly;
	public JButton btnRutaAssembly;
	public JButton btnAbrirAbsoluto;
	public JButton btnEjecutarAbsoluto;
	public JButton btnGuardarAbsoluto;
	public JButton btnRutaAbsoluto;
	public JButton btnConvertirDecimal;
	public JButton btnConvertirA2;
	public JButton btnConvertirHexa;
	public JButton btnPasoAPaso;
	public boolean enEjecucion = false;
	public JCheckBox lineByLineAssembly;
	public JCheckBox lineByLineAbsoluto;
	public JTextArea txtCodigoAssembly;
	public JFileChooser dialogAssembly;
	public JLabel lblRutaAssembly;
	public JTextField txtNombreAssembly;
	public JFileChooser dialogAbsoluto;
	public JLabel lblRutaAbsoluto;
	public JTextArea txtCodigoAbsoluto;
	public JTextField txtNombreAbsoluto;
	public String codigoHintAbsoluto;
	public String nombreHintAbsoluto;
	public String codigoHintAssembly;
	public String nombreHintAssembly;	
	public JTabbedPane tabbedPane;
	public MemoryTableModel memoryTableModel;
	public RegistryTableModel registryTableModel;
	public PipelineTableModel pipelineTableModel;
	public ProgramCounterTableModel pcTableModel;
	public ModelosInterfaz modelosTablas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSimulador window = new VentanaSimulador();
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaSimulador() {
		inicializar();
	}

	private void inicializar() {
		frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setBounds(0, 0, 1300, 1000);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pestana1 = new JPanel();
		tabbedPane.addTab("Assembly", null, pestana1, null);
		EditorAssemblyPestana pestanaAssembly = new EditorAssemblyPestana();
		pestanaAssembly.inicializarVentanaAssembly(pestana1, this);
		pestana1.setLayout(null);

		JPanel pestana2 = new JPanel();
		tabbedPane.addTab("Absoluto", null, pestana2, null);
		EditorCodigoAbsolutoPestana pestanaAbsoluto = new EditorCodigoAbsolutoPestana();
		pestanaAbsoluto.inicializarVentanaAbsoluto(pestana2, this);
		pestana2.setLayout(null);
		
		JPanel pestana3 = new JPanel();
		tabbedPane.addTab("Arquitectura", null, pestana3, null);
		ArquitecturaPestana pestanaArquitectura = new ArquitecturaPestana();
		pestanaArquitectura.inicializarVentanaArquitectura(pestana3, this);
		pestana3.setLayout(null);

		JPanel pestana4 = new JPanel();
		tabbedPane.addTab("Ayuda en linea", null, pestana4, null);
		AyudaEnLineaPestana pestanaAyuda = new AyudaEnLineaPestana();
		pestanaAyuda.inicializarVentanaAyuda(pestana4);
		pestana4.setLayout(null);

	}

	private void ejecutarAssemblyHandler(){
		String path = lblRutaAssembly.getText();
		String filename = txtNombreAssembly.getText();
		guardarText(txtCodigoAssembly.getText(),path, filename,".asm");
		try {
			if (lineByLineAssembly.isSelected()){
				CompilarYEjecutarPasoAPaso compilarPasoAPasoController = new CompilarYEjecutarPasoAPaso();
				compilarPasoAPasoController.compilarYEjecutar(path + "/" + filename +".asm", modelosTablas, this);
				btnPasoAPaso.setEnabled(true);
			}else{
				CompilarYEjecutar compilarController = new CompilarYEjecutar();
				compilarController.compilarYEjecutar(path + "/" + filename +".asm", modelosTablas, this);
				habilitarBotonesConversion();
			}
			txtCodigoAbsoluto.setText(abrirTxt(path + "/" + filename + ".maq"));
			lblRutaAbsoluto.setText(lblRutaAssembly.getText());
			txtNombreAbsoluto.setText(filename);
			tabbedPane.setSelectedIndex(2);


			JOptionPane.showMessageDialog(null,Modelo.getModelo().getCpu().resultado);
		} catch (ExtensionInvalidaException
				| ProgramaMuyLargoException | ProgramaYaCompiladoException
				| InstruccionAssemblyInvalidaException
				| ProgramaMalFormadoException | IOException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		}
	}
	
	private void rutaAssemblyHandler(){
		int resultado = dialogAssembly.showOpenDialog(new JPanel());
		if  (resultado == JFileChooser.APPROVE_OPTION){
			btnEjecutarAssembly.setEnabled(true);
			lblRutaAssembly.setText(dialogAssembly.getSelectedFile().getAbsolutePath());
			btnGuardarAssembly.setEnabled(true);
		}
	}
	
	private void guardarAssemblyHandler(){
		guardarText(txtCodigoAssembly.getText(),lblRutaAssembly.getText(),txtNombreAssembly.getText(),".asm");			
	    JOptionPane.showMessageDialog(null,"Archivo assembly guardado correctamente");		
	}
	
	private void ejecutarAbsolutoHandler(){
		try {
			String path = lblRutaAbsoluto.getText();
			String filename = txtNombreAbsoluto.getText();
			guardarText(txtCodigoAbsoluto.getText(),path, filename,".maq");
			if (lineByLineAbsoluto.isSelected()){
				EjecutarPasoAPasoController pasoAPasoController = new EjecutarPasoAPasoController();
				pasoAPasoController.ejecutar(path + "/" + filename +".maq", modelosTablas, this);
				btnPasoAPaso.setEnabled(true);
			}else{
				EjecutarContoller ejecucionController = new EjecutarPasoAPasoController();
				ejecucionController.ejecutar(path + "/" + filename +".maq", modelosTablas, null);
				habilitarBotonesConversion();
			}
			JOptionPane.showMessageDialog(null,Modelo.getModelo().getCpu().resultado);
			tabbedPane.setSelectedIndex(2);
		} catch (FileNotFoundException | ProgramaMalFormadoException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		}
		notificarCambiosTablas();
	}
	
	private void rutaAbsolutoHandler(){
		int resultado = dialogAbsoluto.showOpenDialog(new JPanel());
		if  (resultado == JFileChooser.APPROVE_OPTION){
			btnEjecutarAbsoluto.setEnabled(true);
			lblRutaAbsoluto.setText(dialogAbsoluto.getSelectedFile().getAbsolutePath());
		}
	}
	private String getFilename(String pathCompleto){
		String filename = pathCompleto.substring(pathCompleto.lastIndexOf("/") + 1);
		return 	filename.substring(0,filename.lastIndexOf("."));
	}
	private String getDirectory(String pathCompleto){
		return pathCompleto.substring(0, pathCompleto.lastIndexOf("/") + 1);
	}
	private void abrirAssemblyHandler(){
		String pathCompleto = obtenerRuta(frame, "asm", "Codigo assembly (.asm)");
		if (pathCompleto != null){
			String filename = getFilename(pathCompleto);
			String directorio = getDirectory(pathCompleto);
			lblRutaAssembly.setText(directorio);
			txtNombreAssembly.setText(filename);
			btnEjecutarAssembly.setEnabled(true);
			try {
				txtCodigoAssembly.setText(abrirTxt(pathCompleto));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void abrirAbsolutoHandler(){

		String pathCompleto = obtenerRuta(frame,"maq", "Codigo de maquina (.maq)");
		if (pathCompleto != null){
			String filename = getFilename(pathCompleto);
			String directorio = getDirectory(pathCompleto);
			lblRutaAbsoluto.setText(directorio);
			txtNombreAbsoluto.setText(filename);
			btnEjecutarAbsoluto.setEnabled(true);
			try {
				txtCodigoAbsoluto.setText(abrirTxt(pathCompleto));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void guardarAbsolutoHandler(){
		guardarText(txtCodigoAbsoluto.getText(),lblRutaAbsoluto.getText(),txtNombreAbsoluto.getText(),".maq");			
	    JOptionPane.showMessageDialog(null,"Archivo absoluto guardado correctamente");
	}
	
	private void convertirDecimalHandler(){
		ConversorController controller = new ConversorController();
		controller.visualizarDecimal(modelosTablas);
		notificarCambiosTablas();
	}
	
	private void convertirA2handler(){
		ConversorController controller = new ConversorController();
		controller.visualizarComplemento(modelosTablas);
		notificarCambiosTablas();
	}
	
	private void convertirHexaHandler(){
		ConversorController controller = new ConversorController();
		controller.visualizarHexa(modelosTablas);
		notificarCambiosTablas();
	}
	
	private void pasoAPasoHandler(){
		ejecutarPasoAPaso();
		habilitarBotonesConversion();
		notificarCambiosTablas();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEjecutarAssembly){
			ejecutarAssemblyHandler();
		}else if (e.getSource() == btnRutaAssembly){
			rutaAssemblyHandler();
		}else if (e.getSource() == btnGuardarAssembly){
			guardarAssemblyHandler();
		}else if (e.getSource() == btnEjecutarAbsoluto){
			ejecutarAbsolutoHandler();
		}else if (e.getSource() == btnRutaAbsoluto){
			rutaAbsolutoHandler();
		}else if (e.getSource() == btnAbrirAssembly){
			abrirAssemblyHandler();
		}else if (e.getSource() == btnAbrirAbsoluto){
			abrirAbsolutoHandler();
		}else if (e.getSource() == btnGuardarAbsoluto){
				guardarAbsolutoHandler();
		}else if (e.getSource() == btnConvertirDecimal){
				convertirDecimalHandler();
		}else if (e.getSource() == btnConvertirA2){
				convertirA2handler();
		}else if (e.getSource() == btnConvertirHexa){
				convertirHexaHandler();
		}else if (e.getSource() == btnPasoAPaso){
			pasoAPasoHandler();
		}
	}
	private void habilitarBotonesConversion(){
		btnConvertirA2.setEnabled(true);
		btnConvertirDecimal.setEnabled(true);
		btnConvertirHexa.setEnabled(true);		
	}
	private void notificarCambiosTablas(){
		registryTableModel.fireTableDataChanged();
		memoryTableModel.fireTableDataChanged();
		pipelineTableModel.fireTableDataChanged();
		pcTableModel.fireTableDataChanged();
	}
	private void ejecutarPasoAPaso(){
		EjecutarPasoAPasoController pasoAPasoController = new EjecutarPasoAPasoController();
		pasoAPasoController.avanzarPaso(modelosTablas, this);
		notificarCambiosTablas();
		Cpu cpu = Modelo.getModelo().getCpu();
		if (cpu.terminoEjecucion()) {
			JOptionPane.showMessageDialog(null,cpu.resultado);
			btnPasoAPaso.setEnabled(false);
		}
	}
	private String obtenerRuta(JFrame parentComponent, String extension, String extensionDescription){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(extensionDescription , extension);
		chooser.setFileFilter(filter);
		int option = chooser.showOpenDialog(parentComponent); 
		if (option == JFileChooser.APPROVE_OPTION) {
		   File selectedFile = chooser.getSelectedFile();
		   return selectedFile.getAbsolutePath();
		}
		return null;
	}
	private String abrirTxt(String path) throws IOException{
	   BufferedReader reader = new BufferedReader(new FileReader(path));
	    String texto = "";
	    String line = "";
	    while ((line = reader.readLine()) != null) {
	        texto += line + "\n";
	    }
	    reader.close();
	    return texto;
	}
	private void guardarText(String file, String ruta,String filename, String extension){
		BufferedWriter wr;
		String path = (ruta + "/" + filename + extension) ;
        try { 
        	wr = new BufferedWriter(new FileWriter(path));
            wr.write(file);
            wr.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }		
	}
	
	//
	// PEDIR DATO DE ENTRADA
	//
	public int pedirEntradaUsuario() {
		 int resultado = 0;
		 boolean valorIngresadoNumerico = false;
		 boolean CANCELO = true;
		 int MINVALUE = -128, MAXVALUE = 127;
		 while (!valorIngresadoNumerico) {
			 String inputValue = JOptionPane.showInputDialog(null,"Ingresar valor dentro del rango [-128;127], si es valor es invalido o cancela, se toma cero por defecto. ","Ingrese un dato desde dispositivo", JOptionPane.INFORMATION_MESSAGE);
			 if (inputValue != null){
			 try {
					resultado = Integer.parseInt(inputValue);
					if (resultado < MINVALUE || resultado > MAXVALUE){
						resultado = 0;
					}
					valorIngresadoNumerico = true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: Se debe ingresar un valor numerico. Ingreselo nuevamente.", "Error: formato de entrada no valida", JOptionPane.ERROR_MESSAGE);
				}
			 }else {
				 valorIngresadoNumerico = CANCELO;
			 }
		}
		 return resultado;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == txtCodigoAbsoluto){
			if (txtCodigoAbsoluto.getText().equals(codigoHintAbsoluto)) txtCodigoAbsoluto.setText("");
		}else if (e.getSource() == txtNombreAbsoluto){
			if (txtNombreAbsoluto.getText().equals(nombreHintAbsoluto)) txtNombreAbsoluto.setText("");
		}else if (e.getSource() == txtCodigoAssembly){
			if (txtCodigoAssembly.getText().equals(codigoHintAssembly)) txtCodigoAssembly.setText("");
		}else if (e.getSource() == txtNombreAssembly){
			if (txtNombreAssembly.getText().equals(nombreHintAssembly)) txtNombreAssembly.setText("");
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;
	}

}
