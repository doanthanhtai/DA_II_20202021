package ctuet.edu.vn.ui.banhang;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.ChiTietDonHang;
import ctuet.edu.vn.model.DonHang;
import ctuet.edu.vn.model.KhachHang;
import ctuet.edu.vn.model.SanPham;
import ctuet.edu.vn.service.ChiTietDonHangService;
import ctuet.edu.vn.service.DonHangService;
import ctuet.edu.vn.service.KhachHangService;
import ctuet.edu.vn.service.SanPhamService;
import ctuet.edu.vn.ui.QL_MenuUI;


public class TaoDonHangUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	String username;
	JButton btnTaoDon;
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
	long tongGiaTri;

	SanPhamService svSanPham = new SanPhamService();
	DonHangService svDonHang = new DonHangService();
	KhachHangService svKhachHang = new KhachHangService();
	ChiTietDonHangService svChiTietDonHang = new ChiTietDonHangService();

	ArrayList<ChiTietDonHang> arrChiTietDonHang;
	String maDonHang;

	public TaoDonHangUI(String username) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.username = username;
		addControls();
		addEvents();
	}

	private void hienThiChiTietDonHang() {
		tongGiaTri = 0;
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
			tongGiaTri = (long)(tongGiaTri + chitietDonHang.getGiatri());
			dtmDanhSachSanPham.addRow(vec);
		}
		lblTong.setText("Tổng: " + tongGiaTri);
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
		btnTaoDon.addActionListener(new ActionListener() {

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

					KhachHang khachhang = new KhachHang();
					khachhang.setMaKhachHang("KH" + (svKhachHang.demKhachHang() + 1));
					khachhang.setTenKhachHang(txtTenKhachHang.getText());
					khachhang.setSodienthoai(txtSDT.getText());
					svKhachHang.themKhachHang(khachhang);

					DonHang donhang = new DonHang();
					donhang.setMaDonHang("DH" + (svDonHang.demSoDonHang() + 1));
					donhang.setMaKhachHang(khachhang.getMaKhachHang());
					donhang.setMaNhanVien(username);
					donhang.setNgaytao(Date.valueOf(LocalDate.now()));
					donhang.setTrangthai(2); //trang thai đợi thanh toán
					svDonHang.themDonHang(donhang);

					for(ChiTietDonHang chitietDonHang : arrChiTietDonHang) {
						chitietDonHang.setMaDonHang(donhang.getMaDonHang());
						svChiTietDonHang.themChiTietDonHang(chitietDonHang);
					}
					JOptionPane.showMessageDialog(null, "Tạo đơn hàng thành công!");
					TaoDonHangUI.this.removeAll();
					TaoDonHangUI.this.add(new TaoDonHangUI(username));
					TaoDonHangUI.this.updateUI();
				}
			}
		});
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaoDonHangUI.this.removeAll();
				TaoDonHangUI.this.add(new QL_BanHangUI(username));
				TaoDonHangUI.this.updateUI();
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
						}else {
							JOptionPane.showMessageDialog(null, "Vui long chọn sản phẩm có trong đơn hàng!");
						}
						//Cập nhật hiển thị
						hienThiChiTietDonHang();

					}else {
						JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là số nguyên dương!");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dữ liệu số lượng sản phẩm không hợp lệ!\n\t\tHoặc sản phẩm chưa tồn tai trong đơn hàng.");
				}
			}
		});
	}
	private void addControls() {

		arrChiTietDonHang = new ArrayList<ChiTietDonHang>();
		maDonHang = "DH" + svDonHang.demSoDonHang() + 1;

		/*---------------------Top-----------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout());
		this.add(pnTop);
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout());
		pnTop.add(pnMa);
		JLabel lblMaDH = new JLabel("Mã ĐH: " + maDonHang);
		pnMa.add(lblMaDH);
		JLabel lblMaNV = new JLabel("   Mã NV:     " + username);
		pnMa.add(lblMaNV);

		JPanel pnTenKhachHang = new JPanel();
		pnTenKhachHang.setLayout(new FlowLayout());
		pnTop.add(pnTenKhachHang);
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		txtTenKhachHang = new JTextField(15);
		pnTenKhachHang.add(lblTenKhachHang);
		pnTenKhachHang.add(txtTenKhachHang);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout());
		pnTop.add(pnSDT);
		JLabel lblSDT = new JLabel("SDT");
		txtSDT = new JTextField(15);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);

		//Thêm nút chức năng tạo đơn
		btnTaoDon = new JButton("Tạo đơn");
		btnHuy = new JButton("Hủy");
		pnTop.add(btnTaoDon);
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
