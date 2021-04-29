package ctuet.edu.vn.ui.kho;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ctuet.edu.vn.model.Kho;
import ctuet.edu.vn.model.SanPham;
import ctuet.edu.vn.service.ChiTietKhoService;
import ctuet.edu.vn.service.KhoService;
import ctuet.edu.vn.service.SanPhamService;

public class NhapXuatUI extends JFrame{

	JButton btnNhapKho;
	JButton btnHuy;
	JComboBox<String> cboMaKhoNhap;
	JComboBox<String> cboMaKhoXuat;
	JComboBox<String> cboMaSanPham;
	JTextField txtSoLuong;

	SanPhamService svSanPham = new SanPhamService();
	KhoService svKho = new KhoService();
	ChiTietKhoService svChiTietKho = new ChiTietKhoService();


	public NhapXuatUI(String title) {
		super(title);
		addControls();
		addEvents();
	}

	public void showWindow() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(500,200);
		this.setVisible(true);
	}

	//Đổ danh sách mã kho cho combobox
	private void hiendanhsachmaKho() {
		//Khởi tạo combobox cboMaKhoXuat giá trị mặc định Hàng mới nhập về
		cboMaKhoXuat.addItem("Hàng mới nhập về");
		for(Kho kho : svKho.layToanBoKho()) {
			cboMaKhoNhap.addItem(kho.getMaKho());
			cboMaKhoXuat.addItem(kho.getMaKho());
		}
	}

	//Đổ danh sách mã sản phẩm cho combobox
	private void hiendanhsachmaSanPham() {
		for(SanPham sanpham : svSanPham.layToanBoSanPham()) {
			cboMaSanPham.addItem(sanpham.getMaSanPham());
		}
	}

	private void addControls() {

		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);

		/*----------------------Top----------------------------*/
		JPanel pnTop = new JPanel(new FlowLayout());
		pnMain.add(pnTop);

		//Mã kho nhập
		JPanel pnMaKhoNhap = new JPanel();
		pnMaKhoNhap.setLayout(new BoxLayout(pnMaKhoNhap, BoxLayout.Y_AXIS));
		pnTop.add(pnMaKhoNhap);
		JLabel lblMaKhoNhap = new JLabel("Nhập vào");
		pnMaKhoNhap.add(lblMaKhoNhap);
		lblMaKhoNhap.setAlignmentX(CENTER_ALIGNMENT);
		cboMaKhoNhap = new JComboBox<String>();
		pnMaKhoNhap.add(cboMaKhoNhap);

		//Mã sản phẩm
		JPanel pnMaSanPham = new JPanel();
		pnMaSanPham.setLayout(new BoxLayout(pnMaSanPham, BoxLayout.Y_AXIS));
		pnTop.add(pnMaSanPham);
		JLabel lblMaSanPham = new JLabel("Mã sản phẩm");
		pnMaSanPham.add(lblMaSanPham);
		lblMaSanPham.setAlignmentX(CENTER_ALIGNMENT);
		cboMaSanPham = new JComboBox<String>();
		pnMaSanPham.add(cboMaSanPham);

		//Số lượng
		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new BoxLayout(pnSoLuong, BoxLayout.Y_AXIS));
		pnTop.add(pnSoLuong);
		JLabel lblSoLuong = new JLabel("Số lượng");
		pnSoLuong.add(lblSoLuong);
		lblSoLuong.setAlignmentX(CENTER_ALIGNMENT);
		txtSoLuong = new JTextField(10);
		pnSoLuong.add(txtSoLuong);

		//Mã kho xuất
		JPanel pnMaKhoXuat = new JPanel();
		pnMaKhoXuat.setLayout(new BoxLayout(pnMaKhoXuat, BoxLayout.Y_AXIS));
		pnTop.add(pnMaKhoXuat);
		JLabel lblMaKhoXuat = new JLabel("Xuất từ");
		pnMaKhoXuat.add(lblMaKhoXuat);
		lblMaKhoXuat.setAlignmentX(CENTER_ALIGNMENT);
		cboMaKhoXuat = new JComboBox<String>();
		pnMaKhoXuat.add(cboMaKhoXuat);

		/*----------------------Bottom--------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		pnMain.add(pnBottom);
		btnNhapKho = new JButton("Nhập kho");
		btnHuy = new JButton("Hủy");
		pnBottom.add(btnNhapKho);
		pnBottom.add(btnHuy);

		hiendanhsachmaKho();
		hiendanhsachmaSanPham();

	}

	private void addEvents() {
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNhapKho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int soluong = Integer.parseInt(txtSoLuong.getText().toString().trim());
					if(soluong < 1) {
						JOptionPane.showMessageDialog(null, "Số lượng sản phẩm nhập tối thiểu là 1!");
					}else if(cboMaKhoNhap.getSelectedItem()==cboMaKhoXuat.getSelectedItem()) {
						JOptionPane.showMessageDialog(null, "Kho nhập và Kho xuất bị trùng!");
					}else {
						Kho khonhap = new Kho();
						Kho khoxuat = new Kho();
						SanPham sanpham = new SanPham();
						khonhap.setMaKho(cboMaKhoNhap.getSelectedItem().toString().trim());
						khoxuat.setMaKho(cboMaKhoXuat.getSelectedItem().toString().trim());
						sanpham.setMaSanPham(cboMaSanPham.getSelectedItem().toString().trim());
						//khi kho xuất được chọn là "Hàng mới nhập về"
						if(cboMaKhoXuat.getSelectedIndex() == 0) {
							//thực hiện thêm  chi tiết khi chi tiết chưa tồn tại và ngược lại
							if(svChiTietKho.soluongSanPham(khonhap, sanpham) == -1) {
								svChiTietKho.themChiTietKho(khonhap, sanpham, soluong);
								JOptionPane.showMessageDialog(null, "Nhập xuất thành công!");
							}else {
								svChiTietKho.capnhatChiTietKho(khonhap, sanpham, soluong);
								JOptionPane.showMessageDialog(null, "Nhập xuất thành công!");
							}
						}else {
							if(svChiTietKho.soluongSanPham(khoxuat, sanpham) == -1 || svChiTietKho.soluongSanPham(khoxuat, sanpham) == 0) {
								JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại trong kho xuất!");
							}else if(svChiTietKho.soluongSanPham(khoxuat, sanpham) < soluong) {
								JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không đủ xuất!");
							}else {
								//thực hiện thêm  chi tiết khi chi tiết chưa tồn tại và ngược lại
								if(svChiTietKho.soluongSanPham(khonhap, sanpham) == -1) {
									svChiTietKho.themChiTietKho(khonhap, sanpham, soluong);
									svChiTietKho.capnhatChiTietKho(khoxuat, sanpham, -soluong);
									JOptionPane.showMessageDialog(null, "Nhập xuất thành công!");
								}else {
									svChiTietKho.capnhatChiTietKho(khonhap, sanpham, soluong);
									svChiTietKho.capnhatChiTietKho(khoxuat, sanpham, -soluong);
									JOptionPane.showMessageDialog(null, "Nhập xuất thành công!");
								}
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ!");
				}
			}
		});
	}
}
