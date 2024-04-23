package entity;

import java.sql.Date;

public class LichChieu {
	private String maLichChieu;
	private Date ngayChieu;
	public LichChieu() {
		super();
	}
	
	public LichChieu(String ma) {
		super();
		this.maLichChieu=ma;
	}
	public LichChieu(String maLichChieu, Date ngayChieu) {
		super();
		this.maLichChieu = maLichChieu;
		this.ngayChieu = ngayChieu;
	}
	public String getMaLichChieu() {
		return maLichChieu;
	}
	public void setMaLichChieu(String maLichChieu) {
		this.maLichChieu = maLichChieu;
	}
	public Date getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	
	
}
