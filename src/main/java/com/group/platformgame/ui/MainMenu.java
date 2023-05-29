package main.java.com.group.platformgame.ui;
import main.java.com.group.platformgame.utils.PanelBuilder;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{

  public MainMenu(){
    this.setLayout(new GridBagLayout());

    GridBagConstraints layout = new GridBagConstraints();
    layout.anchor = GridBagConstraints.LINE_START;
    layout.insets = new Insets(5, 5, 5, 5);

    JLabel menuTitle = new JLabel("Main Menu");

    JButton startButton = new JButton("Start");

    JButton exitButton = new JButton ("Exit");

    exitButton.addActionListener((event) -> {
      System.exit(0);
    });


    PanelBuilder.changeCords(layout, 0, 0);
    add(menuTitle, layout);
    
    PanelBuilder.changeCords(layout, 0, 1);
    add(startButton, layout);

    PanelBuilder.changeCords(layout, 0, 2);
    add(exitButton, layout);

  }
  
}