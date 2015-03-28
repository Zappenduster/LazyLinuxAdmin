package de.ortimnetz.lazylinuxadmin.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import de.ortimnetz.lazylinuxadmin.model.Config;
import de.ortimnetz.lazylinuxadmin.model.Host;
import de.ortimnetz.lazylinuxadmin.view.Gui;



public class Controller {

	private static Controller instance;
	private CopyOnWriteArrayList<Host> hosts;
	private Gui gui;
	private Config config;
	
		
	private Controller(){
		gui = Gui.getInstance();
		config = Config.getInstance();
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


	
	
}