package ctuet.edu.vn.ui.thongke;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.SanPham;
import ctuet.edu.vn.service.SanPhamService;

public class TK_SanPham extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel dtmDanhSachThongKeSanPham;
	
	public TK_SanPham() {
		this.setLayout(new BorderLayout());
		addControls();
		addEvents();
		addData();
	}

	private void addData() {
		SanPhamService svSanPham = new SanPhamService();
		Vector<Object> vec;
		for(SanPham sp : svSanPham.layToanBoSanPham()) {
			vec = new Vector<Object>();
			vec.add(sp.getMaSanPham());
			vec.add(sp.getTenSanPham());
			vec.add(svSanPham.tongban(sp));
			vec.add(svSanPham.tongton(sp));
			
			dtmDanhSachThongKeSanPham.addRow(vec);
		}
	}

	private void addEvents() {
		// TODO Auto-generated method stub

	}
	
	private void addControls() {
		
		dtmDanhSachThongKeSanPham = new DefaultTableModel();

		// Bảng Danh Sách Thống Kê Sản Phẩm
		dtmDanhSachThongKeSanPham.addColumn("Mã sản phẩm");
		dtmDanhSachThongKeSanPham.addColumn("Tên sản phẩm");
		dtmDanhSachThongKeSanPham.addColumn("Số lượng bán");
		dtmDanhSachThongKeSanPham.addColumn("Số lượng tồn");

		JTable tblDanhSachThongKeSanPham = new JTable(dtmDanhSachThongKeSanPham);
		JScrollPane scrDanhSachThongKeSanPham = new JScrollPane(tblDanhSachThongKeSanPham,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrDanhSachThongKeSanPham,BorderLayout.CENTER);
		
	}
}
