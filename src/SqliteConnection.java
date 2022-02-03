import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {

	
public static Connection dbConnector() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn =  DriverManager.getConnection("jdbc:sqlite:Studentdb.db");
			
			JOptionPane.showMessageDialog(null, "The Connection is working");
			return conn;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
	}
	
	
	
	
}
