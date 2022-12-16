package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

public class TienBHXHCongNhan extends TienBHXH{
	private CongNhan congNhan;

	public TienBHXHCongNhan(String maTienBHXH, double tienBHXH, int thang, int nam, CongNhan congNhan) {
		super(maTienBHXH, tienBHXH, thang, nam);
		this.congNhan = congNhan;
	}

	public TienBHXHCongNhan(String maTienBHXH) {
		this(maTienBHXH, 0, 0, 0, new CongNhan());
	}

	public TienBHXHCongNhan() {
		this("", 0, 0, 0, new CongNhan());
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	
}
