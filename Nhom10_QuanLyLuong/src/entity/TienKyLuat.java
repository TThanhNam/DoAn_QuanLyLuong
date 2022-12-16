package entity;

/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:12/11/2021
 */

import java.util.Objects;

public class TienKyLuat {
	private String maKyLuat;
	private double tienKyLuat;
	private int thang;
	private int nam;
	public TienKyLuat(String maKyLuat, double tienKyLuat, int thang, int nam) {
		super();
		this.maKyLuat = maKyLuat;
		this.tienKyLuat = tienKyLuat;
		this.thang = thang;
		this.nam = nam;
	}
	public TienKyLuat(String maKyLuat) {
		this(maKyLuat, 0, 0, 0);
	}
	public TienKyLuat() {
		this("", 0, 0, 0);
	}
	public String getMaKyLuat() {
		return maKyLuat;
	}
	public void setMaKyLuat(String maKyLuat) {
		this.maKyLuat = maKyLuat;
	}
	public double getTienKyLuat() {
		return tienKyLuat;
	}
	public void setTienKyLuat(double tienKyLuat) {
		if (tienKyLuat < 0)
			this.tienKyLuat = 0;
		else
			this.tienKyLuat = tienKyLuat;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		if (thang < 1 || nam > 12)
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
		return Objects.hash(maKyLuat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TienKyLuat other = (TienKyLuat) obj;
		return Objects.equals(maKyLuat, other.maKyLuat);
	}
	
	
}	
