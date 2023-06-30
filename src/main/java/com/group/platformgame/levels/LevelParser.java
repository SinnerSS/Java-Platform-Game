package main.java.com.group.platformgame.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.com.group.platformgame.gameobjects.character.Player;
import main.java.com.group.platformgame.utils.Rect;

public class LevelParser {
    private JSONObject levelGridDesc;
    private JSONObject cameraData;
    private JSONObject spawnData;
    public LevelParser(String path) {
        parseFile(path);
    }
    private void parseFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/resources/levels/" + path);
            JSONObject fileData = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            levelGridDesc = (JSONObject) fileData.get("levelGrid");
            cameraData = (JSONObject) fileData.get("camera");
            spawnData = (JSONObject) fileData.get("spawnData");
        } catch(IOException|ParseException e) {
            e.printStackTrace();
        } 

    }
    public int[][] getLevelGrid() {
        int rows = (int) (long) levelGridDesc.get("rows");
        int columns = (int) (long) levelGridDesc.get("columns");

        int[][] gridData = new int[rows][columns];

        try {
            InputStream inputStream = getClass().getResourceAsStream("/resources/levels/" + (String) levelGridDesc.get("data"));
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int row = 0;
            while((line = fileReader.readLine()) != null) {
                String[] values = line.trim().split("\\s+");

                for(int col = 0; col < columns; col++){
                    gridData[row][col] = Integer.parseInt(values[col]);
                }

                row++;
                
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return gridData;
    }
    public Camera getCamera() {
        double x =  (double) cameraData.get("x");
        double y =  (double) cameraData.get("y");
        return new Camera(x, y);
    }
    public Player getPlayer() {
        JSONObject playerSpawn = (JSONObject) spawnData.get("player");
        double x = (double) ((JSONObject)playerSpawn.get("position")).get("x");
        double y = (double) ((JSONObject)playerSpawn.get("position")).get("y");
        int width = (int) (long) ((JSONObject)playerSpawn.get("hitbox")).get("width");
        int height = (int) (long) ((JSONObject)playerSpawn.get("hitbox")).get("height");
        return new Player(x, y, new Rect(x, y, width, height));
    }
}

