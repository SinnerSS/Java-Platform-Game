package main.java.com.group.platformgame.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LevelParser {
    private JSONObject levelGridDesc;
    private JSONObject cameraData;
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
        int x = (int) (long) cameraData.get("x");
        int y = (int) (long) cameraData.get("y");
        return new Camera(x, y);
    }
}

