package de.ortimnetz.lazylinuxadmin.model;

import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
	private String[] columNames;
	
	public MyTableModel() {
		super(0, 7);
		
		columNames = new String[7];
		columNames[0]="Hostname";
		columNames[1]="apt-get update";
		columNames[2]="apt-get -y upgrade";
		columNames[3]="apt-get -y dist-upgrade";
		columNames[4]="apt-get -y autoremove";
		columNames[5]="df -h";
		columNames[6]="reboot";
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
 		rowData.add(new JTextField(String.valueOf(tableEntry.isAutoremove())));
 		rowData.add(new JTextField(tableEntry.getDfH()));
 		rowData.add(new JTextField(tableEntry.getReboot()));
 		addRow(rowData);
 		
 	}
 	
 	@Override
 	public boolean isCellEditable(int row, int column) {
 		return false;
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
