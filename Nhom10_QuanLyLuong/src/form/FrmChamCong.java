package form;

/*
 * Author:Nguyễn Võ Vươn Lập
 * Date:09/12/2021
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DanhSachBangChamCongNgayCongNhan;
import dao.DanhSachBangChamCongNgayNhanVienHanhChanh;
import dao.DanhSachBangChamCongThangNhanVienHanhChanh;
import dao.DanhSachChiTietChamCongCongNhan;
import dao.DanhSachCongDoanSanPham;
import dao.DanhSachCongNhan;
import dao.DanhSachDonVi;
import dao.DanhSachNhanVienHanhChanh;
import entity.BangChamCongNgayCongNhan;
import entity.BangChamCongNgayNhanVienHanhChanh;
import entity.BangChamCongThangNhanVienHanhChanh;
import entity.ChiTietChamCongCongNhan;
import entity.CongDoanSanPham;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVienHanhChanh;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmChamCong extends JPanel {
	private JTable tableCCCN, tableBangCCNgayNVHC, tableBangCCThangNVHC, tableChamCongNVHC;
	private DefaultTableModel tableModelChamCongCN, tableModelCCNVHC, tableModelChamCongThangNVHC, tableModelChamCongNgayNVHC;
	private JDateChooser dateNgayCCCN;
	private JTextField txtMaCN, txtSoLuongSP, txtTenCN, txtTimDSCCNVHC;
	private DefaultTableModel tableModelDSCCCN;
	private JTable tableDSCCCN;
	private JTable tableChiTietCCCN;
	private DefaultTableModel tableModelChiTietCCCN;
	private JTextField txtTimDSCCCN;
	private JDateChooser dateTimKiemDSCCCN;
	private JComboBox<String> cboTimTheoDV;
	private DanhSachDonVi dsDonVi = new DanhSachDonVi();
	private DanhSachCongDoanSanPham dsCongDoan = new DanhSachCongDoanSanPham();
	private JComboBox<String> cboMaCD;
	private DanhSachBangChamCongNgayCongNhan dsBCCCN = new DanhSachBangChamCongNgayCongNhan();
	private DanhSachChiTietChamCongCongNhan dsCTCCCN = new DanhSachChiTietChamCongCongNhan();
	private DanhSachBangChamCongNgayNhanVienHanhChanh dsCCNgayNVHC = new DanhSachBangChamCongNgayNhanVienHanhChanh();
	private DanhSachBangChamCongThangNhanVienHanhChanh dsCCThangNVHC = new DanhSachBangChamCongThangNhanVienHanhChanh();
	private DanhSachCongNhan dsCN = new DanhSachCongNhan();
	private DanhSachNhanVienHanhChanh dsNVHC = new DanhSachNhanVienHanhChanh();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private List<String[]> rowsCCCN = new ArrayList<String[]>();
	private List<Object[]> rowsCCNVHC = new ArrayList<Object[]>();
	private List<BangChamCongNgayCongNhan> listCCCN = new ArrayList<BangChamCongNgayCongNhan>();
	private List<ChiTietChamCongCongNhan> listCTCC = new ArrayList<ChiTietChamCongCongNhan>();
	private JComboBox<String> cboTimTheoCa;
	private JLabel lblMessCCCN;
	private JComboBox<String> cboTimDSCCCN;
	private JComboBox<String> cboTimNVHCTheoDV;
	private JComboBox<String> cboTimDSCCNVHCTheoDV;
	private JComboBox<String> cboTimDSCCNVHC;
	private JComboBox<String> cboTimDSCCNVHCTheoThang;
	private JComboBox<String> cboTimDSCCNVHCTheoNam;


	public FrmChamCong() {
		setPreferredSize(new Dimension(1140, 660));
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tab = new JTabbedPane();
		add(tab, BorderLayout.CENTER);

		JPanel pChamCongCN = new JPanel();
		pChamCongCN.setLayout(null);
		pChamCongCN.setBounds(10, 11, 1120, 627);
		tab.addTab("Chấm công công nhân", pChamCongCN);

		JPanel pThucHienChamCongCN = new JPanel();
		pThucHienChamCongCN.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pThucHienChamCongCN.setBounds(8, 11, 1120, 378);
		pThucHienChamCongCN.setLayout(null);
		pChamCongCN.add(pThucHienChamCongCN);

		JLabel lblChamCongCN = new JLabel("CHẤM CÔNG CÔNG NHÂN");
		lblChamCongCN.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblChamCongCN.setHorizontalAlignment(SwingConstants.CENTER);
		lblChamCongCN.setBounds(10, 9, 1100, 31);
		pThucHienChamCongCN.add(lblChamCongCN);

		JScrollPane scrollChamCongCN = new JScrollPane();
		scrollChamCongCN.setViewportView(tableCCCN);
		String [] headersCCCN = "Mã nhân viên;Tên nhân viên;Đơn vị;Ngày chấm công;Ca làm việc;Mã công đoạn; Số lượng SP".split(";");
		tableModelChamCongCN = new DefaultTableModel(headersCCCN, 0);
		scrollChamCongCN.setBounds(10, 53, 786, 286);
		pThucHienChamCongCN.add(scrollChamCongCN = new JScrollPane(tableCCCN = new JTable(tableModelChamCongCN), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		tableCCCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableCCCN.getSelectedRow();
				String maCN = (String) tableCCCN.getValueAt(row, 0);
				String maCD = (String) tableCCCN.getValueAt(row, 5); 
				String soLuongSP = (String) tableCCCN.getValueAt(row, 6);
				List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan(maCN));
				txtMaCN.setText(maCN);
				txtTenCN.setText(listCN.get(0).getTenNhanVien());
				txtSoLuongSP.setText(soLuongSP);
				cboMaCD.setSelectedItem(maCD);
			}
		});

		JPanel pTimChamCongCN = new JPanel();
		pTimChamCongCN.setBorder(new TitledBorder(null, "T\u00ECm c\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimChamCongCN.setBounds(678, 43, 432, 146);
		pThucHienChamCongCN.add(pTimChamCongCN);
		pTimChamCongCN.setLayout(null);

		cboTimTheoDV = new JComboBox<String>();
		cboTimTheoDV.setBounds(119, 11, 303, 28);
		pTimChamCongCN.add(cboTimTheoDV);
		cboTimTheoDV.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblDonViCCCN = new JLabel("Chọn đơn vị:");
		lblDonViCCCN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDonViCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonViCCCN.setBounds(0, 15, 109, 19);
		pTimChamCongCN.add(lblDonViCCCN);

		JLabel lblNgayCCCN = new JLabel("Chọn ngày:");
		lblNgayCCCN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayCCCN.setBounds(0, 46, 109, 19);
		pTimChamCongCN.add(lblNgayCCCN);
		scrollChamCongCN.setBounds(10, 42, 655, 325);

		dateNgayCCCN = new JDateChooser();
		dateNgayCCCN.setSize(303, 28);
		dateNgayCCCN.setLocation(119, 40);
		dateNgayCCCN.setPreferredSize(new Dimension(140, 20));
		dateNgayCCCN.setDateFormatString("dd-MM-yyyy");
		dateNgayCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimChamCongCN.add(dateNgayCCCN);

		JButton btnTimCN = new JButton("Tìm");
		btnTimCN.setBackground(new Color(255, 204, 153));
		btnTimCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String donVi = cboTimTheoDV.getSelectedItem()+"";
				List<DonVi> listDV = dsDonVi.timKiemDonVi(new DonVi("", donVi));
				Date dateCC = dateNgayCCCN.getDate();
				int ca = Integer.parseInt(cboTimTheoCa.getSelectedItem()+"");
				List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan("", "", "", null, new DonVi(listDV.get(0).getMaDonVi()), "", "", 0));
				rowsCCCN = new ArrayList<String[]>();
				listCCCN = new ArrayList<BangChamCongNgayCongNhan>();
				listCTCC = new ArrayList<ChiTietChamCongCongNhan>();
				for (CongNhan cn : listCN) {
					BangChamCongNgayCongNhan bcc = dsBCCCN.timTheoNgayVaMaNV(cn.getMaNhanVien(), dateCC);

					if (bcc != null) {
						listCCCN.add(bcc);
						ChiTietChamCongCongNhan ct = dsCTCCCN.timTheoCaVaMaCC(bcc.getMaChamCongNgay(), ca);
						if (ct != null) {
							listCTCC.add(ct);
							String[] row = {cn.getMaNhanVien(),cn.getTenNhanVien()
									,donVi,formatter.format(dateCC),ca+""
									,ct.getCongDoan().getMaCongDoan(),ct.getSoLuongSP()+""};
							rowsCCCN.add(row);
						} else {
							String[] row = {cn.getMaNhanVien(),cn.getTenNhanVien()
									,donVi,formatter.format(dateCC),ca+"","",""};
							rowsCCCN.add(row);
						}
					} else {
						String[] row = {cn.getMaNhanVien(),cn.getTenNhanVien()
								,donVi,formatter.format(dateCC),ca+"","",""};
						rowsCCCN.add(row);
					}
				}
				updateTableBCCCNFromList();
				txtMaCN.setText(rowsCCCN.get(0)[0]);
				txtTenCN.setText(rowsCCCN.get(0)[1]);
				cboMaCD.setSelectedItem(rowsCCCN.get(0)[5]);
				txtSoLuongSP.setText(rowsCCCN.get(0)[6]);
			}
		});
		btnTimCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimCN.setBounds(312, 108, 110, 27);
		pTimChamCongCN.add(btnTimCN);

		JPanel pThongTinCCCN = new JPanel();
		pThongTinCCCN.setBounds(678, 188, 432, 179);
		pThucHienChamCongCN.add(pThongTinCCCN);
		pThongTinCCCN.setLayout(null);

		JLabel lblMaCN = new JLabel("Mã nhân viên:");
		lblMaCN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaCN.setBounds(10, 9, 144, 20);
		pThongTinCCCN.add(lblMaCN);

		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		txtMaCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaCN.setBounds(164, 5, 266, 28);
		pThongTinCCCN.add(txtMaCN);
		txtMaCN.setColumns(10);

		JLabel lblTenCN = new JLabel("Tên nhân viên:");
		lblTenCN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenCN.setBounds(10, 40, 144, 20);
		pThongTinCCCN.add(lblTenCN);

		txtTenCN = new JTextField();
		txtTenCN.setEditable(false);
		txtTenCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenCN.setColumns(10);
		txtTenCN.setBounds(164, 35, 266, 28);
		pThongTinCCCN.add(txtTenCN);

		JLabel lblMaCongDoan = new JLabel("Mã công đoạn:");
		lblMaCongDoan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaCongDoan.setBounds(10, 68, 144, 20);
		pThongTinCCCN.add(lblMaCongDoan);

		JLabel lblSoLuongSP = new JLabel("Số lượng sản phẩm:");
		lblSoLuongSP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoLuongSP.setBounds(10, 99, 144, 20);
		pThongTinCCCN.add(lblSoLuongSP);

		txtSoLuongSP = new JTextField();
		txtSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongSP.setColumns(10);
		txtSoLuongSP.setBounds(164, 95, 266, 28);
		pThongTinCCCN.add(txtSoLuongSP);

		JButton btnBack = new JButton("<<");
		btnBack.setBackground(Color.CYAN);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maCN = txtMaCN.getText();
				if (validDataCCCN()) {
					try {
						String maCD = cboMaCD.getSelectedItem()+"";
						int soLuongSP = Integer.parseInt(txtSoLuongSP.getText().trim());
						Date ngayCC;
						ngayCC = formatter.parse((String) tableCCCN.getValueAt(0, 3));
						for (int i = 0; i < listCCCN.size(); i++)  {
							if (listCCCN.get(i).getCongNhan().getMaNhanVien().equals(maCN) && listCCCN.get(i).getNgayChamCong().equals(ngayCC)) {
								BangChamCongNgayCongNhan bcc = listCCCN.get(i);
								bcc.setCongNhan(new CongNhan(maCN));
								bcc.setNgayChamCong(ngayCC);
								listCCCN.set(i, bcc);
							} else {
								listCCCN.add(new BangChamCongNgayCongNhan(maCN, ngayCC, new CongNhan(maCN)));
							}
						}
						if (listCTCC.isEmpty()) {
							listCTCC.add(new ChiTietChamCongCongNhan(maCD+maCN, new CongDoanSanPham(maCD), Integer.parseInt((String) tableCCCN.getValueAt(0, 4)), soLuongSP, new BangChamCongNgayCongNhan(maCN)));
						} else {
							boolean daCo = false;
							for (int i = 0; i < listCTCC.size(); i++) {
								if (listCTCC.get(i).getMaChiTietCC().equals(maCD+maCN)) {
									ChiTietChamCongCongNhan ct = listCTCC.get(i);
									ct.setSoLuongSP(soLuongSP);
									listCTCC.set(i, ct);
									daCo = true;
								}
							}
							if (!daCo) {
								listCTCC.add(new ChiTietChamCongCongNhan(maCD+maCN, new CongDoanSanPham(maCD), Integer.parseInt((String) tableCCCN.getValueAt(0, 4)), soLuongSP, new BangChamCongNgayCongNhan(maCN)));
							}
						}
						for (int i = 0; i < rowsCCCN.size(); i++) {
							if (rowsCCCN.get(i)[0].equals(maCN)) {
								String[] row = rowsCCCN.get(i);
								row[5] = maCD;
								row[6] = soLuongSP+"";
								rowsCCCN.set(i, row);
								tableModelChamCongCN.setValueAt(maCD, i, 5);
								tableModelChamCongCN.setValueAt(soLuongSP+"", i, 6);
								if (i == 0) {
									break;
								}
								txtMaCN.setText(rowsCCCN.get(i-1)[0]);
								txtTenCN.setText(rowsCCCN.get(i-1)[1]);
								cboMaCD.setSelectedItem(rowsCCCN.get(i-1)[5]);
								txtSoLuongSP.setText(rowsCCCN.get(i-1)[6]);
								lblMessCCCN.setText("");
								break;
							}
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				} else {
					for (int i = 0; i < rowsCCCN.size(); i++) {
						if (rowsCCCN.get(i)[0].equals(maCN)) {
							if (i == 0) {
								break;
							}
							txtMaCN.setText(rowsCCCN.get(i-1)[0]);
							txtTenCN.setText(rowsCCCN.get(i-1)[1]);
							cboMaCD.setSelectedItem(rowsCCCN.get(i-1)[5]);
							txtSoLuongSP.setText(rowsCCCN.get(i-1)[6]);
							lblMessCCCN.setText("");
							break;
						}
					}
				}
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(164, 151, 75, 23);
		pThongTinCCCN.add(btnBack);

		JButton btnNext = new JButton(">>");
		btnNext.setBackground(Color.CYAN);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maCN = txtMaCN.getText();
				if (validDataCCCN()) {
					try {
						String maCD = cboMaCD.getSelectedItem()+"";
						int soLuongSP = Integer.parseInt(txtSoLuongSP.getText().trim());
						Date ngayCC;
						ngayCC = formatter.parse((String) tableCCCN.getValueAt(0, 3));
						if (listCCCN.isEmpty()) {
							listCCCN.add(new BangChamCongNgayCongNhan(maCN, ngayCC, new CongNhan(maCN)));
						} else {
							for (int i = 0; i < listCCCN.size(); i++)  {
								if (listCCCN.get(i).getCongNhan().getMaNhanVien().equals(maCN) && listCCCN.get(i).getNgayChamCong().equals(ngayCC)) {
									BangChamCongNgayCongNhan bcc = listCCCN.get(i);
									bcc.setCongNhan(new CongNhan(maCN));
									bcc.setNgayChamCong(ngayCC);
									listCCCN.set(i, bcc);
								} else {
									listCCCN.add(new BangChamCongNgayCongNhan(maCN, ngayCC, new CongNhan(maCN)));
								}
							}
						}
						if (listCTCC.isEmpty()) {
							listCTCC.add(new ChiTietChamCongCongNhan(maCD+maCN, new CongDoanSanPham(maCD), Integer.parseInt((String) tableCCCN.getValueAt(0, 4)), soLuongSP, new BangChamCongNgayCongNhan(maCN)));
						} else {
							boolean daCo = false;
							for (int i = 0; i < listCTCC.size(); i++) {
								if (listCTCC.get(i).getMaChiTietCC().equals(maCD+maCN)) {
									ChiTietChamCongCongNhan ct = listCTCC.get(i);
									ct.setSoLuongSP(soLuongSP);
									listCTCC.set(i, ct);
									daCo = true;
								}
							}
							if (!daCo) {
								listCTCC.add(new ChiTietChamCongCongNhan(maCD+maCN, new CongDoanSanPham(maCD), Integer.parseInt((String) tableCCCN.getValueAt(0, 4)), soLuongSP, new BangChamCongNgayCongNhan(maCN)));
							}
						}
						for (int i = 0; i < rowsCCCN.size(); i++) {
							if (rowsCCCN.get(i)[0].equals(maCN)) {
								String[] row = rowsCCCN.get(i);
								row[5] = maCD;
								row[6] = soLuongSP+"";
								rowsCCCN.set(i, row);
								tableModelChamCongCN.setValueAt(maCD, i, 5);
								tableModelChamCongCN.setValueAt(soLuongSP+"", i, 6);
								if ((i+1) == rowsCCCN.size()) {
									break;
								}
								txtMaCN.setText(rowsCCCN.get(i+1)[0]);
								txtTenCN.setText(rowsCCCN.get(i+1)[1]);
								cboMaCD.setSelectedItem(rowsCCCN.get(i+1)[5]);
								txtSoLuongSP.setText(rowsCCCN.get(i+1)[6]);
								lblMessCCCN.setText("");
								break;
							}
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				} else {
					for (int i = 0; i < rowsCCCN.size(); i++) {
						if (rowsCCCN.get(i)[0].equals(maCN)) {
							if ((i+1) == rowsCCCN.size()) {
								break;
							}
							txtMaCN.setText(rowsCCCN.get(i+1)[0]);
							txtTenCN.setText(rowsCCCN.get(i+1)[1]);
							cboMaCD.setSelectedItem(rowsCCCN.get(i+1)[5]);
							txtSoLuongSP.setText(rowsCCCN.get(i+1)[6]);
							lblMessCCCN.setText("");
							break;
						}
					}
				}

			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNext.setBounds(355, 151, 75, 23);
		pThongTinCCCN.add(btnNext);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setBackground(Color.CYAN);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listCCCN.isEmpty()) {
					boolean luuBCC = false;
					boolean luuCT = false;
					for (BangChamCongNgayCongNhan bccCN : listCCCN) {
						BangChamCongNgayCongNhan bccCNTK = dsBCCCN
								.timTheoNgayVaMaNV(bccCN.getCongNhan().getMaNhanVien()
										, bccCN.getNgayChamCong()); 
						if (bccCNTK == null) {
							bccCN.setMaChamCongNgay(setMaCCCNTuDong());
							if (dsBCCCN.themBangChamCongNgayCN(bccCN)) {
								luuBCC = true;
							}
							if (!listCTCC.isEmpty()) {
								for (ChiTietChamCongCongNhan ct : listCTCC) {
//									System.out.println("ádasd");
									if (bccCN.getCongNhan().getMaNhanVien().equals(ct.getBangChamCongCN().getMaChamCongNgay())) {
										ChiTietChamCongCongNhan ctTK = dsCTCCCN
												.timTheoCaVaMaCC(bccCN.getMaChamCongNgay(), ct.getCaLamViec());
										if (ctTK == null) {
											ct.setMaChiTietCC(setMaCTCCTuDong());
											ct.setBangChamCongCN(bccCN);
											if (dsCTCCCN.themChiTietCCCN(ct)) {
												luuCT = true;
											}

										} else {
											ct.setBangChamCongCN(bccCN);
											if (dsCTCCCN.capNhatChiTietCCCN(ct)) {
												luuCT = true;
											}
										}
									}
								}
							} else {
								luuCT = true;
							}
						} else {
							bccCN.setMaChamCongNgay(bccCNTK.getMaChamCongNgay());
							luuBCC = true;
							for (ChiTietChamCongCongNhan ct : listCTCC) {
								if (bccCN.getCongNhan().getMaNhanVien().equals(ct.getBangChamCongCN().getMaChamCongNgay())) {
									ChiTietChamCongCongNhan ctTK = dsCTCCCN
											.timTheoCaVaMaCC(bccCN.getMaChamCongNgay()
													, ct.getCaLamViec());
									if (ctTK == null) {
										ct.setMaChiTietCC(setMaCTCCTuDong());
										ct.setBangChamCongCN(bccCN);
										if (dsCTCCCN.themChiTietCCCN(ct)) {
											luuCT = true;
										}

									} else {
										ct.setMaChiTietCC(ctTK.getMaChiTietCC());
										ct.setBangChamCongCN(bccCNTK);
										if (dsCTCCCN.capNhatChiTietCCCN(ct)) {
											luuCT = true;
										}
									}
								}
							}
						}
					}
					if (luuBCC && luuCT) {
						lblMessCCCN.setText("Lưu thành công");
						updateTableDSBCCCN();
						updateTableCTCC(new ChiTietChamCongCongNhan());
					} else {
						if (!luuBCC) {
							lblMessCCCN.setText("Lưu bảng chấm công không thành công");
						} 
						if (!luuCT) {
							lblMessCCCN.setText("Lưu chi tiết chấm công không thành công");
						}
					}

				}
			}
		});
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLuu.setBounds(10, 151, 110, 23);
		pThongTinCCCN.add(btnLuu);

		cboMaCD = new JComboBox<String>();
		cboMaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboMaCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboMaCD.setBounds(164, 65, 266, 28);
		pThongTinCCCN.add(cboMaCD);

		lblMessCCCN = new JLabel("");
		lblMessCCCN.setForeground(Color.RED);
		lblMessCCCN.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMessCCCN.setBounds(71, 126, 351, 20);
		pThongTinCCCN.add(lblMessCCCN);

		JPanel pDanhSachBCCCN = new JPanel();
		pDanhSachBCCCN.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pDanhSachBCCCN.setBounds(8, 400, 1120, 221);
		pDanhSachBCCCN.setLayout(null);
		pChamCongCN.add(pDanhSachBCCCN);

		JLabel lblTitleDSBCC = new JLabel("DANH SÁCH BẢNG CHẤM CÔNG CÔNG NHÂN");
		lblTitleDSBCC.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitleDSBCC.setBounds(10, 11, 480, 30);
		pDanhSachBCCCN.add(lblTitleDSBCC);

		String [] headersDSCCCN = "Mã chấm công;Mã nhân viên;Tên nhân viên;Đơn vị;Ngày chấm công".split(";");
		tableModelDSCCCN = new DefaultTableModel(headersDSCCCN, 0);
		JScrollPane scrollDSBangCCCN = new JScrollPane(tableDSCCCN = new JTable(tableModelDSCCCN), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableDSCCCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowCCCN = tableDSCCCN.getSelectedRow();
				String maCCCN = (String) tableDSCCCN.getValueAt(rowCCCN, 0);
				updateTableCTCC(new ChiTietChamCongCongNhan("", new CongDoanSanPham(), 0, 0, new BangChamCongNgayCongNhan(maCCCN)));
			}
		});

		scrollDSBangCCCN.setBorder(new TitledBorder(null, "Danh s\u00E1ch b\u1EA3ng ch\u1EA5m c\u00F4ng ng\u00E0y c\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDSBangCCCN.setBounds(10, 41, 604, 169);
		pDanhSachBCCCN.add(scrollDSBangCCCN);


		scrollDSBangCCCN.setViewportView(tableDSCCCN);

		String [] headersChiTietCCCN = "Mã chi tiết;Mã công đoạn;Ca làm việc;Số lượng sản phẩm".split(";");
		tableModelChiTietCCCN = new DefaultTableModel(headersChiTietCCCN, 0);
		JScrollPane scrollDSChiTietCCCN = new JScrollPane(tableChiTietCCCN = new JTable(tableModelChiTietCCCN), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDSChiTietCCCN.setBorder(new TitledBorder(null, "Chi ti\u1EBFt ch\u1EA5m c\u00F4ng c\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDSChiTietCCCN.setBounds(624, 76, 486, 134);
		pDanhSachBCCCN.add(scrollDSChiTietCCCN);

		scrollDSChiTietCCCN.setViewportView(tableChiTietCCCN);

		JButton btnTimDSCCCN = new JButton("Tìm");
		btnTimDSCCCN.setBackground(new Color(255, 204, 153));
		btnTimDSCCCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<BangChamCongNgayCongNhan> list = new ArrayList<BangChamCongNgayCongNhan>();
				if (cboTimDSCCCN.getSelectedItem().equals("theo mã chấm công")) {
					String ma = txtTimDSCCCN.getText();
					list = dsBCCCN.timKiemBangChamCongNgayCN(new BangChamCongNgayCongNhan(ma));
				}

				if (cboTimDSCCCN.getSelectedItem().equals("theo mã nhân viên")) {
					String maNV = txtTimDSCCCN.getText();
					list = dsBCCCN.timKiemBangChamCongNgayCN(new BangChamCongNgayCongNhan("", null, new CongNhan(maNV)));
				}

				if (cboTimDSCCCN.getSelectedItem().equals("theo tên nhân viên")) {
					String tenNV = txtTimDSCCCN.getText();
					List<CongNhan> listCNTK = dsCN.timKiemCN(new CongNhan("", tenNV, "", null, new DonVi(), "", "", 0));
					for (CongNhan cn : listCNTK) {
						ArrayList<BangChamCongNgayCongNhan> listBCCTK = dsBCCCN.timKiemBangChamCongNgayCN(new BangChamCongNgayCongNhan("", null, new CongNhan(cn.getMaNhanVien(), "", "", null, new DonVi(), "", "", 0)));
						for (BangChamCongNgayCongNhan bcc : listBCCTK) {
							list.add(bcc);
						}

					}
				}
				if (cboTimDSCCCN.getSelectedItem().equals("theo ngày chấm công")) {
					list = dsBCCCN.timKiemBangChamCongNgayCN(new BangChamCongNgayCongNhan("", dateTimKiemDSCCCN.getDate(), new CongNhan()));					
				}

				if (!list.isEmpty()) {
					xoaHetTableDSCCCN();
					for (BangChamCongNgayCongNhan s : list) {
						List<CongNhan> listCNTK = dsCN.timKiemCN(new CongNhan(s.getCongNhan().getMaNhanVien()));
						List<DonVi> listDV = dsDonVi.timKiemDonVi(new DonVi(listCNTK.get(0).getDonVi().getMaDonVi()));
						String[] rowData = {s.getMaChamCongNgay(),s.getCongNhan().getMaNhanVien(),listCNTK.get(0).getTenNhanVien()
								, listDV.get(0).getTenDonVi(),formatter.format(s.getNgayChamCong())};
						((DefaultTableModel) tableModelDSCCCN).addRow(rowData);
					}
				}
				else {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Không tìm thấy");
				}
			}
		});
		btnTimDSCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimDSCCCN.setBounds(1015, 11, 95, 29);
		pDanhSachBCCCN.add(btnTimDSCCCN);

		txtTimDSCCCN = new JTextField();
		txtTimDSCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimDSCCCN.setColumns(10);
		txtTimDSCCCN.setBounds(597, 11, 205, 28);
		pDanhSachBCCCN.add(txtTimDSCCCN);

		cboTimDSCCCN = new JComboBox<String>();
		cboTimDSCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimDSCCCN.setBounds(812, 11, 193, 29);
		for (String string : headersDSCCCN) {
			cboTimDSCCCN.addItem("theo " + string.toLowerCase());
		}
		cboTimDSCCCN.removeItem("theo đơn vị");

		cboTimDSCCCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboTimDSCCCN.getSelectedItem().equals("theo ngày chấm công")) {
					txtTimDSCCCN.setVisible(false);
					dateTimKiemDSCCCN.setVisible(true);
				} else {
					txtTimDSCCCN.setVisible(true);
					dateTimKiemDSCCCN.setVisible(false);
				}
			}
		});
		pDanhSachBCCCN.add(cboTimDSCCCN);

		dateTimKiemDSCCCN = new JDateChooser();
		dateTimKiemDSCCCN.setBounds(597, 11, 205, 28);
		dateTimKiemDSCCCN.setDateFormatString("dd-MM-yyyy");
		dateTimKiemDSCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateTimKiemDSCCCN.setVisible(false);
		pDanhSachBCCCN.add(dateTimKiemDSCCCN);

		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableDSBCCCN();
				updateTableCTCC(new ChiTietChamCongCongNhan());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(1008, 45, 102, 30);
		pDanhSachBCCCN.add(btnNewButton);

		JPanel pChamCongNVHC = new JPanel();
		tab.addTab("Chấm công nhân viên hành chánh", pChamCongNVHC);
		pChamCongNVHC.setLayout(null);

		JPanel pThucHienChamCongNVHC = new JPanel();
		pThucHienChamCongNVHC.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pThucHienChamCongNVHC.setBounds(10, 11, 1115, 375);
		pChamCongNVHC.add(pThucHienChamCongNVHC);
		pThucHienChamCongNVHC.setLayout(null);



		String [] headersCCNVHC = "Mã nhân viên;Tên nhân viên;Đơn vị;Ngày chấm công;Đi làm;Làm thêm;Tình trạng".split(";");
		tableModelCCNVHC = new DefaultTableModel(headersCCNVHC, 0) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Boolean.class;
				case 5:
					return Boolean.class;
				case 6:
					return String.class;
				default:
					return String.class;
				}
			}
		};
		JScrollPane scrollChamCongNVHC = new JScrollPane(tableChamCongNVHC = new JTable(tableModelCCNVHC), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableChamCongNVHC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableChamCongNVHC.getSelectedRow();
				Object[] newRow = rowsCCNVHC.get(row);
				newRow[4] = tableChamCongNVHC.getValueAt(row, 4);
				newRow[5] = tableChamCongNVHC.getValueAt(row, 5);
				if (tableChamCongNVHC.isCellSelected(row, 4) || tableChamCongNVHC.isCellSelected(row, 5)) {
					newRow[6] = "Đã chấm công";
					tableModelCCNVHC.setValueAt("Đã chấm công", row, 6);
				}
				rowsCCNVHC.set(row, newRow);
			}
		});

		scrollChamCongNVHC.setBounds(10, 100, 1095, 234);
		pThucHienChamCongNVHC.add(scrollChamCongNVHC);

		scrollChamCongNVHC.setViewportView(tableChamCongNVHC);

		JLabel lblChamCongNVHC = new JLabel("CHẤM CÔNG NHÂN VIÊN HÀNH CHÁNH");
		lblChamCongNVHC.setHorizontalAlignment(SwingConstants.CENTER);
		lblChamCongNVHC.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblChamCongNVHC.setBounds(10, 11, 1100, 31);
		pThucHienChamCongNVHC.add(lblChamCongNVHC);

		JPanel pTimChamCongNVHC = new JPanel();
		pTimChamCongNVHC.setLayout(null);
		pTimChamCongNVHC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm nh\u00E2n vi\u00EAn h\u00E0nh ch\u00E1nh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pTimChamCongNVHC.setBounds(10, 41, 1095, 56);
		pThucHienChamCongNVHC.add(pTimChamCongNVHC);

		cboTimNVHCTheoDV = new JComboBox<String>();
		cboTimNVHCTheoDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimNVHCTheoDV.setBounds(139, 17, 303, 28);
		pTimChamCongNVHC.add(cboTimNVHCTheoDV);

		JLabel lblDonViNVHC = new JLabel("Chọn đơn vị:");
		lblDonViNVHC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDonViNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonViNVHC.setBounds(20, 21, 109, 19);
		pTimChamCongNVHC.add(lblDonViNVHC);

		JDateChooser dateNgayCCNVHC = new JDateChooser();
		dateNgayCCNVHC.setBounds(662, 17, 303, 28);
		dateNgayCCNVHC.setDateFormatString("dd-MM-yyyy");
		dateNgayCCNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimChamCongNVHC.add(dateNgayCCNVHC);

		JButton btnTimNVHC = new JButton("Tìm");
		btnTimNVHC.setBackground(new Color(255, 204, 153));
		btnTimNVHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date ngayCC = dateNgayCCNVHC.getDate();
				String donVi = cboTimNVHCTheoDV.getSelectedItem()+"";
				List<DonVi> listDV = dsDonVi.timKiemDonVi(new DonVi("", donVi));

				List<NhanVienHanhChanh> listNVHC = dsNVHC
						.timKiemNVHC(new NhanVienHanhChanh("", "", "", null
								, new DonVi(listDV.get(0).getMaDonVi()), "", "", "", "", 0));
				if (!listNVHC.isEmpty()) {
					rowsCCNVHC = new ArrayList<Object[]>();
					for (NhanVienHanhChanh nvhc : listNVHC) {
						BangChamCongNgayNhanVienHanhChanh bccNgay = dsCCNgayNVHC
								.timTheoMaNVVaNgayCC(nvhc.getMaNhanVien(), ngayCC);
						if (bccNgay != null) {
							Object[] row = {nvhc.getMaNhanVien()
									, nvhc.getTenNhanVien(), listDV.get(0).getTenDonVi()
									, formatter.format(ngayCC),bccNgay.isCoDilam(),bccNgay.isCoLamThem()
									, "Đã chấm công"}; 
							rowsCCNVHC.add(row);
						} else {
							Object[] row = {nvhc.getMaNhanVien()
									, nvhc.getTenNhanVien(), listDV.get(0).getTenDonVi()
									, formatter.format(ngayCC),true,false, "Chưa chấm công"};
							rowsCCNVHC.add(row);
						}
					}
					updateTableCCNVHCFromList();
				}

			}
		});
		btnTimNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimNVHC.setBounds(975, 17, 110, 27);
		pTimChamCongNVHC.add(btnTimNVHC);

		JLabel lblNgayCCNVHC = new JLabel("Chọn ngày:");
		lblNgayCCNVHC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayCCNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayCCNVHC.setBounds(543, 21, 109, 19);
		pTimChamCongNVHC.add(lblNgayCCNVHC);

		JButton btnLuuCCNVHC = new JButton("Lưu");
		btnLuuCCNVHC.setBackground(Color.CYAN);
		btnLuuCCNVHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Object[] object : rowsCCNVHC) {
					try {
						Date ngayCC = formatter.parse(object[3]+"");
						BangChamCongNgayNhanVienHanhChanh bccNgayTK = dsCCNgayNVHC.timTheoMaNVVaNgayCC(object[0]+"", ngayCC);
						if (bccNgayTK == null) {
							List<BangChamCongNgayNhanVienHanhChanh> listBCCNgay = dsCCNgayNVHC.timTheoMaNVVaThangNam(object[0]+"", ngayCC.getMonth()+1, ngayCC.getYear()+1900);
							BangChamCongNgayNhanVienHanhChanh bccNgay 
							= new BangChamCongNgayNhanVienHanhChanh(setMaCCNVHCNgayTuDong()
									, ngayCC
									, new NhanVienHanhChanh(object[0]+""), Boolean.parseBoolean(object[4]+"")
									, Boolean.parseBoolean(object[5]+"")
									, new BangChamCongThangNhanVienHanhChanh("BCCTNVHC"+object[0]+""
											+(ngayCC.getMonth()+1) + (1900+ngayCC.getYear())));
							if (listBCCNgay.isEmpty()) {
								listBCCNgay.add(bccNgay);
								BangChamCongThangNhanVienHanhChanh bccThang = new BangChamCongThangNhanVienHanhChanh("BCCTNVHC"+object[0]+""
										+(ngayCC.getMonth()+1) + (1900+ngayCC.getYear())
										, new NhanVienHanhChanh(object[0]+""), ngayCC.getMonth()+1, 1900+ngayCC.getYear());
								bccThang.setSoNgayDiLamVaSoBuoiLamThem(listBCCNgay);
								if (dsCCThangNVHC.themBangChamCongThangNVHC(bccThang)) {
									dsCCNgayNVHC.themBangChamCongNgayNVHC(bccNgay);
								}
							} else {
								listBCCNgay.add(bccNgay);
								BangChamCongThangNhanVienHanhChanh bccThang = new BangChamCongThangNhanVienHanhChanh("BCCTNVHC"+object[0]+""
										+(ngayCC.getMonth()+1) + (1900+ngayCC.getYear())
										, new NhanVienHanhChanh(object[0]+""), ngayCC.getMonth()+1, 1900+ngayCC.getYear());
								bccThang.setSoNgayDiLamVaSoBuoiLamThem(listBCCNgay);
								if (dsCCNgayNVHC.themBangChamCongNgayNVHC(bccNgay)) {
									dsCCThangNVHC.capNhatBangChamCongThangNVHC(bccThang);
								}
							}
						} else {
							bccNgayTK.setCoDilam(Boolean.parseBoolean(object[4]+""));
							bccNgayTK.setCoLamThem(Boolean.parseBoolean(object[5]+""));
							if (dsCCNgayNVHC.capNhatBangChamCongNgayNVHC(bccNgayTK)) {
								List<BangChamCongNgayNhanVienHanhChanh> listBCCNgay = dsCCNgayNVHC.timTheoMaNVVaThangNam(object[0]+"", ngayCC.getMonth()+1, ngayCC.getYear()+1900);
								BangChamCongThangNhanVienHanhChanh bccThang = new BangChamCongThangNhanVienHanhChanh("BCCTNVHC"+object[0]+""
										+(ngayCC.getMonth()+1) + (1900+ngayCC.getYear())
										, new NhanVienHanhChanh(object[0]+""), ngayCC.getMonth()+1, 1900+ngayCC.getYear());
								bccThang.setSoNgayDiLamVaSoBuoiLamThem(listBCCNgay);
								dsCCThangNVHC.capNhatBangChamCongThangNVHC(bccThang);
							}
						}
						updateTableDSCCThangNVHC();
						updateTableCCNgayNVHC(new BangChamCongNgayNhanVienHanhChanh());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnLuuCCNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLuuCCNVHC.setBounds(995, 338, 110, 31);
		pThucHienChamCongNVHC.add(btnLuuCCNVHC);

		JPanel pDanhSachBangCCNVHC = new JPanel();
		pDanhSachBangCCNVHC.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pDanhSachBangCCNVHC.setBounds(10, 397, 1115, 224);
		pChamCongNVHC.add(pDanhSachBangCCNVHC);
		pDanhSachBangCCNVHC.setLayout(null);

		JLabel lblTitleDSBCCNVHC = new JLabel("DANH SÁCH BẢNG CHẤM CÔNG NHÂN VIÊN HÀNH CHÁNH\r\n");
		lblTitleDSBCCNVHC.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitleDSBCCNVHC.setBounds(10, 11, 586, 30);
		pDanhSachBangCCNVHC.add(lblTitleDSBCCNVHC);

		txtTimDSCCNVHC = new JTextField();
		txtTimDSCCNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimDSCCNVHC.setColumns(10);
		txtTimDSCCNVHC.setBounds(596, 11, 205, 28);
		pDanhSachBangCCNVHC.add(txtTimDSCCNVHC);

		cboTimDSCCNVHCTheoDV = new JComboBox<String>();
		cboTimDSCCNVHCTheoDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimDSCCNVHCTheoDV.setBounds(596, 11, 205, 28);
		pDanhSachBangCCNVHC.add(cboTimDSCCNVHCTheoDV);
		cboTimDSCCNVHCTheoDV.setVisible(false);

		cboTimDSCCNVHCTheoThang = new JComboBox<String>();
		cboTimDSCCNVHCTheoThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimDSCCNVHCTheoThang.setBounds(596, 11, 100, 28);
		pDanhSachBangCCNVHC.add(cboTimDSCCNVHCTheoThang);
		cboTimDSCCNVHCTheoThang.setVisible(false);
		cboTimDSCCNVHCTheoThang.addItem("--Tháng--");
		for (int i = 1; i <= 12; i++) {
			cboTimDSCCNVHCTheoThang.addItem(i+"");
		}

		cboTimDSCCNVHCTheoNam = new JComboBox<String>();
		cboTimDSCCNVHCTheoNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimDSCCNVHCTheoNam.setBounds(701, 11, 100, 28);
		pDanhSachBangCCNVHC.add(cboTimDSCCNVHCTheoNam);
		cboTimDSCCNVHCTheoNam.setVisible(false);
		cboTimDSCCNVHCTheoNam.addItem("--Năm--");
		for (int i = (new Date().getYear() + 1900); i > 2009; i--) {
			cboTimDSCCNVHCTheoNam.addItem(i+"");
		}

		cboTimDSCCNVHC = new JComboBox<String>();
		cboTimDSCCNVHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboTimDSCCNVHC.getSelectedItem().equals("theo tháng và năm")) {
					cboTimDSCCNVHCTheoDV.setVisible(false);
					txtTimDSCCNVHC.setVisible(false);
					cboTimDSCCNVHCTheoThang.setVisible(true);
					cboTimDSCCNVHCTheoNam.setVisible(true);
				} else if (cboTimDSCCNVHC.getSelectedItem().equals("theo đơn vị")) {
					cboTimDSCCNVHCTheoDV.setVisible(true);
					txtTimDSCCNVHC.setVisible(false);
					cboTimDSCCNVHCTheoThang.setVisible(false);
					cboTimDSCCNVHCTheoNam.setVisible(false);
				} else {
					cboTimDSCCNVHCTheoDV.setVisible(false);
					txtTimDSCCNVHC.setVisible(true);
					cboTimDSCCNVHCTheoThang.setVisible(false);
					cboTimDSCCNVHCTheoNam.setVisible(false);
				}
			}
		});
		cboTimDSCCNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimDSCCNVHC.setBounds(811, 11, 193, 29);
		pDanhSachBangCCNVHC.add(cboTimDSCCNVHC);

		cboTimDSCCNVHC.addItem("theo mã chấm công tháng");
		cboTimDSCCNVHC.addItem("theo mã nhân viên");
		cboTimDSCCNVHC.addItem("theo tên nhân viên");
		cboTimDSCCNVHC.addItem("theo đơn vị");
		cboTimDSCCNVHC.addItem("theo tháng và năm");

		JButton btnTimDSCCNVHC = new JButton("Tìm");
		btnTimDSCCNVHC.setBackground(new Color(255, 204, 153));
		btnTimDSCCNVHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = cboTimDSCCNVHC.getSelectedItem()+"";
				List<BangChamCongThangNhanVienHanhChanh> list = new ArrayList<BangChamCongThangNhanVienHanhChanh>();
				if (choice.equals("theo mã chấm công tháng")) {
					String maCC = txtTimDSCCNVHC.getText().trim();
					list = dsCCThangNVHC.timBangChamCongThangNVHC(new BangChamCongThangNhanVienHanhChanh(maCC));
				}
				if (choice.equals("theo mã nhân viên")) {
					String maNV = txtTimDSCCNVHC.getText().trim();
					list = dsCCThangNVHC.timBangChamCongThangNVHC(new BangChamCongThangNhanVienHanhChanh("", new NhanVienHanhChanh(maNV), 0, 0));
				}
				if (choice.equals("theo đơn vị")) {
					String donVi = cboTimDSCCNVHCTheoDV.getSelectedItem()+"";
					List<DonVi> listDV = dsDonVi.timKiemDonVi(new DonVi("", donVi));
					List<NhanVienHanhChanh> listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh("", "", ""
							, null, new DonVi(listDV.get(0).getMaDonVi()), "", "", "", "", 0));
					for (NhanVienHanhChanh nvhc : listNVHC) {
						List<BangChamCongThangNhanVienHanhChanh> listCCThangNVHC = dsCCThangNVHC.timBangChamCongThangNVHC(new BangChamCongThangNhanVienHanhChanh("", new NhanVienHanhChanh(nvhc.getMaNhanVien()), 0, 0));
						for (BangChamCongThangNhanVienHanhChanh bcc : listCCThangNVHC) {
							list.add(bcc);
						}
					}
				}
				if (choice.equals("theo tên nhân viên")) {
					String tenNV = txtTimDSCCNVHC.getText().trim();
					List<NhanVienHanhChanh> listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(""
							, tenNV, "", null, new DonVi(), "", "", "", "", 0));
					for (NhanVienHanhChanh nvhc : listNVHC) {
						List<BangChamCongThangNhanVienHanhChanh> listCCThangNVHC = dsCCThangNVHC.timBangChamCongThangNVHC(new BangChamCongThangNhanVienHanhChanh("", new NhanVienHanhChanh(nvhc.getMaNhanVien()), 0, 0));
						for (BangChamCongThangNhanVienHanhChanh bcc : listCCThangNVHC) {
							list.add(bcc);
						}

					}
				}
				if (choice.equals("theo tháng và năm")) {
					int thang = 0;
					int nam = 0;
					try {
						thang = Integer.parseInt(cboTimDSCCNVHCTheoThang.getSelectedItem()+"");
						nam = Integer.parseInt(cboTimDSCCNVHCTheoNam.getSelectedItem()+"");
						list = dsCCThangNVHC.timBangChamCongTheoThangVaNam(thang, nam);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Vui lòng chọn đầy đủ tháng năm chấm công cần tìm");
					}
					
				}
				if (!list.isEmpty()) {
					xoaHetTableCCThangNVHC();
					updateTableCCNgayNVHC(new BangChamCongNgayNhanVienHanhChanh());
					for (BangChamCongThangNhanVienHanhChanh s : list) {
						List<NhanVienHanhChanh> listNVHCTK = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(s.getNhanVienHanhChanh().getMaNhanVien()));
						List<DonVi> listDV = dsDonVi.timKiemDonVi(new DonVi(listNVHCTK.get(0).getDonVi().getMaDonVi()));
						String[] rowData = {s.getMaChamCongThang(),s.getNhanVienHanhChanh().getMaNhanVien()
								,listNVHCTK.get(0).getTenNhanVien()
								, listDV.get(0).getTenDonVi(),s.getThang()+"",s.getNam()+"",s.getSoNgayDiLam()+""
								, s.getSoBuoiLamThem()+""};
						tableModelChamCongThangNVHC.addRow(rowData);
					}
				}
				else {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Không tìm thấy");
				}
			}
		});
		btnTimDSCCNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimDSCCNVHC.setBounds(1014, 11, 95, 29);
		pDanhSachBangCCNVHC.add(btnTimDSCCNVHC);

		String [] headersCCNgayNVHC = "Mã chấm công ngày;Ngày chấm công;Đi làm;Làm thêm".split(";");
		tableModelChamCongNgayNVHC = new DefaultTableModel(headersCCNgayNVHC, 0);
		JScrollPane scrollDSBangCCNgayNVHC = new JScrollPane(tableBangCCNgayNVHC = new JTable(tableModelChamCongNgayNVHC), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDSBangCCNgayNVHC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch b\u1EA3ng ch\u1EA5m c\u00F4ng ng\u00E0y nh\u00E2n vi\u00EAn h\u00E0nh ch\u00E1nh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollDSBangCCNgayNVHC.setBounds(702, 76, 408, 137);
		pDanhSachBangCCNVHC.add(scrollDSBangCCNgayNVHC);

		String [] headersCCThangNVHC = "Mã chấm công tháng;Mã nhân viên;Tên nhân viên;Đơn vị;Tháng;Năm;Số ngày đi làm;Số buổi làm thêm".split(";");
		tableModelChamCongThangNVHC = new DefaultTableModel(headersCCThangNVHC, 0);
		JScrollPane scrollDSBangCCThangNVHC = new JScrollPane(tableBangCCThangNVHC = new JTable(tableModelChamCongThangNVHC), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableBangCCThangNVHC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableBangCCThangNVHC.getSelectedRow();
				String maCC = (String) tableBangCCThangNVHC.getValueAt(row, 0);
				List<BangChamCongNgayNhanVienHanhChanh> listCCCN =  dsCCNgayNVHC.timBangChamCongNgayNVHC(new BangChamCongNgayNhanVienHanhChanh("", null, new NhanVienHanhChanh(), true, true, new BangChamCongThangNhanVienHanhChanh(maCC)));
				updateTableCCNgayNVHC(new BangChamCongNgayNhanVienHanhChanh("", null, new NhanVienHanhChanh(), true, true, new BangChamCongThangNhanVienHanhChanh(maCC)));
			}
		});
		scrollDSBangCCThangNVHC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch b\u1EA3ng ch\u1EA5m c\u00F4ng th\u00E1ng nh\u00E2n vi\u00EAn h\u00E0nh ch\u00E1nh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollDSBangCCThangNVHC.setBounds(10, 41, 691, 172);
		pDanhSachBangCCNVHC.add(scrollDSBangCCThangNVHC);
		scrollDSBangCCThangNVHC.setViewportView(tableBangCCThangNVHC);

		JButton btnLamMoiDSNVHC = new JButton("Làm mới");
		btnLamMoiDSNVHC.setBackground(Color.CYAN);
		btnLamMoiDSNVHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableCCNgayNVHC(new BangChamCongNgayNhanVienHanhChanh());
				updateTableDSCCThangNVHC();
			}
		});
		btnLamMoiDSNVHC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoiDSNVHC.setBounds(1007, 45, 102, 29);
		pDanhSachBangCCNVHC.add(btnLamMoiDSNVHC);

		JLabel lblCaLamViec = new JLabel("Ca làm việc:");
		lblCaLamViec.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaLamViec.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCaLamViec.setBounds(0, 75, 109, 20);
		pTimChamCongCN.add(lblCaLamViec);

		cboTimTheoCa = new JComboBox<String>();
		cboTimTheoCa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimTheoCa.setBounds(119, 69, 303, 28);
		cboTimTheoCa.addItem("1");
		cboTimTheoCa.addItem("2");
		cboTimTheoCa.addItem("3");
		pTimChamCongCN.add(cboTimTheoCa);

		updateComboBox();
		updateTableDSBCCCN();
		updateTableDSCCThangNVHC();
		dateNgayCCCN.setDate(new Date());


		dateNgayCCNVHC.setDate(new Date());
	}
	public boolean validDataCCCN() {
		try {
			int soLuong = Integer.parseInt(txtSoLuongSP.getText().trim());
			if (soLuong < 0) {
				lblMessCCCN.setText("Số lượng phải >= 0");
				txtSoLuongSP.requestFocus();
				return false;
			}
		} catch (Exception e) {
			lblMessCCCN.setText("Sai định dạng số lượng");
			txtSoLuongSP.requestFocus();	
			return false;
		}
		return true;
	}
	public void updateComboBox() {
		List<DonVi> listDV = dsDonVi.docDataBase();
		List<CongDoanSanPham> listCD = dsCongDoan.docDataBase();

		for (CongDoanSanPham congDoanSanPham : listCD) {
			cboMaCD.addItem(congDoanSanPham.getMaCongDoan());
		}

		for (DonVi donVi : listDV) {
			List<NhanVienHanhChanh> listNVHC = dsNVHC
					.timKiemNVHC(new NhanVienHanhChanh("", "", "", null
							, new DonVi(donVi.getMaDonVi()), "", "", "", "", 0));
			List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan("", "", "", null
					, new DonVi(donVi.getMaDonVi()), "", "", 0));
			if (!listNVHC.isEmpty()) {
				cboTimNVHCTheoDV.addItem(donVi.getTenDonVi());
				cboTimDSCCNVHCTheoDV.addItem(donVi.getTenDonVi());
			} 
			if (!listCN.isEmpty()) {
				cboTimTheoDV.addItem(donVi.getTenDonVi());				
			}
		}


	}

	public void xoaHetTableCCCN() {
		DefaultTableModel dm = (DefaultTableModel) tableCCCN.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void xoaHetTableDSCCCN() {
		DefaultTableModel dm = (DefaultTableModel) tableDSCCCN.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void xoaHetTableCTCC() {
		DefaultTableModel dm = (DefaultTableModel) tableChiTietCCCN.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void xoaHetTableCCNVHC() {
		DefaultTableModel dm = (DefaultTableModel) tableChamCongNVHC.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void xoaHetTableCCNgayNVHC() {
		DefaultTableModel dm = (DefaultTableModel) tableBangCCNgayNVHC.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void xoaHetTableCCThangNVHC() {
		DefaultTableModel dm = (DefaultTableModel) tableBangCCThangNVHC.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void updateTableBCCCNFromList() {
		xoaHetTableCCCN();
		for (String[] row : rowsCCCN) {
			((DefaultTableModel) tableModelChamCongCN).addRow(row);
		}
		tableCCCN.setModel(tableModelChamCongCN);
	}

	public void updateTableDSBCCCN() {
		xoaHetTableDSCCCN();
		List<BangChamCongNgayCongNhan> list = dsBCCCN.docDataBase();
		List<CongNhan> listCN = new ArrayList<CongNhan>();
		List<DonVi> listDV = new ArrayList<DonVi>();
		for (BangChamCongNgayCongNhan s : list) {
			listCN = dsCN.timKiemCN(new CongNhan(s.getCongNhan().getMaNhanVien()));
			listDV = dsDonVi.timKiemDonVi(new DonVi(listCN.get(0).getDonVi().getMaDonVi()));
			String[] rowData = {s.getMaChamCongNgay(),s.getCongNhan().getMaNhanVien()
					,listCN.get(0).getTenNhanVien(),listDV.get(0).getTenDonVi(),formatter.format(s.getNgayChamCong())};
			((DefaultTableModel) tableModelDSCCCN).addRow(rowData);
		}
		tableDSCCCN.setModel(tableModelDSCCCN);
	}

	public void updateTableCTCC(ChiTietChamCongCongNhan ct) {
		xoaHetTableCTCC();
		List<ChiTietChamCongCongNhan> list = dsCTCCCN.timKiemChiTietCCCN(ct);
		if (list.isEmpty()) {
			String[] rowData = {};
			tableModelChiTietCCCN.addRow(rowData);
		}
		else {
			for (ChiTietChamCongCongNhan s : list) {
				String[] rowData = {s.getMaChiTietCC(),s.getCongDoan().getMaCongDoan(),s.getCaLamViec()+"",s.getSoLuongSP()+""};
				tableModelChiTietCCCN.addRow(rowData);
			}
		}
		tableChiTietCCCN.setModel(tableModelChiTietCCCN);
	}

	public void updateTableDSCCThangNVHC() {
		xoaHetTableCCThangNVHC();
		List<BangChamCongThangNhanVienHanhChanh> dsBCC = dsCCThangNVHC.docDataBase();
		if (dsBCC.isEmpty()) {
			String[] rowData = {};
			tableModelChamCongThangNVHC.addRow(rowData);
		}
		else {
			List<NhanVienHanhChanh> listNVHC = new ArrayList<NhanVienHanhChanh>();
			List<DonVi> listDV = new ArrayList<DonVi>();
			for (BangChamCongThangNhanVienHanhChanh s : dsBCC) {
				listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(s.getNhanVienHanhChanh().getMaNhanVien()));
				listDV = dsDonVi.timKiemDonVi(new DonVi(listNVHC.get(0).getDonVi().getMaDonVi()));
				String[] rowData = {s.getMaChamCongThang(),s.getNhanVienHanhChanh().getMaNhanVien()
						,listNVHC.get(0).getTenNhanVien(), listDV.get(0).getTenDonVi()
						,s.getThang()+"",s.getNam()+""
						,s.getSoNgayDiLam()+"",s.getSoBuoiLamThem()+""};
				tableModelChamCongThangNVHC.addRow(rowData);
			}

		}
		tableBangCCThangNVHC.setModel(tableModelChamCongThangNVHC);
	}

	public void updateTableCCNgayNVHC(BangChamCongNgayNhanVienHanhChanh ccn) {
		xoaHetTableCCNgayNVHC();
		List<BangChamCongNgayNhanVienHanhChanh> list = dsCCNgayNVHC.timBangChamCongNgayNVHC(ccn);
		if (list.isEmpty()) {
			String[] rowData = {};
			tableModelChamCongNgayNVHC.addRow(rowData);
		}
		else {
			for (BangChamCongNgayNhanVienHanhChanh s : list) {
				String[] rowData = {s.getMaChamCongNgay(), formatter.format(s.getNgayChamCong())
						, s.isCoDilam()? "Có":"Không", s.isCoLamThem()? "Có":"Không"};
				tableModelChamCongNgayNVHC.addRow(rowData);
			}
		}
		tableBangCCNgayNVHC.setModel(tableModelChamCongNgayNVHC);
	}

	public String setMaCCCNTuDong() {
		String maCCCN = "BCCNCN";
		int n = dsBCCCN.getTongSoLuongBangChamCongCN()+1;
		while (!dsBCCCN.timKiemBangChamCongNgayCN(new BangChamCongNgayCongNhan(maCCCN+n)).isEmpty()) {
			n++;
		}
		return (maCCCN + n);
	}

	public String setMaCTCCTuDong() {
		String maCT = "CTCCCN";
		int n = dsCTCCCN.getTongSoLuongChiTietCTCCCN()+1;
		while (!dsCTCCCN.timKiemChiTietCCCN(new ChiTietChamCongCongNhan(maCT+n)).isEmpty()) {
			n++;
		}
		return (maCT + n);
	}
	public String setMaCCNVHCNgayTuDong() {
		String maCT = "CCNVHCN";
		int n = dsCCNgayNVHC.getTongSoLuongBangChamCongNgayNVHC()+1;
		while (!dsCCNgayNVHC.timBangChamCongNgayNVHC(new BangChamCongNgayNhanVienHanhChanh(maCT + n)).isEmpty()) {
			n++;
		}
		return (maCT + n);
	}
	public void updateTableCCNVHCFromList() {
		xoaHetTableCCNVHC();
		for (Object[] row : rowsCCNVHC) {
			tableModelCCNVHC.addRow(row);
		}
		tableChamCongNVHC.setModel(tableModelCCNVHC);
	}
}
