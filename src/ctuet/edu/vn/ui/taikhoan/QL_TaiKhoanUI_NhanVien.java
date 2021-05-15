package ctuet.edu.vn.ui.taikhoan;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ctuet.edu.vn.model.TaiKhoan;
import ctuet.edu.vn.service.TaiKhoanService;

public class QL_TaiKhoanUI_NhanVien extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txtMatKhauCu,txtMatKhauMoi1,txtMatKhauMoi2;
	JButton btnLuu,btnDangXuat;

	TaiKhoanService svTaiKhoan = new TaiKhoanService();
	TaiKhoan taikhoan = new TaiKhoan();

	public QL_TaiKhoanUI_NhanVien(String username) {
		taikhoan.setUsername(username);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DangNhapUI("Đăng nhập");
			}

		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtMatKhauMoi1.getText().equals(txtMatKhauMoi2.getText()) && (txtMatKhauMoi1.getText().equals("") == false)) {
					taikhoan.setPassword(txtMatKhauCu.getText().toString().trim());
					if(svTaiKhoan.kiemtraDangNhap(taikhoan)) {
						taikhoan.setPassword(txtMatKhauMoi1.getText().toString().trim());
						svTaiKhoan.doiMatKhau(taikhoan);
						JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!");
					}else {
						JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp hoặc còn trống!");
				}
			}
		});
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
