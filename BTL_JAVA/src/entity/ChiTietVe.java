package entity;

import java.sql.Date;
import java.sql.Time;

public class ChiTietVe {
	private String maVe;
	private String tenPhim;
	private Date ngayChieu;
	private Time suatChieu;
	private String tenPhong;
	private String dsGhe;
	private String tenKH;
	private String sdt;
	private int soLuongGhe;
	
	
	public ChiTietVe(String maVe, String tenPhim, Date ngayChieu, Time suatChieu, String tenPhong, String dsGhe,
			String tenKH, String sdt, int soLuongGhe) {
		super();
		this.maVe = maVe;
		this.tenPhim = tenPhim;
		this.ngayChieu = ngayChieu;
		this.suatChieu = suatChieu;
		this.tenPhong = tenPhong;
		this.dsGhe = dsGhe;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.soLuongGhe = soLuongGhe;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public String getTenPhim() {
		return tenPhim;
	}
	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}
	public Date getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public Time getSuatChieu() {
		return suatChieu;
	}
	public void setSuatChieu(Time suatChieu) {
		this.suatChieu = suatChieu;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public String getDsGhe() {
		return dsGhe;
	}
	public void setDsGhe(String dsGhe) {
		this.dsGhe = dsGhe;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getSoLuongGhe() {
		return soLuongGhe;
	}
	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	
}
