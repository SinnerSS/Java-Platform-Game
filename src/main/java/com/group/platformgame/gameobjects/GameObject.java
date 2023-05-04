package main.java.com.group.platformgame.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public abstract class GameObject {
  protected List<Integer> position = new ArrayList<>();
  protected BufferedImage sprite = null;

  public GameObject(int x, int y, File spriteFile) {
    position.add(x);
    position.add(y);
    try {
      sprite = ImageIO.read(spriteFile);
    } catch(IOException e) {
      System.out.println("Cant read sprite file!");
    }
  }

  public void setX(int x) {
    position.set(0, x);
  }

  public void setY(int y) {
    position.set(1, y);
  }

  public abstract void update(); 

  public abstract void render(Graphics g);
}
