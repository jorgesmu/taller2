package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuVentana extends JFrame implements ActionListener{
	JPanel panel;
	JButton btnEditor;
	JLabel lblMenu;

	public MenuVentana(){
	panel = new JPanel();
	panel.setLayout(null);

	lblMenu = new JLabel("Simulador");
	lblMenu.setBounds(80, 15, 150, 30);
	Font labelFont = lblMenu.getFont();
	lblMenu.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
	
	panel.add(lblMenu);

	btnEditor = new JButton("Editor assembly");
	btnEditor.addActionListener(this);
	btnEditor.setBounds(0, 60, 300, 60);
	panel.add(btnEditor);

	this.getContentPane().add(panel);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnEditor ) {
			JOptionPane.showMessageDialog(null,"Boton1");
		}
	}

}
