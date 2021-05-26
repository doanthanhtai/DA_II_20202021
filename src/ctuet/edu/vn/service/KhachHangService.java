package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;


import ctuet.edu.vn.model.KhachHang;

public class KhachHangService extends SQLServerConnection {
	
	//Cập nhật thông tin khách hàng
	public void capnhatKhachHang(KhachHang khachhang) {
		try {
			String sql = "update KhachHang set tenkhachhang = ?,sodienthoai = ? where maKhachHang = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, khachhang.getTenKhachHang());
			preparedStatement.setString(2, khachhang.getSodienthoai());
			preparedStatement.setString(3, khachhang.getMaKhachHang());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Tìm thông tin khách hàng bằng số điện thoại
	public KhachHang TimKhachHang(String sodienthoai) {
		KhachHang kh = new KhachHang();
		try {
			String sql = "select * from KhachHang where sodienthoai = ?;";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, sodienthoai);
			ResultSet result = preStatement.executeQuery();
			if(result.next()) {
				kh.setMaKhachHang(result.getString(1));
				kh.setTenKhachHang(result.getString(2));
				kh.setSodienthoai(result.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kh;
	}

	//Đếm số khách hàng
	public int demKhachHang() {
		int sokhachhang = 0;
		try {
			String sql = "Select count(maKhachHang) from KhachHang;";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			result.next();
			sokhachhang =  result.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sokhachhang;
	}

	//Thêm khách hàng mới
	public void themKhachHang(KhachHang khachhang) {
		try {
			String sql = "insert into KhachHang values(?,?,?);";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, khachhang.getMaKhachHang());
			preStatement.setString(2, khachhang.getTenKhachHang());
			preStatement.setString(3, khachhang.getSodienthoai());
			preStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Lấy thông tin đơn hàng của khách hàng
	@SuppressWarnings("rawtypes")
	public ArrayList<Vector> DanhSachDonHang(String sodienthoai){

		ArrayList<Vector> arrDanhSachDonHang = new ArrayList<Vector>();		
		try {
			//Tìm danh sách đơn hàng theo khách hàng
			String sqlTimMaDonHang = "select * from DonHang where maKhachHang = ?;";
			PreparedStatement preStatement = conn.prepareStatement(sqlTimMaDonHang);
			preStatement.setString(1, TimKhachHang(sodienthoai).getMaKhachHang());
			ResultSet resultMaDonHang = preStatement.executeQuery();
			//Tính tổng giá trị đơn hàng
			while(resultMaDonHang.next()){
				int giatriDonHang = 0;
				String sqlTimChiTietDonHang = "select giatri from ChiTietDonHang where maDonHang = ?;";
				preStatement = conn.prepareStatement(sqlTimChiTietDonHang);
				preStatement.setString(1,resultMaDonHang.getString(1));
				ResultSet resultgiatri = preStatement.executeQuery();
				while(resultgiatri.next()) {
					giatriDonHang += resultgiatri.getInt(1);
				}
				//Thêm thông tin đơn hàng vào danh sách trả về
				Vector<Object> vec = new Vector<Object>();
				vec.add(resultMaDonHang.getString(1));
				vec.add(resultMaDonHang.getString(4));
				vec.add(giatriDonHang);
				vec.add(resultMaDonHang.getString(5));
				arrDanhSachDonHang.add(vec);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrDanhSachDonHang;
	}
	//Lấy thông tin đơn hàng theo mã
	public KhachHang LayKhachHangTheoMa(String maKhachHang) {
		KhachHang kh = new KhachHang();
		try {
			String sql = "select * from KhachHang where maKhachHang = ?;";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, maKhachHang);
			ResultSet resultKhachHang = preStatement.executeQuery();
			if(resultKhachHang.next()) {
				kh.setMaKhachHang(resultKhachHang.getString(1));
				kh.setTenKhachHang(resultKhachHang.getString(2));
				kh.setSodienthoai(resultKhachHang.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kh;
	}
	//Lấy toàn bộ khách hàng
		public ArrayList<KhachHang> layToanBoKhachHang(){
			ArrayList<KhachHang> arrKhachHang = new ArrayList<KhachHang>();
			try {
				String sql = "select * from KhachHang where sodienthoai != '';";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet result = preparedStatement.executeQuery();
				while(result.next()) {
					KhachHang khachhang = new KhachHang();
					khachhang.setMaKhachHang(result.getString(1));
					khachhang.setTenKhachHang(result.getString(2));
					khachhang.setSodienthoai(result.getString(3));
					arrKhachHang.add(khachhang);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrKhachHang;
		}
}
