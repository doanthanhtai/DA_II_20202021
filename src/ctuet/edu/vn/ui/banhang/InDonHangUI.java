package ctuet.edu.vn.ui.banhang;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class InDonHangUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username;
	JButton btnHuy;
	JButton btnIn;
	JTextField txtTenKhachHang;
	JTextField txtSDT;
	DefaultTableModel dtmDanhSachSanPham;
	JTable tblDanhSachSanPham;
	JLabel lblTong;
	JLabel lblNgayTao;
	JTextField txtTenSanPham;
	long tongGiaTri;


	SanPhamService svSanPham = new SanPhamService();
	DonHangService svDonHang = new DonHangService();
	KhachHangService svKhachHang = new KhachHangService();
	ChiTietDonHangService svChiTietDonHang = new ChiTietDonHangService();
	
	ArrayList<ChiTietDonHang> arrChiTietDonHang;
	KhachHang khachhang;
	DonHang donhang;
	
	public InDonHangUI(String title,DonHang donhang) {
		super(title);
		this.donhang = donhang;
		addControls();
		addEventls();
		showWindow();
		hienThiChiTietDonHang();
	}
	private void hienThiChiTietDonHang() {
		dtmDanhSachSanPham.setRowCount(0);
		tongGiaTri = 0;
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
			tongGiaTri = (tongGiaTri + chitietDonHang.getGiatri());
			dtmDanhSachSanPham.addRow(vec);
		}
		lblTong.setText("T???ng: " + tongGiaTri);
	}
	
	private void showWindow() {
		this.setSize(700,500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private void addEventls() {
		btnHuy.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ch???c n??ng ch??a ho??n thi???n!");
			}
		});
	}
	private void addControls() {
		//L???y ????n h??ng t??? c?? s??? d??? li???u kh???p v???i m?? ????n h??ng c???a ?????i t?????ng ????n h??ng
				donhang = svDonHang.layDonHangTheoMa(donhang);
				khachhang = svKhachHang.LayKhachHangTheoMa(donhang.getMaKhachHang());
				arrChiTietDonHang = svChiTietDonHang.layToanBoChiTietDonHang(donhang);
				
				Container con = getContentPane();
				JPanel pnMain = new JPanel(new BorderLayout());
				con.add(pnMain);
				
				/*---------------------Top-----------------------*/
				JPanel pnTop = new JPanel();
				pnTop.setLayout(new FlowLayout());
				pnMain.add(pnTop,BorderLayout.NORTH);
				JPanel pnMa = new JPanel();
				pnMa.setLayout(new FlowLayout());
				pnTop.add(pnMa);
				JLabel lblMaDH = new JLabel("M?? ??H: " + donhang.getMaDonHang());
				pnMa.add(lblMaDH);
				JLabel lblMaNV = new JLabel("   M?? NV:     " + username);
				pnMa.add(lblMaNV);

				JPanel pnTenKhachHang = new JPanel();
				pnTenKhachHang.setLayout(new FlowLayout());
				pnTop.add(pnTenKhachHang);
				JLabel lblTenKhachHang = new JLabel("T??n kh??ch h??ng");
				txtTenKhachHang = new JTextField(15);
				txtTenKhachHang.setText(khachhang.getTenKhachHang());
				pnTenKhachHang.add(lblTenKhachHang);
				pnTenKhachHang.add(txtTenKhachHang);

				JPanel pnSDT = new JPanel();
				pnSDT.setLayout(new FlowLayout());
				pnTop.add(pnSDT);
				JLabel lblSDT = new JLabel("SDT");
				txtSDT = new JTextField(8);
				txtSDT.setText(khachhang.getSodienthoai());
				pnSDT.add(lblSDT);
				pnSDT.add(txtSDT);

				/*---------------------Center-----------------------*/
				JPanel pnCenter = new JPanel();
				pnCenter.setLayout(new BorderLayout());
				pnMain.add(pnCenter,BorderLayout.CENTER);
				dtmDanhSachSanPham = new DefaultTableModel();
				dtmDanhSachSanPham.addColumn("M?? SP");
				dtmDanhSachSanPham.addColumn("T??n s???n ph???m");
				dtmDanhSachSanPham.addColumn("M??u s???c");
				dtmDanhSachSanPham.addColumn("K??ch th?????c");
				dtmDanhSachSanPham.addColumn("Gi??");
				dtmDanhSachSanPham.addColumn("S??? l?????ng");
				dtmDanhSachSanPham.addColumn("Th??nh ti???n");
				tblDanhSachSanPham = new JTable(dtmDanhSachSanPham);
				pnCenter.add(tblDanhSachSanPham,BorderLayout.CENTER);
				
				JPanel pnTong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				pnCenter.add(pnTong,BorderLayout.SOUTH);
				lblNgayTao = new JLabel(donhang.getNgaytao() + "");
				lblTong = new JLabel("T???ng: ");
				pnTong.add(lblNgayTao);
				pnTong.add(lblTong);
				
				hienThiChiTietDonHang();
				/*---------------------Bottom-----------------------*/
				JPanel pnBottom = new JPanel();
				pnBottom.setLayout(new FlowLayout());
				pnMain.add(pnBottom,BorderLayout.SOUTH);
				btnIn = new JButton("In ra file");
				btnHuy = new JButton("H???y");
				pnBottom.add(btnIn);
				pnBottom.add(btnHuy);
	}
}
