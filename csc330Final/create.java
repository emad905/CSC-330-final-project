package csc330Final;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class create {

	private JFrame frame;

	private JTextField userText;
	private JTextField passText;
	private JTextField nameText;
	private JTextField cardText;
	private JTextField emailText;
	private JTextField addText;
	static Connection conn = null;
	private JTextField cText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create window = new create();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public create() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(186, 50, 66, 14);
		frame.getContentPane().add(lblNewLabel);
		
		userText = new JTextField();
		userText.setBounds(261, 47, 139, 20);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(10, 108, 66, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		passText = new JTextField();
		passText.setBounds(73, 105, 86, 20);
		frame.getContentPane().add(passText);
		passText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(10, 50, 53, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		nameText = new JTextField();
		nameText.setBounds(73, 47, 86, 20);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email: ");
		lblNewLabel_3.setBounds(10, 155, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		cardText = new JTextField();
		cardText.setBounds(261, 105, 139, 20);
		frame.getContentPane().add(cardText);
		cardText.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Street:");
		lblNewLabel_4.setBounds(186, 155, 53, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		emailText = new JTextField();
		emailText.setBounds(73, 152, 86, 20);
		frame.getContentPane().add(emailText);
		emailText.setColumns(10);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String userID =  userText.getText();
				String name =  nameText.getText();
				String street =  addText.getText();
				String email = emailText.getText();
				String card =  cardText.getText();
				String pass =  passText.getText();
				String city = cText.getText();
				
				conn = sqliteConnection.dbConnector();
				try {
					PreparedStatement posted = conn.prepareStatement("INSERT INTO customers (username,name,street,city,email,cardInfo,password)"
							+ "VALUES('"+ userID+"' ,'"+ name +"','"+ street +"','"+ city +"' ,'"+ email +"','"+ card +"' ,'"
							+ pass +"')");
						posted.executeUpdate();
				      posted.close();
				      JOptionPane.showMessageDialog(null, "Account Created Successfully");
				}catch(SQLException p) {
					JOptionPane.showMessageDialog(null, p);
				}
			}
		});
		btnCreateAccount.setBounds(21, 218, 118, 32);
		frame.getContentPane().add(btnCreateAccount);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage h = new HomePage();
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(282, 218, 118, 32);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_5 = new JLabel("Card Number:");
		lblNewLabel_5.setBounds(186, 108, 74, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		addText = new JTextField();
		addText.setBounds(261, 152, 139, 20);
		frame.getContentPane().add(addText);
		addText.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(106, 183, 46, 14);
		frame.getContentPane().add(lblCity);
		
		cText = new JTextField();
		cText.setBounds(153, 180, 99, 20);
		frame.getContentPane().add(cText);
		cText.setColumns(10);
	}
}
