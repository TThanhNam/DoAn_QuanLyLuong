package dao;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.NhanVienHanhChanh;
import entity.SanPham;
import entity.TienBHXH;
import entity.TienBHXHNhanVienHanhChanh;

public class DanhSachBHXHNhanVienHanhChanh {
	private ArrayList<TienBHXHNhanVienHanhChanh> dsTienBHXH;

	public DanhSachBHXHNhanVienHanhChanh() {
		dsTienBHXH = new ArrayList<TienBHXHNhanVienHanhChanh>();
	}
	
	public boolean themTienBHXHNVHC(TienBHXHNhanVienHanhChanh bhxh) {
		for (TienBHXHNhanVienHanhChanh s : dsTienBHXH) {
			if (s.getMaTienBHXH().equals(bhxh.getMaTienBHXH()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into TienBHXHNVHC values(?, ?, ?, ?, ?)");
			stmt.setString(1, bhxh.getMaTienBHXH());
			stmt.setDouble(2, bhxh.getTienBHXH());
			stmt.setString(3, bhxh.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(4, bhxh.getThang());
			stmt.setInt(5, bhxh.getNam());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean xoaBHXH(String maTienBHXH) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from TienBHXHNVHC where maTienBHXH = ?");
			stmt.setString(1, maTienBHXH);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatTienBHXH(TienBHXHNhanVienHanhChanh bhxh) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TienBHXHNVHC set maTienBHXH = ?, tienBHXH = ?, maNhanVien = ?, thang = ? , nam = ? where maTienBHXH = ?");
			stmt.setString(1, bhxh.getMaTienBHXH());
			stmt.setDouble(2, bhxh.getTienBHXH());
			stmt.setString(3, bhxh.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(4, bhxh.getThang());
			stmt.setInt(5, bhxh.getNam());
			stmt.setString(6, bhxh.getMaTienBHXH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<TienBHXHNhanVienHanhChanh> timKiemBHXH(TienBHXHNhanVienHanhChanh bhxh) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<TienBHXHNhanVienHanhChanh> dsTK = new ArrayList<TienBHXHNhanVienHanhChanh>();
		try {
			if (!bhxh.getMaTienBHXH().equals("")) {
				String sql = "Select * from TienBHXHNVHC where maTienBHXH = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bhxh.getMaTienBHXH());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienBHXH = rs.getString(1);
					double tienBHXH = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienBHXHNhanVienHanhChanh tienbhxh = new TienBHXHNhanVienHanhChanh(maTienBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienbhxh);
				}
			}
			if (bhxh.getTienBHXH() != 0) {
				String sql = "Select * from TienBHXHNVHC where tienBHXH = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setDouble(1, bhxh.getTienBHXH());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienBHXH = rs.getString(1);
					double tienBHXH = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienBHXHNhanVienHanhChanh tienbhxh = new TienBHXHNhanVienHanhChanh(maTienBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienbhxh);
				}
			}
			if (!bhxh.getNhanVienHanhChanh().getMaNhanVien().equals("")) {
				String sql = "Select * from TienBHXHNVHC where maNhanVien = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bhxh.getNhanVienHanhChanh().getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienBHXH = rs.getString(1);
					double tienBHXH = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienBHXHNhanVienHanhChanh tienbhxh = new TienBHXHNhanVienHanhChanh(maTienBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienbhxh);
				}
			}
			
			if (bhxh.getThang() != 0) {
				String sql = "Select * from TienBHXHNVHC where thang = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, bhxh.getThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienBHXH = rs.getString(1);
					double tienBHXH = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienBHXHNhanVienHanhChanh tienbhxh = new TienBHXHNhanVienHanhChanh(maTienBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienbhxh);
				}
			}
			
			if (bhxh.getNam() != 0) {
				String sql = "Select * from TienBHXHNVHC where nam = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, bhxh.getNam());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienBHXH = rs.getString(1);
					double tienBHXH = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienBHXHNhanVienHanhChanh tienbhxh = new TienBHXHNhanVienHanhChanh(maTienBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienbhxh);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public int getTongSoLuongBHXHNVHC() {
		int tongBHXH = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maTienBHXH) from TienBHXHNVHC";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongBHXH = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongBHXH;
	}
	
	public ArrayList<TienBHXHNhanVienHanhChanh> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from TienBHXHNVHC";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsTienBHXH.clear();
			while (rs.next()) {
				String maTienBHXH = rs.getString(1);
				double tienBHXH = rs.getDouble(2);
				String maNV = rs.getString(3);
				int thang = rs.getInt(4);
				int nam = rs.getInt(5);
				TienBHXHNhanVienHanhChanh tienbhxh = new TienBHXHNhanVienHanhChanh(maTienBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
				dsTienBHXH.add(tienbhxh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsTienBHXH;
	}
	
	public TienBHXHNhanVienHanhChanh timTienBHXHTheoMaNVThangNam(String maNV, int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<TienBHXHNhanVienHanhChanh> dsTK = new ArrayList<TienBHXHNhanVienHanhChanh>();
		try {
			String sql = "Select * from TienBHXHNVHC where maNhanVien = ? and thang = ? and nam = ?";
			// String sql = "Select * from ChiTietThanhToan
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maBHXH = rs.getString(1);
				double tienBHXH = rs.getDouble(2);
				return new TienBHXHNhanVienHanhChanh(maBHXH, tienBHXH, thang, nam, new NhanVienHanhChanh(maNV));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new TienBHXHNhanVienHanhChanh();
	}
}
