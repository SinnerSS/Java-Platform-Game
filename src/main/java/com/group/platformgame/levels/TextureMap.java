package main.java.com.group.platformgame.levels;

import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.utils.IntRange;
import main.java.com.group.platformgame.utils.Loader;

public enum TextureMap {
  GROUND (new IntRange(1, 100), "Ground.png"),
  CEILING (new IntRange(100, 200), "Ceiling.png"),
  WALL (new IntRange(200, 300), "Wall.png"),
  PLATFORM(new IntRange(300, 350), "Platform.png"),
  SLOPE (new IntRange(350, 400), "Slope.png"),
  CEILING_SLOPE (new IntRange(400, 450), "Ceiling_Slope.png"),
  DOOR (new IntRange(450, 500), "Door.png"),
  TRAP (new IntRange(500, 550), "Trap.png"),
  BACKGROUND (new IntRange(550, 1000), "Background.png");
  

  public static String resourcePath = "/resources/assets/images/platforms/texture/";

  private final IntRange range;
  private final String fileName;
  private BufferedImage mapImage;
  private boolean mapCheck = false;

  private TextureMap(IntRange range, String fileName) {
    this.range = range;
    this.fileName = fileName;
  }

  public boolean inRange(int value) {
    return range.isInRange(value);
  }

  public BufferedImage loadTexture(int key) {
    if(!mapCheck) loadMap();
    int idx = range.getOffSet(key);

    int mapRow = mapImage.getHeight() / Level.CELL_HEIGHT;
    int mapCol = mapImage.getWidth() / Level.CELL_WIDTH;

    
    if(idx > mapRow * mapCol) throw new IndexOutOfBoundsException();

    int y = idx / mapCol;
    int x = idx - mapCol * y;

    return mapImage.getSubimage(x * Level.CELL_WIDTH, y * Level.CELL_HEIGHT, Level.CELL_WIDTH, Level.CELL_HEIGHT);
  }

  private void loadMap() {
    String path = resourcePath + fileName;
    mapImage = Loader.loadBufferedImage(path);
    mapCheck = true;
  }
} 
