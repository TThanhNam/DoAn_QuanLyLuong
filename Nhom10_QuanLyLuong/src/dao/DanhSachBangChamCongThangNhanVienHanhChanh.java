package dao;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
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
import entity.NhanVienHanhChanh;
import entity.SanPham;

public class DanhSachBangChamCongThangNhanVienHanhChanh {
	private ArrayList<BangChamCongThangNhanVienHanhChanh> dsBCCTNVHC;
	public DanhSachBangChamCongThangNhanVienHanhChanh() {
		dsBCCTNVHC = new ArrayList<BangChamCongThangNhanVienHanhChanh>();
	}

	public boolean themBangChamCongThangNVHC(BangChamCongThangNhanVienHanhChanh bcc) {
		for (BangChamCongThangNhanVienHanhChanh d : dsBCCTNVHC) {
			if (d.getMaChamCongThang().equals(bcc.getMaChamCongThang()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into BangChamCongThangNhanVienHanhChanh values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, bcc.getMaChamCongThang());
			stmt.setString(2, bcc.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(3, bcc.getSoNgayDiLam());
			stmt.setInt(4, bcc.getSoBuoiLamThem());
			stmt.setInt(5, bcc.getThang());
			stmt.setInt(6, bcc.getNam());
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
			stmt = con.prepareStatement("delete from BangChamCongThangNhanVienHanhChanh where maChamCongThang = ?");
			stmt.setString(1, maChamCong);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatBangChamCongThangNVHC(BangChamCongThangNhanVienHanhChanh bcc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update BangChamCongThangNhanVienHanhChanh set soNgayDiLam = ?, soBuoiLamThem = ? where maChamCongThang = ?");
			stmt.setInt(1, bcc.getSoNgayDiLam());
			stmt.setInt(2, bcc.getSoBuoiLamThem());
			stmt.setString(3, bcc.getMaChamCongThang());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public int getTongSoLuongBangChamCongThangNVHC() {
		int tongBCC = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maChamCongThang) from BangChamCongThangNhanVienHanhChanh";
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

	public void name() {

	}

	public List<BangChamCongThangNhanVienHanhChanh> timBangChamCongThangNVHC(BangChamCongThangNhanVienHanhChanh bcc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmtNgay = null;
		ArrayList<BangChamCongThangNhanVienHanhChanh> dsTK = new ArrayList<BangChamCongThangNhanVienHanhChanh>();
		try {
			if (!bcc.getMaChamCongThang().equals("")) {
				String sql = "Select * from BangChamCongThangNhanVienHanhChanh where maChamCongThang = ?";
				String sqlNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmt.setString(1, bcc.getMaChamCongThang());
				stmtNgay.setString(1, bcc.getMaChamCongThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				ResultSet rsNgay = stmtNgay.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCC = rs.getString(1);
					String maNV = rs.getString(2);
					int thang = rs.getInt(5);
					int nam = rs.getInt(6);
					BangChamCongThangNhanVienHanhChanh bccTK = new BangChamCongThangNhanVienHanhChanh(maCC, new NhanVienHanhChanh(maNV), thang, nam);
					List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
					while (rsNgay.next()) {
						String maChamCongNgay = rsNgay.getString(1);
						String maNVNgay = rsNgay.getString(2);
						String maCCThang = rsNgay.getString(3);
						Date ngayCC = rsNgay.getDate(4);
						boolean coDiLam = rsNgay.getBoolean(5);
						boolean coLamThem = rsNgay.getBoolean(6);
						BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
								, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
						dsBccNgay.add(bccNgay);
					}
					bccTK.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
					dsTK.add(bccTK);
				}
			} 
			if (!bcc.getNhanVienHanhChanh().getMaNhanVien().equals("")) {
				String sql = "Select * from BangChamCongThangNhanVienHanhChanh where maNhanVien = ?";
				String sqlNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmt.setString(1, bcc.getNhanVienHanhChanh().getMaNhanVien());
				stmtNgay.setString(1, bcc.getMaChamCongThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maCC = rs.getString(1);
					String maNV = rs.getString(2);
					int thang = rs.getInt(5);
					int nam = rs.getInt(6);
					stmtNgay = con.prepareStatement(sqlNgay);
					stmtNgay.setString(1, maCC);
					ResultSet rsNgay = stmtNgay.executeQuery();
					List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
					while (rsNgay.next()) {
						String maChamCongNgay = rsNgay.getString(1);
						String maNVNgay = rsNgay.getString(2);
						String maCCThang = rsNgay.getString(3);
						Date ngayCC = rsNgay.getDate(4);
						boolean coDiLam = rsNgay.getBoolean(5);
						boolean coLamThem = rsNgay.getBoolean(6);
						BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
								, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
						dsBccNgay.add(bccNgay);
					}
					BangChamCongThangNhanVienHanhChanh bccThang = new BangChamCongThangNhanVienHanhChanh(maCC, new NhanVienHanhChanh(maNV), thang, nam);
					bccThang.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
					dsTK.add(bccThang);
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;	
	}
	public List<BangChamCongThangNhanVienHanhChanh> timBangChamCongTheoThangVaNam(int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmtNgay = null;
		List<BangChamCongThangNhanVienHanhChanh> dsTK = new ArrayList<BangChamCongThangNhanVienHanhChanh>();
		try {
			String sql = "Select * from BangChamCongThangNhanVienHanhChanh where thang = ? and nam = ?";
			String sqlNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
			// String sql = "Select * from ChiTietThanhToan
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maCC = rs.getString(1);
				String maNV = rs.getString(2);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmtNgay.setString(1, maCC);
				ResultSet rsNgay = stmtNgay.executeQuery();
				List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
				while (rsNgay.next()) {
					String maChamCongNgay = rsNgay.getString(1);
					String maNVNgay = rsNgay.getString(2);
					String maCCThang = rsNgay.getString(3);
					Date ngayCC = rsNgay.getDate(4);
					boolean coDiLam = rsNgay.getBoolean(5);
					boolean coLamThem = rsNgay.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
							, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsBccNgay.add(bccNgay);
				}
				BangChamCongThangNhanVienHanhChanh bccThang = new BangChamCongThangNhanVienHanhChanh(maCC, new NhanVienHanhChanh(maNV), thang, nam);
				bccThang.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
				dsTK.add(bccThang);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;	
	}
	public BangChamCongThangNhanVienHanhChanh timBangChamCongTheoMaNVVaThangNam(String maNV, int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmtNgay = null;
		try {
			String sql = "Select * from BangChamCongThangNhanVienHanhChanh where maNhanVien = ? and thang = ? and nam = ?";
			String sqlNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
			// String sql = "Select * from ChiTietThanhToan
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maCC = rs.getString(1);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmtNgay.setString(1, maCC);
				ResultSet rsNgay = stmtNgay.executeQuery();
				List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
				while (rsNgay.next()) {
					String maChamCongNgay = rsNgay.getString(1);
					String maNVNgay = rsNgay.getString(2);
					String maCCThang = rsNgay.getString(3);
					Date ngayCC = rsNgay.getDate(4);
					boolean coDiLam = rsNgay.getBoolean(5);
					boolean coLamThem = rsNgay.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
							, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsBccNgay.add(bccNgay);
				}
				BangChamCongThangNhanVienHanhChanh bccThang = new BangChamCongThangNhanVienHanhChanh(maCC, new NhanVienHanhChanh(maNV), thang, nam);
				bccThang.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
				return bccThang;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	public List<BangChamCongThangNhanVienHanhChanh> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from BangChamCongThangNhanVienHanhChanh";
			String sqlNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
			Statement statement = con.createStatement();
			PreparedStatement stmtNgay = null;
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsBCCTNVHC.clear();
			while (rs.next()) {
				String maCC = rs.getString(1);
				String maNV = rs.getString(2);
				int thang = rs.getInt(5);
				int nam = rs.getInt(6);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmtNgay.setString(1, maCC);
				ResultSet rsNgay = stmtNgay.executeQuery();
				List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
				while (rsNgay.next()) {
					String maChamCongNgay = rsNgay.getString(1);
					String maNVNgay = rsNgay.getString(2);
					String maCCThang = rsNgay.getString(3);
					Date ngayCC = rsNgay.getDate(4);
					boolean coDiLam = rsNgay.getBoolean(5);
					boolean coLamThem = rsNgay.getBoolean(6);
					BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
							, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
					dsBccNgay.add(bccNgay);
				}
				BangChamCongThangNhanVienHanhChanh s = new BangChamCongThangNhanVienHanhChanh(maCC
						, new NhanVienHanhChanh(maNV), thang, nam);
				s.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
				dsBCCTNVHC.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsBCCTNVHC;
	}
}
