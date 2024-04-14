package entity;

public class RapPhim {
	private String maRapPhim;
	private String tenRap;
	private String diaChi;
	public RapPhim() {
		super();
	}
	public RapPhim(String maRapPhim, String tenRap, String diaChi) {
		super();
		this.maRapPhim = maRapPhim;
		this.tenRap = tenRap;
		this.diaChi = diaChi;
	}
	public String getMaRapPhim() {
		return maRapPhim;
	}
	public void setMaRapPhim(String maRapPhim) {
		this.maRapPhim = maRapPhim;
	}
	public String getTenRap() {
		return tenRap;
	}
	public void setTenRap(String tenRap) {
		this.tenRap = tenRap;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "RapPhim [maRapPhim=" + maRapPhim + ", tenRap=" + tenRap + ", diaChi=" + diaChi + "]";
	}
	
}
