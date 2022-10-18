package gameObject;

import tileMap.*;
import gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


public class Player extends Character {

    private char name;
    private char[][] charMap;

    private PImage projectile;
    private PImage characterRight;
    private PImage characterLeft;
    private PImage characterUp;
    private PImage characterDown;


    public Player(char name, char[][] charMap, PImage projectile, PImage characterRight, PImage characterLeft,
            PImage characterUp, PImage characterDown) {
        super(name, charMap, projectile, characterRight);

        this.name = name;
        this.charMap = charMap;

        this.projectile = projectile;
        this.characterRight = characterRight;
        this.characterLeft = characterLeft;
        this.characterUp = characterUp;
        this.characterDown = characterDown;
    }
}

