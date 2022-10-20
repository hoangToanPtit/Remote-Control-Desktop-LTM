/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection.server;

/**
 *
 * @author Nguyen Van Toan
 */
public class ListenThread extends Thread{
    private ServerSide serverSide = null;

    public ListenThread(ServerSide serverSide) {
        this.serverSide = serverSide;
        start();
    }

    @Override
    public void run() {
        serverSide.startListening();
    }
    
    public void terminal(){
        serverSide.stopListening();
    }
}
