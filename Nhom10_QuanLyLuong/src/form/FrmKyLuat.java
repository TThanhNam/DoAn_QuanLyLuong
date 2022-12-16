package form;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:16/11/2021
 */
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.DanhSachCongNhan;
import dao.DanhSachNhanVienHanhChanh;
import dao.DanhSachTienKLCongNhan;
import dao.DanhSachTienKLNhanVienhHanhChanh;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVienHanhChanh;
import entity.TienBHXHCongNhan;
import entity.TienBHXHNhanVienHanhChanh;
import entity.TienKyLuatCongNhan;
import entity.TienKyLuatNhanVienHanhChanh;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;

public class FrmKyLuat extends JPanel {
	private DanhSachCongNhan dsCN = new DanhSachCongNhan();
	private DanhSachNhanVienHanhChanh dsNVHC = new DanhSachNhanVienHanhChanh();
	private DanhSachTienKLCongNhan dsKLCN = new DanhSachTienKLCongNhan();
	private DanhSachTienKLNhanVienhHanhChanh dsKLNVHC = new DanhSachTienKLNhanVienhHanhChanh();

	private JTextField txtTimKiem;
	private JTextField txtTienKyLuat;
	private JTextField txtMaKyLuat;
	private JTextField txtTenNV;
	private JTable table;
	private TableModel tableModel;
	private JComboBox<String> cboMaKL;
	private JLabel lblMess;
	private JComboBox<Integer> cboThang;
	private JComboBox<Integer> cboNam;

	/**
	 * Create the panel.
	 */
	public FrmKyLuat() {
		setBorder(new MatteBorder(0, 5, 5, 5, (Color) null));
		setBackground(new Color(240, 240, 240));
		setPreferredSize(new Dimension(1140, 660));
		setLayout(new BorderLayout(0, 0));

		JPanel pNhanVien = new JPanel();
		pNhanVien.setLayout(new BorderLayout(0, 0));

		add(pNhanVien, BorderLayout.CENTER);

		JLabel lblTitle = new JLabel("TIỀN KỶ LUẬT");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		pNhanVien.add(lblTitle, BorderLayout.NORTH);

		JPanel pCen = new JPanel();
		pCen.setOpaque(false);
		pNhanVien.add(pCen, BorderLayout.SOUTH);
		pCen.setLayout(new BorderLayout(0, 0));

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		verticalBox.setBackground(Color.WHITE);
		verticalBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pCen.add(verticalBox, BorderLayout.CENTER);

		JPanel pTimKiem = new JPanel();
		pTimKiem.setMaximumSize(new Dimension(32767, 20));
		pTimKiem.setMinimumSize(new Dimension(10, 20));
		pTimKiem.setPreferredSize(new Dimension(10, 30));
		verticalBox.add(pTimKiem);
		pTimKiem.setLayout(new BoxLayout(pTimKiem, BoxLayout.X_AXIS));

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(800, 0));
		pTimKiem.add(horizontalStrut_1);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_2.setMinimumSize(new Dimension(10, 0));
		pTimKiem.add(horizontalStrut_2);

