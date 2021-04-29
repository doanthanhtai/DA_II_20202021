package ctuet.edu.vn.ui.taikhoan;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class QL_TaiKhoanUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QL_TaiKhoanUI(String username) {
		this.setLayout(new BorderLayout());
		if(username == "admin") {
			this.add(new QL_TaiKhoanUI_ChuShop(username),BorderLayout.CENTER);
		}else {
			this.add(new QL_TaiKhoanUI_NhanVien(username),BorderLayout.CENTER);
		}	
	}
}
