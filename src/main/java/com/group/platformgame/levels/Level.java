package main.java.com.group.platformgame.levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

public class Level {
  public static int CELL_HEIGHT = 16;
  public static int CELL_WIDTH = 16;

  private List<TextureMap> mapMem = new ArrayList<>();
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

  public void render(Graphics2D g2d) {
    int numRows = gridData.length;
    int numCols = gridData[0].length;

    int startX = Math.max(0, camera.getX() / CELL_WIDTH); 
    int startY = Math.max(0, camera.getY() / CELL_HEIGHT); 
    
    int endX = Math.min(numRows, (camera.getX() + 1280) / CELL_WIDTH + 1); 
    int endY = Math.min(numCols, (camera.getY() + 1024) / CELL_HEIGHT + 1); 
    
    for (int row = startX; row < endX; row++) {
      for (int col = startY; col < endY; col++) {
        int textureKey = gridData[row][col];
        BufferedImage textureImage = null;

        for(TextureMap type : TextureMap.values()) {
          if(type.inRange(textureKey)) {
            if(!mapMem.contains(type)) mapMem.add(type);
            textureImage = type.loadTexture(textureKey);
          }
        }
      
      

        int xPos = col * CELL_WIDTH; 
        int yPos = row * CELL_HEIGHT; 
          

        Point transformedPos = camera.applyTransformation(xPos, yPos);
          
        
        g2d.drawImage(textureImage, transformedPos.x, transformedPos.y, null);
      }
    }
  }
}
