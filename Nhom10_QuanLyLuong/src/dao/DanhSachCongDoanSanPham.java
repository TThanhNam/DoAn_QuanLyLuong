package dao;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:13/11/2021
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.CongDoanSanPham;
import entity.SanPham;

public class DanhSachCongDoanSanPham {
	private ArrayList<CongDoanSanPham> dsCD;

	public DanhSachCongDoanSanPham() {
		dsCD = new ArrayList<CongDoanSanPham>();
	}
	
	public boolean themCongDoan(CongDoanSanPham cd) {
		for (CongDoanSanPham c : dsCD) {
			if (c.getMaCongDoan().equals(cd.getMaCongDoan()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into CongDoanSanPham values(?, ?, ?, ?)");
			stmt.setString(1, cd.getMaCongDoan());
			stmt.setString(2, cd.getTenCongDoan());
			stmt.setString(3, cd.getSanPham().getMaSanPham());
			stmt.setDouble(4, cd.getDonGiaCD());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean xoaCongDoan(String maCongDoan) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from CongDoanSanPham where maCongDoan = ?");
			stmt.setString(1, maCongDoan);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatCongDoan(CongDoanSanPham p) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update CongDoanSanPham set maCongDoan = ?, tenCongDoan = ?, maSanPham = ?, donGiaCD = ? where maCongDoan = ?");
			stmt.setString(1, p.getMaCongDoan());
			stmt.setString(2, p.getTenCongDoan());
			stmt.setString(3, p.getSanPham().getMaSanPham());
			stmt.setDouble(4, p.getDonGiaCD());
			stmt.setString(5, p.getMaCongDoan());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<CongDoanSanPham> timKiemCongDoan(CongDoanSanPham tt) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<CongDoanSanPham> dsCD = new ArrayList<CongDoanSanPham>();
		try {
			if (!tt.getMaCongDoan().equals("")) {
				String sql = "Select * from CongDoanSanPham where maCongDoan = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tt.getMaCongDoan());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCongDoan = rs.getString(1);
					String tenCongDoan = rs.getString(2);
					String maSanPham = rs.getString(3);
					double donGia = rs.getDouble(4);
					CongDoanSanPham cd = new CongDoanSanPham(maCongDoan, tenCongDoan, new SanPham(maSanPham),donGia);
					dsCD.add(cd);
				}
			}
			if (!tt.getTenCongDoan().equals("")) {
				String sql = "Select * from CongDoanSanPham where tenCongDoan like ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1,"%" + tt.getTenCongDoan() + "%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCongDoan = rs.getString(1);
					String tenCongDoan = rs.getString(2);
					String maSanPham = rs.getString(3);
					double donGia = rs.getDouble(4);
					CongDoanSanPham cd = new CongDoanSanPham(maCongDoan, tenCongDoan, new SanPham(maSanPham),donGia);
					dsCD.add(cd);
				}
			}
			
			if (tt.getDonGiaCD() != 0) {
				String sql = "Select * from CongDoanSanPham where donGiaCD = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setDouble(1, tt.getDonGiaCD());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCongDoan = rs.getString(1);
					String tenCongDoan = rs.getString(2);
					String maSanPham = rs.getString(3);
					double donGia = rs.getDouble(4);
					CongDoanSanPham cd = new CongDoanSanPham(maCongDoan, tenCongDoan, new SanPham(maSanPham),donGia);
					dsCD.add(cd);
				}
			}
			if (!tt.getSanPham().getMaSanPham().equals("")) {
				String sql = "Select * from CongDoanSanPham where maSanPham = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tt.getSanPham().getMaSanPham());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCongDoan = rs.getString(1);
					String tenCongDoan = rs.getString(2);
					String maSanPham = rs.getString(3);
					double donGia = rs.getDouble(4);
					CongDoanSanPham cd = new CongDoanSanPham(maCongDoan, tenCongDoan, new SanPham(maSanPham),donGia);
					dsCD.add(cd);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCD;
	}
	
	public int getTongSoLuongCongDoan() {
		int tongCD = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maCongDoan) from CongDoanSanPham";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongCD = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongCD;
	}
	
	public ArrayList<CongDoanSanPham> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from CongDoanSanPham";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsCD.clear();
			while (rs.next()) {
				String maCongDoan = rs.getString(1);
				String tenCongDoan = rs.getString(2);
				String maSanPham = rs.getString(3);
				double donGia = rs.getDouble(4);
				CongDoanSanPham cd = new CongDoanSanPham(maCongDoan, tenCongDoan, new SanPham(maSanPham),donGia);
				dsCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsCD;
	}
}
