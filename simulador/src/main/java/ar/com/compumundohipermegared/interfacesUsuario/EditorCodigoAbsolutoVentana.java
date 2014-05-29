package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditorCodigoAbsolutoVentana extends JFrame implements ActionListener, MouseListener{
	JPanel panel;
	JButton btnMenu;
	JButton btnEjecutar;
	JLabel lblTitulo;
	JTextArea txtCodigo;
	JFileChooser dialog;
	JLabel lblRuta;
	JButton btnRuta;
	JTextField txtNombre;
	String codigoHint;
	String nombreHint;
	public EditorCodigoAbsolutoVentana(){
	panel = new JPanel();
	panel.setLayout(null);

	lblTitulo = new JLabel("Editor de codigo absoluto");
	lblTitulo.setBounds(400, 15, 600, 40);
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
	btnEjecutar.setEnabled(false);;
	panel.add(btnEjecutar);
	

	codigoHint = "Ingrese su codigo assembler aqui";
	txtCodigo = new JTextArea(codigoHint);
	txtCodigo.setBounds(25, 110, 1200, 550);
	txtCodigo.addMouseListener(this);
	panel.add(txtCodigo);
	
	dialog = new JFileChooser();
	dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	
	lblRuta = new JLabel();
	lblRuta.setText("Ruta destino para archivos ejecutables");
	lblRuta.setBounds(25, 65, 1000, 20);
	panel.add(lblRuta);
	
	btnRuta = new JButton("Explorar");
	btnRuta.addActionListener(this);
	btnRuta.setBounds(1010, 65, 215, 20);
	panel.add(btnRuta);

	nombreHint = "Nombre del archivo (Modificar)";
	txtNombre = new JTextField();
	txtNombre.setText(nombreHint);
	txtNombre.setBounds(20,87,1208,20);
	txtNombre.addMouseListener(this);
	panel.add(txtNombre);
	
	this.getContentPane().add(panel);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu){
		    MenuVentana menu = new MenuVentana();
	        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        menu.setBounds(0, 0, 300, 300);
	        menu.setVisible(true);
			this.dispose();		
		}else if (e.getSource() == btnEjecutar){
			JOptionPane.showMessageDialog(null,"compilar");
		}else if (e.getSource() == btnRuta){
			int resultado = dialog.showOpenDialog(panel);
			if  (resultado == JFileChooser.APPROVE_OPTION) btnEjecutar.setEnabled(true);
			lblRuta.setText(dialog.getSelectedFile().getAbsolutePath());
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == txtCodigo){
			if (txtCodigo.getText().equals(codigoHint)) txtCodigo.setText("");
		}else if (e.getSource() == txtNombre){
			if (txtNombre.getText().equals(nombreHint)) txtNombre.setText("");
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
