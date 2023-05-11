package main.java.com.group.platformgame.levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class LevelParser {
  private Map<String,String> levelData;

  public LevelParser() {
    levelData = new HashMap<>();
  }

  public void parseFile(String fileName) {
    try(InputStream inputStream = getClass().getResourceAsStream("/resources/levels/" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
      String line;
      String section = null;

      while((line = reader.readLine()) != null) {
        line = line.trim();

        if(line.startsWith("#") || line.isEmpty()) continue;

        if(line.startsWith("[")) {
          section = line.substring(1, line.length() - 1);
          continue;
        }

        int equalIndex = line.indexOf("=");
        if(equalIndex == -1) {
          throw new IllegalArgumentException("Invalid line: " + line);
        }

        String key = line.substring(0, equalIndex);
        String value = line.substring(equalIndex + 1);

        String fullKey = (section != null) ? section + "." + key : key;
        levelData.put(fullKey, value);

      }

    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void printLevelData() {
    for(Map.Entry<String, String> pair : levelData.entrySet()) {
      System.out.println(pair.getKey() + ":" + pair.getValue());
    }
  }
}

