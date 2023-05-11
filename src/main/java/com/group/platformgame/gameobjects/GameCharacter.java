package main.java.com.group.platformgame.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameCharacter extends GameObject {
  private int i = 0;
  private BufferedImage animationBlock = null;

  public GameCharacter(int x, int y, File spriteFile) {
    super(x, y, spriteFile);
  }

  public void update(Graphics g) {
    //TODO: iterate through charactersheet
    animationBlock = sprite.getSubimage(0, 0, 128, 64);
    render(g);
  }

  public void render(Graphics g){
    g.drawImage(animationBlock, position.get(0), position.get(1), null);
  } 
}
