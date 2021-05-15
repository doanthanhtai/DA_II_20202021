package ctuet.edu.vn.ui.timkiem;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.SanPham;

public class KQ_TimKiemSanPhamUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenSanPham;
	private JTextField txtMauSac;
	private JTextField txtKichThuoc;
	private JTextField txtNguonCap;
	private JTextField txtGiaBan;
	private JButton btnHinhAnh;
	private DefaultTableModel dtmTonKho;

	public KQ_TimKiemSanPhamUI(String title,SanPham sp,@SuppressWarnings("rawtypes") ArrayList<Vector> arrTonKho) {
		super(title);
		addControls(sp,arrTonKho);
		showWindow();
	}

	private void showWindow() {
		this.setSize(500, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	private void addControls(SanPham sp,@SuppressWarnings("rawtypes") ArrayList<Vector> arrTonKho) {
		
		/*---------------------------------------------------------*/
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		/*--------------------------Top----------------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout());
		pnMain.add(pnTop);
		//TopLeft
		JPanel pnTopLeft = new JPanel();
		pnTopLeft.setLayout(new BoxLayout(pnTopLeft, BoxLayout.Y_AXIS));
		pnTop.add(pnTopLeft);
		//Tên sản phẩm
		JPanel pnTenSanPham = new JPanel();
		pnTenSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnTopLeft.add(pnTenSanPham);
		JLabel lblTenSanPham = new JLabel("Tên sp");
		txtTenSanPham = new JTextField(20);
		pnTenSanPham.add(lblTenSanPham);
		pnTenSanPham.add(txtTenSanPham);
		//Màu sắc sản phẩm
		JPanel pnMauSac = new JPanel();
		pnMauSac.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnTopLeft.add(pnMauSac);
		JLabel lblMauSac = new JLabel("Màu sắc");
		txtMauSac = new JTextField(20);
		pnMauSac.add(lblMauSac);
		pnMauSac.add(txtMauSac);
		//Kích thước sản phẩm
		JPanel pnKichThuoc = new JPanel();
		pnKichThuoc.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnTopLeft.add(pnKichThuoc);
		JLabel lblKichThuoc = new JLabel("Kích thước");
		txtKichThuoc = new JTextField(20);
		pnKichThuoc.add(lblKichThuoc);
		pnKichThuoc.add(txtKichThuoc);
		//Nguồn cung cấp
		JPanel pnNguonCap = new JPanel();
		pnNguonCap.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnTopLeft.add(pnNguonCap);
		JLabel lblNguonCap = new JLabel("Nguồn cấp");
		txtNguonCap = new JTextField(20);
		pnNguonCap.add(lblNguonCap);
		pnNguonCap.add(txtNguonCap);
		//Giá bán
		JPanel pnGiaBan = new JPanel();
		pnGiaBan.setLayout(new FlowLayout());
		pnTopLeft.add(pnGiaBan);
		JLabel lblGiaBan = new JLabel("Giá bán");
		txtGiaBan = new JTextField(20);
		pnGiaBan.add(lblGiaBan);
		pnGiaBan.add(txtGiaBan);
		//TopRight
		JPanel pnTopRight = new JPanel();
		pnTopRight.setLayout(new BorderLayout());
		pnTop.add(pnTopRight);
		btnHinhAnh = new JButton();
		pnTopRight.add(btnHinhAnh,BorderLayout.CENTER);
		
		/*----------------------Botom-------------------------------*/
		JPanel pnBotom = new JPanel();
		pnBotom.setLayout(new BorderLayout());
		pnMain.add(pnBotom);
		JLabel lblTileTableTonKho = new JLabel("Tình trạng tồn");
		pnBotom.add(lblTileTableTonKho);
		dtmTonKho = new DefaultTableModel();
		dtmTonKho.addColumn("Mã kho");
		dtmTonKho.addColumn("Tên kho");
		dtmTonKho.addColumn("SL");
		JTable tblTonKho = new JTable(dtmTonKho);
		JScrollPane scrTonKho = new JScrollPane(tblTonKho,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBotom.add(scrTonKho);
		
		//Tùy chỉnh kích thước
		
		lblTenSanPham.setPreferredSize(lblKichThuoc.getPreferredSize());
		lblMauSac.setPreferredSize(lblKichThuoc.getPreferredSize());
		lblNguonCap.setPreferredSize(lblKichThuoc.getPreferredSize());
		lblGiaBan.setPreferredSize(lblKichThuoc.getPreferredSize());
		
		//Đổ dữ liệu
		addData(sp,arrTonKho);
	}

	private void addData(SanPham sp,@SuppressWarnings("rawtypes") ArrayList<Vector> arrTonKho) {
		txtTenSanPham.setText(sp.getTenSanPham());
		txtMauSac.setText(sp.getMauSac());
		txtKichThuoc.setText(sp.getKichthuoc() + "");
		txtNguonCap.setText(sp.getNguoncungcap());
		txtGiaBan.setText(sp.getGiaban() + "");
		btnHinhAnh.setIcon(new ImageIcon("image/product_96px.png"));
		try {
			File fileImage = new File(sp.getHinhanh());
			if(fileImage.exists())
				btnHinhAnh.setIcon(new ImageIcon(sp.getHinhanh()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		for(@SuppressWarnings("rawtypes") Vector vec : arrTonKho) {
			dtmTonKho.addRow(vec);
		}
	}	
}
