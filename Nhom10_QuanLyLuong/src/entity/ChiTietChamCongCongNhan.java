package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

import java.util.Objects;

public class ChiTietChamCongCongNhan {
	private String maChiTietCC;
	private CongDoanSanPham congDoan;
	private int caLamViec;
	private int soLuongSP;
	private BangChamCongNgayCongNhan bangChamCongCN;
	public ChiTietChamCongCongNhan(String maChiTietCC, CongDoanSanPham congDoan, int caLamViec, int soLuongSP,
			BangChamCongNgayCongNhan bangChamCongCN) {
		this.maChiTietCC = maChiTietCC;
		this.congDoan = congDoan;
		this.caLamViec = caLamViec;
		this.soLuongSP = soLuongSP;
		this.bangChamCongCN = bangChamCongCN;
	}
	public ChiTietChamCongCongNhan() {
		this("", new CongDoanSanPham(), 0, 0, new BangChamCongNgayCongNhan());
	}
	public ChiTietChamCongCongNhan(String maChiTietCC) {
		this(maChiTietCC, new CongDoanSanPham(), 0, 0, new BangChamCongNgayCongNhan());
	}
	public String getMaChiTietCC() {
		return maChiTietCC;
	}
	public void setMaChiTietCC(String maChiTietCC) {
		this.maChiTietCC = maChiTietCC;
	}
	public CongDoanSanPham getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoanSanPham congDoan) {
		this.congDoan = congDoan;
	}
	public int getCaLamViec() {
		return caLamViec;
	}
	public void setCaLamViec(int caLamViec) {
		if (caLamViec < 1)
			this.caLamViec = 1;
		else
			this.caLamViec = caLamViec;
	}
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		if (soLuongSP < 0)
			this.soLuongSP = 0;
		else
			this.soLuongSP = soLuongSP;
	}
	public BangChamCongNgayCongNhan getBangChamCongCN() {
		return bangChamCongCN;
	}
	public void setBangChamCongCN(BangChamCongNgayCongNhan bangChamCongCN) {
		this.bangChamCongCN = bangChamCongCN;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maChiTietCC);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietChamCongCongNhan other = (ChiTietChamCongCongNhan) obj;
		return Objects.equals(maChiTietCC, other.maChiTietCC);
	}
	
	
	
}
