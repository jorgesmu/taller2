package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import ar.com.compumundohipermegared.compilacion.Compilador;
import ar.com.compumundohipermegared.compilacion.ExtensionInvalidaException;
import ar.com.compumundohipermegared.compilacion.InstruccionAssemblyInvalidaException;
import ar.com.compumundohipermegared.compilacion.ProgramaMuyLargoException;
import ar.com.compumundohipermegared.compilacion.ProgramaYaCompiladoException;
import ar.com.compumundohipermegared.controladores.CompilarYEjecutar;
import ar.com.compumundohipermegared.controladores.CompilarYEjecutarPasoAPaso;
import ar.com.compumundohipermegared.controladores.EjecutarContoller;
import ar.com.compumundohipermegared.controladores.EjecutarPasoAPasoController;
import ar.com.compumundohipermegared.simulador.Modelo;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.Cpu;
import ar.com.compumundohipermegared.simulador.cicloInstruccion.ProgramaMalFormadoException;

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
import java.util.Scanner;

public class VentanaPPal implements ActionListener, MouseListener {
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	//instance object declaration
	JButton btnAbrirAssembly;
	JButton btnEjecutarAssembly;
	JButton btnGuardarAssembly;
	JButton btnRutaAssembly;
	JButton btnAbrirAbsoluto;
	JButton btnEjecutarAbsoluto;
	JButton btnGuardarAbsoluto;
	JButton btnRutaAbsoluto;
	JButton btnConvertirDecimal;
	JButton btnConvertirA2;
	JButton btnConvertirHexa;
	JButton btnPasoAPaso;
	boolean enEjecucion = false;
	JCheckBox lineByLineAssembly;
	JCheckBox lineByLineAbsoluto;
	JTextArea txtCodigoAssembly;
	JFileChooser dialogAssembly;
	JLabel lblRutaAssembly;
	JTextField txtNombreAssembly;
	JFileChooser dialogAbsoluto;
	JLabel lblRutaAbsoluto;
	JTextArea txtCodigoAbsoluto;
	JTextField txtNombreAbsoluto;
	String codigoHintAbsoluto;
	String nombreHintAbsoluto;
	String codigoHintAssembly;
	String nombreHintAssembly;	
	JTabbedPane tabbedPane;
	MemoryTableModel memoryTableModel;
	RegistryTableModel registryTableModel;
	PipelineTableModel pipelineTableModel;
	ProgramCounterTableModel pcTableModel;
	ModelosInterfaz modelosTablas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPPal window = new VentanaPPal();
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPPal() {
		inicializar();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void inicializar() {
		frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setBounds(0, 0, 1300, 1000);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Assembly", null, panel_1, null);
		inicializarVentanaAssembly(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Absoluto", null, panel_2, null);
		inicializarVentanaAbsoluto(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Arquitectura", null, panel_3, null);
		inicializarVentanaArquitectura(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Ayuda en linea", null, panel_4, null);
		inicializarVentanaAyuda(panel_4);
		panel_4.setLayout(null);

	}
	private void inicializarVentanaAssembly(JPanel panel){

		JLabel lblTitulo;
		JScrollPane scrollCodigo;


		lblTitulo = new JLabel("Editor de código Assembly");
		lblTitulo.setBounds(300, 15, 900, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));
		
		panel.add(lblTitulo);
	
		btnAbrirAssembly = new JButton("Abrir");
		btnAbrirAssembly.addActionListener(this);
		btnAbrirAssembly.setBounds(25, 600, 300, 60);
		panel.add(btnAbrirAssembly);
		
	
		btnGuardarAssembly = new JButton("Guardar");
		btnGuardarAssembly.addActionListener(this);
		btnGuardarAssembly.setBounds(380, 600, 300, 60);
		btnGuardarAssembly.setEnabled(false);;
		panel.add(btnGuardarAssembly);
	
		
		btnEjecutarAssembly = new JButton("Compilar y Ejecutar");
		btnEjecutarAssembly.addActionListener(this);
		btnEjecutarAssembly.setBounds(925, 600, 300, 60);
		btnEjecutarAssembly.setEnabled(false);
		panel.add(btnEjecutarAssembly);
		
	
		codigoHintAssembly = "Ingrese su código assembler aquí";
		txtCodigoAssembly = new JTextArea(codigoHintAssembly);
		txtCodigoAssembly.addMouseListener(this);
		scrollCodigo = new JScrollPane(txtCodigoAssembly);
		scrollCodigo.setBounds(25, 110, 1200, 470);
		scrollCodigo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollCodigo);
		
		dialogAssembly = new JFileChooser();
		dialogAssembly.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		lblRutaAssembly = new JLabel();
		lblRutaAssembly.setText("Ruta destino para archivos ejecutables");
		lblRutaAssembly.setBounds(25, 65, 1000, 20);
		panel.add(lblRutaAssembly);
		
		btnRutaAssembly = new JButton("Guardar en");
		btnRutaAssembly.addActionListener(this);
		btnRutaAssembly.setBounds(1010, 65, 215, 20);
		panel.add(btnRutaAssembly);
	
		nombreHintAssembly = "Nombre del archivo";
		txtNombreAssembly = new JTextField();
		txtNombreAssembly.setText(nombreHintAssembly);
		txtNombreAssembly.setBounds(20,87,1208,20);
		txtNombreAssembly.addMouseListener(this);
		panel.add(txtNombreAssembly);
		
		lineByLineAssembly = new JCheckBox("Línea por línea");
		lineByLineAssembly.setBounds(740,615,150,30);
		panel.add(lineByLineAssembly);
		
	}
	private void inicializarVentanaAbsoluto(JPanel panel) {
		JLabel lblTitulo;
		JScrollPane scrollCodigo;

		
	
		lblTitulo = new JLabel("Editor de código absoluto");
		lblTitulo.setBounds(400, 15, 600, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));
		
		panel.add(lblTitulo);
	
		btnAbrirAbsoluto = new JButton("Abrir");
		btnAbrirAbsoluto.addActionListener(this);
		btnAbrirAbsoluto.setBounds(25, 600, 300, 60);
		panel.add(btnAbrirAbsoluto);
	
		btnGuardarAbsoluto = new JButton("Guardar");
		btnGuardarAbsoluto.addActionListener(this);
		btnGuardarAbsoluto.setBounds(380, 600, 300, 60);
		panel.add(btnGuardarAbsoluto);
		
		btnEjecutarAbsoluto = new JButton("Ejecutar");
		btnEjecutarAbsoluto.addActionListener(this);
		btnEjecutarAbsoluto.setBounds(925, 600, 300, 60);
		btnEjecutarAbsoluto.setEnabled(false);;
		panel.add(btnEjecutarAbsoluto);
		
	
		codigoHintAbsoluto = "Ingrese su código absoluto aquí";
		txtCodigoAbsoluto = new JTextArea(codigoHintAbsoluto);
		txtCodigoAbsoluto.addMouseListener(this);
		scrollCodigo = new JScrollPane(txtCodigoAbsoluto);
		scrollCodigo.setBounds(25, 110, 1200, 470);
		scrollCodigo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollCodigo);
		
		dialogAbsoluto = new JFileChooser();
		dialogAbsoluto.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		lblRutaAbsoluto = new JLabel();
		lblRutaAbsoluto.setText("Ruta destino para archivos ejecutables");
		lblRutaAbsoluto.setBounds(25, 65, 1000, 20);
		panel.add(lblRutaAbsoluto);
		
		btnRutaAbsoluto = new JButton("Guardar en");
		btnRutaAbsoluto.addActionListener(this);
		btnRutaAbsoluto.setBounds(1010, 65, 215, 20);
		panel.add(btnRutaAbsoluto);
	
		nombreHintAbsoluto = "Nombre del archivo (Modificar)";
		txtNombreAbsoluto = new JTextField();
		txtNombreAbsoluto.setText(nombreHintAbsoluto);
		txtNombreAbsoluto.setBounds(20,87,1208,20);
		txtNombreAbsoluto.addMouseListener(this);
		panel.add(txtNombreAbsoluto);
	
		lineByLineAbsoluto = new JCheckBox("Línea por línea");
		lineByLineAbsoluto.setBounds(740,615,150,30);
		panel.add(lineByLineAbsoluto);
		
	}
	private void inicializarVentanaArquitectura(JPanel panel) {	
		JLabel lblTitulo;
		JLabel lblRegistros;
		JLabel lblMemoria;
		JLabel lblPipeline;
		JTable tblPipeline;
		JLabel lblProgramCounter;
		JTable tblProgramCounter;
		JTable tblMemoria;
		JTable tblRegistros;
		JScrollPane contenedorTabla;
		
		lblTitulo = new JLabel("Simulador");
		lblTitulo.setBounds(450, 15, 900, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));	
		panel.add(lblTitulo);
	
