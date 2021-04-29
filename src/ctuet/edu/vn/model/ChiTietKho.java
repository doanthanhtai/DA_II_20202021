package ctuet.edu.vn.model;

public class ChiTietKho {
	private String maKho;
	private String maSanPham;
	private float soluong;
	public ChiTietKho() {
		super();
	}
	public ChiTietKho(String maKho, String maSanPham, float soluong) {
		super();
		this.maKho = maKho;
		this.maSanPham = maSanPham;
		this.soluong = soluong;
	}
	public String getMaKho() {
		return maKho;
	}
	public void setMaKho(String maKho) {
		this.maKho = maKho;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public float getSoluong() {
		return soluong;
	}
	public void setSoluong(float soluong) {
		this.soluong = soluong;
	}
}
