package ctuet.edu.vn.ui.khachhang;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.KhachHang;
import ctuet.edu.vn.service.KhachHangService;


public class QL_KhachHangUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DefaultTableModel dtmDanhSachKhachHang;
	JTable tblDanhSachKhachHang;
	KhachHangService svKhachHang = new KhachHangService();
	public QL_KhachHangUI() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEvents();
	}
	
	private void addControls()
	{
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		this.add(pnTop);
		// Bảng Danh Sách Nhân Viên
		dtmDanhSachKhachHang = new DefaultTableModel();
		dtmDanhSachKhachHang.addColumn("Họ và tên");
		dtmDanhSachKhachHang.addColumn("SDT khách hàng");
		dtmDanhSachKhachHang.addColumn("Giá trị đơn cao nhất");
		dtmDanhSachKhachHang.addColumn("Tổng số lương");
		dtmDanhSachKhachHang.addColumn("Đơn gần nhất");
		tblDanhSachKhachHang = new JTable(dtmDanhSachKhachHang);
		JScrollPane scrDanhSachKhachHang = new JScrollPane(tblDanhSachKhachHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(scrDanhSachKhachHang,BorderLayout.CENTER);
		
		
		
	}
	
	private void addEvents() {
		// TODO Auto-generated method stub
		hienDanhSachKhachHang();
		
		
		
	}
	public void hienDanhSachKhachHang() {
		dtmDanhSachKhachHang.setRowCount(0);
		for(KhachHang khachhang : svKhachHang.layToanBoKhachHang()) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(khachhang.getTenKhachHang());
			vec.add(khachhang.getSodienthoai());

			dtmDanhSachKhachHang.addRow(vec);

		}
	}


}
