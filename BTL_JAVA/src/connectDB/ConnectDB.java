package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectDB {
	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance () {
		return instance;
	}
	
	public void connect() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databasename=RapBHV;trustServerCertificate=true;encrypt=true";
<<<<<<< HEAD
			String user = "sa";
			String pwd = "123";
=======
			String user = "kriscao";
			String pwd = "07122002";
>>>>>>> e218dff0cf4707aeea752b27be6ebe6d1c198028
			con = DriverManager.getConnection(url, user, pwd);
//			JOptionPane.showMessageDialog(null, "Connected!");
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unconnected!");
		}
	}
	public void disconnect() throws SQLException {
		if(con != null) {
			con.close();
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
