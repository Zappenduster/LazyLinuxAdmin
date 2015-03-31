package de.ortimnetz.lazylinuxadmin.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;



import de.ortimnetz.lazylinuxadmin.controller.Controller;
import de.ortimnetz.lazylinuxadmin.model.Config;


public class GuiConfig extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
	private JTextField txtKeyFile;
	private JTextField txtHostFile;
	private JPasswordField pwdPassword;
	private JPasswordField pwdKeyPassword;
	private JTextField txtPort;
	private JLabel lblPassword;
	private Config config;

	public GuiConfig(){
		this.config = Config.getInstance();
		this.setTitle("Configuration");
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setSize(400, 260);
		getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(10, 11, 46, 14);
		getContentPane().add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setText(config.getUser());
		txtUser.setBounds(101, 8, 120, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 61, 95, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblKeyFile = new JLabel("Key File:");
		lblKeyFile.setBounds(10, 86, 46, 14);
		getContentPane().add(lblKeyFile);
		
		txtKeyFile = new JTextField();
		txtKeyFile.setText(config.getKeyfile());
		txtKeyFile.setBounds(101, 83, 120, 20);
		getContentPane().add(txtKeyFile);
		txtKeyFile.setColumns(10);
		
		JLabel lblHostFile = new JLabel("Host File:");
		lblHostFile.setBounds(10, 136, 95, 14);
		getContentPane().add(lblHostFile);
		
		txtHostFile = new JTextField();
		txtHostFile.setText(config.getHostsfile());
		txtHostFile.setBounds(101, 133, 120, 20);
		getContentPane().add(txtHostFile);
		txtHostFile.setColumns(10);
		
		JLabel lblKeyPassword = new JLabel("Key Password:");
		lblKeyPassword.setBounds(10, 111, 95, 14);
		getContentPane().add(lblKeyPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText(config.getPass());
		pwdPassword.setBounds(101, 58, 120, 20);
		getContentPane().add(pwdPassword);
		
		pwdKeyPassword = new JPasswordField();
		pwdKeyPassword.setText(config.getKeypass());
		pwdKeyPassword.setBounds(101, 108, 120, 20);
		getContentPane().add(pwdKeyPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 182, 112, 29);
		getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(262, 182, 112, 29);
		getContentPane().add(btnExit);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 36, 46, 14);
		getContentPane().add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setText(""+config.getPort());
		txtPort.setBounds(101, 33, 120, 20);
		getContentPane().add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnPickKeyFile = new JButton("Choose Key File");
		btnPickKeyFile.setBounds(231, 83, 143, 20);
		getContentPane().add(btnPickKeyFile);
		
		JButton btnPickHostFile = new JButton("Choose Host File");
		btnPickHostFile.setBounds(231, 133, 143, 20);
		getContentPane().add(btnPickHostFile);
		this.setVisible(true);
		
		btnPickKeyFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				txtKeyFile.setText(fc.getSelectedFile().toString());
				
				
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		btnPickHostFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				txtHostFile.setText(fc.getSelectedFile().toString());
				
			}
		});
		

		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				
				// User textfield is being checked
				if(txtUser.getText().equalsIgnoreCase("")){
					txtUser.setBackground(Color.RED);
					System.out.println("Please fill out a user.");

				} else {
					config.setUser(txtUser.getText());
					System.out.println("User has been written to config.");
				}
				
				// Port textfield is being checked
				if(txtPort.getText().equalsIgnoreCase("")){
					txtPort.setBackground(Color.RED);
					System.out.println("Please fill out the port.");

				} else {
					config.setPort(Integer.parseInt(txtPort.getText().toString()));
					System.out.println("Port has been written to config.");
				}
				
				config.setPass(pwdPassword.getText().toString());

				if(txtKeyFile.getText().equalsIgnoreCase("")){
					txtKeyFile.setBackground(Color.RED);
					System.out.println("Please choose a key file.");

				} else {
					config.setKeyfile(txtKeyFile.getText().toString());
					System.out.println("Key file has been written to config.");
				}
				
				config.setKeypass(pwdKeyPassword.getText().toString());
				
				if(txtHostFile.getText().equalsIgnoreCase("")){
					txtHostFile.setBackground(Color.RED);
					System.out.println("Please choose a host file.");
	
				} else {
					config.setHostsfile(txtHostFile.getText().toString());
					System.out.println("Host file has been written to config.");
				}
				

					Config.setInstance(config);
					Controller.getInstance().saveConfig();

				
			}
		});
		
	}
	

	
}
