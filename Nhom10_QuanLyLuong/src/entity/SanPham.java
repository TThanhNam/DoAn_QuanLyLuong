package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:12/11/2021
 */

import java.util.Objects;

public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	private double donGiaSP;
	public SanPham(String maSanPham, String tenSanPham, double donGiaSP) {
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.donGiaSP = donGiaSP;
	}
	public SanPham() {
		this("", "", 0);
	}
	public SanPham(String maSanPham) {
		this(maSanPham, "", 0);
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public double getDonGiaSP() {
		return donGiaSP;
	}
	public void setDonGiaSP(double donGiaSP) {
		if (donGiaSP < 0)
			this.donGiaSP = 0;
		else
			this.donGiaSP = donGiaSP;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}
	
	
	
}
