package gameObject;

import tileMap.*;
import gremlins.*;

import java.util.*;
import java.io.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


/**
 * Class to manage gremlins, extends the Character class
 */
public class Enemy extends Character {

    Random ran = new Random();
    int dir = ran.nextInt(4);

    private PImage character;

    /**
     * Class constructor
     *
     * @param name name on the level
     * @param charMap 2d char array
     * @param projectile PImage of the projectile associated with the name
     * @param character PImage of the character
     * @param x current x coordinate
     * @param y current y coordinate
     * @param speed amount of pixels moved per second
     */
    public Enemy(char name, char[][] charMap, PImage projectile, PImage character, int x, int y, int speed) {
        super(name, charMap, projectile, character, x, y, speed);

        this.character = character;
    }

    /**
     * Decides which direction to move the gremlin
     */
    public void decideMove() {

        Random ran = new Random();
        int dir = ran.nextInt(4);
        if (!super.validMove()) {
            dir = ran.nextInt(4);
        }


        if (dir == 0) {
            super.right(this.character);
        } else if (dir == 2) {
            super.left(this.character); 
        } else if (dir == 3) {
            super.up(this.character); 
        } else {
            super.down(this.character); 
        }
    }
}

