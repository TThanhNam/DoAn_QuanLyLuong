package entity;

/*
 * Author:Hoàng Huy Vũ
 * Date:13/11/2021
 */

public class PhuCapNhanVienHanhChanh extends PhuCap{
	private NhanVienHanhChanh nhanVienHanhChanh;

	public PhuCapNhanVienHanhChanh(String maPhuCap, double tienPhuCap, int thang, int nam,
			NhanVienHanhChanh nhanVienHanhChanh) {
		super(maPhuCap, tienPhuCap, thang, nam);
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}

	public PhuCapNhanVienHanhChanh(String maPhuCap) {
		this(maPhuCap, 0, 0, 0, new NhanVienHanhChanh());
	}

	public PhuCapNhanVienHanhChanh() {
		this("", 0, 0, 0, new NhanVienHanhChanh());
	}

	public NhanVienHanhChanh getNhanVienHanhChanh() {
		return nhanVienHanhChanh;
	}

	public void setNhanVienHanhChanh(NhanVienHanhChanh nhanVienHanhChanh) {
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}
	
}
