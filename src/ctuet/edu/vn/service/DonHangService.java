package ctuet.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import ctuet.edu.vn.model.DonHang;

public class DonHangService extends SQLServerConnection{

	String sql;
	PreparedStatement preparedStatement;
	
	
	//Lấy đơn hàng theo mã
	public DonHang layDonHangTheoMa(DonHang donhang) {
		try {

			sql = "SELECT * from DonHang where maDonHang = ?;";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, donhang.getMaDonHang());
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				donhang.setMaDonHang(result.getString(1));
				donhang.setMaNhanVien(result.getString(2));
				donhang.setMaKhachHang(result.getString(3));
				donhang.setNgaytao(result.getDate(4));
				donhang.setTrangthai(result.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return donhang;
	}
	//Xóa đơn hàng trong cơ sỡ dữ liệu
	public void xoaDonHang(DonHang donhang) {
		try {
			String sql = "update DonHang set trangthaidonhang = ? where maDonHang = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "0");
			preparedStatement.setString(2, donhang.getMaDonHang());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Cập nhật trạng thái đơn hàng vào cơ sỡ dữ liệu
	public void hoanthanhDonHang(DonHang donhang) {
		try {
			sql = "update DonHang set trangthaidonhang = ? where maDonHang = ? and trangthaidonhang = ?;";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "1");
			preparedStatement.setString(2, donhang.getMaDonHang());
			preparedStatement.setString(3, "2");
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	



	//Tổng giá trị đơn hàng
	public int tonggiatriDonHang(String maDonHang) {
		int tonggiatri = 0;
		try {
			sql = "select SUM(giatri) from ChiTietDonHang where maDonHang = ?;";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1,maDonHang);
			ResultSet resultTongGiaTri = preStatement.executeQuery();
			resultTongGiaTri.next();
			tonggiatri = resultTongGiaTri.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tonggiatri;

	}

	//Số lượng sản phẩm trong đơn hàng
	public int soluongSanPham(String maDonHang) {
		int soluong = 1;
		try {
			String sql = "select SUM(soluong) from ChiTietDonHang where maDonHang = ?;";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1,maDonHang);
			ResultSet resultSoSanPham = preStatement.executeQuery();
			resultSoSanPham.next();
			soluong = resultSoSanPham.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soluong;
	}
	//Thêm đơn hàng mới vào cơ sỡ dữ liệu
	public void themDonHang(DonHang donhang) {
		try {
			sql = "insert into DonHang values(?,?,?,?,?);";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, donhang.getMaDonHang());
			preparedStatement.setString(2, donhang.getMaNhanVien());
			preparedStatement.setString(3, donhang.getMaKhachHang());
			preparedStatement.setDate(4,donhang.getNgaytao());
			preparedStatement.setInt(5, donhang.getTrangthai());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Đếm số đơn hàng hiện có hỗ trợ việc tạo mã đơn hàng mới tự động tăng
	public int demSoDonHang() {
		int sodonhang = 0;
		try {
			String sqlSoDonHang = "Select count(maDonHang) from DonHang;";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSoDonHang);
			ResultSet resultSoDonHang = preparedStatement.executeQuery();
			resultSoDonHang.next();
			sodonhang = resultSoDonHang.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sodonhang;
	}
	//Thông kê đơn hàng theo thời gian
	@SuppressWarnings("rawtypes")
	public ArrayList<Vector> thongkeDonHang(String ngaybatdau,String ngayketthuc){
		ArrayList<Vector> arrDanhSachDonHang = new ArrayList<Vector>();
		try {
			String sql = "select * from DonHang where ngaytao >= ? and ngaytao <= ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ngaybatdau);
			preStatement.setString(2, ngayketthuc);
			ResultSet result = preStatement.executeQuery();
			while(result.next()) {
				Vector<Object> vecDonHang = new Vector<Object>();
				KhachHangService svKhachHang = new KhachHangService();
				//Lấy mã đơn hàng
				vecDonHang.add(result.getString(1));
				//Lấy tên khách hàng
				vecDonHang.add(svKhachHang.LayKhachHangTheoMa(result.getString(3)).getTenKhachHang());
				//Lấy ngày tạo
				vecDonHang.add(result.getString(4));
				//Lấy tổng giá trị
				vecDonHang.add(tonggiatriDonHang(result.getString(1)));
				
				arrDanhSachDonHang.add(vecDonHang);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrDanhSachDonHang;
	}

	//Lấy toàn bô đơn hàng để hiển thị lên bảng trong quản lý bán hàng
	@SuppressWarnings("rawtypes")
	public ArrayList<Vector> LayToanBoDonHang() {
		ArrayList<Vector> arrDanhSachDonHang = new ArrayList<Vector>();
		try {
			String sqlLayDanhSachDonHang = "select * from DonHang";
			PreparedStatement preStatement = conn.prepareStatement(sqlLayDanhSachDonHang);
			ResultSet resultDanhSachDonHang = preStatement.executeQuery();
			while(resultDanhSachDonHang.next()) {
				Vector<Object> vecDonHang = new Vector<Object>();
				KhachHangService svKhachHang = new KhachHangService();
				//Lấy mã đơn hàng
				vecDonHang.add(resultDanhSachDonHang.getString(1));
				//Lấy tên khách hàng
				vecDonHang.add(svKhachHang.LayKhachHangTheoMa(resultDanhSachDonHang.getString(3)).getTenKhachHang());
				//Lấy SDT khách hàng
				vecDonHang.add(svKhachHang.LayKhachHangTheoMa(resultDanhSachDonHang.getString(3)).getSodienthoai());
				//Lấy mã nhân viên
				vecDonHang.add(resultDanhSachDonHang.getString(2));
				//Lấy ngày tạo
				vecDonHang.add(resultDanhSachDonHang.getString(4));
				//Lấy số lượng sản phẩm
				vecDonHang.add(soluongSanPham(resultDanhSachDonHang.getString(1)));
				//Lấy tổng giá trị
				vecDonHang.add(tonggiatriDonHang(resultDanhSachDonHang.getString(1)));
				//Lấy trạng thái
				if(resultDanhSachDonHang.getInt(5) == 2) {
					vecDonHang.add("Đợi thanh toán");
				}else if(resultDanhSachDonHang.getInt(5) == 1) {
					vecDonHang.add("Đã thanh toán");
				}else {
					vecDonHang.add("Đã xóa");
				}

				arrDanhSachDonHang.add(vecDonHang);
				//vecDonHang.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrDanhSachDonHang;
	}
}
