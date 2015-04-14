package de.ortimnetz.lazylinuxadmin.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import de.ortimnetz.lazylinuxadmin.controller.Controller;
import de.ortimnetz.lazylinuxadmin.model.MyTableModel;
import de.ortimnetz.lazylinuxadmin.model.TableData;
import de.ortimnetz.lazylinuxadmin.renderer.MyCellRenderer;


public class Gui extends JFrame implements Observer{
	
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
	private MyCellRenderer cellRenderer;
	

	
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
		cellRenderer = new MyCellRenderer(dm);
		table = new JTable(dm);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
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
		
		menuItemVersion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().addEntryTest();
				
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
				System.out.println(1);
			Thread thread = new Thread(new Runnable() {
	

			@Override
			public void run() {
				guiBar = new GuiBar();
				Controller.getInstance().addObserver(guiBar);
				
			}
		});
		thread.start();
		
				System.out.println(2);
				Controller.getInstance().start();
				
				
			}
		});
		
	}
	
	private GuiBar guiBar;
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

	@Override
	public void update(Observable arg0, Object arg1) {
		TableData tableData = Controller.getInstance().getTableData();
		
			dm.addRow(tableData.getLastEntry());
		
		
	}

	public GuiBar getGuiBar() {
		return guiBar;
	}

	public void setGuiBar(GuiBar guiBar) {
		this.guiBar = guiBar;
	}





	
}
