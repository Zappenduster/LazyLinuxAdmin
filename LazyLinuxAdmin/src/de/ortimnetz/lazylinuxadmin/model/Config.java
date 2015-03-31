package de.ortimnetz.lazylinuxadmin.model;

import java.io.Serializable;

public class Config implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String keyfile;
	private String keypass;
	private String pass;
	private String user;
	private String hostsfile;
	private int port;
	private static Config instance;
	
	private Config(){
		
		keyfile = "";
		keypass = "";
		pass = "";
		port = 22;
		
	}
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		
		return instance;
	}

	public String getKeyfile() {
		return keyfile;
	}

	public void setKeyfile(String keyfile) {
		this.keyfile = keyfile;
	}

	public String getKeypass() {
		return keypass;
	}

	public void setKeypass(String keypass) {
		this.keypass = keypass;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getHostsfile() {
		return hostsfile;
	}

	public void setHostsfile(String hostsfile) {
		this.hostsfile = hostsfile;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void setInstance(Config instance) {
		Config.instance = instance;
	}
	
	
	
}
