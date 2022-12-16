package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

import java.util.List;
import java.util.Objects;

public class BangLuongNgayCongNhan {
	private String maLuongNgay;
	private BangChamCongNgayCongNhan bangChamCongNgayCongNhan;
	private BangLuongThangCongNhan bangLuongThangCongNhan;
	private double luongNgay;
	public BangLuongNgayCongNhan(String maLuongNgay, BangChamCongNgayCongNhan bangChamCongNgayCongNhan,
			BangLuongThangCongNhan bangLuongThangCongNhan) {
		this.maLuongNgay = maLuongNgay;
		this.bangChamCongNgayCongNhan = bangChamCongNgayCongNhan;
		this.bangLuongThangCongNhan = bangLuongThangCongNhan;
		this.luongNgay = 0;
	}
	public BangLuongNgayCongNhan() {
		this("", new BangChamCongNgayCongNhan(), new BangLuongThangCongNhan());
	}
	public BangLuongNgayCongNhan(String maLuongNgay) {
		this(maLuongNgay, new BangChamCongNgayCongNhan(), new BangLuongThangCongNhan());
	}
	public String getMaLuongNgay() {
		return maLuongNgay;
	}
	public void setMaLuongNgay(String maLuongNgay) {
		this.maLuongNgay = maLuongNgay;
	}
	public BangChamCongNgayCongNhan getBangChamCongNgayCongNhan() {
		return bangChamCongNgayCongNhan;
	}
	public void setBangChamCongNgayCongNhan(BangChamCongNgayCongNhan bangChamCongNgayCongNhan) {
		this.bangChamCongNgayCongNhan = bangChamCongNgayCongNhan;
	}
	public BangLuongThangCongNhan getBangLuongThangCongNhan() {
		return bangLuongThangCongNhan;
	}
	public void setBangLuongThangCongNhan(BangLuongThangCongNhan bangLuongThangCongNhan) {
		this.bangLuongThangCongNhan = bangLuongThangCongNhan;
	}
	public double getLuongNgay() {
		return luongNgay;
	}
	public void tinhLuongNgay(List<ChiTietChamCongCongNhan> dsCTCC) {
		for (ChiTietChamCongCongNhan ct : dsCTCC) {
			if (ct.getCaLamViec() == 3) {
				luongNgay += ct.getSoLuongSP() *  ct.getCongDoan().getDonGiaCD() * (150.0/100.0);
			} else {
				luongNgay += ct.getSoLuongSP() *  ct.getCongDoan().getDonGiaCD();
			}
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLuongNgay);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangLuongNgayCongNhan other = (BangLuongNgayCongNhan) obj;
		return Objects.equals(maLuongNgay, other.maLuongNgay);
	}
	
	
}
