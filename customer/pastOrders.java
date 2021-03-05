package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;


import java.awt.Color;
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

public class pastOrders extends JFrame {
	
	private static JFrame frame;
	private static DefaultTableModel model ;
	private static JTable table;
	static Connection conn = null;
	private static String id;
	
	
	public pastOrders(String a) {
		id = a;
		init();
		
	}

	
	public void init(){
		frame = new JFrame("Orders");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		frame.getContentPane().setLayout(null);
		frame.setSize(678, 502);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 642, 370);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 622, 348);
		panel_1.add(scrollPane);
		
		table = new JTable();
		refresh();
		scrollPane.setViewportView(table);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer x = new customer(id);
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
			String sqlQuery = "SELECT * FROM orders WHERE username = '" + id+"'" ;
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


