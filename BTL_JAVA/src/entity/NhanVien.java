package entity;

import java.sql.Date;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private Date ngaySinh;
	private String sdt;
	private Double luong;
	private String diaChi;
	private String email;
	private String chucVu;
	private String tenDN;
	private String matKhau;
	public NhanVien() {
		super();
	}
	public NhanVien(String maNV, String tenNV, Date ngaySinh, String sdt, Double luong, String diaChi, String email,
			String chucVu, String tenDN, String matKhau) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.sdt = sdt;
		this.luong = luong;
		this.diaChi = diaChi;
		this.email = email;
		this.chucVu = chucVu;
		this.tenDN = tenDN;
		this.matKhau = matKhau;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public Double getLuong() {
		return luong;
	}
	public void setLuong(Double luong) {
		this.luong = luong;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", luong="
				+ luong + ", diaChi=" + diaChi + ", email=" + email + ", chucVu=" + chucVu + ", tenDN=" + tenDN
				+ ", matKhau=" + matKhau + "]";
	}
	
	
}
