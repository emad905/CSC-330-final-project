package csc330Final;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuDragMouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home");
		frame.getContentPane().setBackground(new Color(0, 128, 128));
		frame.setBounds(100, 100, 517, 284);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CSC 330 Final");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(10, 4, 123, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton CustomerButton = new JButton("Customers");
		CustomerButton.setForeground(new Color(0, 0, 0));
		CustomerButton.setBackground(new Color(255, 255, 255));
		CustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerLogin x = new customerLogin();
				frame.setVisible(false);
			}
		});
		CustomerButton.setBounds(36, 157, 168, 45);
		frame.getContentPane().add(CustomerButton);
		
		JButton SupButton = new JButton("Suppliers");
		SupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierLogin sL = new SupplierLogin();
				frame.setVisible(false);
			}
		});
		SupButton.setBounds(317, 157, 180, 45);
		frame.getContentPane().add(SupButton);
		
		JLabel lblNewLabel_1 = new JLabel("Account Type");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(203, 64, 115, 23);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setVisible(true);
	}
}
