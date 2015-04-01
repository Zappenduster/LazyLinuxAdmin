package de.ortimnetz.lazylinuxadmin.command;

import com.jcraft.jsch.Session;

public class CommandUpgrade extends Command{


	public CommandUpgrade(Session session) {
		super.session = session;
		super.command =  "apt-get -y upgrade";

	}


	
	
}
