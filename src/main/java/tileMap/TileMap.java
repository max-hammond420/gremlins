package tileMap;

import java.io.*;
import java.util.*;
import gremlins.App;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;


public class TileMap {

    private String f;
    private char[][] map;
    private Tile[][] mapObj;

    private PImage image;

    public PImage stonewall;
    public PImage brickwall;

    public TileMap(String f, PImage stonewall, PImage brickwall) {
        this.f = f;      
        this.stonewall = stonewall;
        this.brickwall = brickwall;

        getCharMap();
        getTileMap(map);
    }

    public char[][] getCharMap() {
        
        map = new char[33][36];

        try {
            // Open file
            File file = new File(this.f);
            Scanner sc = new Scanner(file); 

            int i = 0;
            // Iterate over text file, and convert it to a char array
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                for (int j = 0; j < line.length(); j++) {
                    map[i][j] = line.charAt(j);
                }

                i++;
            }

            sc.close();

        } catch (FileNotFoundException e) { // Error handling for file not found
            e.printStackTrace();
        }

        return map;
    }

    public Tile[][] getTileMap(char[][] map) {

        // Convert chars in map array to Tile objects in a new array
        // and returns
        mapObj = new Tile[33][36];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'X') {
                    this.image = stonewall;
                }
                else if (map[i][j] == 'B') {
                    this.image = brickwall;
                }
                else {
                    this.image = null;
                }
                // System.out.println(map[i][j]);
                mapObj[i][j] = new Tile(map[i][j], image);
            }
        }

        return mapObj;
    }

    public void draw(App app) {

        for (int i = 0; i < mapObj.length; i++) {
            for (int j = 0; j < mapObj[i].length; j++) {
                if (mapObj[i][j].getImage() != null) {
                    app.image(mapObj[i][j].getImage(), j*20, i*20);
                    
                }
            }
        }
    }
}
