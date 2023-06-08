package main.java.com.group.platformgame.levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

public class Level {

  private int[][] gridData;
  private Camera camera;
  public Level(String levelFile) {
    LevelParser parser = new LevelParser(levelFile);
    gridData = parser.getLevelGrid();
    camera = parser.getCamera();
  }
  
  public Camera getCamera() {
    return camera;
  }
}


