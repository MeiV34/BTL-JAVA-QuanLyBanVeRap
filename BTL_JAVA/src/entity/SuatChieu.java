package entity;

import java.sql.Time;
import java.util.Objects;

public class SuatChieu {
	private String maSC;
	private LichChieu lichChieu;
	private Phong phong;
	private Phim phim;
	private Time SuatChieu;
	public SuatChieu(String maSC, LichChieu lichChieu, Phong phong, Phim phim, Time suatChieu) {
		super();
		this.maSC = maSC;
		this.lichChieu = lichChieu;
		this.phong = phong;
		this.phim = phim;
		SuatChieu = suatChieu;
	}
	
	public SuatChieu(Phong phong) {
		super();
		this.phong = phong;
	}

	public SuatChieu(Phim phim) {
		super();
		this.phim = phim;
	}

	public SuatChieu(LichChieu lichChieu) {
		super();
		this.lichChieu = lichChieu;
	}

	public String getMaSC() {
		return maSC;
	}
	public void setMaSC(String maSC) {
		this.maSC = maSC;
	}
	public LichChieu getLichChieu() {
		return lichChieu;
	}
	public void setLichChieu(LichChieu lichChieu) {
		this.lichChieu = lichChieu;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public Phim getPhim() {
		return phim;
	}
	public void setPhim(Phim phim) {
		this.phim = phim;
	}
	public Time getSuatChieu() {
		return SuatChieu;
	}
	public void setSuatChieu(Time suatChieu) {
		SuatChieu = suatChieu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSC);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuatChieu other = (SuatChieu) obj;
		return Objects.equals(maSC, other.maSC);
	}

	
	
}
