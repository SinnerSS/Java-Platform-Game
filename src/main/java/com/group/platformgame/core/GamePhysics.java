package main.java.com.group.platformgame.core;

import main.java.com.group.platformgame.levels.Level;

public class GamePhysics implements Runnable {
  private final int simulationRate = 120;
  private Level level;
  
  public GamePhysics(Level level) {
    this.level = level;
  }

  public void run() {
    double updateTime = 1000000000.0/simulationRate;
    long lastUpdateTime = System.nanoTime();
    
    while(true) {
      long currentUpdateTime = System.nanoTime();
      if(currentUpdateTime - lastUpdateTime >= updateTime) {
        double delta = (currentUpdateTime - lastUpdateTime) / 1000000000.0;
        lastUpdateTime = System.nanoTime();
        synchronized (level.lock) {
          level.update(delta);
        }
      }
    }
  }
}

