package swing;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:16/11/2021
 */
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class ButtonDangXuat extends JButton {
public ButtonDangXuat() {
	setContentAreaFilled(false);
	setCursor(new Cursor(Cursor.HAND_CURSOR));
	setBackground(new Color(65, 152,216));
	
}
@Override
	public void paint(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(10, 10, getWidth()-20, getHeight()-20, 20,20);
		super.paint(g);
	}
}
