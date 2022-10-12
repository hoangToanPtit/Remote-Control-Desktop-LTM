/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.common;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nguyen Van Toan
 */
public class CommonPanel extends JPanel{

    private JLabel host_label;
    private JComboBox<String> host_combo; //for server
    private JTextField host_text; // for client
    
    private JLabel port_label;
    private JTextField port_text; 
    
    private JLabel pass_label;
    private JTextField pass_text; // for server
    private JPasswordField pass_field; //for client
    
    private JLabel help_label;
    
    
    public CommonPanel() {
        // style common panel
        this.setBorder(BorderFactory.createTitledBorder(null, "Cho phép điều khiển",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("segoe ui", Font.BOLD, 16),
            Color.decode(CommonStatic.FOREGROUND))
        );
        this.setBounds(30, 35, 300, 250);
        this.setBackground(Color.decode(CommonStatic.BACKGROUND));
        this.setForeground(Color.decode(CommonStatic.FOREGROUND));
        this.setLayout(null);

        // add components
        this.initcomponents();
    }

    private void initcomponents() {
        // initalize components
        this.host_label = new JLabel();
        this.host_combo = new JComboBox<>();
        this.host_text = new JTextField(); 

        this.port_label = new JLabel();
        this.port_text = new JTextField(); 

        this.pass_label = new JLabel();
        this.pass_text = new JTextField();
        this.pass_field = new JPasswordField();

        this.help_label = new JLabel();
        
        
        // style host_label
        this.host_label.setText("Server IP:");
        this.host_label.setBounds(30, 30, 100, 30);
        this.host_label.setFont(new Font("segoe ui", Font.BOLD, 14));
        this.add(this.host_label);

        // style host_combo for server panel
        this.host_combo.setBounds(140, 35, 130, 20);
        this.add(this.host_combo);

        // style host_text for client panel
        this.host_text.setBounds(140, 35, 130, 20);
        this.host_text.setVisible(false);
        this.add(this.host_text);

        // style port_label
        this.port_label.setText("Server port:");
        this.port_label.setBounds(30, 60, 100, 30);
        this.port_label.setFont(new Font("segoe ui", Font.BOLD, 14));
        this.add(this.port_label);

        // style port_text
        this.port_text.setBounds(140, 65, 130, 20);
        this.port_text.setText("9999");
        this.add(this.port_text);

        // style pass_label
        this.pass_label.setText("Set password:");
        this.pass_label.setBounds(30, 90, 100, 30);
        this.pass_label.setFont(new Font("segoe ui", Font.BOLD, 14));
        this.add(this.pass_label);

        // style pass_text for server panel
        this.pass_text.setBounds(140, 95, 130, 20);
        this.pass_text.setText("123456");
        this.add(this.pass_text);

        // style pass_field for client panel
        this.pass_field.setBounds(140, 95, 130, 20);
        this.pass_field.setText("123456");
        this.add(this.pass_field);

        // style help_label
        this.help_label.setText("<html>>>Help: send name or IP address and port to your partner.<br>>>Example: 192.168.0.1:9999</html>");
        this.help_label.setBounds(40, 130, 230, 70);
        this.help_label.setForeground(Color.decode(CommonStatic.FOREGROUND));
        this.help_label.setFont(new Font("segoe ui", Font.BOLD, 13));
        this.add(this.help_label);
        
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.host_combo.setEnabled(enabled);
        this.host_text.setEnabled(enabled);
        this.pass_field.setEnabled(enabled);
        this.pass_text.setEnabled(enabled);
        this.port_text.setEnabled(enabled);
    }

    public JLabel getHostLabel() {
        return host_label;
    }

    public JComboBox<String> getHostCombo() {
        return host_combo;
    }

    public JTextField getHostText() {
        return host_text;
    }

    public JLabel getPortLabel() {
        return port_label;
    }

    public JTextField getPortText() {
        return port_text;
    }

    public JLabel getPassLabel() {
        return pass_label;
    }

    public JTextField getPassText() {
        return pass_text;
    }

    public JPasswordField getPassField() {
        return pass_field;
    }

    public JLabel getHelpLabel() {
        return help_label;
    }

    
    
    
    
}
