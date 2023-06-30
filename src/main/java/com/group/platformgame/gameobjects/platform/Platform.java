package main.java.com.group.platformgame.gameobjects.platform;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.gameobjects.GameObject;

public class Platform<T extends Shape> extends GameObject<T> {
  protected BufferedImage sprite;

  public Platform(int x, int y, T hitbox, BufferedImage sprite) {
    super(x, y, hitbox);
    this.sprite = sprite;
  }

  @Override
  public void update(double delta) {
  }

  @Override
  public void render(Graphics2D g2D) {
    g2D.drawImage(sprite, x, y, null);
  }
}
