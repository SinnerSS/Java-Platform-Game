package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.gameobjects.GameObject;
import main.java.com.group.platformgame.utils.Rect;
import main.java.com.group.platformgame.utils.Vector2D;

public class GameCharacter extends GameObject {
  protected Vector2D vel = new Vector2D(0, 250);
  protected BufferedImage spriteSheet;
  protected int animationTick = 0;

  public GameCharacter(double x, double y, Rect hitbox) {
    super(x, y, hitbox);
  }

  public Vector2D getVel() {
    return vel;
  }

  public void setVel(Vector2D vel) {
    this.vel = vel;
    hitbox.vel = vel;
  }

  @Override
  public void update(double delta) {
  }

  @Override
  public void render(Graphics2D g){

  } 
}
