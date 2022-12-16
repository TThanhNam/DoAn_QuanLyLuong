package form;
/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:09/12/2021
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.DanhSachBHXHCongNhan;
import dao.DanhSachBHXHNhanVienHanhChanh;
import dao.DanhSachBangChamCongNgayCongNhan;
import dao.DanhSachBangChamCongThangNhanVienHanhChanh;
import dao.DanhSachChiTietChamCongCongNhan;
import dao.DanhSachCongDoanSanPham;
import dao.DanhSachCongNhan;
import dao.DanhSachDonVi;
import dao.DanhSachLuongNgayCongNhan;
import dao.DanhSachLuongThangCongNhan;
import dao.DanhSachLuongThangNhanVienHanhChanh;
import dao.DanhSachNhanVienHanhChanh;
import dao.DanhSachPhuCapCongNhan;
import dao.DanhSachPhuCapNhanVienHC;
import dao.DanhSachTienKLCongNhan;
import dao.DanhSachTienKLNhanVienhHanhChanh;
import entity.BangChamCongNgayCongNhan;
import entity.BangChamCongThangNhanVienHanhChanh;
import entity.BangLuongNgayCongNhan;
import entity.BangLuongThangCongNhan;
import entity.BangLuongThangNhanVienHanhChanh;
import entity.ChiTietChamCongCongNhan;
import entity.CongDoanSanPham;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVienHanhChanh;
import entity.PhuCapCongNhan;
import entity.PhuCapNhanVienHanhChanh;
import entity.SanPham;
import entity.TienBHXHCongNhan;
import entity.TienBHXHNhanVienHanhChanh;
import entity.TienKyLuatCongNhan;
import entity.TienKyLuatNhanVienHanhChanh;

public class FrmTinhLuong extends JPanel {
	private JTextField txtTimNV;
	private TableModel tableModel;
	private JTable table;
	private JComboBox<Integer> cboThang;
	private JComboBox<Integer> cboNam;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private DanhSachNhanVienHanhChanh dsNVHC = new DanhSachNhanVienHanhChanh();
	private DanhSachCongNhan dsCN = new DanhSachCongNhan();
	private DanhSachDonVi dsDV = new DanhSachDonVi(); 
	private DanhSachBHXHCongNhan dsBHXHCN = new DanhSachBHXHCongNhan();
	private DanhSachBHXHNhanVienHanhChanh dsBHXHNVHC = new DanhSachBHXHNhanVienHanhChanh();
	
	private DanhSachPhuCapCongNhan dsPCCN = new DanhSachPhuCapCongNhan();
	private DanhSachPhuCapNhanVienHC dsPCNVHC = new DanhSachPhuCapNhanVienHC();
	private DanhSachTienKLNhanVienhHanhChanh dsKLNVHC = new DanhSachTienKLNhanVienhHanhChanh();
	private DanhSachTienKLCongNhan dsKLCN = new DanhSachTienKLCongNhan();
	private DanhSachBangChamCongThangNhanVienHanhChanh dsBCCNVHCThang = new DanhSachBangChamCongThangNhanVienHanhChanh();
	private DanhSachBangChamCongNgayCongNhan dsBCCCN = new DanhSachBangChamCongNgayCongNhan();
	private DanhSachChiTietChamCongCongNhan dsCTCC = new DanhSachChiTietChamCongCongNhan();
	private DanhSachCongDoanSanPham dsCD = new DanhSachCongDoanSanPham();
	private DanhSachLuongNgayCongNhan dsLCNNgay = new DanhSachLuongNgayCongNhan();
	private DanhSachLuongThangCongNhan dsLCNThang = new DanhSachLuongThangCongNhan();
	private DanhSachLuongThangNhanVienHanhChanh dsLNVHCThang = new DanhSachLuongThangNhanVienHanhChanh();
	private JLabel lblMess;
	DecimalFormat formatterTien = new DecimalFormat("###,###,###.##");
	
	
	
