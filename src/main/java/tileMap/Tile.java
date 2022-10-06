package tileMap; 

import java.util.Scanner;

/* Tile class
 * @param
 * @param
 */
public class Tile {

    private String name;
    private boolean isBreakable;
    private boolean isHit = false;

    /*
     * Constructor method of the Tile public class Tile 
     *
     * @param name name of the Tile
     */
    Tile(String name, boolean isBreakable) {
        this.name = name;
    }

    public String get_name() {
        return this.name;
    }

    public boolean isBreakable() {
        if (name == "brick") {
            isBreakable = true;
        }
        else if (name == "stone") {
            isBreakable = false;
        }
        return isBreakable;
    }

    public boolean get_isHit() {
        return this.isHit;
    }

    public void hit() {
        this.isHit = true;
    }
}
