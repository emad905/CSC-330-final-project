package customer;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import customer.Randomizer;
import csc330Final.sqliteConnection;
import net.proteanit.sql.DbUtils;
import csc330Final.HomePage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class customer extends JFrame{

	private JFrame frame;
	static Connection conn = null;
	 JLabel label = null;  
	    JLabel label2 = null;
	    JLabel label3 = null; 
	    JLabel label4 = null; 
	    JPanel panel = null;  
	    Icon icon = null;
	    ImageIcon img1 = null;
	    ImageIcon img2 = null;
	    ImageIcon img3 = null;
	    ImageIcon img4 = null;
	    JButton btnNewButton_6 ;
	    JButton btnNewButton_1 ;
	    JButton btnNewButton_2;
		JButton btnNewButton;
		private String id;
	    private final JButton btnRefresh = new JButton("Refresh");

 	/**
	 * Create the application.
	 */
	public customer(String a) {
		id=a;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Customer");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Account");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAccount c = new cAccount(id);
				frame.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(10, 10, 88, 28);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("Orders");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pastOrders p = new pastOrders(id);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(208, 10, 86, 28);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage h = new HomePage();
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(392, 10, 86, 28);
		frame.getContentPane().add(btnLogOut);
		
		refresh();
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnRefresh.setBounds(186, 227, 90, 28);
		frame.getContentPane().add(btnRefresh);
		
		frame.setVisible(true);
	}
	private void refresh(){
		conn = sqliteConnection.dbConnector();
		ResultSet rs = null;
		Integer rand = null;
		List<String> sids = new ArrayList<String>();
		int i = 0;
		try{
			String sqlQuery = "SELECT name FROM stocks " ;
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			rs = st.executeQuery();
			 while (rs.next()) {
			        sids.add(rs.getString("name"));
			        i = rs.getRow();
			    }
			
			//System.out.println(i);
			rand = Randomizer.generateInt(0, (i-2));
			//System.out.println(rand);
		     img1 = new ImageIcon(this.getClass().getResource("/"+sids.get(rand)+".png"));
		     btnNewButton = new JButton(sids.get(rand));
		     btnNewButton.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent arg0) {
		     		itemView item = new itemView(btnNewButton.getText(), id);
		     		frame.setVisible(false);
		     	}
		     });
		     label3 = new JLabel(img1);
				label3.setBounds(12, 96, 64, 64);
				frame.getContentPane().add(label3);
				btnNewButton.setBounds(4, 186, 94, 28);
				frame.getContentPane().add(btnNewButton);
				
				
		     rand = Randomizer.generateInt(0, (i-2));
		     //System.out.println(rand);
		     img2 = new ImageIcon(this.getClass().getResource("/"+sids.get(rand)+".png"));
		     btnNewButton_6 = new JButton(sids.get(rand));
		     btnNewButton_6.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		itemView item = new itemView(btnNewButton_6.getText(), id);
		     		frame.setVisible(false);
		     	}
		     });
		     label = new JLabel(img2);
				label.setBounds(153, 112, 48, 48);
				frame.getContentPane().add(label);
				btnNewButton_6.setBounds(127, 186, 94, 28);
				frame.getContentPane().add(btnNewButton_6);
				
				
		     rand = Randomizer.generateInt(0, (i-2));
		    // System.out.println(rand);
		     img3 = new ImageIcon(this.getClass().getResource( "/"+sids.get(rand)+".png"));
		     btnNewButton_1 = new JButton(sids.get(rand));
		     btnNewButton_1.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		itemView item = new itemView(btnNewButton_1.getText(), id);
		     		frame.setVisible(false);
		     	}
		     });
		     label2 = new JLabel(img3);
				label2.setBounds(279, 96, 64, 64);
				frame.getContentPane().add(label2);
				btnNewButton_1.setBounds(249, 186, 94, 28);
				frame.getContentPane().add(btnNewButton_1);
				
				
		     rand = Randomizer.generateInt(0, (i-2));
		     //System.out.println(rand);
		     img4 = new ImageIcon(this.getClass().getResource( "/"+sids.get(rand)+".png"));
		     btnNewButton_2 = new JButton(sids.get(rand));
		     btnNewButton_2.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		itemView item = new itemView(btnNewButton_2.getText(), id);
		     		frame.setVisible(false);
		     	}
		     });
		     label4 = new JLabel(img4);
				label4.setBounds(397, 112, 64, 64);
				frame.getContentPane().add(label4);
				btnNewButton_2.setBounds(384, 186, 94, 28);
				frame.getContentPane().add(btnNewButton_2);
	
				
			st.close();
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		

	}
	
}
