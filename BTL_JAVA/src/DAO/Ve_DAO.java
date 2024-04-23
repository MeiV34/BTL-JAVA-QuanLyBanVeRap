package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChiTietSuatChieu;
import entity.SuatChieu;
import entity.Ve;

public class Ve_DAO {
	private Connection con;
	public ArrayList<ChiTietSuatChieu> getListSuatChieu(){
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
				// Chuyển đổi java.sql.Date thành java.util.Date
				java.sql.Date nagyChieu = rs.getDate(3);
				int soPhong = rs.getInt(4);
				Time suatChieu = rs.getTime(5);
				ChiTietSuatChieu sc = new ChiTietSuatChieu(idSC, tenPhim, nagyChieu, soPhong, suatChieu);
				listSuatChieu.add(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSuatChieu;
	}
	
}


