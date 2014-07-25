package ar.com.compumundohipermegared.interfacesUsuario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class MemoriaTableRender extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (row == 15){
        	if (column == 14 || column == 15){
        		cellComponent.setBackground(Color.YELLOW);
        	}else if (column == 12 || column == 13){
        		cellComponent.setBackground(Color.GREEN);        		
        	}
        }
        return cellComponent;
    }
}
