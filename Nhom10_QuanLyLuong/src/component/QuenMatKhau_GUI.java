package component;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:10/11/2021
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.org.glassfish.external.statistics.annotations.Reset;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class QuenMatKhau_GUI extends JFrame {

	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtMatKhauXacThucMoi;
	private final JLabel lblNewLabel_1 = new JLabel("");
	// kết nối SQL
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	public String user;

	public QuenMatKhau_GUI(String username) {
		this.user = username;
		initialize();
	}

	public QuenMatKhau_GUI() {
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setTitle("Đổi mật khẩu");
		getContentPane().setForeground(Color.WHITE);
		setBounds(100, 100, 993, 501);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		JLabel lblMatKhauMoi = new JLabel("Nhập mật khẩu mới:");
		lblMatKhauMoi.setForeground(new Color(255, 0, 51));
		lblMatKhauMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhauMoi.setBounds(473, 100, 161, 50);
		getContentPane().add(lblMatKhauMoi);

		JLabel lblXacThucMatKhauMoi = new JLabel("Nhập lại mật khẩu mới:");
		lblXacThucMatKhauMoi.setForeground(new Color(255, 0, 51));
		lblXacThucMatKhauMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblXacThucMatKhauMoi.setBounds(451, 178, 172, 43);
		getContentPane().add(lblXacThucMatKhauMoi);

		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhauMoi.setBounds(633, 109, 240, 36);
		txtMatKhauMoi.setEchoChar('*');
		getContentPane().add(txtMatKhauMoi);
		txtMatKhauMoi.setColumns(10);

		JButton btnXacNhanDoiMatKhau = new JButton("Xác nhận thay đổi");
		btnXacNhanDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnXacNhanDoiMatKhau.setIcon(new ImageIcon("image\\login_circle-512 (1).png"));
		btnXacNhanDoiMatKhau.setBackground(new Color(245, 245, 245));
		btnXacNhanDoiMatKhau.setForeground(new Color(255, 0, 0));
		btnXacNhanDoiMatKhau.setBounds(676, 252, 279, 58);
		getContentPane().add(btnXacNhanDoiMatKhau);
		btnXacNhanDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtMatKhauMoi.getText().equals("")&&txtMatKhauXacThucMoi.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Không được bỏ trống");
				}
				else if (txtMatKhauMoi.getText().equals(txtMatKhauXacThucMoi.getText())) {
					try {
						String updateQuery = "UPDATE Forgotpassjava SET password = ? WHERE username = ?";
						con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=ForgotPassWord",
								"sa", "sapassword");
						pst = con.prepareStatement(updateQuery);
						pst.setString(1, txtMatKhauXacThucMoi.getText());
						pst.setString(2, user);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
						new GuiFormDangNhap().setVisible(true);
						dispose();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);

					}
				} 

				else {
					JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không trùng khớp");
				}

			}
		});

		txtMatKhauXacThucMoi = new JPasswordField();
		txtMatKhauXacThucMoi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMatKhauXacThucMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhauXacThucMoi.setEchoChar('*');
		txtMatKhauXacThucMoi.setBounds(633, 184, 240, 35);
		getContentPane().add(txtMatKhauXacThucMoi);

		JButton btnAnMatKhau = new JButton("");
		btnAnMatKhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ImageIcon showPass = new ImageIcon("image\\eye.png");

				if (!txtMatKhauXacThucMoi.getText().isEmpty() && !txtMatKhauMoi.getText().isEmpty()) {
					txtMatKhauMoi.setEchoChar((char) 0);
					txtMatKhauXacThucMoi.setEchoChar((char) 0);
					btnAnMatKhau.setIcon(showPass);

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				ImageIcon hidePass = new ImageIcon("image\\hidden.png");

				if (!txtMatKhauXacThucMoi.getText().isEmpty() && !txtMatKhauMoi.getText().isEmpty()) {
					txtMatKhauXacThucMoi.setEchoChar('*');
					txtMatKhauMoi.setEchoChar('*');
					btnAnMatKhau.setIcon(hidePass);

				}
			}

		});

		btnAnMatKhau.setIcon(new ImageIcon("image\\hidden.png"));
		btnAnMatKhau.setContentAreaFilled(false);
		btnAnMatKhau.setBorderPainted(false);
		btnAnMatKhau.setBounds(883, 171, 54, 50);
		getContentPane().add(btnAnMatKhau);

		JLabel lblTieuDe = new JLabel("PHẦN MỀM QUẢN LÝ LƯƠNG SAMA");
		lblTieuDe.setForeground(new Color(255, 0, 51));
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTieuDe.setBounds(10, 0, 414, 50);
		getContentPane().add(lblTieuDe);
		lblNewLabel_1.setIcon(new ImageIcon("image\\background_login.jpg"));
		lblNewLabel_1.setBounds(0, 0, 424, 472);
		getContentPane().add(lblNewLabel_1);

		JLabel lblDangNhap = new JLabel("Quên mật khẩu");
		lblDangNhap.setForeground(new Color(255, 0, 51));
		lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDangNhap.setBounds(633, 0, 188, 50);
		getContentPane().add(lblDangNhap);

		JLabel lblbackground = new JLabel("");
		lblbackground.setIcon(new ImageIcon("image\\anh-background-nen-trang-trong-dep .jpg"));
		lblbackground.setBackground(Color.WHITE);
		lblbackground.setBounds(423, 0, 564, 472);
		getContentPane().add(lblbackground);

	}

	public static void main(String[] args) {
		QuenMatKhau_GUI sc = new QuenMatKhau_GUI();
		sc.setVisible(true);
	}
}
