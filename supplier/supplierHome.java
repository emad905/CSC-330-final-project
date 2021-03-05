package supplier;

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
import csc330Final.HomePage;
import javax.swing.event.MenuDragMouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class supplierHome {

	private JFrame frame;
	private String sId ;

	
	public supplierHome(String x) {
		sId = x;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Supplier");
		frame.setBounds(100, 100, 524, 299);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(559, 0, 17, 321);
		frame.getContentPane().add(scrollBar);
		
		JButton btnNewButton = new JButton("Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sAccount a = new sAccount(sId);
				frame.setVisible(false);
			}
		});
		
		btnNewButton.setBounds(330, 0, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Welcome "+ sId);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(10, 4, 137, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton StockButton = new JButton("Stock");
		StockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StockView s = new StockView(sId);
				frame.setVisible(false);
			}
		});
		StockButton.setBounds(330, 100, 146, 46);
		frame.getContentPane().add(StockButton);
		
		JButton OrdersButton = new JButton("Orders");
		OrdersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String g = sId.toString();
				orders o = new orders(g);
				frame.setVisible(false);
			}
		});
		OrdersButton.setBounds(20, 100, 151, 46);
		frame.getContentPane().add(OrdersButton);
		
		JButton ticktButton = new JButton("Tickets");
		ticktButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tickets t = new tickets(sId);
				frame.setVisible(false);
			}
		});
		ticktButton.setBounds(179, 167, 146, 46);
		frame.getContentPane().add(ticktButton);
		
		JButton btnNewButton_1 = new JButton("Log out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage h = new HomePage();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(419, 0, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
}
