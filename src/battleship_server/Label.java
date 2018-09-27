/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship_server;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author mada94
 */
public class Label extends JLabel{
    public Label(int n, int m){
        setBounds(n, m, 80, 30);
        //setText(a);
        setFont(new Font(Font.DIALOG_INPUT, Font.ITALIC, 17));
    }
}
