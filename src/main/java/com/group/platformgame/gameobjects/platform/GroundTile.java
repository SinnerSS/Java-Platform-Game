package main.java.com.group.platformgame.gameobjects.platform;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GroundTile extends Platform<Rectangle>{

  public GroundTile(int x, int y, Rectangle hitbox, BufferedImage sprite) {
    super(x, y, hitbox, sprite);
  }
  
  @Override
  public Rectangle getHitbox() {
    return hitbox;
  }
}
