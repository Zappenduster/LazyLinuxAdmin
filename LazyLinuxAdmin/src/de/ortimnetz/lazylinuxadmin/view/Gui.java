package de.ortimnetz.lazylinuxadmin.view;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Gui extends JFrame {
	
	private static Gui instance;
	private JMenuBar menubar;
	private JMenu menuFile;
	private JMenu menuHelp;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemConfig;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemVersion;
	
	
	private Gui(){
		
		menubar = new JMenuBar();
		menuFile = new JMenu("Datei");
		menuHelp = new JMenu("?");
		menuItemStart = new JMenuItem("Start");
		menuItemConfig = new JMenuItem("Konfiguration");
		menuItemExit = new JMenuItem("Beenden");
		menuItemVersion = new JMenuItem("Version");
		

		
		menubar.add(menuFile);
		menubar.add(menuHelp);
		menuFile.add(menuItemStart);
		menuFile.add(menuItemConfig);
		menuFile.add(menuItemExit);
		menuHelp.add(menuItemVersion);
		

		

		this.setJMenuBar(menubar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setTitle("Lazy Linux Admin - Server Verwaltungstool");
		this.setMinimumSize(new Dimension(800, 600));
		this.setVisible(true);

		
		// ActionListener
		menuItemStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		menuItemConfig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				GuiConfig config = new GuiConfig();
				
			}
		});
		
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
	}
	
	public static Gui getInstance(){
		if(instance == null){
			instance = new Gui();
		}
		
		
		return instance;
	}


	
}
