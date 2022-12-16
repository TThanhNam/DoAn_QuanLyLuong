package form;
/*
 * Author:Trần Thành Nam
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
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

import com.toedter.calendar.JDateChooser;

import dao.DanhSachCongNhan;
import dao.DanhSachDonVi;
import dao.DanhSachNhanVienHanhChanh;
import entity.CongNhan;
import entity.DonVi;
import entity.NhanVienHanhChanh;
import entity.TienBHXHCongNhan;
import entity.TienBHXHNhanVienHanhChanh;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmNhanVien extends JPanel {
	private JTextField txtTimKiem,txtMaNV,txtTenNV;
	private JTextField txtSoNamKN;
	private JTextField txtTrinhDo;
	private JTextField txtBangCap;
	private JTextField txtSoTK;
	private JTable table;
	private JTable tableNV;
	private TableModel tableModel;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField txtTenNH;
	private JTextField txtSdt;
	private JDateChooser dateNgayCCCN;
	private JTextField txtLuongNgay;
	DanhSachNhanVienHanhChanh nvHCDao = new DanhSachNhanVienHanhChanh();
	DanhSachCongNhan cnDao = new DanhSachCongNhan();
	DanhSachDonVi dvDao = new DanhSachDonVi();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Create the panel.
	 */
	public FrmNhanVien() {
		setBorder(new MatteBorder(0, 5, 5, 5, (Color) null));
		setBackground(new Color(240, 240, 240));
		setPreferredSize(new Dimension(1140, 728));
		setLayout(null);
		
		
		
		JPanel pNhanVien = new JPanel();
		pNhanVien.setBounds(5, 37, 1140, 690);
		add(pNhanVien);
		pNhanVien.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("NHÂN VIÊN");
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
		
		String[] timKiem = {"Theo Mã","Theo Tên","Theo Đơn Vị"};
		JComboBox cboTimKiem = new JComboBox(timKiem);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTimKiem.add(cboTimKiem);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_3.setMinimumSize(new Dimension(10, 0));
		pTimKiem.add(horizontalStrut_3);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(255, 204, 153));
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
		
//		String [] headers1 = {"Mã nhân viên","Tên Nhân viên","Loại nhân viên","Chức vụ","Số điện thoại","Ngày tham gia công tác","Mã đơn vị","Số tài khoản","Tên ngân hàng","Trình độ","Bằng cấp"};		
		JScrollPane scrollNV;
		pTable.add(scrollNV = new JScrollPane(tableNV = new JTable(tableModel), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		loadtieuDe();
		loadData();
		tableNV.setMinimumSize(new Dimension(200, 1600));
		tableNV.setPreferredScrollableViewportSize(new Dimension(1100, 350));
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
		pButton.setLayout(null);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBounds(12, 5, 100, 150);
		verticalBox_1.setBackground(new Color(240, 240, 240));
		verticalBox_1.setPreferredSize(new Dimension(100, 150));
		pButton.add(verticalBox_1);
		
		JButton btnThem = new JButton("Thêm");
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
		
		JPanel pMaNV = new JPanel();
		pMaNV.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pMaNV);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaNV.setPreferredSize(new Dimension(120, 20));
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaNV.add(lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		lblMaNV.setLabelFor(txtMaNV);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaNV.setPreferredSize(new Dimension(200, 20));
		pMaNV.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		JPanel pTenNV = new JPanel();
		pTenNV.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pTenNV);
		
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
		
		JPanel pLoaiNV = new JPanel();
		pLoaiNV.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pLoaiNV);
		
		JLabel lblLoaiNV = new JLabel("Loại nhân viên:");
		lblLoaiNV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoaiNV.setPreferredSize(new Dimension(120, 20));
		lblLoaiNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pLoaiNV.add(lblLoaiNV);
		
		String[] lNV = {"Nhân Viên Hành Chánh","Công Nhân"};
		JComboBox cboLoai = new JComboBox(lNV);
