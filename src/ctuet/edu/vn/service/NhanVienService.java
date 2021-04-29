package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ctuet.edu.vn.model.NhanVien;

public class NhanVienService extends SQLServerConnection{
	//Lấy toàn bộ nhân viên
	public ArrayList<NhanVien> layToanBoNhanVien(){
		ArrayList<NhanVien> arrNhanVien = new ArrayList<NhanVien>();
		try {
			String sql = "select * from NhanVien where vitri != 'off';";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				NhanVien nhanvien = new NhanVien();
				nhanvien.setMaNhanVien(result.getString(1));
				nhanvien.setTenNhanVien(result.getString(2));
				nhanvien.setSodienthoai(result.getString(3));
				nhanvien.setNgaysinh(result.getDate(4));
				nhanvien.setCmnd(result.getString(5));
				nhanvien.setDiachi(result.getString(6));
				nhanvien.setVitri(result.getString(7));
				nhanvien.setMucluong(result.getLong(8));
				nhanvien.setNgaynhanviec(result.getDate(9));
				nhanvien.setHinhanh(result.getString(10));
				arrNhanVien.add(nhanvien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrNhanVien;
	}
}