		lblMemoria = new JLabel("Memoria principal");
		lblMemoria.setBounds(25, 65, 400, 40);
		Font labelFontMemoria = lblTitulo.getFont();
		lblMemoria.setFont(new Font(labelFontMemoria.getName(), Font.PLAIN, 20));	
		panel.add(lblMemoria);
		
		memoryTableModel = new MemoryTableModel();
		tblMemoria = new JTable(memoryTableModel);
		tblMemoria.setShowGrid(true);
		tblMemoria.setShowVerticalLines(true);
		tblMemoria.setGridColor(Color.BLUE);
		tblMemoria.setBounds(25,95,1100,255);
		panel.add(tblMemoria);
		
		lblRegistros = new JLabel("Registros");
		lblRegistros.setBounds(25, 370, 400, 40);
		Font labelFontRegistros = lblRegistros.getFont();
		lblRegistros.setFont(new Font(labelFontRegistros.getName(), Font.PLAIN, 20));	
		panel.add(lblRegistros);
		
		registryTableModel = new RegistryTableModel();
		tblRegistros = new JTable(registryTableModel);
		tblRegistros.setShowGrid(true);
		tblRegistros.setShowVerticalLines(true);
		tblRegistros.setGridColor(Color.BLUE);
		tblRegistros.setBounds(25,400,100,255);
		panel.add(tblRegistros);

