package ctuet.edu.vn.ui.thongke;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QL_ThongKeUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btnXem;
	
	
	public QL_ThongKeUI() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}
	private void addEvents() {
		// TODO Auto-generated method stub
		
	}
	private void addControls() {
		// TODO Auto-generated method stub
		/*----------------------Top----------------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		this.add(pnTop);
		JTabbedPane tab = new JTabbedPane();
		pnTop.add(tab);
		
		// Tab Thống Kê Sản Phẩm
		JPanel pntabThongKeSanPham = new JPanel();
		tab.add(pntabThongKeSanPham,"Thống Kê Sản Phẩm");
		
					DefaultTableModel dtmDanhSachThongKeSanPham;	
					JTable tblDanhSachThongKeSanPham;
						// Bảng Danh Sách Thống Kê Sản Phẩm
						dtmDanhSachThongKeSanPham = new DefaultTableModel();
						dtmDanhSachThongKeSanPham.addColumn("Mã sản phẩm");
						dtmDanhSachThongKeSanPham.addColumn("Tên sản phẩm");
						dtmDanhSachThongKeSanPham.addColumn("Số lượng bán");
						dtmDanhSachThongKeSanPham.addColumn("Số lượng tồn");
						tblDanhSachThongKeSanPham = new JTable(dtmDanhSachThongKeSanPham);
						JScrollPane scrDanhSachThongKeSanPham = new JScrollPane(tblDanhSachThongKeSanPham,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						pntabThongKeSanPham.add(scrDanhSachThongKeSanPham,BorderLayout.CENTER);




		// Tab Thống Kê Đơn Hàng
		JPanel pntabThongKeDonHang = new JPanel();
		tab.add(pntabThongKeDonHang,"Thống Kê Đơn Hàng");
					DefaultTableModel dtmDanhSachThongKeDonHang;	
					JTable tblDanhSachThongKeDonHang;					
						// Bảng Danh Sách Thống Kê Đơn Hàng
						dtmDanhSachThongKeDonHang = new DefaultTableModel();
						dtmDanhSachThongKeDonHang.addColumn("Mã đơn hàng");
						dtmDanhSachThongKeDonHang.addColumn("Tên sản phẩm");
						dtmDanhSachThongKeDonHang.addColumn("Tên khách hàng");
						dtmDanhSachThongKeDonHang.addColumn("Ngày tạo");
						dtmDanhSachThongKeDonHang.addColumn("Giá trị");
						tblDanhSachThongKeDonHang = new JTable(dtmDanhSachThongKeDonHang);
						JScrollPane scrDanhSachThongKeDonHang = new JScrollPane(tblDanhSachThongKeDonHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						pntabThongKeDonHang.add(scrDanhSachThongKeDonHang,BorderLayout.CENTER);



		// Tab Thống Kê Khách Hàng
				JPanel pntabThongKeKhachHang = new JPanel();
				tab.add(pntabThongKeKhachHang,"Thống Kê Khách Hàng");
							DefaultTableModel dtmDanhSachThongKeKhachHang;	
							JTable tblDanhSachThongKeKhachHang;
								// Bảng Danh Sách Thống Kê Khách Hàng
								dtmDanhSachThongKeKhachHang = new DefaultTableModel();
								dtmDanhSachThongKeKhachHang.addColumn("Sdt khách hàng");
								dtmDanhSachThongKeKhachHang.addColumn("Tên khách hàng");
								dtmDanhSachThongKeKhachHang.addColumn("Tổng giá trị đơn hàng");
								dtmDanhSachThongKeKhachHang.addColumn("Ngày tạo");
								dtmDanhSachThongKeKhachHang.addColumn("Tổng số đơn hàng");
								tblDanhSachThongKeKhachHang = new JTable(dtmDanhSachThongKeKhachHang);
								JScrollPane scrDanhSachThongKeKhachHang = new JScrollPane(tblDanhSachThongKeKhachHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								pntabThongKeKhachHang.add(scrDanhSachThongKeKhachHang,BorderLayout.CENTER);
		
		
		
		
		/*----------------------Bottom----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		this.add(pnBottom);
		//Thêm nút chức năng
		btnXem = new JButton("Xem");
		pnBottom.add(btnXem);
	}

}
