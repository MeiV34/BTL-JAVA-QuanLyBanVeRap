package entity;

public class KhachHang {
	private int maKH;
	private String hoTen;
	private String email;
	private String sdt;
	
	public KhachHang(int maKH, String hoTen, String email, String sdt) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.email = email;
		this.sdt = sdt;
	}

	public KhachHang(int maKH) {
		this.maKH=maKH;
	}
	
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}	
}
