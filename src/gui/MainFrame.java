/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import gui.common.CommonLabel;
import gui.common.CommonStatic;
import gui.common.CommonPanel;
import gui.component.ClientPanel;
import gui.component.ServerPanel;
import gui.component.TaskbarPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

/**
 *
 * @author Nguyen Van Toan
 */
public class MainFrame extends JFrame{

    private ServerPanel server_panel;
    private ClientPanel client_panel;
    private JPanel wrap_client_server_panel;
    private TaskbarPanel taskbar_panel;
    
    public MainFrame() {
        // sytle main_frame
        this.setSize(CommonStatic.WIDTH_FRAME, CommonStatic.HEIGHT_FRAME);
        this.setBackground(Color.decode(CommonStatic.BACKGROUND));
//        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(" Remote Desktop Software");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("./images/network.png")).getImage());
        this.setVisible(true);

        
        this.initConponent();
    }

    private void initConponent() {
        // initalize
        this.wrap_client_server_panel = new JPanel();
        this.server_panel = new ServerPanel();
        this.client_panel = new ClientPanel();
        this.taskbar_panel = new TaskbarPanel();
        
        
        // style for wrap_client_server_panel
        this.wrap_client_server_panel.setLocation(0, CommonStatic.HEIGHT_TASKBAR);
        this.wrap_client_server_panel.setSize(CommonStatic.WIDTH_FRAME, CommonStatic.HEIGHT_FRAME-CommonStatic.HEIGHT_TASKBAR);
        this.wrap_client_server_panel.setLayout(null);
        this.client_panel.setVisible(true);
        this.server_panel.setVisible(true);
        this.wrap_client_server_panel.add(this.client_panel);
        this.wrap_client_server_panel.add(this.server_panel);
        
        // default
        this.add(this.wrap_client_server_panel);
        this.add(this.taskbar_panel);
    }
    
    
    private void tabLabelMouseClicked(MouseEvent e, CommonLabel common_label, int key) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            int focus_key = this.taskbar_panel.getFocusKey();
            
            if(key == focus_key) return;
            JPanel show_panel = (key == 1) ? this.wrap_client_server_panel : (key == 2) ? this.wrap_client_server_panel : this.wrap_client_server_panel;
            JPanel hide_panel = (focus_key == 1) ? this.wrap_client_server_panel : (focus_key == 2) ? this.wrap_client_server_panel : this.wrap_client_server_panel;
            if(key > focus_key) {
                this.showPanelsSlider(show_panel, hide_panel, true);
            }
            else {
                this.showPanelsSlider(show_panel, hide_panel, false);
            }

            // TODO: update status
            this.taskbar_panel.setFocusKey(key);
            this.taskbar_panel.getConnectLabel().setSuperSmallFont();
            this.taskbar_panel.getChatLabel().setSuperSmallFont();
            this.taskbar_panel.getSettingLabel().setSuperSmallFont();
            common_label.setBoldFont();
        }
    }

    private void showPanelsSlider(JPanel show_panel, JPanel hide_panel, boolean isLeft) {
        show_panel.setVisible(true);

        // TODO: atomic integer for lambda expression
        AtomicInteger x_hide_location = new AtomicInteger(0);
        AtomicInteger x_show_location = new AtomicInteger(0);
        AtomicInteger value = new AtomicInteger(0);

        if(isLeft) {
            x_show_location.set(CommonStatic.WIDTH_FRAME);
            value.set(-50);
        }
        else {
            x_show_location.set(-CommonStatic.WIDTH_FRAME);
            value.set(50);
        }

        Timer timer = new Timer(10, (e) -> {
            hide_panel.setLocation(x_hide_location.get(), CommonStatic.HEIGHT_TASKBAR);
            show_panel.setLocation(x_show_location.get(), CommonStatic.HEIGHT_TASKBAR);
            if(x_show_location.get() == 0) {
                ((Timer)e.getSource()).stop();
                hide_panel.setVisible(false);
            }
            x_show_location.addAndGet(value.get());
            x_hide_location.addAndGet(value.get());
        });
        timer.start();
    }
}
