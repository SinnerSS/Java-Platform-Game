package main.java.com.group.platformgame.levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Level extends JPanel{
    private LevelGrid levelGrid;
    private Camera camera;
    public Level(String levelFile) {
        LevelParser parser = new LevelParser(levelFile);
        levelGrid = parser.getLevelGrid();
        camera = parser.getCamera();
        addKeyListener(camera);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        levelGrid.render(g2D, camera);
    }
}