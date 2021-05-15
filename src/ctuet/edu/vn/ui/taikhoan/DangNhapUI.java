package ctuet.edu.vn.ui.taikhoan;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ctuet.edu.vn.model.TaiKhoan;
import ctuet.edu.vn.service.TaiKhoanService;
import ctuet.edu.vn.ui.QL_MenuUI;

public class DangNhapUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txtUsername;
	JButton btnThoat,btnDangNhap;
	JPasswordField psdPassword;

	TaiKhoanService svTaiKhoan = new TaiKhoanService();

	public DangNhapUI(String title) {
		super(title);
		addControls();
		addEvents();
		showWindow();
	}

	private void showWindow() {
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,300);
		this.setAlwaysOnTop(true);
	}

	private void addEvents() {
		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnDangNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String username = txtUsername.getText().toString();
				@SuppressWarnings("deprecation")
				String password = psdPassword.getText().toString();
				if(username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Không được bỏ trống Username hoặc Password!");	
				}else {
					TaiKhoan taikhoan = new TaiKhoan();
					taikhoan.setUsername(username);
					taikhoan.setPassword(password);
					if(svTaiKhoan.kiemtraDangNhap(taikhoan) == true) {
						new QL_MenuUI("Phần mềm quản lý shop thời trang", username);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Username hoăc Password không đúng!");
					}
				}
			}
		});
	}

	private void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout());
		JLabel lblTitle = new JLabel("PHẦN MỀM QUẢN LÝ SHOP THỜI TRANG");
		Font fontTitle = new Font("Cambria", 1, 24);
		lblTitle.setFont(fontTitle);
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		pnTitle.add(lblTitle);
		pnMain.add(pnTitle);

		JPanel pnUsername = new JPanel();
		pnUsername.setLayout(new FlowLayout());
		pnMain.add(pnUsername);
		JLabel lblUsername = new JLabel("Username");
		txtUsername = new JTextField(20);
		pnUsername.add(lblUsername);
		pnUsername.add(txtUsername);

		JPanel pnPassword = new JPanel();
		pnPassword.setLayout(new FlowLayout());
		pnMain.add(pnPassword);
		JLabel lblPassword = new JLabel("Password");
		psdPassword = new JPasswordField(20);
		pnPassword.add(lblPassword);
		pnPassword.add(psdPassword);

		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		pnMain.add(pnButton);
		btnThoat = new JButton("Thoát");
		btnDangNhap = new JButton("Đăng nhập");
		pnButton.add(btnThoat);
		pnButton.add(btnDangNhap);


	}
}
