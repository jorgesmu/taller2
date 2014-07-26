package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class EditorCodigoAbsolutoPestana {
	public void inicializarVentanaAbsoluto(JPanel panel, final VentanaSimulador ventana) {
		JLabel lblTitulo;
		JScrollPane scrollCodigo;

		
	
		lblTitulo = new JLabel("Editor de código absoluto");
		lblTitulo.setBounds(300, 15, 600, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));
		
		panel.add(lblTitulo);
	
		ventana.btnAbrirAbsoluto = new JButton("Abrir");
		ventana.btnAbrirAbsoluto.addActionListener(ventana);
		ventana.btnAbrirAbsoluto.setBounds(25, 600, 300, 60);
		panel.add(ventana.btnAbrirAbsoluto);
	
		ventana.btnGuardarAbsoluto = new JButton("Guardar");
		ventana.btnGuardarAbsoluto.addActionListener(ventana);
		ventana.btnGuardarAbsoluto.setBounds(380, 600, 300, 60);
		panel.add(ventana.btnGuardarAbsoluto);
		
		ventana.btnEjecutarAbsoluto = new JButton("Ejecutar");
		ventana.btnEjecutarAbsoluto.addActionListener(ventana);
		ventana.btnEjecutarAbsoluto.setBounds(925, 600, 300, 60);
		ventana.btnEjecutarAbsoluto.setEnabled(false);;
		panel.add(ventana.btnEjecutarAbsoluto);
		
	
		ventana.codigoHintAbsoluto = "Ingrese su código absoluto aquí";
		ventana.txtCodigoAbsoluto = new JTextArea(ventana.codigoHintAbsoluto);
		ventana.txtCodigoAbsoluto.addMouseListener(ventana);
		scrollCodigo = new JScrollPane(ventana.txtCodigoAbsoluto);
		scrollCodigo.setBounds(25, 110, 1200, 470);
		scrollCodigo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollCodigo);
		
		ventana.dialogAbsoluto = new JFileChooser();
		ventana.dialogAbsoluto.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		ventana.lblRutaAbsoluto = new JLabel();
		ventana.lblRutaAbsoluto.setText("Ruta destino para archivos ejecutables");
		ventana.lblRutaAbsoluto.setBounds(25, 65, 1000, 20);
		panel.add(ventana.lblRutaAbsoluto);
		
		ventana.btnRutaAbsoluto = new JButton("Guardar en");
		ventana.btnRutaAbsoluto.addActionListener(ventana);
		ventana.btnRutaAbsoluto.setBounds(1010, 65, 215, 20);
		panel.add(ventana.btnRutaAbsoluto);
	
		ventana.nombreHintAbsoluto = "Nombre del archivo";
		ventana.txtNombreAbsoluto = new JTextField();
		ventana.txtNombreAbsoluto.setText(ventana.nombreHintAbsoluto);
		ventana.txtNombreAbsoluto.setBounds(22,87,1208,20);
		ventana.txtNombreAbsoluto.addMouseListener(ventana);
		panel.add(ventana.txtNombreAbsoluto);
	
		ventana.lineByLineAbsoluto = new JCheckBox("Línea por línea");
		ventana.lineByLineAbsoluto.setBounds(740,615,150,30);
		panel.add(ventana.lineByLineAbsoluto);
		
		/* Numeros Lineas */
		final JLabel lblLineaColReferencia= new JLabel();
		ventana.txtCodigoAbsoluto.addCaretListener( new CaretListener() {
		     public void caretUpdate( CaretEvent e ) {
		     int pos = e.getDot();
		        try {
		           int row = ventana.txtCodigoAbsoluto .getLineOfOffset(pos) + 1;
		           int col = pos - ventana.txtCodigoAbsoluto .getLineStartOffset( row - 1 ) + 1;
		           lblLineaColReferencia.setText("Línea: " + row + " Columna: " + col );
		       }
		       catch( BadLocationException exc ){ 
		           System.out.println(exc); 
		       }
		    } 
		  });
		
		lblLineaColReferencia.setBounds(1080, 570, 900, 40);
		panel.add(lblLineaColReferencia);
		
	}


}
