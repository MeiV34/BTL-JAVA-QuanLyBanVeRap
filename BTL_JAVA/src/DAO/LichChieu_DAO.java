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
import entity.NhanVien;

public class LichChieu_DAO {
	private Connection ketNoi;
	private List<LichChieu> dsLC;
	public LichChieu_DAO() {
		// TODO Auto-generated constructor stub
		ConnectDB.getInstance().connect();
		ketNoi = ConnectDB.getConnection();
	}

	public boolean themLichChieu(LichChieu lc) {
		String sql = "insert into LichChieu values(?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, lc.getMaLichChieu());
			stmt.setDate(2,(Date) lc.getNgayChieu());
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

	public boolean capNhat(String maLichChieu ,LichChieu lc) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update LichChieu set NgayChieu = ? where MaLichChieu = ?");
			stmt.setDate(1, (Date) lc.getNgayChieu());
			stmt.setString(2, maLichChieu);
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
	
	public List<LichChieu> timTheoMa(String maLC) {
		dsLC = new ArrayList<LichChieu>();
		LichChieu lc=new LichChieu();
		PreparedStatement stmt = null;
		String sql = "Select * from LichChieu where MaLichChieu = '" + maLC +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {

				lc.setMaLichChieu(rs.getString("MaLichChieu"));
				lc.setNgayChieu(rs.getDate("NgayChieu"));
				dsLC.add(lc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLC;
	}

	public List<LichChieu> getAllLC() {
		List<LichChieu> dsLC = new ArrayList<LichChieu>();
		PreparedStatement stmt = null;
		String sql = "select * from LichChieu";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LichChieu lc = new LichChieu();
				lc.setMaLichChieu(rs.getString("MaLichChieu"));
				lc.setNgayChieu(rs.getDate("NgayChieu"));
				dsLC.add(lc);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLC;
	}

	public boolean xoaLichChieu(String maLC) {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from LichChieu where MaLichChieu = ?");
			stmt.setString(1, maLC);
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
