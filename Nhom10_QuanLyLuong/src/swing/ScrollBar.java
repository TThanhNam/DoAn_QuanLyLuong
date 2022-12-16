package swing;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:16/11/2021
 */
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

import com.raven.swing.ModernScrollBarUI;

public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setBackground(new Color(250, 250, 250));
        setUnitIncrement(20);
    }
}
