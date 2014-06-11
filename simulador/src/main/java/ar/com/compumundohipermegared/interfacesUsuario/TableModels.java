package ar.com.compumundohipermegared.interfacesUsuario;

import javax.swing.table.AbstractTableModel;

class MemoryModel extends AbstractTableModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Two arrays used for the table data
    String[] columnNames = {"1", "2", "3", "4", "5", "6","7", "8",
    						"9", "10", "11", "12", "13", "14", "15", "16"};
    
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
class RegisterModel extends AbstractTableModel{
    
    //Two arrays used for the table data
    String[] columnNames = {"1"};
    
    Object[][] data = {
    	{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},
    	{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"},{"Basura"}
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
class PipelineModel extends AbstractTableModel{
    
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
class ProgramCounterModel extends AbstractTableModel{
    
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