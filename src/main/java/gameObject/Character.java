package gameObject;

import tileMap.*;
import gremlins.*;

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

    int dirVert;
    int dirHor;

    int playerX;
    int playerY;
    

    public Character(char name, char[][] charMap, PImage projectile, PImage character) {
        this.name = name;
        this.charMap = charMap;

        this.character = character;
        this.projectile = projectile;
    }

    public void draw(App app) {

        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap[i].length; j++) {
                if (charMap[i][j] == this.name) {
                    app.image(character, j*20, i*20);
                    playerX = j;
                    playerY = i;
                }
            }
        }

    }

    public boolean checkMove(char direction, int playerX, int playerY) {
        if (direction == 'u') {
            dirVert = 1;
        } else if (direction == 'd') {
            dirVert = -1;
        } else if (direction == 'l') {
            dirHor = -1;
        } else if (direction == 'r') {
            dirHor = 1;
        }
        if (charMap[playerY+dirVert][playerX+dirHor] != ' ') {
            return false;
        }
        return true;
    }

    public void move(App app, char direction, PImage newImage) {
        // get x and y coords of the player
        System.out.println("Player X: " + playerX);
        System.out.println("Player Y: " + playerY);
        System.out.println(checkMove(direction, playerX, playerY));
        System.out.println();

        // Check if valid move
        if (checkMove(direction, playerX, playerY)) {
            charMap[playerY][playerX] = ' ';
            if (direction == 'u') {
                playerY++;
            } else if (direction == 'd') {
                playerY--;
            } else if (direction == 'l') {
                playerX--;
            } else if (direction == 'r') {
                playerX++;
            }
        }
        charMap[playerY][playerX] = name;
        character = newImage;
        dirVert = 0;
        dirHor = 0;
    }
}

