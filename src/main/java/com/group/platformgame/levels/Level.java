package main.java.com.group.platformgame.levels;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.group.platformgame.core.GamePanel;
import main.java.com.group.platformgame.gameobjects.character.Enemy;
import main.java.com.group.platformgame.gameobjects.character.FlyingEye;
import main.java.com.group.platformgame.gameobjects.character.Player;
import main.java.com.group.platformgame.gameobjects.platform.Platform;
import main.java.com.group.platformgame.gameobjects.platform.GroundTile;
import main.java.com.group.platformgame.utils.Loader;
import main.java.com.group.platformgame.utils.Rect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Level {
  public static int CELL_HEIGHT = 16;
  public static int CELL_WIDTH = 16;

  private Map<Integer, BufferedImage> textureCache = new HashMap<>();
  private List<Enemy> enemies = new ArrayList<>();
  private Platform[][] activePool;
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
    activePool = new Platform[gridData.length][gridData[0].length];
    enemies.add(new FlyingEye(80, 80, new Rect(80, 80, 28, 22), 48, 0, 200, 50, player));
    poolUpdate();
  }

  public BufferedImage getBackground() {
    return background;
  }

  public Camera getCamera() {
    return camera;
  }

  public void update(double delta) {
    enemies.get(0).update(delta);
    player.update(delta);
    checkCollision();
    camera.update(player, this);
    poolUpdate();
  }

  private void checkCollision() { 
    List<Rect> hitboxCollided = new ArrayList<>(); 
    Rect playerHitbox = player.getHitbox();

    do {
      hitboxCollided.clear();
      int startX = (int) player.pos.x / CELL_WIDTH;
      int startY = (int) player.pos.y / CELL_HEIGHT;

      int endX = (int) (playerHitbox.pos.x + playerHitbox.getWidth()) / CELL_WIDTH + 2;
      int endY = (int) (playerHitbox.pos.y + playerHitbox.getWidth()) / CELL_HEIGHT + 2;

      for(int row = endY - 1; row >= startY; row--) {
        for(int col = startX; col < endX; col++) {
          if(activePool[row][col] != null) {
            Rect cellHitbox = activePool[row][col].getHitbox();
            if(cellHitbox.intersects(playerHitbox)) {
              hitboxCollided.add(cellHitbox);
            }
          }
        }
      }
      collisionResolve(playerHitbox, hitboxCollided);
    } while(!hitboxCollided.isEmpty());
  }
  private void collisionResolve(Rect playerHitbox, List<Rect> hitboxCollided) {
    if(player.getVel().x < 0) Collections.reverse(hitboxCollided);
    for (Rect hitbox : hitboxCollided) {
      double penetrationX = Math.min(hitbox.pos.x + hitbox.size.x - playerHitbox.pos.x, playerHitbox.pos.x + playerHitbox.size.x - hitbox.pos.x);
      double penetrationY = Math.min(hitbox.pos.y + hitbox.size.y - playerHitbox.pos.y, playerHitbox.pos.y + playerHitbox.size.y - hitbox.pos.y);
      if (penetrationX < penetrationY) {
        if (playerHitbox.pos.x < hitbox.pos.x) 
          player.setX(player.pos.x - penetrationX);
        else
          player.setX(player.pos.x + penetrationX);
      } else {
        if (playerHitbox.pos.y < hitbox.pos.y) {
          if(player.isJumping) player.isJumping = false;
          player.setY(player.pos.y - penetrationY);
        }
        else {
          player.setY(player.pos.y + penetrationY);
        }
      }
    }
  }

  public void render(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, 1280, 1024);

    g2d.drawImage(background, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

    AffineTransform originalTransform = g2d.getTransform();

    g2d = camera.applyTransformation(g2d);

    for (int row = 0; row < activePool.length - 1; row++) {
      for (int col = 0; col < activePool[0].length - 1; col++) {
        if(activePool[row][col] != null) activePool[row][col].render(g2d);
      }
    }

    player.render(g2d);

    enemies.get(0).render(g2d);

    g2d.setTransform(originalTransform);
  }

  public Player getPlayer() {
    return player;
  }

  public int[][] getGridData() {
    return gridData;
  }

  private BufferedImage getTextureImage(int textureKey) {
    if (textureCache.containsKey(textureKey)) {
      return textureCache.get(textureKey);
    }

    for (TextureMap type : TextureMap.values()) {
      if (type.inRange(textureKey)) {
        BufferedImage textureImage = type.loadTexture(textureKey);
        textureCache.put(textureKey, textureImage);
        return textureImage;
      }
    }

    return null;
  }

  private Point visibleGridStart() {
    int startX = Math.max(0, ((int) camera.getX() - camera.getVisibleWidth()) / CELL_WIDTH);
    int startY = Math.max(0, ((int) camera.getY() - camera.getVisibleHeight()) / CELL_HEIGHT);

    return new Point(startX, startY);
  }

  private Point visibleGridEnd() {
    int numRows = gridData.length - 1;
    int numCols = gridData[0].length - 1;

    int endX = Math.min(numCols, ((int) camera.getX() + camera.getVisibleWidth()) / CELL_WIDTH + 1);
    int endY = Math.min(numRows, ((int) camera.getY() + camera.getVisibleHeight()) / CELL_HEIGHT + 1);

    return new Point(endX, endY);
  }

  private void poolUpdate() {
    Point start = visibleGridStart();
    Point end = visibleGridEnd();

      for (int row = 0; row < gridData.length; row++) {
        for (int col = 0; col < gridData[0].length; col++) {
          if(col >= start.x && col < end.x && row >= start.y && row < end.y) {
            int x = col * CELL_HEIGHT;
            int y = row * CELL_WIDTH;
            if(gridData[row][col] > 0) activePool[row][col] = (Platform) new GroundTile(x, y, new Rect(x, y, CELL_WIDTH, CELL_HEIGHT), getTextureImage(gridData[row][col]));
          }
          else 
            activePool[row][col] = null;
      }
    }
    
  }
}
