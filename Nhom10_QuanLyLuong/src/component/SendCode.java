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
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class SendCode extends JFrame {

	int randomCode;
	private JFrame frame;
	private JTextField txtEmail;
//	private JTextField txtXacThucCode;
	private JPasswordField txtXacThucCode;
	private final JLabel lblNewLabel_1 = new JLabel("");

	public static void main(String[] args) {
		SendCode sc = new SendCode();
		sc.setVisible(true);
	}

	public SendCode() {
		setResizable(false);
		setTitle("Quên mật khẩu");
		getContentPane().setForeground(Color.WHITE);
		setBounds(100, 100, 993, 501);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		JLabel lblEmail = new JLabel("Nhập email:");
		lblEmail.setForeground(new Color(255, 0, 51));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(480, 99, 138, 50);
		getContentPane().add(lblEmail);

		JLabel lblXacThucCode = new JLabel("Nhập Code:");
		lblXacThucCode.setForeground(new Color(255, 0, 51));
		lblXacThucCode.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblXacThucCode.setBounds(479, 177, 116, 43);
		getContentPane().add(lblXacThucCode);

		txtEmail = new JTextField();
		txtEmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setBounds(592, 109, 240, 36);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JButton btnXacThucCode = new JButton("Xác thực Code");
		btnXacThucCode.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnXacThucCode.setIcon(new ImageIcon("image\\login_circle-512 (1).png"));
		btnXacThucCode.setBackground(new Color(245, 245, 245));
		btnXacThucCode.setForeground(new Color(255, 0, 0));
		btnXacThucCode.setBounds(676, 252, 279, 58);
		getContentPane().add(btnXacThucCode);
		btnXacThucCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(txtXacThucCode.getText()) == randomCode) {
					QuenMatKhau_GUI quenmatkhaugui = new QuenMatKhau_GUI(txtEmail.getText());
					quenmatkhaugui.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Code nhập không hợp lệ");
				}
			}
		});

		txtXacThucCode = new JPasswordField();
		txtXacThucCode.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtXacThucCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtXacThucCode.setEchoChar('*');
		txtXacThucCode.setBounds(592, 184, 240, 35);
		getContentPane().add(txtXacThucCode);

		JButton btnAnMatKhau = new JButton("");
		btnAnMatKhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ImageIcon showPass = new ImageIcon("image\\eye.png");

				if (!txtXacThucCode.getText().isEmpty()) {
					txtXacThucCode.setEchoChar((char) 0);
					btnAnMatKhau.setIcon(showPass);

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				ImageIcon hidePass = new ImageIcon("image\\hidden.png");

				if (!txtXacThucCode.getText().isEmpty()) {
					txtXacThucCode.setEchoChar('*');
					btnAnMatKhau.setIcon(hidePass);

				}
			}

		});

		btnAnMatKhau.setIcon(new ImageIcon("image\\hidden.png"));
		btnAnMatKhau.setContentAreaFilled(false);
		btnAnMatKhau.setBorderPainted(false);
		btnAnMatKhau.setBounds(845, 177, 54, 50);
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

		JButton btnSendCode = new JButton("Gửi Code");
		btnSendCode.setIcon(new ImageIcon("image\\login_circle-512 (1).png"));
		btnSendCode.setForeground(Color.RED);
		btnSendCode.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSendCode.setBackground(Color.WHITE);
		btnSendCode.setBounds(845, 108, 132, 36);
		getContentPane().add(btnSendCode);
		btnSendCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Random rand = new Random();
					randomCode = rand.nextInt(999999);
					String to = txtEmail.getText();
					String subject = "Code thay đổi mật khẩu";
					String msg = "Your Code:" + randomCode;
					final String from = "nhatminh59102@gmail.com";
					final String password = "Minhdeptrai21";

					Properties props = new Properties();
					props.setProperty("mail.transport.protocol", "smtp");
					props.setProperty("mail.host", "smtp.gmail.com");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.port", "465");
					props.put("mail.debug", "true");
					props.put("mail.smtp.socketFactory.port", "465");
					props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					props.put("mail.smtp.socketFactory.fallback", "false");
					Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from, password);
						}
					});

					// session.setDebug(true);
					Transport transport = session.getTransport();
					InternetAddress addressFrom = new InternetAddress(from);

					MimeMessage message = new MimeMessage(session);
					message.setSender(addressFrom);
					message.setSubject(subject);
					message.setContent(msg, "text/plain");
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

					transport.connect();
					Transport.send(message);
					transport.close();

					JOptionPane.showMessageDialog(null, "Code gồm 6 số đã được gửi đến email của bạn");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane, e2);
				}

			}
		});
		
		JButton btnQuayLai = new JButton("Quay lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GuiFormDangNhap().setVisible(true);
				dispose();
			}
		});
		btnQuayLai.setIcon(new ImageIcon("D:\\game\\word_space_eclipse_2020\\QuanLyLuong_Final\\src\\icon_trangchu\\icons8_back_64px.png"));
		btnQuayLai.setForeground(Color.RED);
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnQuayLai.setBackground(new Color(245, 245, 245));
		btnQuayLai.setBounds(769, 370, 218, 102);
		getContentPane().add(btnQuayLai);
		
		JLabel lblbackground = new JLabel("");
		lblbackground.setIcon(new ImageIcon("image\\anh-background-nen-trang-trong-dep .jpg"));
		lblbackground.setBackground(Color.WHITE);
		lblbackground.setBounds(423, 0, 554, 472);
		getContentPane().add(lblbackground);

	}
}
