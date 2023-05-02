package main.java.com.group.platformgame.core;

import java.awt.Container;
import main.java.com.group.platformgame.ui.MainMenu;

public class GameLoop<T extends Container> implements Runnable {
  private final int FPS = 120;
  private T container;
  public GameLoop(T container) {
    this.container = container;
  }

  public void run() {
    double frameTime = 1000000000.0/FPS;
    long currentFrameTime = System.nanoTime();
    
    while(true) {
      if(System.nanoTime() - currentFrameTime >= frameTime) {
        currentFrameTime = System.nanoTime();
        ((MainMenu) container).update();
      }
    }
  }
}