	/**
	 * Create the frame.
	 */
	public FrmTinhLuong() {	
		setBorder(new MatteBorder(0, 5, 5, 5, (Color) null));
		setBackground(new Color(240, 240, 240));
		setPreferredSize(new Dimension(1140, 690));
		setLayout(null);
		
		JLabel lblTitle = new JLabel("TÍNH LƯƠNG");
		
		lblTitle.setBounds(0, 10, 1130, 33);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);
		
		JPanel pInfo = new JPanel();
		pInfo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pInfo.setBounds(15, 43, 1115, 187);
		add(pInfo);
		pInfo.setLayout(null);
		
		JLabel lblThang = new JLabel("Chọn Tháng :");
		lblThang.setPreferredSize(new Dimension(120, 20));
		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThang.setBounds(57, 60, 120, 20);
		pInfo.add(lblThang);
		
		JLabel lblNam = new JLabel("Chọn Năm :");
		lblNam.setMinimumSize(new Dimension(120, 20));
		lblNam.setMaximumSize(new Dimension(120, 20));
		lblNam.setPreferredSize(new Dimension(120, 20));
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNam.setBounds(72, 110, 120, 20);
		pInfo.add(lblNam);
		
		JPanel pTimNV = new JPanel();
		pTimNV.setBorder(new TitledBorder(null, "T\u00ECm Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimNV.setBounds(346, 25, 530, 135);
		pInfo.add(pTimNV);
		pTimNV.setLayout(null);
		
		txtTimNV = new JTextField();
		txtTimNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTimNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimNV.setBounds(176, 34, 245, 25);
		pTimNV.add(txtTimNV);
		txtTimNV.setColumns(10);
		
		JRadioButton radTheoMa = new JRadioButton("Theo Mã");
		radTheoMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTheoMa.setBounds(29, 81, 107, 21);
		pTimNV.add(radTheoMa);
		radTheoMa.setSelected(true);
		
		JRadioButton radTheoTen = new JRadioButton("Theo Tên");
		radTheoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTheoTen.setBounds(176, 81, 107, 21);
		pTimNV.add(radTheoTen);
		
		JRadioButton radTheoDonVi = new JRadioButton("Theo Tên Đơn Vị");
		radTheoDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTheoDonVi.setBounds(322, 81, 159, 21);
		pTimNV.add(radTheoDonVi);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radTheoMa);
		bg.add(radTheoDonVi);
		bg.add(radTheoTen);
		
		JLabel lblNewLabel_3 = new JLabel("Nhân Viên :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(68, 34, 112, 19);
		pTimNV.add(lblNewLabel_3);
		
		String[] thang = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		cboThang = new JComboBox(thang);
		cboThang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboThang.setBounds(173, 59, 102, 21);
		
		pInfo.add(cboThang);
		
		cboNam = new JComboBox();
		cboNam.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboNam.setBounds(173, 109, 102, 21);
		pInfo.add(cboNam);
		
		for (int i = new Date().getYear() + 1900; i > 2009; i--) {
			cboNam.addItem(i);
		}
		
		 lblMess = new JLabel("");
		lblMess.setForeground(Color.RED);
		lblMess.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMess.setBackground(Color.WHITE);
		lblMess.setBounds(26, 158, 310, 20);
		pInfo.add(lblMess);
		
		JButton btnTinhLuong = new JButton("Tính lương");
		btnTinhLuong.setBackground(Color.CYAN);
		btnTinhLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int thang = Integer.parseInt(cboThang.getSelectedItem()+"");
				int nam = Integer.parseInt(cboNam.getSelectedItem()+"");
				String timNV = txtTimNV.getText().trim();
				List<NhanVienHanhChanh> listNVHCTK = new ArrayList<NhanVienHanhChanh>();
				List<CongNhan> listCNTK = new ArrayList<CongNhan>();
				if (radTheoMa.isSelected()) {
					listNVHCTK = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(timNV));
					listCNTK = dsCN.timKiemCN(new CongNhan(timNV));
				}
				if (radTheoTen.isSelected()) {
					listNVHCTK = dsNVHC.timKiemNVHC(new NhanVienHanhChanh("", timNV, "", null, new DonVi(), "", "", "", "", 0));
					listCNTK = dsCN.timKiemCN(new CongNhan("", timNV, "", null, new DonVi(), "", "", 0));					
				}
				if (radTheoDonVi.isSelected()) {
					List<DonVi> listDVTK = dsDV.timKiemDonVi(new DonVi("", timNV));
					if (!listDVTK.isEmpty()) {
						for (DonVi donVi : listDVTK) {
							listNVHCTK = dsNVHC.timKiemNVHC(new NhanVienHanhChanh("", "", "", null, new DonVi(donVi.getMaDonVi()), "", "", "", "", 0));
							listCNTK = dsCN.timKiemCN(new CongNhan("", "", "", null, new DonVi(donVi.getMaDonVi()), "", "", 0));
						}	
						
					}
				}
				
				if (txtTimNV.getText().trim().equals("")) {
					listCNTK = dsCN.docDataBase();
					listNVHCTK = dsNVHC.docDataBase();
				}
				
				xoaHetTable();
				if (listCNTK.isEmpty() && listNVHCTK.isEmpty()) {
					lblMess.setText("Không tìm thấy nhân viên");
					String[] rowData = {"","","","","","","","", ""};
					((DefaultTableModel) tableModel).addRow(rowData);
				} else {
					lblMess.setText("");
					for (NhanVienHanhChanh nhanVienHC : listNVHCTK) {
						String maNV = nhanVienHC.getMaNhanVien();
						BangChamCongThangNhanVienHanhChanh bangChamCongThangNVHC = dsBCCNVHCThang
								.timBangChamCongTheoMaNVVaThangNam(maNV, thang, nam);
						if (bangChamCongThangNVHC != null) {
							String maBLNVHC = "MLT" + nhanVienHC.getMaNhanVien() + thang + nam;	
							TienBHXHNhanVienHanhChanh  tienBHXH = dsBHXHNVHC.timTienBHXHTheoMaNVThangNam(maNV, thang, nam);
							TienKyLuatNhanVienHanhChanh  tienKyLuat = dsKLNVHC.timTienTienKyLuatTheoMaNVThangNam(maNV, thang, nam);
							PhuCapNhanVienHanhChanh  tienPhuCap = dsPCNVHC.timPhuCapTheoMaNVThangNam(maNV, thang, nam);
							List<BangLuongThangNhanVienHanhChanh> listLNVHCTK = dsLNVHCThang
									.timKiemBangLuong(new BangLuongThangNhanVienHanhChanh(maBLNVHC));
							if (listLNVHCTK.isEmpty()) {
								if (tienPhuCap.getMaPhuCap().equals("")) {
									tienPhuCap = new PhuCapNhanVienHanhChanh(getMaPhuCapTuDong(), 0, thang, nam, nhanVienHC);
									dsPCNVHC.themPhuCapNhanVienHC(tienPhuCap);		
								}
								if (tienBHXH.getMaTienBHXH().equals("")) {
									tienBHXH = new TienBHXHNhanVienHanhChanh(getMaBHXHTuDong(), 0, thang, nam, nhanVienHC); 
									dsBHXHNVHC.themTienBHXHNVHC(tienBHXH);		
								}
								if (tienKyLuat.getMaKyLuat().equals("")) {
									tienKyLuat = new TienKyLuatNhanVienHanhChanh(getMaKLTuDong(), 0, thang, nam, nhanVienHC);
									dsKLNVHC.themTienKLNVHC(tienKyLuat);
								}
								List<NhanVienHanhChanh> listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(maNV));
								bangChamCongThangNVHC.setNhanVienHanhChanh(listNVHC.get(0));
								BangLuongThangNhanVienHanhChanh bangLuongThangNVHC = new BangLuongThangNhanVienHanhChanh(maBLNVHC, 
										thang, nam, tienBHXH, tienKyLuat, tienPhuCap, bangChamCongThangNVHC);
								bangLuongThangNVHC.setLuongThangVaThucLinh();
								if (dsLNVHCThang.themBangLuong(bangLuongThangNVHC)) {
									String[] rowData = {maBLNVHC,maNV,
											nhanVienHC.getTenNhanVien(),thang+"",nam+"",formatterTien.format(bangLuongThangNVHC.getLuongThang()),
											formatterTien.format(tienBHXH.getTienBHXH()),
											formatterTien.format(tienKyLuat.getTienKyLuat()),formatterTien.format(tienPhuCap.getTienPhuCap())
											, formatterTien.format(bangLuongThangNVHC.getThucLinh())};
									((DefaultTableModel) tableModel).addRow(rowData);
								}
							} else {
								BangLuongThangNhanVienHanhChanh bangLuongThangNVHC = new BangLuongThangNhanVienHanhChanh(maBLNVHC, 
										thang, nam, tienBHXH, tienKyLuat, tienPhuCap, bangChamCongThangNVHC);
								List<NhanVienHanhChanh> listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(maNV));
								bangChamCongThangNVHC.setNhanVienHanhChanh(listNVHC.get(0));
								bangLuongThangNVHC.setLuongThangVaThucLinh();
								if (dsLNVHCThang.capNhatBangLuong(bangLuongThangNVHC)) {
									String[] rowData = {maBLNVHC,maNV,
											nhanVienHC.getTenNhanVien(),thang+"",nam+"",formatterTien.format(bangLuongThangNVHC.getLuongThang()),
											formatterTien.format(tienBHXH.getTienBHXH()),
											formatterTien.format(tienKyLuat.getTienKyLuat()),formatterTien.format(tienPhuCap.getTienPhuCap())
											,formatterTien.format(bangLuongThangNVHC.getThucLinh())};
									((DefaultTableModel) tableModel).addRow(rowData);
								}
							}
						}	
					}
					
					
					for (CongNhan congNhan : listCNTK) {
						String maNV = congNhan.getMaNhanVien();
						List<BangChamCongNgayCongNhan> listBCCCN = dsBCCCN.timTheoThangVaMaNV(maNV, thang, nam);
						if (!listBCCCN.isEmpty()) {
							String maBLThangCN = "MLT" + congNhan.getMaNhanVien() + thang + nam;
							TienBHXHCongNhan  tienBHXH = dsBHXHCN.timTienBHXHTheoMaNVThangNam(maNV, thang, nam);
							TienKyLuatCongNhan  tienKyLuat = dsKLCN.timTienTienKyLuatTheoMaNVThangNam(maNV, thang, nam);
							PhuCapCongNhan  tienPhuCap = dsPCCN.timPhuCapTheoMaNVThangNam(maNV, thang, nam);
							
							if (tienPhuCap.getMaPhuCap().equals("")) {
								tienPhuCap = new PhuCapCongNhan(getMaPhuCapTuDong(), 0, thang, nam, congNhan);
								dsPCCN.themPhuCapCongNhan(tienPhuCap);
							}
							if (tienBHXH.getMaTienBHXH().equals("")) {
								tienBHXH = new TienBHXHCongNhan(getMaBHXHTuDong(), 0, thang, nam, congNhan); 
								dsBHXHCN.themTienBHXHCN(tienBHXH);		
							}
							if (tienKyLuat.getMaKyLuat().equals("")) {
								tienKyLuat = new TienKyLuatCongNhan(getMaKLTuDong(), 0, thang, nam, congNhan);
								dsKLCN.themTienKLCN(tienKyLuat);
							}
							List<BangLuongNgayCongNhan> listLuongNgayCN = new ArrayList<BangLuongNgayCongNhan>();

							for (BangChamCongNgayCongNhan bcc : listBCCCN) {
								String maLuongNgay = "MLN" + maNV + bcc.getNgayChamCong().getDate() 
								+ ( bcc.getNgayChamCong().getMonth()+1) 
								+ ( bcc.getNgayChamCong().getYear() + 1900);
								List<BangLuongNgayCongNhan> listBLNgayCN = dsLCNNgay.timKiemBangLuong(new BangLuongNgayCongNhan(maLuongNgay));
								if (listBLNgayCN.isEmpty()) {
									BangLuongNgayCongNhan bangLuongNgayCN = new BangLuongNgayCongNhan(maLuongNgay, bcc, new BangLuongThangCongNhan(maBLThangCN));
									List<ChiTietChamCongCongNhan> listCTCCCN = dsCTCC
											.timKiemChiTietCCCN(new ChiTietChamCongCongNhan(""
													, new CongDoanSanPham(), 0, 0
													, new BangChamCongNgayCongNhan(bcc.getMaChamCongNgay())));
									for (ChiTietChamCongCongNhan ct : listCTCCCN) {
										List<CongDoanSanPham> listCD = dsCD.timKiemCongDoan(new CongDoanSanPham(ct.getCongDoan().getMaCongDoan()));
										ct.setCongDoan(new CongDoanSanPham(ct.getCongDoan().getMaCongDoan(), "", new SanPham(), listCD.get(0).getDonGiaCD()));
									}
									bangLuongNgayCN.tinhLuongNgay(listCTCCCN);
									listLuongNgayCN.add(bangLuongNgayCN);
								} else {
									BangLuongNgayCongNhan bangLuongNgayCN = new BangLuongNgayCongNhan(maLuongNgay, bcc, new BangLuongThangCongNhan(maBLThangCN));
									List<ChiTietChamCongCongNhan> listCTCCCN = dsCTCC
											.timKiemChiTietCCCN(new ChiTietChamCongCongNhan(""
													, new CongDoanSanPham(), 0, 0
													, new BangChamCongNgayCongNhan(bcc.getMaChamCongNgay())));
									for (ChiTietChamCongCongNhan ct : listCTCCCN) {
										List<CongDoanSanPham> listCD = dsCD.timKiemCongDoan(new CongDoanSanPham(ct.getCongDoan().getMaCongDoan()));
										ct.setCongDoan(new CongDoanSanPham(ct.getCongDoan().getMaCongDoan(), "", new SanPham(), listCD.get(0).getDonGiaCD()));
									}
									bangLuongNgayCN.tinhLuongNgay(listCTCCCN);
									dsLCNNgay.capNhatBangLuong(bangLuongNgayCN);
									listLuongNgayCN.add(bangLuongNgayCN);
								}
							}
							
							List<BangLuongThangCongNhan> listLCNTK = dsLCNThang
									.timKiemBangLuong(new BangLuongThangCongNhan(maBLThangCN));
							if (listLCNTK.isEmpty()) {
								BangLuongThangCongNhan bangLuongThangCN = new BangLuongThangCongNhan(maBLThangCN, 
										thang, nam, tienBHXH, tienKyLuat, tienPhuCap, congNhan);
								bangLuongThangCN.setLuongThangVaThucLinh(listLuongNgayCN);
								if (dsLCNThang.themBangLuong(bangLuongThangCN)) {
									for (BangLuongNgayCongNhan bangLuongNgay : listLuongNgayCN) {
										dsLCNNgay.themBangLuong(bangLuongNgay);
									}
									String[] rowData = {maBLThangCN,maNV,
											congNhan.getTenNhanVien(),thang+"",nam+"",formatterTien.format(bangLuongThangCN.getLuongThang()),
											formatterTien.format(tienBHXH.getTienBHXH()),
											formatterTien.format(tienKyLuat.getTienKyLuat()),formatterTien.format(tienPhuCap.getTienPhuCap())
											,formatterTien.format(bangLuongThangCN.getThucLinh())};
									((DefaultTableModel) tableModel).addRow(rowData);
								}
							} else {
								BangLuongThangCongNhan bangLuongThangCN = new BangLuongThangCongNhan(maBLThangCN, 
										thang, nam, tienBHXH, tienKyLuat, tienPhuCap, congNhan);
								bangLuongThangCN.setLuongThangVaThucLinh(listLuongNgayCN);
								if (dsLCNThang.capNhatBangLuong(bangLuongThangCN)) {
									for (BangLuongNgayCongNhan bangLuongNgay : listLuongNgayCN) {
										List<BangLuongNgayCongNhan> bangLuongNgayTK = dsLCNNgay
												.timKiemBangLuong(new BangLuongNgayCongNhan(bangLuongNgay.getMaLuongNgay()));
										if (bangLuongNgayTK.isEmpty()) {
											dsLCNNgay.themBangLuong(bangLuongNgay);
										} else {
											dsLCNNgay.capNhatBangLuong(bangLuongNgay);
										}
									}
									String[] rowData = {maBLThangCN,maNV,
											congNhan.getTenNhanVien(),thang+"",nam+"",formatterTien.format(bangLuongThangCN.getLuongThang()),
											formatterTien.format(tienBHXH.getTienBHXH()),
											formatterTien.format(tienKyLuat.getTienKyLuat()),formatterTien.format(tienPhuCap.getTienPhuCap())
											,formatterTien.format(bangLuongThangCN.getThucLinh())};
									((DefaultTableModel) tableModel).addRow(rowData);
								}
							}
							
						}
					}
					
				}
				
			}
		});
		btnTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTinhLuong.setBounds(924, 80, 128, 29);
		pInfo.add(btnTinhLuong);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 240, 1115, 410);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1115, 410);
		panel_2.add(scrollPane);
		
		String [] headers1 = "Mã Bảng lương;Mã nhân viên;Tên nhân viên;Tháng;Năm;Lương tháng;Tiền BHXH;Tiền Kỉ luật;Tiền Phụ cấp;Thực Lãnh".split(";");
		tableModel = new DefaultTableModel(headers1, 0);
		table = new JTable();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
	}
	
	public void xoaHetTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	
	
	public String getMaBHXHTuDong() {
		String maBHXH = "BHXH";
		int n = dsBHXHCN.getTongSoLuongBHXHNVHC() + dsBHXHNVHC.getTongSoLuongBHXHNVHC() + 1;
		while (!dsBHXHCN.timKiemBHXH(new TienBHXHCongNhan(maBHXH + n)).isEmpty() ||
				!dsBHXHNVHC.timKiemBHXH(new TienBHXHNhanVienHanhChanh(maBHXH + n)).isEmpty()) {
			n++;
		}
		return maBHXH + n;
	}
	
	public String getMaKLTuDong() {
		String maKL = "KL";
		int n = dsKLCN.getTongSoLuongKyLuatCN() + dsKLNVHC.getTongSoLuongKyLuatNVHC() + 1;
		while (!dsKLCN.timKiemKL(new TienKyLuatCongNhan(maKL + n)).isEmpty()
				|| !dsKLNVHC.timKiemKL(new TienKyLuatNhanVienHanhChanh(maKL + n)).isEmpty()) {
			n++;
		}
		return maKL + n;
	}
	
	public String getMaPhuCapTuDong() {
		String maPhuCap = "PC";
		int n = dsPCCN.getTongSoLuongTienPhuCap()
				+ dsPCNVHC.getTongSoLuongTienPhuCap() + 1;
		while (!dsPCCN.timKiemPhuCap(new PhuCapCongNhan(maPhuCap + n)).isEmpty()
				|| !dsPCNVHC.timKiemPhuCap(new PhuCapNhanVienHanhChanh(maPhuCap  + n))
						.isEmpty()) {
			n++;
		}
		return maPhuCap + n;
	}
}
