package ctuet.edu.vn.ui.taikhoan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.NhanVien;
import ctuet.edu.vn.model.TaiKhoan;
import ctuet.edu.vn.service.NhanVienService;
import ctuet.edu.vn.service.TaiKhoanService;

public class QL_TaiKhoanUI_ChuShop extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel dtmDanhSachTaiKhoan;
	JButton btnThem,btnLuu,btnXoa,btnDangXuat;
	JTable tblDanhSachTaiKhoan;
	JComboBox<String> cboMaNhanVien;

	JTextField txtUsername,txtPassword;
	TaiKhoanService svTaiKhoan = new TaiKhoanService();
	TaiKhoan taikhoan = new TaiKhoan();
	NhanVienService svNhanVien = new NhanVienService();
	
	public QL_TaiKhoanUI_ChuShop(String username) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}
	private void hienDanhSachMaNhanVien() {
		for(NhanVien nhanvien : svNhanVien.layToanBoNhanVien()) {
			cboMaNhanVien.addItem(nhanvien.getMaNhanVien().trim());
		}
	}

	private void hienDanhSachTaiKhoan() {
		dtmDanhSachTaiKhoan.setRowCount(0);
		for(TaiKhoan taikhoan : svTaiKhoan.layToanBoTaiKhoan()) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(taikhoan.getUsername());
			vec.add(taikhoan.getPassword());
			vec.add(taikhoan.getMaNhanVien());
			dtmDanhSachTaiKhoan.addRow(vec);

		}
	}

	private void addEvents() {

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taikhoan.setUsername(txtUsername.getText().toString().trim());
				taikhoan.setPassword(txtPassword.getText().toString().trim());
				taikhoan.setVaitro("nhanvien");
				if(cboMaNhanVien.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên sử hữu tài khoản này!");
				}else if(taikhoan.getUsername().equals("") || taikhoan.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Username và password không được bỏ trống!");
				}else if(svTaiKhoan.kiemtraTonTai(taikhoan)){
					JOptionPane.showMessageDialog(null, "Username đã tồn tại!");
				}else {
					taikhoan.setMaNhanVien(cboMaNhanVien.getSelectedItem().toString().trim());
					svTaiKhoan.themTaiKhoan(taikhoan);
					JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
					hienDanhSachTaiKhoan();
				}
				

			}
		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taikhoan.setUsername(txtUsername.getText().toString().trim());
				taikhoan.setPassword(txtPassword.getText().toString().trim());
				taikhoan.setMaNhanVien(cboMaNhanVien.getSelectedItem().toString().trim());
				//Nếu password hoặc username bị trống
				if(taikhoan.getUsername().equals("") || taikhoan.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Username và password không được bỏ trống!");
				}else if(svTaiKhoan.kiemtraTonTai(taikhoan) == false){
					JOptionPane.showMessageDialog(null, "Username chưa tồn tại,vui lòng nhấn Thêm để thêm mới!");
				}else {
					svTaiKhoan.capnhatTaiKhoan(taikhoan);			
					JOptionPane.showMessageDialog(null, "Cập nhật thông tin tài khoản thành công!");
					hienDanhSachTaiKhoan();
				}

			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachTaiKhoan.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để xóa!");
				}else {
					if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản không!","Xác nhận xóa",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						taikhoan.setUsername(tblDanhSachTaiKhoan.getValueAt(tblDanhSachTaiKhoan.getSelectedRow(), 0).toString().trim());
						svTaiKhoan.xoaTaiKhoan(taikhoan);
						hienDanhSachTaiKhoan();
					}
				}
			}
		});
		tblDanhSachTaiKhoan.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText(tblDanhSachTaiKhoan.getValueAt(tblDanhSachTaiKhoan.getSelectedRow(), 0).toString().trim());
				txtPassword.setText(tblDanhSachTaiKhoan.getValueAt(tblDanhSachTaiKhoan.getSelectedRow(), 1).toString().trim());
				cboMaNhanVien.setSelectedItem(tblDanhSachTaiKhoan.getValueAt(tblDanhSachTaiKhoan.getSelectedRow(), 2).toString().trim());
			}
		});
		btnDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DangNhapUI("Đăng nhập");
			}
		});

	}
	private void addControls() {
		/*----------------------Center----------------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		this.add(pnCenter);
		//Bảng danh sách đơn hàng
		dtmDanhSachTaiKhoan = new DefaultTableModel();
		dtmDanhSachTaiKhoan.addColumn("Username");
		dtmDanhSachTaiKhoan.addColumn("Password");
		dtmDanhSachTaiKhoan.addColumn("Tên nhân viên");
		tblDanhSachTaiKhoan = new JTable(dtmDanhSachTaiKhoan);
		JScrollPane scrDanhSachTaiKhoan = new JScrollPane(tblDanhSachTaiKhoan,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scrDanhSachTaiKhoan,BorderLayout.CENTER);

		/*----------------------Bottom----------------------------*/

		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		this.add(pnBottom);

		//Textbox chỉnh sửa và thêm sản phẩm
		JPanel pnChinhSua = new JPanel();
		pnChinhSua.setLayout(new FlowLayout());
		pnBottom.add(pnChinhSua);

		JPanel pnUsername = new JPanel();
		pnUsername.setLayout(new BoxLayout(pnUsername, BoxLayout.Y_AXIS));
		txtUsername = new JTextField(13);
		pnUsername.add(new JLabel("Username"));
		pnUsername.add(txtUsername);
		pnChinhSua.add(pnUsername);

		JPanel pnPassword = new JPanel();
		pnPassword.setLayout(new BoxLayout(pnPassword, BoxLayout.Y_AXIS));
		txtPassword = new JTextField(13);
		pnPassword.add(new JLabel("Password"));
		pnPassword.add(txtPassword);
		pnChinhSua.add(pnPassword);

		JPanel pnTenNhanVien = new JPanel();
		pnTenNhanVien.setLayout(new BoxLayout(pnTenNhanVien, BoxLayout.Y_AXIS));
		cboMaNhanVien = new JComboBox<String>();
		pnTenNhanVien.add(new JLabel("Mã nhân viên"));
		pnTenNhanVien.add(cboMaNhanVien);
		pnChinhSua.add(pnTenNhanVien);

		//Button thao tác sản phẩm
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		pnBottom.add(pnButton);

		btnThem = new JButton("Thêm");
		btnLuu = new JButton("Lưu");
		btnXoa = new JButton("Xóa");
		btnDangXuat = new JButton("Đăng xuất");
		pnButton.add(btnThem);
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);		
		pnButton.add(btnDangXuat);
		//Đổ danh sách đơn hàng lên bảng hiển thị
		hienDanhSachTaiKhoan();
		hienDanhSachMaNhanVien();
	}
}