/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Van Toan
 */
public class ClientSide {

    private Socket client = null;

    public ClientSide(String ip, int port) {
        try {
            this.client = new Socket(ip, port);
            System.out.println("Connecting to the Server");
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startRemote() {
        try {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("12345");
            new FrameCreator(client, "2000", "100");
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stopRemote() {
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
