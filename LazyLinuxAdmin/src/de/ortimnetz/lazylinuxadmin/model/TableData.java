package de.ortimnetz.lazylinuxadmin.model;

import java.util.ArrayList;

public class TableData {
	
	private ArrayList<TableEntry> entries;
	
	public TableData(){
		entries = new ArrayList<>();
	}

	public void add(TableEntry tableEntry){
		entries.add(tableEntry);
	}
	
	public void remove(TableEntry tableEntry){
		entries.remove(tableEntry);
	}
	
}
