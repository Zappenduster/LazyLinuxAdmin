package de.ortimnetz.lazylinuxadmin.command;

import com.jcraft.jsch.Session;

public class CommandAutoRemove extends Command {

	public CommandAutoRemove(Session session) {
		super.session = session;
		super.command = "apt-get -y autoremove";
	}
	
}
