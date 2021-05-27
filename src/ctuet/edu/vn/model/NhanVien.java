package ctuet.edu.vn.model;


public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private String sodienthoai;
	private String ngaysinh;
	private String cmnd;
	private String diachi;
	private String vitri;
	private String mucluong;
	private String ngaynhanviec;
	public NhanVien() {
		super();
	}
	public NhanVien(String maNhanVien, String tenNhanVien, String sodienthoai, String ngaysinh, String cmnd,
			String diachi, String vitri, String mucluong, String ngaynhanviec) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.sodienthoai = sodienthoai;
		this.ngaysinh = ngaysinh;
		this.cmnd = cmnd;
		this.diachi = diachi;
		this.vitri = vitri;
		this.mucluong = mucluong;
		this.ngaynhanviec = ngaynhanviec;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getVitri() {
		return vitri;
	}
	public void setVitri(String vitri) {
		this.vitri = vitri;
	}
	public String getMucluong() {
		return mucluong;
	}
	public void setMucluong(String mucluong) {
		this.mucluong = mucluong;
	}
	public String getNgaynhanviec() {
		return ngaynhanviec;
	}
	public void setNgaynhanviec(String ngaynhanviec) {
		this.ngaynhanviec = ngaynhanviec;
	}
}
