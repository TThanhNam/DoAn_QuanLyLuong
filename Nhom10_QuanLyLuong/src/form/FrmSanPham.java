package form;
/*
 * Author:Nguyễn Võ Vươn Lập
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
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.DanhSachCongDoanSanPham;
import dao.DanhSachSanPham;
import entity.CongDoanSanPham;
import entity.SanPham;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmSanPham extends JPanel {
	private JTextField txtTimKiem, txtTenSP, txtMaSP, txtDonGiaSP, txtTimSP;
	private TableModel tableModelSP;
	private JTable tableSP;
	private JLabel lblMessSP;
	private DanhSachSanPham dsSP = new DanhSachSanPham();

	private JTextField txtMaCD, txtTenCD, txtDonGiaCD, txtTimCD;
	private DefaultTableModel tableModelCD;
	private JTable tableCD;
	private JComboBox cboMaSPCD;
	private JLabel lblMessCD;
	private DanhSachCongDoanSanPham dsCD = new DanhSachCongDoanSanPham();

	/**
	 * Create the panel.
	 */
	public FrmSanPham() {
		setBorder(new MatteBorder(0, 5, 5, 5, (Color) null));
		setBackground(new Color(240, 240, 240));
		setPreferredSize(new Dimension(1140, 660));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pSanPham = new JPanel();
		pSanPham.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(pSanPham, BorderLayout.CENTER);
		pSanPham.setLayout(new BorderLayout(0, 0));

		JPanel pTable = new JPanel();
		pSanPham.add(pTable, BorderLayout.CENTER);

		String [] headersSP = "Mã sản phẩm;Tên sản phẩm;Đơn giá SP".split(";");
		tableModelSP = new DefaultTableModel(headersSP, 0);
		JScrollPane scrollSP;
		pTable.add(scrollSP = new JScrollPane(tableSP = new JTable(tableModelSP), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		tableSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSP = tableSP.getSelectedRow();
				String maSP = (String) tableSP.getValueAt(rowSP, 0);
				ArrayList<SanPham> listSP =  dsSP.timKiemSanPham(new SanPham(maSP, "", 0));
				txtMaSP.setText(maSP);
				txtMaSP.setEditable(false);
				for (SanPham x : listSP) {
					txtTenSP.setText(x.getTenSanPham());
					txtDonGiaSP.setText(x.getDonGiaSP()+"");
					updateTableDataCD(new CongDoanSanPham("","", new SanPham(maSP), 0));
					cboMaSPCD.setSelectedItem(maSP);
				}
			}
		});
		scrollSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollSP.setPreferredSize(new Dimension(810, 270));

		JPanel pHeadingSP = new JPanel();
		pHeadingSP.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pSanPham.add(pHeadingSP, BorderLayout.NORTH);

		JLabel lblTitleSP = new JLabel("SẢN PHẨM");
		lblTitleSP.setPreferredSize(new Dimension(300, 36));
		lblTitleSP.setFont(new Font("Tahoma", Font.BOLD, 25));
		pHeadingSP.add(lblTitleSP);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1_1.setPreferredSize(new Dimension(350, 0));
		pHeadingSP.add(horizontalStrut_1_1);
		
		JPanel pTimKiemSP = new JPanel();
		pTimKiemSP.setPreferredSize(new Dimension(450, 35));
		pHeadingSP.add(pTimKiemSP);
		
		txtTimSP = new JTextField();
		txtTimSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimSP.setColumns(10);
		pTimKiemSP.add(txtTimSP);
		
		Component horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		horizontalStrut_2_1.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_2_1.setMinimumSize(new Dimension(10, 0));
		pTimKiemSP.add(horizontalStrut_2_1);
		
		JComboBox cboTimSP = new JComboBox();
		cboTimSP.setPreferredSize(new Dimension(166, 30));
		cboTimSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimKiemSP.add(cboTimSP);
		
		Component horizontalStrut_3_1 = Box.createHorizontalStrut(20);
		horizontalStrut_3_1.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_3_1.setMinimumSize(new Dimension(10, 0));
		pTimKiemSP.add(horizontalStrut_3_1);
		
		JButton btnTimSP = new JButton("Tìm");
		btnTimSP.setBackground(new Color(255, 204, 153));
		btnTimSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<SanPham> list = new ArrayList<SanPham>();
				if (cboTimSP.getSelectedItem().equals("theo mã sản phẩm")) {
					String ma = txtTimSP.getText();
					list = dsSP.timKiemSanPham(new SanPham(ma, "", 0));
				}

				if (cboTimSP.getSelectedItem().equals("theo tên sản phẩm")) {
					String ten = txtTimSP.getText();
					list = dsSP.timKiemSanPham(new SanPham("", ten, 0));
				}

				if (cboTimSP.getSelectedItem().equals("theo đơn giá sp")) {
					try {
						double donGia = Double.parseDouble(txtTimSP.getText().trim());
						list = dsSP.timKiemSanPham(new SanPham("", "", donGia));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Sai định dạng mục tìm theo đơn giá");
					}
				}

				if (list != null) {
					xoaHetTableSP();
					for (SanPham s : list) {
						String[] rowData = {s.getMaSanPham(),s.getTenSanPham(),s.getDonGiaSP()+""};
						((DefaultTableModel) tableModelSP).addRow(rowData);
					}
				}
				else {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Không tìm thấy");
				}
				
			}
		});
		pTimKiemSP.add(btnTimSP);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_4.setMinimumSize(new Dimension(10, 0));
		pTimKiemSP.add(horizontalStrut_4);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setPreferredSize(new Dimension(310, 120));
		pSanPham.add(verticalBox, BorderLayout.EAST);

		JPanel pDistance = new JPanel();
		pDistance.setPreferredSize(new Dimension(300, 10));
		pDistance.setBackground(SystemColor.menu);
		verticalBox.add(pDistance);

		JPanel pMaSP = new JPanel();
		pMaSP.setPreferredSize(new Dimension(300, 10));
		pMaSP.setBackground(SystemColor.menu);
		verticalBox.add(pMaSP);

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setPreferredSize(new Dimension(120, 20));
		lblMaSP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaSP.add(lblMaSP);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setPreferredSize(new Dimension(200, 20));
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaSP.setColumns(10);
		txtMaSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pMaSP.add(txtMaSP);
		setMaSPTuDong();

		JPanel pTenSP = new JPanel();
		pTenSP.setPreferredSize(new Dimension(300, 10));
		pTenSP.setBackground(SystemColor.menu);
		verticalBox.add(pTenSP);

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setPreferredSize(new Dimension(120, 20));
		lblTenSP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTenSP.add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSP.setColumns(10);
		txtTenSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pTenSP.add(txtTenSP);

		JPanel pDonGiaSP = new JPanel();
		pDonGiaSP.setPreferredSize(new Dimension(300, 10));
		pDonGiaSP.setBackground(SystemColor.menu);
		verticalBox.add(pDonGiaSP);

		JLabel lblDonGiaSP = new JLabel("Đơn giá SP:");
		lblDonGiaSP.setPreferredSize(new Dimension(120, 20));
		lblDonGiaSP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDonGiaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pDonGiaSP.add(lblDonGiaSP);

		txtDonGiaSP = new JTextField();
		txtDonGiaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGiaSP.setColumns(10);
		txtDonGiaSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pDonGiaSP.add(txtDonGiaSP);

		JPanel pMessSP = new JPanel();
		pMessSP.setPreferredSize(new Dimension(300, 10));
		pMessSP.setBackground(SystemColor.menu);
		verticalBox.add(pMessSP);

		lblMessSP = new JLabel("");
		lblMessSP.setForeground(new Color(255, 0, 0));
		lblMessSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessSP.setFont(new Font("Tahoma", Font.ITALIC, 14));
		pMessSP.add(lblMessSP);

		JPanel pButtonSP = new JPanel();
		pButtonSP.setPreferredSize(new Dimension(10, 30));
		pButtonSP.setBackground(SystemColor.menu);
		verticalBox.add(pButtonSP);

		JButton btnThemSP = new JButton("Thêm");
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validDataSP()) {
					SanPham sp = new SanPham(txtMaSP.getText(), txtTenSP.getText(), Double.parseDouble(txtDonGiaSP.getText().trim()));
					if (dsSP.themSanPham(sp)) {
						updateTableDataSP();
						updateComboBox();
						xoaRongSP();
						showMessSP("Thêm thành công", txtMaSP);
					}
					else
						showMessSP("Không được trùng mã", txtMaSP);
				}
			}
		});
		btnThemSP.setPreferredSize(new Dimension(70, 30));
		btnThemSP.setMaximumSize(new Dimension(70, 30));
		btnThemSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThemSP.setBorder(null);
		btnThemSP.setBackground(Color.CYAN);
		pButtonSP.add(btnThemSP);

		JButton btnSuaSP = new JButton("Sửa");
		btnSuaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPham sp = new SanPham(txtMaSP.getText(), txtTenSP.getText(), Double.parseDouble(txtDonGiaSP.getText().trim()));
				if (dsSP.capNhatSanPham(sp)) {
					updateTableDataSP();
					updateComboBox();
					showMessSP("Sửa thành công", txtMaSP);
				}
			}
		});
		btnSuaSP.setPreferredSize(new Dimension(70, 30));
		btnSuaSP.setMaximumSize(new Dimension(70, 30));
		btnSuaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSuaSP.setBorder(null);
		btnSuaSP.setBackground(Color.CYAN);
		pButtonSP.add(btnSuaSP);

		JButton btnXoaSP = new JButton("Xóa");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hoiNhac = JOptionPane.showConfirmDialog((Component) e.getSource(), "Nếu xóa một sản phẩm sẽ xóa tất cả công đoạn của nó","Chu y",JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					int row = tableSP.getSelectedRow();
					String ma = (String) tableSP.getValueAt(row, 0);
					List<CongDoanSanPham> dsCDTK = dsCD.timKiemCongDoan(new CongDoanSanPham("", "", new SanPham(ma), 0));
					for (CongDoanSanPham cd : dsCDTK) {
						dsCD.xoaCongDoan(cd.getMaCongDoan());
						updateTableDataCD(new CongDoanSanPham("", "", new SanPham(ma), 0));
						xoaRongCD();
					}
					if (dsSP.xoaSanPham(ma)) {
						updateTableDataSP();
						xoaRongSP();
						updateComboBox();
						
						showMessSP("Xóa thành công", txtMaSP);
					}
				}
			}
		});
		btnXoaSP.setPreferredSize(new Dimension(70, 30));
		btnXoaSP.setMaximumSize(new Dimension(70, 30));
		btnXoaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoaSP.setBorder(null);
		btnXoaSP.setBackground(Color.CYAN);
		pButtonSP.add(btnXoaSP);
	
		JButton btnTaiLaiSP = new JButton("Tải lại");
		btnTaiLaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongSP();
			}
		});
		btnTaiLaiSP.setPreferredSize(new Dimension(70, 30));
		btnTaiLaiSP.setMaximumSize(new Dimension(70, 30));
		btnTaiLaiSP.setForeground(new Color(0, 0, 51));
		btnTaiLaiSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTaiLaiSP.setBorder(null);
		btnTaiLaiSP.setBackground(Color.CYAN);
		pButtonSP.add(btnTaiLaiSP);

		JPanel pCongDoan = new JPanel();
		pCongDoan.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pCongDoan.setPreferredSize(new Dimension(1140, 310));
		add(pCongDoan, BorderLayout.SOUTH);
		pCongDoan.setLayout(new BorderLayout(0, 0));

		JPanel pHeadingCD = new JPanel();
		pCongDoan.add(pHeadingCD, BorderLayout.NORTH);
		pHeadingCD.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitleCD = new JLabel("CÔNG ĐOẠN SẢN PHẨM");
		lblTitleCD.setPreferredSize(new Dimension(300, 36));
		lblTitleCD.setFont(new Font("Tahoma", Font.BOLD, 25));
		pHeadingCD.add(lblTitleCD);

		Component horizontalStrut_1_1_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1_1_1.setPreferredSize(new Dimension(350, 0));
		pHeadingCD.add(horizontalStrut_1_1_1);
		
		JPanel pTimKiemCD = new JPanel();
		pTimKiemCD.setPreferredSize(new Dimension(450, 35));
		pHeadingCD.add(pTimKiemCD);
		
		txtTimCD = new JTextField();
		txtTimCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimCD.setColumns(10);
		pTimKiemCD.add(txtTimCD);
		
		Component horizontalStrut_2_1_1 = Box.createHorizontalStrut(20);
		horizontalStrut_2_1_1.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_2_1_1.setMinimumSize(new Dimension(10, 0));
		pTimKiemCD.add(horizontalStrut_2_1_1);
		
		JComboBox cboTimCD = new JComboBox();
		cboTimCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimKiemCD.add(cboTimCD);
		
		Component horizontalStrut_3_1_1 = Box.createHorizontalStrut(20);
		horizontalStrut_3_1_1.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_3_1_1.setMinimumSize(new Dimension(10, 0));
		pTimKiemCD.add(horizontalStrut_3_1_1);
		
		JButton btnTimCD = new JButton("Tìm");
		btnTimCD.setBackground(new Color(255, 204, 153));
		btnTimCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<CongDoanSanPham> list = new ArrayList<CongDoanSanPham
						>();
				if (cboTimCD.getSelectedItem().equals("theo mã công đoạn")) {
					String ma = txtTimCD.getText();
					list = dsCD.timKiemCongDoan(new CongDoanSanPham(ma));
				}

				if (cboTimCD.getSelectedItem().equals("theo mã sản phẩm")) {
					String maSP = txtTimCD.getText();
					list = dsCD.timKiemCongDoan(new CongDoanSanPham("", "", new SanPham(maSP), 0));
				}
				
				if (cboTimCD.getSelectedItem().equals("theo tên công đoạn")) {
					String ten = txtTimCD.getText();
					list = dsCD.timKiemCongDoan(new CongDoanSanPham("", ten, new SanPham(), 0));
				}
				
				if (cboTimCD.getSelectedItem().equals("theo đơn giá cd")) {
					try {
						double donGia = Double.parseDouble(txtTimCD.getText().trim());
						list = dsCD.timKiemCongDoan(new CongDoanSanPham("", "", new SanPham(), donGia));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog((Component) e.getSource(), "Sai định dạng mục tìm theo đơn giá");
					}
				}

				if (list != null) {
					xoaHetTableCD();
					for (CongDoanSanPham s : list) {
						String[] rowData = {s.getMaCongDoan(),s.getSanPham().getMaSanPham(),s.getTenCongDoan(),s.getDonGiaCD()+""};
						tableModelCD.addRow(rowData);
					}
				}
				else {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Không tìm thấy");
				}
			}
		});
		pTimKiemCD.add(btnTimCD);
		
		Component horizontalStrut_4_1 = Box.createHorizontalStrut(20);
		horizontalStrut_4_1.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_4_1.setMinimumSize(new Dimension(10, 0));
		pTimKiemCD.add(horizontalStrut_4_1);

		JPanel pTableCD = new JPanel();
		pCongDoan.add(pTableCD, BorderLayout.CENTER);
		tableSP.setMinimumSize(new Dimension(200, 1600));
		tableSP.setPreferredScrollableViewportSize(new Dimension(1100, 350));

		String [] headersCD = "Mã công đoạn;Mã sản phẩm;Tên công đoạn;Đơn giá CD".split(";");
		tableModelCD = new DefaultTableModel(headersCD, 0);
		JScrollPane scrollCD;
		pTableCD.add(scrollCD = new JScrollPane(tableCD = new JTable(tableModelCD), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		tableCD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowCD = tableCD.getSelectedRow();
				String maCD = (String) tableCD.getValueAt(rowCD, 0);
				ArrayList<CongDoanSanPham> listCD =  dsCD.timKiemCongDoan(new CongDoanSanPham(maCD));
				txtMaCD.setText(maCD);
				txtMaCD.setEditable(false);
				for (CongDoanSanPham x : listCD) {
					txtTenCD.setText(x.getTenCongDoan());
					txtDonGiaCD.setText(x.getDonGiaCD()+"");
					cboMaSPCD.setSelectedItem(x.getSanPham().getMaSanPham());
				}
			}
		});
		scrollCD.setBorder(new TitledBorder(null, "Danh s\u00E1ch c\u00F4ng \u0111o\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollCD.setPreferredSize(new Dimension(810, 250));

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setPreferredSize(new Dimension(310, 120));
		pCongDoan.add(verticalBox_1, BorderLayout.EAST);

		JPanel pMaSP_1_5_1_1 = new JPanel();
		pMaSP_1_5_1_1.setPreferredSize(new Dimension(300, 10));
		pMaSP_1_5_1_1.setBackground(SystemColor.menu);
		verticalBox_1.add(pMaSP_1_5_1_1);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pMaSP_1_5_1_1.add(verticalStrut_1);

		JPanel pMaCD = new JPanel();
		pMaCD.setPreferredSize(new Dimension(300, 20));
		pMaCD.setBackground(SystemColor.menu);
		verticalBox_1.add(pMaCD);

		JLabel lblMaCD = new JLabel("Mã công đoạn:");
		lblMaCD.setPreferredSize(new Dimension(120, 20));
		lblMaCD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaCD.add(lblMaCD);

		txtMaCD = new JTextField();
		txtMaCD.setEditable(false);
		txtMaCD.setPreferredSize(new Dimension(200, 20));
		txtMaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaCD.setColumns(10);
		txtMaCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pMaCD.add(txtMaCD);
		setMaCDTuDong();
		
		
		JPanel pMaSPCD = new JPanel();
		pMaSPCD.setPreferredSize(new Dimension(300, 20));
		pMaSPCD.setBackground(SystemColor.menu);
		verticalBox_1.add(pMaSPCD);

		JLabel lblMaSPCD = new JLabel("Mã sản phẩm:");
		lblMaSPCD.setPreferredSize(new Dimension(120, 20));
		lblMaSPCD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaSPCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaSPCD.add(lblMaSPCD);

		cboMaSPCD = new JComboBox();
		cboMaSPCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboMaSPCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboMaSPCD.setPreferredSize(new Dimension(140, 20));
		pMaSPCD.add(cboMaSPCD);

		JPanel pTenCD = new JPanel();
		pTenCD.setPreferredSize(new Dimension(300, 20));
		pTenCD.setBackground(SystemColor.menu);
		verticalBox_1.add(pTenCD);

		JLabel lblTenCD = new JLabel("Tên công đoạn:");
		lblTenCD.setPreferredSize(new Dimension(120, 20));
		lblTenCD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTenCD.add(lblTenCD);

		txtTenCD = new JTextField();
		txtTenCD.setPreferredSize(new Dimension(200, 20));
		txtTenCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenCD.setColumns(10);
		txtTenCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pTenCD.add(txtTenCD);

		JPanel pDonGiaCD = new JPanel();
		pDonGiaCD.setPreferredSize(new Dimension(300, 20));
		pDonGiaCD.setBackground(SystemColor.menu);
		verticalBox_1.add(pDonGiaCD);

		JLabel lblDonGiaCD = new JLabel("Đơn giá CD:");
		lblDonGiaCD.setPreferredSize(new Dimension(120, 20));
		lblDonGiaCD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDonGiaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pDonGiaCD.add(lblDonGiaCD);

		txtDonGiaCD = new JTextField();
		txtDonGiaCD.setPreferredSize(new Dimension(200, 20));
		txtDonGiaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGiaCD.setColumns(10);
		txtDonGiaCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pDonGiaCD.add(txtDonGiaCD);

		JPanel pMessCD = new JPanel();
		pMessCD.setPreferredSize(new Dimension(300, 20));
		pMessCD.setBackground(SystemColor.menu);
		verticalBox_1.add(pMessCD);

		lblMessCD = new JLabel("");
		lblMessCD.setPreferredSize(new Dimension(120, 20));
		lblMessCD.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessCD.setForeground(Color.RED);
		lblMessCD.setFont(new Font("Tahoma", Font.ITALIC, 14));
		pMessCD.add(lblMessCD);

		JPanel pButtonCD = new JPanel();
		pButtonCD.setPreferredSize(new Dimension(300, 20));
		pButtonCD.setBackground(SystemColor.menu);
		verticalBox_1.add(pButtonCD);

		JButton btnThemCD = new JButton("Thêm");
		btnThemCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validDataCD()) {
					CongDoanSanPham cd = new CongDoanSanPham(txtMaCD.getText(), txtTenCD.getText(), new SanPham(cboMaSPCD.getSelectedItem()+""), Double.parseDouble(txtDonGiaCD.getText()));
					if (dsCD.themCongDoan(cd)) {
						updateTableDataCD(new CongDoanSanPham("","", new SanPham(cd.getSanPham().getMaSanPham()), 0));
						xoaRongCD();
						showMessCD("Thêm thành công", txtMaCD);
					}
					else
						showMessCD("Không được trùng mã", txtMaCD);
				}
			}
		});
		btnThemCD.setPreferredSize(new Dimension(70, 30));
		btnThemCD.setMaximumSize(new Dimension(70, 30));
		btnThemCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThemCD.setBorder(null);
		btnThemCD.setBackground(Color.CYAN);
		pButtonCD.add(btnThemCD);

		JButton btnSuaCD = new JButton("Sửa");
		btnSuaCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CongDoanSanPham cd = new CongDoanSanPham(txtMaCD.getText(), txtTenCD.getText(),  new SanPham(cboMaSPCD.getSelectedItem()+""), Double.parseDouble(txtDonGiaCD.getText()));
				if (dsCD.capNhatCongDoan(cd)) {
					updateTableDataCD(new CongDoanSanPham("", "", new SanPham(cd.getSanPham().getMaSanPham()), 0));
					showMessCD("Sửa thành công", txtMaCD);
				}
			}
		});
		btnSuaCD.setPreferredSize(new Dimension(70, 30));
		btnSuaCD.setMaximumSize(new Dimension(70, 30));
		btnSuaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSuaCD.setBorder(null);
		btnSuaCD.setBackground(Color.CYAN);
		pButtonCD.add(btnSuaCD);

		JButton btnXoaCD = new JButton("Xóa");
		btnXoaCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hoiNhac = JOptionPane.showConfirmDialog((Component) e.getSource(),"Co chac chan xoa","Chu y",JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					int row = tableCD.getSelectedRow();
					String maCD = (String) tableCD.getValueAt(row, 0);
					String maSP = (String) tableCD.getValueAt(row, 1);
	 				if (dsCD.xoaCongDoan(maCD)) {
						updateTableDataCD(new CongDoanSanPham("", "", new SanPham(maSP), 0));
						xoaRongCD();
						showMessCD("Xóa thành công", txtMaCD);
					}
				}
			}
		});
		btnXoaCD.setPreferredSize(new Dimension(70, 30));
		btnXoaCD.setMaximumSize(new Dimension(70, 30));
		btnXoaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoaCD.setBorder(null);
		btnXoaCD.setBackground(Color.CYAN);
		pButtonCD.add(btnXoaCD);

		JButton btnTaiLaiCD = new JButton("Tải lại");
		btnTaiLaiCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongCD();
			}
		});
		btnTaiLaiCD.setPreferredSize(new Dimension(70, 30));
		btnTaiLaiCD.setMaximumSize(new Dimension(70, 30));
		btnTaiLaiCD.setForeground(new Color(0, 0, 51));
		btnTaiLaiCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTaiLaiCD.setBorder(null);
		btnTaiLaiCD.setBackground(Color.CYAN);
		pButtonCD.add(btnTaiLaiCD);

		//Thêm cboTimSP
		String s = "";
		for (String str : headersCD) {
			s = str.toLowerCase();
			cboTimCD.addItem("theo "+s);
		}

		
		for (String str : headersSP) {
			s = str.toLowerCase();
			cboTimSP.addItem("theo "+s);
		}
		
		//update table
		updateTableDataSP();
		
		//update combobox
		updateComboBox();
	}
	public void xoaRongCD() {
		txtMaCD.setText("");
		txtTenCD.setText("");
		txtDonGiaCD.setText("");
		txtMaCD.requestFocus();
		setMaCDTuDong();
		txtTenCD.requestFocus();
	}
	
	public void xoaRongSP() {
		updateTableDataSP();
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGiaSP.setText("");
		txtMaSP.requestFocus();
		setMaSPTuDong();
		txtTenSP.requestFocus();
	}
	
	public void showMessSP(String s, JTextField txt) {
		lblMessSP.setText(s);
		txt.requestFocus();
	}
	
	public void showMessCD(String s, JTextField txt) {
		lblMessCD.setText(s);
		txt.requestFocus();
	}
	
	public boolean validDataSP() {
		String ma = txtMaSP.getText().trim();
		if (ma.length() <= 0) {
			showMessSP("Mã sản phẩm không được bỏ trống", txtMaSP);
			return false;
		}
		String tenDonVi = txtTenSP.getText().trim();
		if (tenDonVi.length() <= 0) {
			showMessSP("Tên sản phẩm không được bỏ trống", txtTenSP);
			return false;
		}
		try {
			double donGia = Double.parseDouble(txtDonGiaSP.getText().trim());
			if (donGia < 0) {
				showMessSP("Đơn giá phải >= 0", txtDonGiaSP);
				return false;
			}
		} catch (Exception e) {
			showMessSP("Sai định dạng đơn giá", txtDonGiaSP);
			return false;
		}
		return true;
	}
	
	public boolean validDataCD() {
		String ma = txtMaCD.getText().trim();
		if (ma.length() <= 0) {
			showMessCD("Mã công đoạn không được bỏ trống", txtMaCD);
			return false;
		}
		String tenCongDoan = txtTenCD.getText().trim();
		if (tenCongDoan.length() <= 0) {
			showMessCD("Tên công đoạn không được bỏ trống", txtTenCD);
			return false;
		}
		try {
			double donGia = Double.parseDouble(txtDonGiaCD.getText().trim());
			if (donGia < 0) {
				showMessCD("Đơn giá phải >= 0", txtDonGiaCD);
				return false;
			}
		} catch (Exception e) {
			showMessCD("Sai định dạng đơn giá", txtDonGiaCD);
			return false;
		}
		
		return true;
	}
	
	public void updateComboBox() {
		int i = 0;
		DanhSachSanPham dsSP = new DanhSachSanPham();
		List<SanPham> list = dsSP.docDataBase();
		String[] items = new String[list.size()];  
		for (SanPham s : list) {
			items[i++] = s.getMaSanPham();
		}
		cboMaSPCD.setModel(new javax.swing.DefaultComboBoxModel<String>(items));

	}
	
	public void xoaHetTableSP() {
		DefaultTableModel dm = (DefaultTableModel) tableSP.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void xoaHetTableCD() {
		DefaultTableModel dm = (DefaultTableModel) tableCD.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void updateTableDataSP() {
		xoaHetTableSP();
		List<SanPham> list = dsSP.docDataBase();
		for (SanPham s : list) {
			String[] rowData = {s.getMaSanPham(),s.getTenSanPham(),s.getDonGiaSP()+""};
			((DefaultTableModel) tableModelSP).addRow(rowData);
		}
		tableSP.setModel(tableModelSP);
	}

	public void updateTableDataCD(CongDoanSanPham cd) {
		xoaHetTableCD();
		xoaRongCD();
		List<CongDoanSanPham> list = dsCD.timKiemCongDoan(cd);
		if (list.isEmpty()) {
			String[] rowData = {};
			tableModelCD.addRow(rowData);
		}
		else {
			for (CongDoanSanPham s : list) {
				String[] rowData = {s.getMaCongDoan(),s.getSanPham().getMaSanPham(),s.getTenCongDoan(),s.getDonGiaCD()+""};
				tableModelCD.addRow(rowData);
			}
			
		}
		tableCD.setModel(tableModelCD);
	}
	public void setMaSPTuDong() {
		String maSP = "SP";
		int n = dsSP.getTongSoLuongSanPham()+1;
		while (!dsSP.timKiemSanPham(new SanPham(maSP + n)).isEmpty()) {
			n++;
		}
		txtMaSP.setText(maSP + n);
	}
	
	public void setMaCDTuDong() {
		String maCD = "CD";
		int n = dsCD.getTongSoLuongCongDoan()+1;
		while (!dsCD.timKiemCongDoan(new CongDoanSanPham(maCD + n)).isEmpty()) {
			n++;
		}
		txtMaCD.setText(maCD + n);
	}
}
