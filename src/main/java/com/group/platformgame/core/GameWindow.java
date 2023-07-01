package main.java.com.group.platformgame.core;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GameWindow extends JFrame{
  public GameWindow() {
    // Set JFrame properties
    setUndecorated(true); // Remove window decorations
    setResizable(false); // Prevent resizing
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Get the default screen device
    GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    
    if (graphicsDevice.isFullScreenSupported()) {
        // Enter fullscreen mode
        graphicsDevice.setFullScreenWindow(this);
    } else {
        System.err.println("Fullscreen mode is not supported on this device.");
        // You can handle this situation as per your requirements
    }

    // Set the background color to black
    getContentPane().setBackground(Color.BLACK);
  }
}
