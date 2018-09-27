/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_client;

/**
 *
 * @author mada94
 */
public class Location {

    public Location(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (x >= Map.coordinate[i][0] && x < Map.coordinate[i][2]
                    && y >= Map.coordinate[i][1] && y < Map.coordinate[i][3]) {
                Map.location = false;
            } else if (x >= Map.coordinate[i][0] && x < Map.coordinate[i][2]
                    && y + Ship.sizeY > Map.coordinate[i][1] && y + Ship.sizeY <= Map.coordinate[i][3]) {
                Map.location = false;
            } else if (x + Ship.sizeX > Map.coordinate[i][0] && x + Ship.sizeX <= Map.coordinate[i][2]
                    && y >= Map.coordinate[i][1] && y < Map.coordinate[i][3]) {
                Map.location = false;
            } else if (x + Ship.sizeX > Map.coordinate[i][0] && x + Ship.sizeX <= Map.coordinate[i][2]
                    && y + Ship.sizeY > Map.coordinate[i][1] && y + Ship.sizeY <= Map.coordinate[i][3]) {
                Map.location = false;
            } else if (Map.coordinate[i][0] >= x && Map.coordinate[i][0] < x + Ship.sizeX
                    && Map.coordinate[i][1] >= y && Map.coordinate[i][1] < y + Ship.sizeY) {
                Map.location = false;
            } else if (Map.coordinate[i][0] >= x && Map.coordinate[i][0] < x + Ship.sizeX
                    && Map.coordinate[i][3] > y && Map.coordinate[i][3] <= y + Ship.sizeY) {
                Map.location = false;
            } else if (Map.coordinate[i][2] > x && Map.coordinate[i][2] <= x + Ship.sizeX
                    && Map.coordinate[i][1] >= y && Map.coordinate[i][1] < y + Ship.sizeY) {
                Map.location = false;
            } else if (Map.coordinate[i][2] > x && Map.coordinate[i][2] <= x + Ship.sizeX
                    && Map.coordinate[i][3] > y && Map.coordinate[i][3] <= y + Ship.sizeY) {
                Map.location = false;
            }
            if (Ship.sizeX == 125 || Ship.sizeY == 125) {
                float a = (float) (Map.coordinate[i][0] + Map.coordinate[i][2]) / 2;
                float b = (float) (Map.coordinate[i][1] + Map.coordinate[i][3]) / 2;
                if (a >= x && a <= x + Ship.sizeX && b >= y && b <= y + Ship.sizeY) {
                    Map.location = false;
                }
            } else {
                float a = (float) (x + x + Ship.sizeX) / 2;
                float b = (float) (y + y + Ship.sizeY) / 2;
                if (a >= Map.coordinate[i][0] && a <= Map.coordinate[i][2]
                        && b >= Map.coordinate[i][1] && b <= Map.coordinate[i][3]) {
                    Map.location = false;
                }
            }
        }
    }
}
