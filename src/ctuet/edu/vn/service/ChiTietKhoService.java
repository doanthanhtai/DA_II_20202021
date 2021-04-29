package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ctuet.edu.vn.model.Kho;
import ctuet.edu.vn.model.SanPham;

public class ChiTietKhoService extends SQLServerConnection{
	
	//Cập nhật chi tiết kho
	public void capnhatChiTietKho(Kho kho,SanPham sanpham,int soluongnhapvao) {
		try {
			float soluonghientai = soluongSanPham(kho, sanpham);
			String sql = "update ChiTietKho set soluong = ? where maKho = ? and maSanPham = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setFloat(1, soluongnhapvao + soluonghientai);
			preparedStatement.setString(2, kho.getMaKho());
			preparedStatement.setString(3, sanpham.getMaSanPham());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Thêm chi tiết kho mới
	public void themChiTietKho(Kho kho,SanPham sanpham,int soluong) {
		try {
			String sql = "insert into ChiTietKho values(?,?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, kho.getMaKho());
			preparedStatement.setString(2, sanpham.getMaSanPham());
			preparedStatement.setFloat(3, soluong);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//đếm số sản phẩm một kho
	public float demSoSanPham(Kho kho) {
		float soSanPham = 0f;
		try {
			String sql = "select SUM(soluong) from ChiTietKho where maKho = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, kho.getMaKho());
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				soSanPham = result.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return soSanPham;
	}
	
	//đếm số lượng sản phẩm 
	public float soluongSanPham(Kho kho,SanPham sanpham) {
		float soluong = -1;
		try {
			String sql = "select soluong from ChiTietKho where maKho = ? and maSanPham = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, kho.getMaKho());
			preparedStatement.setString(2, sanpham.getMaSanPham());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				soluong = resultSet.getFloat(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soluong;
	}
}
