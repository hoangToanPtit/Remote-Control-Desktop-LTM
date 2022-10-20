/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection.server;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Van Toan
 */
public class ServerSide {

    private ServerSocket socket = null;
    private int port;
    private String password;
    private boolean isAvailable = false;

    public ServerSide(int port, String password) {
        this.port = port;
        this.password = password;
        try {
            socket = new ServerSocket(this.port);
            System.out.println("Awaiting Connection from Client");
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startListening() {
        
        try {
            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            dim.setSize(dim.getWidth(), dim.getHeight());
            String width = "" + (dim.getWidth());
            String height = "" + (dim.getHeight());

            Robot robot = new Robot(gDev);
            Rectangle rectangle = new Rectangle(dim);
            isAvailable = true;
            while (isAvailable) {
                Socket connect = socket.accept();
                DataInputStream dis = new DataInputStream(connect.getInputStream());
                String psw = dis.readUTF();

                if (checkConnection(password)) {
                    System.out.println("connection is valid!");
                    new SendScreenThread(connect, robot, rectangle);
                    new ReceiveEventThread(connect, robot);
                } else {
                    System.out.println("connection is valid!");
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopListening(){
        isAvailable = false;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private Boolean checkConnection(String password) {
        if (!this.password.equals(password)) {
            System.err.println("client enter wrong password!");
            return false;
        }
        return true;
    }
}
