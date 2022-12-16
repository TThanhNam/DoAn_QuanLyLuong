package dao;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.BangChamCongNgayCongNhan;
import entity.BangLuongNgayCongNhan;
import entity.BangLuongThangCongNhan;
import entity.ChiTietChamCongCongNhan;
import entity.CongDoanSanPham;
import entity.SanPham;

public class DanhSachLuongNgayCongNhan {
	private List<BangLuongNgayCongNhan> dsBLCN;

	public DanhSachLuongNgayCongNhan() {
		this.dsBLCN = new ArrayList<BangLuongNgayCongNhan>();
	}
	
	public boolean themBangLuong(BangLuongNgayCongNhan blCN) {
		for (BangLuongNgayCongNhan s : dsBLCN) {
			if (s.getMaLuongNgay().equals(blCN.getMaLuongNgay()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into BangLuongNgayCongNhan values(?, ?, ?, ?)");
			stmt.setString(1, blCN.getMaLuongNgay());
			stmt.setString(2, blCN.getBangChamCongNgayCongNhan().getMaChamCongNgay());
			stmt.setString(3, blCN.getBangLuongThangCongNhan().getMaLuongThang());
			stmt.setDouble(4, blCN.getLuongNgay());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatBangLuong(BangLuongNgayCongNhan bl) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update BangLuongNgayCongNhan set "
					+ "maChamCongNgay = ?, "
					+ "maLuongThang = ?,"
					+ "luongNgay = ?  where maLuongNgay = ?");		
			stmt.setString(1, bl.getBangChamCongNgayCongNhan().getMaChamCongNgay());
			stmt.setString(2, bl.getBangLuongThangCongNhan().getMaLuongThang());
			stmt.setDouble(3, bl.getLuongNgay());
			stmt.setString(4, bl.getMaLuongNgay());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<BangLuongNgayCongNhan> timKiemBangLuong(BangLuongNgayCongNhan blCN) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmtChiTietCC = null;
		PreparedStatement stmtCD = null;

		ArrayList<BangLuongNgayCongNhan> dsTK = new ArrayList<BangLuongNgayCongNhan>();		
		try {		
			if (!blCN.getMaLuongNgay().equals("")) {
				String sql = "Select * from BangLuongNgayCongNhan where maLuongNgay = ?";
				String sqlChiTietCC = "Select * from ChiTietChamCongCongNhan where maChamCong = ?";
				String sqlCD = "Select * from CongDoanSanPham where maCongDoan = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtChiTietCC = con.prepareStatement(sqlChiTietCC);
				stmtCD = con.prepareStatement(sqlCD);
				
				stmt.setString(1, blCN.getMaLuongNgay());
				
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maLuongNgay = rs.getString(1);
					String maChamCongNgay = rs.getString(2);
					String maLuongThang = rs.getString(3);
					stmtChiTietCC.setString(1, maChamCongNgay);
					List<ChiTietChamCongCongNhan> dsChiTietCCCN = new ArrayList<ChiTietChamCongCongNhan>();
					ResultSet rsChiTietCC = stmtChiTietCC.executeQuery();
					while (rsChiTietCC.next()) {
						String maChiTiet = rsChiTietCC.getString(1);
						String maCD = rsChiTietCC.getString(2);
						int caLamViec = rsChiTietCC.getInt(3);
						int soLuongSP = rsChiTietCC.getInt(4);
						stmtCD.setString(1, maCD);
						ResultSet rsCD = stmtCD.executeQuery();
						CongDoanSanPham cd = new CongDoanSanPham();
						while (rsCD.next()) {
							double donGia = rsCD.getDouble(4);
							cd = new CongDoanSanPham(maCD, "", new SanPham(), donGia);
						}
						dsChiTietCCCN.add(new ChiTietChamCongCongNhan(maChiTiet
								, cd, caLamViec, soLuongSP
								, new BangChamCongNgayCongNhan(maChamCongNgay)));
					}
					BangLuongNgayCongNhan bl = new BangLuongNgayCongNhan(maLuongNgay, new BangChamCongNgayCongNhan(maChamCongNgay), new BangLuongThangCongNhan(maLuongThang));
					bl.tinhLuongNgay(dsChiTietCCCN);
					dsTK.add(bl);		
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
}
