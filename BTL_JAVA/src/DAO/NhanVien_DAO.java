package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
//import connectDB.ConnectDB;
import entity.NhanVien;


public class NhanVien_DAO {
	private Connection ketNoi;
	private List<NhanVien> dsNV;
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
		ConnectDB.getInstance().connect();
		ketNoi = ConnectDB.getConnection();
	}
	
	/**
	 * Hàm thêm nhân viên khi có thông tin một nhân viên
	 * @param nv
	 * @return true nếu thêm thành công và false nếu thêm thất bại
	 */
	public boolean themNhanVien(NhanVien nv) {
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getChucVu());
			stmt.setDouble(4, nv.getLuong());
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getDiaChi());
			stmt.setString(7,  nv.getSdt());
			stmt.setDate(8,(Date) nv.getNgayVaoLam());
			stmt.setString(9, nv.getMatKhau());
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
	
	public boolean capNhat(String maNhanVien ,NhanVien nv) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update NhanVien set HoTen = ?, ChucVu = ?, Luong = ?"
					+ ",Email = ?, DiaChi = ?, Sdt = ?, NgayVaoLam = ?, MatKhau = ? where MaNhanVien = ?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getChucVu());
			stmt.setDouble(3, nv.getLuong());
			stmt.setString(4, nv.getEmail());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getSdt());
			stmt.setDate(7, (Date) nv.getNgayVaoLam());
			stmt.setString(8, nv.getMatKhau());
			stmt.setString(9, maNhanVien);
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

	public List<NhanVien> timTheoMa_VerK(String maNhanVien) {
		dsNV = new ArrayList<NhanVien>();
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "Select * from NhanVien where MaNhanVien = '" + maNhanVien +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {

				nv.setMaNV(rs.getString("MaNhanVien"));
				nv.setTenNV(rs.getString("HoTen"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setSdt(rs.getString("Sdt"));
				nv.setLuong(Double.parseDouble(rs.getString("Luong")));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setEmail(rs.getString("Email"));
				nv.setChucVu(rs.getString("ChucVu"));
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public List<NhanVien> timTheoTen_VerK(String tenNhanVien) {
		dsNV = new ArrayList<NhanVien>();
		
		PreparedStatement stmt = null;
		String sql = "Select * from NhanVien where HoTen = N'" + tenNhanVien +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNhanVien"));
				nv.setTenNV(rs.getString("HoTen"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setSdt(rs.getString("Sdt"));
				nv.setLuong(Double.parseDouble(rs.getString("Luong")));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setEmail(rs.getString("Email"));
				nv.setChucVu(rs.getString("ChucVu"));
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public List<NhanVien> timTheoChucVu_VerK(String ChucVu) {
		dsNV = new ArrayList<NhanVien>();
		
		PreparedStatement stmt = null;
		String sql = "Select * from NhanVien where ChucVu = N'"+ ChucVu +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NhanVien nv=new NhanVien();
				nv.setMaNV(rs.getString("MaNhanVien"));
				nv.setTenNV(rs.getString("HoTen"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setSdt(rs.getString("Sdt"));
				nv.setLuong(Double.parseDouble(rs.getString("Luong")));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setEmail(rs.getString("Email"));
				nv.setChucVu(rs.getString("ChucVu"));
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}

	

	public List<NhanVien> getAllNV() {
		List<NhanVien> dSNV = new ArrayList<NhanVien>();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNhanVien"));
				nv.setTenNV(rs.getString("HoTen"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setSdt(rs.getString("Sdt"));
				nv.setLuong(rs.getDouble("Luong"));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setEmail(rs.getString("Email"));
				nv.setChucVu(rs.getString("ChucVu"));
				nv.setMatKhau(rs.getString("MatKhau"));
				dSNV.add(nv);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSNV;
	}


	public boolean xoaNhanVien(String maNhanVien) {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from NhanVien where MaNhanVien = ?");
			stmt.setString(1, maNhanVien);
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
