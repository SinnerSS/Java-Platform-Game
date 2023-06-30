package main.java.com.group.platformgame.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.image.VolatileImage;

import javax.swing.JPanel;

import main.java.com.group.platformgame.levels.Level;

public class GamePanel extends JPanel {
  public static final int WIDTH = 1280;
  public static final int HEIGHT = 1024;
  private Level level;
  public GamePanel(Level level) {
    this.level = level;
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    addKeyListener(level.getPlayer());
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    GraphicsConfiguration gc = getGraphicsConfiguration();
    VolatileImage volatileImage = gc.createCompatibleVolatileImage(WIDTH, HEIGHT);

    do {
      if(volatileImage.contentsLost()) {
        if(volatileImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
          volatileImage = gc.createCompatibleVolatileImage(WIDTH, HEIGHT);
        }
      }

      Graphics2D gVolatile = volatileImage.createGraphics();
      level.render(gVolatile);
      gVolatile.dispose();

    } while(volatileImage.contentsLost());

    g.drawImage(volatileImage, 0, 0, null);
  }
  public Level getLevel() {
    return level;
  }
}
