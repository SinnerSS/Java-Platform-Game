package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.gameobjects.GameObject;

public class GameCharacter extends GameObject {
  protected BufferedImage spriteSheet;
  protected int animationTick = 0;

  public GameCharacter(int x, int y) {
    super(x, y);
  }

  public void update() {
  }

  public void render(Graphics g){

  } 
}
