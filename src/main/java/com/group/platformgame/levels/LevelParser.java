package main.java.com.group.platformgame.levels;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LevelParser {
    private JSONObject levelGridData;
    private JSONObject cameraData;
    public LevelParser(String path) {
        parseFile(path);
    }
    private void parseFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/resources/levels/" + path);
            JSONObject fileData = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            levelGridData = (JSONObject) fileData.get("levelGrid");
            cameraData = (JSONObject) fileData.get("camera");
        } catch(IOException|ParseException e) {
            e.printStackTrace();
        } 
    }
    public LevelGrid getLevelGrid() {
        int cellHeight = (int) (long) levelGridData.get("cellHeight");
        int cellWidth = (int) (long) levelGridData.get("cellWidth");
        int rows = (int) (long) levelGridData.get("rows");
        int columns = (int) (long) levelGridData.get("columns");
        return new LevelGrid(cellHeight, cellWidth, rows, columns);
    }
    public Camera getCamera() {
        int x = (int) (long) cameraData.get("x");
        int y = (int) (long) cameraData.get("y");
        return new Camera(x, y);
    }
}

