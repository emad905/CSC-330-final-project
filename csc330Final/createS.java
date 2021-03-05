package csc330Final;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class createS {

	private JFrame frame;
	private final JButton btnCreateAccount = new JButton("Create Account");
	private JTextField nameText;
	private JTextField passText;
	private JTextField sText;
	private JTextField eText;
	private JTextField phText;
	private JTextField cText;
	static Connection conn = sqliteConnection.dbConnector();


	/**
	 * Create the application.
	 */
	public createS() {
		initialize();
	}
	
	private boolean checkUser(String u, String p){
		boolean x = true ;
		try{
			String query = " SELECT * FROM suppliers WHERE name = ?  and password = ? ";
			PreparedStatement pst = conn.prepareStatement(query);
						
			pst.setString(1, u);
			pst.setString(2, p);
			ResultSet rs = pst.executeQuery();
			int count = 0; 
			while(rs.next()){
				count ++;	
			}
			if(count == 1){
				JOptionPane.showMessageDialog(null, "Username and password already exist use different password or Login!!");
				x =  false;
			}else{
				JOptionPane.showMessageDialog(null, "Account created succeffully!!");
			}
			rs.close();
			pst.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return x;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Create Account");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							String name =  nameText.getText();
							String street =  sText.getText();
							String email = eText.getText();
							String phone =  phText.getText();
							String pass =  passText.getText();
							String city = cText.getText();
							if(checkUser(name, pass)){
							try {
								PreparedStatement posted = conn.prepareStatement("INSERT INTO suppliers (name,street,city,email,phone,password)"
										+ "VALUES('"+ name +"','"+ street +"','"+ city +"' ,'"+ email +"','"+ phone +"' ,'"
										+ pass +"')");
									posted.executeUpdate();
							      posted.close();
							}catch(SQLException p) {
								JOptionPane.showMessageDialog(null, p);
							}
							}
			}
		});
		btnCreateAccount.setBounds(10, 208, 145, 31);
		frame.getContentPane().add(btnCreateAccount);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage h = new HomePage();
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(287, 208, 126, 32);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(20, 41, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Street:");
		lblNewLabel_1.setBounds(20, 153, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("City:");
		lblNewLabel_2.setBounds(224, 153, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("email:");
		lblNewLabel_3.setBounds(224, 41, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("phone");
		lblNewLabel_4.setBounds(224, 103, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password:");
		lblNewLabel_5.setBounds(20, 103, 50, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		nameText = new JTextField();
		nameText.setBounds(88, 38, 86, 20);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		passText = new JTextField();
		passText.setBounds(88, 100, 86, 20);
		frame.getContentPane().add(passText);
		passText.setColumns(10);
		
		sText = new JTextField();
		sText.setBounds(88, 150, 86, 20);
		frame.getContentPane().add(sText);
		sText.setColumns(10);
		
		eText = new JTextField();
		eText.setBounds(299, 38, 86, 20);
		frame.getContentPane().add(eText);
		eText.setColumns(10);
		
		phText = new JTextField();
		phText.setBounds(299, 100, 86, 20);
		frame.getContentPane().add(phText);
		phText.setColumns(10);
		
		cText = new JTextField();
		cText.setBounds(299, 150, 86, 20);
		frame.getContentPane().add(cText);
		cText.setColumns(10);
		
		frame.setVisible(true);
		
	}

}
