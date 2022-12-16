package dao;

/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:12/11/2021
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.CongNhan;
import entity.NhanVien;
import entity.NhanVienHanhChanh;
import entity.TienBHXHNhanVienHanhChanh;
import entity.TienKyLuatCongNhan;
import entity.TienKyLuatNhanVienHanhChanh;

public class DanhSachTienKLNhanVienhHanhChanh {

	private ArrayList<TienKyLuatNhanVienHanhChanh>dsTienKL;

	public DanhSachTienKLNhanVienhHanhChanh() {
		dsTienKL=new ArrayList<TienKyLuatNhanVienHanhChanh>();
	}

	//thêm
	public boolean themTienKLNVHC(TienKyLuatNhanVienHanhChanh kl) {
		for (TienKyLuatNhanVienHanhChanh s : dsTienKL) {
			if (s.getMaKyLuat().equals(kl.getMaKyLuat()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into TienKyLuatNVHC values(?, ?, ?, ?, ?)");
			stmt.setString(1, kl.getMaKyLuat());
			stmt.setDouble(2,kl.getTienKyLuat());
			stmt.setString(3, kl.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(4, kl.getThang());
			stmt.setInt(5, kl.getNam());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//xoá
	public boolean xoaKL(String maTienKL) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from TienKyLuatNVHC where maTienKyLuat = ?");
			stmt.setString(1, maTienKL);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//cập nhật
	public boolean capNhatTienKL(TienKyLuatNhanVienHanhChanh kl) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TienKyLuatCN set maTienKyLuat = ?, tienKyLuat = ?, maNhanVien = ?, thang = ? , nam = ? where maTienKyLuat = ?");
			stmt.setString(1, kl.getMaKyLuat());
			stmt.setDouble(2, kl.getTienKyLuat());
			stmt.setString(3, kl.getNhanVienHanhChanh().getMaNhanVien());
			stmt.setInt(4, kl.getThang());
			stmt.setInt(5, kl.getNam());
			stmt.setString(6, kl.getMaKyLuat());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//tìm
	public ArrayList<TienKyLuatNhanVienHanhChanh> timKiemKL(TienKyLuatNhanVienHanhChanh kl) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<TienKyLuatNhanVienHanhChanh> dsTK = new ArrayList<TienKyLuatNhanVienHanhChanh>();
		try {
			if (!kl.getMaKyLuat().equals("")) {
				String sql = "Select * from TienKyLuatNVHC where maTienKyLuat = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, kl.getMaKyLuat());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienKL = rs.getString(1);
					double tienKL = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienKyLuatNhanVienHanhChanh tienkl = new TienKyLuatNhanVienHanhChanh(maTienKL, tienKL, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienkl);
				}
			}
			if (kl.getTienKyLuat() != 0) {
				String sql = "Select * from TienKyLuatNVHC where tienKyLuat = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setDouble(1, kl.getTienKyLuat());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienKL = rs.getString(1);
					double tienKL = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienKyLuatNhanVienHanhChanh tienkl = new TienKyLuatNhanVienHanhChanh(maTienKL, tienKL, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienkl);
				}
			}
			if (!kl.getNhanVienHanhChanh().getMaNhanVien().equals("")) {
				String sql = "Select * from TienKyLuatNVHC where maNhanVien = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, kl.getNhanVienHanhChanh().getMaNhanVien());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienKL = rs.getString(1);
					double tienKL = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienKyLuatNhanVienHanhChanh tienkl = new TienKyLuatNhanVienHanhChanh(maTienKL, tienKL, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienkl);
				}
			}

			if (kl.getThang() != 0) {
				String sql = "Select * from TienKyLuatNVHC where thang = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, kl.getThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienKL = rs.getString(1);
					double tienKL = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienKyLuatNhanVienHanhChanh tienkl = new TienKyLuatNhanVienHanhChanh(maTienKL, tienKL, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienkl);
				}
			}

			if (kl.getNam() != 0) {
				String sql = "Select * from TienKyLuatNVHC where nam = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, kl.getNam());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maTienKL = rs.getString(1);
					double tienKL = rs.getDouble(2);
					String maNV = rs.getString(3);
					int thang = rs.getInt(4);
					int nam = rs.getInt(5);
					TienKyLuatNhanVienHanhChanh tienkl = new TienKyLuatNhanVienHanhChanh(maTienKL, tienKL, thang, nam, new NhanVienHanhChanh(maNV));
					dsTK.add(tienkl);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	//
	public int getTongSoLuongKyLuatNVHC() {
		int tongKL = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maTienKyLuat) from TienKyLuatNVHC";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongKL = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongKL;
	}
	//đọc database
	public ArrayList<TienKyLuatNhanVienHanhChanh> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from TienKyLuatNVHC";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsTienKL.clear();
			while (rs.next()) {
				String maTienKL = rs.getString(1);
				double tienKL = rs.getDouble(2);
				String maNV = rs.getString(3);
				int thang = rs.getInt(4);
				int nam = rs.getInt(5);
				TienKyLuatNhanVienHanhChanh tienkl = new TienKyLuatNhanVienHanhChanh(maTienKL, tienKL, thang, nam, new NhanVienHanhChanh(maNV));
				dsTienKL.add(tienkl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsTienKL;
	}
	public TienKyLuatNhanVienHanhChanh timTienTienKyLuatTheoMaNVThangNam(String maNV, int thang, int nam) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select * from TienKyLuatNVHC where maNhanVien = ? and thang = ? and nam = ?";
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
				return new TienKyLuatNhanVienHanhChanh(ma, tien, thang, nam, new NhanVienHanhChanh(maNV));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new TienKyLuatNhanVienHanhChanh();
	}
}
