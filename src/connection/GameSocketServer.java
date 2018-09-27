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
import  battleship_server.Check;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GameSocketServer {

    private ServerSocket severSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;
    public Data dout = new Data(0, 0);

    public GameSocketServer() {

    }

    public void createSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            socket = serverSocket.accept();
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Connected");
            createReadThread();
            createWriteThread();
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
                        int len = str.length();
                        Check ck = new Check();
                        
                        // nhận được 1 tọa độ
                        if (len == 3) {
                            int a = Integer.parseInt(str.substring(0, 1));
                            int b = Integer.parseInt(str.substring(2));
                            ck.checkMap(a, b);
                            if(ck.check == 0){ // nếu mà nó không bắn trúng mình
                                ck.setReady(true);
                            }
                            dout.setX(-1);
                            dout.setY(ck.check);
                            dout.ss = true;
                        }
                        
                        // nhận được thông điệp có dạng "-1 0"
                        if (len == 4) {
                            int b = Integer.parseInt(str.substring(3));
                            ck.checkMessage(dout.getX(), dout.getY(), b);
                        }
                        
                        // nhận được thông điệp có 2 số âm, nghĩa là đã sẵn sàng
                        if(len == 5){
                            // nếu nhận được phản hồi à  -1 -2 là mình được đánh trước
                            // nếu nhận được phản hồi -1 -1 tức là mình phải đánh sau, gán false
                            int b = Integer.parseInt(str.substring(3));
                            System.out.println("b= "+b);
                            if(b == -2){
                                // nghĩa là khi ta nhận được thông báo này
                                ck.setReady(true);
                            }else{
                                ck.setReady(false);
                            }
                        }
                        //System.arraycopy();
                    } catch (SocketException se) {
                        System.exit(0);

                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
                JOptionPane.showConfirmDialog(null, "đối thủ đã thoát");
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
                        if (dout.ss) {
                            String str = "";
                            str = str + dout.getX() + " " + dout.getY();
                            try {
                                outStream.writeUTF(str);
                                outStream.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(GameSocketServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            dout.ss = false;
                        }
                    }
                }
                JOptionPane.showConfirmDialog(null, "đối thủ đã thoát");
            }
        };
        writeThread.start();

    }
}
