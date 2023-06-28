package main.java.com.group.platformgame.levels;

import java.awt.Graphics2D;

import main.java.com.group.platformgame.core.GamePanel;
import main.java.com.group.platformgame.gameobjects.character.Player;

public class Camera {
    private int x;
    private int y;
    private float zoom = 3;
    private int visibleHeight;
    private int visibleWidth;
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
        visibleHeight = (int) (GamePanel.HEIGHT / zoom);
        visibleWidth = (int) (GamePanel.WIDTH / zoom);
    }
    public float getZoom() {
        return zoom;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    

    public int getVisibleHeight() {
        return visibleHeight;
    }
    public int getVisibleWidth() {
        return visibleWidth;
    }
    public void update(Player player, Level level) {

        x = player.getX() + (96 / 2) - (GamePanel.WIDTH / 2) / (int)zoom;
        y = player.getY() + (48 / 2) - (GamePanel.HEIGHT / 2) / (int)zoom;


        x = Math.max(0, Math.min(x, (Level.CELL_WIDTH * (level.getGridData()[0].length - 1)) - GamePanel.WIDTH / (int)zoom));
        y = Math.max(0, Math.min(y, (Level.CELL_HEIGHT * (level.getGridData().length - 1)) - GamePanel.HEIGHT / (int)zoom));
    }

    public Graphics2D applyTransformation(Graphics2D g2D) {
        g2D.scale(zoom, zoom);
        g2D.translate(-x, -y);

        return g2D;
    }
}
