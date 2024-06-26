package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import common.Utils;
public class KhachHang_DAO {

	private Connection con;
	
	//get danh sach khach hang
	public ArrayList<KhachHang> getAllKhachHang(){
		ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "SELECT * FROM KhachHang";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				int idKhachHang = rs.getInt(1);
				String hoTen = rs.getString(2);
				String email = rs.getString(3);
				String sdt = rs.getString(4);
				KhachHang kh = new KhachHang(idKhachHang, hoTen, email, sdt);
				listKhachHang.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listKhachHang;
	}
	
	//get ma khach hang lon nhat
		public int getMaKHAutoTang(){
			int newInput = 1;
			try {
				ConnectDB.getInstance().connect();
				con = ConnectDB.getConnection();
				String sql = "SELECT TOP 1 MaKhachHang FROM KhachHang ORDER BY MaKhachHang DESC";
				Statement state = con.createStatement();
				ResultSet rs = state.executeQuery(sql);
				while(rs.next()) {
					newInput = rs.getInt(1) + 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return newInput;
		}
	
	//Them
	public boolean addKhachHang(KhachHang kh) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
			try {
				String sql = "INSERT INTO KhachHang VALUES(?,?,?,?)";
				PreparedStatement state = con.prepareStatement(sql);
				state.setInt(1, kh.getMaKH());
				state.setString(2, kh.getHoTen());
				state.setString(3, kh.getEmail());
				state.setString(4, kh.getSdt());
				n = state.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
//				JOptionPane.showMessgeDialog(null, "Mã khách hàng đã tồn tại!");
			}
			return n > 0;
	}
	
	
	//Cap nhat
	public boolean updateKhachHang(KhachHang kh) {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		try {
			String sql = "UPDATE KhachHang SET "
						+ "HoTen = ?, "
						+ "Email = ?, "
						+ "Sdt = ? "
						+ "WHERE MaKhachHang = ?";
			state = con.prepareStatement(sql);
			state.setString(1, kh.getHoTen());
			state.setString(2, kh.getEmail());
			state.setString(3, kh.getSdt());
			state.setInt(4, kh.getMaKH());
			n = state.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
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
	public boolean removeKhachHang(int maKhachHang) {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0 ;
		try {
			String sql = "DELETE FROM KhachHang Where MaKhachHang = ?";
			state = con.prepareStatement(sql);
			state.setInt(1, maKhachHang);
			n = state.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
