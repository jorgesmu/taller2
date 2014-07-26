package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class AyudaEnLineaPestana {
	
		
	public void inicializarVentanaAyuda(JPanel panel) {
		JLabel lblTitulo;
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
	

}
