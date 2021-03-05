package csc330Final;

import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;

import customer.customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class customerLogin  extends JPanel{

	private JFrame frame;
	Connection conn = null;
	private JTextField UNField;
	private JPasswordField passwordField;
	private String id = "";


	/**
	 * Create the application.
	 */
	public customerLogin() {
		conn = sqliteConnection.dbConnector();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton logInButton = new JButton("Login");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = " SELECT * FROM customers WHERE username = ?  and password = ? ";
					PreparedStatement pst = conn.prepareStatement(query);
					id = UNField.getText();			
					pst.setString(1, id);
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0; 
					while(rs.next()){
						count ++;	
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Username and Password are correct");
						customer x = new customer(id);
						frame.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "Username and Passwrod are not correct. Try Again!!");
					}
					rs.close();
					pst.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	
		logInButton.setBounds(275, 166, 133, 36);
		frame.getContentPane().add(logInButton);
		
		JLabel UNLabel = new JLabel("User Name :");
		UNLabel.setBounds(48, 52, 88, 23);
		frame.getContentPane().add(UNLabel);
		
		JLabel passLabel = new JLabel("Password :");
		passLabel.setBounds(48, 96, 77, 23);
		frame.getContentPane().add(passLabel);
		
		UNField = new JTextField();
		UNField.setBounds(146, 53, 86, 20);
		frame.getContentPane().add(UNField);
		UNField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 97, 89, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creatC c = new creatC();
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(36, 173, 133, 29);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}
