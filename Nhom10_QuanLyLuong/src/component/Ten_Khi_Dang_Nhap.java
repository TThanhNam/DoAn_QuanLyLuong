package component;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:10/11/2021
 */
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import swing.ImageAvatar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Ten_Khi_Dang_Nhap extends JPanel {

	private float alpha;
	public static final ImageAvatar avatar = new ImageAvatar();
	public static final JLabel lblTen = new JLabel();

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public Ten_Khi_Dang_Nhap() {
		setOpaque(false);
		setBackground(new Color(65, 152, 216));
		

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

	

		lblTen.setFont(new Font("Tahoma", Font.BOLD, 18)); 
		lblTen.setForeground(Color.RED);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(10)
					.addComponent(avatar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTen)
					.addContainerGap(321, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(241, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTen)
						.addComponent(avatar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		this.setLayout(layout);

	}

	@Override
	public void paint(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
		super.paint(grphcs);
	}
}
