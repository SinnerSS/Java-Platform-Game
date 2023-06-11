package main.java.com.group.platformgame.utils;

import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.java.com.group.platformgame.levels.TextureMap;

public class Loader {
  public static BufferedImage loadBufferedImage(String path) {
    BufferedImage image = null;
    InputStream is = TextureMap.class.getResourceAsStream(path);
    try {
      image = ImageIO.read(is);
      is.close();
    } catch (IOException|NullPointerException e) {
      e.printStackTrace();
    }
    return image;
  }
}
