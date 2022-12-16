package starting;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:31/10/2021
 */
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import component.GuiFormDangNhap;

public class Starting extends JFrame {

	private JPanel contentPane;
	private JLabel lblphan_tram;
	private JLabel lblLoangding;
	private JProgressBar progressBar;

	public Starting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 451);
		this.setLocationRelativeTo(null);
		setResizable(false);
		this.contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbldiaCD = new JLabel("");
		lbldiaCD.setIcon(new ImageIcon("image\\tenlua.gif"));
		lbldiaCD.setBounds(109, 70, 688, 332);
		contentPane.add(lbldiaCD);

		lblLoangding = new JLabel("Loangding...");
		lblLoangding.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoangding.setBounds(10, 351, 171, 47);
		contentPane.add(lblLoangding);

		JLabel lblTieuDe = new JLabel("PHẦN MỀM QUẢN LÝ LƯƠNG SAMA");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTieuDe.setForeground(new Color(255, 0, 0));
		lblTieuDe.setBounds(79, 0, 618, 59);
		contentPane.add(lblTieuDe);

		lblphan_tram = new JLabel("0 %");
		lblphan_tram.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblphan_tram.setBounds(612, 351, 107, 47);
		contentPane.add(lblphan_tram);
		progressBar = new JProgressBar();
		progressBar.setBounds(0, 408, 719, 14);
		contentPane.add(progressBar);
	}

	public static void main(String[] args) {
		Starting sp = new Starting();
		sp.setVisible(true);

		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(20);
				sp.lblphan_tram.setText(i + "%");
				if (i == 10) {
					sp.lblLoangding.setText("Vui lòng chờ....");
				}
				if (i == 20) {
					sp.lblLoangding.setText("Đang tải...");
				}
				if (i == 50) {
					sp.lblLoangding.setText("Đang kết nối đến server");
				}
				if (i == 70) {
					sp.lblLoangding.setText("Kết nối thành công....");
				}
				if (i == 80) {
					sp.lblLoangding.setText("Khởi động...");
				}
				sp.progressBar.setValue(i);
			}
			new GuiFormDangNhap().setVisible(true);

			sp.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
