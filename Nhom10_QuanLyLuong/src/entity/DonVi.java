package entity;

/*
 * Author:Trần Thành Nam
 * Date:13/11/2021
 */

import java.util.Objects;

public class DonVi {
	private String maDonVi;
	private String tenDonVi;
	public DonVi(String maDonVi, String tenDonVi) {
		this.maDonVi = maDonVi;
		this.tenDonVi = tenDonVi;
	}
	public DonVi() {
		this("", "");
	}
	public DonVi(String maDonVi) {
		this(maDonVi, "");
	}
	public String getMaDonVi() {
		return maDonVi;
	}
	public void setMaDonVi(String maDonVi) {
		this.maDonVi = maDonVi;
	}
	public String getTenDonVi() {
		return tenDonVi;
	}
	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDonVi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonVi other = (DonVi) obj;
		return Objects.equals(maDonVi, other.maDonVi);
	}
	
}
