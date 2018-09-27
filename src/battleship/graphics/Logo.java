/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author mada94
 */
public class Logo extends JLabel{
    public Logo(){
        setSize(100, 250);
        try {
                    BufferedImage road = ImageIO.read(getClass().getResource("/graphics/logo.jpg"));
                    ImageIcon icon = new ImageIcon(road.getScaledInstance(100, 250, BufferedImage.SCALE_SMOOTH));
                    setIcon(icon);
                } catch (IOException ex) {
                }
    }
} 
