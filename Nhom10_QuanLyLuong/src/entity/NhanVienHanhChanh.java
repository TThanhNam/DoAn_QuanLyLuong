package entity;

/*
 * Author:Trần Thành Nam
 * Date:13/11/2021
 */

import java.util.Date;

public class NhanVienHanhChanh extends NhanVien{
	private String trinhDo;
	private String bangCap;
	private double luongNgayCoBan;
	public NhanVienHanhChanh(String maNhanVien, String tenNhanVien, String soDienThoai, Date ngayCT, DonVi donVi,
			String soTaiKhoan, String tenNhanHang, String trinhDo, String bangCap, double luongNgayCoBan) {
		super(maNhanVien, tenNhanVien, soDienThoai, ngayCT, donVi, soTaiKhoan, tenNhanHang);
		this.trinhDo = trinhDo;
		this.bangCap = bangCap;
		this.luongNgayCoBan = luongNgayCoBan;
	}
	public NhanVienHanhChanh() {
		this("", "", "", null, new DonVi(), "", "", "", "", 0);
	}
	public NhanVienHanhChanh(String maNhanVien) {
		this(maNhanVien, "", "", null, new DonVi(), "", "", "", "", 0);
	}
	public String getTrinhDo() {
		return trinhDo;
	}
	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}
	public String getBangCap() {
		return bangCap;
	}
	public void setBangCap(String bangCap) {
		this.bangCap = bangCap;
	}
	public double getLuongNgayCoBan() {
		return luongNgayCoBan;
	}
	public void setLuongNgayCoBan(double luongNgayCoBan) {
		if (luongNgayCoBan < 0)
			this.luongNgayCoBan = 0;
		else
			this.luongNgayCoBan = luongNgayCoBan;
	}
	
	
}
