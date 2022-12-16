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
import entity.CongNhan;
import entity.PhuCap;
import entity.SanPham;
import entity.TienBHXH;
import entity.TienKyLuat;

public class DanhSachLuongThangCongNhan {
	private List<BangLuongThangCongNhan> dsBLCN;

	public DanhSachLuongThangCongNhan() {
		this.dsBLCN = new ArrayList<BangLuongThangCongNhan>();
	}
	
	public boolean themBangLuong(BangLuongThangCongNhan blCN) {
		for (BangLuongThangCongNhan s : dsBLCN) {
			if (s.getMaLuongThang().equals(blCN.getMaLuongThang()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into BangLuongThangCongNhan values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, blCN.getMaLuongThang());
			stmt.setInt(2, blCN.getThang());
			stmt.setInt(3, blCN.getNam());
			stmt.setString(4, blCN.getCongNhan().getMaNhanVien());
			stmt.setString(5, blCN.getTienPhuCap().getMaPhuCap());
			stmt.setString(6, blCN.getTienBHXH().getMaTienBHXH());
			stmt.setString(7, blCN.getTienKyLuat().getMaKyLuat());
			stmt.setDouble(8, blCN.getLuongThang());
			stmt.setDouble(9, blCN.getThucLinh());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatBangLuong(BangLuongThangCongNhan bl) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update BangLuongThangCongNhan set "
					+ "thang = ?, nam = ?, maCongNhan = ?, "
					+ "maPhuCap = ?, maTienBHXH = ?, maTienKyLuat = ?, luongThang = ?, "
					+ "thucLinh = ?  where maLuongThang = ?");		
			stmt.setInt(1, bl.getThang());
			stmt.setInt(2, bl.getNam());
			stmt.setString(3, bl.getCongNhan().getMaNhanVien());
			stmt.setString(4, bl.getTienPhuCap().getMaPhuCap());
			stmt.setString(5, bl.getTienBHXH().getMaTienBHXH());
			stmt.setString(6, bl.getTienKyLuat().getMaKyLuat());
			stmt.setDouble(7, bl.getLuongThang());
			stmt.setDouble(8, bl.getThucLinh());
			stmt.setString(9,bl.getMaLuongThang());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<BangLuongThangCongNhan> timKiemBangLuong(BangLuongThangCongNhan blCN) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmtNgay = null;
		PreparedStatement stmtCTCC = null;
		PreparedStatement stmtCD = null;
		PreparedStatement stmtBHXH = null;
		PreparedStatement stmtPC = null;
		PreparedStatement stmtKL = null;
		ArrayList<BangLuongThangCongNhan> dsTK = new ArrayList<BangLuongThangCongNhan>();		
		try {		
			if (!blCN.getMaLuongThang().equals("")) {
				String sql = "Select * from BangLuongThangCongNhan where maLuongThang = ?";
				String sqlNgay = "Select * from BangLuongNgayCongNhan where maLuongThang = ?";
				String sqlCTCC = "Select * from ChiTietChamCongCongNhan where maChamCong = ?";
				String sqlCD = "Select * from CongDoanSanPham where maCongDoan = ?";
				String sqlBHXH = "Select * from TienBHXHCN where maTienBHXH = ?";
				String sqlPC = "Select * from PhuCapCN where maPhuCap = ?";
				String sqlKL = "Select * from TienKyLuatCN where maTienKyLuat = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmtCTCC = con.prepareStatement(sqlCTCC);
				stmtCD = con.prepareStatement(sqlCD);
				stmtBHXH = con.prepareStatement(sqlBHXH);
				stmtPC = con.prepareStatement(sqlPC);
				stmtKL = con.prepareStatement(sqlKL);
				
				stmt.setString(1, blCN.getMaLuongThang());
				stmtNgay.setString(1, blCN.getMaLuongThang());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				ResultSet rsNgay = stmtNgay.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maLuongThang = rs.getString(1);
					int thang = rs.getInt(2);
					int nam = rs.getInt(3);
					String maCongNhan = rs.getString(4);
					String maPhuCap = rs.getString(5);
					String maBHXH = rs.getString(6);
					String maKyLuat = rs.getString(7);
					List<BangLuongNgayCongNhan> dsLuongNgay = new ArrayList<BangLuongNgayCongNhan>();
					while (rsNgay.next()) {
						String maLuongNgay = rsNgay.getString(1);
						String maCCNgay = rsNgay.getString(2);
						stmtCTCC.setString(1, maCCNgay);
						List<ChiTietChamCongCongNhan> listCTCC = new ArrayList<ChiTietChamCongCongNhan>();
						ResultSet rsCTCC = stmtCTCC.executeQuery();
						while(rsCTCC.next()) {
							String maChiTiet = rsCTCC.getString(1);
							String maCD = rsCTCC.getString(2);
							int caLamViec = rsCTCC.getInt(3);
							int soLuongSP = rsCTCC.getInt(4);
							stmtCD.setString(1, maCD);
							ResultSet rsCD = stmtCD.executeQuery();
							CongDoanSanPham cd = new CongDoanSanPham();
							while (rsCD.next()) {
								double donGia = rsCD.getDouble(4);
								cd = new CongDoanSanPham(maCD, "", new SanPham(), donGia);
							}
							listCTCC.add(new ChiTietChamCongCongNhan(maChiTiet, cd, caLamViec, soLuongSP, new BangChamCongNgayCongNhan(maCCNgay)));
						}
						BangLuongNgayCongNhan bangLuongNgay = new BangLuongNgayCongNhan(maLuongNgay, new BangChamCongNgayCongNhan(maCCNgay), new BangLuongThangCongNhan(maLuongThang));
						bangLuongNgay.tinhLuongNgay(listCTCC);
						dsLuongNgay.add(bangLuongNgay);
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
					BangLuongThangCongNhan bl = new BangLuongThangCongNhan(
							maLuongThang, thang, nam, 
							BHXH, kyLuat, phuCap,
							new CongNhan(maCongNhan));
					bl.setLuongThangVaThucLinh(dsLuongNgay);
					dsTK.add(bl);		
				}
			}		
			if(blCN.getThang() != 0 && blCN.getNam() != 0) {
				String sql = "Select * from BangLuongThangCongNhan where thang = ? and nam = ?";
				String sqlNgay = "Select * from BangLuongNgayCongNhan where maLuongThang = ?";
				String sqlCTCC = "Select * from ChiTietChamCongCongNhan where maChamCong = ?";
				String sqlBHXH = "Select * from TienBHXHCN where maTienBHXH = ?";
				String sqlPC = "Select * from PhuCapCN where maPhuCap = ?";
				String sqlKL = "Select * from TienKyLuatCN where maTienKyLuat = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmtNgay = con.prepareStatement(sqlNgay);
				stmtCTCC = con.prepareStatement(sqlCTCC);
				stmtBHXH = con.prepareStatement(sqlBHXH);
				stmtPC = con.prepareStatement(sqlPC);
				stmtKL = con.prepareStatement(sqlKL);
				stmt.setInt(1, blCN.getThang());
				stmt.setInt(2, blCN.getNam());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
			
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maLuongThang = rs.getString(1);
					int thang = rs.getInt(2);
					int nam = rs.getInt(3);
					String maCongNhan = rs.getString(4);
					String maPhuCap = rs.getString(5);
					String maBHXH = rs.getString(6);
					String maKyLuat = rs.getString(7);
					stmtNgay.setString(1, maLuongThang); 
					ResultSet rsNgay = stmtNgay.executeQuery();
					List<BangLuongNgayCongNhan> dsLuongNgay = new ArrayList<BangLuongNgayCongNhan>();
					while (rsNgay.next()) {
						String maLuongNgay = rsNgay.getString(1);
						String maCCNgay = rsNgay.getString(2);
						stmtCTCC.setString(1, maCCNgay);
						List<ChiTietChamCongCongNhan> listCTCC = new ArrayList<ChiTietChamCongCongNhan>();
						ResultSet rsCTCC = stmtCTCC.executeQuery();
						while(rsCTCC.next()) {
							String maChiTiet = rsCTCC.getString(1);
							String maCD = rsCTCC.getString(2);
							int caLamViec = rsCTCC.getInt(3);
							int soLuongSP = rsCTCC.getInt(4);
							listCTCC.add(new ChiTietChamCongCongNhan(maChiTiet, new CongDoanSanPham(maCD), caLamViec, soLuongSP, new BangChamCongNgayCongNhan(maCCNgay)));
						}
						BangLuongNgayCongNhan bangLuongNgay = new BangLuongNgayCongNhan(maLuongNgay, new BangChamCongNgayCongNhan(maCCNgay), new BangLuongThangCongNhan(maLuongThang));
						bangLuongNgay.tinhLuongNgay(listCTCC);
						dsLuongNgay.add(bangLuongNgay);
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
					BangLuongThangCongNhan bl = new BangLuongThangCongNhan(
							maLuongThang, thang, nam, 
							BHXH, kyLuat, phuCap,
							new CongNhan(maCongNhan));
					bl.setLuongThangVaThucLinh(dsLuongNgay);
					dsTK.add(bl);	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
}
