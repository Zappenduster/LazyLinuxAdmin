package de.ortimnetz.lazylinuxadmin.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;








import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import de.ortimnetz.lazylinuxadmin.controller.Controller;
import de.ortimnetz.lazylinuxadmin.model.MyTableModel;


public class Gui extends JFrame {
	
	private static Gui instance;
	private BorderLayout borderlayout;
	private JScrollPane tablepanel;
	private JMenuBar menubar;
	private JMenu menuFile;
	private JMenu menuHelp;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemConfig;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemVersion;
	private JTable table;
	private MyTableModel dm;
	
	private Gui(){
		borderlayout = new BorderLayout();
	

		
		menubar = new JMenuBar();
		menuFile = new JMenu("File");
		menuHelp = new JMenu("Help");
		menuItemStart = new JMenuItem("Start");
		menuItemConfig = new JMenuItem("Configuration");
		menuItemExit = new JMenuItem("Exit");
		menuItemVersion = new JMenuItem("Version");
		dm=new MyTableModel();
		
		table = new JTable(dm);
	
		tablepanel = new JScrollPane(table);
		this.add(tablepanel);
		
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

	public void createTable(int numRows, int numColumns){
		JTable table = new JTable(numRows, numColumns);
		this.add(table);
		this.validate();
		this.repaint();
	}



	
}
