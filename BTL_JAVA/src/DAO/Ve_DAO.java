package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChiTietSuatChieu;
import entity.LichChieu;
import entity.SuatChieu;
import entity.Ve;

public class Ve_DAO {
	
	public ArrayList<ChiTietSuatChieu> getListSuatChieu(){
		Connection con;
		ArrayList<ChiTietSuatChieu> listSuatChieu = new ArrayList<ChiTietSuatChieu>();
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "select SuatChieu.MaSuatChieu ,Phim.TenPhim, LichChieu.NgayChieu, Phong.TenPhong,SuatChieu.SuatChieu "
					+ "from SuatChieu JOIN LichChieu "
					+ "on SuatChieu.MaLichChieu = LichChieu.MaLichChieu JOIN Phim "
					+ "on Phim.MaPhim = SuatChieu.MaPhim JOIN Phong "
					+ "on Phong.MaPhong = SuatChieu.MaPhong";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				String idSC = rs.getString(1);
				String tenPhim = rs.getString(2);
				java.sql.Date ngayChieu = rs.getDate(3);
				int soPhong = rs.getInt(4);
				Time suatChieu = rs.getTime(5);
				ChiTietSuatChieu sc = new ChiTietSuatChieu(idSC, tenPhim, ngayChieu, soPhong, suatChieu);
				listSuatChieu.add(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSuatChieu;
	}

	public List<ChiTietSuatChieu> timTheoNgayChieu(Date date) {
		Connection con;
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();
	    List<ChiTietSuatChieu> dsSC = new ArrayList<>();
	    PreparedStatement stmt = null;
	    String sql = "SELECT SuatChieu.MaSuatChieu, Phim.TenPhim, LichChieu.NgayChieu, Phong.soPhong, SuatChieu.SuatChieu " +
	                 "FROM SuatChieu " +
	                 "JOIN LichChieu ON SuatChieu.MaLichChieu = LichChieu.MaLichChieu " +
	                 "JOIN Phim ON Phim.MaPhim = SuatChieu.MaPhim " +
	                 "JOIN Phong ON Phong.MaPhong = SuatChieu.MaPhong " +
	                 "WHERE NgayChieu = ? ";
	    try {
	        stmt = con.prepareStatement(sql);
	        stmt.setDate(1,(java.sql.Date) date); // Chuyển đổi từ java.util.Date sang java.sql.Date
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	            String maSC = rs.getString(1);
	            String phim = rs.getString(2);
	            Date ngayChieu = rs.getDate(3); // Đây là java.sql.Date, không cần chuyển đổi
	            int phong = rs.getInt(4);
	            Time sc = rs.getTime(5);
	            ChiTietSuatChieu ctsc = new ChiTietSuatChieu(maSC, phim, ngayChieu, phong, sc);
	            dsSC.add(ctsc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsSC;
	}
}


