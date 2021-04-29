package ctuet.edu.vn.model;

import java.sql.Date;

public class DonHang {
	
	private String maDonHang;
	private String maKhachHang;
	private String maNhanVien;
	private Date ngaytao;
	private int trangthai;
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public Date getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	public DonHang(String maDonHang, String maKhachHang, String maNhanVien, Date ngaytao, int trangthai) {
		super();
		this.maDonHang = maDonHang;
		this.maKhachHang = maKhachHang;
		this.maNhanVien = maNhanVien;
		this.ngaytao = ngaytao;
		this.trangthai = trangthai;
	}
	public DonHang() {
		super();
	}
	
}
