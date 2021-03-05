package supplier;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import csc330Final.sqliteConnection;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class sAccount {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static Connection conn = null;
	String email = "";
	String city = "";
	String ph = null;
	String street = "";
	String name = "";
	String sID = "";


	public sAccount(String q) {
		sID = q;
		initialize();
	}

	private void getValues(){
		conn = sqliteConnection.dbConnector();
		try{
			String sqlQuery = "SELECT * FROM suppliers WHERE supplerid ='"+ sID +"'";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
			name = rs.getString("name");
			street = rs.getString("street");
			ph	= rs.getString("phone");
			city = rs.getString("city");
			email = rs.getString("email");
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
		
		getValues();
		
		textField = new JTextField();
		textField.setBounds(109, 73, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(27, 49, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Street Address:");
		lblAddress.setBounds(12, 104, 90, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setBounds(240, 49, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(240, 104, 46, 14);
		frame.getContentPane().add(lblCity);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(12, 162, 92, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
		textField_1 = new JTextField();
		textField_1.setBounds(296, 73, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(109, 129, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(296, 129, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(109, 186, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblUpdateAccount = new JLabel("Update Account");
		lblUpdateAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUpdateAccount.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUpdateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateAccount.setBounds(160, 11, 116, 37);
		frame.getContentPane().add(lblUpdateAccount);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqliteConnection.dbConnector();
				try{
					String sqlQuery = "UPDATE suppliers SET name = '"+ name +"' "
							+ ", street = '"+street+"', phone = '"+ ph +"'"
									+ ", city = '"+ city+"', email = '"+email+"'"
											+ " WHERE supplerid ='"+ sID+"' ";
					PreparedStatement st = conn.prepareStatement(sqlQuery);
					st.executeUpdate();
					st.close();
					}
					catch (Exception d){
						JOptionPane.showMessageDialog(null, d);
					}
			}
		});
		btnNewButton.setBounds(27, 217, 126, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierHome sh = new supplierHome(sID);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(256, 206, 126, 28);
		frame.getContentPane().add(btnNewButton_1);
		

		JLabel addrlbl = new JLabel(street);
		addrlbl.setBounds(119, 104, 46, 14);
		frame.getContentPane().add(addrlbl);
		
		JLabel namelbl = new JLabel(name);
		namelbl.setBounds(107, 49, 46, 14);
		frame.getContentPane().add(namelbl);
		
		JLabel phonelbl = new JLabel(ph);
		phonelbl.setBounds(119, 162, 46, 14);
		frame.getContentPane().add(phonelbl);
		
		JLabel elbl = new JLabel(email);
		elbl.setBounds(296, 49, 46, 14);
		frame.getContentPane().add(elbl);
		
		JLabel clbl = new JLabel(city);
		clbl.setBounds(296, 104, 46, 14);
		frame.getContentPane().add(clbl);
		
		frame.setVisible(true);
	}
}
