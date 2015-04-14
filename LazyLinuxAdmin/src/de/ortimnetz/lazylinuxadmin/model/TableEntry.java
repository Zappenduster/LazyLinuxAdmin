package de.ortimnetz.lazylinuxadmin.model;

public class TableEntry {

	private String hostname;
	private boolean update;
	private boolean upgrade;
	private boolean distUpgrade;
	
	public TableEntry(String hostname){
		this.hostname = hostname;
		update = false;
		upgrade = false;
		distUpgrade = false;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isUpgrade() {
		return upgrade;
	}

	public void setUpgrade(boolean upgrade) {
		this.upgrade = upgrade;
	}

	public boolean isDistUpgrade() {
		return distUpgrade;
	}

	public void setDistUpgrade(boolean distUpgrade) {
		this.distUpgrade = distUpgrade;
	}
	
	
	
	
}
