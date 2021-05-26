package ctuet.edu.vn.ui.thongke;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.service.SanPhamService;
public class QL_ThongKeUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton btnXem;
	SanPhamService svSanPham = new SanPhamService();

	public QL_ThongKeUI() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}
	private void addEvents() {	
	}
	private void addControls() {
		
		JTabbedPane tab = new JTabbedPane();
		JPanel pntabThongKeSanPham = new JPanel(new BorderLayout());
		JPanel pntabThongKeDonHang = new JPanel(new BorderLayout());
		JPanel pntabThongKeKhachHang = new JPanel(new BorderLayout());
		
		tab.add(pntabThongKeSanPham,"Thống Kê Sản Phẩm");
		tab.add(pntabThongKeDonHang,"Thống Kê Đơn Hàng");
		tab.add(pntabThongKeKhachHang,"Thống Kê Khách Hàng");
		
		
		// Tab Thống Kê Sản Phẩm
		DefaultTableModel dtmDanhSachThongKeSanPham = new DefaultTableModel();
		
		// Bảng Danh Sách Thống Kê Sản Phẩm
		dtmDanhSachThongKeSanPham.addColumn("Mã sản phẩm");
		dtmDanhSachThongKeSanPham.addColumn("Tên sản phẩm");
		dtmDanhSachThongKeSanPham.addColumn("Số lượng bán");
		dtmDanhSachThongKeSanPham.addColumn("Số lượng tồn");
		
		JTable tblDanhSachThongKeSanPham = new JTable(dtmDanhSachThongKeSanPham);
		JScrollPane scrDanhSachThongKeSanPham = new JScrollPane(tblDanhSachThongKeSanPham,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pntabThongKeSanPham.add(scrDanhSachThongKeSanPham,BorderLayout.CENTER);



		// Tab Thống Kê Đơn Hàng
		DefaultTableModel dtmDanhSachThongKeDonHang = new DefaultTableModel();
		
		// Bảng Danh Sách Thống Kê Đơn Hàng
		dtmDanhSachThongKeDonHang.addColumn("Mã đơn hàng");
		dtmDanhSachThongKeDonHang.addColumn("Tên sản phẩm");
		dtmDanhSachThongKeDonHang.addColumn("Tên khách hàng");
		dtmDanhSachThongKeDonHang.addColumn("Ngày tạo");
		dtmDanhSachThongKeDonHang.addColumn("Giá trị");
		
		JTable tblDanhSachThongKeDonHang = new JTable(dtmDanhSachThongKeDonHang);
		JScrollPane scrDanhSachThongKeDonHang = new JScrollPane(tblDanhSachThongKeDonHang,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pntabThongKeDonHang.add(scrDanhSachThongKeDonHang,BorderLayout.CENTER);



		// Tab Thống Kê Khách Hàng
		DefaultTableModel dtmDanhSachThongKeKhachHang = new DefaultTableModel();
		
		// Bảng Danh Sách Thống Kê Khách Hàng
		dtmDanhSachThongKeKhachHang.addColumn("Sdt khách hàng");
		dtmDanhSachThongKeKhachHang.addColumn("Tên khách hàng");
		dtmDanhSachThongKeKhachHang.addColumn("Tổng giá trị đơn hàng");
		dtmDanhSachThongKeKhachHang.addColumn("Ngày tạo");
		dtmDanhSachThongKeKhachHang.addColumn("Tổng số đơn hàng");
		
		JTable tblDanhSachThongKeKhachHang = new JTable(dtmDanhSachThongKeKhachHang);
		JScrollPane scrDanhSachThongKeKhachHang = new JScrollPane(tblDanhSachThongKeKhachHang,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pntabThongKeKhachHang.add(scrDanhSachThongKeKhachHang,BorderLayout.CENTER);
		
		this.add(tab);
	}

}
