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
	JButton btnEditorAssembler;
	JButton btnEditorAbsoluto;
	JButton btnCargarArchivo;
	JButton btnAyuda;
	JLabel lblMenu;

	public MenuVentana(){
	panel = new JPanel();
	panel.setLayout(null);

	lblMenu = new JLabel("Simulador");
	lblMenu.setBounds(80, 15, 150, 30);
	Font labelFont = lblMenu.getFont();
	lblMenu.setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
	
	panel.add(lblMenu);

	btnEditorAssembler = new JButton("Editor assembly");
	btnEditorAssembler.addActionListener(this);
	btnEditorAssembler.setBounds(0, 60, 300, 60);
	panel.add(btnEditorAssembler);
	
	btnEditorAbsoluto = new JButton("Editor codigo absoluto");
	btnEditorAbsoluto.addActionListener(this);
	btnEditorAbsoluto.setBounds(0, 120, 300, 60);
	panel.add(btnEditorAbsoluto);
	
	btnCargarArchivo = new JButton("Cargar programa");
	btnCargarArchivo.addActionListener(this);
	btnCargarArchivo.setBounds(0, 180, 300, 60);
	panel.add(btnCargarArchivo);
	
	btnAyuda = new JButton("Ayuda en linea");
	btnAyuda.addActionListener(this);
	btnAyuda.setBounds(0, 240, 300, 60);
	panel.add(btnAyuda);
	
	this.getContentPane().add(panel);

	}
	
	public void editorAssemblyHandler(){
	    EditorAssemblyVentana editor = new EditorAssemblyVentana();
	    editor.setBounds(0, 0, 1500, 800);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    editor.setVisible(true);
	    this.dispose();
	}
	
	public void editorCodigoAbsolutoHandler(){
	    EditorCodigoAbsolutoVentana editor = new EditorCodigoAbsolutoVentana();
	    editor.setBounds(0, 0, 1500, 800);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    editor.setVisible(true);
	    this.dispose();
	}

	public void editorAyudaEnLineaHandler(){
	    AyudaEnLineaVentana editor = new AyudaEnLineaVentana();
	    editor.setBounds(0, 0, 1500, 800);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    editor.setVisible(true);
	    this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnEditorAssembler ) {
			editorAssemblyHandler();
		}else if ( e.getSource() == btnEditorAbsoluto){
			editorCodigoAbsolutoHandler();
		}else if ( e.getSource() == btnAyuda){
			editorAyudaEnLineaHandler();
		}
	}

}
