package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

import java.util.List;
import java.util.Objects;

public class BangChamCongThangNhanVienHanhChanh {
	private String maChamCongThang;
	private NhanVienHanhChanh nhanVienHanhChanh;
	private int soNgayDiLam;
	private int soBuoiLamThem;
	private int thang;
	private int nam;
	public BangChamCongThangNhanVienHanhChanh(String maChamCongThang, NhanVienHanhChanh nhanVienHanhChanh,
			 int thang, int nam) {
		this.maChamCongThang = maChamCongThang;
		this.nhanVienHanhChanh = nhanVienHanhChanh;
		this.soNgayDiLam = 0;
		this.soBuoiLamThem = 0;
		this.thang = thang;
		this.nam = nam;
	}
	public BangChamCongThangNhanVienHanhChanh() {
		this("", new NhanVienHanhChanh(), 0, 0);
	}
	public BangChamCongThangNhanVienHanhChanh(String maChamCongThang) {
		this(maChamCongThang, new NhanVienHanhChanh(), 0, 0);
	}
	public String getMaChamCongThang() {
		return maChamCongThang;
	}
	public void setMaChamCongThang(String maChamCongThang) {
		this.maChamCongThang = maChamCongThang;
	}
	public NhanVienHanhChanh getNhanVienHanhChanh() {
		return nhanVienHanhChanh;
	}
	public void setNhanVienHanhChanh(NhanVienHanhChanh nhanVienHanhChanh) {
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}
	public int getSoNgayDiLam() {
		return soNgayDiLam;
	}
	public void setSoNgayDiLamVaSoBuoiLamThem(List<BangChamCongNgayNhanVienHanhChanh> dsBangChamCongNgay) {
		for (BangChamCongNgayNhanVienHanhChanh bangChamCongNgayNVHC : dsBangChamCongNgay) {
			if (bangChamCongNgayNVHC.isCoDilam()) {
				this.soNgayDiLam += 1;
			}
			if (bangChamCongNgayNVHC.isCoLamThem()) {
				this.soBuoiLamThem += 1;
			}
		}
	}
	public int getSoBuoiLamThem() {
		return soBuoiLamThem;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		if (thang < 1 || thang > 12)
			this.thang = 1;
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
		return Objects.hash(maChamCongThang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangChamCongThangNhanVienHanhChanh other = (BangChamCongThangNhanVienHanhChanh) obj;
		return Objects.equals(maChamCongThang, other.maChamCongThang);
	}
	
}
