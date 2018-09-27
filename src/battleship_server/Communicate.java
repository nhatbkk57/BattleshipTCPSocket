/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author mada94
 */
public class Communicate extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0, 100, 90));
        g2.drawLine(0, 140, 250, 140);
        g2.drawLine(0, 10, 0, 140);
        g2.drawLine(250, 10, 250, 140);
        g2.drawLine(0, 10, 25, 10);
        g2.drawLine(60, 10, 250, 10);
    }

    private JLabel nameChat = new JLabel("Chat");
    private JTextArea conten = new JTextArea();
    private JScrollPane scroll_conten = new JScrollPane(conten);
    private JTextField input = new JTextField();
    private JLabel send = new JLabel("Send");
    private boolean ck = false;

    public void appendConten(String str) {
        this.conten.append(str);
    }

    /**
     * hàm cài lại text cho ô nhập
     *
     * @param str
     */
    public void resetText() {
        this.input.setText("");
        ck = false;
    }

    /**
     * hàm lấy text ra, trong TH cho phép lấy thì trả về đầy đủ, không cho phép
     * trả về ""
     *
     * @return
     */
    public String getTextInput() {

        if (ck) {
            String str = this.input.getText();
            this.input.setText("");
            ck = false;
            return str;

        } else {
            return "";
        }
    }

    public Communicate() {
        setBounds(10, 470, 260, 150);
        setBackground(Color.red);
        setLayout(null);

        nameChat.setBounds(30, 0, 50, 20);
        add(nameChat);

        conten.setEditable(false);
        scroll_conten.setBounds(5, 20, 240, 80);
        scroll_conten.setFocusable(false);
        add(scroll_conten);

        input.setBounds(5, 110, 200, 25);
        add(input);

        send.setBounds(210, 110, 35, 25);
        send.setFocusable(false);
        send.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                ck = true;
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        input.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
                if(ke.getKeyChar() == KeyEvent.VK_ENTER){
                    ck = true;
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        send.setHorizontalAlignment(JLabel.CENTER);
        send.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        send.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
        add(send);
    }
}
