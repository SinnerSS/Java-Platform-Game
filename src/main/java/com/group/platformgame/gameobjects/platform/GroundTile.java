package main.java.com.group.platformgame.gameobjects.platform;

import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.utils.Rect;

public class GroundTile extends Platform {

  public GroundTile(double x, double y, Rect hitbox, BufferedImage sprite) {
    super(x, y, hitbox, sprite);
  }
}
