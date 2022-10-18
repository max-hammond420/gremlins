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

    public Enemy(char name, char[][] charMap, PImage projectile, PImage character) {
        super(name, charMap, projectile, character);

        this.name = name;
        this.charMap = charMap;
        this.projectile = projectile;
        this.character = character;
    }
}

