package dao;

/*
 * Author:Hoàng Huy Vũ
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
import entity.NhanVienHanhChanh;
import entity.PhuCapNhanVienHanhChanh;
import entity.TienKyLuatNhanVienHanhChanh;

public class DanhSachPhuCapNhanVienHC {
	private ArrayList<PhuCapNhanVienHanhChanh> dsPhuCapNhanVienHanhChanh;

	public DanhSachPhuCapNhanVienHC() {
		dsPhuCapNhanVienHanhChanh = new ArrayList<PhuCapNhanVienHanhChanh>();
	}

	public boolean themPhuCapNhanVienHC(PhuCapNhanVienHanhChanh pc) {
		for (PhuCapNhanVienHanhChanh s : dsPhuCapNhanVienHanhChanh) {
			if (s.getMaPhuCap().equals(pc.getMaPhuCap()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into PhuCapNVHC values(?, ?, ?, ?, ?)");
			stmt.setString(1, pc.getMaPhuCap());
			stmt.setDouble(2, pc.getTienPhuCap());
			stmt.setString(3, pc.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(4, pc.getThang());
			stmt.setInt(5, pc.getNam());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaTienPhuCap(String maPhuCap) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from PhuCapNVHC where maPhuCap = ?");
			stmt.setString(1, maPhuCap);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatTienPhuCap(PhuCapNhanVienHanhChanh pc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update PhuCapNVHC set maPhuCap = ?, tienPhuCap = ?, maNhanVien = ?, thang = ? , nam = ? where maPhuCap = ?");
			stmt.setString(1, pc.getMaPhuCap());
			stmt.setDouble(2, pc.getTienPhuCap());
			stmt.setString(3, pc.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(4, pc.getThang());
			stmt.setInt(5, pc.getNam());
			stmt.setString(6, pc.getMaPhuCap());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<PhuCapNhanVienHanhChanh> timKiemPhuCap(PhuCapNhanVienHanhChanh pc) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<PhuCapNhanVienHanhChanh> dsTK = new ArrayList<PhuCapNhanVienHanhChanh>();
		try {
			if (!pc.getMaPhuCap().equals("")) {
				String sql = "Select * from PhuCapNVHC where maPhuCap = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, pc.getMaPhuCap());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maPhuCap = rs.getString(1);
					double tienPhuCap = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					PhuCapNhanVienHanhChanh tienPC = new PhuCapNhanVienHanhChanh(maPhuCap, tienPhuCap, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienPC);
				}
			}
			if (pc.getTienPhuCap() != 0) {
				String sql = "Select * from PhuCapNVHC where tienPhuCap = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setDouble(1,pc.getTienPhuCap());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maPhuCap = rs.getString(1);
					double tienPhuCap = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					PhuCapNhanVienHanhChanh phucap = new PhuCapNhanVienHanhChanh(maPhuCap, tienPhuCap, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(phucap);
				}
			}
			if (!pc.getNhanVienHanhChanh().getMaNhanVien().equals("")) {
				String sql = "Select * from PhuCapNVHC where maNhanVien = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, pc.getNhanVienHanhChanh().getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maPhuCap = rs.getString(1);
					double tienPhuCap = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					PhuCapNhanVienHanhChanh phucap = new PhuCapNhanVienHanhChanh(maPhuCap, tienPhuCap, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(phucap);
				}
			}
			
			if (pc.getThang() != 0) {
				String sql = "Select * from PhuCapNVHC where thang = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, pc.getThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maPhuCap = rs.getString(1);
					double tienPhuCap = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					PhuCapNhanVienHanhChanh phucap = new PhuCapNhanVienHanhChanh(maPhuCap, tienPhuCap, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(phucap);
				}
			}
			
			if (pc.getNam() != 0) {
				String sql = "Select * from PhuCapNVHC where nam = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, pc.getNam());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maPhuCap = rs.getString(1);
					double tienPhuCap = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					PhuCapNhanVienHanhChanh phucap = new PhuCapNhanVienHanhChanh(maPhuCap, tienPhuCap, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(phucap);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	public int getTongSoLuongTienPhuCap() {
		int tongTienThuong = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maPhuCap) from PhuCapNVHC";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongTienThuong = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongTienThuong;
	}
	public ArrayList<PhuCapNhanVienHanhChanh> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from PhuCapNVHC";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsPhuCapNhanVienHanhChanh.clear();
			while (rs.next()) {
				String maPhuCap = rs.getString(1);
				double tienPhuCap = rs.getDouble(2);
				String maNV = rs.getString(3);
				int thang = rs.getInt(4);
				int nam = rs.getInt(5);
				PhuCapNhanVienHanhChanh tienPC = new PhuCapNhanVienHanhChanh(maPhuCap, tienPhuCap, thang, nam, new NhanVienHanhChanh(maNV));
				dsPhuCapNhanVienHanhChanh.add(tienPC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsPhuCapNhanVienHanhChanh;
	}
	
	public PhuCapNhanVienHanhChanh timPhuCapTheoMaNVThangNam(String maNV, int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<PhuCapNhanVienHanhChanh> dsTK = new ArrayList<PhuCapNhanVienHanhChanh>();
		try {
			String sql = "Select * from PhuCapNVHC where maNhanVien = ? and thang = ? and nam = ?";
			// String sql = "Select * from ChiTietThanhToan
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String ma = rs.getString(1);
				double tien = rs.getDouble(2);
				return new PhuCapNhanVienHanhChanh(ma, tien, thang, nam, new NhanVienHanhChanh(maNV));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new PhuCapNhanVienHanhChanh();
	}
}
