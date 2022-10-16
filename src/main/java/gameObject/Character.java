package gameObject;

import tileMap.*;
import gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


public class Character {

    private PImage character;
    private char[][] charMap;
    //protected Tile[][] tileMap;
    private char name;
    

    public Character(char name, PImage character, char[][] charMap) {
        this.name = name;
        this.character = character;
        this.charMap = charMap;
        // this.tileMap = tileMap;
    }

    public void drawCharacter(App app) {

        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap[i].length; j++) {
                if (charMap[i][j] == this.name) {
                    app.image(character, j*20, i*20);
                }
            }
        }
    }
}

