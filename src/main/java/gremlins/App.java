package gremlins;

import tileMap.*;
// import gameObject.*;
import gameObject.*;

import gameObject.Character;
import gameObject.Enemy;
import gameObject.Player;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.event.KeyEvent;

import java.util.*;
import java.io.*;


public class App extends PApplet {

	public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;

    public static final int FPS = 60;

    public static final Random randomGenerator = new Random();

    public String configPath;

    public PImage stonewall;
    public PImage brickwall;
    public PImage gremlin;
    public PImage slime;
    public PImage fireball;

    public PImage wizardLeft;
    public PImage wizardRight;
    public PImage wizardUp;
    public PImage wizardDown;

    private TileMap map1;
    private TileMap map2;
    private char[][] currentCharMap;
    private TileMap currentMap;

    private Character player;
    private Character gremlins;
    private Character[] gremlinss;

    int i = 0;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        // Load images during setup
        this.stonewall = loadImage(this.getClass().getResource("stonewall.png").getPath().replace("%20", ""));
        this.brickwall = loadImage(this.getClass().getResource("brickwall.png").getPath().replace("%20", ""));
        this.gremlin = loadImage(this.getClass().getResource("gremlin.png").getPath().replace("%20", ""));
        this.slime = loadImage(this.getClass().getResource("slime.png").getPath().replace("%20", ""));
        this.fireball = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", ""));

        this.wizardRight = loadImage(this.getClass().getResource("wizard1.png").getPath().replace("%20", ""));
        this.wizardLeft = loadImage(this.getClass().getResource("wizard0.png").getPath().replace("%20", ""));
        this.wizardUp = loadImage(this.getClass().getResource("wizard2.png").getPath().replace("%20", ""));
        this.wizardDown = loadImage(this.getClass().getResource("wizard3.png").getPath().replace("%20", ""));


        JSONObject conf = loadJSONObject(new File(this.configPath));

        String text1 = "level1.txt";
        String text2 = "level2.txt";
        map1 = new TileMap(text1, stonewall, brickwall);
        map2 = new TileMap(text2, stonewall, brickwall);

        // level 1
        // currentMapChar = map1.getTileMap();
        currentMap = map1;

        // Level 2
        // currentMapChar = map2.getTileMap();
        // currentMap = map2.getTileMapObj(currentMapChar);

        currentCharMap = currentMap.getCharMap();

        // Load the wizard
        player = new Character('W', currentCharMap, fireball, wizardRight);

        gremlins = new Enemy('G',currentCharMap, slime, gremlin);


        // Load the gremlins
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if (key == 39) { // right
            player.right(wizardRight);
        } else if (key == 37) { // left
            player.left(wizardLeft);
        } else if (key == 38) { // up
            player.up(wizardUp);
        } else if (key == 40) { // down
            player.down(wizardDown);
        } else if (key == ' ') {
            player.shoot();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 37 || key == 38 || key == 39 || key == 40) {
            player.release();
        }
    }

    /**
     * Draw all elements in the game by current frame.
	  */
    public void draw() {
        // set background colour
        background(200, 157, 124);

        textSize(20);
        text("Lives:", 5, 700);
        image(wizardLeft, 70,685);
        image(wizardLeft, 90, 685);
        image(wizardLeft, 110, 685);


        // Draw map

        currentMap.draw(this);
        player.tick(this, i);
        // gremlins.draw(this);

        i++;
    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
