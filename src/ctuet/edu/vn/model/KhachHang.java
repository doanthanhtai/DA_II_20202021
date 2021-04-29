package ctuet.edu.vn.model;

public class KhachHang {
	private String maKhachHang;
	private String tenKhachHang;
	private String sodienthoai;
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public KhachHang(String maKhachHang, String tenKhachHang, String sodienthoai) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sodienthoai = sodienthoai;
	}
	public KhachHang() {
		super();
	}
	
}
