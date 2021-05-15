package ctuet.edu.vn.ui.banhang;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

import ctuet.edu.vn.model.ChiTietDonHang;
import ctuet.edu.vn.model.DonHang;
import ctuet.edu.vn.model.KhachHang;
import ctuet.edu.vn.model.SanPham;
import ctuet.edu.vn.service.ChiTietDonHangService;
import ctuet.edu.vn.service.DonHangService;
import ctuet.edu.vn.service.KhachHangService;
import ctuet.edu.vn.service.SanPhamService;

public class ChinhSuaDonHangUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username;
	JButton btnLuu;
	JButton btnDaThanhToan;
	JButton btnHuy;
	JTextField txtTenKhachHang;
	JTextField txtSDT;
	JTextField txtSoLuong;
	DefaultTableModel dtmDanhSachSanPham;
	JTable tblDanhSachSanPham;
	JLabel lblTong;
	JComboBox<String> cboMaSanPham;
	JButton btnThem;
	JButton btnCapNhat;
	JTextField txtTenSanPham;


	SanPhamService svSanPham = new SanPhamService();
	DonHangService svDonHang = new DonHangService();
	KhachHangService svKhachHang = new KhachHangService();
	ChiTietDonHangService svChiTietDonHang = new ChiTietDonHangService();
	
	ArrayList<ChiTietDonHang> arrChiTietDonHang;
	KhachHang khachhang;
	DonHang donhang;
	
	public ChinhSuaDonHangUI(String title,String username,DonHang donhang) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.username = username;
		this.donhang = donhang;
		addControls();
		addEvents();
		
		hienThiChiTietDonHang();
		
	}
	
	private void hienThiChiTietDonHang() {
		dtmDanhSachSanPham.setRowCount(0);
		for(ChiTietDonHang chitietDonHang : arrChiTietDonHang) {
			SanPham sp = svSanPham.TimSanPham(chitietDonHang.getMaSanPham());
			Vector<String> vec = new Vector<String>();
			vec.add(sp.getMaSanPham());
			vec.add(sp.getTenSanPham());
			vec.add(sp.getMauSac());
			vec.add(sp.getKichthuoc() + "");
			vec.add(sp.getGiaban() + "");
			vec.add(chitietDonHang.getSoluong() + "");
			vec.add(chitietDonHang.getGiatri() + "");
			dtmDanhSachSanPham.addRow(vec);
		}
	}
	
	private int vitriSanPhamDaCo(String maSanPham) {
		int vitriSanPham = -1;
		for (ChiTietDonHang chitietDonHang : arrChiTietDonHang) {
			if(chitietDonHang.getMaSanPham().toString().equals(maSanPham)) {
				vitriSanPham = arrChiTietDonHang.indexOf(chitietDonHang);
			}
		}
		return vitriSanPham;
	}
	
	//Chỉnh sửa chi tiết đơn hàng theo vị trí trong danh sách chi tiết đơn hàng
	private void chinhsuaArrayChiTietDonHang(int vitri,ChiTietDonHang chitietDonHang) {
		arrChiTietDonHang.set(vitri,chitietDonHang);
	}
	
	private void addEvents() {
		
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tenKhachHang = txtTenKhachHang.getText();
				String sdtKhachHang = txtSDT.getText();
				if(tenKhachHang.equals("")) {
					JOptionPane.showMessageDialog(null, "Tên khách hàng không được bỏ trống!");
				}else if(sdtKhachHang.equals("")) {
					JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được bỏ trống!");
				}else if(arrChiTietDonHang.isEmpty()){
					JOptionPane.showMessageDialog(null, "Đơn hàng chưa có sản phẩm!");
				}else {
					khachhang.setTenKhachHang(txtTenKhachHang.getText());
					khachhang.setSodienthoai(txtSDT.getText());
					svKhachHang.capnhatKhachHang(khachhang);
							
					for(ChiTietDonHang chitietDonHang : arrChiTietDonHang) {
						chitietDonHang.setMaDonHang(donhang.getMaDonHang());
						svChiTietDonHang.capnhatChiTietDonHang(chitietDonHang);
						
					}
					JOptionPane.showMessageDialog(null, "Tạo đơn hàng thành công!");
					ChinhSuaDonHangUI.this.removeAll();
					ChinhSuaDonHangUI.this.add(new QL_BanHangUI(username));
					ChinhSuaDonHangUI.this.updateUI();
				}
			}
		});
		
		cboMaSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtTenSanPham.setText(svSanPham.TimSanPham(cboMaSanPham.getSelectedItem().toString()).getTenSanPham());
			}
		});
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Ép kiểu lấy giá trị số lượng
					int soluong = Integer.parseInt(txtSoLuong.getText().toString());

					if(soluong >= 0){
						SanPham sp = svSanPham.TimSanPham(cboMaSanPham.getSelectedItem().toString());
						int vitriSanPham = vitriSanPhamDaCo(sp.getMaSanPham());
						//Khi sản phẩm đã có trong danh sách
						if(vitriSanPham != -1) {
							//Khi số lượng mới bằng 0
							if(soluong == 0) {
								arrChiTietDonHang.remove(vitriSanPham);
								JOptionPane.showMessageDialog(null, "Sản phẩm đã được loại bỏ khỏi đơn hàng!");
							}else {
								ChiTietDonHang chitietDonHang = new ChiTietDonHang();
								chitietDonHang.setMaDonHang(svDonHang.demSoDonHang() + 1 + "");
								chitietDonHang.setMaSanPham(sp.getMaSanPham());
								chitietDonHang.setSoluong(soluong);
								chitietDonHang.setGiatri(sp.getGiaban() * chitietDonHang.getSoluong() );
								chinhsuaArrayChiTietDonHang(vitriSanPham, chitietDonHang);	
							}	
						}else {
							//Khi số lượng mới khác 0 và sản phẩm chưa tồn tại
							if(soluong != 0) {

								//Tạo một chi tiết mới
								ChiTietDonHang chitietDonHang = new ChiTietDonHang();
								chitietDonHang.setMaDonHang(svDonHang.demSoDonHang() + 1 + "");
								chitietDonHang.setMaSanPham(sp.getMaSanPham());
								chitietDonHang.setSoluong(soluong);
								chitietDonHang.setGiatri(sp.getGiaban() * chitietDonHang.getSoluong() );
								arrChiTietDonHang.add(chitietDonHang);

							}						
						}

						//Cập nhật lại danh sách
						hienThiChiTietDonHang();
					}else {
						JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là số nguyên dương!");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dữ liệu số lượng sản phẩm không hợp lệ!\n\t\tVui lòng kiểm tra lại.");
				}

			}
		});
		btnCapNhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int soluong = Integer.parseInt(txtSoLuong.getText().toString());
					SanPham sp = svSanPham.TimSanPham(cboMaSanPham.getSelectedItem().toString());
					int vitriSanPham = vitriSanPhamDaCo(sp.getMaSanPham());
					if(soluong >= 0){
						//Khi sản phẩm đã có trong danh sách
						if(vitriSanPham != -1) {
							//Khi số lượng mới bằng 0
							if(soluong == 0) {
								arrChiTietDonHang.remove(vitriSanPham);
								JOptionPane.showMessageDialog(null, "Sản phẩm đã được loại bỏ khỏi đơn hàng!");
							}else {
								ChiTietDonHang chitietDonHang = new ChiTietDonHang();
								chitietDonHang.setMaDonHang(svDonHang.demSoDonHang() + 1 + "");
								chitietDonHang.setMaSanPham(sp.getMaSanPham());
								chitietDonHang.setSoluong(soluong);
								chitietDonHang.setGiatri(sp.getGiaban() * chitietDonHang.getSoluong() );
								chinhsuaArrayChiTietDonHang(vitriSanPham, chitietDonHang);	
							}
							//Cập nhật hiển thị
							hienThiChiTietDonHang();
						}else {
							JOptionPane.showMessageDialog(null, "Vui long chọn sản phẩm có trong đơn hàng!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là số nguyên dương!");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dữ liệu số lượng sản phẩm không hợp lệ!\n\t\tHoặc sản phẩm chưa tồn tai trong đơn hàng.");
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
				String maSanPham = (String) tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 0);
				String tenSanPham = (String) tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 1);
				String soluongSanPham = (String) tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 5);
				cboMaSanPham.setSelectedItem(maSanPham);
				txtTenSanPham.setText(tenSanPham);
				txtSoLuong.setText(soluongSanPham);
			}
		});
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChinhSuaDonHangUI.this.removeAll();
				ChinhSuaDonHangUI.this.add(new QL_BanHangUI(username));
				ChinhSuaDonHangUI.this.updateUI();
			}
		});
	}
	private void addControls() {
		donhang = svDonHang.layDonHangTheoMa(donhang);
		khachhang = svKhachHang.LayKhachHangTheoMa(donhang.getMaKhachHang());
		arrChiTietDonHang = svChiTietDonHang.layToanBoChiTietDonHang(donhang);
		
		/*---------------------Top-----------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout());
		this.add(pnTop);
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout());
		pnTop.add(pnMa);
		JLabel lblMaDH = new JLabel("Mã ĐH: " + donhang.getMaDonHang());
		pnMa.add(lblMaDH);
		JLabel lblMaNV = new JLabel("   Mã NV:     " + username);
		pnMa.add(lblMaNV);

		JPanel pnTenKhachHang = new JPanel();
		pnTenKhachHang.setLayout(new FlowLayout());
		pnTop.add(pnTenKhachHang);
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		txtTenKhachHang = new JTextField(15);
		txtTenKhachHang.setText(khachhang.getTenKhachHang());
		pnTenKhachHang.add(lblTenKhachHang);
		pnTenKhachHang.add(txtTenKhachHang);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout());
		pnTop.add(pnSDT);
		JLabel lblSDT = new JLabel("SDT");
		txtSDT = new JTextField(15);
		txtSDT.setText(khachhang.getSodienthoai());
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);

		//Thêm nút chức năng tạo đơn
		btnLuu = new JButton("Lưu");
		btnHuy = new JButton("Hủy");
		pnTop.add(btnLuu);
		pnTop.add(btnHuy);

		/*---------------------Center-----------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		this.add(pnCenter);
		dtmDanhSachSanPham = new DefaultTableModel();
		dtmDanhSachSanPham.addColumn("Mã SP");
		dtmDanhSachSanPham.addColumn("Tên sản phẩm");
		dtmDanhSachSanPham.addColumn("Màu sắc");
		dtmDanhSachSanPham.addColumn("Kích thước");
		dtmDanhSachSanPham.addColumn("Giá");
		dtmDanhSachSanPham.addColumn("Số lượng");
		dtmDanhSachSanPham.addColumn("Thành tiền");
		tblDanhSachSanPham = new JTable(dtmDanhSachSanPham);
		JScrollPane scrDanhSachSanPham = new JScrollPane(tblDanhSachSanPham,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scrDanhSachSanPham,BorderLayout.CENTER);

		JPanel pnTong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnCenter.add(pnTong,BorderLayout.SOUTH);
		lblTong = new JLabel("Tổng: 0");
		pnTong.add(lblTong);
		/*---------------------Bottom-----------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom,BoxLayout.Y_AXIS));
		this.add(pnBottom);

		//Thêm nút chức năng thêm sản phẩm
		JPanel pnButtonSanPham = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnBottom.add(pnButtonSanPham);
		JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
		pnButtonSanPham.add(lblMaSanPham);
		//Lấy danh sách mã sản phẩm thêm vào combobox
		cboMaSanPham = new JComboBox<String>();
		for(SanPham sp : (new SanPhamService().layToanBoSanPham())) {
			cboMaSanPham.addItem(sp.getMaSanPham());
		}
		pnButtonSanPham.add(cboMaSanPham);
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		pnButtonSanPham.add(lblTenSanPham);
		txtTenSanPham = new JTextField(20);
		pnButtonSanPham.add(txtTenSanPham);
		JLabel lblSoLuong = new JLabel("Số lượng:");
		pnButtonSanPham.add(lblSoLuong);
		txtSoLuong = new JTextField(8);
		txtSoLuong.setText("1");
		pnButtonSanPham.add(txtSoLuong);
		btnThem = new JButton("Thêm");
		pnButtonSanPham.add(btnThem);
		btnCapNhat = new JButton("Cập nhật");
		pnButtonSanPham.add(btnCapNhat);

	}
}
