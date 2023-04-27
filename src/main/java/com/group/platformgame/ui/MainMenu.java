import javax.swing.*;
import java.awt.Layout;
import java.awt.GridBagConstraints;

public class MainMenu extend JPanel{
  GridBagConstraints layout = new GridBagConstraints();

  JLabel menuTitle = new JLabel("Main Menu");

  JButton exitButton = new JButton ("Exit");

  layout.insets = [5, 5, 5, 5];

  layout.gridx = 0;
  layout.gridy = 0;
  add(menuTitle, layout);
  
  layout.gridx = 0;
  layout.gridy = 1;
  add(exitButton, layout);

  
}
