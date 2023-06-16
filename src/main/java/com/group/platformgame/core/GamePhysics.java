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
    long currentUpdateTime = System.nanoTime();
    
    while(true) {
      if(System.nanoTime() - currentUpdateTime >= updateTime) {
        currentUpdateTime = System.nanoTime();
        synchronized (level.lock) {
          level.update();
        }
      }
    }
  }
}

