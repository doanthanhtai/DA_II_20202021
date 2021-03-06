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
	
    
	
	public QL_NhanVienUI(String username) {
		this.setLayout(new BorderLayout());
		if(username.trim().equals("admin")) {
			addControls();
			addEvents();
		}else {
			
		}
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
		// B???ng Danh S??ch Nh??n Vi??n
		dtmDanhSachNhanVien = new DefaultTableModel();
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("H??? v?? t??n");
		dtmDanhSachNhanVien.addColumn("SDT nh??n vi??n");
		dtmDanhSachNhanVien.addColumn("N??m sinh");
		dtmDanhSachNhanVien.addColumn("M???c l????ng");
		dtmDanhSachNhanVien.addColumn("V??? tr??");
		dtmDanhSachNhanVien.addColumn("?????a ch???");
		dtmDanhSachNhanVien.addColumn("Ng??y nh???n vi???c");
		tblDanhSachNhanVien = new JTable(dtmDanhSachNhanVien);
		JScrollPane scrDanhSachNhanVien = new JScrollPane(tblDanhSachNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(scrDanhSachNhanVien,BorderLayout.CENTER);

		hienDanhSachNhanVien();

		/*----------------------Between----------------------------*/
		JPanel pnBetween = new JPanel();
		pnBetween.setLayout(new BoxLayout(pnBetween, BoxLayout.X_AXIS));
		
		
		JPanel pnHoTen = new JPanel();
		pnHoTen.setLayout(new BoxLayout(pnHoTen,BoxLayout.Y_AXIS));
		JLabel lblHoTen = new JLabel("H??? T??n Nh??n Vi??n");
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
		JLabel lblNamSinh = new JLabel("N??m Sinh");
		txtNamSinh = new JFormattedTextField(mfNamSinh);
		pnNamSinh.add(lblNamSinh);
		pnNamSinh.add(txtNamSinh);
		pnBetween.add(pnNamSinh);
		
		JPanel pnSoDienThoai = new JPanel();
		pnSoDienThoai.setLayout(new BoxLayout(pnSoDienThoai,BoxLayout.Y_AXIS));
		JLabel lblSoDienThoai = new JLabel("S??? ??i???n Tho???i");
		txtSoDienThoai = new JFormattedTextField(mfSoDienThoai);
		pnSoDienThoai.add(lblSoDienThoai);
		pnSoDienThoai.add(txtSoDienThoai);
		pnBetween.add(pnSoDienThoai);
		
		JPanel pnNgayNhanViec = new JPanel();
		pnNgayNhanViec.setLayout(new BoxLayout(pnNgayNhanViec,BoxLayout.Y_AXIS));
		JLabel lblNgayNhanViec = new JLabel("Ng??y Nh???n Vi???c");
		txtNgayNhanViec = new JFormattedTextField(mfNgayThang);
		pnNgayNhanViec.add(lblNgayNhanViec);
		pnNgayNhanViec.add(txtNgayNhanViec);
		pnBetween.add(pnNgayNhanViec);
		
		JPanel pnMucLuong = new JPanel();
		pnMucLuong.setLayout(new BoxLayout(pnMucLuong,BoxLayout.Y_AXIS));
		JLabel lblMucLuong = new JLabel("M???c L????ng");
		txtMucLuong = new JFormattedTextField(mfMucLuong);
		pnMucLuong.add(lblMucLuong);
		pnMucLuong.add(txtMucLuong);
		pnBetween.add(pnMucLuong);
		
		JPanel pnViTri = new JPanel();
		pnViTri.setLayout(new BoxLayout(pnViTri,BoxLayout.Y_AXIS));
		JLabel lblViTri = new JLabel("V??? Tr??");
		txtViTri = new JTextField();
		pnViTri.add(lblViTri);
		pnViTri.add(txtViTri);
		pnBetween.add(pnViTri);
		
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new BoxLayout(pnDiaChi,BoxLayout.Y_AXIS));
		JLabel lblDiaChi = new JLabel("?????a Ch???");
		txtDiaChi = new JTextField();
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnBetween.add(pnDiaChi);
		
		
		/*----------------------Bottom----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		this.add(pnBottom,BorderLayout.SOUTH);
		
		pnBottom.add(pnBetween);
		
		//Th??m n??t ch???c n??ng
		JPanel pnButton = new JPanel(new FlowLayout());
		pnBottom.add(pnButton);
		
		btnThem = new JButton("Th??m");
		btnChinhSua = new JButton("Ch???nh s???a");
		btnXoa = new JButton("X??a");
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
		
		//Button Th??m nh??n vi??n
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
					JOptionPane.showMessageDialog(null, "T??n nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if(nhanvien.getCmnd().equals("")) {
					JOptionPane.showMessageDialog(null, "Ch???ng minh nh??n d??n nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if((nhanvien.getNgaysinh() + "").equals("")) {
					JOptionPane.showMessageDialog(null, "Ng??y sinh nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if(nhanvien.getSodienthoai().equals("")) {
					JOptionPane.showMessageDialog(null, "S??? ??i???n tho???i nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if(nhanvien.getNgaynhanviec().equals("")) {
					JOptionPane.showMessageDialog(null, "Ng??y nh???n vi???c nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if((nhanvien.getMucluong() + "").equals("")) {
					JOptionPane.showMessageDialog(null, "M???c l????ng nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if(nhanvien.getVitri().equals("")) {
					JOptionPane.showMessageDialog(null, "V??? tr?? nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else if(nhanvien.getDiachi().equals("")) {
					JOptionPane.showMessageDialog(null, "?????a ch??? nh??n vi??n kh??ng ???????c b??? tr???ng!");
				}else {
					svNhanVien.themNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "Th??m nh??n vi??n th??nh c??ng!");
					hienDanhSachNhanVien();
				}
			}
		});



		// Button X??a nh??n vi??n
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NhanVien nhanvien = new NhanVien();
				if(tblDanhSachNhanVien.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Ch??a ch???n nh??n vi??n ????? x??a!");
				}else if(JOptionPane.showConfirmDialog(null,"B???n c?? ch???c mu???n x??a Nh??n vi??n n??y!","X??c nh???n",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String cmnd  = tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(), 0).toString().trim();
					//JOptionPane.showMessageDialog(null, cmnd);
					nhanvien.setCmnd(cmnd);
					svNhanVien.xoaNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "X??a th??nh c??ng!");
					hienDanhSachNhanVien();
				}
			}
		});
		
		
		//Buton Ch???nh s???a nh??n vi??n
		btnChinhSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachNhanVien.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Ch??a ch???n nh??n vi??n ????? ch???nh s???a!");
				}else if(JOptionPane.showConfirmDialog(null,"B???n c?? ch???c mu???n c???p nh???t th??ng tin Nh??n vi??n n??y!","X??c nh???n",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					String cmnd  = tblDanhSachNhanVien.getValueAt(tblDanhSachNhanVien.getSelectedRow(), 0).toString().trim();
					nhanvien.setCmnd(cmnd);
					svNhanVien.xoaNhanVien(nhanvien);
					
					nhanvien.setTenNhanVien(txtHoTen.getText());
					nhanvien.setCmnd(txtCMND.getText().toString().trim());
					nhanvien.setNgaysinh(txtNamSinh.getText());
					nhanvien.setSodienthoai(txtSoDienThoai.getText().toString().trim());
					nhanvien.setNgaynhanviec(txtNgayNhanViec.getText());
					nhanvien.setMucluong(txtMucLuong.getText());
					nhanvien.setVitri(txtViTri.getText());
					nhanvien.setDiachi(txtDiaChi.getText());
					
					svNhanVien.themNhanVien(nhanvien);
					JOptionPane.showMessageDialog(null, "Ch???nh s???a th??nh c??ng!");
					hienDanhSachNhanVien();
					
				}
			}
		});

	}


}
