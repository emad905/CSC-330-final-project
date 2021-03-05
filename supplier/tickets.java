package supplier;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import csc330Final.HomePage;
import java.sql.*;
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

import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class tickets {
	private static JFrame frame;
	private static JTextField ticketText;
	private static JTextField statusText;
	private static DefaultTableModel model ;
	private static JTable table;
	private static JTextField issueText;
	static Connection conn = null;
	private static String sId = null;

	public tickets(String x){
		sId = x;
		init();
	}
	private void init(){
		frame = new JFrame("Tickets");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(678, 502);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 291, 609, 106);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		ticketText = new JTextField();
		ticketText.setBounds(82, 25, 105, 20);
		panel.add(ticketText);
		ticketText.setColumns(10);
		
		statusText = new JTextField();
		statusText.setBounds(82, 64, 105, 20);
		panel.add(statusText);
		statusText.setColumns(10);
		
		JLabel ItemLabel = new JLabel("Ticket ID:");
		ItemLabel.setBounds(10, 28, 62, 14);
		panel.add(ItemLabel);
		
		JLabel AmountLabel = new JLabel("Status:");
		AmountLabel.setBounds(10, 67, 50, 14);
		panel.add(AmountLabel);
		
		
		JButton AddItemButton = new JButton("Update Status");
		AddItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn = sqliteConnection.dbConnector();
				String ticketID = ticketText.getText();
				String issue = issueText.getText();
				String status = statusText.getText();
				try {
					
					PreparedStatement posted = conn.prepareStatement("Update Tickets SET status = " + status + ",issue= " + issue
							+ "WHERE TicketID = '"+ticketID+"'");
						posted.executeUpdate();
				      posted.close();
				      refresh();
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
					
			}
		});

		AddItemButton.setBounds(459, 72, 140, 23);
		panel.add(AddItemButton);
		
		JLabel lblNewLabel = new JLabel("Issue:");
		lblNewLabel.setBounds(230, 42, 50, 14);
		panel.add(lblNewLabel);
		
		issueText = new JTextField();
		issueText.setBounds(272, 39, 140, 20);
		panel.add(issueText);
		issueText.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 609, 269);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 589, 247);
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
		
		frame.setVisible(true);
	}
	
	
	public static void refresh(){
		conn = sqliteConnection.dbConnector();
		try{
			String sqlQuery = "SELECT tickets.TicketID, tickets.issue, tickets.orderID, tickets.status, orders.username" 
					+ " FROM tickets INNER JOIN orders ON orders.orderID = tickets.orderID "
					+ "INNER JOIN stocks ON orders.itemID = stocks.itemID INNER JOIN suppliers ON stocks.supplierID = '" +  sId +"'";
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