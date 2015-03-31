package de.ortimnetz.lazylinuxadmin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import de.ortimnetz.lazylinuxadmin.command.Command;
import de.ortimnetz.lazylinuxadmin.command.CommandDistUpgrade;
import de.ortimnetz.lazylinuxadmin.command.CommandUpdate;
import de.ortimnetz.lazylinuxadmin.command.CommandUpgrade;
import de.ortimnetz.lazylinuxadmin.model.Config;
import de.ortimnetz.lazylinuxadmin.model.Host;
import de.ortimnetz.lazylinuxadmin.view.Gui;



public class Controller {

	private static Controller instance;
	private CopyOnWriteArrayList<Host> hosts;
	private Config config;

		
	private Controller(){
		Gui.getInstance();
		hosts = new CopyOnWriteArrayList<Host>();
		loadConfig();
		
	}
	
	private void createHosts() {
				
		try {
			ArrayList<String> hostnames = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(config.getHostsfile()));
			String line;
			
			
			while ((line = br.readLine()) != null){
				hostnames.add(line);
			}
			br.close();
			
			
			
			for(String hostname : hostnames){
				
				Host host = new Host(hostname);
				hosts.add(host);
			
			}
			

		} catch (FileNotFoundException e) {
			System.out.println("Datei wurde nicht gefunden.");
		} catch (IOException e) {
			System.out.println("Zugriff fehlerhaft.");
		}

	}
	
	private void createSessions() {

		for(Host host:hosts){
			JSch jsch = new JSch();
			try {
				jsch.addIdentity(config.getKeyfile(), config.getKeypass());
				Session session = jsch.getSession(config.getUser(), host.getName(), config.getPort());
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();
				
				host.setSession(session);

			} catch (JSchException e) {
				System.out.println(host.getName() + ": Verbindung konnte nicht aufgebaut werden.");
			}

		}	
	}
	
	
	public void start(){
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					config = Config.getInstance();
					createHosts();
					createSessions();

					startCommands();
				} catch (NullPointerException e) {
					System.out.println("Configuration is not valid.");
				}
				

				
			}
		});
		
		thread.start();
		

	}
	
	
	
	
	
	
	
	
	
	public static Controller getInstance(){
		if(instance == null){
			instance = new Controller();
		}
		
		return instance;
	}

	

	public CopyOnWriteArrayList<Host> getHosts() {
		return hosts;
	}

	public void setHosts(CopyOnWriteArrayList<Host> hosts) {
		this.hosts = hosts;
	}


	public void saveConfig(){
		try {
			FileOutputStream fos = new FileOutputStream("config.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Config.getInstance());
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Configuration cannot be saved.");
		}
		
	}
	
	public void loadConfig(){
		
		File file = new File("config.ser");
		
		if(file.exists()){
			FileInputStream fis;
			try {
				fis = new FileInputStream("config.ser");		
				ObjectInputStream ois = new ObjectInputStream(fis);
				Config.setInstance((Config) ois.readObject());
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				System.out.println("Config error.");
			} catch (IOException e) {
				System.out.println("Config error.");
			} catch (ClassNotFoundException e) {
				System.out.println("Config error.");
			}
		} else {
			this.config = Config.getInstance();
		}
		

	}
	
	private void startCommands(){
		for(final Host host : hosts){
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					ArrayList<Command> commands = host.getCommands();
					Session session = host.getSession();
					
					Command update = new CommandUpdate(session);
					commands.add(update);
					Command upgrade = new CommandUpgrade(session);
					commands.add(upgrade);
					Command distUpgrade = new CommandDistUpgrade(session);
					commands.add(distUpgrade);
					
					for(Command command : commands){
						command.execute();
						if(!command.isSuccessful()){
							System.out.println("Error");
						}
					}
					
				}
			});
			
			thread.start();
			
		}
	}
	
//	private void paintTable(){
//		
//		int numRows = hosts.size();
//		int numColumns = commands.size();
//		Gui.getInstance().createTable(numRows, numColumns);
//
//		
//	}
	
	
}
