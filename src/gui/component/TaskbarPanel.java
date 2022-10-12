/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.component;

import gui.MainFrame;
import gui.common.CommonLabel;
import gui.common.CommonStatic;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Van Toan
 */
public class TaskbarPanel extends JPanel {

    private CommonLabel chat_label;
    private CommonLabel connect_label;
    private CommonLabel setting_label;
    private int focus_key;

    public TaskbarPanel() {
        // layout of taskbar_panel
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setHgap(20);
        
        // style taskbar_panel
        this.setLayout(flowLayout);
        this.setSize(CommonStatic.WIDTH_FRAME, CommonStatic.HEIGHT_TASKBAR);
        this.setBackground(Color.decode(CommonStatic.TASKBAR_BACKGROUND));

        // add components
        this.initComponents();
    }

    private void initComponents() {
        // initalize
        this.connect_label = new CommonLabel();
        this.chat_label = new CommonLabel();
        this.setting_label = new CommonLabel();
        this.focus_key = 1;
        
        
        // style connect_label
        this.connect_label.setText("Điểu khiển");
        this.connect_label.setSuperSmallFont();
        this.connect_label.setBoldFont();
        this.add(this.connect_label);


        // style chat_label
        this.chat_label.setText("Trò chuyện");
        this.chat_label.setSuperSmallFont();
        this.add(this.chat_label);
        
        
        // style setting_label
        this.setting_label.setText("Cài đặt");
        this.setting_label.setSuperSmallFont();
        this.add(this.setting_label);
    }

    public CommonLabel getChatLabel() {
        return chat_label;
    }

    public CommonLabel getConnectLabel() {
        return connect_label;
    }

    public CommonLabel getSettingLabel() {
        return setting_label;
    }

    public int getFocusKey() {
        return focus_key;
    }

    public void setFocusKey(int key) {
        this.focus_key = key;
    }

}
