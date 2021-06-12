package ctuet.edu.vn.ui.timkiem;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ctuet.edu.vn.model.KhachHang;
import ctuet.edu.vn.model.SanPham;
import ctuet.edu.vn.service.KhachHangService;
import ctuet.edu.vn.service.SanPhamService;

public class TimKiemUI extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JComboBox<String> cboTuyChon;
	JButton btnTimKiem;
	JTextField txtTimKiem;
	
	public TimKiemUI() {
		this.setLayout(new FlowLayout());
		addControls();
		addEvents(); 
	}
	private void addControls(){
		
		//Tao đối tượng
		cboTuyChon = new JComboBox<String>();
		txtTimKiem = new JTextField();
		btnTimKiem = new JButton("Tìm kiếm");
		
		//Thêm đối tượng vào
		this.add(cboTuyChon);
		this.add(txtTimKiem);
		this.add(btnTimKiem);
		
		//Tùy chỉnh đối tượng 
		cboTuyChon.addItem("Tìm kiếm sản phẩm theo mã");
		cboTuyChon.addItem("Tìm kiếm khách hàng theo SDT");
		txtTimKiem.setPreferredSize(new Dimension(350,30));
	}
	
	private void addEvents() {
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = cboTuyChon.getSelectedIndex();
				if(selected == 0) {
					SanPhamService sv = new SanPhamService();
					SanPham sp = sv.TimSanPham(txtTimKiem.getText());
					if(sp.getMaSanPham() != null) {
						new KQ_TimKiemSanPhamUI("Thông tin chi tiết sản phẩm " + sp.getMaSanPham(),sp,sv.TonKho(sp.getMaSanPham()));
					}else {
						JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại");
					}
				}else {
					KhachHangService sv = new KhachHangService();
					KhachHang kh = sv.TimKhachHang(txtTimKiem.getText());
					if(kh.getMaKhachHang() != null) {
						new KQ_TimKiemKhachHangUI("Thông tin khách hàng", kh, sv.DanhSachDonHang(kh.getSodienthoai()));
					}else {
						JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại");
					}
				}
			}
		});
	}

}
