package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

import java.util.Date;
import java.util.Objects;

public class BangChamCongNgay {
	private String maChamCongNgay;
	private Date ngayChamCong;
	public BangChamCongNgay(String maChamCongNgay, Date ngayChamCong) {
		this.maChamCongNgay = maChamCongNgay;
		this.ngayChamCong = ngayChamCong;
	}
	public BangChamCongNgay() {
		this("", null);
	}
	public BangChamCongNgay(String maChamCongNgay) {
		this(maChamCongNgay, null);
	}
	public String getMaChamCongNgay() {
		return maChamCongNgay;
	}
	public void setMaChamCongNgay(String maChamCongNgay) {
		this.maChamCongNgay = maChamCongNgay;
	}
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	public void setNgayChamCong(Date ngayChamCong) {
		this.ngayChamCong = ngayChamCong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maChamCongNgay);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangChamCongNgay other = (BangChamCongNgay) obj;
		return Objects.equals(maChamCongNgay, other.maChamCongNgay);
	}
	
	
}	
