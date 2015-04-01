package de.ortimnetz.lazylinuxadmin.model;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
	private String[] columNames;
	
	public MyTableModel() {
		super(1, 4);
		columNames = new String[4];
		columNames[0]="Hostname";
		columNames[1]="apt-get update";
		columNames[2]="apt-get -y upgrade";
		columNames[3]="apt-get -y dist-upgrade";
	}
	
@Override
public String getColumnName(int column) {
	// TODO Auto-generated method stub
	return columNames[column];
}


 	public void addRow(TableEntry tableEntry){
 		Vector<String> rowData = new Vector<>();
 		rowData.add(tableEntry.getHostname());
 		rowData.add(String.valueOf(tableEntry.isUpdate()));
 		rowData.add(String.valueOf(tableEntry.isUpgrade()));
 		rowData.add(String.valueOf(tableEntry.isDistUpgrade()));
 		addRow(rowData);
 	}
}
