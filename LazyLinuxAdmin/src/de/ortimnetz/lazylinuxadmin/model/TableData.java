package de.ortimnetz.lazylinuxadmin.model;

import java.util.ArrayList;
import java.util.Observable;

public class TableData extends Observable{
	
	private ArrayList<TableEntry> entries;
	
	public TableData(){
		entries = new ArrayList<>();
	}

	public void add(TableEntry tableEntry){
		entries.add(tableEntry);
		setChanged();
		notifyObservers();
	}
	
	public void remove(TableEntry tableEntry){
		entries.remove(tableEntry);
	}

	public ArrayList<TableEntry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<TableEntry> entries) {
		this.entries = entries;
	}

	public TableEntry getLastEntry() {
		
		return entries.get(entries.size()-1);
	}
	
	
	
}
