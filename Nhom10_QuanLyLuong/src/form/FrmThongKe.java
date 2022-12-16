package form;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:09/12/2021
 */

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachBangChamCongNgayCongNhan;
import dao.DanhSachBangChamCongNgayNhanVienHanhChanh;
import dao.DanhSachBangChamCongThangNhanVienHanhChanh;
import dao.DanhSachChiTietChamCongCongNhan;
import dao.DanhSachCongDoanSanPham;
import dao.DanhSachCongNhan;
import dao.DanhSachDonVi;
import dao.DanhSachLuongNgayCongNhan;
import dao.DanhSachLuongThangCongNhan;
import dao.DanhSachLuongThangNhanVienHanhChanh;
import dao.DanhSachNhanVienHanhChanh;
import entity.BangChamCongNgayCongNhan;
import entity.BangChamCongNgayNhanVienHanhChanh;
import entity.BangChamCongThangNhanVienHanhChanh;
import entity.BangLuongNgayCongNhan;
import entity.BangLuongThangCongNhan;
import entity.BangLuongThangNhanVienHanhChanh;
import entity.ChiTietChamCongCongNhan;
import entity.CongDoanSanPham;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVien;
import entity.NhanVienHanhChanh;
import entity.PhuCap;
import entity.TienBHXH;
import entity.TienKyLuat;

