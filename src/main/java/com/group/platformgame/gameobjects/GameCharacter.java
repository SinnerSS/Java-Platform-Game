package main.java.com.group.platformgame.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameCharacter extends GameObject {
  private BufferedImage animationBlock = null;

  public GameCharacter(int x, int y, File spriteFile) {
    super(x, y, spriteFile);
  }

  public void update() {
  }

  public void render(Graphics g){
    animationBlock = sprite.getSubimage(0, 0, 128, 64);
    g.drawImage(animationBlock, position.get(0), position.get(1), null);
  } 
}
