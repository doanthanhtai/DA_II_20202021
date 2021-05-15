package ctuet.edu.vn.ui.banhang;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.DonHang;
import ctuet.edu.vn.service.DonHangService;

public class QL_BanHangUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel dtmDanhSachDonHang;
	JButton btnTaoDon;
	JButton btnDaThanhToan;
	JButton btnXoa;
	JButton btnChinhSua;
	JButton btnIn;
	JTable tblDanhSachDonHang;
	DonHangService svDonHang = new DonHangService();
	
	String username;
	
	public QL_BanHangUI(String username) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.username = username;
		addControls();
		addEvents();
	}
	
	private void addControls() {
		/*----------------------Center----------------------------*/
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		this.add(pnCenter);
		//Bảng danh sách đơn hàng
		dtmDanhSachDonHang = new DefaultTableModel();
		dtmDanhSachDonHang.addColumn("Mã ĐH");
		dtmDanhSachDonHang.addColumn("Tên khách hàng");
		dtmDanhSachDonHang.addColumn("SDT khách hàng");
		dtmDanhSachDonHang.addColumn("Nhân viên");
		dtmDanhSachDonHang.addColumn("Ngày tạo");
		dtmDanhSachDonHang.addColumn("SL sản phẩm");
		dtmDanhSachDonHang.addColumn("Tổng giá trị");
		dtmDanhSachDonHang.addColumn("Trạng thái");
		tblDanhSachDonHang = new JTable(dtmDanhSachDonHang);
		JScrollPane scrDanhSachDonHang = new JScrollPane(tblDanhSachDonHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scrDanhSachDonHang,BorderLayout.CENTER);
		
		//Đổ danh sách đơn hàng lên bảng hiển thị
		hienDanhSachDonHang();
		
		
		/*----------------------Bottom----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		this.add(pnBottom);
		//Thêm nút chức năng
		btnTaoDon = new JButton("Tạo đơn");
		btnDaThanhToan = new JButton("Đã thanh toán");
		btnXoa = new JButton("Xóa");
		btnChinhSua = new JButton("Chỉnh sửa");
		btnIn = new JButton("In");
		pnBottom.add(btnTaoDon);
		pnBottom.add(btnDaThanhToan);
		pnBottom.add(btnXoa);
		pnBottom.add(btnChinhSua);
		pnBottom.add(btnIn);
		
	
	}

	@SuppressWarnings("unchecked")
	private void hienDanhSachDonHang() {
		DonHangService sv = new DonHangService();
		for(Vector<Object> vec : sv.LayToanBoDonHang()) {
			dtmDanhSachDonHang.addRow(vec);
		}
	}

	private void addEvents() {
		btnTaoDon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QL_BanHangUI.this.removeAll();
				QL_BanHangUI.this.add(new TaoDonHangUI(username));
				QL_BanHangUI.this.updateUI();	
			}
		});
		btnDaThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(tblDanhSachDonHang.getSelectedRow() > -1) {
					String maDonHang = tblDanhSachDonHang.getValueAt(tblDanhSachDonHang.getSelectedRow(), 0).toString();
					DonHang donhang = new DonHang();
					donhang.setMaDonHang(maDonHang);
					
					svDonHang.hoanthanhDonHang(donhang);
					QL_BanHangUI.this.removeAll();
					QL_BanHangUI.this.add(new QL_BanHangUI(username));
					QL_BanHangUI.this.updateUI();
				}else {
					JOptionPane.showMessageDialog(null, "Chưa chọn đơn hàng hoàn thành");
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachDonHang.getSelectedRow() > -1) {
					String maDonHang = tblDanhSachDonHang.getValueAt(tblDanhSachDonHang.getSelectedRow(), 0).toString();
					DonHang donhang = new DonHang();
					donhang.setMaDonHang(maDonHang);
					
					svDonHang.xoaDonHang(donhang);
					QL_BanHangUI.this.removeAll();
					QL_BanHangUI.this.add(new QL_BanHangUI(username));
					QL_BanHangUI.this.updateUI();
				}else {
					JOptionPane.showMessageDialog(null, "Chưa chọn đơn hàng để xóa");
				}
				
			}
		});
		btnChinhSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachDonHang.getSelectedRow() > -1) {
					String maDonHang = tblDanhSachDonHang.getValueAt(tblDanhSachDonHang.getSelectedRow(), 0).toString();
					DonHang donhang = new DonHang();
					donhang.setMaDonHang(maDonHang);
					QL_BanHangUI.this.removeAll();
					QL_BanHangUI.this.add(new ChinhSuaDonHangUI("Chỉnh sửa đơn hàng",username,donhang));
					QL_BanHangUI.this.updateUI();
				}else {
					JOptionPane.showMessageDialog(null, "Chưa chọn đơn hàng để chỉnh sửa!");
				}
				
			}
		});
		btnIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDanhSachDonHang.getSelectedRow() > -1) {
					String maDonHang = tblDanhSachDonHang.getValueAt(tblDanhSachDonHang.getSelectedRow(), 0).toString().trim();
					DonHang donhang = new DonHang();
					donhang.setMaDonHang(maDonHang);
					new InDonHangUI("In đơn hàng", donhang);
				}else {
					JOptionPane.showMessageDialog(null, "Chưa chọn đơn hàng để In!");
				}
			}
		});
	}

}