//		cboLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboLoai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cboLoai.getSelectedItem().toString().equals("Nhân Viên Hành Chánh")) {		
					txtSoNamKN.setEditable(false);
					txtBangCap.setEditable(true);
					txtTrinhDo.setEditable(true);
					txtLuongNgay.setEditable(true);
				}
				else {
					txtBangCap.setEditable(false);
					txtTrinhDo.setEditable(false);
					txtLuongNgay.setEditable(false);
					txtSoNamKN.setEditable(true);
				}
			}
		});
		lblLoaiNV.setLabelFor(cboLoai);
		cboLoai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboLoai.setPreferredSize(new Dimension(140, 21));
		pLoaiNV.add(cboLoai);
		
		JPanel pSDT = new JPanel();
		pSDT.setBackground(SystemColor.menu);
		verticalBox_2.add(pSDT);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setPreferredSize(new Dimension(120, 20));
		lblSDT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pSDT.add(lblSDT);
		
		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSdt.setColumns(10);
		txtSdt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pSDT.add(txtSdt);
		
		Box verticalBox_2_1 = Box.createVerticalBox();
		pInfo.add(verticalBox_2_1);
		
		JPanel pNgayCT = new JPanel();
		pNgayCT.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pNgayCT);
		
		JLabel lblNgayCT = new JLabel("Ngày công tác:");
		lblNgayCT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayCT.setPreferredSize(new Dimension(120, 20));
		lblNgayCT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pNgayCT.add(lblNgayCT);
		
		dateNgayCCCN = new JDateChooser();
		dateNgayCCCN.setPreferredSize(new Dimension(140, 25));
		dateNgayCCCN.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		dateNgayCCCN.setDateFormatString("dd-MM-yyyy");
		dateNgayCCCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pNgayCT.add(dateNgayCCCN);
		
		JPanel pDonVi = new JPanel();
		pDonVi.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pDonVi);
		
		JLabel lblDonVi = new JLabel("Đơn vị:");
		lblDonVi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDonVi.setPreferredSize(new Dimension(120, 20));
		lblDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pDonVi.add(lblDonVi);
		ArrayList<DonVi> dv = dvDao.getMaDonVi();
		String[] loai = new String[dv.size()];	
		int i = -1;
		for (DonVi rs : dv) {
			i=i+1;
			loai[i] = rs.getMaDonVi().toString();
		}		
		JComboBox cboLoai_1 = new JComboBox(loai);
		cboLoai_1.setPreferredSize(new Dimension(140, 21));
		cboLoai_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cboLoai_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pDonVi.add(cboLoai_1);
		
		JPanel pSoTaiKhoan = new JPanel();
		pSoTaiKhoan.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pSoTaiKhoan);
		
		JLabel lblSoTK = new JLabel("Số tài khoản:");
		lblSoTK.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoTK.setPreferredSize(new Dimension(120, 20));
		lblSoTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pSoTaiKhoan.add(lblSoTK);
		
		txtSoTK = new JTextField();
		lblSoTK.setLabelFor(txtSoTK);
		txtSoTK.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSoTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoTK.setColumns(10);
		pSoTaiKhoan.add(txtSoTK);
		
		JPanel pNganHang_1 = new JPanel();
		pNganHang_1.setBackground(SystemColor.menu);
		verticalBox_2_1.add(pNganHang_1);
		
		JLabel lblNganHang_1 = new JLabel("Tên ngân hàng:");
		lblNganHang_1.setPreferredSize(new Dimension(120, 20));
		lblNganHang_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNganHang_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pNganHang_1.add(lblNganHang_1);
		
		txtTenNH = new JTextField();
		txtTenNH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNH.setColumns(10);
		txtTenNH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pNganHang_1.add(txtTenNH);
		
		Box verticalBox_2_2 = Box.createVerticalBox();
		pInfo.add(verticalBox_2_2);
		
		JPanel pSoNamKN = new JPanel();
		pSoNamKN.setBackground(new Color(240, 240, 240));
		verticalBox_2_2.add(pSoNamKN);
		
		JLabel lblSoNamKN = new JLabel("Số năm KN:");
		lblSoNamKN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoNamKN.setPreferredSize(new Dimension(120, 20));
		lblSoNamKN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pSoNamKN.add(lblSoNamKN);
		
		txtSoNamKN = new JTextField();
		lblSoNamKN.setLabelFor(txtSoNamKN);
		txtSoNamKN.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSoNamKN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoNamKN.setColumns(10);
		pSoNamKN.add(txtSoNamKN);
		
		JPanel pTrinhDo = new JPanel();
		pTrinhDo.setBackground(new Color(240, 240, 240));
		verticalBox_2_2.add(pTrinhDo);
		
		JLabel lblTrinhDo = new JLabel("Trình độ:");
		lblTrinhDo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrinhDo.setPreferredSize(new Dimension(120, 20));
		lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTrinhDo.add(lblTrinhDo);
		
		txtTrinhDo = new JTextField();
		lblTrinhDo.setLabelFor(txtTrinhDo);
		txtTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTrinhDo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTrinhDo.setColumns(10);
		pTrinhDo.add(txtTrinhDo);
		
		JPanel pBangCap = new JPanel();
		pBangCap.setBackground(new Color(240, 240, 240));
		verticalBox_2_2.add(pBangCap);
		
		JLabel lblBangCap = new JLabel("Bằng cấp:");
		lblBangCap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBangCap.setPreferredSize(new Dimension(120, 20));
		lblBangCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pBangCap.add(lblBangCap);
		
		txtBangCap = new JTextField();
		lblBangCap.setLabelFor(txtBangCap);
		txtBangCap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtBangCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBangCap.setColumns(10);
		pBangCap.add(txtBangCap);
		
		JPanel pLuongNgay = new JPanel();
		pLuongNgay.setBackground(SystemColor.menu);
		verticalBox_2_2.add(pLuongNgay);
		
		JLabel lblLuongNgay = new JLabel("Lương ngày:");
		lblLuongNgay.setPreferredSize(new Dimension(120, 20));
		lblLuongNgay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLuongNgay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pLuongNgay.add(lblLuongNgay);
		
		txtLuongNgay = new JTextField();
		lblLuongNgay.setLabelFor(txtLuongNgay);
		txtLuongNgay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLuongNgay.setColumns(10);
		txtLuongNgay.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pLuongNgay.add(txtLuongNgay);
		setMaNVTuDong();
		//Them
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object loaiNV = cboLoai.getSelectedItem();
					if(loaiNV.toString().endsWith("Nhân Viên Hành Chánh")) {
								
						String maNhanVien = txtMaNV.getText();
						String tenNhanVien = txtTenNV.getText();
						String soDienThoai = txtSdt.getText();
						
						String donVi = cboLoai_1.getSelectedItem().toString();
						String soTaiKhoan = txtSoTK.getText();
						String tenNganHang = txtTenNV.getText();
						String trinhDo = txtTrinhDo.getText();
						String bangCap = txtBangCap.getText();
						double luongCoBan = Double.parseDouble(txtLuongNgay.getText());
						
						NhanVienHanhChanh nv = new NhanVienHanhChanh(maNhanVien, tenNhanVien, soDienThoai, dateNgayCCCN.getDate(), new DonVi(donVi), 
								soTaiKhoan, tenNganHang, trinhDo, bangCap, luongCoBan);
						System.out.println(nvHCDao.themNhanVien(nv));
						
						loadData();
					}
					else {
						String maNhanVien = txtMaNV.getText();
						String tenNhanVien = txtTenNV.getText();
						String soDienThoai = txtSdt.getText();
						
						String donVi = cboLoai_1.getSelectedItem().toString();
						String soTaiKhoan = txtSoTK.getText();
						String tenNganHang = txtTenNV.getText();
						int soNamKM = Integer.parseInt(txtSoNamKN.getText());
						
						CongNhan cn = new CongNhan(maNhanVien, tenNhanVien, soDienThoai, dateNgayCCCN.getDate(), new DonVi(donVi), soTaiKhoan, tenNganHang, soNamKM);
						System.out.println(cnDao.themCongNhan(cn));
						
						loadData();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnThem,"Lỗi Nhập liệu ,kiểm tra các trường dữ liệu và nhập lại");
				}
						
			}
		});	
		////sửa
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object loaiNV = cboLoai.getSelectedItem();
					String nv = txtMaNV.getText();
					if(loaiNV.toString().endsWith("Nhân Viên Hành Chánh")) {
						String maNhanVien = txtMaNV.getText();
						String tenNhanVien = txtTenNV.getText();
						String soDienThoai = txtSdt.getText();
						String donVi = cboLoai_1.getSelectedItem().toString();
						String soTaiKhoan = txtSoTK.getText();
						String tenNganHang = txtTenNV.getText();
						String trinhDo = txtTrinhDo.getText();
						String bangCap = txtBangCap.getText();
						double luongCoBan = Double.parseDouble(txtLuongNgay.getText());
						
						NhanVienHanhChanh nvHC = new NhanVienHanhChanh(maNhanVien, tenNhanVien, soDienThoai, dateNgayCCCN.getDate(), new DonVi(donVi), 
								soTaiKhoan, tenNganHang, trinhDo, bangCap, luongCoBan);
						nvHCDao.capNhatNhanVien(nvHC);
					}else {
						String maNhanVien = txtMaNV.getText();
						String tenNhanVien = txtTenNV.getText();
						String soDienThoai = txtSdt.getText();
						
						String donVi = cboLoai_1.getSelectedItem().toString();
						String soTaiKhoan = txtSoTK.getText();
						String tenNganHang = txtTenNV.getText();
						int soNamKM = Integer.parseInt(txtSoNamKN.getText());
						
						CongNhan cn = new CongNhan(maNhanVien, tenNhanVien, soDienThoai, dateNgayCCCN.getDate(), new DonVi(donVi), soTaiKhoan, tenNganHang, soNamKM);
						
						cnDao.capNhatCongNhan(cn);
					}
					loadData();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnSua,"Lỗi Nhập liệu");
				}
				
			}
		});		
		////xoa
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Object loaiNV = cboLoai.getSelectedItem();
					String nv = txtMaNV.getText();
					if(!nv.equals("")) {
						if(loaiNV.toString().endsWith("Nhân Viên Hành Chánh")) {
							nvHCDao.xoaNhanVien(nv);
							loadData();
						}else {
							cnDao.xoaCongNhan(nv);
							loadData();
						}
					}		
					else
						JOptionPane.showMessageDialog(btnXoa,"Chọn Nhân Viên Cần Xóa Từ Table");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnXoa,"Lỗi Dữ Liệu Nhấn Tải Lại Sửa");
				}
				
			}
		});
		////Tai Lai
		btnTaiLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData();		
				cboLoai.setEnabled(true);
				txtMaNV.setText("");
				txtTenNV.setText("");				
				cboLoai.setSelectedIndex(0);
				txtSdt.setText("");	
				cboLoai_1.setSelectedIndex(0);
				txtSoTK.setText("");
				txtTenNH.setText("");
				txtSoNamKN.setText("");
				txtTrinhDo.setText("");
				txtBangCap.setText("");
				txtLuongNgay.setText("");
				
				txtMaNV.setEditable(true);
				txtBangCap.setEditable(true);
				txtLuongNgay.setEditable(true);
				txtSoNamKN.setEditable(true);
				txtTrinhDo.setEditable(true);
				setMaNVTuDong();
			}
		});
		//"Theo Mã","Theo Tên","Theo Đơn Vị"
		////TimKiem
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object loaiTK = cboTimKiem.getSelectedItem();
					String tk = txtTimKiem.getText();
					
					if(loaiTK.toString().equals("Theo Mã")) {			
						ArrayList<NhanVienHanhChanh> nvHC = nvHCDao.timKiemNVHC(new NhanVienHanhChanh(tk));	
						ArrayList<CongNhan> cn = cnDao.timKiemCN(new CongNhan(tk));
							if(nvHC.size() != 0) {
								xoaHetTable();
								for (NhanVienHanhChanh nv : nvHC) {
									model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Nhân Viên Hành Chánh", nv.getSoDienThoai(), nv.getNgayCT(),
											
											nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), "", 
											nv.getTrinhDo(), nv.getBangCap() ,nv.getLuongNgayCoBan()});
								}
							}
							else if(cn.size() != 0){
								xoaHetTable();
								for (CongNhan nv : cn) {
									model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Công Nhân", nv.getSoDienThoai(), nv.getNgayCT(),
											nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), nv.getSoNamKN()});
								}
								tableNV.setModel(model);
							}
						
						
					}else if(loaiTK.toString().equals("Theo Tên")){
						ArrayList<NhanVienHanhChanh> nvHC = nvHCDao.timKiemNVHC(new NhanVienHanhChanh("", tk, "", null, new DonVi(), "", "", "", "", 0));	
						ArrayList<CongNhan> cn = cnDao.timKiemCN(new CongNhan("", tk, "", null, new DonVi(), "", "", 0));
						if(nvHC.size() != 0) {
							xoaHetTable();
							for (NhanVienHanhChanh nv : nvHC) {
								model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Nhân Viên Hành Chánh", nv.getSoDienThoai(), nv.getNgayCT(),
										
										nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), "", 
										nv.getTrinhDo(), nv.getBangCap() ,nv.getLuongNgayCoBan()});
							}
						}
						else if(cn.size() != 0){
							xoaHetTable();
							for (CongNhan nv : cn) {
								model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Công Nhân", nv.getSoDienThoai(), formatter.format(nv.getNgayCT()),
										nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), nv.getSoNamKN()});
							}
							tableNV.setModel(model);
						}
					}
					else if(loaiTK.toString().equals("Theo Đơn Vị")){
						ArrayList<NhanVienHanhChanh> nvHC = nvHCDao.timKiemNVHC(new NhanVienHanhChanh("", "", "", null, new DonVi(tk), "", "", "", "", 0));	
						ArrayList<CongNhan> cn = cnDao.timKiemCN(new CongNhan("", "", "", null, new DonVi(tk), "", "", 0));
						if(nvHC.size() != 0 && cn.size() != 0) {
							xoaHetTable();
							for (NhanVienHanhChanh nv : nvHC) {
								model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Nhân Viên Hành Chánh", nv.getSoDienThoai(), nv.getNgayCT(),
										
										nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), "", 
										nv.getTrinhDo(), nv.getBangCap() ,nv.getLuongNgayCoBan()});
							}
							for (CongNhan nv : cn) {
								model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Công Nhân", nv.getSoDienThoai(), nv.getNgayCT(),
										nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), nv.getSoNamKN()});
							}
						}
						else if(nvHC.size() != 0) {
							xoaHetTable();
							for (NhanVienHanhChanh nv : nvHC) {
								model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Nhân Viên Hành Chánh", nv.getSoDienThoai(), nv.getNgayCT(),
										
										nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), "", 
										nv.getTrinhDo(), nv.getBangCap() ,nv.getLuongNgayCoBan()});
							}
						}
						else if(cn.size() != 0){
							xoaHetTable();
							for (CongNhan nv : cn) {
								model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Công Nhân", nv.getSoDienThoai(), nv.getNgayCT(),
										nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), nv.getSoNamKN()});
							}
						}
						tableNV.setModel(model);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnTimKiem,"Lỗi");
				}
				
			}
		});
		//// click chuot table
		tableNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaNV.setEditable(false);
				int row = tableNV.getSelectedRow();
				txtMaNV.setText(tableNV.getValueAt(row, 0).toString());
				txtTenNV.setText(tableNV.getValueAt(row, 1).toString());
				
				
				if(tableNV.getValueAt(row, 2).toString().endsWith("Nhân Viên Hành Chánh"))
				{
					
					cboLoai.setSelectedIndex(0);
					cboLoai.setEnabled(false);
					
					txtSoNamKN.setEditable(false);
					txtBangCap.setEditable(true);
					txtTrinhDo.setEditable(true);
					txtLuongNgay.setEditable(true);
				}
				else {
					cboLoai.setSelectedIndex(1);
					cboLoai.setEnabled(false);
					
					txtBangCap.setEditable(false);
					txtTrinhDo.setEditable(false);
					txtLuongNgay.setEditable(false);
					txtSoNamKN.setEditable(true);
				}
				txtSdt.setText(tableNV.getValueAt(row, 3).toString());
				
						
				String da = tableNV.getValueAt(row, 4).toString();
				try {
					Date date =  new SimpleDateFormat("yyyy-MM-dd").parse(da);
					dateNgayCCCN.setDate(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
							
				//tableNV.getValueAt(row, 5)
				cboLoai_1.setSelectedItem(tableNV.getValueAt(row, 5));
				txtSoTK.setText(tableNV.getValueAt(row, 6).toString());
				txtTenNH.setText(tableNV.getValueAt(row, 7).toString());
				txtSoNamKN.setText(tableNV.getValueAt(row, 8).toString());
				txtTrinhDo.setText((String) tableNV.getValueAt(row, 9));
				txtBangCap.setText((String) tableNV.getValueAt(row, 10));
				String tien =  String.valueOf(tableNV.getValueAt(row, 11));
				if(tien != null) {
					txtLuongNgay.setText(tien);
				}				
			}
		});	
	}
