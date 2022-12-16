package form;
/*
 * Author:Hoàng Huy Vũ
 * Date:16/11/2021
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.DanhSachBHXHCongNhan;
import dao.DanhSachBHXHNhanVienHanhChanh;
import dao.DanhSachCongNhan;
import dao.DanhSachNhanVienHanhChanh;
import dao.DanhSachPhuCapCongNhan;
import dao.DanhSachPhuCapNhanVienHC;

//import DanhSachSanPham;
import entity.BangChamCongNgayCongNhan;
import entity.CongDoanSanPham;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVienHanhChanh;
import entity.PhuCapCongNhan;
import entity.PhuCapNhanVienHanhChanh;
import entity.SanPham;
import entity.TienBHXHCongNhan;
import entity.TienBHXHNhanVienHanhChanh;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmPhuCap extends JPanel {

	private JTextField txtTimKiem;
	private JTextField txtTienPhuCap;
	private JTextField txtMaPhuCap;
	private JTextField txtTenNV;
	private JTable table;
	private TableModel tableModel;
	private JComboBox<String> cboMaNV;
	private DanhSachPhuCapCongNhan danhSachPhuCapCongNhan = new DanhSachPhuCapCongNhan();
	private DanhSachPhuCapNhanVienHC danhSachPhuCapNhanVienHC = new DanhSachPhuCapNhanVienHC();
	private DanhSachCongNhan dsCN = new DanhSachCongNhan();
	private DanhSachNhanVienHanhChanh dsNVHC = new DanhSachNhanVienHanhChanh();
	private JLabel lblMess;
	private JComboBox<Integer> cboThang;
	private JComboBox<Integer> cboNam;

	/**
	 * Create the panel.
	 */
	public FrmPhuCap() {
		setBorder(new MatteBorder(0, 5, 5, 5, (Color) null));
		setBackground(new Color(240, 240, 240));
		setPreferredSize(new Dimension(1140, 660));
		setLayout(new BorderLayout(0, 0));

		JPanel pNhanVien = new JPanel();
		pNhanVien.setLayout(new BorderLayout(0, 0));

		add(pNhanVien, BorderLayout.CENTER);

		JLabel lblTitle = new JLabel("PHỤ CẤP");
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
			public void actionPerformed(ActionEvent e) {
				List<PhuCapCongNhan> listPCCN = new ArrayList<PhuCapCongNhan>();
				List<PhuCapNhanVienHanhChanh> listPCNVHC = new ArrayList<PhuCapNhanVienHanhChanh>();
				if (cboTimKiem.getSelectedItem().equals("theo mã phụ cấp")) {
					String ma = txtTimKiem.getText();
					listPCCN = danhSachPhuCapCongNhan.timKiemPhuCap(new PhuCapCongNhan(ma));
					if (listPCCN.isEmpty())
						listPCNVHC = danhSachPhuCapNhanVienHC.timKiemPhuCap(new PhuCapNhanVienHanhChanh(ma));
				}

				if (cboTimKiem.getSelectedItem().equals("theo tên nhân viên")) {
					String ten = txtTimKiem.getText();
					List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan("", ten, "", null, new DonVi(), "", "", 0));
					for (CongNhan congNhan : listCN) {
						ArrayList<PhuCapCongNhan> listPCCongNhan = danhSachPhuCapCongNhan
								.timKiemPhuCap(new PhuCapCongNhan("", 0, 0, 0, congNhan));
						for (PhuCapCongNhan pc : listPCCongNhan) {
							listPCCN = Arrays.asList(pc);
						}
					}
					List<NhanVienHanhChanh> listNVHC = dsNVHC
							.timKiemNVHC(new NhanVienHanhChanh("", ten, "", null, new DonVi(), "", "", "", "", 0));
					for (NhanVienHanhChanh nvhc : listNVHC) {
						ArrayList<PhuCapNhanVienHanhChanh> listPCNVHC1 = danhSachPhuCapNhanVienHC
								.timKiemPhuCap(new PhuCapNhanVienHanhChanh("", 0, 0, 0, nvhc));
						for (PhuCapNhanVienHanhChanh pc : listPCNVHC1) {
							listPCNVHC = Arrays.asList(pc);
						}
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo tiền phụ cấp")) {
					try {
						double tienPhuCap = Double.parseDouble(txtTimKiem.getText().trim());
						listPCCN = danhSachPhuCapCongNhan
								.timKiemPhuCap(new PhuCapCongNhan("", tienPhuCap, 0, 0, new CongNhan()));
						listPCNVHC = danhSachPhuCapNhanVienHC.timKiemPhuCap(
								new PhuCapNhanVienHanhChanh("", tienPhuCap, 0, 0, new NhanVienHanhChanh()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(),
								"Sai định dạng mục tìm theo tiền phụ cấp");
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo tháng")) {
					try {
						int thang = Integer.parseInt(txtTimKiem.getText().trim());
						listPCCN = danhSachPhuCapCongNhan
								.timKiemPhuCap(new PhuCapCongNhan("", 0, thang, 0, new CongNhan()));
						listPCNVHC = danhSachPhuCapNhanVienHC
								.timKiemPhuCap(new PhuCapNhanVienHanhChanh("", 0, thang, 0, new NhanVienHanhChanh()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Sai định dạng mục tìm theo tháng");
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo năm")) {
					try {
						int nam = Integer.parseInt(txtTimKiem.getText().trim());
						listPCCN = danhSachPhuCapCongNhan
								.timKiemPhuCap(new PhuCapCongNhan("", 0, 0, nam, new CongNhan()));
						listPCNVHC = danhSachPhuCapNhanVienHC
								.timKiemPhuCap(new PhuCapNhanVienHanhChanh("", 0, 0, nam, new NhanVienHanhChanh()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Sai định dạng mục tìm theo năm");
					}
				}

				if (cboTimKiem.getSelectedItem().equals("theo mã nhân viên")) {
					String maNV = txtTimKiem.getText();
					listPCCN = danhSachPhuCapCongNhan
							.timKiemPhuCap(new PhuCapCongNhan("", 0, 0, 0, new CongNhan(maNV)));
					listPCNVHC = danhSachPhuCapNhanVienHC
							.timKiemPhuCap(new PhuCapNhanVienHanhChanh("", 0, 0, 0, new NhanVienHanhChanh(maNV)));

				}

				xoaHetTable();
				if (!listPCCN.isEmpty()) {
					for (PhuCapCongNhan s : listPCCN) {
						List<CongNhan> listCNTK = dsCN.timKiemCN(new CongNhan(s.getCongNhan().getMaNhanVien()));
						String[] rowData = { s.getMaPhuCap(), s.getCongNhan().getMaNhanVien(),
								listCNTK.get(0).getTenNhanVien(), s.getTienPhuCap() + "", s.getThang() + "",
								s.getNam() + "" };
						((DefaultTableModel) tableModel).addRow(rowData);
					}
				} else if (!listPCNVHC.isEmpty()) {
					for (PhuCapNhanVienHanhChanh s : listPCNVHC) {
						List<NhanVienHanhChanh> listCNTK = dsNVHC
								.timKiemNVHC(new NhanVienHanhChanh(s.getNhanVienHanhChanh().getMaNhanVien()));
						String[] rowData = { s.getMaPhuCap(), s.getNhanVienHanhChanh().getMaNhanVien(),
								listCNTK.get(0).getTenNhanVien(), s.getTienPhuCap() + "", s.getThang() + "",
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
//		panel.add(table);

		String[] headers1 = "Mã Phụ Cấp;Mã nhân viên;Tên nhân viên;Tiền phụ cấp ;Tháng;Năm".split(";");
		tableModel = new DefaultTableModel(headers1, 0);
		JScrollPane scroll;
		pTable.add(scroll = new JScrollPane(table = new JTable(tableModel),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String maPhuCap = (String) table.getValueAt(row, 0);
				String maNV = (String) table.getValueAt(row, 1);
				if (isCongNhan(maNV)) {
					ArrayList<PhuCapCongNhan> listCN = danhSachPhuCapCongNhan
							.timKiemPhuCap(new PhuCapCongNhan(maPhuCap));
					for (PhuCapCongNhan x : listCN) {
						txtMaPhuCap.setText(x.getMaPhuCap());
						cboMaNV.setSelectedItem(x.getCongNhan().getMaNhanVien());
						txtTienPhuCap.setText(x.getTienPhuCap() + "");
						cboThang.setSelectedItem(x.getThang());
						cboNam.setSelectedItem(x.getNam());
					}
				} else {
					ArrayList<PhuCapNhanVienHanhChanh> listNVHC = danhSachPhuCapNhanVienHC
							.timKiemPhuCap(new PhuCapNhanVienHanhChanh(maPhuCap));
					for (PhuCapNhanVienHanhChanh x : listNVHC) {
						txtMaPhuCap.setText(x.getMaPhuCap());
						cboMaNV.setSelectedItem(x.getNhanVienHanhChanh().getMaNhanVien());
						txtTienPhuCap.setText(x.getTienPhuCap() + "");
						cboThang.setSelectedItem(x.getThang());
						cboNam.setSelectedItem(x.getNam());
					}
				}
			}
		});
		table.setMinimumSize(new Dimension(200, 1600));
		table.setPreferredScrollableViewportSize(new Dimension(1100, 350));
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
		btnThem.setBorder(null);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
					if (isCongNhan(cboMaNV.getSelectedItem() + "")) {
						PhuCapCongNhan pc = new PhuCapCongNhan(txtMaPhuCap.getText().trim(),
								Double.parseDouble(txtTienPhuCap.getText().trim()),
								Integer.parseInt(cboThang.getSelectedItem() + ""),
								Integer.parseInt(cboNam.getSelectedItem() + ""),
								new CongNhan(cboMaNV.getSelectedItem() + ""));
						if (danhSachPhuCapCongNhan.themPhuCapCongNhan(pc)) {
							updateTableData();
							xoaRong();
							showMess("Thêm thành công", txtMaPhuCap);
						} else
							showMess("Không được trùng mã", txtMaPhuCap);

					} else {
						PhuCapNhanVienHanhChanh pc = new PhuCapNhanVienHanhChanh(
								txtMaPhuCap.getText().trim(), Double.parseDouble(txtTienPhuCap.getText().trim()),
								Integer.parseInt(cboThang.getSelectedItem() + ""),
								Integer.parseInt(cboNam.getSelectedItem() + ""),
								new NhanVienHanhChanh(cboMaNV.getSelectedItem() + ""));
						if (danhSachPhuCapNhanVienHC.themPhuCapNhanVienHC(pc)) {
							updateTableData();
							xoaRong();
							showMess("Thêm thành công", txtMaPhuCap);
						} else
							showMess("Không được trùng mã", txtMaPhuCap);
					}
				}
			}
		});
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
				if (isCongNhan(cboMaNV.getSelectedItem() + "")) {
					PhuCapCongNhan pc = new PhuCapCongNhan(txtMaPhuCap.getText().trim(),
							Double.parseDouble(txtTienPhuCap.getText().trim()),
							Integer.parseInt(cboThang.getSelectedItem() + ""),
							Integer.parseInt(cboNam.getSelectedItem() + ""),
							new CongNhan(cboMaNV.getSelectedItem() + ""));
					if (danhSachPhuCapCongNhan.capNhatTienPhuCap(pc)) {
						updateTableData();
						showMess("Sửa thành công", txtMaPhuCap);
					}
				} else {
					PhuCapNhanVienHanhChanh pc = new PhuCapNhanVienHanhChanh(txtMaPhuCap.getText().trim(),
							Double.parseDouble(txtTienPhuCap.getText().trim()),
							Integer.parseInt(cboThang.getSelectedItem() + ""),
							Integer.parseInt(cboNam.getSelectedItem() + ""),
							new NhanVienHanhChanh(cboMaNV.getSelectedItem() + ""));
					if (danhSachPhuCapNhanVienHC.capNhatTienPhuCap(pc)) {
						updateTableData();
						showMess("Sửa thành công", txtMaPhuCap);
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
					if (isCongNhan(cboMaNV.getSelectedItem() + "")) {
						int row = table.getSelectedRow();
						String ma = (String) table.getValueAt(row, 0);
						if (danhSachPhuCapCongNhan.xoaTienPhuCap(ma)) {
							updateTableData();
							xoaRong();
							showMess("Xóa thành công", txtMaPhuCap);
						}
					} else {
						int row = table.getSelectedRow();
						String ma = (String) table.getValueAt(row, 0);
						if (danhSachPhuCapNhanVienHC.xoaTienPhuCap(ma)) {
							updateTableData();
							xoaRong();
							showMess("Xóa thành công", txtMaPhuCap);
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
		btnTaiLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
			}
		});
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

		JPanel pMaPhuCap = new JPanel();
		pMaPhuCap.setPreferredSize(new Dimension(150, 20));
		pMaPhuCap.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pMaPhuCap);

		JLabel lblMaPhuCap = new JLabel("Mã phụ cấp:");
		lblMaPhuCap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaPhuCap.setPreferredSize(new Dimension(120, 20));
		lblMaPhuCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaPhuCap.add(lblMaPhuCap);

		txtMaPhuCap = new JTextField();
		txtMaPhuCap.setEditable(false);
		lblMaPhuCap.setLabelFor(txtMaPhuCap);
		txtMaPhuCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaPhuCap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaPhuCap.setPreferredSize(new Dimension(200, 20));
		pMaPhuCap.add(txtMaPhuCap);
		txtMaPhuCap.setColumns(10);

		JPanel pMaNV = new JPanel();
		pMaNV.setPreferredSize(new Dimension(150, 20));
		pMaNV.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pMaNV);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaNV.setPreferredSize(new Dimension(120, 20));
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaNV.add(lblMaNV);

		cboMaNV = new JComboBox();
		cboMaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<CongNhan> listCN = dsCN.timKiemCN(new CongNhan(cboMaNV.getSelectedItem() + ""));
				if (!listCN.isEmpty()) {
					txtTenNV.setText(listCN.get(0).getTenNhanVien());
				} else {
					List<NhanVienHanhChanh> listNVHC = dsNVHC
							.timKiemNVHC(new NhanVienHanhChanh(cboMaNV.getSelectedItem() + ""));
					txtTenNV.setText(listNVHC.get(0).getTenNhanVien());
				}
			}
		});
		cboMaNV.setPreferredSize(new Dimension(140, 20));
		cboMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaNV.add(cboMaNV);

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

		JPanel pTienPhuCap = new JPanel();
		pTienPhuCap.setPreferredSize(new Dimension(150, 20));
		pTienPhuCap.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pTienPhuCap);

		JLabel lblTienPhuCap = new JLabel("Tiền Phụ Cấp:");
		lblTienPhuCap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienPhuCap.setPreferredSize(new Dimension(120, 20));
		lblTienPhuCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTienPhuCap.add(lblTienPhuCap);

		txtTienPhuCap = new JTextField();
		lblTienPhuCap.setLabelFor(txtTienPhuCap);
		txtTienPhuCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienPhuCap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTienPhuCap.setColumns(10);
		pTienPhuCap.add(txtTienPhuCap);

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

		// update table
		updateTableData();

		// update combobox
		updateComboBox();

		// phát sinh mã tự động
		setMaPhuCapTuDong();
	}

	public void xoaRong() {
		updateTableData();
		txtTienPhuCap.setText("");
		cboMaNV.setSelectedIndex(0);
		cboNam.setSelectedIndex(0);
		cboThang.setSelectedIndex(0);
		txtTenNV.setText("");
		setMaPhuCapTuDong();
		cboMaNV.requestFocus();
		showMess("", txtMaPhuCap);
	}

	public boolean validData() {
		try {
			double tienPhuCap = Double.parseDouble(txtTienPhuCap.getText().trim());
			if (tienPhuCap < 0) {
				showMess("Tiền phụ cấp phải >= 0", txtTienPhuCap);
				return false;
			}
		} catch (Exception e) {
			showMess("Sai định dạng tiền phụ cấp", txtTienPhuCap);
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
		List<PhuCapNhanVienHanhChanh> listPCNVHC = danhSachPhuCapNhanVienHC.docDataBase();
		List<NhanVienHanhChanh> listNVHC = new ArrayList<NhanVienHanhChanh>();
		if (!listPCNVHC.isEmpty()) {

			for (PhuCapNhanVienHanhChanh s : listPCNVHC) {
				listNVHC = dsNVHC.timKiemNVHC(new NhanVienHanhChanh(s.getNhanVienHanhChanh().getMaNhanVien()));
				String[] rowData = { s.getMaPhuCap(), s.getNhanVienHanhChanh().getMaNhanVien(),
						listNVHC.get(0).getTenNhanVien(), s.getTienPhuCap() + "", s.getThang() + "", s.getNam() + "" };
				((DefaultTableModel) tableModel).addRow(rowData);
			}
		}

		List<PhuCapCongNhan> listPCCN = danhSachPhuCapCongNhan.docDataBase();
		List<CongNhan> listCN = new ArrayList<CongNhan>();
		if (listPCCN.isEmpty()) {
			return;
		}
		for (PhuCapCongNhan s : listPCCN) {
			listCN = dsCN.timKiemCN(new CongNhan(s.getCongNhan().getMaNhanVien()));
			if (!listCN.isEmpty()) {
				String[] rowData = { s.getMaPhuCap(), s.getCongNhan().getMaNhanVien(),
						listCN.get(0).getTenNhanVien(), s.getTienPhuCap() + "", s.getThang() + "", s.getNam() + "" };
			}
			String[] rowData = { s.getMaPhuCap(), s.getCongNhan().getMaNhanVien(), listCN.get(0).getTenNhanVien(),
					s.getTienPhuCap() + "", s.getThang() + "", s.getNam() + "" };

			((DefaultTableModel) tableModel).addRow(rowData);
		}

		table.setModel(tableModel);
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
		cboMaNV.setModel(new javax.swing.DefaultComboBoxModel<String>(items));
	}

	public void setMaPhuCapTuDong() {
		String maPhuCap = "PC";
		int n = danhSachPhuCapCongNhan.getTongSoLuongTienPhuCap()
				+ danhSachPhuCapNhanVienHC.getTongSoLuongTienPhuCap() + 1;
		while (!danhSachPhuCapCongNhan.timKiemPhuCap(new PhuCapCongNhan(maPhuCap + n)).isEmpty()
				|| !danhSachPhuCapNhanVienHC.timKiemPhuCap(new PhuCapNhanVienHanhChanh(maPhuCap  + n))
						.isEmpty()) {
			n++;
		}
		txtMaPhuCap.setText(maPhuCap + n);
	}

	public boolean isCongNhan(String maNV) {
		if (dsCN.timKiemCN(new CongNhan(maNV)).isEmpty())
			return false;
		return true;

	}
}
