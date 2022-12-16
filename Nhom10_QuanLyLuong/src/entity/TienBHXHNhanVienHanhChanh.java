package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

public class TienBHXHNhanVienHanhChanh extends TienBHXH{
	private NhanVienHanhChanh nhanVienHanhChanh;

	public TienBHXHNhanVienHanhChanh(String maTienBHXH, double tienBHXH, int thang, int nam,
			NhanVienHanhChanh nhanVienHanhChanh) {
		super(maTienBHXH, tienBHXH, thang, nam);
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}

	public TienBHXHNhanVienHanhChanh(String maTienBHXH) {
		this(maTienBHXH, 0, 0, 0, new NhanVienHanhChanh());
	}

	public TienBHXHNhanVienHanhChanh() {
		this("", 0, 0, 0, new NhanVienHanhChanh());
	}

	public NhanVienHanhChanh getNhanVienHanhChanh() {
		return nhanVienHanhChanh;
	}

	public void setNhanVienHanhChanh(NhanVienHanhChanh nhanVienHanhChanh) {
		this.nhanVienHanhChanh = nhanVienHanhChanh;
	}
	
}
