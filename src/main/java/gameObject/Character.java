package gameObject;

import tileMap.*;
import gremlins.*;
import gameObject.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


/**
 * Class to manage wizards and gremlins
 */
public class Character {

    private PImage character;
    private PImage projectile;

    private char[][] charMap;
    private char name;
    private char direction = 'r';

    private int playerX;
    private int playerY;

    private int speed;
    private int xVel;
    private int yVel;

    private boolean released = false;
    private boolean xDir = false;
    private boolean yDir = false;

    /**
     * Class constructor
     * 
     * @param name char of the wizard or gremlin
     * @param charMap 2d character array of the current map
     * @param projectile PImage of the projectile the player shoots
     * @param character PImage of the current character
     * @param x x coordinate in the current charMap
     * @param y y coordinate in the current charMap
     * @param speed the amount of pixels moved per second
     */
    public Character(char name, char[][] charMap, PImage projectile, PImage character, int x, int y, int speed) {
        this.name = name;
        this.charMap = charMap;

        this.character = character;
        this.projectile = projectile;

        this.playerX = x*20;
        this.playerY = y*20;

        this.speed = speed;
    }

    /**
     * Getter method
     *
     * @return 2d char array ofthe current map the character is reading
     */
    public char[][] getCharMap() { return this.charMap; }

    /**
     * Getter method
     *
     * @return the current X coordinate
     */
    public int getPlayerX() { return this.playerX; }

    /**
     * Getter method
     *
     * @return the current Y coordinate
     */
    public int getPlayerY() { return this.playerY; }

    /**
     * Getter method
     *
     * @return the current direction
     */
    public char getDirection() { return this.direction; }

    /**
     * Gets the current coordinates from of the name from the character map
     */
    public void getCoords() {
        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap[i].length; j++) {
                if (charMap[i][j] == this.name) {
                    playerX = j*20;
                    playerY = i*20;
                }
            }
        }
    }

    /**
     * Draws the character to the screen using the X and Y coordinates
     *
     * @param app App object of the current app
     * @param playerX current X coordinate
     * @param playerY current Y coordinate
     */
    public void draw(App app, int playerX, int playerY) {
        app.image(character, playerX, playerY);
    }

    /**
     * moves the character right by the amount of pixels specified in speed
     *
     * @param newImage the new image that the character takes
     */
    public void right(PImage newImage) {
        if (!yDir) {
            xVel = speed;
            yVel = 0;
            direction = 'r';
            character = newImage;
            xDir = true;
        }
    }

    /**
     * moves the character left by the amount of pixels specified in speed
     *
     * @param newImage the new image that the character takes
     */
    public void left(PImage newImage) {
        if (!yDir) {
            xVel = -speed;
            yVel = 0;
            direction = 'l';
            character = newImage;
            xDir = true;
        }
    }

    /**
     * moves the character up by the amount of pixels specified in speed
     *
     * @param newImage the new image that the character takes
     */
    public void up(PImage newImage) {
        if (!xDir) {
            yVel = -speed;
            xVel = 0;
            direction = 'u';
            character = newImage;
            yDir = true;
        }
    }

    /**
     * moves the character down by the amount of pixels specified in speed
     *
     * @param newImage the new image that the character takes
     */
    public void down(PImage newImage) {
        if (!xDir) {
            yVel = speed;
            xVel = 0;
            direction = 'd';
            character = newImage;
            yDir = true;
        }
    }

    /**
     * sets the current speed to 0, creating the character to stop moving
     */
    public void stop() {
        xVel = 0;
        yVel = 0;
    }

    /**
     * sets a variable to true if the arrow keys have been released
     */
    public void release() {
        released = true;
    }

    /**
     * Checks if the direction it is about to move in is a valid move
     *
     * @return true if it is a valid move, false if not
     */
    public boolean validMove() {
        int xDiff = 0;
        int yDiff = 0;
        if (xVel != 0) {
            if (xVel < 0) {
                xDiff = -1;
            } else {
                xDiff = 1;
            }
        } else if (yVel != 0) {
            if (yVel < 0) {
                yDiff = -1;
            } else {
                yDiff = 1;
            }
        }
        int y = (playerY / 20) + yDiff;
        int x = (playerX / 20) + xDiff;
        if (charMap[y][x] == 'X' || charMap[y][x] == 'B') {
            return false;
        }
        return true;
    }

    /**
     * Checks if the current X and Y position of the coordinates are in the center of a tile
     *
     * @return true if character is in the center, false if else.
     */
    public boolean checkCenter() {
        if (playerX % 20 == 0 && playerY % 20 == 0) {
            return true;
        }
        return false;
    }

    /**
     * Called every frame of the game,
     * is responsible for moving the character and making every move valid
     *
     * @param app the current app
     */
    public void tick(App app ) {

        boolean correctMove = checkCenter();

        if (released) {
            if (correctMove) {
                stop();
                released = false;
                yDir = false;
                xDir = false;
            }
        }
        if (correctMove) {
            if (!validMove()) {
                yDir = false;
                xDir = false;
            }
        }

        if (yDir) {
            playerY += yVel;
        }
        if (xDir) {
            playerX += xVel;
        }

        draw(app, playerX, playerY);
    }
}
