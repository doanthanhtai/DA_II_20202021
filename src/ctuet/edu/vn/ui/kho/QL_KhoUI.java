package ctuet.edu.vn.ui.kho;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.Kho;
import ctuet.edu.vn.service.ChiTietKhoService;
import ctuet.edu.vn.service.DonHangService;
import ctuet.edu.vn.service.KhoService;
import ctuet.edu.vn.service.SanPhamService;

public class QL_KhoUI extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String username;

	DefaultTableModel dtmDanhSachKho;
	JButton btnThem;
	JButton btnLuu;
	JButton btnXoa;
	JButton btnNhapXuat;
	JButton btnCapNhat;
	JTable tblDanhSachKho;

	JTextField txtTenKho,txtDiaChi;

	DonHangService svDonHang = new DonHangService();
	SanPhamService svSanPham = new SanPhamService();
	
	ChiTietKhoService svChiTietKho = new ChiTietKhoService();
	KhoService svKho = new KhoService();
	
	
	Kho kho = new Kho();


	public QL_KhoUI(String username) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.username = username;
		addControls();
		addEvents();
	}


	private void hienDanhSachKho() {
		dtmDanhSachKho.setRowCount(0);
		for(Kho kho : svKho.layToanBoKho()) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(kho.getTenKho());
			vec.add(kho.getMaKho());
			vec.add(kho.getDiaChi());
			vec.add(svChiTietKho.demSoSanPham(kho));
			dtmDanhSachKho.addRow(vec);

		}
	}


	private void addEvents() {
		btnCapNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hienDanhSachKho();
			}
		});
		btnNhapXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NhapXuatUI ui = new NhapXuatUI("Nh???p xu???t kho");
				ui.showWindow();
				hienDanhSachKho();
			}
		});
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tenKho = txtTenKho.getText().toString();
				String diachi = txtDiaChi.getText().toString();
				
				if(tenKho.equals("") || diachi.equals("")) {
					JOptionPane.showMessageDialog(null, "T??n Kho v?? ?????a ch??? Kho kh??ng ???????c ????? tr???ng!");
				}else {
					kho.setMaKho("KHO" + svKho.demsoKho());
					kho.setTenKho(txtTenKho.getText().toString().trim());
					kho.setDiaChi(txtDiaChi.getText().toString().trim());
					kho.setTrangthai(1);
					svKho.themKhoMoi(kho);
					JOptionPane.showMessageDialog(null, "Th??m kho m???i th??nh c??ng!");
				
				}
				hienDanhSachKho();
			}
		});
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachKho.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Ch??a ch???n Kho ????? x??a!");
				}else {
					if(JOptionPane.showConfirmDialog(null, "X??c nh???n x??a" + kho.getMaKho() + "!","X??c nh???n x??a Kho",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if(svChiTietKho.demSoSanPham(kho) > 0) {
							JOptionPane.showMessageDialog(null, "Kho h??ng hi???n c?? s???n ph???m b??n trong n??n kh??ng th??? th???c hi???n x??a th??nh c??ng!");
						}else {
							svKho.xoaKho(kho);
						}
						
					}
					hienDanhSachKho();
				}
			}
		});
		
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachKho.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Ch??a ch???n Kho ????? ch???nh s???a!");
				}else {
					kho.setTenKho(txtTenKho.getText());
					kho.setDiaChi(txtDiaChi.getText());
					svKho.capnhatKho(kho);
					
					JOptionPane.showMessageDialog(null, "C???p nh???t th??ng tin " + kho.getMaKho() + " th??nh c??ng!");
					hienDanhSachKho();
				}
			}
		});
		
		tblDanhSachKho.addMouseListener(new MouseListener() {
			
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
				//l???y th??ng tin kho ???????c ch???n tr??n table
				kho.setTenKho(tblDanhSachKho.getValueAt(tblDanhSachKho.getSelectedRow(), 0).toString().trim());
				kho.setMaKho(tblDanhSachKho.getValueAt(tblDanhSachKho.getSelectedRow(), 1).toString().trim());
				kho.setDiaChi(tblDanhSachKho.getValueAt(tblDanhSachKho.getSelectedRow(), 2).toString().trim());
				kho.setTrangthai(1);
				//set gi?? tr??? cho c??c textbox
				txtTenKho.setText(kho.getTenKho());
				txtDiaChi.setText(kho.getDiaChi());
				
			}
		});
	}

	private void addControls() {
		/*----------------------Center----------------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		this.add(pnCenter);
		//B???ng danh s??ch Kho
		dtmDanhSachKho = new DefaultTableModel();
		dtmDanhSachKho.addColumn("T??n kho");
		dtmDanhSachKho.addColumn("M?? kho");
		dtmDanhSachKho.addColumn("?????a ch??? kho");
		dtmDanhSachKho.addColumn("T???ng s???n ph???m");
		tblDanhSachKho = new JTable(dtmDanhSachKho);
		JScrollPane scrDanhSachKho = new JScrollPane(tblDanhSachKho,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scrDanhSachKho,BorderLayout.CENTER);

		//????? danh s??ch kho l??n b???ng hi???n th???
		hienDanhSachKho();


		/*----------------------Bottom----------------------------*/

		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		this.add(pnBottom);

		//Textbox ch???nh s???a v?? th??m Kho
		JPanel pnChinhSua = new JPanel();
		pnChinhSua.setLayout(new FlowLayout());
		pnBottom.add(pnChinhSua);

		JPanel pnTenKho = new JPanel();
		pnTenKho.setLayout(new BoxLayout(pnTenKho, BoxLayout.Y_AXIS));
		txtTenKho = new JTextField(13);
		pnTenKho.add(new JLabel("T??n kho"));
		pnTenKho.add(txtTenKho);
		pnChinhSua.add(pnTenKho);
	
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new BoxLayout(pnDiaChi, BoxLayout.Y_AXIS));
		txtDiaChi = new JTextField(13);
		pnDiaChi.add(new JLabel("?????a ch???"));
		pnDiaChi.add(txtDiaChi);
		pnChinhSua.add(pnDiaChi);

		//Button thao t??c Kho
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		pnBottom.add(pnButton);

		btnThem = new JButton("Th??m");
		btnLuu = new JButton("L??u");
		btnXoa = new JButton("X??a");
		btnNhapXuat = new JButton("Nh???p Xu???t");
		btnCapNhat = new JButton("C???p Nh???t");
		pnButton.add(btnThem);
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);		
		pnButton.add(btnNhapXuat);
		pnButton.add(btnCapNhat);
	}

}
