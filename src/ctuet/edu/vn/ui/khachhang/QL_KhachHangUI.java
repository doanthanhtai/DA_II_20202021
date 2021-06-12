package ctuet.edu.vn.ui.khachhang;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BoxLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.service.DonHangService;


public class QL_KhachHangUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DefaultTableModel dtmDanhSachKhachHang;
	JTable tblDanhSachKhachHang;
	public QL_KhachHangUI(String username) {
		// TODO Auto-generated constructor stub
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		if(username.trim().equals("admin")) {
			addControls();
			addEvents();
			hienthidulieuKhachHang();
		}else {

		}
	}

	@SuppressWarnings("unchecked")
	private void hienthidulieuKhachHang() {
		DonHangService svDonHang = new DonHangService();
		//Vòng lặp chạy duyệt tất cả đơn hàng
		for(Vector<Object> vecDonHang : svDonHang.LayToanBoDonHang()) {
			//Tạo một vector lưu thông tin cho một dòng sẽ đổ lên bảng
			Vector<Object> vecKhachHang = new Vector<Object>();

			//lấy tên khách hàng trong vecDonHang gán cho vecKhachHang
			vecKhachHang.add(vecDonHang.get(1));
			//Lấy sdt khách hàng trong vecDonHang gán cho vecKhachHang
			vecKhachHang.add(vecDonHang.get(2));
			//Lấy gia trị đơn hàng trong vecDonHang gan cho vecKhachHang
			vecKhachHang.add(vecDonHang.get(6));
			//Lấy số lượng sp trong vecDonHang gán cho vecKhachHang
			vecKhachHang.add(vecDonHang.get(5));
			//Lấy ngày tạo đơn hàng trong vecDonHang gán cho vecKhachHang
			vecKhachHang.add(vecDonHang.get(4));

			dtmDanhSachKhachHang.addRow(vecKhachHang);
		}
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
		dtmDanhSachKhachHang.addColumn("Giá trị đơn");
		dtmDanhSachKhachHang.addColumn("Số lượng SP");
		dtmDanhSachKhachHang.addColumn("Ngày tạo");
		tblDanhSachKhachHang = new JTable(dtmDanhSachKhachHang);
		JScrollPane scrDanhSachKhachHang = new JScrollPane(tblDanhSachKhachHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(scrDanhSachKhachHang,BorderLayout.CENTER);



	}

	private void addEvents() {
		// TODO Auto-generated method stub

	}


}
