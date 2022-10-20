package gameObject;

import tileMap.*;
import gremlins.*;
import gameObject.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


public class Character {

    private PImage projectile;
    private PImage character;

    private char[][] charMap;
    private char name;
    private char direction;

    private int dirVert;
    private int dirHor;

    private int playerX;
    private int playerY;

    private int speed = 2;
    private int shootSpeed = 4;
    private int xVel;
    private int yVel;

    private boolean released = false;
    private boolean xDir = false;
    private boolean yDir = false;

    public Character(char name, char[][] charMap, PImage projectile, PImage character) {
        this.name = name;
        this.charMap = charMap;

        this.character = character;
        this.projectile = projectile;

        getCoords();
    }

    public char[][] getCharMap() { return this.charMap; }

    public int getPlayerX() { return this.playerX; }

    public int getPlayerY() { return this.playerY; }

    public char getDirection() { return this.direction; }

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

    public void draw(App app, int playerX, int playerY) {
        app.image(character, playerX, playerY);
    }

    public void right(PImage newImage) {
        if (!yDir) {
            xVel = speed;
            yVel = 0;
            direction = 'r';
            character = newImage;
            xDir = true;
        }
    }

    public void left(PImage newImage) {
        if (!yDir) {
            xVel = -speed;
            yVel = 0;
            direction = 'l';
            character = newImage;
            xDir = true;
        }
    }

    public void up(PImage newImage) {
        if (!xDir) {
            yVel = -speed;
            xVel = 0;
            direction = 'u';
            character = newImage;
            yDir = true;
        }
    }

    public void down(PImage newImage) {
        if (!xDir) {
            yVel = speed;
            xVel = 0;
            direction = 'd';
            character = newImage;
            yDir = true;
        }
    }

    public void stop() {
        xVel = 0;
        yVel = 0;
        direction = ' ';
    }

    public void release() {
        released = true;
    }

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
        // System.out.println(charMap[y][x]);
        if (charMap[y][x] == 'X' || charMap[y][x] == 'B') {
            return false;
        }
        return true;
    }

    public boolean checkCenter() {
        if (playerX % 20 == 0 && playerY % 20 == 0) {
            return true;
        }
        return false;
    }

    public boolean shoot() {
        return true;
    }

    public void tick(App app, int i) {
        i = i%60;

        boolean correctMove = checkCenter();
        // System.out.println(correctMove);

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

        boolean shoot = shoot();
        if (shoot) {
            Shoot fireball = new Shoot(projectile, direction, playerX, playerY);
            fireball.tick(app);
        }
    }
}
