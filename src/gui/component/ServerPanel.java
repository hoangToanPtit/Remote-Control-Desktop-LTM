/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.component;

import connection.server.ListenThread;
import connection.server.ServerSide;
import gui.common.CommonLabel;
import gui.common.CommonPanel;
import gui.common.CommonStatic;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Nguyen Van Toan
 */
public class ServerPanel extends JPanel {

    private ListenThread listen_thread = null;
    
    private CommonPanel main_panel;
    private JLabel status_label;
    private CommonLabel listen_label;
    private CommonLabel stop_label;

    public ServerPanel() {
        // style ClientPanel
        this.setLocation(CommonStatic.WIDTH_TASKBAR, 0);
        this.setSize((CommonStatic.WIDTH_FRAME - CommonStatic.WIDTH_TASKBAR) / 2, CommonStatic.HEIGHT_FRAME);
        this.setBackground(Color.decode(CommonStatic.BACKGROUND));
        this.setLayout(null);

        // add components
        this.initComponents();
    }

    private void initComponents() {
        // initalize components
        this.main_panel = new CommonPanel();
        this.status_label = new JLabel();
        this.listen_label = new CommonLabel();
        this.stop_label = new CommonLabel();

        // add main_panel
        this.add(this.main_panel);

        // add items for host_combo in main_panel
        this.main_panel.getHostCombo().addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                try {
                    main_panel.getHostCombo().removeAllItems();
                    List<String> ipv4_addresses = this.getAllIpv4AddressesOnLocal();
                    for (String ipv4 : ipv4_addresses) {
                        main_panel.getHostCombo().addItem(ipv4);
                    }
                } catch (Exception exception) {
                    System.err.println(exception);
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            // get ip addreses
            private List<String> getAllIpv4AddressesOnLocal() throws SocketException {
                List<String> ipv4_addresses = new ArrayList<>();
                Enumeration networks = NetworkInterface.getNetworkInterfaces();
                while (networks.hasMoreElements()) {
                    NetworkInterface sub_networks = (NetworkInterface) networks.nextElement();
                    Enumeration inet_addresses = sub_networks.getInetAddresses();
                    while (inet_addresses.hasMoreElements()) {
                        try {
                            Inet4Address ipv4 = (Inet4Address) inet_addresses.nextElement();
                            ipv4_addresses.add(ipv4.getHostAddress());
                        } catch (Exception e) {
                            // pass ip version 6
                        }
                    }
                }
                return ipv4_addresses;
            }

        });
        this.main_panel.getHostCombo().setVisible(true);

        // style status_label
        this.status_label.setText("Status: Stopped");
        this.status_label.setBounds(30, 200, 150, 20);
        this.status_label.setFont(new Font("segoe ui", Font.ITALIC | Font.BOLD, 13));
        this.status_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
        this.main_panel.add(this.status_label);

        // style and add event for listen_label
        this.listen_label.setText("Start listening");
        this.listen_label.setBounds(50, 290, 150, 50);
        this.listen_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
        this.listen_label.setFont(new Font("segoe ui", Font.PLAIN, 15));
        this.listen_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listenLabelMousePressed(e);
            }
        });
        this.add(this.listen_label);

        // style and add event for stop_label
        this.stop_label.setText("Stop listening");
        this.stop_label.setBounds(220, 290, 150, 50);
        this.stop_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
        this.stop_label.setFont(new Font("segoe ui", Font.PLAIN, 15));
        this.stop_label.setEnabled(false);
        this.stop_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                stopLabelMousePressed(e);
            }
        });
        this.add(this.stop_label);

    }

    // handle events of listen_label
    private void listenLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && this.listen_label.isEnabled()) {
            try {
                // get ip, port, password
                String host = this.main_panel.getHostCombo().getSelectedItem().toString().trim();
                int port = Integer.parseInt(this.main_panel.getPortText().getText().trim());
                String password = this.main_panel.getPassText().getText().trim();

                // start listen_thread
                this.listen_thread = new ListenThread(new ServerSide(port, password));
//                this.listen_thread = new ListenThread(new ServerSide(port, password));

                // set status
                this.main_panel.setEnabled(false);
                this.listen_label.resetFont();
                this.listen_label.setEnabled(false);
                this.stop_label.setEnabled(true);
                this.status_label.setText("Status: Listening...");
                this.status_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Can't start listening on server:\n" + exception.getMessage());
            }
        }
    }

    // handle events of stop_label
    private void stopLabelMousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && this.stop_label.isEnabled()) {
            try {

                // stop listen_thread
                this.listen_thread.terminal();

                // TODO: set status
                this.main_panel.setEnabled(true);
                this.stop_label.resetFont();
                this.stop_label.setEnabled(false);
                this.listen_label.setEnabled(true);
                this.status_label.setText("Status: Stopped");
                this.status_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
            }
            catch(Exception exception) {
                System.out.println(exception.getMessage());
                JOptionPane.showMessageDialog(this, "Can't stop listening on server:\n" + exception.getMessage());
            }
        }
    }
}
