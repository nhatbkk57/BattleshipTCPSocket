/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author Dai
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatSocketClient {

    private ServerSocket severSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;

    public ChatSocketClient() {

    }

    public void createSocket(String server) {
        try {
            socket = new Socket(server, 1994);
            System.out.println("Connected");
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            createReadThread();
            createWriteThread();
        } catch (UnknownHostException u) {
            u.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void createReadThread() {
        Thread readThread;
        readThread = new Thread() {
            @Override
            public void run() {
                while (socket.isConnected()) {

                    try {
                        String str = "";
                        str = inStream.readUTF();
                        if (str.length() > 0) {
                            battleship_client.BattleShip.communicate.appendConten("ĐỐI THỦ: "+str+"\n");
                        }
                    } catch (SocketException se) {
                        System.exit(0);

                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
            }
        };
        readThread.start();
    }

    public void createWriteThread() {
        Thread writeThread;
        writeThread = new Thread() {
            @Override
            public void run() {

                while (socket.isConnected()) {
                    synchronized (socket) {
                        String str = battleship_client.BattleShip.communicate.getTextInput();
                        if (str.length() > 0) {
                            try {
                                battleship_client.BattleShip.communicate.appendConten("BẠN: "+str+"\n");
                                outStream.writeUTF(str);
                                outStream.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(ChatSocketClient.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                }
            }
        };
        writeThread.start();
    }
}
