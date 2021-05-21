package ctuet.edu.vn.ui.nhanvien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QL_NhanVienUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btnThem;
	JButton btnChinhSua;
	JButton btnXoa;
	
	JTable tblDanhSachNhanVien;
	DefaultTableModel dtmDanhSachNhanVien;

	public QL_NhanVienUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addControls();
		addEventls();
	}

	private void addEventls() {
		
	}

	private void addControls() {
		//-----------------------Top------------------------//
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		this.add(pnTop);
		dtmDanhSachNhanVien = new DefaultTableModel();
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("Họ và tên");
		dtmDanhSachNhanVien.addColumn("SDT");
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("CMND");
		dtmDanhSachNhanVien.addColumn("CMND");
		
		tblDanhSachNhanVien = new JTable(dtmDanhSachNhanVien);
		JScrollPane scrDanhSachNhanVien = new JScrollPane(tblDanhSachNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(scrDanhSachNhanVien);
		//-----------------------Top------------------------//
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		this.add(pnBottom);
		btnThem = new JButton("Thêm");
		btnChinhSua = new JButton("Chỉnh sửa");
		btnXoa = new JButton("Xóa");
		pnBottom.add(btnThem);
		pnBottom.add(btnChinhSua);
		pnBottom.add(btnXoa);
		
	}

}
