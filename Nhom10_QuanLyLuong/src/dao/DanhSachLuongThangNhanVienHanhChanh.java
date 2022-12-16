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
import java.util.Date;
import java.util.List;

import connectDB.Database;
import entity.BangChamCongNgayNhanVienHanhChanh;
import entity.BangChamCongThangNhanVienHanhChanh;
import entity.BangLuongThangNhanVienHanhChanh;
import entity.NhanVienHanhChanh;
import entity.PhuCap;
import entity.TienBHXH;
import entity.TienKyLuat;

public class DanhSachLuongThangNhanVienHanhChanh {
	private List<BangLuongThangNhanVienHanhChanh> dsBLNVHC;

	public DanhSachLuongThangNhanVienHanhChanh() {
		this.dsBLNVHC = new ArrayList<>();
	}
	
	public boolean themBangLuong(BangLuongThangNhanVienHanhChanh blNVHC) {
		for (BangLuongThangNhanVienHanhChanh s : dsBLNVHC) {
			if (s.getMaLuongThang().equals(blNVHC.getMaLuongThang()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into BangLuongThangNhanVienHanhChanh values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, blNVHC.getMaLuongThang());
			stmt.setInt(2, blNVHC.getThang());
			stmt.setInt(3, blNVHC.getNam());
			stmt.setString(4, blNVHC.getBangChamCongThangNhanVienHanhChanh().getMaChamCongThang());
			stmt.setString(5, blNVHC.getTienPhuCap().getMaPhuCap());
			stmt.setString(6, blNVHC.getTienBHXH().getMaTienBHXH());
			stmt.setString(7, blNVHC.getTienKyLuat().getMaKyLuat());
			stmt.setDouble(8, blNVHC.getLuongThang());
			stmt.setDouble(9, blNVHC.getThucLinh());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatBangLuong(BangLuongThangNhanVienHanhChanh bl) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update BangLuongThangNhanVienHanhChanh set "
					+ "thang = ?, nam = ?, maChamCongThang = ?, "
					+ "maPhuCap = ?, maTienBHXH = ?, maTienKyLuat = ?, luongThang = ?, "
					+ "thucLinh = ?  where maLuongThang = ?");		
			stmt.setInt(1, bl.getThang());
			stmt.setInt(2, bl.getNam());
			stmt.setString(3, bl.getBangChamCongThangNhanVienHanhChanh().getMaChamCongThang());
			stmt.setString(4, bl.getTienPhuCap().getMaPhuCap());
			stmt.setString(5, bl.getTienBHXH().getMaTienBHXH());
			stmt.setString(6, bl.getTienKyLuat().getMaKyLuat());
			stmt.setDouble(7, bl.getLuongThang());
			stmt.setDouble(8, bl.getThucLinh());
			stmt.setString(9, bl.getMaLuongThang());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<BangLuongThangNhanVienHanhChanh> timKiemBangLuong(BangLuongThangNhanVienHanhChanh blNVHC) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmtBCCThang = null;
		PreparedStatement stmtBCCNgay = null;
		PreparedStatement stmtNV = null;
		PreparedStatement stmtBHXH = null;
		PreparedStatement stmtPC = null;
		PreparedStatement stmtKL = null;
		ArrayList<BangLuongThangNhanVienHanhChanh> dsTK = new ArrayList<BangLuongThangNhanVienHanhChanh>();		
		try {		
			if (!blNVHC.getMaLuongThang().equals("")) {
				String sql = "Select * from BangLuongThangNhanVienHanhChanh where maLuongThang = ?";
				String sqlBCCThang = "Select * from BangChamCongThangNhanVienHanhChanh where maChamCongThang = ?";
				String sqlBCCNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
				String sqlNV = "Select * from NhanVienHanhChanh where maNhanVien = ?";
				String sqlBHXH = "Select * from TienBHXHNVHC where maTienBHXH = ?";
				String sqlPC = "Select * from PhuCapNVHC where maPhuCap = ?";
				String sqlKL = "Select * from TienKyLuatNVHC where maTienKyLuat = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtBCCThang = con.prepareStatement(sqlBCCThang);
				stmtBCCNgay = con.prepareStatement(sqlBCCNgay);
				stmtNV = con.prepareStatement(sqlNV);
				stmtBHXH = con.prepareStatement(sqlBHXH);
				stmtPC = con.prepareStatement(sqlPC);
				stmtKL = con.prepareStatement(sqlKL);
				stmt.setString(1, blNVHC.getMaLuongThang());
				
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maLuongThang = rs.getString(1);
					int thang = rs.getInt(2);
					int nam = rs.getInt(3);
					String maChamCong = rs.getString(4);
					String maPhuCap = rs.getString(5);
					String maBHXH = rs.getString(6);
					String maKyLuat = rs.getString(7);
					stmtBCCThang.setString(1, maChamCong);
					ResultSet rsBCCThang = stmtBCCThang.executeQuery();
					BangChamCongThangNhanVienHanhChanh bangChamCongThang = new BangChamCongThangNhanVienHanhChanh(maChamCong);
					while (rsBCCThang.next()) {
						String maNV = rsBCCThang.getString(2);
						stmtNV.setString(1, maNV);
						ResultSet rsNV = stmtNV.executeQuery();
						NhanVienHanhChanh nvhc = new NhanVienHanhChanh(maNV);
						while (rsNV.next()) {
							double luongCoBan = rsNV.getDouble(10);
							nvhc.setLuongNgayCoBan(luongCoBan);
						}
						stmtBCCNgay.setString(1, maChamCong);
						ResultSet rsBCCNgay = stmtBCCNgay.executeQuery();
						List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
						while (rsBCCNgay.next()) {
							String maChamCongNgay = rsBCCNgay.getString(1);
							String maNVNgay = rsBCCNgay.getString(2);
							String maCCThang = rsBCCNgay.getString(3);
							Date ngayCC = rsBCCNgay.getDate(4);
							boolean coDiLam = rsBCCNgay.getBoolean(5);
							boolean coLamThem = rsBCCNgay.getBoolean(6);
							BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
									, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
							dsBccNgay.add(bccNgay);
						}
						bangChamCongThang = new BangChamCongThangNhanVienHanhChanh(maChamCong, nvhc, thang, nam);
						bangChamCongThang.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
					}
					stmtBHXH.setString(1, maBHXH);
					ResultSet rsBHXH = stmtBHXH.executeQuery();
					TienBHXH BHXH = new TienBHXH(maBHXH);
					while (rsBHXH.next()) {
						double tienBHXH = rsBHXH.getDouble(2);
						BHXH.setTienBHXH(tienBHXH);
					}
					stmtPC.setString(1, maPhuCap);
					ResultSet rsPC = stmtPC.executeQuery();
					PhuCap phuCap = new PhuCap(maPhuCap);
					while (rsPC.next()) {
						double tienPC = rsPC.getDouble(2);
						phuCap.setTienPhuCap(tienPC);
					}
					stmtKL.setString(1, maKyLuat);
					ResultSet rsKL = stmtKL.executeQuery();
					TienKyLuat kyLuat = new TienKyLuat(maKyLuat);
					while (rsKL.next()) {
						double tienKL = rsKL.getDouble(2);
						kyLuat.setTienKyLuat(tienKL);
					}
					BangLuongThangNhanVienHanhChanh bl = new BangLuongThangNhanVienHanhChanh(
							maLuongThang, thang, nam, 
							BHXH, kyLuat, phuCap,
							bangChamCongThang);
					bl.setLuongThangVaThucLinh();
					dsTK.add(bl);		
				}
			}		
			if(blNVHC.getThang() != 0 && blNVHC.getNam() != 0) {
				String sql = "Select * from BangLuongThangNhanVienHanhChanh where thang = ? and nam = ?";
				String sqlBCCThang = "Select * from BangChamCongThangNhanVienHanhChanh where maChamCongThang = ?";
				String sqlBCCNgay = "Select * from BangChamCongNgayNhanVienHanhChanh where maChamCongThang = ?";
				String sqlNV = "Select * from NhanVienHanhChanh where maNhanVien = ?";
				String sqlBHXH = "Select * from TienBHXHNVHC where maTienBHXH = ?";
				String sqlPC = "Select * from PhuCapNVHC where maPhuCap = ?";
				String sqlKL = "Select * from TienKyLuatNVHC where maTienKyLuat = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtBCCThang = con.prepareStatement(sqlBCCThang);
				stmtBCCNgay = con.prepareStatement(sqlBCCNgay);
				stmtNV = con.prepareStatement(sqlNV);
				stmtBHXH = con.prepareStatement(sqlBHXH);
				stmtPC = con.prepareStatement(sqlPC);
				stmtKL = con.prepareStatement(sqlKL);
				
				stmt.setInt(1, blNVHC.getThang());
				stmt.setInt(2, blNVHC.getNam());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
			
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maLuongThang = rs.getString(1);
					int thang = rs.getInt(2);
					int nam = rs.getInt(3);
					String maChamCong = rs.getString(4);
					String maPhuCap = rs.getString(5);
					String maBHXH = rs.getString(6);
					String maKyLuat = rs.getString(7);
					stmtBCCThang.setString(1, maChamCong);
					ResultSet rsBCCThang = stmtBCCThang.executeQuery();
					BangChamCongThangNhanVienHanhChanh bangChamCongThang = new BangChamCongThangNhanVienHanhChanh(maChamCong);
					while (rsBCCThang.next()) {
						String maNV = rsBCCThang.getString(2);
						stmtNV.setString(1, maNV);
						ResultSet rsNV = stmtNV.executeQuery();
						NhanVienHanhChanh nvhc = new NhanVienHanhChanh(maNV);
						while (rsNV.next()) {
							double luongCoBan = rsNV.getDouble(10);
							nvhc.setLuongNgayCoBan(luongCoBan);
						}
						stmtBCCNgay.setString(1, maChamCong);
						ResultSet rsBCCNgay = stmtBCCNgay.executeQuery();
						List<BangChamCongNgayNhanVienHanhChanh> dsBccNgay = new ArrayList<BangChamCongNgayNhanVienHanhChanh>();
						while (rsBCCNgay.next()) {
							String maChamCongNgay = rsBCCNgay.getString(1);
							String maNVNgay = rsBCCNgay.getString(2);
							String maCCThang = rsBCCNgay.getString(3);
							Date ngayCC = rsBCCNgay.getDate(4);
							boolean coDiLam = rsBCCNgay.getBoolean(5);
							boolean coLamThem = rsBCCNgay.getBoolean(6);
							BangChamCongNgayNhanVienHanhChanh bccNgay = new BangChamCongNgayNhanVienHanhChanh(maChamCongNgay, ngayCC
									, new NhanVienHanhChanh(maNVNgay), coDiLam, coLamThem, new BangChamCongThangNhanVienHanhChanh(maCCThang));
							dsBccNgay.add(bccNgay);
						}
						bangChamCongThang = new BangChamCongThangNhanVienHanhChanh(maChamCong, nvhc, thang, nam);
						bangChamCongThang.setSoNgayDiLamVaSoBuoiLamThem(dsBccNgay);
					}
					stmtBHXH.setString(1, maBHXH);
					ResultSet rsBHXH = stmtBHXH.executeQuery();
					TienBHXH BHXH = new TienBHXH(maBHXH);
					while (rsBHXH.next()) {
						double tienBHXH = rsBHXH.getDouble(2);
						BHXH.setTienBHXH(tienBHXH);
					}
					stmtPC.setString(1, maPhuCap);
					ResultSet rsPC = stmtPC.executeQuery();
					PhuCap phuCap = new PhuCap(maPhuCap);
					while (rsPC.next()) {
						double tienPC = rsPC.getDouble(2);
						phuCap.setTienPhuCap(tienPC);
					}
					stmtKL.setString(1, maKyLuat);
					ResultSet rsKL = stmtKL.executeQuery();
					TienKyLuat kyLuat = new TienKyLuat(maKyLuat);
					while (rsKL.next()) {
						double tienKL = rsKL.getDouble(2);
						kyLuat.setTienKyLuat(tienKL);
					}
					BangLuongThangNhanVienHanhChanh bl = new BangLuongThangNhanVienHanhChanh(
							maLuongThang, thang, nam, 
							BHXH, kyLuat, phuCap,
							bangChamCongThang);
					bl.setLuongThangVaThucLinh();
					dsTK.add(bl);		
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
}
