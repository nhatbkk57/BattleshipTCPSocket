/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_server;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author mada94
 */
public class Check {

    public int check = 0;
    
    public Check() {

    }

    /////////////////////// liên quan tới kết nối trò chơi
    /**
     * báo rằng sẵn sàng cho đối thủ đã sẵn sàng và trả về true nếu mình cũng đã ss trước đó
     * @param can 
     */
    public void setReady(boolean can) {
//   
        BattleShip.rivel.ready = true;
        BattleShip.rivel.can = can;
    }

    /**
     * hàm này truyền vào 1 tọa độ a, b do mình bắn trước đó và biến y là thông
     * điệp đối thủ báo với mình rằng trúng đích hay không
     *
     * @param a
     * @param b
     * @param y
     */
    public void checkMessage(int a, int b, int y) {
        // xử lý kiểu khác, kiểu thông báo tói chúng ta kết quả của việc bắn lúc trước
        switch (y) {
            case 0: {
                // không làm gì cả vì trong phần Map, đã gán hình vòng tròn trắng
                // nghĩa là đối thủ gửi lại mình kết quả mình vừa bắn
                BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.red));
                BattleShip.rivel.can = false;
                break;
            }
            case 1: {
                // đây là bắn trúng 1 con tàu :D
                BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.green));
                try {
                    BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/fire.gif"));
                    ImageIcon icon = new ImageIcon(splash.getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH));
                    Map.lb.setIcon(icon);
                } catch (IOException ex) {
                }
                Map.lb.setBounds(a * 25, b * 25, 25, 25);
                BattleShip.rivel.add(Map.lb);
                BattleShip.rivel.updateUI();
                break;
            }
            case 2: {
                BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.green));
                try {
                    BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/fire.gif"));
                    ImageIcon icon = new ImageIcon(splash.getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH));
                    Map.lb.setIcon(icon);
                } catch (IOException ex) {
                }
                Map.lb.setBounds(a * 25, b * 25, 25, 25);
                BattleShip.rivel.add(Map.lb);
                BattleShip.rivel.updateUI();
                break;
            }
            case 3: {
                
                try {
                    BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/fire.gif"));
                    ImageIcon icon = new ImageIcon(splash.getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH));
                    Map.lb.setIcon(icon);
                } catch (IOException ex) {
                }
                Map.lb.setBounds(a * 25, b * 25, 25, 25);
                BattleShip.rivel.add(Map.lb);
                BattleShip.rivel.updateUI();
                JOptionPane.showMessageDialog(null, "Chúc mừng  SERVER đã giành chiến thắng");
                BattleShip.ship.control.rePlay.setEnabled(true);
                break;
            }
        }
    }

    /**
     * hàm này là phần cũ của Lực làm, dùng để check cái ma trận bản đồ với tọa
     * độ gửi vào
     *
     * @param x
     * @param y
     */
    public void checkMap(int x, int y) {
        // xử lý theo kiểu cả x, y >0 => dữ liệu được gửi tới là 1 tọa độ mà chúng ta vừa bị bắn
        if (onTarget(x, y) < 0) {
            setIcon("onTarget", x, y, onTarget(x, y));
            if (distroy(x, y)) {
                String name = "";
                if (onTarget(x, y) == -1) {
                    name = "patrol";
                    ShowShip.labPat.setText("distroy");
                } else if (onTarget(x, y) == -2) {
                    name = "submarine";
                    ShowShip.labSub.setText("distroy");
                } else if (onTarget(x, y) == -3) {
                    name = "seawolf";
                    ShowShip.labSea.setText("distroy");
                } else if (onTarget(x, y) == -4) {
                    name = "battleship";
                    ShowShip.labBat.setText("distroy");
                } else if (onTarget(x, y) == -5) {
                    name = "carrier";
                    ShowShip.labCar.setText("distroy");
                }
                BattleShip.thanhtich.append("ban da bi ha tau: " + name + "\n");
                BattleShip.ship.updateUI();
                if (win()) {
                    JOptionPane.showMessageDialog(null, "SERVER  ĐÃ THUA!");
                    BattleShip.ship.control.rePlay.setEnabled(true);
                    check = 3; //chiến thắng
                } else {
                    BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.red));
                    check = 2; // hạ tàu
                }
            } else {
                BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.red));
                check = 1; // bắn trúng
            }
        } else {
            BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.green));
            check = 0; //bắn trượt
        }
    }

//kiểm tra bắn trúng đích
    public int onTarget(int x, int y) {
        if (BattleShip.corShip[y][x] > 0) {
            BattleShip.corShip[y][x] = 0 - BattleShip.corShip[y][x];
        }
        return BattleShip.corShip[y][x];
    }

    //kiểm tra hạ tàu
    public boolean distroy(int x, int y) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((y != i || x != j) && BattleShip.corShip[i][j] == -BattleShip.corShip[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    //kiểm tra win
    public boolean win() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (BattleShip.corShip[i][j] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //
    public void setIcon(String string, int x, int y, int a) {
        if (string.equals("onTarget")) {
            JLabel lb1 = new JLabel();
            try {
                BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/fire.gif"));
                ImageIcon icon = new ImageIcon(splash.getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH));
                lb1.setIcon(icon);
                lb1.setBounds(x * 25, y * 25, 25, 25);
                JLabel bl = null;
                if (a == -4) {
                    bl = Map.battleship;
                } else if (a == -5) {
                    bl = Map.carrier;
                } else if (a == -3) {
                    bl = Map.seawolf;
                } else if (a == -2) {
                    bl = Map.submarine;
                } else if (a == -1) {
                    bl = Map.patrol;
                }

                BattleShip.player.remove(bl);
                BattleShip.player.add(lb1);
                BattleShip.player.add(bl);
                BattleShip.player.updateUI();
            } catch (IOException ex) {
            }
        }
    }
}
