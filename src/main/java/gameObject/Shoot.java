package gameObject;

import tileMap.*;
import gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;

/**
 * Class to manage projectiles from the player or gremlins
 */
public class Shoot {

    private PImage projectile;
    private char direction;
    private int x;
    private int y;
    private int xVel;
    private int yVel;
    private char[][] charMap;

    private int speed = 4;

    /**
     * Class constructor
     *
     * @param projectile PImage of the projectile to Shoot
     * @param direction char of the direction the projectile travles
     * @param charMap the current char map
     * @param x current x position of the player/gremlin
     * @param y current y position of the player/gremlin
     */
    public Shoot(PImage projectile, char direction, char[][] charMap, int x, int y) {
        this.projectile = projectile;
        this.direction = direction;
        this.x = x;
        this.y = y;

        this.charMap = charMap;
    }

    /**
     * Getter method
     *
     * @return 2d char array
     */
    public char[][] getCharMap() { return charMap; }

    /**
     * Getter method
     *
     * @return x position
     */
    public int getX() { return x; }

    /**
     * Getter method
     *
     * @return y position
     */
    public int getY() { return y; }

    /**
     * moves the current x and y position of the projectile in the intended direction
     */
    public void move() {
        if (direction == 'l') {
            xVel = -speed;
        }
        else if (direction == 'u') {
            yVel = -speed;
        }
        else if (direction == 'r') {
            xVel = speed;
        }
        else if (direction == 'd') {
            yVel = speed;
        }

    }

    /**
     * determines if the projectile has hit a tile
     *
     * @return true if it has, false if not
     */
    public boolean collision() {
        if (charMap[y/20][x/20] == 'B' || charMap[y/20][x/20] == 'X') {
            return true;
        }
        return false;
    }

    /**
     * draws the projectile
     *
     * @param app the current app
     */
    public void draw(App app) {
        app.image(projectile, x, y);
    }

    /**
     * runs every frame, runs logic of whether the fireball should keep going or not, and draws
     *
     * @param app the current app
     */
    public void tick(App app) {
        move();
        if (!collision()) {
            draw(app);
            x += xVel;
            y += yVel;
        } else {
            // charMap[y/20][x/20] = ' ';
        }
    }
}
