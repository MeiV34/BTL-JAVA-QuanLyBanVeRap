package entity;

import java.sql.Time;
import java.util.Date;

public class ChiTietSuatChieu {
	private String maSC;
	private String phim;
	private Date ngayChieu;
	private String phong;
	private Time sc;
	
	public ChiTietSuatChieu() {
		super();
	}
	public ChiTietSuatChieu(String suatChieu, String phim, Date ngayChieu, String phong, Time sc) {
		super();
		this.maSC = suatChieu;
		this.phim = phim;
		this.ngayChieu = ngayChieu;
		this.phong = phong;
		this.sc = sc;
	}
	public String getMaSC() {
		return maSC;
	}
	public void setMaSC(String suatChieu) {
		this.maSC = suatChieu;
	}
	public String getPhim() {
		return phim;
	}
	public void setPhim(String phim) {
		this.phim = phim;
	}
	public Date getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public String getPhong() {
		return phong;
	}
	public void setPhong(String phong) {
		this.phong = phong;
	}
	public Time getSc() {
		return sc;
	}
	public void setSc(Time sc) {
		this.sc = sc;
	}
	
	
}
