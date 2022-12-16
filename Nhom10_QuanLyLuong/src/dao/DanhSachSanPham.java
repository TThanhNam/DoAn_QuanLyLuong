package dao;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:12/11/2021
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.DonVi;
import entity.SanPham;

public class DanhSachSanPham {
	private ArrayList<SanPham> dsSanPham;

	public DanhSachSanPham() {
		dsSanPham = new ArrayList<SanPham>();
	}
	
	public boolean themSanPham(SanPham sp) {
		for (SanPham s : dsSanPham) {
			if (s.getMaSanPham().equals(sp.getMaSanPham()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into SanPham values(?, ?, ?)");
			stmt.setString(1, sp.getMaSanPham());
			stmt.setString(2, sp.getTenSanPham());
			stmt.setDouble(3, sp.getDonGiaSP());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean xoaSanPham(String maSanPham) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from SanPham where maSanPham = ?");
			stmt.setString(1, maSanPham);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatSanPham(SanPham sp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update SanPham set maSanPham = ?, tenSanPham = ?, donGiaSP = ? where maSanPham = ?");
			stmt.setString(1, sp.getMaSanPham());
			stmt.setString(2, sp.getTenSanPham());
			stmt.setDouble(3, sp.getDonGiaSP());
			stmt.setString(4, sp.getMaSanPham());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<SanPham> timKiemSanPham(SanPham sp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try {
			if (!sp.getMaSanPham().equals("")) {
				String sql = "Select * from SanPham where maSanPham = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, sp.getMaSanPham());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maSanPham = rs.getString(1);
					String tenSanPham = rs.getString(2);
					double donGia = rs.getDouble(3);
					SanPham s = new SanPham(maSanPham, tenSanPham, donGia);
					dsSP.add(s);
				}
			}
			if (!sp.getTenSanPham().equals("")) {
				String sql = "Select * from SanPham where tenSanPham like ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, "%"+sp.getTenSanPham()+"%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maSanPham = rs.getString(1);
					String tenSanPham = rs.getString(2);
					double donGia = rs.getDouble(3);
					SanPham s = new SanPham(maSanPham, tenSanPham, donGia);
					dsSP.add(s);
				}
			}
			if (sp.getDonGiaSP() != 0) {
				String sql = "Select * from SanPham where donGiaSP = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setDouble(1, sp.getDonGiaSP());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maSanPham = rs.getString(1);
					String tenSanPham = rs.getString(2);
					double donGia = rs.getDouble(3);
					SanPham s = new SanPham(maSanPham, tenSanPham, donGia);
					dsSP.add(s);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSP;
	}
	
	public int getTongSoLuongSanPham() {
		int tongSP = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maSanPham) from SanPham";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongSP = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongSP;
	}
	
	public ArrayList<SanPham> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from SanPham";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsSanPham.clear();
			while (rs.next()) {
				String maSanPham = rs.getString(1);
				String tenSanPham = rs.getString(2);
				double donGia = rs.getDouble(3);
				SanPham sp = new SanPham(maSanPham, tenSanPham, donGia);
				dsSanPham.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsSanPham;
	}
}
