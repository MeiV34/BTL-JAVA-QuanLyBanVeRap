package entity;

public class Ghe {
	private String maGhe;
	private String soGhe;
	private boolean trangThaiGhe;
	private int soLuong;
	public Ghe() {
		super();
	}
	public Ghe(String maGhe, String soGhe, boolean trangThaiGhe, int soLuong) {
		super();
		this.maGhe = maGhe;
		this.soGhe = soGhe;
		this.trangThaiGhe = trangThaiGhe;
		this.soLuong = soLuong;
	}
	public String getMaGhe() {
		return maGhe;
	}
	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}
	public String getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(String soGhe) {
		this.soGhe = soGhe;
	}
	public boolean isTrangThaiGhe() {
		return trangThaiGhe;
	}
	public void setTrangThaiGhe(boolean trangThaiGhe) {
		this.trangThaiGhe = trangThaiGhe;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + ", soGhe=" + soGhe + ", trangThaiGhe=" + trangThaiGhe + ", soLuong=" + soLuong
				+ "]";
	}
	
}
