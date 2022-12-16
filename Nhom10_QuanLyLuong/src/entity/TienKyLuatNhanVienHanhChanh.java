package entity;

/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:12/11/2021
 */

public class TienKyLuatNhanVienHanhChanh extends TienKyLuat{
	private NhanVienHanhChanh nhanVienHanhChanh;

	public TienKyLuatNhanVienHanhChanh(String maKyLuat, double tienKyLuat, int thang, int nam,
			NhanVienHanhChanh nhanVienHanhChanh) {
		super(maKyLuat, tienKyLuat, thang, nam);
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}

	public TienKyLuatNhanVienHanhChanh(String maKyLuat) {
		this(maKyLuat, 0, 0, 0, new NhanVienHanhChanh());
	}

	public TienKyLuatNhanVienHanhChanh() {
		this("", 0, 0, 0, new NhanVienHanhChanh());	
	}

	public NhanVienHanhChanh getNhanVienHanhChanh() {
		return nhanVienHanhChanh;
	}

	public void setNhanVienHanhChanh(NhanVienHanhChanh nhanVienHanhChanh) {
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}
	
}
