package main.java.com.group.platformgame.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.java.com.group.platformgame.levels.Level;

public class GamePanel extends JPanel {
  public static final int WIDTH = 1280;
  public static final int HEIGHT = 1024;
  private Level level;
  public GamePanel(Level level) {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.level = level;
    addKeyListener(level.getCamera());
  }
  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    level.render(g2d);
  }
}
