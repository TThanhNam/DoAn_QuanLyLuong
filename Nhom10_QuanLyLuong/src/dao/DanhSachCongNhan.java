package dao;
/*
 * Author:Trần Thành Nam
 * Date:14/11/2021
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connectDB.Database;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVienHanhChanh;

public class DanhSachCongNhan {
	private ArrayList<CongNhan> dsCongNhan;

	public DanhSachCongNhan() {
		dsCongNhan = new ArrayList<CongNhan>();
	}
	
	public boolean themCongNhan(CongNhan sp) {
		for (CongNhan s : dsCongNhan) {
			if (s.getMaNhanVien().equals(sp.getMaNhanVien()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into CongNhan values(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sp.getMaNhanVien());
			stmt.setString(2, sp.getTenNhanVien());
			stmt.setString(3, sp.getSoDienThoai());
			java.sql.Date sqlDate = new java.sql.Date(sp.getNgayCT().getTime());
			stmt.setDate(4, sqlDate);
			stmt.setString(5, sp.getDonVi().getMaDonVi());
			stmt.setString(6, sp.getSoTaiKhoan());
			stmt.setString(7, sp.getTenNhanHang());
			stmt.setInt(8, sp.getSoNamKN());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean xoaCongNhan(String maNhanVien) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from CongNhan where maNhanVien = ?");
			stmt.setString(1, maNhanVien);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatCongNhan(CongNhan sp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVienHanhChanh set tenNhanVen = ?, soDienThoai = ? , ngayCT = ?, maDonVi = ? , soTaiKhoan = ?, tenNganHang = ? , soNamKM = ? where maNhanVien = ?");
			stmt.setString(1, sp.getTenNhanVien());
			stmt.setString(2, sp.getSoDienThoai());
			java.sql.Date sqlDate = new java.sql.Date(sp.getNgayCT().getTime());
			stmt.setDate(3, sqlDate);
			stmt.setString(4, sp.getDonVi().toString());
			stmt.setString(5, sp.getSoTaiKhoan());
			stmt.setString(6, sp.getTenNhanHang());
			stmt.setInt(7, sp.getSoNamKN());
			
			stmt.setString(8, sp.getMaNhanVien());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<CongNhan> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from CongNhan";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsCongNhan.clear();
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);
				String soDienThoai = rs.getString(3);
				Date ngayCT = rs.getDate(4);
				String donVi = rs.getString(5);
				String soTaiKhoan = rs.getString(6);
				String tenNganHang = rs.getString(7);
				int soNamKM = rs.getInt(8);
							
				CongNhan cn = new CongNhan(maNhanVien, tenNhanVien, soDienThoai, ngayCT, new DonVi(donVi), soTaiKhoan, tenNganHang, soNamKM);
				dsCongNhan.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsCongNhan;
	}
	
	public ArrayList<CongNhan> timKiemCN(CongNhan cn) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		ArrayList<CongNhan> dsCn = new ArrayList<CongNhan>();		
		try {		
			if (!cn.getMaNhanVien().equals("")) {
				String sql = "Select * from CongNhan where maNhanVien = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, cn.getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maNhanVien = rs.getString(1);
					String tenNhanVien = rs.getString(2);
					String soDienThoai = rs.getString(3);
					Date ngayCT  = rs.getDate(4);
					String donVi = rs.getString(5);
					String soTaiKhoan = rs.getString(6);
					String tenNganHang = rs.getString(7);
					int soNamKM = rs.getInt(8);
							
					CongNhan CN = new CongNhan(maNhanVien, tenNhanVien, soDienThoai, ngayCT, new DonVi(donVi), soTaiKhoan, tenNganHang, soNamKM);
					dsCn.add(CN);
					
				}
			}		
			else if(!cn.getTenNhanVien().equals("")) {
				String sql = "Select * from CongNhan where tenNhanVien like ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, "%"+cn.getTenNhanVien()+"%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				
				// Duyet tren ket qua ve
		
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maNhanVien = rs.getString(1);
					String tenNhanVien = rs.getString(2);
					String soDienThoai = rs.getString(3);
					Date ngayCT  = rs.getDate(4);
					String donVi = rs.getString(5);
					String soTaiKhoan = rs.getString(6);
					String tenNganHang = rs.getString(7);
					int soNamKM = rs.getInt(8);
					
					CongNhan CN = new CongNhan(maNhanVien, tenNhanVien, soDienThoai, ngayCT, new DonVi(donVi), soTaiKhoan, tenNganHang, soNamKM);
					dsCn.add(CN);
				}
			}
			else if(!cn.getDonVi().getMaDonVi().equals("")) {
				String sql = "Select * from CongNhan where maDonVi like ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, "%"+cn.getDonVi().getMaDonVi()+"%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				
				// Duyet tren ket qua ve
		
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maNhanVien = rs.getString(1);
					String tenNhanVien = rs.getString(2);
					String soDienThoai = rs.getString(3);
					Date ngayCT  = rs.getDate(4);
					String donVi = rs.getString(5);
					String soTaiKhoan = rs.getString(6);
					String tenNganHang = rs.getString(7);
					int soNamKM = rs.getInt(8);
					
					CongNhan CN = new CongNhan(maNhanVien, tenNhanVien, soDienThoai, ngayCT, new DonVi(donVi), soTaiKhoan, tenNganHang, soNamKM);
					dsCn.add(CN);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCn;
	}
	public int getTongSoLuongCN() {
		int tongCN = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maNhanVien) from CongNhan";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongCN = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongCN;
	}
}
