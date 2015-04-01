package de.ortimnetz.lazylinuxadmin.command;

import com.jcraft.jsch.Session;

public class CommandDistUpgrade extends Command {

	
	public CommandDistUpgrade(Session session) {
		super.session = session;
		super.command = "apt-get -y dist-upgrade";
	}


	
}
