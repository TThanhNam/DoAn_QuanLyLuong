package entity;

/*
 * Author:Hoàng Huy Vũ
 * Date:13/11/2021
 */

import java.util.Objects;

public class PhuCap {
	private String maPhuCap;
	private double tienPhuCap;
	private int thang;
	private int nam;
	public PhuCap(String maPhuCap, double tienPhuCap, int thang, int nam) {
		this.maPhuCap = maPhuCap;
		this.tienPhuCap = tienPhuCap;
		this.thang = thang;
		this.nam = nam;
	}
	public PhuCap() {
		this("", 0, 0, 0);
	}
	public PhuCap(String maPhuCap) {
		this(maPhuCap, 0, 0, 0);
	}
	public String getMaPhuCap() {
		return maPhuCap;
	}
	public void setMaPhuCap(String maPhuCap) {
		this.maPhuCap = maPhuCap;
	}
	public double getTienPhuCap() {
		return tienPhuCap;
	}
	public void setTienPhuCap(double tienPhuCap) {
		if (tienPhuCap < 0)
			this.tienPhuCap = 0;
		else
			this.tienPhuCap = tienPhuCap;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		if (thang < 1 && thang > 12)
			this.thang = 0;
		else
			this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		if (nam < 0)
			this.nam = 0;
		else
			this.nam = nam;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhuCap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhuCap other = (PhuCap) obj;
		return Objects.equals(maPhuCap, other.maPhuCap);
	}
	
	
}	
