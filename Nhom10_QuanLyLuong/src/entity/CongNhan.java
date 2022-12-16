package entity;

/*
 * Author:Trần Thành Nam
 * Date:13/11/2021
 */

import java.util.Date;

public class CongNhan extends NhanVien{
	private int soNamKN;

	public CongNhan(String maNhanVien, String tenNhanVien, String soDienThoai, Date ngayCT, DonVi donVi,
			String soTaiKhoan, String tenNhanHang, int soNamKN) {
		super(maNhanVien, tenNhanVien, soDienThoai, ngayCT, donVi, soTaiKhoan, tenNhanHang);
		this.soNamKN = soNamKN;
	}

	public CongNhan() {
		this("", "", "", null, new DonVi(), "", "", 0);
	}

	public CongNhan(String maNhanVien) {
		this(maNhanVien, "", "", null, new DonVi(), "", "", 0);
	}

	public int getSoNamKN() {
		return soNamKN;
	}

	public void setSoNamKN(int soNamKN) {
		if (soNamKN < 0)
			this.soNamKN = 0;
		else
			this.soNamKN = soNamKN;
	}
	
}
