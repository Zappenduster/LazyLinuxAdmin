package de.ortimnetz.lazylinuxadmin.command;

import com.jcraft.jsch.Session;

public class CommandDisconnect extends Command{
	
	public CommandDisconnect(Session session) {
		super.session = session;
	}
	
	@Override
	public void execute() {
		if(super.session.isConnected()){
			super.session.disconnect();
			super.setExitcode(0);
			System.out.println(super.getSession().getHost() + ": Connection closed.");
		}
	}
}