private void loadtieuDe() {
	model.addColumn("Mã Nhân Viên");
	model.addColumn("Tên Nhân Viên");
	model.addColumn("Loại Nhân Viên");
	model.addColumn("Số Điện Thoại");
	model.addColumn("Ngày Công Tác");
	model.addColumn("Đơn Vị");
	model.addColumn("Số Tải Khoản");
	model.addColumn("Tên Ngân Hàng");
	model.addColumn("Số Năm KN");
	model.addColumn("Trình Độ");
	model.addColumn("Bằng Cấp");
	model.addColumn("Lương NGày");	
	
	}
//	"Mã nhân viên;Tên Nhân viên;Loại nhân viên;Số điện thoại;Ngày tham gia công tác;Đơn vị;Số tài khoản;Tên ngân hàng;Số năm KN;Trình độ;Bằng cấp;Lương ngày".split(";");
	private void loadData() {
		xoaHetTable();	
		List<NhanVienHanhChanh> nvHC = nvHCDao.docDataBase();
		
		for (NhanVienHanhChanh nv : nvHC) {
			model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Nhân Viên Hành Chánh", nv.getSoDienThoai(), nv.getNgayCT(),
					
					nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), "", nv.getTrinhDo(), nv.getBangCap() ,nv.getLuongNgayCoBan()});
		}
		
		List<CongNhan> cn = cnDao.docDataBase();
		
		for (CongNhan nv : cn) {
			model.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), "Công Nhân", nv.getSoDienThoai(), nv.getNgayCT(),
					nv.getDonVi().getMaDonVi().toString(), nv.getSoTaiKhoan(), nv.getTenNhanHang(), nv.getSoNamKN()});
		}
		tableNV.setModel(model);
	}
	public void xoaHetTable() {
		DefaultTableModel dm = (DefaultTableModel) tableNV.getModel();
		dm.getDataVector().removeAllElements();
	}
	public void setMaNVTuDong() {
		String maNV = "NV";
		int n = cnDao.getTongSoLuongCN() + nvHCDao.getTongSoLuongNVHC() + 1;
		while (!cnDao.timKiemCN(new CongNhan(maNV + n)).isEmpty() ||
				!nvHCDao.timKiemNVHC(new NhanVienHanhChanh(maNV + n)).isEmpty()) {
			n++;
		}
		txtMaNV.setText(maNV + n);
	}


}
