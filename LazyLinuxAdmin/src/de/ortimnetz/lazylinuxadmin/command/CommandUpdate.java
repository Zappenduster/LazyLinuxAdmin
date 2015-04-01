package de.ortimnetz.lazylinuxadmin.command;

import com.jcraft.jsch.Session;

public class CommandUpdate extends Command{

	
	public CommandUpdate(Session session) {
		super.session = session;
		super.command = "apt-get update";
	}


	
}
