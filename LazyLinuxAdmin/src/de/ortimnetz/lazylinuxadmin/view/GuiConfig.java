package de.ortimnetz.lazylinuxadmin.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class GuiConfig extends JFrame {
	private JTextField txtUser;
	private JTextField txtKeyFile;
	private JTextField txtHostFile;
	private JPasswordField pwdPassword;
	private JPasswordField pwdKeyPassword;
	private JTextField txtPort;
	private JLabel lblPassword;

	public GuiConfig(){
		
		this.setTitle("Configuration");
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setSize(400, 260);
		getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(10, 11, 46, 14);
		getContentPane().add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setText("");
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
		txtKeyFile.setText("");
		txtKeyFile.setBounds(101, 83, 120, 20);
		getContentPane().add(txtKeyFile);
		txtKeyFile.setColumns(10);
		
		JLabel lblHostFile = new JLabel("Host File:");
		lblHostFile.setBounds(10, 136, 95, 14);
		getContentPane().add(lblHostFile);
		
		txtHostFile = new JTextField();
		txtHostFile.setText("");
		txtHostFile.setBounds(101, 133, 120, 20);
		getContentPane().add(txtHostFile);
		txtHostFile.setColumns(10);
		
		JLabel lblKeyPassword = new JLabel("Key Password:");
		lblKeyPassword.setBounds(10, 111, 95, 14);
		getContentPane().add(lblKeyPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("");
		pwdPassword.setBounds(101, 58, 120, 20);
		getContentPane().add(pwdPassword);
		
		pwdKeyPassword = new JPasswordField();
		pwdKeyPassword.setText("");
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
		txtPort.setText("22");
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
		
		
		
		
		
		
		
	}
}
