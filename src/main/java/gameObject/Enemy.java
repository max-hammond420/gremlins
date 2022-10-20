package gameObject;

import tileMap.*;
import gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


public class Enemy extends Character {

    private char name;
    private char[][] charMap;

    private PImage projectile;
    private PImage character;

    private int playerX;
    private int playerY;

    public Enemy(char name, char[][] charMap, PImage projectile, PImage character) {
        super(name, charMap, projectile, character);

        this.name = name;
        this.charMap = charMap;
        this.projectile = projectile;
        this.character = character;
    }

    public char findLongestSpaceDir() {
        // returns direction of the path with the longest white space in charMap

        int currentLongest = 0;
        int longest = 0;

        playerX = super.getPlayerX();
        playerY = super.getPlayerX();

        int i = 0;
        // Check the right
        while (charMap[playerY][playerX + i] == ' ') {
            currentLongest++;
            i++;
        }
        if (currentLongest > longest) {
            longest = currentLongest;
        }
        // Check the left
        i = 0;
        currentLongest = 0;
        while (charMap[playerY][playerX - i] == ' ') {
            currentLongest++;
            i++;
        }
        if (currentLongest > longest) {
            longest = currentLongest;
        }
        // Check the top
        while (charMap[playerY+i][playerX] == ' ') {
            currentLongest++;
            i++;
        }
        if (currentLongest > longest) {
            longest = currentLongest;
        }
        // Check below
        i = 0;
        currentLongest = 0;
        while (charMap[playerY-i][playerX] == ' ') {
            currentLongest++;
            i++;
        }
        if (currentLongest > longest) {
            longest = currentLongest;
        }

        return 'd';
    }

    public void decideMove() {

    }
}

