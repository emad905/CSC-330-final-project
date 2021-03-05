package customer;

import javax.swing.*;

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

import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
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

public class cart extends JFrame {
	public cart() {
		init();
		
	}
	private static JFrame frmCart;
	private static DefaultTableModel model ;
	private static JTable table;
	
	public void init(){
		frmCart = new JFrame("cart");
		frmCart.setTitle("Cart");
		
		frmCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Object[] columns = {"Item ID", " Supplier", "Amount", "Cost"};
		
		
		frmCart.getContentPane().setLayout(null);
		frmCart.setSize(678, 502);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 642, 299);
		frmCart.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 610, 277);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Item ID", "Price"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//customer m  = new customer();
				frmCart.setVisible(false);
			}
		});
		backButton.setBounds(471, 412, 148, 40);
		frmCart.getContentPane().add(backButton);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(36, 346, 59, 22);
		frmCart.getContentPane().add(lblTotal);
		
		JLabel lblNewLabel = new JLabel("$$");
		lblNewLabel.setBounds(93, 350, 76, 14);
		frmCart.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Check out");
		btnNewButton.setBounds(77, 412, 148, 40);
		frmCart.getContentPane().add(btnNewButton);
		
		frmCart.setVisible(true);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cart window = new cart();
					window.frmCart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
