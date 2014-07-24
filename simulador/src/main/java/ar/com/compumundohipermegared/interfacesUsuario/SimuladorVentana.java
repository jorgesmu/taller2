package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class SimuladorVentana extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	JButton btnMenu;
	JButton btnConvertirDecimal;
	JButton btnConvertirA2;
	JButton btnConvertirHexa;
	JButton btnPasoAPaso;	
	JButton btnAyudaEnLinea;	
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
	public SimuladorVentana(){
		panel = new JPanel();
		panel.setLayout(null);
	
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
		

		tblMemoria = new JTable(new MemoryTableModel());
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
		
		tblRegistros = new JTable(new RegistryTableModel());
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
		
		tblPipeline = new JTable(new PipelineTableModel());
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
		
		tblProgramCounter = new JTable(new ProgramCounterTableModel());
		tblProgramCounter.setShowGrid(true);
		tblProgramCounter.setShowVerticalLines(true);
		tblProgramCounter.setGridColor(Color.BLUE);
		tblProgramCounter.setBounds(150,500,200,17);
		panel.add(tblProgramCounter);
		
		btnConvertirDecimal = new JButton("Visualizar en decimal");
		btnConvertirDecimal.addActionListener(this);
		btnConvertirDecimal.setBounds(500, 400, 200, 50);
		panel.add(btnConvertirDecimal);
		
		btnConvertirHexa = new JButton("Visualizar en hexadecimal");
		btnConvertirHexa.addActionListener(this);
		btnConvertirHexa.setBounds(500, 460, 200, 50);
		panel.add(btnConvertirHexa);
		
		btnConvertirA2 = new JButton("Visualizar en complemento");
		btnConvertirA2.addActionListener(this);
		btnConvertirA2.setBounds(500, 520, 200, 50);
		panel.add(btnConvertirA2);
		
		btnPasoAPaso = new JButton("Proximo paso");
		btnPasoAPaso.setEnabled(false);
		btnPasoAPaso.addActionListener(this);
		btnPasoAPaso.setBounds(930, 400, 200, 50);
		panel.add(btnPasoAPaso);
		
		btnMenu = new JButton("Menu Ppal");
		btnMenu.addActionListener(this);
		btnMenu.setBounds(930, 460, 200, 50);
		panel.add(btnMenu);
		
		btnAyudaEnLinea = new JButton("Ayuda en linea");
		btnAyudaEnLinea.addActionListener(this);
		btnAyudaEnLinea.setBounds(930, 520, 200, 50);
		panel.add(btnAyudaEnLinea);
		
		this.getContentPane().add(panel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu){
		    MenuVentana menu = new MenuVentana();
	        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        menu.setBounds(0, 0, 300, 325);
	        menu.setVisible(true);
			this.dispose();		
		}
	}
    

}
