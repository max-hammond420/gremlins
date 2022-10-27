package gremlins;

import tileMap.*;
// import gameObject.*;
import gameObject.*;

import gameObject.Character;
import gameObject.Enemy;
// import gameObject.Player;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.event.KeyEvent;

import java.util.*;
import java.io.*;

/**
 * app
 */
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
    private Enemy[] gremlins = {};
    private Shoot[] projectiles = {};

    private int fireballChargeTimer = 0;
    private boolean fireballRecharging = false;


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

        JSONArray levels = new JSONArray();

        levels = conf.getJSONArray("levels");

        JSONObject level1 = levels.getJSONObject(0);
        JSONObject level2 = levels.getJSONObject(1);

        String text1 = level1.get("layout").toString();
        String text2 = level2.get("layout").toString();

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
        // player = new Character('W', currentCharMap, fireball, wizardRight);

        // Load the gremlins
        for (int i = 0; i < currentCharMap.length; i++) {
            for (int j = 0; j < currentCharMap[i].length; j++) {
                if (currentCharMap[i][j] == 'G') {
                    gremlins = Arrays.copyOf(gremlins, gremlins.length + 1);
                    gremlins[gremlins.length - 1] = new Enemy('G', currentCharMap, slime, gremlin, j, i, 1);
                }
                if (currentCharMap[i][j] == 'W') {
                    player = new Character('W', currentCharMap, fireball, wizardRight, j, i, 2);
                }
            }
        }
    }

    /**
     * Receive key pressed signal from the keyboard.
     *
     * @param e integer code of the key pressed
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
            if (!fireballRecharging) {
                projectiles = Arrays.copyOf(projectiles, projectiles.length + 1);
                projectiles[projectiles.length - 1] = new Shoot(fireball, player.getDirection(), currentCharMap, player.getPlayerX(), player.getPlayerY());
                fireballRecharging = true;
            }
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

        currentMap.tick(this, currentCharMap);
        player.tick(this);

        for (int i = 0; i < gremlins.length; i++) {
            gremlins[i].tick(this);
        }



        for (int i = 0; i < projectiles.length; i++) {
            projectiles[i].tick(this);
        }

        fill(255);
        rect(620, 680, 80, 5);

        fill(0);
        rect(620,680,4*fireballChargeTimer,5);

        if (fireballRecharging) {
            fireballChargeTimer++;
            if (fireballChargeTimer > 20 ) {
                fireballChargeTimer = 0;
                fireballRecharging = false;
            }
        }



    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
