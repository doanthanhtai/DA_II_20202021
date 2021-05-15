package ctuet.edu.vn.ui.sanpham;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import ctuet.edu.vn.model.SanPham;
import ctuet.edu.vn.service.DonHangService;
import ctuet.edu.vn.service.SanPhamService;

public class QL_SanPhamUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String username;

	DefaultTableModel dtmDanhSachSanPham;
	JButton btnThem;
	JButton btnLuu;
	JButton btnXoa;
	JButton btnCapNhat;
	JButton btnChonAnh;
	JTable tblDanhSachSanPham;

	JTextField txtTenSanPham,txtNguonCungCap,txtMauSac,txtKichThuoc,txtGiaBan,txtTongTon,txtAnhSanPham;

	DonHangService svDonHang = new DonHangService();
	SanPhamService svSanPham = new SanPhamService();		
	SanPham sanpham = new SanPham();

	public QL_SanPhamUI(String username) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.username = username;
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachSanPham.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm cập nhật!");
				}else try {
					int giaban = Integer.parseInt(txtGiaBan.getText());
					int kichthuong = Integer.parseInt(txtKichThuoc.getText());
					
					if(JOptionPane.showConfirmDialog(null,"Xác nhận cập nhật sản phẩm!","Xác nhận",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String maSanPham = tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 0).toString().trim();
						sanpham.setMaSanPham(maSanPham);
						sanpham.setTenSanPham(txtTenSanPham.getText());
						sanpham.setGiaban(giaban);
						sanpham.setKichthuoc(kichthuong);
						sanpham.setMauSac(txtMauSac.getText());
						sanpham.setHinhanh(txtAnhSanPham.getText().trim());
						sanpham.setNguoncungcap(txtNguonCungCap.getText());
						
						svSanPham.capnhatSanPham(sanpham);
						
						JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
						hienDanhSachSanPham();
					}
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Giá bán hoặc kích thước sản phẩm không hợp lệ!");
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachSanPham.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để xóa!");
				}else if(JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xóa Sản phẩm!","Xác nhận",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maSanPham = tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 0).toString().trim();
					sanpham.setMaSanPham(maSanPham);
					svSanPham.xoaSanPham(sanpham);
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
					hienDanhSachSanPham();
				}
			}
		});
		btnChonAnh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtAnhSanPham.setText("image/product_64px.png");
				JFileChooser chooser = new JFileChooser();
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					txtAnhSanPham.setText(chooser.getSelectedFile().getAbsolutePath());
				}else {
					JOptionPane.showMessageDialog(null, "Chưa chọn ảnh cho sản phẩm!");
				}
			}
		});
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int kichthuoc = Integer.parseInt(txtKichThuoc.getText().trim());
					int giaban = Integer.parseInt(txtGiaBan.getText().trim());
					if(kichthuoc > 0) {
						if(giaban > 0) {
							sanpham.setMaSanPham("SP" + svSanPham.demSoSanPham());
							sanpham.setTenSanPham(txtTenSanPham.getText().trim());
							sanpham.setNguoncungcap(txtNguonCungCap.getText().trim());
							sanpham.setMauSac(txtMauSac.getText().trim());
							sanpham.setKichthuoc(kichthuoc);
							sanpham.setGiaban(giaban);
							sanpham.setHinhanh(txtAnhSanPham.getText().trim());

							svSanPham.themSanPhamMoi(sanpham);

							JOptionPane.showMessageDialog(null, "Tạo sản phẩm thành công!");
							hienDanhSachSanPham();
						}else {
							JOptionPane.showMessageDialog(null, "Giá bán sản phẩm phải là số dương!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Kích thước sản phẩm phải là số nguyên dương!");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dữ liệu giá bán và kích thước sản phẩm không hợp lệ!");
				}	
			}
		});
		tblDanhSachSanPham.addMouseListener(new MouseListener() {

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
				txtTenSanPham.setText(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 1).toString());
				txtNguonCungCap.setText(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 2).toString());
				txtMauSac.setText(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 3).toString());
				txtKichThuoc.setText(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 4).toString());
				txtGiaBan.setText(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 5).toString());
				txtAnhSanPham.setText(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 7).toString());
			}
		});
		btnCapNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hienDanhSachSanPham();
			}
		});
	}

	private void hienDanhSachSanPham() {
		dtmDanhSachSanPham.setRowCount(0);
		for(SanPham sanpham : svSanPham.layToanBoSanPham()) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(sanpham.getMaSanPham());
			vec.add(sanpham.getTenSanPham());
			vec.add(sanpham.getNguoncungcap());
			vec.add(sanpham.getMauSac());
			vec.add(sanpham.getKichthuoc());
			vec.add(sanpham.getGiaban());
			vec.add(svSanPham.tongton(sanpham));
			vec.add(sanpham.getHinhanh());
			dtmDanhSachSanPham.addRow(vec);

		}
	}

	private void addControls() {
		/*----------------------Center----------------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		this.add(pnCenter);
		//Bảng danh sách đơn hàng
		dtmDanhSachSanPham = new DefaultTableModel();
		dtmDanhSachSanPham.addColumn("Mã SP");
		dtmDanhSachSanPham.addColumn("Tên Sản phẩm");
		dtmDanhSachSanPham.addColumn("Nguôn cung cấp");
		dtmDanhSachSanPham.addColumn("Màu sắc");
		dtmDanhSachSanPham.addColumn("Kích thước");
		dtmDanhSachSanPham.addColumn("Giá bán");
		dtmDanhSachSanPham.addColumn("Tổng tồn");
		dtmDanhSachSanPham.addColumn("Ảnh sản phẩm");
		tblDanhSachSanPham = new JTable(dtmDanhSachSanPham);
		JScrollPane scrDanhSachSanPham = new JScrollPane(tblDanhSachSanPham,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scrDanhSachSanPham,BorderLayout.CENTER);

		//Đổ danh sách đơn hàng lên bảng hiển thị
		hienDanhSachSanPham();


		/*----------------------Bottom----------------------------*/

		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		this.add(pnBottom);

		//Textbox chỉnh sửa và thêm sản phẩm
		JPanel pnChinhSua = new JPanel();
		pnChinhSua.setLayout(new FlowLayout());
		pnBottom.add(pnChinhSua);

		JPanel pnTenSP = new JPanel();
		pnTenSP.setLayout(new BoxLayout(pnTenSP, BoxLayout.Y_AXIS));
		txtTenSanPham = new JTextField(13);
		pnTenSP.add(new JLabel("Tên sản phẩm"));
		pnTenSP.add(txtTenSanPham);
		pnChinhSua.add(pnTenSP);

		JPanel pnNguonCungCap = new JPanel();
		pnNguonCungCap.setLayout(new BoxLayout(pnNguonCungCap, BoxLayout.Y_AXIS));
		txtNguonCungCap = new JTextField(13);
		pnNguonCungCap.add(new JLabel("Nguồn cung cấp"));
		pnNguonCungCap.add(txtNguonCungCap);
		pnChinhSua.add(pnNguonCungCap);

		JPanel pnMauSac = new JPanel();
		pnMauSac.setLayout(new BoxLayout(pnMauSac, BoxLayout.Y_AXIS));
		txtMauSac = new JTextField(13);
		pnMauSac.add(new JLabel("Màu sắc"));
		pnMauSac.add(txtMauSac);
		pnChinhSua.add(pnMauSac);

		JPanel pnKichThuoc = new JPanel();
		pnKichThuoc.setLayout(new BoxLayout(pnKichThuoc, BoxLayout.Y_AXIS));
		txtKichThuoc = new JTextField(13);
		pnKichThuoc.add(new JLabel("Kích thước"));
		pnKichThuoc.add(txtKichThuoc);
		pnChinhSua.add(pnKichThuoc);

		JPanel pnGiaBan = new JPanel();
		pnGiaBan.setLayout(new BoxLayout(pnGiaBan, BoxLayout.Y_AXIS));
		txtGiaBan = new JTextField(13);
		pnGiaBan.add(new JLabel("Giá bán"));
		pnGiaBan.add(txtGiaBan);
		pnChinhSua.add(pnGiaBan);

		JPanel pnAnhSanPham = new JPanel();
		pnAnhSanPham.setLayout(new BoxLayout(pnAnhSanPham, BoxLayout.Y_AXIS));
		txtAnhSanPham = new JTextField("image/product_64px.png");
		btnChonAnh = new JButton("Chọn ảnh");
		pnAnhSanPham.add(new JLabel("Ảnh sản phẩm"));
		pnAnhSanPham.add(btnChonAnh);
		pnChinhSua.add(pnAnhSanPham);


		//Button thao tác sản phẩm
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		pnBottom.add(pnButton);

		btnThem = new JButton("Thêm");
		btnLuu = new JButton("Lưu");
		btnXoa = new JButton("Xóa");
		btnCapNhat = new JButton("Cập Nhật");
		pnButton.add(btnThem);
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);	
		pnButton.add(btnCapNhat);
	}

}
