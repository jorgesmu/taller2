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


public class AyudaEnLineaVentana extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		
		
		JTextArea ayuda = new JTextArea(TextoAyuda.TEXTO);
		ayuda.setEditable(false);
		Font labelFontAyuda = lblTitulo.getFont();
		ayuda.setFont(new Font(labelFontAyuda.getName(), Font.PLAIN, 15));
		
		JScrollPane scroll = new JScrollPane(ayuda);
		scroll.setBounds(10, 65, 1345, 600);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		btnMenu = new JButton("Menú Principal");
		btnMenu.addActionListener(this);
		btnMenu.setBounds(25, 680, 300, 60);
		panel.add(btnMenu);
		
		this.getContentPane().add(panel);
		this.setLocationRelativeTo (null);
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
