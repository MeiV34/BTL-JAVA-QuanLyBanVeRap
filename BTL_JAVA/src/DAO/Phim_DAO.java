package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import connect.ConnectDB;
import entity.Phim;

public class Phim_DAO {
	private Connection con;
	public ArrayList<Phim> getAllPhim(){
		ArrayList<Phim> listPhim = new ArrayList<Phim>();
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "SELECT * FROM Phim";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				String idPhim = rs.getString(1);
				String tenPhim = rs.getString(2);
				String daoDien = rs.getString(3);
				String theLoai = rs.getString(4);
				int thoiLuong = rs.getInt(5);
				// Chuyển đổi java.sql.Date thành LocalDate
				java.sql.Date sqlReleaseNgayCC = rs.getDate(6);
				java.sql.Date sqlReleaseNgayKT = rs.getDate(7);
				int danhGia = rs.getInt(8);
				Phim p = new Phim(idPhim, tenPhim, daoDien, theLoai, thoiLuong, sqlReleaseNgayCC, sqlReleaseNgayKT, danhGia);
				listPhim.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPhim;
	}
	public boolean addPhim(Phim p) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
			try {
				// Lấy Date từ java.util.Date
				Date ngayCC = p.getNgayCC();
				Date ngayKT = p.getNgayKT();
				
				// Chuyển đổi từ java.util.Date sang java.sql.Date
			    java.sql.Date sqlNgayCC = new java.sql.Date(ngayCC.getTime());
			    java.sql.Date sqlNgayKT = new java.sql.Date(ngayKT.getTime());
					
				String sql = "INSERT INTO Phim VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement state = con.prepareStatement(sql);
				state.setString(1, p.getIdPhim());
				state.setString(2, p.getTenPhim());
				state.setString(3, p.getDaoDien());
				state.setString(4, p.getTheLoai());
				state.setInt(5, p.getThoiLuong());
				state.setDate(6,sqlNgayCC);
				state.setDate(7,sqlNgayKT);
				state.setInt(8, p.getDanhGia());
				n = state.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Trùng mã Phim!");
			}
			return n > 0;
	}
	
	public boolean updatePhim(Phim p) {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0;
		try {
			Date ngayCongChieu = (Date) p.getNgayCC();
			Date ngayKetThuc = (Date) p.getNgayKT();
			// Chuyển đổi từ java.util.Date sang java.sql.Date
		    java.sql.Date sqlNgayCC = new java.sql.Date(ngayCongChieu.getTime());
		    java.sql.Date sqlNgayKT = new java.sql.Date(ngayKetThuc.getTime());
		    
			String sql = "UPDATE Phim set "
						+ "TenPhim = ?,"
						+ "DaoDien = ?,"
						+ "TheLoai = ?,"
						+ "ThoiLuong = ?,"
						+ "NgayPhatHanh = ?,"
						+ "NgayKetThuc = ? ,"
						+ "XepHang = ? "
						+ "WHERE MaPhim = ?";
			state = con.prepareStatement(sql);
			state.setString(1, p.getTenPhim());
			state.setString(2, p.getDaoDien());
			state.setString(3, p.getTheLoai());
			state.setInt(4, p.getThoiLuong());
			state.setDate(5, sqlNgayCC);
			state.setDate(6, sqlNgayKT);
			state.setInt(7, p.getDanhGia());
			state.setString(8, p.getIdPhim());
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
	
	public boolean removePhim(String maPhim) {
		ConnectDB.getInstance();
		con = ConnectDB.getConnection();
		PreparedStatement state = null;
		int n = 0 ;
		try {
			String sql = "DELETE FROM Phim Where MaPhim = ?";
			state = con.prepareStatement(sql);
			state.setString(1, maPhim);
			n = state.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
