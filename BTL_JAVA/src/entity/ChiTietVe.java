package entity;

public class ChiTietVe {
	private Ve ve;
	private NhanVien nhanVien;
	private Double tongTien;
	private int soLuongVe;
	
	public ChiTietVe(Ve ve) {
		super();
		this.ve = ve;
	}
	public ChiTietVe() {
		super();
	}
	public ChiTietVe(Ve ve, NhanVien nhanVien, Double tongTien, int soLuongVe) {
		super();
		this.ve = ve;
		this.nhanVien = nhanVien;
		this.tongTien = tongTien;
		this.soLuongVe = soLuongVe;
	}
	public Ve getVe() {
		return ve;
	}
	public void setVe(Ve ve) {
		this.ve = ve;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public Double getTongTien() {
		return tongTien;
	}
	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}
	public int getSoLuongVe() {
		return soLuongVe;
	}
	public void setSoLuongVe(int soLuongVe) {
		this.soLuongVe = soLuongVe;
	}
	
}