		lblPipeline = new JLabel("Pipeline");
		lblPipeline.setBounds(150, 370, 400, 40);
		Font labelFontPipeline = lblPipeline.getFont();
		lblPipeline.setFont(new Font(labelFontPipeline.getName(), Font.PLAIN, 20));	
		panel.add(lblPipeline);
		
		pipelineTableModel = new PipelineTableModel();
		tblPipeline = new JTable(pipelineTableModel);
		tblPipeline.setShowGrid(true);
		tblPipeline.setShowVerticalLines(true);
		tblPipeline.setGridColor(Color.BLUE);
		tblPipeline.setBounds(150,400,200,50);
		panel.add(tblPipeline);
		
		lblProgramCounter = new JLabel("Contador del programa");
		lblProgramCounter.setBounds(150, 470, 400, 40);
		Font labelFontProgramCounter = lblProgramCounter.getFont();
		lblProgramCounter.setFont(new Font(labelFontProgramCounter.getName(), Font.PLAIN, 20));	
		panel.add(lblProgramCounter);
		
		pcTableModel = new ProgramCounterTableModel();
		tblProgramCounter = new JTable(pcTableModel);
		tblProgramCounter.setShowGrid(true);
		tblProgramCounter.setShowVerticalLines(true);
		tblProgramCounter.setGridColor(Color.BLUE);
		tblProgramCounter.setBounds(150,500,200,17);
		panel.add(tblProgramCounter);
		
