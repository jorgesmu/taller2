package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class SimuladorVentana extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	JButton btnMenu;
	JLabel lblTitulo;
	JLabel lblAyuda;
	JTable tblMemoria;
	JScrollPane contenedorTabla;
	public SimuladorVentana(){
		panel = new JPanel();
		panel.setLayout(null);
	
		lblTitulo = new JLabel("Simulador");
		lblTitulo.setBounds(450, 15, 900, 40);
		Font labelFont = lblTitulo.getFont();
		lblTitulo.setFont(new Font(labelFont.getName(), Font.PLAIN, 40));	
		panel.add(lblTitulo);
	
		lblAyuda = new JLabel("Memoria principal");
		lblAyuda.setBounds(10, 65, 400, 40);
		Font labelFontAyuda = lblTitulo.getFont();
		lblAyuda.setFont(new Font(labelFontAyuda.getName(), Font.PLAIN, 20));	
		panel.add(lblAyuda);
		
		btnMenu = new JButton("Menu Ppal");
		btnMenu.addActionListener(this);
		btnMenu.setBounds(25, 680, 300, 60);
		panel.add(btnMenu);


		tblMemoria = new JTable(new ExampleTableModel());
		tblMemoria.setShowGrid(true);
		tblMemoria.setShowVerticalLines(true);
		tblMemoria.setGridColor(Color.BLUE);
		tblMemoria.setBounds(25,95,600,300);
		panel.add(tblMemoria);
		
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
    class ExampleTableModel extends AbstractTableModel{
        
        //Two arrays used for the table data
        String[] columnNames = {"First Name", "Surname", "Country"
                        , "Event", "Place", "Time", "World Record" };
        
        Object[][] data = {
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"},	
        	{"Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura","Basura"}	

        };
        
        @Override
        public int getRowCount()
        {
            return data.length;
        }
        
        @Override
        public int getColumnCount()            
        {
            return columnNames.length;
        }
        
        @Override
        public Object getValueAt(int row, int column)
        {        
            return data[row][column];
        }
        
        //Used by the JTable object to set the column names
        public String getColumnName(int column) {
            return columnNames[column];
        }
        
        //Used by the JTable object to render different
        //functionality based on the data type
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
        @Override
        public boolean isCellEditable(int row, int column)
        {
           if (column == 0 || column == 1)
	   {
	        return false;
	   }
	   else
	   {
		return true;
	   }
        }
        
   
    }       

}
