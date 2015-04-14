package de.ortimnetz.lazylinuxadmin.model;

import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
	private String[] columNames;
	
	public MyTableModel() {
		super(0, 4);
		columNames = new String[4];
		columNames[0]="Hostname";
		columNames[1]="apt-get update";
		columNames[2]="apt-get -y upgrade";
		columNames[3]="apt-get -y dist-upgrade";
	}
	
@Override
public String getColumnName(int column) {
	return columNames[column];
}



 	public void addRow(TableEntry tableEntry){
 		Vector<JTextField> rowData = new Vector<>();
 		rowData.add(new JTextField(tableEntry.getHostname()));
 		rowData.add(new JTextField(String.valueOf(tableEntry.isUpdate())));
 		rowData.add(new JTextField(String.valueOf(tableEntry.isUpgrade())));
 		rowData.add(new JTextField(String.valueOf(tableEntry.isDistUpgrade())));

 		addRow(rowData);
 		
 	}
 	
 	
 	@Override
 	public Object getValueAt(int row, int column) {
 
 		if ( super.getValueAt(row, column) instanceof JTextField) {
 			JTextField tf = (JTextField) super.getValueAt(row, column) ;
 			return tf.getText();
 					
		}else{
			return super.getValueAt(row, column);
		}
 	}
}
