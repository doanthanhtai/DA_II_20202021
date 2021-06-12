package ctuet.edu.vn.ui.thongke;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class TK_DonHang extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel dtmDanhSachThongKeDonHang;
	JFormattedTextField txtNgayBatDau,txtNgayKetThuc;
	JButton btnXem;
	MaskFormatter mfNgayThang;

	public TK_DonHang() {
		this.setLayout(new BorderLayout());
		addControls();
		addVents();
	}

	private void addVents() {
		btnXem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Chức năng chưa hoàn thiện!");
				
//				dtmDanhSachThongKeDonHang.setRowCount(0);
//				DonHangService svDonHang = new DonHangService();
//				String ngaybatdau = txtNgayBatDau.getText().toString().trim() + "-01";
//				String ngayketthuc = txtNgayKetThuc.getText().toString().trim() + "-31" ;
//				
//				for(Vector<Object> vec : svDonHang.thongkeDonHang(ngaybatdau, ngayketthuc)) {
//					dtmDanhSachThongKeDonHang.addRow(vec);
//				}
			}
		});
	}

	private void addControls() {
		
		try {
			mfNgayThang = new MaskFormatter("####-##");
		} catch (Exception e) {
			e.printStackTrace();
		}
		mfNgayThang.setPlaceholderCharacter('#');
		
		
		
		// Tab Thống Kê Đơn Hàng
		dtmDanhSachThongKeDonHang = new DefaultTableModel();

		// Bảng Danh Sách Thống Kê Đơn Hàng
		dtmDanhSachThongKeDonHang.addColumn("Mã đơn hàng");
		dtmDanhSachThongKeDonHang.addColumn("Tên khách hàng");
		dtmDanhSachThongKeDonHang.addColumn("Ngày tạo");
		dtmDanhSachThongKeDonHang.addColumn("Giá trị");

		JTable tblDanhSachThongKeDonHang = new JTable(dtmDanhSachThongKeDonHang);
		JScrollPane scrDanhSachThongKeDonHang = new JScrollPane(tblDanhSachThongKeDonHang,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrDanhSachThongKeDonHang,BorderLayout.CENTER);
		
		JPanel pnThoiGian = new JPanel(new FlowLayout());
		pnThoiGian.add(new JLabel("Từ"));
		pnThoiGian.add(txtNgayBatDau = new JFormattedTextField(mfNgayThang));
		pnThoiGian.add(new JLabel("Đến"));
		pnThoiGian.add(txtNgayKetThuc = new JFormattedTextField(mfNgayThang));
		pnThoiGian.add(btnXem = new JButton("Xem"));
		
		this.add(pnThoiGian,BorderLayout.NORTH);
		
	}
}
