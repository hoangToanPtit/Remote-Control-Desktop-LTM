/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.component;

import gui.MainFrame;
import gui.common.CommonLabel;
import gui.common.CommonPanel;
import gui.common.CommonStatic;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nguyen Van Toan
 */
public class ClientPanel extends JPanel {

    private CommonPanel main_panel;
    private CommonLabel connect_label;
    private ButtonGroup button_group;
    // ???
    private JRadioButton low_radio;
    private JRadioButton high_radio;

    private JLabel loader_label;

    public ClientPanel() {
        // style ClientPanel
        this.setLocation((CommonStatic.WIDTH_FRAME - CommonStatic.WIDTH_TASKBAR) / 2 + CommonStatic.WIDTH_TASKBAR, 0);
        this.setSize((CommonStatic.WIDTH_FRAME - CommonStatic.WIDTH_TASKBAR) / 2, CommonStatic.HEIGHT_FRAME);
        this.setBackground(Color.decode(CommonStatic.BACKGROUND));
        this.setLayout(null);

        // add components
        this.initComponents();
    }

    private void initComponents() {
        // constructor
        this.main_panel = new CommonPanel();
        this.connect_label = new CommonLabel();
        this.button_group = new ButtonGroup();
        this.low_radio = new JRadioButton();
        this.high_radio = new JRadioButton();
        this.loader_label = new JLabel();

        // style main panel
        this.main_panel.setBorder(BorderFactory.createTitledBorder(null, 
                "Điều khiển máy tính khác",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("segoe ui", Font.BOLD, 16),
                Color.decode(CommonStatic.FOREGROUND))
        );

        this.add(this.main_panel);

        // style host_label
        this.main_panel.getHostLabel().setText("Remote IP:");

        // style host_text
        this.main_panel.getHostCombo().setVisible(false);
        this.main_panel.getHostText().setVisible(true);

        // style port_label
        this.main_panel.getPortLabel().setText("Remote port:");

        // style pass_label
        this.main_panel.getPassLabel().setText("Password:");

        // style pass_field
        this.main_panel.getPassText().setVisible(false);
        this.main_panel.getPassField().setVisible(true);

        // style help_label
        this.main_panel.getHelpLabel().setText("<html>>>Help: Enter a name "
                + "or an IP address and port of server which you want to remote."
                + "<br>>>Example: 192.168.0.1:9999</html>");

        // style connect_label
//        this.connect_label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/connect_icon.png")));
        this.connect_label.setText("Connect now");
        this.connect_label.setBounds(220, 290, 150, 50);
        this.connect_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
        this.connect_label.setFont(new Font("segoe ui", Font.PLAIN, 15));
        this.connect_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                connectLabelMousePressed(e);
            }
        });
        this.add(this.connect_label);

        // style low_radio
        this.low_radio.setText("Low quality");
        this.low_radio.setBounds(60, 290, 100, 30);
        this.low_radio.setOpaque(false);
        this.low_radio.setSelected(true);
        this.button_group.add(this.low_radio);
//        this.add(this.low_radio);

        // style high_radio
        this.high_radio.setText("High quality");
        this.high_radio.setBounds(60, 310, 100, 30);
        this.high_radio.setOpaque(false);
        this.button_group.add(this.high_radio);
//        this.add(this.high_radio);

        // style loader_label
        this.loader_label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/loader_icon.gif")));
        this.loader_label.setBounds(340, 307, 16, 16);
        this.loader_label.setVisible(false);
        this.add(this.loader_label);
    }

}
