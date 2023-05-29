package main.java.com.group.platformgame.levels;

import java.awt.Graphics2D;

public class LevelGrid {
    private int cellHeight;
    private int cellWidth;
    private int rows;
    private int columns;
    public LevelGrid(int cellHeight, int cellWidth, int rows, int columns) {
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.rows = rows;
        this.columns = columns;
    }
    public void render(Graphics2D g, Camera camera) {
        for (int i = 0; i <= rows; i++) {
            int y = i * cellHeight;
            g.drawLine(camera.xRelative(0), camera.yRelative(y), camera.xRelative(columns * cellWidth), camera.yRelative(y));
        }

        for (int j = 0; j <= columns; j++) {
            int x = j * cellWidth;
            g.drawLine(camera.xRelative(x), camera.yRelative(0), camera.xRelative(x), camera.yRelative(rows * cellHeight));
        }
    }
}
