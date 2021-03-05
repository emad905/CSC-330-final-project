package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import csc330Final.sqliteConnection;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class itemView {

	private JFrame frame;
	private String id;
	String itemID = " ";
	String name = "";
	String seller = "";
	String price = "";
	String description = "";
	Integer count = null;
	static Connection conn = null;
	private JTextField countText;

	/**
	 * Create the application.
	 */
	public itemView(String x, String a) {
		id = a;
		initialize(x);
	}
	
	private void getValues(String z){
		conn = sqliteConnection.dbConnector();
		try{
			String sqlQuery = "SELECT * FROM stocks WHERE name ='"+ z +"' ";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
				itemID = rs.getString("itemID");
				 name = rs.getString("name");
				 seller = rs.getString("supplierID");
				 price	= rs.getString("price");
				 description = rs.getString("description");
			
			st.close();
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String f) {
		frame = new JFrame("item");
		frame.setBounds(100, 100, 492, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String m = f + ".png";
		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/"+m));
		JLabel lblNewLabel = new JLabel(img3);
		lblNewLabel.setBounds(26, 45, 103, 104);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = Integer.parseInt(countText.getText());
				if (count > 0){
				
				try {
					conn = sqliteConnection.dbConnector();
					PreparedStatement posted = conn.prepareStatement("INSERT INTO orders (itemID,itemNo,payment,status, username)"
							+ "VALUES('"+itemID+"' ,'"+count +"','card' ,'Order Placed' ,'" + id +"')");
						posted.executeUpdate();
				      posted.close();
				}catch(SQLException p) {
					JOptionPane.showMessageDialog(null, p);
				}
					
			}else{
				JOptionPane.showMessageDialog(null, "Please enter desired amount!");
			}
			}
		});
		btnNewButton.setBounds(26, 225, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton backButton_1 = new JButton("Back");
		backButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer cust = new customer(id);
				frame.setVisible(false);
			}
		});
		backButton_1.setBounds(377, 225, 89, 23);
		frame.getContentPane().add(backButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Price:");
		lblNewLabel_1.setBounds(197, 196, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Seller:");
		lblNewLabel_3.setBounds(197, 95, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Name:");
		lblNewLabel_4.setBounds(197, 45, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		getValues(f);
		JLabel NameLbl = new JLabel(name);
		NameLbl.setBounds(291, 44, 136, 16);
		frame.getContentPane().add(NameLbl);
		
		JLabel sellerLbl = new JLabel(seller);
		sellerLbl.setBounds(291, 94, 147, 16);
		frame.getContentPane().add(sellerLbl);
		
		JLabel priceLbl = new JLabel(price);
		priceLbl.setBounds(291, 195, 147, 16);
		frame.getContentPane().add(priceLbl);
		
		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setBounds(197, 145, 82, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel desLbl = new JLabel(description);
		desLbl.setBounds(291, 145, 147, 16);
		frame.getContentPane().add(desLbl);
		
		JLabel aLbl = new JLabel("Amount");
		aLbl.setBounds(26, 163, 55, 16);
		frame.getContentPane().add(aLbl);
		
		countText = new JTextField();
		countText.setText("0");
		countText.setBounds(26, 185, 46, 28);
		frame.getContentPane().add(countText);
		countText.setColumns(10);
		
		frame.setVisible(true);
	}
}
