package de.ortimnetz.lazylinuxadmin.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import de.ortimnetz.lazylinuxadmin.model.MyTableModel;

public class MyCellRenderer extends DefaultTableCellRenderer{
	
	private MyTableModel dm;


	public MyCellRenderer(MyTableModel dm) {
		this.dm=dm;
		setOpaque(true);
	}

	
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		Component cell = super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
		
		
		String cellValue = (String)dm.getValueAt(arg4, arg5) ;
//		if (dm.getValueAt(arg4, arg5) instanceof JTextField) {
//			System.out.println("Textfeld");
//		}

		
		if (dm.getValueAt(arg4, arg5) instanceof String) {
			
			if (cellValue.equals("false") || cellValue.equals("") || (cellValue.startsWith("/dev") ? checkNumber(cellValue) : false)) {
				cell.setBackground(Color.RED);
			} else {
				cell.setBackground(Color.WHITE);
			}
		}
		
		

		
		
		
		return cell;
	}
	
	private boolean checkNumber(String cellValue){
		boolean isRed = false;
		String[] cellValueArray = cellValue.split(" ");
		
		for(int i = 1; i < cellValueArray.length; i++){
			if((i%2)!=0){
				String s_prozenz = cellValueArray[i].substring(0, cellValueArray[i].indexOf("%"));
				int prozent = Integer.valueOf(s_prozenz);
				
				if(prozent >= 90){
					isRed = true;
				}
				
			}
		}
		
		
		
		
		
		return isRed;
	}
	
}
