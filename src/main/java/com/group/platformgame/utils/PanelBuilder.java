package main.java.com.group.platformgame.utils;

import java.awt.GridBagConstraints;


public class PanelBuilder {

    public static GridBagConstraints changeCords (GridBagConstraints layout, int x, int y) {
        layout.gridx = x;
        layout.gridy = y;

        return layout;
    }
    
}
