/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_client;

import battleship.graphics.Logo;
import connection.ChatSocketClient;
import connection.GameSocketClient;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mada94
 */
public class BattleShip extends JFrame {

    private JLabel nameGame = new JLabel("BattleShip");
    static Map rivel = new Map(true);
    static Map player = new Map(false);
    private final Logo logo = new Logo();
    private final MenuBar menu = new MenuBar();
    static ShowShip ship = new ShowShip();
    public static Communicate communicate = new Communicate();
    static ThanhTich thanhtich = new ThanhTich();
    static int[][] corShip = new int[10][10];
    public static GameSocketClient client = new GameSocketClient();
    public static ChatSocketClient client_chat = new ChatSocketClient();

    static JPanel gaming = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            /* vẽ dòng */
            g2.setColor(new Color(0, 100, 90));
            g2.drawLine(0, 15, 0, 300);
            g2.drawLine(0, 15, 25, 15);
            g2.drawLine(90, 15, 629, 15);
            g2.drawLine(629, 15, 629, 300);
            g2.drawLine(0, 300, 629, 300);
        }
    };

    public BattleShip(String server) {
        
        setTitle("CLIENT");
        setSize(660, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(100, 40);
        setLayout(null);

        add(menu.getMenu());
        gaming.setBounds(10, 20, 630, 320);
        gaming.setLayout(null);

        nameGame.setBounds(30, 0, 70, 30);
        gaming.add(nameGame);
        rivel.setBounds(10, 35, 250, 250);
        rivel.can = false;
        gaming.add(rivel);
        logo.setBounds(265, 35, 100, 250);
        gaming.add(logo);
        player.setBounds(370, 35, 250, 250);
        player.ready = true;
        player.can = true;
        gaming.add(player);

        add(gaming);
        add(ship);
        add(thanhtich);
        add(communicate);
        client.createSocket(server);
        client_chat.createSocket(server);
        setVisible(true);
    }
}
