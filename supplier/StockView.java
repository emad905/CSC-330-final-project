package supplier;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import csc330Final.sqliteConnection;
import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class StockView {
	private static JFrame frame;
	private static JTextField itemText;
	private static JTextField nameText;
	private static JTextField priceText;
	private static JTextField colorText;
	private static JTextField stockText;
	private static JTextField Dtext;
	private static JTextField makeText;
	static Connection conn = null;
	private static JTable table;
	String sI = null;
	public StockView(String sId){
		sI = sId;
		init();
	}
	public static void refresh(String sId){
		conn = sqliteConnection.dbConnector();
		try{
			String x = sId;
			System.out.println(sId);
			String sqlQuery = "SELECT * FROM stocks WHERE supplierID =  '"+ x+"'" ;
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			st.close();
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
	}
	
	private void init(){
		frame = new JFrame("Stock View");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().setLayout(null);
		frame.setSize(678, 502);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 285, 621, 125);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		itemText = new JTextField();
		itemText.setBounds(60, 25, 86, 20);
		panel.add(itemText);
		itemText.setColumns(10);
		
		nameText = new JTextField();
		nameText.setBounds(60, 56, 86, 20);
		panel.add(nameText);
		nameText.setColumns(10);
		
		priceText = new JTextField();
		priceText.setBounds(227, 25, 86, 20);
		panel.add(priceText);
		priceText.setColumns(10);
		
		colorText = new JTextField();
		colorText.setBounds(227, 56, 86, 20);
		panel.add(colorText);
		colorText.setColumns(10);
		
		JLabel ItemLabel = new JLabel("Item ID:");
		ItemLabel.setBounds(10, 28, 46, 14);
		panel.add(ItemLabel);
		
		JLabel SupplierLabel = new JLabel("Name:");
		SupplierLabel.setBounds(10, 59, 46, 14);
		panel.add(SupplierLabel);
		
		JLabel AmountLabel = new JLabel("Price:");
		AmountLabel.setBounds(171, 28, 46, 14);
		panel.add(AmountLabel);
		
		JLabel CostLabel = new JLabel("color:");
		CostLabel.setBounds(171, 59, 47, 14);
		panel.add(CostLabel);
		
		JLabel lblNewLabel = new JLabel("Stock:");
		lblNewLabel.setBounds(323, 28, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description:");
		lblNewLabel_1.setBounds(10, 101, 57, 14);
		panel.add(lblNewLabel_1);
		
		stockText = new JTextField();
		stockText.setBounds(389, 25, 86, 20);
		panel.add(stockText);
		stockText.setColumns(10);
		
		Dtext = new JTextField();
		Dtext.setBounds(77, 98, 86, 20);
		panel.add(Dtext);
		Dtext.setColumns(10);
		
		
		JButton AddItemButton = new JButton("Add Item");
		AddItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String itemID =  itemText.getText();
				String name =  nameText.getText();
				String color =  colorText.getText();
				String inStock = stockText.getText();
				String description =  Dtext.getText();
				String make =  makeText.getText();
				String price =  priceText.getText();
				conn = sqliteConnection.dbConnector();
				try {
					PreparedStatement posted = conn.prepareStatement("INSERT INTO stocks (itemID,name,price,color,supplierID,inStock,description, make)"
							+ "VALUES('"+itemID+"' ,'"+name +"','"+ price+"','"+color+"' ,'"+sI+"','"+ inStock+"' ,'"
							+ description +"', '"+make+"')");
						posted.executeUpdate();
				      posted.close();
				      refresh(sI);
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
				}
		});
		AddItemButton.setBounds(522, 97, 77, 23);
		panel.add(AddItemButton);
		
		JLabel lblNewLabel_2 = new JLabel("Make:");
		lblNewLabel_2.setBounds(323, 59, 46, 14);
		panel.add(lblNewLabel_2);
		
		makeText = new JTextField();
		makeText.setBounds(389, 56, 86, 20);
		panel.add(makeText);
		makeText.setColumns(10);
		
		JButton updButton = new JButton("Update Item");
		updButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemID =  itemText.getText();
				String name =  nameText.getText();
				String color =  colorText.getText();
				String inStock = stockText.getText();
				String description =  Dtext.getText();
				String make =  makeText.getText();
				String price =  priceText.getText();
				conn = sqliteConnection.dbConnector();
				try {
					PreparedStatement posted = conn.prepareStatement("UPDATE stocks SET name = '"+name+"', SET color = '"+color
							+"', SET instock = '"+ inStock +"', SET description = '"+ description +"', SET make = '"+ make 
							+"', SET price = '"+ price +"' WHERE  itemID =  '" + itemID+"'");
						posted.executeUpdate();
				      posted.close();
				      refresh(sI);
				}catch(SQLException p) {
					JOptionPane.showMessageDialog(null, p);
				}
			}
		});
		updButton.setBounds(322, 98, 93, 23);
		panel.add(updButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 23, 621, 246);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(504, 412, 148, 40);
		frame.getContentPane().add(backButton);
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.setBounds(237, 421, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						refresh(sI);
					}
				});
		

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierHome s = new supplierHome(sI);
				frame.setVisible(false);
			}
		});
		
		
		frame.setVisible(true);
	}
	
	
}
