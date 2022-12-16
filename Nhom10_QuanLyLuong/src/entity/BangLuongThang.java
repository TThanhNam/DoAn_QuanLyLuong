package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

import java.util.Objects;

public class BangLuongThang {
	protected String maLuongThang;
	protected int thang;
	protected int nam;
	protected TienBHXH tienBHXH;
	protected TienKyLuat tienKyLuat;
	protected PhuCap tienPhuCap;
	protected double luongThang;
	protected double thucLinh;
	
	public BangLuongThang(String maLuongThang, int thang, int nam, TienBHXH tienBHXH,
			TienKyLuat tienKyLuat, PhuCap tienPhuCap) {
		super();
		this.maLuongThang = maLuongThang;
		this.thang = thang;
		this.nam = nam;
		this.tienBHXH = tienBHXH;
		this.tienKyLuat = tienKyLuat;
		this.tienPhuCap = tienPhuCap;
		this.luongThang = 0;
		this.thucLinh = 0;
	}
	public BangLuongThang(String maLuongThang) {
		this(maLuongThang, 0, 0, new TienBHXH(), new TienKyLuat(), new PhuCap());
	}
	public BangLuongThang() {
		this("", 0, 0, new TienBHXH(), new TienKyLuat(), new PhuCap());

	}
	public String getMaLuongThang() {
		return maLuongThang;
	}
	public void setMaLuongThang(String maLuongThang) {
		this.maLuongThang = maLuongThang;
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
	public TienBHXH getTienBHXH() {
		return tienBHXH;
	}
	public void setTienBHXH(TienBHXH tienBHXH) {
		this.tienBHXH = tienBHXH;
	}
	public TienKyLuat getTienKyLuat() {
		return tienKyLuat;
	}
	public void setTienKyLuat(TienKyLuat tienKyLuat) {
		this.tienKyLuat = tienKyLuat;
	}
	public PhuCap getTienPhuCap() {
		return tienPhuCap;
	}
	public void setTienPhuCap(PhuCap tienPhuCap) {
		this.tienPhuCap = tienPhuCap;
	}
	public double getLuongThang() {
		return luongThang;
	}
	public double getThucLinh() {
		return thucLinh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLuongThang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangLuongThang other = (BangLuongThang) obj;
		return Objects.equals(maLuongThang, other.maLuongThang);
	}
	
	
	
}
