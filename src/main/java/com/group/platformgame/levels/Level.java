package main.java.com.group.platformgame.levels;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.java.com.group.platformgame.core.GamePanel;
import main.java.com.group.platformgame.gameobjects.character.Player;
import main.java.com.group.platformgame.utils.Loader;

import java.awt.Color;
import java.awt.Graphics2D;

public class Level {
  public static int CELL_HEIGHT = 16;
  public static int CELL_WIDTH = 16;

  private List<TextureMap> mapMem = new ArrayList<>();
  private int[][] gridData;
  private Camera camera;
  private BufferedImage background;
  private Player player;

  public final Object lock = new Object();

  public Level(String levelFile) {
    LevelParser parser = new LevelParser(levelFile);
    gridData = parser.getLevelGrid();
    camera = parser.getCamera();
    player = parser.getPlayer();
    background = Loader.loadBufferedImage("/resources/assets/images/platforms/Background.png");
  }
  
  public BufferedImage getBackground() {
    return background;
  }

  public Camera getCamera() {
    return camera;
  }

  public void update() {
    player.update();
    camera.update(player);
  }

  public void render(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, 1280, 1024);

    g2d.drawImage(background, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

    AffineTransform originalTransform = g2d.getTransform();

    g2d = camera.applyTransformation(g2d);

    int numRows = gridData.length - 1;
    int numCols = gridData[0].length - 1;


    int startX = Math.max(0, (camera.getX() - camera.getVisibleWidth()) / CELL_WIDTH);
    int startY = Math.max(0, (camera.getY() - camera.getVisibleHeight()) / CELL_HEIGHT);

    int endX = Math.min(numCols, (camera.getX() + camera.getVisibleWidth()) / CELL_WIDTH + 1);
    int endY = Math.min(numRows, (camera.getY() + camera.getVisibleHeight()) / CELL_HEIGHT + 1);

    for (int row = startY; row < endY; row++) {
      for (int col = startX; col < endX; col++) {
        int textureKey = gridData[row][col];
        BufferedImage textureImage = null;

        for (TextureMap type : TextureMap.values()) {
          if (type.inRange(textureKey)) {
            if (!mapMem.contains(type)) mapMem.add(type);
            textureImage = type.loadTexture(textureKey);
          }
        }

        int xPos = col * CELL_WIDTH;
        int yPos = row * CELL_HEIGHT;

        g2d.drawImage(textureImage, xPos, yPos, null);
      }
    }
    
    player.render(g2d);

    g2d.setTransform(originalTransform);
  }

  public Player getPlayer() {
    return player;
  }
}
