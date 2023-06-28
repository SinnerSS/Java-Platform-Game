package main.java.com.group.platformgame.gameobjects;

import java.awt.Graphics;
import java.awt.Shape;


public abstract class GameObject<T extends Shape> {
  protected int x;
  protected int y;
  protected T hitbox;

  public GameObject(int x, int y) {
    this.x = x;
    this.y = y;
  }

  
  public GameObject(int x, int y, T hitbox) {
    this.x = x;
    this.y = y;
    this.hitbox = hitbox;
  }


  public int getX() {
    return x;
  }


  public void setX(int x) {
    this.x = x;
  }


  public int getY() {
    return y;
  }


  public void setY(int y) {
    this.y = y;
  }


  public T getHitbox() {
    return hitbox;
  }


  public void setHitbox(T hitbox) {
    this.hitbox = hitbox;
  }


  public abstract void update(); 

  public abstract void render(Graphics g);
}
