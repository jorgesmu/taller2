package ar.com.compumundohipermegared.interfacesUsuario;

import javax.swing.table.AbstractTableModel;


public class ProgramCounterTableModel extends AbstractTableModel{
    
    //Two arrays used for the table data
    String[] columnNames = {"1"};
    
    Object[][] data = {
    	{"Basura"}};

    
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
    
    public void setValueAt(String value)
    {        
        data[0][0] = value;
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