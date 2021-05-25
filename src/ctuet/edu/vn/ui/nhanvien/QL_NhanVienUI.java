package ctuet.edu.vn.ui.nhanvien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.NhanVien;
import ctuet.edu.vn.service.NhanVienService;
public class QL_NhanVienUI extends JPanel{


	private static final long serialVersionUID = 1L;

	DefaultTableModel dtmDanhSachNhanVien;
	JButton btnThem;
	JButton btnChinhSua;
	JButton btnXoa;
	JTable tblDanhSachNhanVien;
	JTextField txtHoTen,txtCMND,txtNamSinh,txtSoDienThoai,txtNgayNhanViec,txtMucLuong,txtViTri,txtDiaChi;
	NhanVienService svNhanVien = new NhanVienService();
	public QL_NhanVienUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}

	private void addControls()
	{
		/*----------------------Top----------------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		this.add(pnTop);
		// Bảng Danh Sách Nhân Viên
		dtmDanhSachNhanVien = new DefaultTableModel();
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("Họ và tên");
		dtmDanhSachNhanVien.addColumn("SDT nhân viên");
		dtmDanhSachNhanVien.addColumn("Năm sinh");
		dtmDanhSachNhanVien.addColumn("Mức lương");
		dtmDanhSachNhanVien.addColumn("Vị trí");
		dtmDanhSachNhanVien.addColumn("Địa chỉ");
		dtmDanhSachNhanVien.addColumn("Ngày nhận việc");
		tblDanhSachNhanVien = new JTable(dtmDanhSachNhanVien);
		JScrollPane scrDanhSachNhanVien = new JScrollPane(tblDanhSachNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(scrDanhSachNhanVien,BorderLayout.CENTER);

		hienDanhSachNhanVien();




		/*----------------------Bottom----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		this.add(pnBottom);
		//Thêm nút chức năng
		btnThem = new JButton("Thêm");
		btnChinhSua = new JButton("Chỉnh Sửa");
		btnXoa = new JButton("Xóa");
		pnBottom.add(btnThem);
		pnBottom.add(btnChinhSua);
		pnBottom.add(btnXoa);

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
	private void addEvents() {
		// TODO Auto-generated method stub
		//Button Thêm nhân viên
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				NhanVien nhanvien = new NhanVien();
				
				QL_NhanVienUI.this.removeAll();
				QL_NhanVienUI.this.add(new ThemNhanVienUI(nhanvien));
				QL_NhanVienUI.this.updateUI();
			}
		});



		// Button Xóa nhân viên
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NhanVien nhanvien = new NhanVien();
				if(tblDanhSachNhanVien.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên để xóa!");
				}else if(JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xóa Nhân viên này!","Xác nhận",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String cmnd  = tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(), 0).toString().trim();
					//JOptionPane.showMessageDialog(null, cmnd);
					nhanvien.setCmnd(cmnd);
					svNhanVien.xoaNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
					hienDanhSachNhanVien();
				}
			}
		});

		//Buton Chỉnh sửa nhân viên
		btnChinhSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Lấy dòng được chọn trong bảng
				 * Tạo một nhan vien đổ vô cho nó
				 * Sửa cái hàm khởi tạo QL_NhanVienUI.this.add(new ChinhSuaNhanVienUI()); có nhận một thằng nhân viên
				 * Set dữ liệu cho mấy Jtextfile bên giao diện chỉnh sửa
				 * 
				 * */
				NhanVien nhanvien = new NhanVien();
				
				//Lấy dữ liệu dòng trong bảng như chổ xóa há....nếu không thì lấy cái cmnd thôi...qua kia truy vấn ra từ csdl rồi set cho mấy cái jtextfile
				QL_NhanVienUI.this.removeAll();
				QL_NhanVienUI.this.add(new ChinhSuaNhanVienUI(nhanvien));
				QL_NhanVienUI.this.updateUI();
			}
		});

	}


}
