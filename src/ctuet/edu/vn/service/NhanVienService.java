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
				nhanvien.setNgaysinh(result.getString(4));
				nhanvien.setCmnd(result.getString(5));
				nhanvien.setDiachi(result.getString(6));
				nhanvien.setVitri(result.getString(7));
				nhanvien.setMucluong(result.getString(8));
				nhanvien.setNgaynhanviec(result.getString(9));
				arrNhanVien.add(nhanvien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrNhanVien;
	}
	//Đếm nhân viên mới tự động tăng
		public int demMaNhanVien() {
			int MaNhanVien = 0;
			try {
				String sqlMaNhanVien = "Select count(maNhanVien) from NhanVien;";
				PreparedStatement preparedStatement = conn.prepareStatement(sqlMaNhanVien);
				ResultSet resultMaNhanVien = preparedStatement.executeQuery();
				resultMaNhanVien.next();
				MaNhanVien = resultMaNhanVien.getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return MaNhanVien;
		}
	//Thêm nhân viên mới vào cơ sỡ dữ liệu

		
		public void themNhanVien(NhanVien nhanvien) {
			try {
				String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?,?);";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, nhanvien.getMaNhanVien());
				preparedStatement.setString(2, nhanvien.getTenNhanVien());
				preparedStatement.setString(3, nhanvien.getSodienthoai());
				preparedStatement.setString(4, nhanvien.getNgaysinh());
				preparedStatement.setString(5, nhanvien.getCmnd());
				preparedStatement.setString(6, nhanvien.getDiachi());
				preparedStatement.setString(7, nhanvien.getVitri());
				preparedStatement.setString(8, nhanvien.getMucluong());
				preparedStatement.setString(9, nhanvien.getNgaynhanviec());
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Đánh dấu nhân viên được xóa bằng giá trị 'off' cho vitri nhân viên
		public void xoaNhanVien(NhanVien nhanvien) {
			try {
				String sqlxoaNhanVien = "update NhanVien set vitri = 'off' where cmnd = ?";
				PreparedStatement preparedStatement = conn.prepareStatement(sqlxoaNhanVien);
				preparedStatement.setString(1, nhanvien.getCmnd());
				preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void chinhsuaThongTinNhanVien(NhanVien nhanvien) {
			try {
				String sqlxoaNhanVien = "update NhanVien set tennhanvien = ?,sodienthoai = ?,ngaysinh = ?,cmnd = ?,diachi = ?,vitri = ?,mucluong = ?,ngaynhanviec = ? where maNhanVien = ?";
				PreparedStatement preparedStatement = conn.prepareStatement(sqlxoaNhanVien);
				
				preparedStatement.setString(1, nhanvien.getTenNhanVien());
				preparedStatement.setString(2, nhanvien.getSodienthoai());
				preparedStatement.setString(3,nhanvien.getNgaysinh());
				preparedStatement.setString(4, nhanvien.getCmnd());
				preparedStatement.setString(5, nhanvien.getDiachi());
				preparedStatement.setString(6, nhanvien.getVitri());
				preparedStatement.setString(7, nhanvien.getMucluong());
				preparedStatement.setString(8, nhanvien.getNgaynhanviec());
				preparedStatement.setString(9, nhanvien.getMaNhanVien());
				preparedStatement.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
