package csc330Final;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import supplier.supplierHome;
public class SupplierLogin extends JFrame{

	private JFrame frame;
	Connection conn = null;
	private JTextField UNField;
	private JPasswordField passwordField;
	private String id = "";

	/**
	 * Create the application.
	 */
	public SupplierLogin() {
		initialize();
		conn = sqliteConnection.dbConnector();
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
					String query = " SELECT * FROM suppliers WHERE name = ? and password = ? ";
					PreparedStatement pst = conn.prepareStatement(query);

					pst.setString(1, UNField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					id =  rs.getString("name");
					int count = 0; 
					while(rs.next()){
						System.out.println(rs.toString());
						count ++;	
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Username and Password are correct");
						supplierHome home = new supplierHome(id);
						frame.setVisible(false);
					}else if (count > 1){
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					}else{
						JOptionPane.showMessageDialog(null, "Username and Passwrod are not correct. Try Again!!");
					}
					rs.close();
					pst.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				frame.setVisible(false);
			}
		});
	
		logInButton.setBounds(279, 173, 133, 36);
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
				createS s = new createS();
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(25, 180, 133, 29);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}
