package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import ctuet.edu.vn.model.SanPham;

public class SanPhamService extends SQLServerConnection{
	
	//Câp nhật sản phẩm
	public void capnhatSanPham(SanPham sanpham) {
		try {
			String sqlxoaSanPham = "update SanPham set tenSanPham = ?,mausac = ?,kichthuoc = ?,nguoncungcap = ?,giaban = ?,hinhanh = ? where maSanPham = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlxoaSanPham);
			preparedStatement.setString(1, sanpham.getTenSanPham());
			preparedStatement.setString(2, sanpham.getMauSac());
			preparedStatement.setInt(3, sanpham.getKichthuoc());
			preparedStatement.setString(4, sanpham.getNguoncungcap());
			preparedStatement.setInt(5, sanpham.getGiaban());
			preparedStatement.setString(6, sanpham.getHinhanh());
			preparedStatement.setString(7, sanpham.getMaSanPham());
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Đánh dấu sản phẩm được xóa bằng giá trị -1 cho kích thước sản phẩm
	public void xoaSanPham(SanPham sanpham) {
		try {
			String sqlxoaSanPham = "update SanPham set kichthuoc = -1 where maSanPham = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlxoaSanPham);
			preparedStatement.setString(1, sanpham.getMaSanPham());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Thêm sản phẩm mới vào csdl
	public void themSanPhamMoi(SanPham sanpham) {
		try {
			if(sanpham.getGiaban() <= 0) {
				JOptionPane.showMessageDialog(null, "Giá bán sản phẩm không phù hợp!");
			}else {
				String sqlthemSanPhamMoi = "INSERT INTO SanPham values(?,?,?,?,?,?,?);";
				PreparedStatement preparedStatement = conn.prepareStatement(sqlthemSanPhamMoi);
				preparedStatement.setString(1, sanpham.getMaSanPham());
				preparedStatement.setString(2, sanpham.getTenSanPham());
				preparedStatement.setString(3, sanpham.getMauSac());
				preparedStatement.setInt(4, sanpham.getKichthuoc());
				preparedStatement.setString(5, sanpham.getNguoncungcap());
				preparedStatement.setFloat(6, sanpham.getGiaban());
				preparedStatement.setString(7, sanpham.getHinhanh());
				preparedStatement.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Đếm số sản phẩm hiện có ở phục vụ việc tạo mã sản phẩm
	public int demSoSanPham() {
		int sosanpham = 0;
		try {
			String sqlSoSanPham = "Select count(maSanPham) from SanPham;";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSoSanPham);
			ResultSet resultSoSanPham = preparedStatement.executeQuery();
			resultSoSanPham.next();
			sosanpham = resultSoSanPham.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sosanpham;
	}
	
	//Lấy toàn bộ sản phẩm
	public ArrayList<SanPham> layToanBoSanPham(){
		ArrayList<SanPham> arrToanBoSanPham = new ArrayList<SanPham>();
		try {
			String slq = "select * from SanPham where kichthuoc != -1;";
			PreparedStatement preStatement = conn.prepareStatement(slq);
			ResultSet result = preStatement.executeQuery();
			while(result.next()) {
				SanPham sp = new SanPham();
				sp.setMaSanPham(result.getString(1));
				sp.setTenSanPham(result.getString(2));
				sp.setMauSac(result.getString(3));
				sp.setKichthuoc(result.getInt(4));
				sp.setNguoncungcap(result.getString(5));
				sp.setGiaban(result.getInt(6));
				sp.setHinhanh(result.getString(7));
				arrToanBoSanPham.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrToanBoSanPham;
	}
	//Tìm sản phẩm theo mã
	public SanPham TimSanPham(String maSP) {
		SanPham sp = new SanPham();
		try {
			String sql = "select * from SanPham where maSanpham = ?;";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, maSP);
			ResultSet result = preStatement.executeQuery();
			if(result.next()) {
				sp.setMaSanPham(result.getString(1));
				sp.setTenSanPham(result.getString(2));
				sp.setMauSac(result.getString(3));
				sp.setKichthuoc(result.getInt(4));
				sp.setNguoncungcap(result.getString(5));
				sp.setGiaban(result.getInt(6));
				sp.setHinhanh(result.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
	
	//Tổng tồn sản phẩm
	public long tongton(SanPham sanpham) {
		try {
			String sql = "select SUM(soluong) from ChiTietKho where maSanPham = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, sanpham.getMaSanPham());
			ResultSet result = preStatement.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//Tình trạng tồn kho của sản phẩm
	@SuppressWarnings("rawtypes")
	public ArrayList<Vector> TonKho(String maSP){
		ArrayList<Vector> arrTonKho = new ArrayList<Vector>();
		try {
			String sql = "select * from ChiTietKho where maSanPham = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, maSP);
			ResultSet result = preStatement.executeQuery();
			while(result.next()) {
				Vector<Object> vec = new Vector<Object>();
				vec.add(result.getString(1));
				vec.add(result.getString(2));
				vec.add(result.getInt(3));
				arrTonKho.add(vec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrTonKho;
	}
}
