package entity;

import java.sql.Date;
import java.util.Objects;

public class LichChieu {
	private String maLichChieu;
	private Date ngayChieu;
	public LichChieu() {
		super();
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
	@Override
	public int hashCode() {
		return Objects.hash(maLichChieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichChieu other = (LichChieu) obj;
		return Objects.equals(maLichChieu, other.maLichChieu);
	}
	
}
