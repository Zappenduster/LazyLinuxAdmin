package de.ortimnetz.lazylinuxadmin.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

public class CommandDfH extends Command {

	private ArrayList<String> inputLines;
	private String output;
	
	public CommandDfH(Session session) {
		super.session = session;
		super.command = "df -h";
		inputLines = new ArrayList<String>();
		output = "";
	}
	
	@Override
	public void execute() {

		try {
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			channel.setCommand(command);
			channel.connect();
			
			String msg = null;
			while((msg=in.readLine()) != null){
				inputLines.add(msg);
			}
			
			filterInput();
			channel.disconnect();
			in.close();
			setExitcode(channel.getExitStatus());
		} catch (Exception e) {
			
		}
	
	}
	
	public void filterInput(){
		for(String line : inputLines){
			if(line.startsWith("/dev/sd") || line.startsWith("/dev/vd") || line.startsWith("/dev/md")){
				// Split the line into several Strings, take the important info and merge into one giant String
				String[] split = line.split("\\s+");
				output += split[0] + ": " + split[4] + " ";
				
				Integer i = Integer.valueOf(split[4].replace("%", ""));
				System.out.println("i"+i);
				if(i<85){
					setSuccessful(true);
				}
			}
		}
		System.out.println(output);
		System.out.println("df -h erfolg? " + isSuccessful());
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	
}
