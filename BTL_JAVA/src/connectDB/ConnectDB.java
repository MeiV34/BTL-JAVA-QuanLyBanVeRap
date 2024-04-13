package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyLopHoc;trustServerCertificate=true;encrypt=true";
		String user = "sa";
		String password = "@Sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("lỗi" + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		
	}

	public static Connection getConnection() {
		return con;
	}
}

