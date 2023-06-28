package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.gameobjects.GameObject;

public class GameCharacter extends GameObject<Rectangle> {
  protected BufferedImage spriteSheet;
  protected int animationTick = 0;

  public GameCharacter(int x, int y, Rectangle hitbox) {
    super(x, y, hitbox);
  }

  @Override
  public void update() {
  }

  @Override
  public void render(Graphics2D g){

  } 
}
