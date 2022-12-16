package entity;

/*
 * Author:Trần Thành Nam
 * Date:13/11/2021
 */

import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private String soDienThoai;
	private Date ngayCT;
	private DonVi donVi;
	private String soTaiKhoan;
	private String tenNhanHang;
	public NhanVien(String maNhanVien, String tenNhanVien, String soDienThoai, Date ngayCT, DonVi donVi,
			String soTaiKhoan, String tenNhanHang) {
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.soDienThoai = soDienThoai;
		this.ngayCT = ngayCT;
		this.donVi = donVi;
		this.soTaiKhoan = soTaiKhoan;
		this.tenNhanHang = tenNhanHang;
	}
	public NhanVien() {
		this("", "", "", null, new DonVi(), "", "");
	}
	public NhanVien(String maNhanVien) {
		this(maNhanVien, "", "", null, new DonVi(), "", "");
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public Date getNgayCT() {
		return ngayCT;
	}
	public void setNgayCT(Date ngayCT) {
		this.ngayCT = ngayCT;
	}
	public DonVi getDonVi() {
		return donVi;
	}
	public void setDonVi(DonVi donVi) {
		this.donVi = donVi;
	}
	public String getSoTaiKhoan() {
		return soTaiKhoan;
	}
	public void setSoTaiKhoan(String soTaiKhoan) {
		this.soTaiKhoan = soTaiKhoan;
	}
	public String getTenNhanHang() {
		return tenNhanHang;
	}
	public void setTenNhanHang(String tenNhanHang) {
		this.tenNhanHang = tenNhanHang;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	
	
	
}
