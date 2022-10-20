package gameObject;

import tileMap.*;
import gremlins.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;

public class Shoot {

    public PImage projectile;
    public char direction;
    public int x;
    public int y;

    public int speed = 4;

    public Shoot(PImage projectile, char direction, int x, int y) {
        this.projectile = projectile;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public void decideDirection() {
        if (direction == 'l') {

        }
        else if (direction == 'u') {
        }
        else if (direction == 'r') {
        }
        else if (direction == 'd') {
        }

    }

    public void draw() {
    }

    public void tick(App app) {

    }
}
