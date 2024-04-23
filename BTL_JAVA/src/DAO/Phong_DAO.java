package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Phong;

public class Phong_DAO {
	private Connection con;
	
	//get danh sach
	public ArrayList<Phong> getAllPhong(){
		ArrayList<Phong> listPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "SELECT * FROM Phong";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				String idPhong = rs.getString(1);
				String ten = rs.getString(2);
				int soLuongGhe = rs.getInt(3);
				Phong kh = new Phong(idPhong, ten, soLuongGhe);
				listPhong.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPhong;
	}
	
	//Them
	public boolean addPhong(Phong data) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
			try {
				String sql = "INSERT INTO Phong VALUES(?,?,?)";
				PreparedStatement state = con.prepareStatement(sql);
				state.setString(1, data.getMaPhong());
				state.setString(2, data.getTenPhong());
				state.setInt(3, data.getSoLuongGhe());
				n = state.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			return n > 0;
	}
	
	
	//Cap nhat
	public boolean updatePhong(Phong data) {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
		int n = 0;
		PreparedStatement state = null;
		try {
			String sql = "UPDATE Phong SET "
						+ "TenPhong = ?, "
						+ "SoLuongGhe = ? "
						+ "WHERE MaPhong = ?";
			state = con.prepareStatement(sql);
			state.setString(1, data.getTenPhong());
			state.setInt(2, data.getSoLuongGhe());
			state.setString(3, data.getMaPhong());
			n = state.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				state.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	//xoa
	public boolean removePhong(String maPhong) {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0 ;
		try {
			String sql = "DELETE FROM Phong Where MaPhong = ?";
			state = con.prepareStatement(sql);
			state.setString(1, maPhong);
			n = state.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public int getLength() {
		int length=0;
		try {
			
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "SELECT soPhong FROM Phong";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				length++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}
	
	
	public String[] getALLTenPhong() {
		String [] dsPhong= new String[getLength()];
		int i=0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "SELECT soPhong FROM Phong";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				String ten = rs.getString(1);
				dsPhong[i++]=ten;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
}
