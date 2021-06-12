package ctuet.edu.vn.ui.thongke;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ctuet.edu.vn.service.SanPhamService;
public class QL_ThongKeUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton btnXem;
	SanPhamService svSanPham = new SanPhamService();

	public QL_ThongKeUI(String username) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if(username.trim().equals("admin")){
			addControls();
			addEvents();
		}else {
			
		}
		
	}
	private void addEvents() {	
	}
	private void addControls() {

		JTabbedPane tab = new JTabbedPane();

		TK_SanPham pntabThongKeSanPham = new TK_SanPham();
		TK_DonHang pntabThongKeDonHang = new TK_DonHang();

		tab.add(pntabThongKeSanPham,"Thống Kê Sản Phẩm");
		tab.add(pntabThongKeDonHang,"Thống Kê Đơn Hàng");

		this.add(tab);
	}

}
