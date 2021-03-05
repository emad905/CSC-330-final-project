package csc330Final;

import java.sql.*;
import javax.swing.*;

public class sqliteConnection {

	
	public static Connection dbConnector(){
		Connection conn=null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:csc330FinalProject25.db");
			JOptionPane.showMessageDialog(null, "Connection Succefull!");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
