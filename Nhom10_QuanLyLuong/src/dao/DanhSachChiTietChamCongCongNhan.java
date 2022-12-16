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

import connectDB.Database;
import entity.BangChamCongNgayCongNhan;
import entity.ChiTietChamCongCongNhan;
import entity.CongDoanSanPham;
import entity.CongNhan;

public class DanhSachChiTietChamCongCongNhan {
	private ArrayList<ChiTietChamCongCongNhan> dsCTCCCN;
	public DanhSachChiTietChamCongCongNhan() {
		dsCTCCCN = new ArrayList<ChiTietChamCongCongNhan>();
	}
	
	public boolean themChiTietCCCN(ChiTietChamCongCongNhan ct) {
		for (ChiTietChamCongCongNhan d : dsCTCCCN) {
			if (d.getMaChiTietCC().equals(ct.getMaChiTietCC()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into ChiTietChamCongCongNhan values(?, ?, ?, ?, ?)");
			stmt.setString(1, ct.getMaChiTietCC());
			stmt.setString(2, ct.getCongDoan().getMaCongDoan());
			stmt.setInt(3, ct.getCaLamViec());
			stmt.setInt(4, ct.getSoLuongSP());
			stmt.setString(5, ct.getBangChamCongCN().getMaChamCongNgay());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaChiTietCCCN(String maChiTiet) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from ChiTietChamCongCongNhan where maChiTietCC = ?");
			stmt.setString(1, maChiTiet);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatChiTietCCCN(ChiTietChamCongCongNhan p) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update ChiTietChamCongCongNhan set maChiTietCC = ?, maCongDoan = ?, caLamViec = ?, soLuongSP = ?, maChamCong = ? where maChiTietCC = ?");
			stmt.setString(1, p.getMaChiTietCC());
			stmt.setString(2, p.getCongDoan().getMaCongDoan());
			stmt.setInt(3, p.getCaLamViec());
			stmt.setInt(4, p.getSoLuongSP());
			stmt.setString(5, p.getBangChamCongCN().getMaChamCongNgay());
			stmt.setString(6, p.getMaChiTietCC());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<ChiTietChamCongCongNhan> timKiemChiTietCCCN(ChiTietChamCongCongNhan ct) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<ChiTietChamCongCongNhan> dsTK = new ArrayList<ChiTietChamCongCongNhan>();
		try {
			if (!ct.getMaChiTietCC().equals("")) {
				String sql = "Select * from ChiTietChamCongCongNhan where maChiTietCC = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, ct.getMaChiTietCC());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChiTietCC = rs.getString(1);
					String maCongDoan = rs.getString(2);
					int caLamViec = rs.getInt(3);
					int soLuong = rs.getInt(4);
					String maChamCong = rs.getString(5);
					ChiTietChamCongCongNhan s = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
					dsTK.add(s);
				}
			}
			if (!ct.getBangChamCongCN().getMaChamCongNgay().equals("")) {
				String sql = "Select * from ChiTietChamCongCongNhan where maChamCong = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, ct.getBangChamCongCN().getMaChamCongNgay());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChiTietCC = rs.getString(1);
					String maCongDoan = rs.getString(2);
					int caLamViec = rs.getInt(3);
					int soLuong = rs.getInt(4);
					String maChamCong = rs.getString(5);
					ChiTietChamCongCongNhan s = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
					dsTK.add(s);
				}
			}
			
			if (ct.getCaLamViec() != 0) {
				String sql = "Select * from ChiTietChamCongCongNhan where caLamViec = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, ct.getCaLamViec());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChiTietCC = rs.getString(1);
					String maCongDoan = rs.getString(2);
					int caLamViec = rs.getInt(3);
					int soLuong = rs.getInt(4);
					String maChamCong = rs.getString(5);
					ChiTietChamCongCongNhan s = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
					dsTK.add(s);
				}
			}
			if (!ct.getCongDoan().getMaCongDoan().equals("")) {
				String sql = "Select * from ChiTietChamCongCongNhan where maCongDoan = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, ct.getCongDoan().getMaCongDoan());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChiTietCC = rs.getString(1);
					String maCongDoan = rs.getString(2);
					int caLamViec = rs.getInt(3);
					int soLuong = rs.getInt(4);
					String maChamCong = rs.getString(5);
					ChiTietChamCongCongNhan s = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
					dsTK.add(s);
				}
			}
			if (ct.getSoLuongSP() != 0) {
				String sql = "Select * from ChiTietChamCongCongNhan where soLuongSP = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, ct.getSoLuongSP());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maChiTietCC = rs.getString(1);
					String maCongDoan = rs.getString(2);
					int caLamViec = rs.getInt(3);
					int soLuong = rs.getInt(4);
					String maChamCong = rs.getString(5);
					ChiTietChamCongCongNhan s = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
					dsTK.add(s);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public int getTongSoLuongChiTietCTCCCN() {
		int tongCT = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maChiTietCC) from ChiTietChamCongCongNhan";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongCT = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongCT;
	}
	
	public ArrayList<ChiTietChamCongCongNhan> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from ChiTietChamCongCongNhan";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			dsCTCCCN.clear();
			while (rs.next()) {
				String maChiTietCC = rs.getString(1);
				String maCongDoan = rs.getString(2);
				int caLamViec = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maChamCong = rs.getString(5);
				ChiTietChamCongCongNhan s = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
				dsCTCCCN.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsCTCCCN;
	}
	public ChiTietChamCongCongNhan timTheoCaVaMaCC(String maCC, int ca) {
		
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<BangChamCongNgayCongNhan> dsTK = new ArrayList<BangChamCongNgayCongNhan>();
		try {
			String sql = "select * from ChiTietChamCongCongNhan where maChamCong = ? and caLamViec = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCC);
			stmt.setInt(2, ca);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maChiTietCC = rs.getString(1);
				String maCongDoan = rs.getString(2);
				int caLamViec = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maChamCong = rs.getString(5);
				ChiTietChamCongCongNhan ct = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
				return ct;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
public ChiTietChamCongCongNhan timTheoCaVaMaCD(String maCD, int ca) {
		
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<BangChamCongNgayCongNhan> dsTK = new ArrayList<BangChamCongNgayCongNhan>();
		try {
			String sql = "select * from ChiTietChamCongCongNhan where maCongDoan = ? and caLamViec = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCD);
			stmt.setInt(2, ca);
			// Thuc hien cau lenh sql tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery();
			
			// Duyet tren ket qua ve
			while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
				String maChiTietCC = rs.getString(1);
				String maCongDoan = rs.getString(2);
				int caLamViec = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maChamCong = rs.getString(5);
				ChiTietChamCongCongNhan ct = new ChiTietChamCongCongNhan(maChiTietCC, new CongDoanSanPham(maCongDoan), caLamViec, soLuong, new BangChamCongNgayCongNhan(maChamCong));
				return ct;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}