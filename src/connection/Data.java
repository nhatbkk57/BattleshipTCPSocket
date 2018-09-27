/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.Serializable;

/**
 *
 * @author Dai
 */
public class Data implements Serializable{


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
    private int y;
    public boolean ss;
    private int x;
    
    
    public Data(int x, int y){
        this.x = x;
        this.y = y;
        ss = false;
    }
}
