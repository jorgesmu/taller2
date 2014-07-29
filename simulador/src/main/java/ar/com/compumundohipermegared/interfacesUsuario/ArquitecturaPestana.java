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
import javax.swing.border.MatteBorder;


public class ArquitecturaPestana {
	public void inicializarVentanaArquitectura(JPanel panel, VentanaSimulador ventana) {	
		JLabel lblReferencias;
		JLabel lblTitulo;
		JLabel lblRegistros;
		JLabel lblMemoria;
		JLabel lblPipeline;
		JTable tblPipeline;
		JLabel lblProgramCounter;
		JTable tblProgramCounter;
		JTable tblMemoria;
		JTable tblRegistros;
		JLabel lblColumnaMemoria;
		JLabel lblFilasMemoria;
		MatteBorder border = new MatteBorder(1, 1, 1, 1, Color.BLUE);
		String hexaVerticalReference ="<html>0<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9<br>A<br>"
				+ "B<br>C<br>D<br>E<br>F</html>";
		String pipelineReference ="<html>1<br>2<br>3</html>";
		JLabel lblPipelineReference;
		JLabel lblregistryReference;
		
		lblTitulo = new JLabel("Simulador");
		lblTitulo.setBounds(450, 15, 900, 50);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));	
		panel.add(lblTitulo);
	
		lblMemoria = new JLabel("Memoria principal");
		lblMemoria.setBounds(25, 45, 400, 40);
		Font labelFontMemoria = lblTitulo.getFont();
		lblMemoria.setFont(new Font(labelFontMemoria.getName(), Font.PLAIN, 20));	
		panel.add(lblMemoria);
		
		ventana.memoryTableModel = new MemoryTableModel();
		tblMemoria = new JTable(ventana.memoryTableModel);
		//Basado en ejemplo: http://stackoverflow.com/questions/14563799/jtable-cellrenderer-changes-backgroundcolor-of-cells-while-running
		tblMemoria.getColumnModel().getColumn(12).setCellRenderer(new MemoriaTableRender());
		tblMemoria.getColumnModel().getColumn(13).setCellRenderer(new MemoriaTableRender());
		tblMemoria.getColumnModel().getColumn(14).setCellRenderer(new MemoriaTableRender());
		tblMemoria.getColumnModel().getColumn(15).setCellRenderer(new MemoriaTableRender());
		tblMemoria.setShowGrid(true);
		tblMemoria.setShowVerticalLines(true);
		tblMemoria.setBorder(border);
		tblMemoria.setGridColor(Color.BLUE);
		tblMemoria.setBounds(25,95,1100,255);
		tblMemoria.setFocusable(false);
		tblMemoria.setCellSelectionEnabled(false);
		panel.add(tblMemoria);
		
		lblRegistros = new JLabel("Registros");
		lblRegistros.setBounds(25, 370, 400, 40);
		Font labelFontRegistros = lblRegistros.getFont();
		lblRegistros.setFont(new Font(labelFontRegistros.getName(), Font.PLAIN, 20));	
		panel.add(lblRegistros);
		
		ventana.registryTableModel = new RegistryTableModel();
		tblRegistros = new JTable(ventana.registryTableModel);
		tblRegistros.setShowGrid(true);
		tblRegistros.setShowVerticalLines(true);
		tblRegistros.setBorder(border);
		tblRegistros.setGridColor(Color.BLUE);
		tblRegistros.setBounds(25,400,100,255);
		tblRegistros.setFocusable(false);
		tblRegistros.setCellSelectionEnabled(false);
		panel.add(tblRegistros);

		lblPipeline = new JLabel("Pipeline");
		lblPipeline.setBounds(150, 370, 400, 40);
		Font labelFontPipeline = lblPipeline.getFont();
		lblPipeline.setFont(new Font(labelFontPipeline.getName(), Font.PLAIN, 20));	
		panel.add(lblPipeline);
		
		ventana.pipelineTableModel = new PipelineTableModel();
		tblPipeline = new JTable(ventana.pipelineTableModel);
		tblPipeline.setShowGrid(true);
		tblPipeline.setShowVerticalLines(true);
		tblPipeline.setBorder(border);
		tblPipeline.setGridColor(Color.BLUE);
		tblPipeline.setBounds(150,400,200,48);
		tblPipeline.setFocusable(false);
		tblPipeline.setCellSelectionEnabled(false);
		panel.add(tblPipeline);
		
		lblProgramCounter = new JLabel("Contador del programa");
		lblProgramCounter.setBounds(150, 470, 400, 40);
		Font labelFontProgramCounter = lblProgramCounter.getFont();
		lblProgramCounter.setFont(new Font(labelFontProgramCounter.getName(), Font.PLAIN, 20));	
		panel.add(lblProgramCounter);
		
		ventana.pcTableModel = new ProgramCounterTableModel();
		tblProgramCounter = new JTable(ventana.pcTableModel);
		tblProgramCounter.setShowGrid(true);
		tblProgramCounter.setShowVerticalLines(true);
		tblProgramCounter.setBorder(border);
		tblProgramCounter.setGridColor(Color.BLUE);
		tblProgramCounter.setBounds(150,500,200,15);
		tblProgramCounter.setFocusable(false);
		tblProgramCounter.setCellSelectionEnabled(false);
		panel.add(tblProgramCounter);
		
		ventana.btnConvertirDecimal = new JButton("Visualizar en decimal");
		ventana.btnConvertirDecimal.addActionListener(ventana);
		ventana.btnConvertirDecimal.setEnabled(false);
		ventana.btnConvertirDecimal.setBounds(500, 400, 250, 50);
		panel.add(ventana.btnConvertirDecimal);
		
		ventana.btnConvertirHexa = new JButton("Visualizar en hexadecimal");
		ventana.btnConvertirHexa.addActionListener(ventana);
		ventana.btnConvertirHexa.setEnabled(false);
		ventana.btnConvertirHexa.setBounds(500, 460, 250, 50);
		panel.add(ventana.btnConvertirHexa);
		
		ventana.btnConvertirA2 = new JButton("Visualizar en complemento");
		ventana.btnConvertirA2.addActionListener(ventana);
		ventana.btnConvertirA2.setEnabled(false);
		ventana.btnConvertirA2.setBounds(500, 520, 250, 50);
		panel.add(ventana.btnConvertirA2);
		
		lblReferencias = new JLabel("Verde: entrada, Amarillo: salida.");
		lblReferencias.setBounds(920, 340, 900, 40);
		Font labelReferencias = lblReferencias.getFont();
		lblReferencias.setFont(new Font(labelReferencias.getName(), Font.PLAIN, 13));	
		panel.add(lblReferencias);

		lblregistryReference = new JLabel(hexaVerticalReference);
		lblregistryReference.setBounds(15, 376, 10, 300);
		Font labelReferenciasRegistry = lblregistryReference.getFont();
		lblregistryReference.setFont(new Font(labelReferenciasRegistry.getName(), Font.PLAIN, 13));	
		panel.add(lblregistryReference);
		
		lblPipelineReference = new JLabel(pipelineReference);
		lblPipelineReference.setBounds(140, 273, 10, 300);
		Font labelReferenciasPipeline = lblPipelineReference.getFont();
		lblPipelineReference.setFont(new Font(labelReferenciasPipeline.getName(), Font.PLAIN, 14));	
		panel.add(lblPipelineReference);
		
		lblFilasMemoria = new JLabel(hexaVerticalReference);
		lblFilasMemoria.setBounds(15, 72, 10, 300);
		Font labelFilas = lblFilasMemoria.getFont();
		lblFilasMemoria.setFont(new Font(labelFilas.getName(), Font.PLAIN, 13));	
		panel.add(lblFilasMemoria);
		
		lblColumnaMemoria = new JLabel("       0               1                2"
				+ "               3               4               5               6"
				+ "               7               8               9               A"
				+ "               B               C               D               E"
				+ "               F");
		lblColumnaMemoria.setBounds(25, 65, 1300, 40);
		Font labelCol = lblColumnaMemoria.getFont();
		lblColumnaMemoria.setFont(new Font(labelCol.getName(), Font.PLAIN, 13));	
		panel.add(lblColumnaMemoria);
		
		ventana.btnPasoAPaso = new JButton("Proximo paso");
		ventana.btnPasoAPaso.setEnabled(false);
		ventana.btnPasoAPaso.addActionListener(ventana);
		ventana.btnPasoAPaso.setBounds(930, 400, 200, 50);
		panel.add(ventana.btnPasoAPaso);
		
		ventana.modelosTablas = new ModelosInterfaz(ventana.memoryTableModel, ventana.pipelineTableModel, ventana.pcTableModel, ventana.registryTableModel);
			
	}

}
