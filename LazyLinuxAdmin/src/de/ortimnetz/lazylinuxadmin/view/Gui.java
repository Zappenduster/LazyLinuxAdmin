package de.ortimnetz.lazylinuxadmin.view;


import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import de.ortimnetz.lazylinuxadmin.controller.Controller;


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
		menuFile = new JMenu("File");
		menuHelp = new JMenu("Help");
		menuItemStart = new JMenuItem("Start");
		menuItemConfig = new JMenuItem("Configuration");
		menuItemExit = new JMenuItem("Exit");
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
		this.setTitle("Lazy Linux Admin - Server Management Tool");
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
				
				Controller.getInstance().loadConfig();
				
				GuiConfig config = new GuiConfig();
				
			}
		});
		
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		menuItemStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().start();
				
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
