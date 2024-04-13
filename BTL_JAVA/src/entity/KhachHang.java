package entity;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String sdt;
	
	public KhachHang(String maKH, String hoTen, String sdt) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sdt = sdt;
	}
	
	public KhachHang(String maKH) {
		this.maKH=maKH;
	}
	
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	
}
