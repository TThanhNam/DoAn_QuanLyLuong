package entity;

/*
 * Author:Hoàng Huy Vũ
 * Date:13/11/2021
 */

public class PhuCapCongNhan extends PhuCap{
	private CongNhan congNhan;

	public PhuCapCongNhan(String maPhuCap, double tienPhuCap, int thang, int nam, CongNhan congNhan) {
		super(maPhuCap, tienPhuCap, thang, nam);
		this.congNhan = congNhan;
	}

	public PhuCapCongNhan(String maPhuCap) {
		this(maPhuCap, 0, 0, 0, new CongNhan());
	}

	public PhuCapCongNhan() {
		this("", 0, 0, 0, new CongNhan());
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	
	
}
