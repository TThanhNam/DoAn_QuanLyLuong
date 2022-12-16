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

import connectDB.Database;
import entity.CongNhan;
import entity.NhanVienHanhChanh;
import entity.SanPham;
import entity.TienBHXH;
import entity.TienBHXHCongNhan;
import entity.TienBHXHNhanVienHanhChanh;

public class DanhSachBHXHCongNhan {
	private ArrayList<TienBHXHCongNhan> dsTienBHXH;

	public DanhSachBHXHCongNhan() {
		dsTienBHXH = new ArrayList<TienBHXHCongNhan>();
	}
	
	public boolean themTienBHXHCN(TienBHXHCongNhan bhxh) {
		for (TienBHXHCongNhan s : dsTienBHXH) {
			if (s.getMaTienBHXH().equals(bhxh.getMaTienBHXH()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into TienBHXHCN values(?, ?, ?, ?, ?)");
			stmt.setString(1, bhxh.getMaTienBHXH());
			stmt.setDouble(2, bhxh.getTienBHXH());
			stmt.setString(3, bhxh.getCongNhan().getMaNhanVien());
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
			stmt = con.prepareStatement("delete from TienBHXHCN where maTienBHXH = ?");
			stmt.setString(1, maTienBHXH);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatTienBHXH(TienBHXHCongNhan bhxh) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TienBHXHCN set maTienBHXH = ?, tienBHXH = ?, maNhanVien = ?, thang = ? , nam = ? where maTienBHXH = ?");
			stmt.setString(1, bhxh.getMaTienBHXH());
			stmt.setDouble(2, bhxh.getTienBHXH());
			stmt.setString(3, bhxh.getCongNhan().getMaNhanVien());
			stmt.setInt(4, bhxh.getThang());
			stmt.setInt(5, bhxh.getNam());
			stmt.setString(6, bhxh.getMaTienBHXH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<TienBHXHCongNhan> timKiemBHXH(TienBHXHCongNhan bhxh) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<TienBHXHCongNhan> dsTK = new ArrayList<TienBHXHCongNhan>();
		try {
			if (!bhxh.getMaTienBHXH().equals("")) {
				String sql = "Select * from TienBHXHCN where maTienBHXH = ?";
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
					TienBHXHCongNhan tienbhxh = new TienBHXHCongNhan(maTienBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
					dsTK.add(tienbhxh);
				}
			}
			if (bhxh.getTienBHXH() != 0) {
				String sql = "Select * from TienBHXHCN where tienBHXH = ?";
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
					TienBHXHCongNhan tienbhxh = new TienBHXHCongNhan(maTienBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
					dsTK.add(tienbhxh);
				}
			}
			if (!bhxh.getCongNhan().getMaNhanVien().equals("")) {
				String sql = "Select * from TienBHXHCN where maNhanVien = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bhxh.getCongNhan().getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienBHXH = rs.getString(1);
					double tienBHXH = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienBHXHCongNhan tienbhxh = new TienBHXHCongNhan(maTienBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
					dsTK.add(tienbhxh);
				}
			}
			
			if (bhxh.getThang() != 0) {
				String sql = "Select * from TienBHXHCN where thang = ?";
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
					TienBHXHCongNhan tienbhxh = new TienBHXHCongNhan(maTienBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
					dsTK.add(tienbhxh);
				}
			}
			
			if (bhxh.getNam() != 0) {
				String sql = "Select * from TienBHXHCN where nam = ?";
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
					TienBHXHCongNhan tienbhxh = new TienBHXHCongNhan(maTienBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
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
			String sql = "Select count(maTienBHXH) from TienBHXHCN";
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
	
	public ArrayList<TienBHXHCongNhan> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from TienBHXHCN";
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
				TienBHXHCongNhan tienbhxh = new TienBHXHCongNhan(maTienBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
				dsTienBHXH.add(tienbhxh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsTienBHXH;
	}
	
	public TienBHXHCongNhan timTienBHXHTheoMaNVThangNam(String maNV, int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select * from TienBHXHCN where maNhanVien = ? and thang = ? and nam = ?";
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
				return new TienBHXHCongNhan(maBHXH, tienBHXH, thang, nam, new CongNhan(maNV));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new TienBHXHCongNhan();
	}
}