		JComboBox cboTimKiem = new JComboBox();
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimKiem.add(cboTimKiem);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_3.setMinimumSize(new Dimension(10, 0));
		pTimKiem.add(horizontalStrut_3);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(255, 204, 153));
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<TienKyLuatCongNhan> listKLCN = new ArrayList<TienKyLuatCongNhan>();
				List<TienKyLuatNhanVienHanhChanh> listKLNVHC = new ArrayList<TienKyLuatNhanVienHanhChanh>();
				if (cboTimKiem.getSelectedItem().equals("theo mã kỷ luật")) {
					String ma = txtTimKiem.getText();
					listKLCN = dsKLCN.timKiemKL(new TienKyLuatCongNhan(ma));
					if (listKLCN.isEmpty())
						listKLNVHC = dsKLNVHC.timKiemKL(new TienKyLuatNhanVienHanhChanh(ma));
				}

				if (cboTimKiem.getSelectedItem().equals("theo tên nhân viên")) {
					String ten = txtTimKiem.getText();
					List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan("", ten, "", null, new DonVi(), "", "", 0));
					for (CongNhan congNhan : listCN) {
						ArrayList<TienKyLuatCongNhan> listKLTK = dsKLCN
								.timKiemKL(new TienKyLuatCongNhan("", 0, 0, 0, congNhan));
						for (TienKyLuatCongNhan kl : listKLTK) {
							listKLCN = Arrays.asList(kl);
						}
					}
					List<NhanVienHanhChanh> listNVHC = dsNVHC
							.timKiemNVHC(new NhanVienHanhChanh("", ten, "", null, new DonVi(), "", "", "", "", 0));
					for (NhanVienHanhChanh nvhc : listNVHC) {
						ArrayList<TienKyLuatNhanVienHanhChanh> listKLTK = dsKLNVHC
								.timKiemKL(new TienKyLuatNhanVienHanhChanh("", 0, 0, 0, nvhc));
						for (TienKyLuatNhanVienHanhChanh kl : listKLTK) {
							listKLNVHC = Arrays.asList(kl);
						}
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo tiền kỷ luật")) {
					try {
						double tienKL = Double.parseDouble(txtTimKiem.getText().trim());
						listKLCN = dsKLCN.timKiemKL(new TienKyLuatCongNhan("", tienKL, 0, 0, new CongNhan()));
						listKLNVHC = dsKLNVHC.timKiemKL(
								new TienKyLuatNhanVienHanhChanh("", tienKL, 0, 0, new NhanVienHanhChanh()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(),
								"Sai định dạng mục tìm theo tiền Kỷ Luật");
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo tháng")) {
					try {
						int thang = Integer.parseInt(txtTimKiem.getText().trim());
						listKLCN = dsKLCN.timKiemKL(new TienKyLuatCongNhan("", 0, thang, 0, new CongNhan()));
						listKLNVHC = dsKLNVHC
								.timKiemKL(new TienKyLuatNhanVienHanhChanh("", 0, thang, 0, new NhanVienHanhChanh()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Sai định dạng mục tìm theo tháng");
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo năm")) {
					try {
						int nam = Integer.parseInt(txtTimKiem.getText().trim());
						listKLCN = dsKLCN.timKiemKL(new TienKyLuatCongNhan("", 0, 0, nam, new CongNhan()));
						listKLNVHC = dsKLNVHC
								.timKiemKL(new TienKyLuatNhanVienHanhChanh("", 0, 0, nam, new NhanVienHanhChanh()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Sai định dạng mục tìm theo năm");
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo mã nhân viên")) {
					String maNV = txtTimKiem.getText();
					listKLCN = dsKLCN.timKiemKL(new TienKyLuatCongNhan("", 0, 0, 0, new CongNhan(maNV)));
					listKLNVHC = dsKLNVHC
							.timKiemKL(new TienKyLuatNhanVienHanhChanh("", 0, 0, 0, new NhanVienHanhChanh(maNV)));

				}

				xoaHetTable();
				if (!listKLCN.isEmpty()) {
					for (TienKyLuatCongNhan s : listKLCN) {
						List<CongNhan> listCNTK = dsCN.timKiemCN(new CongNhan(s.getCongNhan().getMaNhanVien()));
						String[] rowData = { s.getMaKyLuat(), s.getCongNhan().getMaNhanVien(),
								listCNTK.get(0).getTenNhanVien(), s.getTienKyLuat() + "", s.getThang() + "",
								s.getNam() + "" };
						((DefaultTableModel) tableModel).addRow(rowData);
					}
				} else if (!listKLNVHC.isEmpty()) {
					for (TienKyLuatNhanVienHanhChanh s : listKLNVHC) {
						List<NhanVienHanhChanh> listCNTK = dsNVHC
								.timKiemNVHC(new NhanVienHanhChanh(s.getNhanVienHanhChanh().getMaNhanVien()));
						String[] rowData = { s.getMaKyLuat(), s.getNhanVienHanhChanh().getMaNhanVien(),
								listCNTK.get(0).getTenNhanVien(), s.getTienKyLuat() + "", s.getThang() + "",
								s.getNam() + "" };
						((DefaultTableModel) tableModel).addRow(rowData);
					}
				} else {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Không tìm thấy");
				}
				
			}
		});
		pTimKiem.add(btnTimKiem);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(10, 0));
		horizontalStrut.setMinimumSize(new Dimension(10, 0));
		pTimKiem.add(horizontalStrut);

		JPanel pTable = new JPanel();
		verticalBox.add(pTable);

		table = new JTable();
		table.setPreferredSize(new Dimension(1100, 100));
		table.setMaximumSize(new Dimension(200, 100));
		String[] headers1 = "Mã kỷ luật;Mã nhân viên;Tên nhân viên;Tiền kỷ luật;Tháng;Năm".split(";");
		tableModel = new DefaultTableModel(headers1, 0);
		JScrollPane scroll;
		pTable.add(scroll = new JScrollPane(table = new JTable(tableModel),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		table.setMinimumSize(new Dimension(200, 1600));
		table.setPreferredScrollableViewportSize(new Dimension(1100, 350));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String maKL = (String) table.getValueAt(row, 0);
				String maNV = (String) table.getValueAt(row, 1);
				if (isCongNhan(maNV)) {
					ArrayList<TienKyLuatCongNhan> listCN = dsKLCN.timKiemKL(new TienKyLuatCongNhan(maKL));
					for (TienKyLuatCongNhan x : listCN) {
						txtMaKyLuat.setText(x.getMaKyLuat());
						cboMaKL.setSelectedItem(x.getCongNhan().getMaNhanVien());
						txtTienKyLuat.setText(x.getTienKyLuat() + "");
						cboThang.setSelectedItem(x.getThang());
						cboNam.setSelectedItem(x.getNam());
					}
				} else {
					ArrayList<TienKyLuatNhanVienHanhChanh> listNVHC = dsKLNVHC
							.timKiemKL(new TienKyLuatNhanVienHanhChanh(maKL));
					for (TienKyLuatNhanVienHanhChanh x : listNVHC) {
						txtMaKyLuat.setText(x.getMaKyLuat());
						cboMaKL.setSelectedItem(x.getNhanVienHanhChanh().getMaNhanVien());
						txtTienKyLuat.setText(x.getTienKyLuat() + "");
						cboThang.setSelectedItem(x.getThang());
						cboNam.setSelectedItem(x.getNam());
					}
				}
				
			}
		});
		Component verticalStrut_4 = Box.createVerticalStrut(43);
		verticalStrut_4.setPreferredSize(new Dimension(0, 5));
		pCen.add(verticalStrut_4, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		splitPane.setBackground(new Color(192, 192, 192));
		splitPane.setPreferredSize(new Dimension(179, 180));
		splitPane.setDividerSize(0);
		pNhanVien.add(splitPane, BorderLayout.CENTER);

		JPanel pButton = new JPanel();
		pButton.setBackground(new Color(240, 240, 240));
		pButton.setPreferredSize(new Dimension(10, 140));
		splitPane.setRightComponent(pButton);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBackground(new Color(240, 240, 240));
		verticalBox_1.setPreferredSize(new Dimension(100, 150));
		pButton.add(verticalBox_1);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
					if (isCongNhan(cboMaKL.getSelectedItem() + "")) {
						TienKyLuatCongNhan kl = new TienKyLuatCongNhan(txtMaKyLuat.getText().trim(),
								Double.parseDouble(txtTienKyLuat.getText().trim()),
								Integer.parseInt(cboThang.getSelectedItem() + ""),
								Integer.parseInt(cboNam.getSelectedItem() + ""),
								new CongNhan(cboMaKL.getSelectedItem() + ""));
						if (dsKLCN.themTienKLCN(kl)) {
							updateTableData();
							xoaRong();
							showMess("Thêm thành công", txtMaKyLuat);
						} else
							showMess("Không được trùng mã", txtMaKyLuat);

					} else {
						TienKyLuatNhanVienHanhChanh kl = new TienKyLuatNhanVienHanhChanh(txtMaKyLuat.getText().trim(),
								Double.parseDouble(txtTienKyLuat.getText().trim()),
								Integer.parseInt(cboThang.getSelectedItem() + ""),
								Integer.parseInt(cboNam.getSelectedItem() + ""),
								new NhanVienHanhChanh(cboMaKL.getSelectedItem() + ""));
						if (dsKLNVHC.themTienKLNVHC(kl)) {
							updateTableData();
							xoaRong();
							showMess("Thêm thành công", txtMaKyLuat);
						} else
							showMess("Không được trùng mã", txtMaKyLuat);
					}
			}
			}
		});
		btnThem.setBorder(null);
		btnThem.setBackground(Color.CYAN);
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setMaximumSize(new Dimension(100, 30));
		btnThem.setPreferredSize(new Dimension(80, 23));
		verticalBox_1.add(btnThem);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setMaximumSize(new Dimension(32767, 10));
		verticalStrut.setMinimumSize(new Dimension(0, 5));
		verticalStrut.setPreferredSize(new Dimension(0, 10));
		verticalBox_1.add(verticalStrut);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isCongNhan(cboMaKL.getSelectedItem() + "")) {
					TienKyLuatCongNhan kl = new TienKyLuatCongNhan(txtMaKyLuat.getText().trim(),
							Double.parseDouble(txtTienKyLuat.getText().trim()),
							Integer.parseInt(cboThang.getSelectedItem() + ""),
							Integer.parseInt(cboNam.getSelectedItem() + ""),
							new CongNhan(cboMaKL.getSelectedItem() + ""));
					if (dsKLCN.capNhatTienKL(kl)) {
						updateTableData();
						showMess("Sửa thành công", txtMaKyLuat);
					}
				} else {
					TienKyLuatNhanVienHanhChanh kl = new TienKyLuatNhanVienHanhChanh(txtMaKyLuat.getText().trim(),
							Double.parseDouble(txtTienKyLuat.getText().trim()),
							Integer.parseInt(cboThang.getSelectedItem() + ""),
							Integer.parseInt(cboNam.getSelectedItem() + ""),
							new NhanVienHanhChanh(cboMaKL.getSelectedItem() + ""));
					if (dsKLNVHC.capNhatTienKL(kl)) {
						updateTableData();
						showMess("Sửa thành công", txtMaKyLuat);
					}
				}
			}
		});
		btnSua.setBackground(Color.CYAN);
		btnSua.setBorder(null);
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setMaximumSize(new Dimension(100, 30));
		verticalBox_1.add(btnSua);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 10));
		verticalStrut_1.setMinimumSize(new Dimension(0, 5));
		verticalStrut_1.setMaximumSize(new Dimension(32767, 10));
		verticalBox_1.add(verticalStrut_1);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hoiNhac = JOptionPane.showConfirmDialog((Component) e.getSource(), "Có chắc chắn xóa", "Chú ý",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					if (isCongNhan(cboMaKL.getSelectedItem() + "")) {
						int row = table.getSelectedRow();
						String ma = (String) table.getValueAt(row, 0);
						if (dsKLCN.xoaKL(ma)) {
							updateTableData();
							xoaRong();
							showMess("Xóa thành công", txtMaKyLuat);
						}
					} else {
						int row = table.getSelectedRow();
						String ma = (String) table.getValueAt(row, 0);
						if (dsKLNVHC.xoaKL(ma)) {
							updateTableData();
							xoaRong();
							showMess("Xóa thành công", txtMaKyLuat);
						}
					}

				}
			}
		});
		btnXoa.setBorder(null);
		btnXoa.setBackground(Color.CYAN);
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setMaximumSize(new Dimension(100, 30));
		verticalBox_1.add(btnXoa);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setPreferredSize(new Dimension(0, 10));
		verticalStrut_2.setMinimumSize(new Dimension(0, 5));
		verticalStrut_2.setMaximumSize(new Dimension(32767, 10));
		verticalBox_1.add(verticalStrut_2);

		JButton btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setForeground(new Color(0, 0, 51));
		btnTaiLai.setBackground(Color.CYAN);
		btnTaiLai.setBorder(null);
		btnTaiLai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTaiLai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTaiLai.setMaximumSize(new Dimension(100, 30));
		verticalBox_1.add(btnTaiLai);

		JPanel pInfo = new JPanel();
		pInfo.setPreferredSize(new Dimension(1000, 130));
		splitPane.setLeftComponent(pInfo);
		pInfo.setLayout(new BoxLayout(pInfo, BoxLayout.X_AXIS));

		Box verticalBox_2 = Box.createVerticalBox();
		pInfo.add(verticalBox_2);

		JPanel pDistanceUp1 = new JPanel();
		pDistanceUp1.setPreferredSize(new Dimension(150, 20));
		pDistanceUp1.setBackground(SystemColor.menu);
		verticalBox_2.add(pDistanceUp1);

		JPanel pMaBHXH = new JPanel();
		pMaBHXH.setPreferredSize(new Dimension(150, 20));
		pMaBHXH.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pMaBHXH);

		JLabel lblMaKL = new JLabel("Mã kỷ luật:");
		lblMaKL.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaKL.setPreferredSize(new Dimension(120, 20));
		lblMaKL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaBHXH.add(lblMaKL);

		txtMaKyLuat = new JTextField();
		txtMaKyLuat.setEditable(false);
		lblMaKL.setLabelFor(txtMaKyLuat);
		txtMaKyLuat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKyLuat.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaKyLuat.setPreferredSize(new Dimension(200, 20));
		pMaBHXH.add(txtMaKyLuat);
		txtMaKyLuat.setColumns(10);

		JPanel pMaNV = new JPanel();
		pMaNV.setPreferredSize(new Dimension(150, 20));
		pMaNV.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pMaNV);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaNV.setPreferredSize(new Dimension(120, 20));
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaNV.add(lblMaNV);

		cboMaKL = new JComboBox();
		cboMaKL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan(cboMaKL.getSelectedItem() + ""));
				if (!listCN.isEmpty()) {
					txtTenNV.setText(listCN.get(0).getTenNhanVien());
				} else {
					List<NhanVienHanhChanh> listNVHC = dsNVHC
							.timKiemNVHC(new NhanVienHanhChanh(cboMaKL.getSelectedItem() + ""));
					txtTenNV.setText(listNVHC.get(0).getTenNhanVien());
				}
			}
		});
		cboMaKL.setPreferredSize(new Dimension(140, 20));
		cboMaKL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaNV.add(cboMaKL);

		JPanel pMess = new JPanel();
		pMess.setPreferredSize(new Dimension(150, 20));
		pMess.setBackground(SystemColor.menu);
		verticalBox_2.add(pMess);

		lblMess = new JLabel("");
		lblMess.setPreferredSize(new Dimension(300, 20));
		lblMess.setMaximumSize(new Dimension(1000, 14));
		lblMess.setForeground(Color.RED);
		lblMess.setFont(new Font("Tahoma", Font.ITALIC, 14));
		pMess.add(lblMess);

		Box verticalBox_2_1 = Box.createVerticalBox();
		pInfo.add(verticalBox_2_1);

		JPanel pDistanceUp2 = new JPanel();
		pDistanceUp2.setPreferredSize(new Dimension(150, 20));
		pDistanceUp2.setBackground(SystemColor.menu);
		verticalBox_2_1.add(pDistanceUp2);

		JPanel pTenNV = new JPanel();
		pTenNV.setPreferredSize(new Dimension(150, 20));
		pTenNV.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pTenNV);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenNV.setPreferredSize(new Dimension(120, 20));
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTenNV.add(lblTenNV);

		txtTenNV = new JTextField();
		lblTenNV.setLabelFor(txtTenNV);
		txtTenNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNV.setColumns(10);
		pTenNV.add(txtTenNV);

		JPanel pTienBHXH = new JPanel();
		pTienBHXH.setPreferredSize(new Dimension(150, 20));
		pTienBHXH.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pTienBHXH);

		JLabel lblTienKL = new JLabel("Tiền Kỷ Luật:");
		lblTienKL.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienKL.setPreferredSize(new Dimension(120, 20));
		lblTienKL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTienBHXH.add(lblTienKL);

		txtTienKyLuat = new JTextField();
		lblTienKL.setLabelFor(txtTienKyLuat);
		txtTienKyLuat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienKyLuat.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTienKyLuat.setColumns(10);
		pTienBHXH.add(txtTienKyLuat);

		JPanel pDistanceDown2 = new JPanel();
		pDistanceDown2.setPreferredSize(new Dimension(150, 20));
		pDistanceDown2.setBackground(SystemColor.menu);
		verticalBox_2_1.add(pDistanceDown2);

		Box verticalBox_2_2 = Box.createVerticalBox();
		pInfo.add(verticalBox_2_2);

		JPanel pDistanceUp3 = new JPanel();
		pDistanceUp3.setPreferredSize(new Dimension(150, 20));
		pDistanceUp3.setBackground(SystemColor.menu);
		verticalBox_2_2.add(pDistanceUp3);

		JPanel pThang = new JPanel();
		pThang.setPreferredSize(new Dimension(150, 20));
		pThang.setBackground(new Color(240, 240, 240));
		verticalBox_2_2.add(pThang);

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThang.setPreferredSize(new Dimension(120, 20));
		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pThang.add(lblThang);

		cboThang = new JComboBox();
		cboThang.setPreferredSize(new Dimension(140, 20));
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pThang.add(cboThang);

		JPanel pNam = new JPanel();
		pNam.setPreferredSize(new Dimension(150, 20));
		pNam.setBackground(new Color(240, 240, 240));
		verticalBox_2_2.add(pNam);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNam.setPreferredSize(new Dimension(120, 20));
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pNam.add(lblNam);

		cboNam = new JComboBox();
		cboNam.setPreferredSize(new Dimension(140, 20));
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pNam.add(cboNam);

		JPanel pDistanceDown3 = new JPanel();
		pDistanceDown3.setPreferredSize(new Dimension(150, 20));
		pDistanceDown3.setBackground(SystemColor.menu);
		verticalBox_2_2.add(pDistanceDown3);

		String s = "";
		for (String str : headers1) {
			s = str.toLowerCase();
			cboTimKiem.addItem("theo " + s);
		}

		for (int i = 1; i < 13; i++) {
			cboThang.addItem(i);
		}

		for (int i = (1900 + new Date().getYear()); i > 2009; i--) {
			cboNam.addItem(i);
		}
		updateTableData();
		updateComboBox();
		setMaKLTuDong();
	}
	//hàm 
	public void xoaRong() {
		updateTableData();
		txtTienKyLuat.setText("");
		cboMaKL.setSelectedIndex(0);
		cboNam.setSelectedIndex(0);
		cboThang.setSelectedIndex(0);
		txtTenNV.setText("");
		setMaKLTuDong();
		cboMaKL.requestFocus();
		showMess("", txtMaKyLuat);
	}
	public boolean validData() {
		try {
			double tienKL = Double.parseDouble(txtTienKyLuat.getText().trim());
			if (tienKL < 0) {
				showMess("Tiền Kỷ Luật phải >= 0", txtTienKyLuat);
				return false;
			}
		} catch (Exception e) {
			showMess("Sai định dạng tiền Kỷ Luật", txtTienKyLuat);
			return false;
		}
		return true;
	}
	public void showMess(String s, JTextField txt) {
		lblMess.setText(s);
		txt.requestFocus();
	}
	public void xoaHetTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
	}
	public void updateTableData() {
		xoaHetTable();
		List<TienKyLuatNhanVienHanhChanh> listKLNVHC = dsKLNVHC.docDataBase();
		List<NhanVienHanhChanh> listNVHC = new ArrayList<NhanVienHanhChanh>();
		if (!listKLNVHC.isEmpty()) {

			for (TienKyLuatNhanVienHanhChanh s : listKLNVHC) {
				listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(s.getNhanVienHanhChanh().getMaNhanVien()));
				String[] rowData = { s.getMaKyLuat(), s.getNhanVienHanhChanh().getMaNhanVien(),
						listNVHC.get(0).getTenNhanVien(), s.getTienKyLuat() + "", s.getThang() + "", s.getNam() + "" };
				((DefaultTableModel) tableModel).addRow(rowData);
			}
		}

		List<TienKyLuatCongNhan> listKLCN = dsKLCN.docDataBase();
		List<CongNhan> listCN = new ArrayList<CongNhan>();
		if (listKLCN.isEmpty()) {
			return;
		}
		for (TienKyLuatCongNhan s : listKLCN) {
			listCN = dsCN.timKiemCN(new CongNhan(s.getCongNhan().getMaNhanVien()));
			if (listCN.isEmpty()) {
				String[] rowData = { s.getMaKyLuat(), s.getCongNhan().getMaNhanVien(), listCN.get(0).getTenNhanVien(),
						s.getTienKyLuat() + "", s.getThang() + "", s.getNam() + "" };
			}
			String[] rowData = { s.getMaKyLuat(), s.getCongNhan().getMaNhanVien(), listCN.get(0).getTenNhanVien(),
					s.getTienKyLuat() + "", s.getThang() + "", s.getNam() + "" };

			((DefaultTableModel) tableModel).addRow(rowData);
		}

		table.setModel(tableModel);
	}
	public boolean isCongNhan(String maNV) {
		if (dsCN.timKiemCN(new CongNhan(maNV)).isEmpty())
			return false;
		return true;

	}
	public void updateComboBox() {
		int i = 0;
		DanhSachNhanVienHanhChanh dsNVHC = new DanhSachNhanVienHanhChanh();
		List<NhanVienHanhChanh> listNVHC = dsNVHC.docDataBase();
		DanhSachCongNhan dsCN = new DanhSachCongNhan();
		List<CongNhan> listCN = dsCN.docDataBase();
		String[] items = new String[listNVHC.size() + listCN.size()];
		for (NhanVienHanhChanh s : listNVHC) {
			items[i++] = s.getMaNhanVien();
		}
		for (CongNhan s : listCN) {
			items[i++] = s.getMaNhanVien();
		}
		cboMaKL.setModel(new javax.swing.DefaultComboBoxModel<String>(items));
	}
	public void setMaKLTuDong() {
		String maKL = "KL";
		int n = dsKLCN.getTongSoLuongKyLuatCN() + dsKLNVHC.getTongSoLuongKyLuatNVHC() + 1;
		while (!dsKLCN.timKiemKL(new TienKyLuatCongNhan(maKL + n)).isEmpty()
				|| !dsKLNVHC.timKiemKL(new TienKyLuatNhanVienHanhChanh(maKL + n)).isEmpty()) {
			n++;
		}
		txtMaKyLuat.setText(maKL + n);
	}
	

}
