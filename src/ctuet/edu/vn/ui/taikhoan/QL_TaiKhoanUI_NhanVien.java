package ctuet.edu.vn.ui.taikhoan;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QL_TaiKhoanUI_NhanVien extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txtMatKhauCu,txtMatKhauMoi1,txtMatKhauMoi2;
	JButton btnLuu,btnDangXuat;
	
	public QL_TaiKhoanUI_NhanVien(String username) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}

	private void addEvents() {
		
	}

	private void addControls() {
		
		JPanel pnMatKhauCu = new JPanel();
		pnMatKhauCu.setLayout(new FlowLayout());
		this.add(pnMatKhauCu);
		JLabel lblMauKhauCu = new JLabel("Nhập mật khẩu củ");
		txtMatKhauCu = new JTextField(20);
		pnMatKhauCu.add(lblMauKhauCu);
		pnMatKhauCu.add(txtMatKhauCu);
		
		JPanel pnMatKhauMoi1 = new JPanel();
		pnMatKhauMoi1.setLayout(new FlowLayout());
		this.add(pnMatKhauMoi1);
		JLabel lblMatKhauMoi1 = new JLabel("Nhập mật khẩu mới");
		txtMatKhauMoi1 = new JTextField(20);
		pnMatKhauMoi1.add(lblMatKhauMoi1);
		pnMatKhauMoi1.add(txtMatKhauMoi1);
		
		JPanel pnMatKhauMoi2 = new JPanel();
		pnMatKhauMoi2.setLayout(new FlowLayout());
		this.add(pnMatKhauMoi2);
		JLabel lblMauKhauMoi2 = new JLabel("Nhập lại mật khẩu mới");
		txtMatKhauMoi2 = new JTextField(20);
		pnMatKhauMoi2.add(lblMauKhauMoi2);
		pnMatKhauMoi2.add(txtMatKhauMoi2);
	
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		this.add(pnButton);
		btnLuu = new JButton("Lưu mật khẩu mới");
		btnDangXuat = new JButton("Đăng xuất");
		pnButton.add(btnLuu);
		pnButton.add(btnDangXuat);
		
		lblMauKhauCu.setPreferredSize(lblMauKhauMoi2.getPreferredSize());
		lblMatKhauMoi1.setPreferredSize(lblMauKhauMoi2.getPreferredSize());
		
	}
}
