/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship_client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mada94
 */
public class ShowShip extends JPanel{
    
    static Ship battleship = new Ship("battleship");
    static Ship carrier = new Ship("carrier");
    static Ship patrol = new Ship("patrol");
    static Ship seawolf = new Ship("seawolf");
    static Ship submarine = new Ship("submarine");
    private JLabel ship = new JLabel("Ships");
    private JLabel size = new JLabel("Size");
    private JLabel status = new JLabel("Status");
    private Label label;
    static Label labBat = new Label(290, 65);
    static Label labCar = new Label(290, 30);
    static Label labSea = new Label(290, 100);
    static Label labSub = new Label(290, 135);
    static Label labPat = new Label(290, 170);
    public Control control = new Control();
    
    public ShowShip(){
        setBounds(270, 330, 380, 300);
        setLayout(null);
        
        ship.setBounds(80, 0, 50, 15);
        add(ship);
        size.setBounds(215, 0, 50, 15);
        add(size);
        status.setBounds(300, 0, 50, 15);
        add(status);
        
        carrier.button.setBounds(10, 30, 180, 30);
        add(carrier.button);
        label = new Label(220, 30);
        label.setText("5");
        add(label);
        labCar.setText("free");
        add(labCar);
        
        battleship.button.setBounds(10, 65, 180, 30);
        add(battleship.button);
        label = new Label(220, 65);
        label.setText("4");
        add(label);
        labBat.setText("free");
        add(labBat);
        
        seawolf.button.setBounds(10, 100, 180, 30);
        add(seawolf.button);
        label = new Label(220, 100);
        label.setText("4");
        add(label);
        labSea.setText("free");
        add(labSea);
        
        submarine.button.setBounds(10, 135, 180, 30);
        add(submarine.button);
        label = new Label(220, 135);
        label.setText("3");
        add(label);
        labSub.setText("free");
        add(labSub);
        
        patrol.button.setBounds(10, 170, 180, 30);
        add(patrol.button);
        label = new Label(220, 170);
        label.setText("2");
        add(label);
        labPat.setText("free");
        add(labPat);
        
        add(control.getControl());
    }
}
