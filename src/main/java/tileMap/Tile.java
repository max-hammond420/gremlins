package tileMap;

import processing.core.PImage;

public class Tile {

    private char name;
    private boolean isBreakable;
    private boolean isHit = false;
    private PImage image;

    /**
     * Constructor method of the Tile public class Tile
     *
     * @param name name of the Tile
     */
    public Tile(char name, PImage image) {
        this.name = name;
        this.image = image;
    }

    public String toString() {
        String s = "" + name;
        return s;
    }

    public char getName() {
        return this.name;
    }

    public PImage getImage() {
        return this.image;
    }

    public boolean isBreakable() {
        if (name == 'X') {
            isBreakable = true;
        }
        else if (name == 'B') {
            isBreakable = false;
        }
        else if (name == 'W') {
            isBreakable = false;
        }
        else if (name == 'G') {
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
