package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ctuet.edu.vn.model.TaiKhoan;

public class TaiKhoanService extends SQLServerConnection{
	//Kiểm tra tồn tại
	public boolean kiemtraTonTai(TaiKhoan taikhoan) {
		try {
			String sql = "select username from TaiKhoan where username = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, taikhoan.getUsername());
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Cập nhật thông tin tài khoản
	public void capnhatTaiKhoan(TaiKhoan taikhoan) {
		try {
			String sql = "update TaiKhoan set password = ? ,maNhanVien = ? where username = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, taikhoan.getPassword());
			preparedStatement.setString(2, taikhoan.getMaNhanVien());
			preparedStatement.setString(3, taikhoan.getUsername());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//lấy toàn bộ Tài khoản
	public ArrayList<TaiKhoan> layToanBoTaiKhoan() {
		ArrayList<TaiKhoan> arrTaiKhoan = new ArrayList<TaiKhoan>();
		try {
			String sql = "select * from TaiKhoan where vaitro != 'off';";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				TaiKhoan taikhoan = new TaiKhoan();
				taikhoan.setUsername(result.getString(1));
				taikhoan.setPassword(result.getString(2));
				taikhoan.setVaitro(result.getString(3));
				taikhoan.setMaNhanVien(result.getString(4));
				arrTaiKhoan.add(taikhoan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrTaiKhoan;
	}
	
	//Xóa tài khoản
	public void xoaTaiKhoan(TaiKhoan taikhoan) {
		try {
			String sql = "update TaiKhoan set vaitro = 'off' where username = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, taikhoan.getUsername()); 
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Thêm tài khoản mới
	public void themTaiKhoan(TaiKhoan taikhoan) {
		try {
			String sql = "insert into TaiKhoan values(?,?,?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, taikhoan.getUsername());
			preparedStatement.setString(2, taikhoan.getPassword());
			preparedStatement.setString(3, taikhoan.getVaitro());
			preparedStatement.setString(4, taikhoan.getMaNhanVien());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
