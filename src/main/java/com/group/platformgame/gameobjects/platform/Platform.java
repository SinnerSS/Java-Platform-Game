package main.java.com.group.platformgame.gameobjects.platform;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.gameobjects.GameObject;
import main.java.com.group.platformgame.utils.Rect;

public class Platform extends GameObject {
  protected BufferedImage sprite;

  public Platform(double x, double y, Rect hitbox, BufferedImage sprite) {
    super(x, y, hitbox);
    this.sprite = sprite;
  }

  @Override 
  public void update(double delta) {
  }

  @Override
  public void render(Graphics2D g2D) {
    g2D.drawImage(sprite, (int) pos.x, (int) pos.y, null);
  }
}
