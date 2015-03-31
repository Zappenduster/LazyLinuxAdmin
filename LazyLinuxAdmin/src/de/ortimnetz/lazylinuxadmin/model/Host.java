package de.ortimnetz.lazylinuxadmin.model;

import java.util.ArrayList;

import com.jcraft.jsch.Session;

import de.ortimnetz.lazylinuxadmin.command.Command;

public class Host {

	private String name;
	private Session session;
	private ArrayList<Command> commands;
	
	public Host(String name){
		this.name = name;
		this.session = null;
		commands = new ArrayList<Command>();
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

	public ArrayList<Command> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<Command> commands) {
		this.commands = commands;
	}
	
	
	
}
