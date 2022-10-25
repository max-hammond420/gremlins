package tileMap;

import processing.core.PImage;

/**
 * Manages individual tiles
 */
public class Tile {

    private char name;
    private boolean isHit = false;
    private PImage image;

    /**
     * Class constructor
     *
     * @param name name of the Tile
     * @param image PImage of the tile to draw
     */
    public Tile(char name, PImage image) {
        this.name = name;
        this.image = image;
    }

    /**
     * getter method
     *
     * @return name of the tile
     */
    public char getName() {
        return this.name;
    }

    /**
     * gets the image of the tile
     *
     * @return the current image
     */
    public PImage getImage() {
        return this.image;
    }

    /**
     * determines whether the tile is isBreakable
     *
     * @return true if it is breakable, false if not
     */
    public boolean isBreakable() {
        if (name == 'X') {
            return true;
        }
        return false;
    }

    /**
     * Getter method
     *
     * @return boolean of if the tile is hit or not
     */
    public boolean getIsHit() {
        return this.isHit;
    }

    /**
     * changes the hit variable to true
     */
    public void hit() {
        this.isHit = true;
    }
}
