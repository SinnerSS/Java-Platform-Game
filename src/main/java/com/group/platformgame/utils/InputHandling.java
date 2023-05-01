package main.java.com.group.platformgame.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class InputHandling implements KeyListener{
  public InputHandling(JPanel panel){
    panel.addKeyListener(this);    
    panel.setFocusable(true);
  }

  public void keyTyped(KeyEvent e) {
  }
  
  public void keyPressed(KeyEvent e){
  }

  public void keyReleased(KeyEvent e){
  }
}
