package ctuet.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ctuet.edu.vn.ui.banhang.QL_BanHangUI;
import ctuet.edu.vn.ui.khachhang.QL_KhachHangUI;
import ctuet.edu.vn.ui.kho.QL_KhoUI;
import ctuet.edu.vn.ui.nhanvien.QL_NhanVienUI;
import ctuet.edu.vn.ui.sanpham.QL_SanPhamUI;
import ctuet.edu.vn.ui.taikhoan.QL_TaiKhoanUI;
import ctuet.edu.vn.ui.thongke.QL_ThongKeUI;
import ctuet.edu.vn.ui.timkiem.TimKiemUI;

public class QL_MenuUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	JTabbedPane tabChucNang;
	public QL_MenuUI(String title,String username){
		super(title);
		this.username = username;
		addControls();
		addEvents();
		showWindow();
	}

	private void addControls() {
		
		//Tạo đối tượng		
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		JPanel pnContent = new JPanel();
		TimKiemUI pnTimKiem = new TimKiemUI();
		QL_BanHangUI pnBanHang = new QL_BanHangUI(username);
		QL_SanPhamUI pnSanPham = new QL_SanPhamUI(username);
		QL_KhoUI pnKho = new QL_KhoUI(username);
		QL_TaiKhoanUI pnTaiKhoan = new QL_TaiKhoanUI(username);
		QL_NhanVienUI pnNhanVien = new QL_NhanVienUI();
		QL_ThongKeUI pnThongKe = new QL_ThongKeUI();
		QL_KhachHangUI pnKhachHang = new QL_KhachHangUI();
		tabChucNang = new JTabbedPane();
		
		//Tùy chỉnh đối tượng
		pnMain.setLayout(new BorderLayout());
		pnContent.setLayout(new BorderLayout());
		tabChucNang.setTabPlacement(JTabbedPane.LEFT);

		
		//Thêm đối tượng
		tabChucNang.addTab("  Bán hàng",new ImageIcon("image/sell_64px.png"),pnBanHang);
		tabChucNang.addTab("  Sản phẩm",new ImageIcon("image/product_64px.png"),pnSanPham);
		tabChucNang.addTab("       Kho",new ImageIcon("image/warehouse_64px.png"),pnKho);
		tabChucNang.addTab(" Tài khoản",new ImageIcon("image/account_64px.png"),pnTaiKhoan);
		tabChucNang.addTab(" Nhân viên",new ImageIcon("image/staff_64px.png"),pnNhanVien);
		tabChucNang.addTab("  Thống kê",new ImageIcon("image/statistics_64px.png"),pnThongKe);
		tabChucNang.addTab("Khách Hàng",new ImageIcon("image/customer_64px.png"), pnKhachHang);
		
		
		pnContent.add(tabChucNang);
		pnMain.add(pnContent,BorderLayout.CENTER);
		pnMain.add(pnTimKiem,BorderLayout.NORTH);
		con.add(pnMain);
	}

	private void showWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(1080,700);
	}

	private void addEvents() {
		tabChucNang.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				showWindow();
			}
		});
	}

}
