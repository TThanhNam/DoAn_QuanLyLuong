package component;

/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:10/11/2021
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import event.EventMenuSelected;
import form.FrmChamCong;
import form.FrmKyLuat;
import form.FrmPhuCap;
import form.FrmTienBHXH;
import form.FrmTinhLuong;
import model.ModelMenu;
import net.miginfocom.swing.MigLayout;

public class Main_KeToan extends javax.swing.JFrame {

	private Menu menu = new Menu();
	private JPanel main = new JPanel();
	private MigLayout layout;
	private Animator animator;
	private boolean menuShow;

	public Main_KeToan() {
		initComponents();
		init();
	}

	private void init() {
		setResizable(false);
		setSize(new Dimension(1366, 728));
		layout = new MigLayout("fill", "0[]0[]0", "0[fill]0");
		body.setLayout(layout);
		main.setOpaque(false);
		main.setLayout(new BorderLayout());

		menu.addEventMenu(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!animator.isRunning()) {
					animator.start();
				}
			}
		});
		// Event Selected Index Item
		menu.setEvent(new EventMenuSelected() {

			@Override
			public void selected(int index) {
//				System.out.println("Selected index "+index);
				if (index == 0) {
					HienThiJpanel(new FrmTienBHXH());
				}

				else if (index == 1) {
					HienThiJpanel(new FrmKyLuat());

				} else if (index == 2) {
					HienThiJpanel(new FrmPhuCap());

				} else if (index == 3) {
					HienThiJpanel(new FrmChamCong());

				} else if (index == 4) {
					HienThiJpanel(new FrmTinhLuong());

				}

			}
		});
		//
		menu.addEventDangXuat(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GuiFormDangNhap().setVisible(true);
				dispose();

			}
		});

		menu.addMenu(new ModelMenu("Quản lý tiền bảo hiểm",
				new ImageIcon(getClass().getResource("/icon_trangchu/icons8_cash_48px.png"))));
		menu.addMenu(new ModelMenu("Quản lý tiền kỷ luật ",
				new ImageIcon(getClass().getResource("/icon_trangchu/icons8_no_cash_48px.png"))));
		menu.addMenu(new ModelMenu("Quản lý tiền phụ cấp ",
				new ImageIcon(getClass().getResource("/icon_trangchu/icons8_money_bag_48px.png"))));
		menu.addMenu(new ModelMenu("Chấm công", new ImageIcon(getClass().getResource("/icon_trangchu/chamcong.png"))));
		menu.addMenu(new ModelMenu("Tính Lương", new ImageIcon(getClass().getResource("/icon_trangchu/report.png"))));
		body.add(menu, "w 50!");
		body.add(main, "w 100%");
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				double width;
				if (menuShow) {
					width = 50 + (150 * (1f - fraction));// ấn để Menu bung vô lại
					menu.setAlpha(1f - fraction);
				} else {
					width = 50 + (150 * fraction);// chiều rộng menu bung ra
					menu.setAlpha(fraction);
				}
				layout.setComponentConstraints(menu, "w " + width + "!");
				body.revalidate();
			}

			@Override
			public void end() {
				menuShow = !menuShow;
			}
		};
		// hiệu ứng menu bung
		animator = new Animator(600, target);
		animator.setResolution(0);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);

	}

	private void HienThiJpanel(Component com) {
		main.removeAll();
		main.add(com);
		main.repaint();
		main.revalidate();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		setTitle("Trang chủ");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Main_KeToan.class.getResource("/icon_trangchu/icondestop.png")));
		body = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setUndecorated(true);

		body.setBackground(new java.awt.Color(245, 245, 245));// màu của background bên phải menu

		javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
		body.setLayout(bodyLayout);
		bodyLayout.setHorizontalGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 1366, Short.MAX_VALUE));
		bodyLayout.setVerticalGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				660, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(body,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
	}//

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main_KeToan.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main_KeToan.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main_KeToan.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main_KeToan.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		// Hàm Main
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main_KeToan().setVisible(true);

			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel body;
	// End of variables declaration//GEN-END:variables
}
