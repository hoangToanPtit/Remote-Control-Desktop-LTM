/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Nguyen Van Toan
 */
public class CommonLabel extends JLabel {

    public CommonLabel() {
        // style client_panel
        this.setSmallFont();
        this.setForeground(Color.decode(CommonStatic.FOREGROUND));

        // add events when mouse entered or exited
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tabLabelMouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tabLabelMouseExited(e);
            }

        });
    }

    public void setSuperSmallFont() {
        this.setFont(new Font(CommonStatic.LABEL_FONT, Font.PLAIN, CommonStatic.SUPER_SMALL_FONT_SIZE));
    }

    public void setSmallFont() {
        this.setFont(new Font(CommonStatic.LABEL_FONT, Font.PLAIN, CommonStatic.SMALL_FONT_SIZE));
    }

    public void setBigFont() {
        this.setFont(new Font(CommonStatic.LABEL_FONT, Font.PLAIN, CommonStatic.BIG_FONT_SIZE));
    }

    public void resetFont() {
        int font_size = this.getFont().getSize();
        this.setFont(new Font(CommonStatic.LABEL_FONT, Font.PLAIN, font_size));
    }

    public void setBoldFont() {
        int font_size = this.getFont().getSize();
        this.setFont(new Font(CommonStatic.LABEL_FONT, Font.BOLD, font_size));
    }

    private void tabLabelMouseEntered(MouseEvent e) {
        if (this.isEnabled()) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int font_size = this.getFont().getSize();
            this.setFont(new Font(CommonStatic.LABEL_FONT, Font.BOLD, font_size));
        }
    }

    private void tabLabelMouseExited(MouseEvent e) {
        if (this.isEnabled()) {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            int font_size = this.getFont().getSize();
            this.setFont(new Font(CommonStatic.LABEL_FONT, Font.PLAIN, font_size));
        }
    }

}
