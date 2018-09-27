/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship_server;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author mada94
 */
public class MenuBar {
    private final JMenuBar menu = new JMenuBar();
    private final JMenu option, game;
    private final JMenuItem newGame, sound;
    
    public JMenuBar getMenu(){
        return menu;
    }
    public MenuBar(){
        game = new JMenu("Game");
        game.setMnemonic('G');
        
        newGame = new JMenuItem("New game");
        newGame.setMnemonic('N');
        game.add(newGame);
        
        menu.add(game);
        
        option = new JMenu("Option");
        option.setMnemonic('O');
        
        sound = new JMenuItem("Sound");
        sound.setMnemonic('S');
        option.add(sound);
        
        menu.add(option);
        menu.setBounds(0, 0, 660, 20);
    }
}
