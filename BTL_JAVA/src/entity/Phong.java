package entity;

import java.util.Objects;

public class Phong {
    private String maPhong;
    private int tenPhong;
    private int soLuongGhe;
    
	public Phong() {
		super();
	}
	public Phong(String maPhong, int tenPhong, int soLuongGhe) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.soLuongGhe = soLuongGhe;
	}
	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public int getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(int tenPhong) {
		this.tenPhong = tenPhong;
	}
	public int getSoLuongGhe() {
		return soLuongGhe;
	}
	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}
	
	
}
