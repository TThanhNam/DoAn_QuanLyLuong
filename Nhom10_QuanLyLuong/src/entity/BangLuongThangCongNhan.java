package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:08/12/2021
 */

import java.util.List;

public class BangLuongThangCongNhan extends BangLuongThang{
	private CongNhan congNhan;

	public BangLuongThangCongNhan(String maLuongThang, int thang, int nam, TienBHXH tienBHXH,
			TienKyLuat tienKyLuat, PhuCap tienPhuCap, CongNhan congNhan) {
		super(maLuongThang, thang, nam, tienBHXH, tienKyLuat, tienPhuCap);
		this.congNhan = congNhan;
	}

	public BangLuongThangCongNhan(String maLuongThang) {
		this(maLuongThang, 1, 0, new TienBHXH(), new TienKyLuat(), new PhuCap(), new CongNhan());
	}

	public BangLuongThangCongNhan() {
		this("", 1, 0, new TienBHXH(), new TienKyLuat(), new PhuCap(), new CongNhan());
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	
	public void setLuongThangVaThucLinh(List<BangLuongNgayCongNhan> dsLuongNgay) {
		for (BangLuongNgayCongNhan bangLuongNgay : dsLuongNgay) {
			luongThang += bangLuongNgay.getLuongNgay();
		}
		thucLinh = luongThang - tienBHXH.getTienBHXH() - tienKyLuat.getTienKyLuat() + tienPhuCap.getTienPhuCap();
	}
}
