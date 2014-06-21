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

public class EditorCodigoAbsolutoVentana extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JButton btnMenu;
	JButton btnEjecutar;
	JButton btnGuardar;
	JLabel lblTitulo;
	JTextArea txtCodigo;
	JScrollPane scrollCodigo;
	JFileChooser dialog;
	JLabel lblRuta;
	JButton btnRuta;
	JTextField txtNombre;
	String codigoHint;
	String nombreHint;
	JCheckBox lineByLine;
	
	public EditorCodigoAbsolutoVentana() {
		panel = new JPanel();
		panel.setLayout(null);
	
		lblTitulo = new JLabel("Editor de código absoluto");
		lblTitulo.setBounds(400, 15, 600, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));
		
		panel.add(lblTitulo);
	
		btnMenu = new JButton("Menu Ppal");
		btnMenu.addActionListener(this);
		btnMenu.setBounds(25, 680, 300, 60);
		panel.add(btnMenu);
	
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(350, 680, 300, 60);
		panel.add(btnGuardar);
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(this);
		btnEjecutar.setBounds(925, 680, 300, 60);
		btnEjecutar.setEnabled(false);;
		panel.add(btnEjecutar);
		
	
		codigoHint = "Ingrese su código absoluto aquí";
		txtCodigo = new JTextArea(codigoHint);
		txtCodigo.addMouseListener(this);
		scrollCodigo = new JScrollPane(txtCodigo);
		scrollCodigo.setBounds(25, 110, 1200, 550);
		scrollCodigo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollCodigo);
		
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
	
		lineByLine = new JCheckBox("Línea por línea");
		lineByLine.setBounds(800,695,200,30);
		panel.add(lineByLine);
		
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
		}else if (e.getSource() == btnEjecutar){
		    SimuladorVentana simulador = new SimuladorVentana();
		    simulador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    simulador.setBounds(0, 0, 1500, 800);
		    simulador.setVisible(true);
			this.dispose();	
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
