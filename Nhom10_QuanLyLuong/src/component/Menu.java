package component;
/*
 * Author:Nguyễn Lâm Nhật Minh
 * Date:10/11/2021
 */
import event.EventMenuSelected;
import model.ModelMenu;
import swing.ButtonDangXuat;
import swing.MenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;

import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    private MigLayout layout;
    private JPanel panelMenu;
    private JButton btnMenu;
    private JButton btnLogOut;
    private TenPhanMem header;
    private Ten_Khi_Dang_Nhap tenKhiDangNhap;
    private EventMenuSelected event;
    
    

	public void setEvent(EventMenuSelected event) {
		this.event = event;
	}

	public Menu() {
        initComponents();
        setOpaque(false);
        init();
        
    }

    private void init() {
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "1[]0[]push[60]0"));
        panelMenu = new JPanel();
        header=new TenPhanMem();
       
        tenKhiDangNhap=new Ten_Khi_Dang_Nhap();
        createButtonMenu();
        createButtonDangXuat();
        panelMenu.setOpaque(false);
        layout = new MigLayout("fillx, wrap", "0[fill]0", "0[]15[]0[]0[]0");//code chỉnh lên xuống
        panelMenu.setLayout(layout);
        add(btnMenu, "pos 1al 0al 100% 50");
        add(btnLogOut,"pos 1al 1al   100% 100, height 65!");
        add(header);
        add(panelMenu);
        add(tenKhiDangNhap);
    }

    public void addMenu(ModelMenu menu) {
        MenuItem item = new MenuItem(menu.getIcon(), menu.getMenuName(), panelMenu.getComponentCount());
        item.addEvent(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                clearMenu(index);
            }
        });
        item.addEvent(event);
        panelMenu.add(item);
    }

    private void createButtonMenu() {
        btnMenu = new JButton();
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);//làm cho icon menu hoà vào viền
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMenu.setIcon(new ImageIcon(getClass().getResource("/icon_trangchu/menu.png")));
        btnMenu.setBorder(new EmptyBorder(6,13,6,13));// tạo khoảng cách trên ,trái , dưới ,phải so với lề
    }
    public void createButtonDangXuat() {
		btnLogOut=new ButtonDangXuat();
		btnLogOut.setIcon(new ImageIcon(getClass().getResource("/icon_trangchu/exit.png")));
		btnLogOut.setBorderPainted(false);
	}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, Color.decode("#56CCF2"), 0, getHeight(), Color.decode("#2F80ED"));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    private void clearMenu(int exceptIndex) {
        for (Component com : panelMenu.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.getIndex() != exceptIndex) {
                item.setSelected(false);
            }
        }
    }

    public void addEventMenu(ActionListener event) {
        btnMenu.addActionListener(event);
    }
    public void addEventDangXuat(ActionListener event) {
		btnLogOut.addActionListener(event);
	}
    
    
    
    public void setAlpha(float alpha) {
		header.setAlpha(alpha);
		tenKhiDangNhap.setAlpha(alpha);
	}
   
    
    
}
