package ctuet.edu.vn.ui.nhanvien;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.NhanVien;
import ctuet.edu.vn.service.NhanVienService;


public class ThemNhanVienUI extends JPanel {

private static final long serialVersionUID = 1L;
	
	JButton btnLuu;
	JButton btnHuy;
	JTable tblDanhSachNhanVien;
	DefaultTableModel dtmDanhSachNhanVien;
	JTextField txtHoTen,txtCMND,txtNamSinh,txtSoDienThoai,txtNgayNhanViec,txtMucLuong,txtViTri,txtDiaChi;
	
	NhanVienService svNhanVien = new NhanVienService();
	public ThemNhanVienUI(NhanVien nhanvien) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}
	
	private void addControls()
	{
		/*----------------------Top----------------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop,BoxLayout.X_AXIS));
		this.add(pnTop);
		
		// TOPLEFT
		JPanel pnTopLeft = new JPanel();
		pnTopLeft.setLayout(new BoxLayout(pnTopLeft, BoxLayout.Y_AXIS));
		pnTop.add(pnTopLeft);
		//Họ và tên nhân viên
		JPanel pnHoTen = new JPanel();
		pnHoTen.setLayout(new FlowLayout());
		JLabel lblHoTen = new JLabel("Họ Tên Nhân Viên");
		lblHoTen.getBorder();
		txtHoTen = new JTextField(30);
		pnHoTen.add(lblHoTen);
		pnHoTen.add(txtHoTen);
		pnTopLeft.add(pnHoTen);
		
		//CMND
		JPanel pnCMND = new JPanel();
		pnCMND.setLayout(new FlowLayout());
		JLabel lblCMND = new JLabel("CMND");
		txtCMND = new JTextField(30);
		pnCMND.add(lblCMND);
		pnCMND.add(txtCMND);
		pnTopLeft.add(pnCMND);
		//Năm sinh
		JPanel pnNamSinh = new JPanel();
		pnNamSinh.setLayout(new FlowLayout());
		JLabel lblNgaySinh = new JLabel("Năm Sinh");
		txtNamSinh = new JTextField(30);
		pnNamSinh.add(lblNgaySinh);
		pnNamSinh.add(txtNamSinh);
		pnTopLeft.add(pnNamSinh);
		//Số Điện Thoại
		JPanel pnSoDienThoai = new JPanel();
		pnSoDienThoai.setLayout(new FlowLayout());
		JLabel lblSoDienThoai = new JLabel("Số Điện Thoại");
		txtSoDienThoai = new JTextField(30);
		pnSoDienThoai.add(lblSoDienThoai);
		pnSoDienThoai.add(txtSoDienThoai);
		pnTopLeft.add(pnSoDienThoai);	
		lblCMND.setPreferredSize(lblHoTen.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblHoTen.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblHoTen.getPreferredSize());
		//TOPRIGHT
		JPanel pnTopRight = new JPanel();
		pnTopRight.setLayout(new BoxLayout(pnTopRight, BoxLayout.Y_AXIS));
		pnTop.add(pnTopRight);
		//Ngày Nhận Việc
		JPanel pnNgayNhanViec = new JPanel();
		pnNgayNhanViec.setLayout(new FlowLayout());
		JLabel lblNgayNhanViec = new JLabel("Ngày Nhận Việc");
		txtNgayNhanViec = new JTextField(30);
		pnNgayNhanViec.add(lblNgayNhanViec);
		pnNgayNhanViec.add(txtNgayNhanViec);
		pnTopRight.add(pnNgayNhanViec);
		//Mức Lương
		JPanel pnMucLuong = new JPanel();
		pnMucLuong.setLayout(new FlowLayout());
		JLabel lblMucLuong = new JLabel("Mức Lương");
		txtMucLuong = new JTextField(30);
		pnMucLuong.add(lblMucLuong);
		pnMucLuong.add(txtMucLuong);
		pnTopRight.add(pnMucLuong);
		// Vị Trí
		JPanel pnViTri = new JPanel();
		pnViTri.setLayout(new FlowLayout());
		JLabel lblViTri = new JLabel("Vị Trí");
		txtViTri = new JTextField(30);
		pnViTri.add(lblViTri);
		pnViTri.add(txtViTri);
		pnTopRight.add(pnViTri);
		
		// Địa Chỉ
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout());
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		txtDiaChi = new JTextField(30);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnTopRight.add(pnDiaChi);
		
		lblDiaChi.setPreferredSize(lblNgayNhanViec.getPreferredSize());
		lblMucLuong.setPreferredSize(lblNgayNhanViec.getPreferredSize());
		lblViTri.setPreferredSize(lblNgayNhanViec.getPreferredSize());

		/*----------------------Bottom----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		this.add(pnBottom);
		//Thêm nút chức năng
		btnLuu = new JButton("Lưu");
		btnHuy = new JButton("Hủy");
		pnBottom.add(btnLuu);
		pnBottom.add(btnHuy);
		
	}	
	private void addEvents() {
		// TODO Auto-generated method stub
		//Buton Hủy
				btnHuy.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
							ThemNhanVienUI.this.removeAll();
							ThemNhanVienUI.this.add(new QL_NhanVienUI());
							ThemNhanVienUI.this.updateUI();
					}
				});
		//Buton Lưu
				btnLuu.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String tenNhanVien = txtHoTen.getText();
						String cmndNhanVien = txtCMND.getText();
						String ngaysinhdNhanVien = txtNamSinh.getText();
						String sdtNhanVien = txtSoDienThoai.getText();
						String ngaynhanvienNhanVien = txtNgayNhanViec.getText();
						String mucluongNhanVien = txtMucLuong.getText();
						String vitriNhanVien = txtViTri.getText();
						String diachiNhanVien = txtDiaChi.getText();
						if(tenNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống!");
						}else if(cmndNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Chứng minh nhân dân nhân viên không được bỏ trống!");
						}else if(ngaysinhdNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Ngày sinh nhân viên không được bỏ trống!");
						}else if(sdtNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên không được bỏ trống!");
						}else if(ngaynhanvienNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Ngày nhận việc nhân viên không được bỏ trống!");
						}else if(mucluongNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Mức lương nhân viên không được bỏ trống!");
						}else if(vitriNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Vị trí nhân viên không được bỏ trống!");
						}else if(diachiNhanVien.equals("")) {
							JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên không được bỏ trống!");
						}else {

							NhanVien nhanvien = new NhanVien();
							nhanvien.setMaNhanVien("NV" + (svNhanVien.demMaNhanVien() + 1));
							nhanvien.setTenNhanVien(txtHoTen.getText());
							nhanvien.setCmnd(txtCMND.getText());
							//Ép kiểu string sang int
							int namsinh = Integer.parseInt(txtNamSinh.getText());
							nhanvien.setNgaysinh(namsinh);
							nhanvien.setSodienthoai(txtSoDienThoai.getText());
							nhanvien.setNgaynhanviec(txtNgayNhanViec.getText());
							//Ép kiểu string sang int
							float mucluong = Integer.parseInt(txtMucLuong.getText());
							nhanvien.setMucluong(mucluong);
							nhanvien.setVitri(txtViTri.getText());
							nhanvien.setDiachi(txtDiaChi.getText());
							svNhanVien.themNhanVien(nhanvien);
							JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
							ThemNhanVienUI.this.removeAll();
							ThemNhanVienUI.this.add(new QL_NhanVienUI());
							ThemNhanVienUI.this.updateUI();
							hienDanhSachNhanVien();
						}
					}
			
		});
				
				
	}
	public void hienDanhSachNhanVien() {
		dtmDanhSachNhanVien.setRowCount(0);
		for(NhanVien nhanvien : svNhanVien.layToanBoNhanVien()) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(nhanvien.getTenNhanVien());
			vec.add(nhanvien.getSodienthoai());
			vec.add(nhanvien.getNgaysinh());
			vec.add(nhanvien.getCmnd());
			vec.add(nhanvien.getDiachi());
			vec.add(nhanvien.getVitri());
			vec.add(nhanvien.getMucluong());
			vec.add(nhanvien.getNgaynhanviec());
			dtmDanhSachNhanVien.addRow(vec);

		}
	}

}
