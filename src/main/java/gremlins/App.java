package gremlins;

import tileMap.TileMap;
import tileMap.Tile;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;

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
    public PImage wizard0;

    public TileMap map1;
    public TileMap map2;
    private Tile[][] currentMap;


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
        this.wizard0 = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", ""));
        

        JSONObject conf = loadJSONObject(new File(this.configPath));

        String folder = "level1.txt";
        map1 = new TileMap(folder, stonewall, brickwall);
        // System.out.println(map1);
        currentMap = map1.get_tileMap();

        // System.out.println(Arrays.deepToString(currentMap));
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){

    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }


    /**
     * Draw all elements in the game by current frame. 
	  */
    public void draw() {
        // currentMap.draw();
        
        // set background colour
        background(200, 157, 124);
       
        // Draw map
        for (int i = 0; i < currentMap.length; i++) {
            for (int j = 0; j < currentMap[i].length; j++) {
                if (currentMap[i][j].get_image() != null) {
                    image(currentMap[i][j].get_image(), j*20, i*20);
                } 
            }
        }
        
    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
