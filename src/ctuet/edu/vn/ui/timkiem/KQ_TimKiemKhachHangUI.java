package ctuet.edu.vn.ui.timkiem;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ctuet.edu.vn.model.KhachHang;

public class KQ_TimKiemKhachHangUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private DefaultTableModel dtmDanhSachDonHang;
	
	public KQ_TimKiemKhachHangUI(String title,KhachHang kh,@SuppressWarnings("rawtypes") ArrayList<Vector> arrDanhSachDonHang) {
		super(title);
		addControls(kh,arrDanhSachDonHang);
		showWindow();
	}
	
	private void showWindow() {
		this.setSize(500, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void addControls(KhachHang kh,@SuppressWarnings("rawtypes") ArrayList<Vector> arrDanhSachDonHang) {
		/*---------------------------------------------------------*/
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		/*----------------------Top--------------------------------*/
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout());
		pnMain.add(pnTop);
		//TopLeft
		JPanel pnTopLeft = new JPanel();
		pnTopLeft.setLayout(new BoxLayout(pnTopLeft, BoxLayout.Y_AXIS));
		pnTop.add(pnTopLeft);
		//Tên khách hàng
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		txtTenKhachHang = new JTextField(20);
		pnTopLeft.add(lblTenKhachHang);
		pnTopLeft.add(txtTenKhachHang);
		//TopRight
		JPanel pnTopRight = new JPanel();
		pnTopRight.setLayout(new BoxLayout(pnTopRight, BoxLayout.Y_AXIS));
		pnTop.add(pnTopRight);
		//Số điện thoại
		JLabel lblSDT = new JLabel("Số điện thoại");
		txtSDT = new JTextField(20);
		pnTopRight.add(lblSDT);
		pnTopRight.add(txtSDT);
		
		/*----------------------Bottom-----------------------------*/
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnMain.add(pnBottom);
		dtmDanhSachDonHang = new DefaultTableModel();
		dtmDanhSachDonHang.addColumn("Mã ĐH");
		dtmDanhSachDonHang.addColumn("Ngày tạo");
		dtmDanhSachDonHang.addColumn("Giá trị");
		dtmDanhSachDonHang.addColumn("Trạng thái");
		JTable tblDanhSachDonHang = new JTable(dtmDanhSachDonHang);
		JScrollPane scrDanhSachDonHang = new JScrollPane(tblDanhSachDonHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(scrDanhSachDonHang,BorderLayout.CENTER);
		
		//Đổ dữ liệu
		addData(kh,arrDanhSachDonHang);
		
	}
	private void addData(KhachHang kh,@SuppressWarnings("rawtypes") ArrayList<Vector> arrDanhSachDonHang) {
		txtTenKhachHang.setText(kh.getTenKhachHang());
		txtSDT.setText(kh.getSodienthoai());
		for(@SuppressWarnings("rawtypes") Vector vec : arrDanhSachDonHang) {
			dtmDanhSachDonHang.addRow(vec);
		}
	}
}