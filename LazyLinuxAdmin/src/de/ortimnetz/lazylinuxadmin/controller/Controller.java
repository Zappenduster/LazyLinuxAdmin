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
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import de.ortimnetz.lazylinuxadmin.command.Command;
import de.ortimnetz.lazylinuxadmin.command.CommandAutoRemove;
import de.ortimnetz.lazylinuxadmin.command.CommandDfH;
import de.ortimnetz.lazylinuxadmin.command.CommandDisconnect;
import de.ortimnetz.lazylinuxadmin.command.CommandDistUpgrade;
import de.ortimnetz.lazylinuxadmin.command.CommandReboot;
import de.ortimnetz.lazylinuxadmin.command.CommandUpdate;
import de.ortimnetz.lazylinuxadmin.command.CommandUpgrade;
import de.ortimnetz.lazylinuxadmin.model.Config;
import de.ortimnetz.lazylinuxadmin.model.Host;
import de.ortimnetz.lazylinuxadmin.model.TableData;
import de.ortimnetz.lazylinuxadmin.model.TableEntry;
import de.ortimnetz.lazylinuxadmin.view.Gui;



public class Controller extends Observable {

	private static Controller instance;
	private CopyOnWriteArrayList<Host> hosts;
	private Config config;
	private TableData tableData;
	private Gui mygui;
	private int progressMax;

		
	private Controller(){
		mygui = Gui.getInstance();
		
		
		hosts = new CopyOnWriteArrayList<Host>();
		loadConfig();
		tableData = new TableData();
		tableData.addObserver(Gui.getInstance());
		
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
				if(!host.getName().equals("")){
				System.out.println(host.getName() + ": Verbindung konnte nicht aufgebaut werden.");
				TableEntry tableEntry = new TableEntry(host.getName());
				tableData.add(tableEntry);
				}

				hosts.remove(host);
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
					
					TableEntry tableEntry = new TableEntry(host.getName());
					

					
					Command update = new CommandUpdate(session);
					commands.add(update);
					Command upgrade = new CommandUpgrade(session);
					commands.add(upgrade);
					Command distUpgrade = new CommandDistUpgrade(session);
					commands.add(distUpgrade);
					Command autoRemove = new CommandAutoRemove(session);
					commands.add(autoRemove);
					Command dfh = new CommandDfH(session);
					commands.add(dfh);
					Command reboot = new CommandReboot(session);
					commands.add(reboot);
					Command disconnect = new CommandDisconnect(session);
					commands.add(disconnect);
					System.out.println(hosts.size()*commands.size());
					setProgressMax(hosts.size()*commands.size());
					for(Command command : commands){
						command.execute();
						setChanged();
						notifyObservers();
						System.out.println("Notify");
						if(command.isSuccessful()){
							if(command instanceof CommandUpdate){
								tableEntry.setUpdate(true);
							} else if (command instanceof CommandUpgrade) {
								tableEntry.setUpgrade(true);
							} else if (command instanceof CommandDistUpgrade) {
								tableEntry.setDistUpgrade(true);
							} else if (command instanceof CommandDfH) {
								tableEntry.setDfH(((CommandDfH) command).getOutput());
							} else if (command instanceof CommandAutoRemove) {
								tableEntry.setAutoremove(true);
							} else if (command instanceof CommandReboot) {
								tableEntry.setReboot(((CommandReboot) command).getOutput());
							}
							
						}
							
						
					}
					tableData.add(tableEntry);
				}
			});
			
			thread.start();
			
		}
	}

	public void setProgressMax(int max){
		this.progressMax=max;
	}
	


	public TableData getTableData() {
		return tableData;
	}

	public int getProgressMax() {
		return progressMax;
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
