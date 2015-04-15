package de.ortimnetz.lazylinuxadmin.command;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class CommandReboot extends Command{

	private String output;
	
	public CommandReboot(Session session) {
		super.session = session;
		super.command = "cat /var/run/reboot-required";
		output = "Reboot not necessary";
	}
	
	@Override
	public void execute() {
		
		ChannelExec channel;
		try {
			channel = (ChannelExec) session.openChannel("exec");
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
			setSuccessful(true);
			
			if(channel.getExitStatus() == 0){
				ChannelExec channel2 = (ChannelExec) session.openChannel("exec");
				channel2.setCommand("echo reboot | at 23:15");
				channel2.connect();
				boolean isOver2 = false;
				while(!isOver2){
					if(channel.isEOF() && channel.getExitStatus() != -1){
						isOver2 = true;
					} 
					
					Thread.sleep(3000);
					
				}
				
				channel2.disconnect();
				output = "Reboot scheduled";
				
			}
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	
	
	
}
