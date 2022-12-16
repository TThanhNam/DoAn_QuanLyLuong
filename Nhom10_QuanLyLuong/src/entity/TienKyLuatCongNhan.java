package entity;

/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:12/11/2021
 */

public class TienKyLuatCongNhan extends TienKyLuat{
	private CongNhan congNhan;

	public TienKyLuatCongNhan(String maKyLuat, double tienKyLuat, int thang, int nam, CongNhan congNhan) {
		super(maKyLuat, tienKyLuat, thang, nam);
		this.congNhan = congNhan;
	}

	public TienKyLuatCongNhan(String maKyLuat) {
		this(maKyLuat, 0, 0, 0, new CongNhan());
	}

	public TienKyLuatCongNhan() {
		this("", 0, 0, 0, new CongNhan());
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	
}
