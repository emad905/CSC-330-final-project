package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import csc330Final.sqliteConnection;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class cAccount {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String id;
	static Connection conn = null;
	String email = "";
	String city = "";
	String street = "";
	String name = "";
	String credit = "";
	private JTextField textField_5;

	

	/**
	 * Create the application.
	 */
	public cAccount(String a) {
		id =a;
		initialize();
	}

	private void getValues(){
		conn = sqliteConnection.dbConnector();
		try{
			String sqlQuery = "SELECT * FROM customers WHERE username ='"+ id+"'";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
			name = rs.getString("name");
			street = rs.getString("street");
		    city = rs.getString("city");
		    email = rs.getString("email");
		    credit = rs.getString("cardInfo");
			st.close();
			rs.close();
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Account");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(112, 66, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(18, 40, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Street Address:");
		lblAddress.setBounds(12, 104, 90, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setBounds(240, 60, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(240, 118, 46, 14);
		frame.getContentPane().add(lblCity);
		
		textField_1 = new JTextField();
		textField_1.setBounds(278, 86, 138, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 126, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(290, 137, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblUpdateAccount = new JLabel("Update Account");
		lblUpdateAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUpdateAccount.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUpdateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateAccount.setBounds(159, 0, 116, 37);
		frame.getContentPane().add(lblUpdateAccount);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqliteConnection.dbConnector();
				try{
					String sqlQuery = "UPDATE customers SET name = '"+ name +"' "
							+ ", street = '"+street+ "', email = '"+email+"'"+", city = '"+ city
									+"', cardInfo = '"+credit+"'"+ " WHERE username = '"+ id+"'";
					PreparedStatement st = conn.prepareStatement(sqlQuery);
					st.executeUpdate();
					st.close();
					}
					catch (Exception d){
						JOptionPane.showMessageDialog(null, d);
						}
			}
		});
		
		btnNewButton.setBounds(25, 224, 126, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer x = new customer(id);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(278, 225, 116, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		getValues();
		JLabel nameLbl = new JLabel(name);
		nameLbl.setBounds(112, 38, 86, 16);
		frame.getContentPane().add(nameLbl);
		
		JLabel streetLbl = new JLabel(street);
		streetLbl.setBounds(122, 102, 76, 16);
		frame.getContentPane().add(streetLbl);
		
		JLabel eLbl = new JLabel(email);
		eLbl.setBounds(290, 58, 126, 16);
		frame.getContentPane().add(eLbl);
		
		JLabel cityLbl = new JLabel(city);
		cityLbl.setBounds(302, 116, 73, 16);
		frame.getContentPane().add(cityLbl);
		
		JLabel cLbl = new JLabel("Credit Card:");
		cLbl.setBounds(57, 175, 73, 16);
		frame.getContentPane().add(cLbl);
		
		JLabel creditLbl = new JLabel(credit);
		creditLbl.setBounds(178, 175, 108, 16);
		frame.getContentPane().add(creditLbl);
		
		textField_5 = new JTextField();
		textField_5.setBounds(164, 199, 122, 24);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		frame.setVisible(true);
	}
}
