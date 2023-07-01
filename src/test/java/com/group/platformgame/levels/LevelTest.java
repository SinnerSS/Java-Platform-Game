package test.java.com.group.platformgame.levels;

import main.java.com.group.platformgame.core.GamePhysics;
import main.java.com.group.platformgame.core.GamePanel;
import main.java.com.group.platformgame.core.GameRenderer;
import main.java.com.group.platformgame.core.GameWindow;
import main.java.com.group.platformgame.levels.Level;

public class LevelTest {
  public static void main(String[] args) {
    GameWindow window = new GameWindow();
    Level levelDemo = new Level("leveldemo.json");
    GamePanel panel = new GamePanel(levelDemo);
    GameRenderer renderer = new GameRenderer(panel);
    Thread renderThread = new Thread(renderer);
    GamePhysics physicsHandler = new GamePhysics(levelDemo);                    
    Thread physicThread = new Thread(physicsHandler);
    window.add(panel);
    panel.setFocusable(true);
    panel.requestFocusInWindow();
    window.pack();
    renderThread.start();
    physicThread.start();
  }
}
