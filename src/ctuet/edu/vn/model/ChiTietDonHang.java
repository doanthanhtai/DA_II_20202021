package ctuet.edu.vn.model;

public class ChiTietDonHang {
	private String maDonHang;
	private String maSanPham;
	private int soluong;
	private long giatri;
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public long getGiatri() {
		return giatri;
	}
	public void setGiatri(long giatri) {
		this.giatri = giatri;
	}
	public ChiTietDonHang(String maDonHang, String maSanPham, int soluong, long giatri) {
		super();
		this.maDonHang = maDonHang;
		this.maSanPham = maSanPham;
		this.soluong = soluong;
		this.giatri = giatri;
	}
	public ChiTietDonHang() {
		super();
	}
	
}
