package model;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:10/11/2021
 */
import javax.swing.Icon;

public class ModelMenu {

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelMenu(String menuName, Icon icon) {
        this.menuName = menuName;
        this.icon = icon;
        
    }

    public ModelMenu() {
    }

    private String menuName;
    private Icon icon;
}
