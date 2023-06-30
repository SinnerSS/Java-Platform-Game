package main.java.com.group.platformgame.gameobjects;

import java.awt.Graphics2D;

import main.java.com.group.platformgame.utils.Rect;
import main.java.com.group.platformgame.utils.Vector2D;


public abstract class GameObject {
  public Vector2D pos;
  protected Rect hitbox;

  public GameObject(double x, double y) {
    this(x, y, null);
  }

  
  public GameObject(double x, double y, Rect hitbox) {
    pos = new Vector2D(x, y);
    this.hitbox = hitbox;
  }


  public double getX() {
    return pos.x;
  }


  public void setX(double x) {
    pos.x = x;
    hitbox.pos.x = x;
  }


  public double getY() {
    return pos.y;
  }


  public void setY(double y) {
    pos.y = y;
    hitbox.pos.y = y;
  }


  public Rect getHitbox() {
    return hitbox;
  }


  public void setHitbox(Rect hitbox) {
    this.hitbox = hitbox;
  }


  public abstract void update(double delta); 

  public abstract void render(Graphics2D g2D);
}
