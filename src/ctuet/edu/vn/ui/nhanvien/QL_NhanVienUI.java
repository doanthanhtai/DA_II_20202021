package ctuet.edu.vn.ui.nhanvien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ctuet.edu.vn.model.NhanVien;
import ctuet.edu.vn.service.NhanVienService;
public class QL_NhanVienUI extends JPanel{


	private static final long serialVersionUID = 1L;

	DefaultTableModel dtmDanhSachNhanVien;
	JButton btnThem;
	JButton btnChinhSua;
	JButton btnXoa;
	JTable tblDanhSachNhanVien;
	JTextField txtHoTen,txtViTri,txtDiaChi;
	
	JFormattedTextField txtCMND,txtNamSinh,txtSoDienThoai,txtNgayNhanViec,txtMucLuong;
	
	NhanVienService svNhanVien = new NhanVienService();
	NhanVien nhanvien = new NhanVien();
	
	MaskFormatter mfSoDienThoai,mfNgayThang,mfNamSinh,mfMucLuong,mfCMND;
	
    
	
	public QL_NhanVienUI() {
		this.setLayout(new BorderLayout());
		addControls();
		addEvents();
		
	}

	private void addControls()
	{
		try {
			mfSoDienThoai = new MaskFormatter("####-###-###");
			mfNgayThang = new MaskFormatter("##-##-####");
			mfNamSinh = new MaskFormatter("####");
			mfMucLuong = new MaskFormatter("##,###,###");
			mfCMND = new MaskFormatter("###-###-###-###");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mfSoDienThoai.setPlaceholderCharacter('#');
		mfNgayThang.setPlaceholderCharacter('#');
		mfNamSinh.setPlaceholderCharacter('#');
		mfMucLuong.setPlaceholderCharacter('#');
		mfCMND.setPlaceholderCharacter('#');
		
		/*----------------------Top----------------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		this.add(pnTop,BorderLayout.CENTER);
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

		/*----------------------Between----------------------------*/
		JPanel pnBetween = new JPanel();
		pnBetween.setLayout(new BoxLayout(pnBetween, BoxLayout.X_AXIS));
		
		
		JPanel pnHoTen = new JPanel();
		pnHoTen.setLayout(new BoxLayout(pnHoTen,BoxLayout.Y_AXIS));
		JLabel lblHoTen = new JLabel("Họ Tên Nhân Viên");
		txtHoTen = new JTextField();
		pnHoTen.add(lblHoTen);
		pnHoTen.add(txtHoTen);
		pnBetween.add(pnHoTen);
		
		JPanel pnCMND = new JPanel();
		pnCMND.setLayout(new BoxLayout(pnCMND,BoxLayout.Y_AXIS));
		JLabel lblCMND = new JLabel("CMND");
		txtCMND = new JFormattedTextField(mfCMND);
		pnCMND.add(lblCMND);
		pnCMND.add(txtCMND);
		pnBetween.add(pnCMND);
		
		JPanel pnNamSinh = new JPanel();
		pnNamSinh.setLayout(new BoxLayout(pnNamSinh,BoxLayout.Y_AXIS));
		JLabel lblNamSinh = new JLabel("Năm Sinh");
		txtNamSinh = new JFormattedTextField(mfNamSinh);
		pnNamSinh.add(lblNamSinh);
		pnNamSinh.add(txtNamSinh);
		pnBetween.add(pnNamSinh);
		
		JPanel pnSoDienThoai = new JPanel();
		pnSoDienThoai.setLayout(new BoxLayout(pnSoDienThoai,BoxLayout.Y_AXIS));
		JLabel lblSoDienThoai = new JLabel("Số Điện Thoại");
		txtSoDienThoai = new JFormattedTextField(mfSoDienThoai);
		pnSoDienThoai.add(lblSoDienThoai);
		pnSoDienThoai.add(txtSoDienThoai);
		pnBetween.add(pnSoDienThoai);
		
		JPanel pnNgayNhanViec = new JPanel();
		pnNgayNhanViec.setLayout(new BoxLayout(pnNgayNhanViec,BoxLayout.Y_AXIS));
		JLabel lblNgayNhanViec = new JLabel("Ngày Nhận Việc");
		txtNgayNhanViec = new JFormattedTextField(mfNgayThang);
		pnNgayNhanViec.add(lblNgayNhanViec);
		pnNgayNhanViec.add(txtNgayNhanViec);
		pnBetween.add(pnNgayNhanViec);
		
		JPanel pnMucLuong = new JPanel();
		pnMucLuong.setLayout(new BoxLayout(pnMucLuong,BoxLayout.Y_AXIS));
		JLabel lblMucLuong = new JLabel("Mức Lương");
		txtMucLuong = new JFormattedTextField(mfMucLuong);
		pnMucLuong.add(lblMucLuong);
		pnMucLuong.add(txtMucLuong);
		pnBetween.add(pnMucLuong);
		
		JPanel pnViTri = new JPanel();
		pnViTri.setLayout(new BoxLayout(pnViTri,BoxLayout.Y_AXIS));
		JLabel lblViTri = new JLabel("Vị Trí");
		txtViTri = new JTextField();
		pnViTri.add(lblViTri);
		pnViTri.add(txtViTri);
		pnBetween.add(pnViTri);
		
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new BoxLayout(pnDiaChi,BoxLayout.Y_AXIS));
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		txtDiaChi = new JTextField();
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnBetween.add(pnDiaChi);
		
		
		/*----------------------Bottom----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		this.add(pnBottom,BorderLayout.SOUTH);
		
		pnBottom.add(pnBetween);
		
		//Thêm nút chức năng
		JPanel pnButton = new JPanel(new FlowLayout());
		pnBottom.add(pnButton);
		
		btnThem = new JButton("Thêm");
		btnChinhSua = new JButton("Chỉnh sửa");
		btnXoa = new JButton("Xóa");
		pnButton.add(btnThem);
		pnButton.add(btnChinhSua);
		pnButton.add(btnXoa);
		
		
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
		
		tblDanhSachNhanVien.addMouseListener(new MouseListener() {
			
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
				
				nhanvien.setCmnd(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),0).toString());
				nhanvien.setTenNhanVien(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),1).toString());
				nhanvien.setSodienthoai(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),2).toString());
				nhanvien.setNgaysinh(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),3).toString());
				nhanvien.setMucluong(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),4).toString());
				nhanvien.setVitri(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),5).toString());
				nhanvien.setDiachi(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),6).toString());
				nhanvien.setNgaynhanviec(tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(),7).toString());
				
				
				txtHoTen.setText(nhanvien.getTenNhanVien());
				txtCMND.setText(nhanvien.getCmnd());
				txtNamSinh.setText(nhanvien.getNgaysinh());
				txtSoDienThoai.setText(nhanvien.getSodienthoai());
				txtNgayNhanViec.setText(nhanvien.getNgaynhanviec());
				txtMucLuong.setText(nhanvien.getMucluong());
				txtViTri.setText(nhanvien.getVitri());
				txtDiaChi.setText(nhanvien.getDiachi());
			}
		});
		
		//Button Thêm nhân viên
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				nhanvien.setMaNhanVien("NV" + (svNhanVien.demMaNhanVien() + 1));
				nhanvien.setTenNhanVien(txtHoTen.getText());
				nhanvien.setCmnd(txtCMND.getText().toString().trim());
				nhanvien.setNgaysinh(txtNamSinh.getText());
				nhanvien.setSodienthoai(txtSoDienThoai.getText().toString().trim());
				nhanvien.setNgaynhanviec(txtNgayNhanViec.getText());
				nhanvien.setMucluong(txtMucLuong.getText());
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
					svNhanVien.themNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
					hienDanhSachNhanVien();
				}
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
				if(tblDanhSachNhanVien.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên để chỉnh sửa!");
				}else if(JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn cập nhật thông tin Nhân viên này!","Xác nhận",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					nhanvien.setTenNhanVien(txtHoTen.getText());
					nhanvien.setCmnd(txtCMND.getText().toString().trim());
					nhanvien.setNgaysinh(txtNamSinh.getText());
					nhanvien.setSodienthoai(txtSoDienThoai.getText().toString().trim());
					nhanvien.setNgaynhanviec(txtNgayNhanViec.getText());
					nhanvien.setMucluong(txtMucLuong.getText());
					nhanvien.setVitri(txtViTri.getText());
					nhanvien.setDiachi(txtDiaChi.getText());
					
					svNhanVien.chinhsuaThongTinNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
					hienDanhSachNhanVien();
					
				}
			}
		});

	}


}
