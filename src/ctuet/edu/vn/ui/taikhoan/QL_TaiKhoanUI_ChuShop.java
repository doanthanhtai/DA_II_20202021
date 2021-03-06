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
					JOptionPane.showMessageDialog(null, "Ch??a ch???n nh??n vi??n s??? h???u t??i kho???n n??y!");
				}else if(taikhoan.getUsername().equals("") || taikhoan.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Username v?? password kh??ng ???????c b??? tr???ng!");
				}else if(svTaiKhoan.kiemtraTonTai(taikhoan)){
					JOptionPane.showMessageDialog(null, "Username ???? t???n t???i!");
				}else {
					taikhoan.setMaNhanVien(cboMaNhanVien.getSelectedItem().toString().trim());
					svTaiKhoan.themTaiKhoan(taikhoan);
					JOptionPane.showMessageDialog(null, "Th??m t??i kho???n th??nh c??ng!");
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
				//N???u password ho???c username b??? tr???ng
				if(taikhoan.getUsername().equals("") || taikhoan.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Username v?? password kh??ng ???????c b??? tr???ng!");
				}else if(svTaiKhoan.kiemtraTonTai(taikhoan) == false){
					JOptionPane.showMessageDialog(null, "Username ch??a t???n t???i,vui l??ng nh???n Th??m ????? th??m m???i!");
				}else {
					svTaiKhoan.capnhatTaiKhoan(taikhoan);			
					JOptionPane.showMessageDialog(null, "C???p nh???t th??ng tin t??i kho???n th??nh c??ng!");
					hienDanhSachTaiKhoan();
				}

			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachTaiKhoan.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Ch??a ch???n s???n ph???m ????? x??a!");
				}else {
					if(JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???n mu???n x??a t??i kho???n kh??ng!","X??c nh???n x??a",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
				new DangNhapUI("????ng nh???p");
			}
		});

	}
	private void addControls() {
		/*----------------------Center----------------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		this.add(pnCenter);
		//B???ng danh s??ch ????n h??ng
		dtmDanhSachTaiKhoan = new DefaultTableModel();
		dtmDanhSachTaiKhoan.addColumn("Username");
		dtmDanhSachTaiKhoan.addColumn("Password");
		dtmDanhSachTaiKhoan.addColumn("T??n nh??n vi??n");
		tblDanhSachTaiKhoan = new JTable(dtmDanhSachTaiKhoan);
		JScrollPane scrDanhSachTaiKhoan = new JScrollPane(tblDanhSachTaiKhoan,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scrDanhSachTaiKhoan,BorderLayout.CENTER);

		/*----------------------Bottom----------------------------*/

		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		this.add(pnBottom);

		//Textbox ch???nh s???a v?? th??m s???n ph???m
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
		pnTenNhanVien.add(new JLabel("M?? nh??n vi??n"));
		pnTenNhanVien.add(cboMaNhanVien);
		pnChinhSua.add(pnTenNhanVien);

		//Button thao t??c s???n ph???m
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		pnBottom.add(pnButton);

		btnThem = new JButton("Th??m");
		btnLuu = new JButton("L??u");
		btnXoa = new JButton("X??a");
		btnDangXuat = new JButton("????ng xu???t");
		pnButton.add(btnThem);
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);		
		pnButton.add(btnDangXuat);
		//????? danh s??ch ????n h??ng l??n b???ng hi???n th???
		hienDanhSachTaiKhoan();
		hienDanhSachMaNhanVien();
	}
}