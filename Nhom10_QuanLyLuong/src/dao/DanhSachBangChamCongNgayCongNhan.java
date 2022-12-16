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
import java.util.Date;
import java.util.List;

import connectDB.Database;
import entity.BangChamCongNgayCongNhan;
import entity.CongNhan;

public class DanhSachBangChamCongNgayCongNhan {
	private ArrayList<BangChamCongNgayCongNhan> dsBCCCN;
	public DanhSachBangChamCongNgayCongNhan() {
		dsBCCCN = new ArrayList<BangChamCongNgayCongNhan>();
	}
	
	public boolean themBangChamCongNgayCN(BangChamCongNgayCongNhan bcc) {
		for (BangChamCongNgayCongNhan d : dsBCCCN) {
			if (d.getMaChamCongNgay().equals(bcc.getMaChamCongNgay()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into BangChamCongNgayCongNhan values(?, ?, ?)");
			stmt.setString(1, bcc.getMaChamCongNgay());
			stmt.setString(2, bcc.getCongNhan().getMaNhanVien());
			java.sql.Date sqlDate = new java.sql.Date(bcc.getNgayChamCong().getTime());
			stmt.setDate(3, sqlDate);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaBangChamCongCN(String maChamCong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from BangChamCongNgayCongNhan where maChamCongNgay = ?");
			stmt.setString(1, maChamCong);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatBangChamCongCN(BangChamCongNgayCongNhan bcc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update BangChamCongNgayCongNhan set maChamCongNgay = ?, maNhanVien = ?, ngayChamCong = ? where maChamCongNgay = ?");
			stmt.setString(1, bcc.getMaChamCongNgay());
			stmt.setString(2, bcc.getCongNhan().getMaNhanVien());
			java.sql.Date sqlDate = new java.sql.Date(bcc.getNgayChamCong().getTime());
			stmt.setDate(3, sqlDate);
			stmt.setString(4, bcc.getMaChamCongNgay());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<BangChamCongNgayCongNhan> timKiemBangChamCongNgayCN(BangChamCongNgayCongNhan bcc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<BangChamCongNgayCongNhan> dsTK = new ArrayList<BangChamCongNgayCongNhan>();
		try {
			if (!bcc.getMaChamCongNgay().equals("")) {
				String sql = "Select * from BangChamCongNgayCongNhan where maChamCongNgay = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bcc.getMaChamCongNgay());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChamCong = rs.getString(1);
					String maNhanVien = rs.getString(2);
					Date ngayChamCong = rs.getDate(3);
					
					BangChamCongNgayCongNhan s = new BangChamCongNgayCongNhan(maChamCong, ngayChamCong, new CongNhan(maNhanVien));
					dsTK.add(s);
				}
			}
			if (!bcc.getCongNhan().getMaNhanVien().equals("")) {
				String sql = "Select * from BangChamCongNgayCongNhan where maNhanVien = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bcc.getCongNhan().getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChamCong = rs.getString(1);
					String maNhanVien = rs.getString(2);
					Date ngayChamCong = rs.getDate(3);
					
					BangChamCongNgayCongNhan s = new BangChamCongNgayCongNhan(maChamCong, ngayChamCong, new CongNhan(maNhanVien));
					dsTK.add(s);
				}
			}
			if (bcc.getNgayChamCong() != null) {
				String sql = "select * from BangChamCongNgayCongNhan where day(ngayChamCong) = ? and MONTH(ngayChamCong) = ? and year(ngayChamCong) = ? ";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, bcc.getNgayChamCong().getDate());
				stmt.setInt(2, bcc.getNgayChamCong().getMonth()+1);
				stmt.setInt(3, 1900 + bcc.getNgayChamCong().getYear());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChamCong = rs.getString(1);
					String maNhanVien = rs.getString(2);
					Date ngayChamCong = rs.getDate(3);
					
					BangChamCongNgayCongNhan s = new BangChamCongNgayCongNhan(maChamCong, ngayChamCong, new CongNhan(maNhanVien));
					dsTK.add(s);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public int getTongSoLuongBangChamCongCN() {
		int tongBCC = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maChamCongNgay) from BangChamCongNgayCongNhan";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongBCC = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongBCC;
	}
	
	public List<BangChamCongNgayCongNhan> timTheoThangVaMaNV(String maNV, int thang, int nam) {
		
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<BangChamCongNgayCongNhan> dsTK = new ArrayList<BangChamCongNgayCongNhan>();
			
		try {
			String sql = "select * from BangChamCongNgayCongNhan where MONTH(ngayChamCong) = ? and year(ngayChamCong) = ? and maNhanVien = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			stmt.setString(3, maNV);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maChamCong = rs.getString(1);
				String maNhanVien = rs.getString(2);
				Date ngayChamCong = rs.getDate(3);
				
				BangChamCongNgayCongNhan s = new BangChamCongNgayCongNhan(maChamCong, ngayChamCong, new CongNhan(maNhanVien));
				dsTK.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public ArrayList<BangChamCongNgayCongNhan> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from BangChamCongNgayCongNhan";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsBCCCN.clear();
			while (rs.next()) {
				String maChamCong = rs.getString(1);
				String maNhanVien = rs.getString(2);
				Date ngayChamCong = rs.getDate(3);
				
				BangChamCongNgayCongNhan s = new BangChamCongNgayCongNhan(maChamCong, ngayChamCong, new CongNhan(maNhanVien));
				dsBCCCN.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsBCCCN;
	}
	
public BangChamCongNgayCongNhan timTheoNgayVaMaNV(String maNV, Date ngayCC) {
		
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<BangChamCongNgayCongNhan> dsTK = new ArrayList<BangChamCongNgayCongNhan>();
		try {
//			String sql = "select * from BangChamCongNgayCongNhan where date(ngayChamCong) = ? and MONTH(ngayChamCong) = ? and year(ngayChamCong) = ? and maNhanVien = ?";
			String sql = "select * from BangChamCongNgayCongNhan where ngayChamCong = ? and maNhanVien = ?";
			stmt = con.prepareStatement(sql);
//			stmt.setInt(1, ngayCC.getDate());
//			stmt.setInt(2, ngayCC.getMonth());
//			stmt.setInt(3, ngayCC.getYear());
			java.sql.Date sqlDate = new java.sql.Date(ngayCC.getTime());
			stmt.setDate(1, sqlDate);
			stmt.setString(2, maNV);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maChamCong = rs.getString(1);
				String maNhanVien = rs.getString(2);
				Date ngayChamCong = rs.getDate(3);
				
				BangChamCongNgayCongNhan bcc = new BangChamCongNgayCongNhan(maChamCong, ngayChamCong, new CongNhan(maNhanVien));
				return bcc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
