package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietVe;


public class ChiTietVeBan_DAO {
	public ArrayList<ChiTietVe> getListVeBan(String maNV){
		Connection con;
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();
		ArrayList<ChiTietVe> list = new ArrayList<ChiTietVe>();
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM ChiTietVe WHERE MaNhanVien = ?";
			
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maVe = rs.getString(1);
				String tenPhim = rs.getString(3);
				java.sql.Date ngayChieu = rs.getDate(4);
				Time suatChieu = rs.getTime(5);
				String phong = rs.getString(6);
				String dsGhe = rs.getString(7);
				String hoTenKh = rs.getString(8);
				String sdt = rs.getString(9);
				int soLuongGhe = rs.getInt(10);
				ChiTietVe sc = new ChiTietVe(maVe, tenPhim, ngayChieu, suatChieu, phong, dsGhe, hoTenKh, sdt, soLuongGhe);
				list.add(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