		btnConvertirDecimal = new JButton("Visualizar en decimal");
		btnConvertirDecimal.addActionListener(this);
		btnConvertirDecimal.setEnabled(false);
		btnConvertirDecimal.setBounds(500, 400, 200, 50);
		panel.add(btnConvertirDecimal);
		
		btnConvertirHexa = new JButton("Visualizar en hexadecimal");
		btnConvertirHexa.addActionListener(this);
		btnConvertirHexa.setEnabled(false);
		btnConvertirHexa.setBounds(500, 460, 200, 50);
		panel.add(btnConvertirHexa);
		
		btnConvertirA2 = new JButton("Visualizar en complemento");
		btnConvertirA2.addActionListener(this);
		btnConvertirA2.setEnabled(false);
		btnConvertirA2.setBounds(500, 520, 200, 50);
		panel.add(btnConvertirA2);
		
		btnPasoAPaso = new JButton("Proximo paso");
		btnPasoAPaso.setEnabled(false);
		btnPasoAPaso.addActionListener(this);
		btnPasoAPaso.setBounds(930, 400, 200, 50);
		panel.add(btnPasoAPaso);
		
		modelosTablas = new ModelosInterfaz(memoryTableModel, pipelineTableModel, pcTableModel, registryTableModel);
			
	}
	private void inicializarVentanaAyuda(JPanel panel) {
		JLabel lblTitulo;
		JLabel lblAyuda;
		lblTitulo = new JLabel("Ayuda en linea");
		lblTitulo.setBounds(450, 15, 900, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));	
		panel.add(lblTitulo);
		
		
		JTextArea ayuda = new JTextArea(TextoAyuda.TEXTO);
		ayuda.setEditable(false);
		Font labelFontAyuda = lblTitulo.getFont();
		ayuda.setFont(new Font(labelFontAyuda.getName(), Font.PLAIN, 15));
		
		JScrollPane scroll = new JScrollPane(ayuda);
		scroll.setBounds(10, 65, 1345, 600);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEjecutarAssembly){
			//
			//Ejecutar ASSEMBLY
			//
			tabbedPane.setSelectedIndex(2);
			String path = lblRutaAssembly.getText();
			String filename = txtNombreAssembly.getText();
			guardarText(txtCodigoAssembly.getText(),path, filename,".asm");
			try {
				if (lineByLineAssembly.isSelected()){
					CompilarYEjecutarPasoAPaso.compilarYEjecutar(path + "/" + filename +".asm", modelosTablas, this);
					btnPasoAPaso.setEnabled(true);
				}else{
					CompilarYEjecutar.compilarYEjecutar(path + "/" + filename +".asm", modelosTablas, this);
				}
				txtCodigoAbsoluto.setText(abrirTxt(path + "/" + filename + ".maq"));
				lblRutaAbsoluto.setText(lblRutaAssembly.getText());
				txtNombreAbsoluto.setText(filename);
				JOptionPane.showMessageDialog(null,Modelo.getModelo().getCpu().resultado);
			} catch (ExtensionInvalidaException
					| ProgramaMuyLargoException | ProgramaYaCompiladoException
					| InstruccionAssemblyInvalidaException
					| ProgramaMalFormadoException | IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}else if (e.getSource() == btnRutaAssembly){
			//
			//RUTA ASSEMBLY
			//
			int resultado = dialogAssembly.showOpenDialog(new JPanel());
			if  (resultado == JFileChooser.APPROVE_OPTION) btnEjecutarAssembly.setEnabled(true);
			lblRutaAssembly.setText(dialogAssembly.getSelectedFile().getAbsolutePath());
			btnGuardarAssembly.setEnabled(true);
		}else if (e.getSource() == btnGuardarAssembly){
			//
			//GUARDAR ASSEMBLY
			//
			guardarText(txtCodigoAssembly.getText(),lblRutaAssembly.getText(),txtNombreAssembly.getText(),".asm");			
		    JOptionPane.showMessageDialog(null,"Archivo assembly guardado correctamente");
		}else if (e.getSource() == btnEjecutarAbsoluto){
			//
			//EJECUTAR ABSOLUTO
			//
			tabbedPane.setSelectedIndex(2);
			try {
				String path = lblRutaAbsoluto.getText();
				String filename = txtNombreAbsoluto.getText();
				guardarText(txtCodigoAbsoluto.getText(),path, filename,".maq");
				if (lineByLineAbsoluto.isSelected()){
					EjecutarPasoAPasoController.ejecutar(path + "/" + filename +".maq", modelosTablas, this);
					btnPasoAPaso.setEnabled(true);
				}else{
					EjecutarContoller.ejecutar(path + "/" + filename +".maq", modelosTablas, null);
				}
				JOptionPane.showMessageDialog(null,Modelo.getModelo().getCpu().resultado);
			} catch (FileNotFoundException | ProgramaMalFormadoException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			notificarCambiosTablas();
		}else if (e.getSource() == btnRutaAbsoluto){
			//
			//RUTA ABSOLUTO
			//
			int resultado = dialogAbsoluto.showOpenDialog(new JPanel());
			if  (resultado == JFileChooser.APPROVE_OPTION) btnEjecutarAbsoluto.setEnabled(true);
			lblRutaAbsoluto.setText(dialogAbsoluto.getSelectedFile().getAbsolutePath());
		}else if (e.getSource() == btnAbrirAssembly){
			//
			//ABRIR ASSEMBLY
			//
			String pathCompleto = obtenerRuta(frame, "asm", "Codigo assembly");
			String filename = pathCompleto.substring(pathCompleto.lastIndexOf("/") + 1);
			filename = filename.substring(0,filename.lastIndexOf("."));
			String directorio = pathCompleto.substring(0, pathCompleto.lastIndexOf("/") + 1);
			lblRutaAssembly.setText(directorio);
			txtNombreAssembly.setText(filename);
			btnEjecutarAssembly.setEnabled(true);
			try {
				txtCodigoAssembly.setText(abrirTxt(pathCompleto));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == btnAbrirAbsoluto){
			//
			//ABRIR ABSOLUTO
			//
			String pathCompleto = obtenerRuta(frame,"maq", "Codigo de maquina");
			String filename = pathCompleto.substring(pathCompleto.lastIndexOf("/") + 1);
			filename = filename.substring(0,filename.lastIndexOf("."));
			String directorio = pathCompleto.substring(0, pathCompleto.lastIndexOf("/") + 1);
			lblRutaAbsoluto.setText(directorio);
			txtNombreAbsoluto.setText(filename);
			btnEjecutarAbsoluto.setEnabled(true);
			try {
				txtCodigoAbsoluto.setText(abrirTxt(pathCompleto));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == btnGuardarAbsoluto){
			//
			//GUARDAR ABSOLUTO
			//
			guardarText(txtCodigoAbsoluto.getText(),lblRutaAbsoluto.getText(),txtNombreAbsoluto.getText(),".maq");			
		    JOptionPane.showMessageDialog(null,"Archivo absoluto guardado correctamente");
		}else if (e.getSource() == btnConvertirDecimal){
		}else if (e.getSource() == btnConvertirA2){
		}else if (e.getSource() == btnConvertirHexa){
		}else if (e.getSource() == btnPasoAPaso){
			//
			//PASO A PASO
			//
			ejecutarPasoAPaso();
		}
	}
	private void notificarCambiosTablas(){
		registryTableModel.fireTableDataChanged();
		memoryTableModel.fireTableDataChanged();
		pipelineTableModel.fireTableDataChanged();
		pcTableModel.fireTableDataChanged();
	}
	private void ejecutarPasoAPaso(){
		EjecutarPasoAPasoController.avanzarPaso(modelosTablas, this);
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
		JOptionPane.showMessageDialog(null,"Ingresar valor por consola");
		// TODO
		Scanner ins = new Scanner(System.in);
        String respuesta = ins.nextLine();
        return Integer.parseInt(respuesta,10);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
