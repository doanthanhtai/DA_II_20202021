package ctuet.edu.vn.model;

public class TaiKhoan {
	private String username;
	private String password;
	private String vaitro;
	private String maNhanVien;
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String username, String password, String vaitro, String maNhanVien) {
		super();
		this.username = username;
		this.password = password;
		this.vaitro = vaitro;
		this.maNhanVien = maNhanVien;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVaitro() {
		return vaitro;
	}
	public void setVaitro(String vaitro) {
		this.vaitro = vaitro;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
}
