package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditorAssemblyVentana extends JFrame implements ActionListener{
	JPanel panel;
	JButton btnMenu;
	JButton btnEjecutar;
	JLabel lblTitulo;
	JTextArea txtCodigo;
	
	public EditorAssemblyVentana(){
	panel = new JPanel();
	panel.setLayout(null);

	lblTitulo = new JLabel("Editor Assembly");
	lblTitulo.setBounds(450, 15, 400, 40);
	Font labelFont = lblTitulo.getFont();
	lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));
	
	panel.add(lblTitulo);

	btnMenu = new JButton("Menu Ppal");
	btnMenu.addActionListener(this);
	btnMenu.setBounds(25, 680, 300, 60);
	panel.add(btnMenu);

	btnEjecutar = new JButton("Compilar y Ejecutar");
	btnEjecutar.addActionListener(this);
	btnEjecutar.setBounds(925, 680, 300, 60);
	panel.add(btnEjecutar);

	txtCodigo = new JTextArea("Ingrese su codigo assembler aqui");
	txtCodigo.setBounds(25, 70, 1200, 600);
	panel.add(txtCodigo);
	
	this.getContentPane().add(panel);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu){
		    MenuVentana menu = new MenuVentana();
	        menu.setBounds(0, 0, 300, 300);
	        menu.setVisible(true);
			this.dispose();
		}else if (e.getSource() == btnEjecutar){
			JOptionPane.showMessageDialog(null,"compilar");
			this.dispose();
		}
		
	}

}
