/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_server;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author mada94
 */
public class Map extends JPanel {

    public boolean can = false;
    static boolean cursor = true, clicked = false, location = true, click_right = false;
    private int x, y;
    static JLabel lb;
    static JLabel battleship;
    static JLabel carrier;
    static JLabel patrol;
    static JLabel seawolf;
    static JLabel submarine;
    private Location locat;
    static JLabel lb1 = new JLabel();
    static int coordinate[][] = new int[5][5], a;
    public boolean ready = false;

    public Map(final boolean rivel) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                coordinate[i][j] = -1;
            }
        }
        setLayout(null);
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX() / 25 * 25;
                y = e.getY() / 25 * 25;
                if (rivel == false && click_right == false) {
                    remove(lb1);
                    if (cursor == true) {
                        locat = new Location(x, y);
                        if (location == true) {
                            try {
                                BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/" + Ship.nameShip + ".gif"));
                                ImageIcon icon = new ImageIcon(splash.getScaledInstance(Ship.sizeX, Ship.sizeY, BufferedImage.SCALE_SMOOTH));
                                lb1.setIcon(icon);
                                lb1.setBounds(x, y, Ship.sizeX, Ship.sizeY);
                            } catch (IOException ex) {
                            }
                            add(lb1);
                        }
                        location = true;
                        updateUI();
                    }
                }
            }
        });

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (ready) {
                    if (can == true) {
                        if (e.getButton() == 3) {
                            click_right = true;
                            lb1.setIcon(null);
                        }
                        lb = new JLabel();
                        x = e.getX() / 25 * 25;
                        y = e.getY() / 25 * 25;
                        if (rivel == false && clicked == false && Ship.sizeX != 0 && e.getButton() == 1) {
                            if (x <= 250 - Ship.sizeX && y <= 250 - Ship.sizeY) {
                                locat = new Location(x, y);
                                if (location == true) {
                                    lb.setBounds(x, y, Ship.sizeX, Ship.sizeY);
                                    try {
                                        BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/" + Ship.nameShip + ".gif"));
                                        ImageIcon icon = new ImageIcon(splash.getScaledInstance(Ship.sizeX, Ship.sizeY, BufferedImage.SCALE_SMOOTH));
                                        lb.setIcon(icon);
                                        lb1.setIcon(null);
                                        cursor = false;
                                    } catch (IOException ex) {
                                    }
                                    if (Ship.nameShip.equals("battleship") || Ship.nameShip.equals("battleshipv")) {
                                        battleship = lb;
                                        add(battleship);
                                        a = 4;
                                    } else if (Ship.nameShip.equals("seawolf") || Ship.nameShip.equals("seawolfv")) {
                                        seawolf = lb;
                                        add(seawolf);
                                        a = 3;
                                    } else if (Ship.nameShip.equals("submarine") || Ship.nameShip.equals("submarinev")) {
                                        submarine = lb;
                                        add(submarine);
                                        a = 2;
                                    } else if (Ship.nameShip.equals("patrol") || Ship.nameShip.equals("patrolv")) {
                                        patrol = lb;
                                        add(patrol);
                                        a = 1;
                                    } else if (Ship.nameShip.equals("carrier") || Ship.nameShip.equals("carrierv")) {
                                        carrier = lb;
                                        add(carrier);
                                        a = 5;
                                    }
                                    clicked = true;
                                    Ship.but.setEnabled(false);
                                    Ship.lab.setText("ready");
                                    for (int i = 0; i < 5; i++) {
                                        if (coordinate[i][3] == -1) {
                                            coordinate[i][0] = x;
                                            coordinate[i][1] = y;
                                            coordinate[i][2] = x + Ship.sizeX;
                                            coordinate[i][3] = y + Ship.sizeY;
                                            coordinate[i][4] = a;
                                            break;
                                        }
                                    }
                                }
                                location = true;
                            }
                        } else if (rivel == true) {
                            BattleShip.server.dout.setX(x / 25);
                            BattleShip.server.dout.setY(y / 25);
                            BattleShip.server.dout.ss = true; // cho phép gửi dữ liệu
                            try {
                                BufferedImage splash = ImageIO.read(getClass().getResource("/graphics/splash.gif"));
                                ImageIcon icon = new ImageIcon(splash.getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH));
                                lb.setIcon(icon);
                            } catch (IOException ex) {
                            }
                            lb.setBounds(x, y, 25, 25);
                            add(lb);
                            updateUI();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chờ đối thủ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cả 2 phải sẵn sàng thì mới chơi được");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                remove(lb1);
                updateUI();
            }
        });
    }

    @Override

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        /* đổ màu */
        GradientPaint gp = new GradientPaint(0.0f, 0.0f,
                new Color(40, 200, 140), 250.0f, 250.0f,
                new Color(40, 180, 210));
        g2.setPaint(gp);
        g2.fillRect(0, 0, 250, 250);

        /* vẽ dòng */
        g2.setColor(new Color(0, 100, 90));
        for (int i = 0; i < 10; i++) {
            g2.drawLine(i * 25, 0, i * 25, 250);
            g2.drawLine(0, i * 25, 250, i * 25);
        }
    }
}
