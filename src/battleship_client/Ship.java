
package battleship_client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author mada94
 */
public class Ship{
    public JButton button = new JButton();
    static JButton but = new JButton();
    static JLabel lab = new JLabel();
    static int sizeX = 0, sizeY = 0;
    static String nameShip;
    public Ship(final String name) {
        button.setBackground(Color.white);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        ImageIcon icon = new ImageIcon(getClass().getResource("/graphics/" + name + ".gif"));
        button.setIcon(icon);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("carrier".equals(name) && Control.select == 1) {
                    nameShip = "carrier";
                    sizeX = 125;
                    sizeY = 25;
                    lab = ShowShip.labCar;
                } else if ("battleship".equals(name) && Control.select == 1) {
                    nameShip = "battleship";
                    sizeX = 100;
                    sizeY = 25;
                    lab = ShowShip.labBat;
                } else if ("seawolf".equals(name) && Control.select == 1) {
                    nameShip = "seawolf";
                    sizeX = 100;
                    sizeY = 25;
                    lab = ShowShip.labSea;
                } else if ("submarine".equals(name) && Control.select == 1) {
                    nameShip = "submarine";
                    sizeX = 75;
                    sizeY = 25;
                    lab = ShowShip.labSub;
                } else if ("patrol".equals(name) && Control.select == 1) {
                    nameShip = "patrol";
                    sizeX = 50;
                    sizeY = 25;
                    lab = ShowShip.labPat;
                } else if ("carrier".equals(name) && Control.select == 2) {
                    nameShip = "carrierv";
                    sizeX = 25;
                    sizeY = 125;
                    lab = ShowShip.labCar;
                } else if ("battleship".equals(name) && Control.select == 2) {
                    nameShip = "battleshipv";
                    sizeX = 25;
                    sizeY = 100;
                    lab = ShowShip.labBat;
                } else if ("seawolf".equals(name) && Control.select == 2) {
                    nameShip = "seawolfv";
                    sizeX = 25;
                    sizeY = 100;
                    lab = ShowShip.labSea;
                } else if ("submarine".equals(name) && Control.select == 2) {
                    nameShip = "submarinev";
                    sizeX = 25;
                    sizeY = 75;
                    lab = ShowShip.labSub;
                } else if ("patrol".equals(name) && Control.select == 2) {
                    nameShip = "patrolv";
                    sizeX = 25;
                    sizeY = 50;
                    lab = ShowShip.labPat;
                }
                but = button;
                Map.cursor = true;
                Map.clicked = false;
                Map.click_right = false;
            }
        });
    }
}
