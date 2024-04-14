package entity;

import java.sql.Date;

public class LichChieu {
	private String maLichChieu;
	private Phim phim;
	private Date ngayChieu;
	private Date suatChieu;
	public LichChieu() {
		super();
	}
	public LichChieu(String maLichChieu, Phim phim, Date ngayChieu, Date suatChieu) {
		super();
		this.maLichChieu = maLichChieu;
		this.phim = phim;
		this.ngayChieu = ngayChieu;
		this.suatChieu = suatChieu;
	}
	public String getMaLichChieu() {
		return maLichChieu;
	}
	public void setMaLichChieu(String maLichChieu) {
		this.maLichChieu = maLichChieu;
	}
	public Phim getPhim() {
		return phim;
	}
	public void setPhim(Phim phim) {
		this.phim = phim;
	}
	public Date getNgayChieu() {
		return ngayChieu;
	}
	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public Date getSuatChieu() {
		return suatChieu;
	}
	public void setSuatChieu(Date suatChieu) {
		this.suatChieu = suatChieu;
	}
	@Override
	public String toString() {
		return "LichChieu [maLichChieu=" + maLichChieu + ", phim=" + phim + ", ngayChieu=" + ngayChieu + ", suatChieu="
				+ suatChieu + "]";
	}
	
}
