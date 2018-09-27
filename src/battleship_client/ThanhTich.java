/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship_client;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**
 *
 * @author mada94
 */
public class ThanhTich extends JTextArea{
    public ThanhTich(){
        setBounds(10, 340, 250, 130);
        setBorder(BorderFactory.createLineBorder(new Color(0, 100, 90), 1));
        setFocusable(false);
    }
}
