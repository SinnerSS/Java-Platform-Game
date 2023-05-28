package main.java.com.group.platformgame.core;

import javax.swing.JFrame;
import main.java.com.group.platformgame.ui.MainMenu;

public class GameEngine extends JFrame{
  private GameWindow gameWindow;
  public GameEngine() {
    gameWindow = new GameWindow();

    MainMenu mainMenu = new MainMenu();


    gameWindow.getContentPane().add(mainMenu);
  }
}
