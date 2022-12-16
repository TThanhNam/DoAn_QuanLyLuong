package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

import java.util.Date;

public class BangChamCongNgayNhanVienHanhChanh extends BangChamCongNgay{
	private NhanVienHanhChanh nhanVienHanhChanh;
	private boolean coDilam;
	private boolean coLamThem;
	private BangChamCongThangNhanVienHanhChanh bangChamCongThangNhanVienHanhChanh;
	public BangChamCongNgayNhanVienHanhChanh(String maChamCongNgay, Date ngayChamCong,
			NhanVienHanhChanh nhanVienHanhChanh, boolean coDilam, boolean coLamThem,
			BangChamCongThangNhanVienHanhChanh bangChamCongThangNhanVienHanhChanh) {
		super(maChamCongNgay, ngayChamCong);
		this.nhanVienHanhChanh = nhanVienHanhChanh;
		this.coDilam = coDilam;
		this.coLamThem = coLamThem;
		this.bangChamCongThangNhanVienHanhChanh = bangChamCongThangNhanVienHanhChanh;
	}
	public BangChamCongNgayNhanVienHanhChanh() {
		this("", null, new NhanVienHanhChanh(), false, false, new BangChamCongThangNhanVienHanhChanh());
	}
	public BangChamCongNgayNhanVienHanhChanh(String maChamCongNgay) {
		this(maChamCongNgay, null, new NhanVienHanhChanh(), false, false, new BangChamCongThangNhanVienHanhChanh());
	}
	public NhanVienHanhChanh getNhanVienHanhChanh() {
		return nhanVienHanhChanh;
	}
	public void setNhanVienHanhChanh(NhanVienHanhChanh nhanVienHanhChanh) {
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}
	public boolean isCoDilam() {
		return coDilam;
	}
	public void setCoDilam(boolean coDilam) {
		this.coDilam = coDilam;
	}
	public boolean isCoLamThem() {
		return coLamThem;
	}
	public void setCoLamThem(boolean coLamThem) {
		this.coLamThem = coLamThem;
	}
	public BangChamCongThangNhanVienHanhChanh getBangChamCongThangNhanVienHanhChanh() {
		return bangChamCongThangNhanVienHanhChanh;
	}
	public void setBangChamCongThangNhanVienHanhChanh(
			BangChamCongThangNhanVienHanhChanh bangChamCongThangNhanVienHanhChanh) {
		this.bangChamCongThangNhanVienHanhChanh = bangChamCongThangNhanVienHanhChanh;
	}
	
}
