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
import java.util.Date;
import java.util.List;

import connectDB.Database;
import entity.BangChamCongNgayCongNhan;
import entity.BangChamCongNgayNhanVienHanhChanh;
import entity.BangChamCongThangNhanVienHanhChanh;
import entity.ChiTietChamCongCongNhan;
import entity.CongDoanSanPham;
import entity.CongNhan;
import entity.NhanVienHanhChanh;

public class DanhSachBangChamCongNgayNhanVienHanhChanh {
	private ArrayList<BangChamCongNgayNhanVienHanhChanh> dsBCCNNVHC;
	public DanhSachBangChamCongNgayNhanVienHanhChanh() {
		dsBCCNNVHC = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
	}
	
	public boolean themBangChamCongNgayNVHC(BangChamCongNgayNhanVienHanhChanh bcc) {
		for (BangChamCongNgayNhanVienHanhChanh d : dsBCCNNVHC) {
			if (d.getMaChamCongNgay().equals(bcc.getMaChamCongNgay()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into BangChamCongNgayNhanVienHanhChanh values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, bcc.getMaChamCongNgay());
			stmt.setString(2, bcc.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setString(3, bcc.getBangChamCongThangNhanVienHanhChanh().getMaChamCongThang());
			java.sql.Date sqlDate = new java.sql.Date(bcc.getNgayChamCong().getTime());
			stmt.setDate(4, sqlDate);
			stmt.setBoolean(5, bcc.isCoDilam());
			stmt.setBoolean(6, bcc.isCoLamThem());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaBangChamCongNgayNVHC(String maChamCong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from BangChamCongNgayNhanVienHanhChanh where maChamCongNgay = ?");
			stmt.setString(1, maChamCong);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatBangChamCongNgayNVHC(BangChamCongNgayNhanVienHanhChanh bcc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update BangChamCongNgayNhanVienHanhChanh set maChamCongNgay = ?, coDiLam = ?, coLamThem = ? where maChamCongNgay = ?");
			stmt.setString(1, bcc.getMaChamCongNgay());
			stmt.setBoolean(2, bcc.isCoDilam());
			stmt.setBoolean(3, bcc.isCoLamThem());
			stmt.setString(4, bcc.getMaChamCongNgay());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public int getTongSoLuongBangChamCongNgayNVHC() {
		int tongBCC = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maChamCongNgay) from BangChamCongNgayNhanVienHanhChanh";
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
	
	public ArrayList<BangChamCongNgayNhanVienHanhChanh> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from BangChamCongNgayNhanVienHanhChanh";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsBCCNNVHC.clear();
			while (rs.next()) {
				String maChamCong = rs.getString(1);
				String maNhanVien = rs.getString(2);
				String maChamCongThang = rs.getString(3);
				Date ngayChamCong = rs.getDate(4);
				boolean coDiLam = rs.getBoolean(5);
				boolean coLamThem = rs.getBoolean(6);
				BangChamCongNgayNhanVienHanhChanh bcc = new BangChamCongNgayNhanVienHanhChanh(maChamCong, ngayChamCong, new NhanVienHanhChanh(maNhanVien), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maChamCongThang));
				dsBCCNNVHC.add(bcc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsBCCNNVHC;
	}
	public List<BangChamCongNgayNhanVienHanhChanh> timBangChamCongNgayNVHC(BangChamCongNgayNhanVienHanhChanh bcc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<BangChamCongNgayNhanVienHanhChanh> dsTK = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
		try {
			if (!bcc.getMaChamCongNgay().equals("")) {
				String sql = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongNgay = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bcc.getMaChamCongNgay());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCC = rs.getString(1);
					String maNV = rs.getString(2);
					String maCCThang = rs.getString(3);
					Date ngayCC = rs.getDate(4);
					boolean coDiLam = rs.getBoolean(5);
					boolean coLamThem = rs.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccTK = new BangChamCongNgayNhanVienHanhChanh(maCC, ngayCC, new NhanVienHanhChanh(maNV), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsTK.add(bccTK);
				}
			} 
			if (!bcc.getNhanVienHanhChanh().getMaNhanVien().equals("")) {
				String sql = "Select * from BangChamCongNgayNhanVienHanhChanh where maNhanVien = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bcc.getNhanVienHanhChanh().getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCC = rs.getString(1);
					String maNV = rs.getString(2);
					String maCCThang = rs.getString(3);
					Date ngayCC = rs.getDate(4);
					boolean coDiLam = rs.getBoolean(5);
					boolean coLamThem = rs.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccTK = new BangChamCongNgayNhanVienHanhChanh(maCC, ngayCC, new NhanVienHanhChanh(maNV), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsTK.add(bccTK);
				}
			} 
			if (bcc.getNgayChamCong() != null) {
				String sql = "Select * from BangChamCongNgayNhanVienHanhChanh where where day(ngayChamCong) = ? and MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, bcc.getNgayChamCong().getDate());
				stmt.setInt(2, bcc.getNgayChamCong().getMonth()+1);
				stmt.setInt(3, 1900 + bcc.getNgayChamCong().getYear());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCC = rs.getString(1);
					String maNV = rs.getString(2);
					String maCCThang = rs.getString(3);
					Date ngayCC = rs.getDate(4);
					boolean coDiLam = rs.getBoolean(5);
					boolean coLamThem = rs.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccTK = new BangChamCongNgayNhanVienHanhChanh(maCC, ngayCC, new NhanVienHanhChanh(maNV), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsTK.add(bccTK);
				}
			} 
			if (!bcc.getBangChamCongThangNhanVienHanhChanh().getMaChamCongThang().equals("")) {
				String sql = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bcc.getBangChamCongThangNhanVienHanhChanh().getMaChamCongThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCC = rs.getString(1);
					String maNV = rs.getString(2);
					String maCCThang = rs.getString(3);
					Date ngayCC = rs.getDate(4);
					boolean coDiLam = rs.getBoolean(5);
					boolean coLamThem = rs.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccTK = new BangChamCongNgayNhanVienHanhChanh(maCC, ngayCC, new NhanVienHanhChanh(maNV), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsTK.add(bccTK);
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;	
	}
	public BangChamCongNgayNhanVienHanhChanh timTheoMaNVVaNgayCC(String maNV, Date ngayCC) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from BangChamCongNgayNhanVienHanhChanh where ngayChamCong = ? and maNhanVien = ?";
			stmt = con.prepareStatement(sql);
			java.sql.Date sqlDate = new java.sql.Date(ngayCC.getTime());
			stmt.setDate(1, sqlDate);
			stmt.setString(2, maNV);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maChamCongNgay = rs.getString(1);
				String maNVTK = rs.getString(2);
				String maCCThang = rs.getString(3);
				Date ngayCCTK = rs.getDate(4);
				boolean coDiLam = rs.getBoolean(5);
				boolean coLamThem = rs.getBoolean(6);
				BangChamCongNgayNhanVienHanhChanh bcc = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCCTK
						, new NhanVienHanhChanh(maNVTK), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
				return bcc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<BangChamCongNgayNhanVienHanhChanh> timTheoMaNVVaThangNam(String maNV, int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		List<BangChamCongNgayNhanVienHanhChanh> dsTK = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
		try {
			String sql = "select * from BangChamCongNgayNhanVienHanhChanh where month(ngayChamCong) = ? and year(ngayChamCong) = ? and maNhanVien = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			stmt.setString(3, maNV);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maChamCongNgay = rs.getString(1);
				String maNVTK = rs.getString(2);
				String maCCThang = rs.getString(3);
				Date ngayCCTK = rs.getDate(4);
				boolean coDiLam = rs.getBoolean(5);
				boolean coLamThem = rs.getBoolean(6);
				BangChamCongNgayNhanVienHanhChanh bcc = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCCTK
						, new NhanVienHanhChanh(maNVTK), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
				dsTK.add(bcc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsTK;
	}
}
