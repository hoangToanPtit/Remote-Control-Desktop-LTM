/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection.client;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Van Toan
 */
public class ReceiveScreenThread extends Thread {

    private ObjectInputStream ois = null;
    private JPanel cPanel = null;
    private boolean continueLoop = true;
    private InputStream in = null;
    private Image image1 = null;

    public ReceiveScreenThread(InputStream in, JPanel panel) {
        this.in = in;
        this.cPanel = panel;
        start();
    }

    public void run() {
        try {
            //Read screenshots of the client and then draw them
            while (continueLoop) {
                byte[] bytes = new byte[1024 * 1024];
                int count = 0;
                do {
                    count += in.read(bytes, count, bytes.length - count);
                } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));

                image1 = ImageIO.read(new ByteArrayInputStream(bytes));
//                image1 = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);
                image1 = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_SMOOTH);

                //Draw the received screenshots
                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
                
//                continueLoop=false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void terminate() {
        continueLoop = false;
    }
}
