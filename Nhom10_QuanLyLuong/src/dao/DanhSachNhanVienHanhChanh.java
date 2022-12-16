package dao;
/*
 * Author:Trần Thành Nam
 * Date:15/11/2021
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.SimpleFormatter;

import connectDB.Database;
import entity.DonVi;
import entity.NhanVienHanhChanh;
import entity.SanPham;

public class DanhSachNhanVienHanhChanh {
	private ArrayList<NhanVienHanhChanh> dsNhanVienHC;

	public DanhSachNhanVienHanhChanh() {
		dsNhanVienHC = new ArrayList<NhanVienHanhChanh>();
	}
	
	public boolean themNhanVien(NhanVienHanhChanh sp) {
		for (NhanVienHanhChanh s : dsNhanVienHC) {
			if (s.getMaNhanVien().equals(sp.getMaNhanVien()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into NhanVienHanhChanh values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sp.getMaNhanVien());
			stmt.setString(2, sp.getTenNhanVien());
			stmt.setString(3, sp.getSoDienThoai());
			java.sql.Date sqlDate = new java.sql.Date(sp.getNgayCT().getTime());
			stmt.setDate(4, sqlDate);
			stmt.setString(5, sp.getDonVi().getMaDonVi());
			stmt.setString(6, sp.getSoTaiKhoan());
			stmt.setString(7, sp.getTenNhanHang());
			stmt.setString(8, sp.getTrinhDo());
			stmt.setString(9, sp.getBangCap());
			stmt.setDouble(10, sp.getLuongNgayCoBan());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean xoaNhanVien(String maNhanVien) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhanVienHanhChanh where maNhanVien = ?");
			stmt.setString(1, maNhanVien);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatNhanVien(NhanVienHanhChanh sp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {	
			stmt = con.prepareStatement("update NhanVienHanhChanh set tenNhanVien = ?, soDienThoai = ? , ngayCT = ?, maDonVi = ? , soTaiKhoan = ?, tenNganHang = ? , trinhDo = ?, bangCap = ? , luongNgayCoBan = ? where maNhanVien = ?");
			stmt.setString(1, sp.getTenNhanVien());		
			stmt.setString(2, sp.getSoDienThoai());	
			java.sql.Date sqlDate = new java.sql.Date(sp.getNgayCT().getTime());
			stmt.setDate(3, sqlDate);
			stmt.setString(4, sp.getDonVi().getMaDonVi());
			stmt.setString(5, sp.getSoTaiKhoan());
			stmt.setString(6, sp.getTenNhanHang());
			stmt.setString(7, sp.getTrinhDo());
			stmt.setString(8, sp.getBangCap());
			stmt.setDouble(9, sp.getLuongNgayCoBan());
			
			stmt.setString(10, sp.getMaNhanVien());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<NhanVienHanhChanh> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from NhanVienHanhChanh";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsNhanVienHC.clear();
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);
				String soDienThoai = rs.getString(3);
				Date ngayCT = rs.getDate(4);
				String donVi = rs.getString(5);
				String soTaiKhoan = rs.getString(6);
				String tenNganHang = rs.getString(7);
				String trinhDo = rs.getString(8);
				String bangCap = rs.getString(9);
				double luongCoBan = rs.getDouble(10);
								
				NhanVienHanhChanh nvHC = new NhanVienHanhChanh(maNhanVien, tenNhanVien, soDienThoai, ngayCT, 
						new DonVi(donVi), soTaiKhoan, tenNganHang, trinhDo, bangCap, luongCoBan);
				dsNhanVienHC.add(nvHC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsNhanVienHC;
	}
	
	
	public ArrayList<NhanVienHanhChanh> timKiemNVHC(NhanVienHanhChanh nv) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<NhanVienHanhChanh> dsNV = new ArrayList<NhanVienHanhChanh>();		
		try {
			
			if (!nv.getMaNhanVien().equals("")) {
				String sql = "Select * from NhanVienHanhChanh where maNhanVien = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, nv.getMaNhanVien());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maNhanVien = rs.getString(1);
					String tenNhanVien = rs.getString(2);
					String soDienThoai = rs.getString(3);
					Date ngayCT = rs.getDate(4);
					String donVi = rs.getString(5);
					String soTaiKhoan = rs.getString(6);
					String tenNganHang = rs.getString(7);
					String trinhDo = rs.getString(8);
					String bangCap = rs.getString(9);
					double luongCoBan = rs.getDouble(10);
									
					NhanVienHanhChanh nvHC = new NhanVienHanhChanh(maNhanVien, tenNhanVien, soDienThoai, ngayCT, 
							new DonVi(donVi), soTaiKhoan, tenNganHang, trinhDo, bangCap, luongCoBan);
					dsNV.add(nvHC);
				}
			}		
			else if(!nv.getTenNhanVien().equals("")) {
				String sql = "Select * from NhanVienHanhChanh where tenNhanVien like ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, "%"+nv.getTenNhanVien()+"%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
			
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maNhanVien = rs.getString(1);
					String tenNhanVien = rs.getString(2);
					String soDienThoai = rs.getString(3);
					Date ngayCT = rs.getDate(4);
					String donVi = rs.getString(5);
					String soTaiKhoan = rs.getString(6);
					String tenNganHang = rs.getString(7);
					String trinhDo = rs.getString(8);
					String bangCap = rs.getString(9);
					double luongCoBan = rs.getDouble(10);
									
					NhanVienHanhChanh nvHC = new NhanVienHanhChanh(maNhanVien, tenNhanVien, soDienThoai, ngayCT, 
							new DonVi(donVi), soTaiKhoan, tenNganHang, trinhDo, bangCap, luongCoBan);
					dsNV.add(nvHC);
				}
			}
			else if(!nv.getDonVi().getMaDonVi().equals("")) {
				String sql = "Select * from NhanVienHanhChanh where maDonVi like ?";
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, "%"+nv.getDonVi().getMaDonVi()+"%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
			
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maNhanVien = rs.getString(1);
					String tenNhanVien = rs.getString(2);
					String soDienThoai = rs.getString(3);
					Date ngayCT = rs.getDate(4);
					String donVi = rs.getString(5);
					String soTaiKhoan = rs.getString(6);
					String tenNganHang = rs.getString(7);
					String trinhDo = rs.getString(8);
					String bangCap = rs.getString(9);
					double luongCoBan = rs.getDouble(10);
									
					NhanVienHanhChanh nvHC = new NhanVienHanhChanh(maNhanVien, tenNhanVien, soDienThoai, ngayCT, 
							new DonVi(donVi), soTaiKhoan, tenNganHang, trinhDo, bangCap, luongCoBan);
					dsNV.add(nvHC);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	public int getTongSoLuongNVHC() {
		int tongNVHC = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maNhanVien) from NhanVienHanhChanh";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongNVHC = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongNVHC;
	}
}
