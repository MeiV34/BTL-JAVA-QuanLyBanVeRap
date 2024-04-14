package entity;

public class Phong {
    private String maPhong;
    private int soPhong;
	public Phong() {
		super();
	}
	public Phong(String maPhong, int soPhong) {
		super();
		this.maPhong = maPhong;
		this.soPhong = soPhong;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public int getSoPhong() {
		return soPhong;
	}
	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", soPhong=" + soPhong + "]";
	}
    
}
