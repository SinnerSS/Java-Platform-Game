package main.java.com.group.platformgame.core;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.java.com.group.platformgame.levels.Level;

public class GamePanel extends JPanel {
  private Level level;
  public GamePanel(Level level) {
    this.level = level;
    addKeyListener(level.getCamera());
  }
  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    level.render(g2d);
  }
}
