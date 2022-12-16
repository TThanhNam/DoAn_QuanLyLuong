package dao;
/*
 * Author:Trần Thành Nam
 * Date:14/11/2021
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.DonVi;

public class DanhSachDonVi {
	private ArrayList<DonVi> dsDonVi;

	public DanhSachDonVi() {
		dsDonVi = new ArrayList<DonVi>();
	}
	public ArrayList<DonVi> docDataBase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from DonVi";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			dsDonVi.clear();
			while (rs.next()) {
				String maDonVi = rs.getString(1);
				String tenDoVi = rs.getString(2);
				
				DonVi dv = new DonVi(maDonVi, tenDoVi);
				dsDonVi.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return dsDonVi;
	}
	public boolean themDonVi(DonVi dv) {
		for (DonVi s : dsDonVi) {
			if (s.getMaDonVi().equals(dv.getMaDonVi()))
				return false;
		}
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into DonVi values(?, ?)");
			stmt.setString(1, dv.getMaDonVi());
			stmt.setString(2, dv.getTenDonVi());
		
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaDonVi(String maDonVi) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from DonVi where maDonVi = ?");
			stmt.setString(1, maDonVi);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capNhatDonVi(DonVi dv) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update DonVi set tenDonVi = ? where maDonVi = ?");		
			stmt.setString(1, dv.getTenDonVi());
			stmt.setString(2, dv.getMaDonVi());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<DonVi> timKiemDonVi(DonVi dv) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ArrayList<DonVi> dsDonVis = new ArrayList<DonVi>();		
		try {		
			if (!dv.getMaDonVi().equals("")) {
				String sql = "Select * from DonVi where maDonVi = ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				stmt.setString(1, dv.getMaDonVi());
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maDonVi = rs.getString(1);
					String tenDonVi = rs.getString(2);
					
					DonVi DV = new DonVi(maDonVi, tenDonVi);
					dsDonVis.add(DV);		
				}
			}		
			else if(!dv.getTenDonVi().equals("")) {
				String sql = "Select * from DonVi where tenDonVi like ?";
				// String sql = "Select * from ChiTietThanhToan
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, "%"+dv.getTenDonVi()+"%");
				// Thuc hien cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				// Duyet tren ket qua ve
			
				while (rs.next()) {// Di chuyen con tro xuong ban ghi ke tiep
					String maDonVi = rs.getString(1);
					String tenDonVi = rs.getString(2);
												
					DonVi DV = new DonVi(maDonVi, tenDonVi);
					dsDonVis.add(DV);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDonVis;
	}
	public ArrayList<DonVi> getMaDonVi(){
	
		try {
			ArrayList<DonVi> dv = new ArrayList<DonVi>();
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from DonVi";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			dsDonVi.clear();
			while (rs.next()) {
				String maDonVi = rs.getString(1);
				
				DonVi DV = new DonVi(maDonVi);
				dv.add(DV);
			}			
			return dv;			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public int getTongSoLuongDonVi() {
		int tongDonVi = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select count(maDonVi) from DonVi";
			Statement statement = con.createStatement();
			// Thuc thi cau lenh tra ve
			ResultSet rs = statement.executeQuery(sql);
			// Duyet tren ket qua tra ve
			while (rs.next()) {
				tongDonVi = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Dong ket noi
		}
		return tongDonVi;
	}
	
}
