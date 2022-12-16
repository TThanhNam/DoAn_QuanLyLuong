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
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

import dao.DanhSachDonVi;
import entity.DonVi;
import entity.TienBHXHCongNhan;
import entity.TienBHXHNhanVienHanhChanh;

public class FrmDonVi extends JPanel {
	private JTextField txtTimKiem;
	private JTextField txtMaDV;
	//private JTextField txtMaNV;
	//private JTextField txtThang;
	//private JTextField txtNam;
	private JTextField txtTenDV;
	//private JTextField txtTienBHXH;
	
	private JTable table_1;
	private TableModel tableModel;
	private DefaultTableModel model = new DefaultTableModel();
	DanhSachDonVi dvDao = new DanhSachDonVi();
	/**
	 * Create the panel.
	 */
	public FrmDonVi() {
		
		setBorder(new MatteBorder(0, 5, 5, 5, (Color) null));
		setBackground(new Color(240, 240, 240));
		setPreferredSize(new Dimension(1140, 660));
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel pNhanVien = new JPanel();
		pNhanVien.setLayout(new BorderLayout(0, 0));
		
		add(pNhanVien, BorderLayout.CENTER);
		
		JLabel lblTitle = new JLabel("ĐƠN VỊ");
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
		
		String[] cbotk = {"TimTheoMa","TimTheoTen"};
		JComboBox cboTimKiem = new JComboBox(cbotk);
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
		
//		panel.add(table);
		
//		String [] headers1 = {"Mã nhân viên","Tên Nhân viên","Loại nhân viên","Chức vụ","Số điện thoại","Ngày tham gia công tác","Mã đơn vị","Số tài khoản","Tên ngân hàng","Trình độ","Bằng cấp"};
//		String [] headers1 = "Mã đơn vị;Tên đơn vị".split(";");
//		tableModel = new DefaultTableModel(headers1, 0);
		JScrollPane scroll;
		pTable.add(scroll = new JScrollPane(table_1 = new JTable(tableModel), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		loatTieuDe();
		loadData();
		table_1.setMinimumSize(new Dimension(200, 1600));
		table_1.setPreferredScrollableViewportSize(new Dimension(1100, 350));
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
		
		JPanel pDistanceUp1 = new JPanel();
		pDistanceUp1.setPreferredSize(new Dimension(150, 20));
		pDistanceUp1.setBackground(SystemColor.menu);
		verticalBox_2.add(pDistanceUp1);
		
		JPanel pMaDonVi = new JPanel();
		pMaDonVi.setPreferredSize(new Dimension(150, 20));
		pMaDonVi.setBackground(new Color(240, 240, 240));
		verticalBox_2.add(pMaDonVi);
		
		JLabel lblMaDonVi = new JLabel("Mã đơn vị:");
		lblMaDonVi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaDonVi.setPreferredSize(new Dimension(120, 20));
		lblMaDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pMaDonVi.add(lblMaDonVi);
		
		txtMaDV = new JTextField();
		txtMaDV.setEditable(false);
		lblMaDonVi.setLabelFor(txtMaDV);
		txtMaDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaDV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaDV.setPreferredSize(new Dimension(200, 20));
		pMaDonVi.add(txtMaDV);
		txtMaDV.setColumns(10);
		
		JPanel pMess = new JPanel();
		pMess.setPreferredSize(new Dimension(150, 20));
		pMess.setBackground(SystemColor.menu);
		verticalBox_2.add(pMess);
		
		JLabel lblMess = new JLabel("");
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
		
		JPanel pTenDV = new JPanel();
		pTenDV.setPreferredSize(new Dimension(150, 20));
		pTenDV.setBackground(new Color(240, 240, 240));
		verticalBox_2_1.add(pTenDV);
		
		JLabel lblTenDV = new JLabel("Tên đơn vị:");
		lblTenDV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenDV.setPreferredSize(new Dimension(120, 20));
		lblTenDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pTenDV.add(lblTenDV);
		
		txtTenDV = new JTextField();
		lblTenDV.setLabelFor(txtTenDV);
		txtTenDV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTenDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenDV.setColumns(10);
		pTenDV.add(txtTenDV);	
		
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
			
		JPanel pDistanceDown3 = new JPanel();
		pDistanceDown3.setPreferredSize(new Dimension(150, 20));
		pDistanceDown3.setBackground(SystemColor.menu);
		verticalBox_2_2.add(pDistanceDown3);
		setMaDonViTuDong();
		
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDonVi = txtMaDV.getText();
				String tenDonVi = txtTenDV.getText();
				DonVi dv = new DonVi(maDonVi, tenDonVi);
				System.out.println(dvDao.themDonVi(dv));
				loadData();
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDonVi = txtMaDV.getText();
				dvDao.xoaDonVi(maDonVi);
				txtMaDV.setText("");
				txtTenDV.setText("");
				loadData();
			}
		});
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDonVi = txtMaDV.getText();
				String tenDonVi = txtTenDV.getText();
				DonVi dv = new DonVi(maDonVi, tenDonVi);
				dvDao.capNhatDonVi(dv);
				loadData();
			}
		});
		btnTaiLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaDV.setText("");
				txtTenDV.setText("");
				txtMaDV.setEditable(true);
				loadData();
				setMaDonViTuDong();
			}
		});
		//"TimTheoMa","TimTheoTen"
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object loaiTK = cboTimKiem.getSelectedItem();
				String tk = txtTimKiem.getText();
				
				if(loaiTK.toString().equals("TimTheoMa")) {			
					ArrayList<DonVi> dv = dvDao.timKiemDonVi(new DonVi(tk));
						if(dv.size() != 0) {
							xoaHetTable();
							model.getDataVector().removeAllElements();
							for (DonVi rs : dv) {
								model.addRow(new Object[] {rs.getMaDonVi(),rs.getTenDonVi()});
							}
						}
						else {
							
						}			
				}else if(loaiTK.toString().equals("TimTheoTen")){
					ArrayList<DonVi> dv = dvDao.timKiemDonVi(new DonVi("", tk));
					if(dv.size() != 0) {
						xoaHetTable();
						model.getDataVector().removeAllElements();
						for (DonVi rs : dv) {
							
							model.addRow(new Object[] {rs.getMaDonVi(),rs.getTenDonVi()});
						}
					}
					else {
						
					}			
				}
			}
		});
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaDV.setEditable(false);
				int row = table_1.getSelectedRow();
				txtMaDV.setText(table_1.getValueAt(row, 0).toString());
				txtTenDV.setText(table_1.getValueAt(row, 1).toString());
			}
		});
	}

	private void loatTieuDe() {		
		model.addColumn("Mã Đơn Vị");
		model.addColumn("Tên Đơn Vị");
		
	}
	private void loadData() {
		xoaHetTable();
		ArrayList<DonVi> dv = dvDao.docDataBase();
		for (DonVi rs : dv) {
			model.addRow(new Object[] {rs.getMaDonVi(),rs.getTenDonVi()});
		}
		table_1.setModel(model);	
	}
	public void xoaHetTable() {
		DefaultTableModel dm = (DefaultTableModel) table_1.getModel();
		dm.getDataVector().removeAllElements();
	}
	public void setMaDonViTuDong() {
		String maDV = "DV";
		int n = dvDao.getTongSoLuongDonVi()+ 1;
		while (!dvDao.timKiemDonVi(new DonVi(maDV + n)).isEmpty() )
				 {
			n++;
		}
		txtMaDV.setText(maDV + n);
	}
}
