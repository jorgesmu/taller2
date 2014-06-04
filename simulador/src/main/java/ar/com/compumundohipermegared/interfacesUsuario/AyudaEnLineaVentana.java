package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AyudaEnLineaVentana extends JFrame implements ActionListener{
	JPanel panel;
	JButton btnMenu;
	JLabel lblTitulo;
	JLabel lblAyuda;
	public AyudaEnLineaVentana(){
		panel = new JPanel();
		panel.setLayout(null);
	
		lblTitulo = new JLabel("Ayuda en linea");
		lblTitulo.setBounds(450, 15, 900, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));	
		panel.add(lblTitulo);
	
		lblAyuda = new JLabel("Aqui va la ayuda en linea");
		lblAyuda.setBounds(10, 65, 900, 40);
		Font labelFontAyuda = lblTitulo.getFont();
		lblAyuda.setFont(new Font(labelFontAyuda.getName(), Font.PLAIN, 20));	
		panel.add(lblAyuda);
		
		btnMenu = new JButton("Menu Ppal");
		btnMenu.addActionListener(this);
		btnMenu.setBounds(25, 680, 300, 60);
		panel.add(btnMenu);

		
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
