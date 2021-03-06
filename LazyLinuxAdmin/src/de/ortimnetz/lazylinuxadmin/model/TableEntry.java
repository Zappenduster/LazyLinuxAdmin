package de.ortimnetz.lazylinuxadmin.model;

public class TableEntry {

	private String hostname;
	private boolean update;
	private boolean upgrade;
	private boolean distUpgrade;
	private boolean autoremove;
	private String dfH;
	private String reboot;
	
	public TableEntry(String hostname){
		this.hostname = hostname;
		update = false;
		upgrade = false;
		distUpgrade = false;
		dfH = "";
		autoremove = false;
		reboot = "";
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

	public String getDfH() {
		return dfH;
	}

	public void setDfH(String dfH) {
		this.dfH = dfH;
	}

	public boolean isAutoremove() {
		return autoremove;
	}

	public void setAutoremove(boolean autoremove) {
		this.autoremove = autoremove;
	}

	public String getReboot() {
		return reboot;
	}

	public void setReboot(String reboot) {
		this.reboot = reboot;
	}

	
	
	
	
	
}
