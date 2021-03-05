package supplier;

import java.awt.Color;
import csc330Final.SupplierLogin;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import csc330Final.HomePage;
import csc330Final.customerLogin;
import csc330Final.sqliteConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class orders extends SupplierLogin {
	private static JFrame frame;
	private static JTable table;
	private JTextField idText;
	private JTextField statusText;
	static Connection conn = null;
	static String sId = null;
	
	public orders( String x) {
		sId = x;
		init();
	}
	
	public void init(){
		frame = new JFrame("Orders");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(678, 502);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 642, 295);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 622, 273);
		panel_1.add(scrollPane);
		
		table = new JTable();
		refresh();
		scrollPane.setViewportView(table);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierHome s = new supplierHome(sId);
				frame.setVisible(false);
			}
		});
		backButton.setBounds(471, 412, 148, 40);
		frame.getContentPane().add(backButton);
		
		JLabel lblNewLabel = new JLabel("Order ID:");
		lblNewLabel.setBounds(10, 344, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Status:");
		lblNewLabel_1.setBounds(284, 344, 63, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		idText = new JTextField();
		idText.setBounds(68, 341, 128, 20);
		frame.getContentPane().add(idText);
		idText.setColumns(10);
		
		statusText = new JTextField();
		statusText.setBounds(370, 341, 148, 20);
		frame.getContentPane().add(statusText);
		statusText.setColumns(10);
		
		JButton btnNewButton = new JButton("Update Status");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sta = statusText.getText();
				String id = idText.getText();
				try{
					String sqlQuery = "UPDATE orders SET status = '"+ sta +"' WHERE orderID = '" + id+"'" ;
					PreparedStatement st = conn.prepareStatement(sqlQuery);
					st.executeUpdate();
					st.close();
					refresh();
					}
					catch (Exception c){
						JOptionPane.showMessageDialog(null, c);
					}
			}
		});
		btnNewButton.setBounds(25, 412, 128, 32);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
	public static void refresh(){
		conn = sqliteConnection.dbConnector();
		try{
			
			String sqlQuery = "SELECT orders.orderID, orders.status, orders.username, orders.itemID, "
					+ "stocks.inStock, stocks.color, stocks.price FROM orders "
					+ "INNER JOIN stocks ON stocks.itemID = orders.itemID and stocks.supplierID ='" + sId +"'";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			st.close();
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
	}


}