/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection.client;

/**
 *
 * @author Nguyen Van Toan
 */
public class RemoteThread extends Thread{
    private ClientSide clientSide = null;

    public RemoteThread(ClientSide clientSide) {
        this.clientSide = clientSide;
        start();
    }

    @Override
    public void run() {
        this.clientSide.startRemote();
    }
    
    public void terminal(){
        this.clientSide.stopRemote();
    }
    
    
}
