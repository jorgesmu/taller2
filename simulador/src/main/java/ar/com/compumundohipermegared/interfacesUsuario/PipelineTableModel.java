package ar.com.compumundohipermegared.interfacesUsuario;

import javax.swing.table.AbstractTableModel;

public class PipelineTableModel extends AbstractTableModel{
    
    //Two arrays used for the table data
    String[] columnNames = {"1"};
    
    Object[][] data = {
    	{"Basura"},{"Basura"},{"Basura"}};

    
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
    
    public void setValueAt(String value, int number)
    {        
        data[number][0] = value;
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
    	return false;
    }
    

}       