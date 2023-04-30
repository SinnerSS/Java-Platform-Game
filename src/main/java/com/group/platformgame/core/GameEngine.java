package main.java.com.group.platformgame.core;

import javax.swing.JFrame;
import main.java.com.group.platformgame.ui.MainMenu;

public class GameEngine extends JFrame{
  public GameEngine() {
    setTitle("Platform Game");
    setSize(1280, 1024);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    MainMenu mainMenu = new MainMenu();
    
    getContentPane().add(mainMenu);
    setVisible(true);

  }
}
