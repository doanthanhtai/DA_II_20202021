package ctuet.edu.vn.model;

public class SanPham {
	
	private String maSanPham;
	private String tenSanPham;
	private String mauSac;
	private int kichthuoc;
	private String nguoncungcap;
	private int giaban;
	private String hinhanh;

	public SanPham() {
		super();
	}

	public SanPham(String maSanPham, String tenSanPham, String mauSac, int kichthuoc, String nguoncungcap, int giaban,
			String hinhanh) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.mauSac = mauSac;
		this.kichthuoc = kichthuoc;
		this.nguoncungcap = nguoncungcap;
		this.giaban = giaban;
		this.hinhanh = hinhanh;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getKichthuoc() {
		return kichthuoc;
	}

	public void setKichthuoc(int kichthuoc) {
		this.kichthuoc = kichthuoc;
	}

	public String getNguoncungcap() {
		return nguoncungcap;
	}

	public void setNguoncungcap(String nguoncungcap) {
		this.nguoncungcap = nguoncungcap;
	}

	public int getGiaban() {
		return giaban;
	}

	public void setGiaban(int giaban) {
		this.giaban = giaban;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

}
