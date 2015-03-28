package de.ortimnetz.lazylinuxadmin.model;

import com.jcraft.jsch.Session;

public class Host {

	private String name;
	private Session session;
	
	public Host(String name){
		this.name = name;
		this.session = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
