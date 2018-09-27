/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author mada94
 */
public class Control {

    private JButton start = new JButton("Start");
    public JButton rePlay = new JButton("Re-play");
    private JButton reset = new JButton("Reset");
    private JPanel control = new JPanel();
    private JRadioButton vertical = new JRadioButton("vertical");
    private JRadioButton horizontal = new JRadioButton("horizontal");
    static int select = 1;

    public JPanel getControl() {
        return control;
    }

    public Control() {
        control.setBounds(10, 210, 360, 60);
        control.setLayout(null);

        start.setBounds(10, 0, 100, 60);
        start.setFocusable(false);
        start.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        start.setFont(new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 24));
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (BattleShip.rivel.ready) { // nếu đối thủ đã sẵn sàng khi ta click, thì gửi lại thông báo
                    BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.red));
                    BattleShip.rivel.updateUI();
                    BattleShip.server.dout.setX(-1);
                    BattleShip.server.dout.setY(-2);
                } else {
                    BattleShip.rivel.setBorder(BorderFactory.createLineBorder(Color.green));
                    BattleShip.rivel.updateUI();
                    BattleShip.server.dout.setX(-1);
                    BattleShip.server.dout.setY(-1);
                }

                BattleShip.server.dout.ss = true;
                for (int i = 0; i < 5; i++) {
                    if (Map.coordinate[i][0] / 25 == Map.coordinate[i][2] / 25 - 1) {
                        for (int j = Map.coordinate[i][1] / 25; j < Map.coordinate[i][3] / 25; j++) {
                            BattleShip.corShip[j][Map.coordinate[i][0] / 25] = Map.coordinate[i][4];
                        }
                    } else {
                        for (int j = Map.coordinate[i][0] / 25; j < Map.coordinate[i][2] / 25; j++) {
                            BattleShip.corShip[Map.coordinate[i][1] / 25][j] = Map.coordinate[i][4];
                        }
                    }
                }
                if (ShowShip.battleship.button.isEnabled() == false
                        && ShowShip.carrier.button.isEnabled() == false
//                        && ShowShip.patrol.button.isEnabled() == false
//                        && ShowShip.seawolf.button.isEnabled() == false
//                        && ShowShip.submarine.button.isEnabled() == false
                        ) {
                    // nếu đặt hết tàu rồi nhưng mà đối thủ sẵn sàng trước thì vẫn chưa cho nó đánh
                    if(!BattleShip.rivel.ready){
                        BattleShip.rivel.can = true;
                    }
                    BattleShip.player.can = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Input full ship, please!");
                }
                reset.setEnabled(false);
            }
        });
        control.add(start);

        rePlay.setBounds(130, 0, 80, 25);
        rePlay.setFocusable(false);
        rePlay.setEnabled(false);
        rePlay.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        rePlay.setFont(new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 15));
        rePlay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BattleShip.player.removeAll();
                BattleShip.player.updateUI();
                BattleShip.player.can = true;
                BattleShip.rivel.removeAll();
                BattleShip.rivel.updateUI();
                BattleShip.rivel.can = false;
                BattleShip.thanhtich.removeAll();
                BattleShip.thanhtich.setText(null);
                reset.setEnabled(true);
                ShowShip.battleship.button.setEnabled(true);
                ShowShip.carrier.button.setEnabled(true);
                ShowShip.patrol.button.setEnabled(true);
                ShowShip.seawolf.button.setEnabled(true);
                ShowShip.submarine.button.setEnabled(true);

                ShowShip.labBat.setText("free");
                ShowShip.labCar.setText("free");
                ShowShip.labPat.setText("free");
                ShowShip.labSea.setText("free");
                ShowShip.labSub.setText("free");
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        Map.coordinate[i][j] = -1;
                    }
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        BattleShip.corShip[i][j] = 0;
                    }
                }
            }
        });
        control.add(rePlay);

        reset.setBounds(130, 35, 80, 25);
        reset.setFocusable(false);
        reset.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        reset.setFont(new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 15));
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BattleShip.player.removeAll();
                BattleShip.player.updateUI();
                ShowShip.battleship.button.setEnabled(true);
                ShowShip.carrier.button.setEnabled(true);
                ShowShip.patrol.button.setEnabled(true);
                ShowShip.seawolf.button.setEnabled(true);
                ShowShip.submarine.button.setEnabled(true);

                ShowShip.labBat.setText("free");
                ShowShip.labCar.setText("free");
                ShowShip.labPat.setText("free");
                ShowShip.labSea.setText("free");
                ShowShip.labSub.setText("free");
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        Map.coordinate[i][j] = -1;
                    }
                }

            }
        });
        control.add(reset);

        vertical.setBounds(220, 40, 100, 20);
        vertical.setFocusable(false);
        vertical.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                select = 2;
            }
        });
        horizontal.setBounds(220, 10, 100, 20);
        horizontal.setSelected(true);
        horizontal.setFocusable(false);
        horizontal.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                select = 1;
            }
        });
        control.add(vertical);
        control.add(horizontal);
        ButtonGroup group = new ButtonGroup();
        group.add(vertical);
        group.add(horizontal);
    }
}
