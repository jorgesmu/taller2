package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class EditorAssemblyPestana  {
	static void inicializarVentanaAssembly(JPanel panel, final VentanaPPal ventana){

		JLabel lblTitulo;
		JScrollPane scrollCodigo;


		lblTitulo = new JLabel("Editor de código Assembly");
		lblTitulo.setBounds(300, 15, 900, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));
		
		panel.add(lblTitulo);
	
		ventana.btnAbrirAssembly = new JButton("Abrir");
		ventana.btnAbrirAssembly.addActionListener(ventana);
		ventana.btnAbrirAssembly.setBounds(25, 600, 300, 60);
		panel.add(ventana.btnAbrirAssembly);
		
	
		ventana.btnGuardarAssembly = new JButton("Guardar");
		ventana.btnGuardarAssembly.addActionListener(ventana);
		ventana.btnGuardarAssembly.setBounds(380, 600, 300, 60);
		ventana.btnGuardarAssembly.setEnabled(false);;
		panel.add(ventana.btnGuardarAssembly);
	
		
		ventana.btnEjecutarAssembly = new JButton("Compilar y Ejecutar");
		ventana.btnEjecutarAssembly.addActionListener(ventana);
		ventana.btnEjecutarAssembly.setBounds(925, 600, 300, 60);
		ventana.btnEjecutarAssembly.setEnabled(false);
		panel.add(ventana.btnEjecutarAssembly);
		
	
		ventana.codigoHintAssembly = "Ingrese su código assembler aquí";
		ventana.txtCodigoAssembly = new JTextArea(ventana.codigoHintAssembly);
		ventana.txtCodigoAssembly.addMouseListener(ventana);
		scrollCodigo = new JScrollPane(ventana.txtCodigoAssembly);
		scrollCodigo.setBounds(25, 110, 1200, 470);
		scrollCodigo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollCodigo);
		
		ventana.dialogAssembly = new JFileChooser();
		ventana.dialogAssembly.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		ventana.lblRutaAssembly = new JLabel();
		ventana.lblRutaAssembly.setText("Ruta destino para archivos ejecutables");
		ventana.lblRutaAssembly.setBounds(25, 65, 1000, 20);
		panel.add(ventana.lblRutaAssembly);
		
		ventana.btnRutaAssembly = new JButton("Guardar en");
		ventana.btnRutaAssembly.addActionListener(ventana);
		ventana.btnRutaAssembly.setBounds(1010, 65, 215, 20);
		panel.add(ventana.btnRutaAssembly);
	
		ventana.nombreHintAssembly = "Nombre del archivo";
		ventana.txtNombreAssembly = new JTextField();
		ventana.txtNombreAssembly.setText(ventana.nombreHintAssembly);
		ventana.txtNombreAssembly.setBounds(22,87,1208,20);
		ventana.txtNombreAssembly.addMouseListener(ventana);
		panel.add(ventana.txtNombreAssembly);
		
		ventana.lineByLineAssembly = new JCheckBox("Línea por línea");
		ventana.lineByLineAssembly.setBounds(740,615,150,30);
		panel.add(ventana.lineByLineAssembly);
		/* Numeros Lineas */
		final JLabel lblLineColReferencia= new JLabel();
		ventana.txtCodigoAssembly.addCaretListener( new CaretListener() {
		     public void caretUpdate(CaretEvent e2) {
		     int pos = e2.getDot();
		        try {
		           int row = ventana.txtCodigoAssembly .getLineOfOffset(pos) + 1;
		           int col = pos - ventana.txtCodigoAssembly .getLineStartOffset(row - 1) + 1;
		           lblLineColReferencia.setText("Línea: " + row + " Columna: " + col );
		       }
		       catch( BadLocationException exc ){ 
		           System.out.println(exc); 
		       }
		    } 
		  });
		
		lblLineColReferencia.setBounds(1080, 570, 900, 40);
		panel.add(lblLineColReferencia);
	
		
	}

}
