package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:13/11/2021
 */

import java.util.Objects;

public class CongDoanSanPham {
	private String maCongDoan;
	private String tenCongDoan;
	private SanPham sanPham;
	private double donGiaCD;
	public CongDoanSanPham(String maCongDoan, String tenCongDoan, SanPham sanPham, double donGiaCD) {
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.sanPham = sanPham;
		this.donGiaCD = donGiaCD;
	}
	public CongDoanSanPham() {
		this("", "", new SanPham(), 0);
	}
	public CongDoanSanPham(String maCongDoan) {
		this(maCongDoan, "", new SanPham(), 0);
	}
	public String getMaCongDoan() {
		return maCongDoan;
	}
	public void setMaCongDoan(String maCongDoan) {
		this.maCongDoan = maCongDoan;
	}
	public String getTenCongDoan() {
		return tenCongDoan;
	}
	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public double getDonGiaCD() {
		return donGiaCD;
	}
	public void setDonGiaCD(double donGiaCD) {
		if (donGiaCD > 0)
			this.donGiaCD = 0;
		else
			this.donGiaCD = donGiaCD;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCongDoan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoanSanPham other = (CongDoanSanPham) obj;
		return Objects.equals(maCongDoan, other.maCongDoan);
	}
	
	
}
