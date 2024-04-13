package entity;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Phim {
	private String idPhim;
	private String tenPhim;
	private String daoDien;
	private String theLoai;
	private int thoiLuong;
	private Date ngayCC;
	private Date ngayKT;
	private int danhGia;
	
	public Phim() {
		super();
	}

	public Phim(String idPhim) {
		super();
		this.idPhim = idPhim;
	}

	public Phim(String idPhim, String tenPhim, String daoDien, String theLoai, int thoiLuong, Date ngayCC,
			Date ngayKT, int danhGia) {
		super();
		this.idPhim = idPhim;
		this.tenPhim = tenPhim;
		this.daoDien = daoDien;
		this.theLoai = theLoai;
		this.thoiLuong = thoiLuong;
		this.ngayCC = ngayCC;
		this.ngayKT = ngayKT;
		this.danhGia = danhGia;
	}

	public String getIdPhim() {
		return idPhim;
	}

	public void setIdPhim(String idPhim) {
		this.idPhim = idPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public Date getNgayCC() {
		return ngayCC;
	}

	public void setNgayCC(Date ngayCC) {
		this.ngayCC = ngayCC;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public int getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(int danhGia) {
		this.danhGia = danhGia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPhim);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phim other = (Phim) obj;
		return Objects.equals(idPhim, other.idPhim);
	}
	
	
	
}
