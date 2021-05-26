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


public class ChinhSuaNhanVienUI extends JPanel {
private static final long serialVersionUID = 1L;
	
	JButton btnCapNhat;
	JButton btnHuy;
	
	JTextField txtHoTen,txtCMND,txtNamSinh,txtSoDienThoai,txtNgayNhanViec,txtMucLuong,txtViTri,txtDiaChi;
	
	JTable tblDanhSachNhanVien;
	DefaultTableModel dtmDanhSachNhanVien;
	NhanVienService svNhanVien = new NhanVienService();
	NhanVien nhanvien = new NhanVien();
	
	public ChinhSuaNhanVienUI(NhanVien nhanvien) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.nhanvien = nhanvien;
		addControls();
		hienthiThongTinNhanVien(nhanvien);
		addEvents();
	}
	
	private void hienthiThongTinNhanVien(NhanVien nhanvien) {
		txtHoTen.setText(nhanvien.getTenNhanVien());
		txtCMND.setText(nhanvien.getCmnd());
		txtNamSinh.setText(nhanvien.getNgaysinh() + "");
		txtSoDienThoai.setText(nhanvien.getSodienthoai());
		txtNgayNhanViec.setText(nhanvien.getNgaynhanviec());
		txtMucLuong.setText(nhanvien.getMucluong() + "");
		txtViTri.setText(nhanvien.getVitri());
		txtDiaChi.setText(nhanvien.getDiachi());
		
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
		//Ngày sinh
		JPanel pnNamSinh = new JPanel();
		pnNamSinh.setLayout(new FlowLayout());
		JLabel lblNamSinh = new JLabel("Năm Sinh");
		txtNamSinh = new JTextField(30);
		pnNamSinh.add(lblNamSinh);
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
		lblNamSinh.setPreferredSize(lblHoTen.getPreferredSize());
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
		btnCapNhat=new JButton("Cập nhật");
		btnHuy = new JButton("Hủy");
		pnBottom.add(btnCapNhat);
		pnBottom.add(btnHuy);
		
	}
	
	private void addEvents() {
		// TODO Auto-generated method stub
		//Buton Hủy
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					ChinhSuaNhanVienUI.this.removeAll();
					ChinhSuaNhanVienUI.this.add(new QL_NhanVienUI());
					ChinhSuaNhanVienUI.this.updateUI();
			}
		});
		// Cập nhật thông tin nhân viên
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				nhanvien.setTenNhanVien(txtHoTen.getText());
				nhanvien.setCmnd(txtCMND.getText().toString().trim());
				nhanvien.setNgaysinh(Integer.parseInt(txtNamSinh.getText()));
				nhanvien.setSodienthoai(txtSoDienThoai.getText().toString().trim());
				nhanvien.setNgaynhanviec(txtNgayNhanViec.getText());
				nhanvien.setMucluong(Float.parseFloat(txtMucLuong.getText()));
				nhanvien.setVitri(txtViTri.getText());
				nhanvien.setDiachi(txtDiaChi.getText());

				if(nhanvien.getTenNhanVien().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống!");
				}else if(nhanvien.getCmnd().equals("")) {
					JOptionPane.showMessageDialog(null, "Chứng minh nhân dân nhân viên không được bỏ trống!");
				}else if((nhanvien.getNgaysinh() + "").equals("")) {
					JOptionPane.showMessageDialog(null, "Ngày sinh nhân viên không được bỏ trống!");
				}else if(nhanvien.getSodienthoai().equals("")) {
					JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên không được bỏ trống!");
				}else if(nhanvien.getNgaynhanviec().equals("")) {
					JOptionPane.showMessageDialog(null, "Ngày nhận việc nhân viên không được bỏ trống!");
				}else if((nhanvien.getMucluong() + "").equals("")) {
					JOptionPane.showMessageDialog(null, "Mức lương nhân viên không được bỏ trống!");
				}else if(nhanvien.getVitri().equals("")) {
					JOptionPane.showMessageDialog(null, "Vị trí nhân viên không được bỏ trống!");
				}else if(nhanvien.getDiachi().equals("")) {
					JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên không được bỏ trống!");
				}else {
					svNhanVien.chinhsuaThongTinNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "Chỉnh sửa nhân viên thành công!");
					ChinhSuaNhanVienUI.this.removeAll();
					ChinhSuaNhanVienUI.this.add(new QL_NhanVienUI());
					ChinhSuaNhanVienUI.this.updateUI();
				}

			}
		});
	}

	
	public void hienDanhSachNhanVien() {
		dtmDanhSachNhanVien.setRowCount(0);
		for(NhanVien nhanvien : svNhanVien.layToanBoNhanVien()) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(nhanvien.getCmnd());
			vec.add(nhanvien.getTenNhanVien());
			vec.add(nhanvien.getSodienthoai());
			vec.add(nhanvien.getNgaysinh());
			vec.add(nhanvien.getMucluong());			
			vec.add(nhanvien.getVitri());
			vec.add(nhanvien.getDiachi());
			vec.add(nhanvien.getNgaynhanviec());
			dtmDanhSachNhanVien.addRow(vec);

		}
	}

}