import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrmThongKe extends JPanel {
	private JTextField txtTongLuong;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> cboThang;
	private JComboBox<String> cboNam;
	private JComboBox<String> cboDonVi;
	
	private DanhSachDonVi dsDV = new DanhSachDonVi();
	private DanhSachLuongNgayCongNhan dsLuongNgayCN = new DanhSachLuongNgayCongNhan();
	private DanhSachLuongThangCongNhan dsLuongThangCN = new DanhSachLuongThangCongNhan();
	private DanhSachLuongThangNhanVienHanhChanh dsLuongThangNVHC = new DanhSachLuongThangNhanVienHanhChanh();
	private DanhSachNhanVienHanhChanh dsNVHC = new DanhSachNhanVienHanhChanh();
	private DanhSachCongNhan dsCN = new DanhSachCongNhan();
	private DanhSachBangChamCongThangNhanVienHanhChanh dsBCCThangNVHC = new DanhSachBangChamCongThangNhanVienHanhChanh();
	private DanhSachBangChamCongNgayCongNhan dsBCCCN = new DanhSachBangChamCongNgayCongNhan();
	private DanhSachBangChamCongNgayCongNhan dsBCCNgayCN = new DanhSachBangChamCongNgayCongNhan();
	private DanhSachBangChamCongNgayNhanVienHanhChanh dsBCCNgayNVHC = new DanhSachBangChamCongNgayNhanVienHanhChanh();
	private DanhSachChiTietChamCongCongNhan dsCTCC = new DanhSachChiTietChamCongCongNhan();
	private DanhSachCongDoanSanPham dsCD =new DanhSachCongDoanSanPham();
	
	private SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MM-yyyy");
	private DecimalFormat formatterTien = new DecimalFormat("###,###,###.##");
	private PrintWriter outFile;
	
	private List<String[]> rows = new ArrayList<String[]>();
	
	public FrmThongKe() {
		setPreferredSize(new Dimension(1140, 660));
		setLayout(null);
		
		JLabel lblTitle = new JLabel("THỐNG KÊ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitle.setBounds(0, 11, 1140, 44);
		add(lblTitle);
		
		JPanel pTimKiem = new JPanel();
		pTimKiem.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pTimKiem.setBounds(10, 55, 1120, 60);
		add(pTimKiem);
		pTimKiem.setLayout(null);
		
		JLabel lblChonThang = new JLabel("Chọn tháng:");
		lblChonThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChonThang.setBounds(10, 14, 102, 28);
		pTimKiem.add(lblChonThang);
		
		cboThang = new JComboBox<String>();
		for (int i = 1; i < 13; i++) {
			cboThang.addItem(i+"");
		}
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboThang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboThang.setBounds(112, 16, 102, 25);
		pTimKiem.add(cboThang);
		
		JLabel lblNam = new JLabel("Chọn Năm :");
		lblNam.setPreferredSize(new Dimension(120, 20));
		lblNam.setMinimumSize(new Dimension(120, 20));
		lblNam.setMaximumSize(new Dimension(120, 20));
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNam.setBounds(274, 18, 120, 20);
		pTimKiem.add(lblNam);
		
		cboNam = new JComboBox<String>();
		for (int i = new Date().getYear() + 1900; i > 2009; i--) {
			cboNam.addItem(i+"");
		}
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboNam.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboNam.setBounds(373, 15, 102, 25);
		pTimKiem.add(cboNam);
		
		JLabel lblChnnV = new JLabel("Chọn đơn vị:");
		lblChnnV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChnnV.setBounds(549, 14, 109, 28);
		pTimKiem.add(lblChnnV);
		
		cboDonVi = new JComboBox<String>();
		cboDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboDonVi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboDonVi.setBounds(660, 15, 251, 25);
		pTimKiem.add(cboDonVi);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(255, 204, 153));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int thang = Integer.parseInt(cboThang.getSelectedItem()+"");
				int nam = Integer.parseInt(cboNam.getSelectedItem()+"");
				String tenDonVi = cboDonVi.getSelectedItem()+"";
				double tongTien = 0;
				
				rows = new ArrayList<String[]>();
				
				List<DonVi> listDV = dsDV.timKiemDonVi(new DonVi("", tenDonVi));
				if (!listDV.isEmpty()) {
					List<NhanVienHanhChanh> listNVHCTheoDV = dsNVHC
							.timKiemNVHC(new NhanVienHanhChanh("", "", ""
									, null, new DonVi(listDV.get(0).getMaDonVi()), "", "", "", "", 0)); 
					if (!listNVHCTheoDV.isEmpty()) {
						for (NhanVienHanhChanh nvhc : listNVHCTheoDV) {
							String maLuongThang = "MLT" + nvhc.getMaNhanVien() + thang + nam;
							List<BangLuongThangNhanVienHanhChanh> listLuongThang = dsLuongThangNVHC
									.timKiemBangLuong(new BangLuongThangNhanVienHanhChanh(maLuongThang, 0, 0
											, new TienBHXH(), new TienKyLuat(), new PhuCap()
											, new BangChamCongThangNhanVienHanhChanh()));
							if (!listLuongThang.isEmpty()) {
								String[] row = {maLuongThang,nvhc.getMaNhanVien(),nvhc.getTenNhanVien(),tenDonVi,thang+""
										,nam+"",formatterTien.format(listLuongThang.get(0).getLuongThang())
										,formatterTien.format(listLuongThang.get(0).getTienBHXH().getTienBHXH())
										,formatterTien.format(listLuongThang.get(0).getTienKyLuat().getTienKyLuat())
										,formatterTien.format(listLuongThang.get(0).getTienPhuCap().getTienPhuCap())
										,formatterTien.format(listLuongThang.get(0).getThucLinh())};
								rows.add(row);
								tongTien += listLuongThang.get(0).getThucLinh();
							}
						}
					}
					List<CongNhan> listCNTheoDV = dsCN.timKiemCN(new CongNhan("", "", ""
							, null, new DonVi(listDV.get(0).getMaDonVi()), "", "", 0));
					if (!listCNTheoDV.isEmpty()) {
						for (CongNhan cn : listCNTheoDV) {
							String maLuongThang = "MLT" + cn.getMaNhanVien() + thang + nam;
							List<BangLuongThangCongNhan> listLuongThang = dsLuongThangCN
									.timKiemBangLuong(new BangLuongThangCongNhan(maLuongThang, 0, 0
											, new TienBHXH(), new TienKyLuat(), new PhuCap()
											, new CongNhan()));
							if (!listLuongThang.isEmpty()) {
								String[] row = {maLuongThang,cn.getMaNhanVien(),cn.getTenNhanVien(),tenDonVi,thang+""
										,nam+"",formatterTien.format(listLuongThang.get(0).getLuongThang())
										,formatterTien.format(listLuongThang.get(0).getTienBHXH().getTienBHXH())
										,formatterTien.format(listLuongThang.get(0).getTienKyLuat().getTienKyLuat())
										,formatterTien.format(listLuongThang.get(0).getTienPhuCap().getTienPhuCap())
										,formatterTien.format(listLuongThang.get(0).getThucLinh())};
								rows.add(row);
								tongTien += listLuongThang.get(0).getThucLinh();
							}
						}
					}
				}
				updateTableData();
				txtTongLuong.setText(formatterTien.format(tongTien));
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(1001, 16, 109, 25);
		pTimKiem.add(btnTim);
		
		JPanel pTable = new JPanel();
		pTable.setBounds(10, 126, 1120, 483);
		add(pTable);
		pTable.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1120, 431);
		pTable.add(scrollPane);
		
		table = new JTable();
		String [] headers1 = "Mã Bảng lương;Mã nhân viên;Tên nhân viên;Đơn vị;Tháng;Năm;Lương tháng;Tiền BHXH;Tiền Kỉ luật;Tiền Phụ cấp;Thực Lãnh".split(";");
		tableModel = new DefaultTableModel(headers1, 0);
		table = new JTable();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongTien.setBounds(759, 445, 82, 20);
		pTable.add(lblTongTien);
		
		txtTongLuong = new JTextField();
		txtTongLuong.setHorizontalAlignment(SwingConstants.LEFT);
		txtTongLuong.setEditable(false);
		txtTongLuong.setForeground(Color.RED);
		txtTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongLuong.setBounds(851, 442, 259, 30);
		pTable.add(txtTongLuong);
		txtTongLuong.setColumns(10);
		
		JButton btnXuatBaoCao = new JButton("Xuất báo cáo");
		btnXuatBaoCao.setBackground(Color.CYAN);
		btnXuatBaoCao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!rows.isEmpty()) {
					int thang = Integer.parseInt(rows.get(0)[4]);
					int nam = Integer.parseInt(rows.get(0)[5]);
					try { 
						List<DonVi> listDV = dsDV.timKiemDonVi(new DonVi("", rows.get(0)[3]));
						outFile = new PrintWriter(new FileWriter("file/BaoCaoLuongTheoDonVi/"+listDV.get(0).getMaDonVi()+".txt"));
						outFile.println("BẢNG LƯƠNG THÁNG "+thang+" NĂM "+nam);
						outFile.println("\nMã đơn vị: "+listDV.get(0).getMaDonVi());
						outFile.println("Tên đơn vị: "+rows.get(0)[3]);
						outFile.println("\nDanh sách lương: ");
						outFile.printf("\t%-25s %-25s %-50s %-25s %-25s %-25s %-25s %-25s"
								,"Mã lương tháng","Mã nhân viên","Tên nhân viên","Lương tháng"
								,"Tiền BHXH","Tiền kỷ luật","Phụ cấp","Thực lĩnh");
						for (String[] row : rows) {
							outFile.printf("\n\t%-25s %-25s %-50s %-25s %-25s %-25s %-25s %-25s"
									,row[0],row[1],row[2],row[6].trim()
									,row[7].trim(),row[8].trim(),row[9].trim(),row[10].trim());
						}
						outFile.println("\nTổng lương đơn vị: "+ txtTongLuong.getText()+"VNĐ");
						
						outFile.flush();
						outFile.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
				
		});
		btnXuatBaoCao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXuatBaoCao.setBounds(10, 620, 137, 29);
		add(btnXuatBaoCao);
		
		JButton btnInBangLuong = new JButton("In bảng lương");
		btnInBangLuong.setBackground(Color.CYAN);
		btnInBangLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String[] row : rows) {
					try {
						int thang = Integer.parseInt(row[4]);
						int nam = Integer.parseInt(row[5]);
						outFile = new PrintWriter(new FileWriter("file/BangLuongTungNhanVien/"+row[0]+".txt"));
						outFile.println("BẢNG LƯƠNG THÁNG "+ thang +" NĂM "+ nam);
						outFile.println("\nMã nhân viên: " + row[1]);
						outFile.println("Tên nhân viên: "+row[2]);
						outFile.println("Đơn vị: "+row[3]);
						
						List<BangChamCongNgayNhanVienHanhChanh> listBCCNgayNVHC = dsBCCNgayNVHC
								.timTheoMaNVVaThangNam(row[1], thang, nam);
						if (!listBCCNgayNVHC.isEmpty()) {
							outFile.println("\nBảng chấm công");
							outFile.printf("%-25s %-15s %-15s","Ngày chấm công","Đi làm","Làm thêm");
							for (BangChamCongNgayNhanVienHanhChanh bcc : listBCCNgayNVHC) {
								outFile.printf("\n%-25s %-15s %-15s",bcc.getNgayChamCong(),bcc.isCoDilam()?"Có":"Không",bcc.isCoLamThem()?"Có":"Không");
							}
							List<BangChamCongThangNhanVienHanhChanh> listCCThangNVHC = dsBCCThangNVHC.timBangChamCongTheoThangVaNam(thang, nam); 
							outFile.println("\nTổng số ngày đi làm trong tháng: "+listCCThangNVHC.get(0).getSoNgayDiLam());
							outFile.println("Tổng số buổi làm thêm trong tháng: "+listCCThangNVHC.get(0).getSoBuoiLamThem());
							List<NhanVienHanhChanh> listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(row[1]));
							outFile.println("\nTiền lương tháng: ");
							outFile.println("\t- Lương ngày cơ bản: "+formatterTien.format(listNVHC.get(0).getLuongNgayCoBan())+" VNĐ");
							outFile.println("\t- Tổng lương tháng: "+row[6]+" VNĐ");
							outFile.println("\t- Tiền phụ cấp: "+row[9]+" VNĐ");
							outFile.println("\t- Khấu trừ: ");
							outFile.println("\t\tTiền kỷ luật: "+row[8]+" VNĐ");
							outFile.println("\t\tTiền BHXH: "+row[7]+" VNĐ");
							outFile.println("\t- Thực lĩnh: "+row[10]+" VNĐ");
						}
						
						List<BangChamCongNgayCongNhan> listBCCNgayCN = dsBCCCN.timTheoThangVaMaNV(row[1], thang, nam);
						if (!listBCCNgayCN.isEmpty()) {
							outFile.println("\nBảng chấm công");
							for (BangChamCongNgayCongNhan bcc : listBCCNgayCN) {
								outFile.println("- Ngày " + formatterDate.format(bcc.getNgayChamCong()) + ":");
								List<ChiTietChamCongCongNhan> listCT = dsCTCC
										.timKiemChiTietCCCN(new ChiTietChamCongCongNhan(""
												, new CongDoanSanPham(), 0, 0, bcc));
								String maLuongNgay = "MLN" + row[1] 
										+ bcc.getNgayChamCong().getDate()
										+ (bcc.getNgayChamCong().getMonth()+1)
										+ (bcc.getNgayChamCong().getYear()+1900);
								
								List<BangLuongNgayCongNhan> listLuongNgay = dsLuongNgayCN
										.timKiemBangLuong(new BangLuongNgayCongNhan(maLuongNgay));
								outFile.printf("\t%-25s %-25s %-30s %-30s"
										,"Ca làm việc","Mã công đoạn","Đơn giá công đoạn"
										,"Số lượng sản phẩm");
								for (ChiTietChamCongCongNhan ct : listCT) {
									List<CongDoanSanPham> listCD = dsCD
											.timKiemCongDoan(new CongDoanSanPham(ct.getCongDoan()
													.getMaCongDoan()));
									
									outFile.printf("\n\t%-25s %-25s %-30s %-30s",ct.getCaLamViec()
											,ct.getCongDoan().getMaCongDoan()
											,formatterTien.format(listCD.get(0).getDonGiaCD()),ct.getSoLuongSP());
								}
								outFile.println("\nTổng lương ngày: "+formatterTien.format(listLuongNgay.get(0).getLuongNgay())+" VNĐ");
							}
							outFile.println("\nTiền lương tháng:");
							outFile.println("\t- Tổng lương tháng: "+row[6]+" VNĐ");
							outFile.println("\t- Tiền phụ cấp: "+row[9]+" VNĐ");
							outFile.println("\t- Khấu trừ: ");
							outFile.println("\t\tTiền kỷ luật: "+row[8]+" VNĐ");
							outFile.println("\t\tTiền BHXH: "+row[7]+" VNĐ");
							outFile.println("\t- Thực lĩnh: "+row[10]+" VNĐ");
						}
						
						outFile.flush();
						outFile.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnInBangLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInBangLuong.setBounds(172, 620, 137, 29);
		add(btnInBangLuong);
		updateComboBox();
	}
	public void updateComboBox() {
		List<DonVi> listDV = dsDV.docDataBase();
		for (DonVi donVi : listDV) {
			cboDonVi.addItem(donVi.getTenDonVi());
		}
	}
	public void xoaHetTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
	}
	public void updateTableData() {
		xoaHetTable();
		if (rows.isEmpty()) {
			String[] row = {"","","","","","","","","","",""};
			tableModel.addRow(row);
		} else {
			for (String[] row : rows) {
				tableModel.addRow(row);
			}
		}
		table.setModel(tableModel);
	}
}
