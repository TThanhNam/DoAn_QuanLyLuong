package entity;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:10/11/2021
 */

import java.util.Date;

public class BangChamCongNgayCongNhan extends BangChamCongNgay{
	private CongNhan congNhan;

	public BangChamCongNgayCongNhan(String maChamCongNgay, Date ngayChamCong, CongNhan congNhan) {
		super(maChamCongNgay, ngayChamCong);
		this.congNhan = congNhan;
	}

	public BangChamCongNgayCongNhan() {
		this("", null, new CongNhan());
	}

	public BangChamCongNgayCongNhan(String maChamCongNgay) {
		this(maChamCongNgay, null, new CongNhan());
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	
	
	
}
