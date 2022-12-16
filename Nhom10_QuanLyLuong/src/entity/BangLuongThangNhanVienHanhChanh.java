package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

public class BangLuongThangNhanVienHanhChanh extends BangLuongThang{
	private BangChamCongThangNhanVienHanhChanh bangChamCongThangNhanVienHanhChanh;

	public BangLuongThangNhanVienHanhChanh(String maLuongThang, int thang, int nam,
			TienBHXH tienBHXH, TienKyLuat tienKyLuat, PhuCap tienPhuCap,
			BangChamCongThangNhanVienHanhChanh bangChamCongThangNhanVienHanhChanh) {
		super(maLuongThang, thang, nam, tienBHXH, tienKyLuat, tienPhuCap);
		this.bangChamCongThangNhanVienHanhChanh = bangChamCongThangNhanVienHanhChanh;
	}

	public BangLuongThangNhanVienHanhChanh(String maLuongThang) {
		this(maLuongThang, 1, 0, new TienBHXH(), new TienKyLuat(), new PhuCap(), new BangChamCongThangNhanVienHanhChanh());
	}
	
	public BangLuongThangNhanVienHanhChanh() {
		this("", 1, 0, new TienBHXH(), new TienKyLuat(), new PhuCap(), new BangChamCongThangNhanVienHanhChanh());
	}

	public BangChamCongThangNhanVienHanhChanh getBangChamCongThangNhanVienHanhChanh() {
		return bangChamCongThangNhanVienHanhChanh;
	}

	public void setBangChamCongThangNhanVienHanhChanh(
			BangChamCongThangNhanVienHanhChanh bangChamCongThangNhanVienHanhChanh) {
		this.bangChamCongThangNhanVienHanhChanh = bangChamCongThangNhanVienHanhChanh;
	}
	public void setLuongThangVaThucLinh() {
		luongThang = bangChamCongThangNhanVienHanhChanh.getSoNgayDiLam()*bangChamCongThangNhanVienHanhChanh.getNhanVienHanhChanh().getLuongNgayCoBan()
				+ bangChamCongThangNhanVienHanhChanh.getSoBuoiLamThem() * bangChamCongThangNhanVienHanhChanh.getNhanVienHanhChanh().getLuongNgayCoBan() * (150.0/100.0);
		thucLinh = luongThang - tienBHXH.getTienBHXH() - tienKyLuat.getTienKyLuat() + tienPhuCap.getTienPhuCap();
	}
}
