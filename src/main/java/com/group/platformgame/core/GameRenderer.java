package main.java.com.group.platformgame.core;


public class GameRenderer implements Runnable {
  private final int FPS = 120;
  private GamePanel gamePanel;
  
  public GameRenderer(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void run() {
    double frameTime = 1000000000.0/FPS;
    long currentFrameTime = System.nanoTime();
    
    while(true) {
      if(System.nanoTime() - currentFrameTime >= frameTime) {
        currentFrameTime = System.nanoTime();
        gamePanel.repaint();
      }
    }
  }
}
