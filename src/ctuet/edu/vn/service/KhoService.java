package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ctuet.edu.vn.model.Kho;

public class KhoService extends SQLServerConnection{
	
//	//Kho hàng có tên Kệ trung bày được khởi tạo mặc định
//	public KhoService() {
//		try {
//			String sql = "insert into Kho values(?,?,?,?);";
//			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(1, "KHO_0");
//			preparedStatement.setString(2, "Kệ trưng bày");
//			preparedStatement.setString(3, "Kệ trưng bày tại cửa hàng");
//			preparedStatement.setInt(4, 1);
//			preparedStatement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	//Thêm kho mới
	public void themKhoMoi(Kho kho) {
		try {
			String sql = "insert into Kho values(?,?,?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, kho.getMaKho());
			preparedStatement.setString(2, kho.getTenKho());
			preparedStatement.setString(3, kho.getDiaChi());
			preparedStatement.setInt(4, kho.getTrangthai());
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Đếm số kho hiên có
	public int demsoKho() {
		int sokho = 0;
		try {
			String sql = "select COUNT(maKho) from Kho;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			
			result.next();
			sokho = result.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sokho;
	}
	
	//Cập nhật trạng thái xóa kho
	public boolean xoaKho(Kho kho) {
		try {
			//Xóa các kho không có sản phẩm và không phải KETRUNGBAY
			String sql = "update Kho set trangthaikho = 0 where maKho = ? and maKho != 'KETRUNGBAY';";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, kho.getMaKho());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//cập nhật thông tin kho
	public void capnhatKho(Kho kho) {
		try {
			String sql = "update Kho set tenKho = ?,diachi = ? where maKho = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, kho.getTenKho());
			preparedStatement.setString(2, kho.getDiaChi());
			preparedStatement.setString(3, kho.getMaKho());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//lấy toàn bộ Kho 
	public ArrayList<Kho> layToanBoKho() {
		ArrayList<Kho> arrKho = new ArrayList<Kho>();
		try {
			String sql = "select * from Kho where trangthaikho = 1";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Kho kho = new Kho();
				kho.setMaKho(result.getString(1));
				kho.setTenKho(result.getString(2));
				kho.setDiaChi(result.getString(3));
				kho.setTrangthai(result.getInt(4));
				arrKho.add(kho);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrKho;
	}
}
