package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.gameobjects.GameObject;
import main.java.com.group.platformgame.utils.Rect;

public class GameCharacter extends GameObject {
  protected BufferedImage spriteSheet;
  protected int animationTick = 0;

  public GameCharacter(double x, double y, Rect hitbox) {
    super(x, y, hitbox);
  }

  @Override
  public void setX(double x) {
    super.setX(x);
    hitbox.pos.x = x;
  }

  @Override
  public void setY(double y) {
    super.setX(y);
    hitbox.pos.y = y;
  }

  @Override
  public void update(double delta) {
  }

  @Override
  public void render(Graphics2D g){

  } 
}
