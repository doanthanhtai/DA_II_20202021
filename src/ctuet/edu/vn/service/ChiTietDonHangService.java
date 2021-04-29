package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ctuet.edu.vn.model.ChiTietDonHang;
import ctuet.edu.vn.model.DonHang;

public class ChiTietDonHangService extends SQLServerConnection{
	PreparedStatement preparedStatement;
	String sql;
	
	//Thêm mới một chi tiết đơn hàng vào cơ sỡ dữ liệu
		public void themChiTietDonHang(ChiTietDonHang chitietdonhang) {
			try {
				sql = "INSERT INTO ChiTietDonHang VALUES(?,?,?,?);";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, chitietdonhang.getMaDonHang());
				preparedStatement.setString(2, chitietdonhang.getMaSanPham());
				preparedStatement.setInt(3, chitietdonhang.getSoluong());
				preparedStatement.setLong(4, chitietdonhang.getGiatri());
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	//Cập nhật chi tiết đơn hàng
	public void capnhatChiTietDonHang(ChiTietDonHang chitietdonhang) {
		try {
			//Kiểm tra tồn tại của một chi tiết đơn hàng
			String sqlKiemTra = "select * from ChiTietDonHang where (maDonHang = ? and maSanPham = ?);";
			preparedStatement = conn.prepareStatement(sqlKiemTra);
			preparedStatement.setString(1, chitietdonhang.getMaDonHang());
			preparedStatement.setString(2, chitietdonhang.getMaSanPham());
			//Nếu chi tiết đã tồn tài thì cập nhật số lượng và giá trị cho sản phẩm của chi tiết này
			//Ngược lại thì thêm mới chi tiết đơn hàng
			if(preparedStatement.executeQuery().next()) {
				sql = "UPDATE ChiTietDonHang SET soluong = ?,giatri = ? WHERE (maDonHang = ? and maSanPham = ?) ;";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, chitietdonhang.getSoluong());
				preparedStatement.setFloat(2, chitietdonhang.getGiatri());
				preparedStatement.setString(3, chitietdonhang.getMaDonHang());
				preparedStatement.setString(4, chitietdonhang.getMaSanPham());
				preparedStatement.execute();

			}else {
				sql = "insert into ChiTietDonHang values(?,?,?,?);";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, chitietdonhang.getMaDonHang());
				preparedStatement.setString(2, chitietdonhang.getMaSanPham());
				preparedStatement.setInt(3, chitietdonhang.getSoluong());
				preparedStatement.setLong(4, chitietdonhang.getGiatri());
				preparedStatement.execute();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Lấy toàn bộ chi tiết đơn hàng của một đơn hàng
	public ArrayList<ChiTietDonHang> layToanBoChiTietDonHang(DonHang donhang){
		ArrayList<ChiTietDonHang> arrChiTietDonHang = new ArrayList<ChiTietDonHang>();
		try {
			String sql = "select * from ChiTietDonHang where maDonHang = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, donhang.getMaDonHang());
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
				chiTietDonHang.setMaDonHang(donhang.getMaDonHang());
				chiTietDonHang.setMaSanPham(result.getString(2));
				chiTietDonHang.setSoluong(result.getInt(3));
				chiTietDonHang.setGiatri(result.getLong(4));
				arrChiTietDonHang.add(chiTietDonHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrChiTietDonHang;
	}
}
