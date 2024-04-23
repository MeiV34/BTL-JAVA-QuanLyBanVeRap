package entity;

import java.util.Objects;

public class Ve {
	private String maVe;
	private SuatChieu suatchieu;
	private KhachHang khachHang;
	private String soGhe;
	private Double giaVe;
	private boolean trangThai;
	public Ve(String maVe, SuatChieu suatchieu, KhachHang khachHang, String soGhe, Double giaVe, boolean trangThai) {
		super();
		this.maVe = maVe;
		this.suatchieu = suatchieu;
		this.khachHang = khachHang;
		this.soGhe = soGhe;
		this.giaVe = giaVe;
		this.trangThai = trangThai;
	}
	public Ve(String maVe) {
		super();
		this.maVe = maVe;
	}
	public Ve(SuatChieu suatchieu) {
		super();
		this.suatchieu = suatchieu;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public SuatChieu getSuatchieu() {
		return suatchieu;
	}
	public void setSuatchieu(SuatChieu suatchieu) {
		this.suatchieu = suatchieu;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public String getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(String soGhe) {
		this.soGhe = soGhe;
	}
	public Double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(Double giaVe) {
		this.giaVe = giaVe;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(maVe, other.maVe);
	}

	
	
}