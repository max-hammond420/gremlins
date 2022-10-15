package gameObject;

import tileMap.*;
import gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


public class Character {

    private PImage character;
    protected Tile[][] currentMap;

    public Character(Tile[][] currentMap) {
        this.currentMap = currentMap;
    }

    public Tile[][] drawCharacter() {
    }
}

