package ctuet.edu.vn.model;

public class Kho {
	private String tenKho;
	private String maKho;
	private String diaChi;
	private int trangthai;
	public Kho() {
		super();
	}
	public Kho(String tenKho, String maKho, String diaChi, int trangthai) {
		super();
		this.tenKho = tenKho;
		this.maKho = maKho;
		this.diaChi = diaChi;
		this.trangthai = trangthai;
	}
	public String getTenKho() {
		return tenKho;
	}
	public void setTenKho(String tenKho) {
		this.tenKho = tenKho;
	}
	public String getMaKho() {
		return maKho;
	}
	public void setMaKho(String maKho) {
		this.maKho = maKho;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
}
