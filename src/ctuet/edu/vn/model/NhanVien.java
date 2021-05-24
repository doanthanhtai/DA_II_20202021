package ctuet.edu.vn.model;


public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private String sodienthoai;
	private int ngaysinh;
	private String cmnd;
	private String diachi;
	private String vitri;
	private float mucluong;
	private String ngaynhanviec;
	private String hinhanh;
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public NhanVien() {
		super();
	}
	public NhanVien(String maNhanVien, String tenNhanVien, String sodienthoai, Integer ngaysinh, String cmnd,
			String diachi, String vitri, long mucluong, String ngaynhanviec, String hinhanh) {
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
		this.hinhanh = hinhanh;
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
	public int getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Integer ngaysinh) {
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
	public float getMucluong() {
		return mucluong;
	}
	public void setMucluong(Float mucluong) {
		this.mucluong = mucluong;
	}
	public String getNgaynhanviec() {
		return ngaynhanviec;
	}
	public void setNgaynhanviec(String ngaynhanviec) {
		this.ngaynhanviec = ngaynhanviec;
	}
	public String getHinhanh() {
		return hinhanh;
	}
	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}
}
