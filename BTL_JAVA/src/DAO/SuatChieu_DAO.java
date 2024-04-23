package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LichChieu;
import entity.Phim;
import entity.Phong;
//import entity.SuatChieu;
import entity.SuatChieu;

public class SuatChieu_DAO {
	private Connection ketNoi;
	private List<SuatChieu> dsSC;
	public SuatChieu_DAO() {
		// TODO Auto-generated constructor stub
		ConnectDB.getInstance().connect();
		ketNoi = ConnectDB.getConnection();
	}

	public boolean themSuatChieu(SuatChieu sc) {
		String sql = "insert into SuatChieu values(?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, sc.getMaSC());
			stmt.setString(2, sc.getLichChieu().getMaLichChieu());
			stmt.setString(3, sc.getLichChieu().getMaLichChieu());
			stmt.setString(4, sc.getLichChieu().getMaLichChieu());
			stmt.setTime(5,sc.getSuatChieu());
			try {
				n = stmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean capNhat(String maSuatChieu ,SuatChieu sc) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update SuatChieu set MaLichChieu = ?,MaPhong = ?,"
					+ "MaPhim = ?,SuatChieu = ?, where MaSuatChieu = ?");
			stmt.setString(1, sc.getLichChieu().getMaLichChieu());
			stmt.setString(1, sc.getPhong().getMaPhong());
			stmt.setString(1, sc.getPhim().getIdPhim());
			stmt.setString(2, maSuatChieu);
			n = stmt.executeUpdate();
		}  catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}
	
	public List<SuatChieu> timTheoMa(String maSC) {
		dsSC = new ArrayList<SuatChieu>();
		SuatChieu sc=new SuatChieu();
		PreparedStatement stmt = null;
		String sql = "Select * from SuatChieu where MaSuatChieu = '" + maSC +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {

				sc.setMaSC(rs.getString("MaSuatChieu"));
				LichChieu lc=new LichChieu(rs.getString("MaLichChieu"));
				Phong p=new Phong(rs.getString("MaPhong"));
				Phim phim=new Phim(rs.getString("MaPhim"));
				sc.setLichChieu(lc);
				sc.setPhong(p);
				sc.setPhim(phim);
				sc.setSuatChieu(rs.getTime("SuatChieu"));
				dsSC.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSC;
	}

	public List<SuatChieu> getAllSC() {
		List<SuatChieu> dsSC = new ArrayList<SuatChieu>();
		PreparedStatement stmt = null;
		String sql = "SELECT sc.MaSuatChieu AS MaSuatChieu,lc.MaLichChieu AS MaLichChieu,p.MaPhong AS MaPhong,\r\n"
				+ "f.MaPhim AS MaPhim,lc.NgayChieu \r\n"
				+ "AS NgayChieu, p.soPhong AS tenPhong, f.TenPhim \r\n"
				+ "AS tenPhim , sc.SuatChieu as GioChieu\r\n"
				+ "FROM SuatChieu sc\r\n"
				+ "JOIN LichChieu lc ON sc.MaLichChieu = lc.MaLichChieu \r\n"
				+ "JOIN Phong p ON sc.MaPhong = p.MaPhong\r\n"
				+ "JOIN Phim f ON sc.MaPhim = f.MaPhim";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SuatChieu sc = new SuatChieu();
				sc.setMaSC(rs.getString("MaSuatChieu"));
				LichChieu lc=new LichChieu(rs.getString("MaLichChieu"));
				lc.setNgayChieu(rs.getDate("NgayChieu"));
				Phong p=new Phong(rs.getString("MaPhong"));
				p.setTenPhong(rs.getString("tenPhong"));
				Phim phim=new Phim(rs.getString("MaPhim"));
				phim.setTenPhim(rs.getString("tenPhim"));
				sc.setLichChieu(lc);
				sc.setPhong(p);
				sc.setPhim(phim);
				sc.setSuatChieu(rs.getTime("GioChieu"));
				dsSC.add(sc);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSC;
	}

	public boolean xoaSuatChieu(String maSC) {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from SuatChieu where MaSuatChieu = ?");
			stmt.setString(1, maSC);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return false;
	}
}
