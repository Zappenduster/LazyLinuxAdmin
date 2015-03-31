package de.ortimnetz.lazylinuxadmin.command;


import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;


public abstract class Command implements ICommand {
	
	private int exitcode;
	private boolean successful;
	protected Session session;
	protected String command;
	
	public Command(){
		successful = false;
		exitcode = -1;
	}
	
	@Override
	public void execute() {
	
		try {
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			channel.connect();
			
			boolean isOver = false;
			while(!isOver){
				if(channel.isEOF() && channel.getExitStatus() != -1){
					isOver = true;
				} 
				
				Thread.sleep(3000);
				
			}	
						
			channel.disconnect();
			setExitcode(channel.getExitStatus());
			System.out.println(session.getHost() + " " + command + ": " + channel.getExitStatus());
		} catch (Exception e) {
			
		}
	}
		
	

	public int getExitcode() {
		return exitcode;
	}

	public void setExitcode(int exitcode) {
		this.exitcode = exitcode;
	}

	public boolean isSuccessful() {
		if(exitcode == 0){
			successful = true;
		}
		
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	
	
}
