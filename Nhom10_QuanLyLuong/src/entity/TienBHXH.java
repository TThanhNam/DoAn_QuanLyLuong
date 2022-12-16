package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

import java.util.Objects;

public class TienBHXH {
	private String maTienBHXH;
	private double tienBHXH;
	private int thang;
	private int nam;
	public TienBHXH(String maTienBHXH, double tienBHXH, int thang, int nam) {
		this.maTienBHXH = maTienBHXH;
		this.tienBHXH = tienBHXH;
		this.thang = thang;
		this.nam = nam;
	}
	public TienBHXH(String maTienBHXH) {
		this(maTienBHXH, 0, 0, 0);
	}
	public TienBHXH() {
		this("", 0, 0, 0);
	}
	public String getMaTienBHXH() {
		return maTienBHXH;
	}
	public void setMaTienBHXH(String maTienBHXH) {
		this.maTienBHXH = maTienBHXH;
	}
	public double getTienBHXH() {
		return tienBHXH;
	}
	public void setTienBHXH(double tienBHXH) {
		if (tienBHXH < 0)
			this.tienBHXH = 0;
		else
			this.tienBHXH = tienBHXH;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		if (thang < 1 || thang > 12)
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
		return Objects.hash(maTienBHXH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TienBHXH other = (TienBHXH) obj;
		return Objects.equals(maTienBHXH, other.maTienBHXH);
	}
	
}	
