package main.java.com.group.platformgame.levels;

import java.awt.Graphics2D;

import main.java.com.group.platformgame.core.GamePanel;
import main.java.com.group.platformgame.gameobjects.character.Player;
import main.java.com.group.platformgame.utils.Vector2D;

public class Camera {
    private Vector2D pos;
    private float zoom = 3;
    private int visibleHeight;
    private int visibleWidth;
    public Camera(double x, double y) {
        pos = new Vector2D(x, y);
        visibleHeight = (int) (GamePanel.HEIGHT / zoom);
        visibleWidth = (int) (GamePanel.WIDTH / zoom);
    }
    public float getZoom() {
        return zoom;
    }
    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }
    

    public int getVisibleHeight() {
        return visibleHeight;
    }
    public int getVisibleWidth() {
        return visibleWidth;
    }
    public void update(Player player, Level level) {

        pos.x = player.getX() + (96 / 2) - (GamePanel.WIDTH / 2) / zoom;
        pos.y = player.getY() + (48 / 2) - (GamePanel.HEIGHT / 2) / zoom;


        pos.x = Math.max(0, Math.min(pos.x, (Level.CELL_WIDTH * (level.getGridData()[0].length - 1)) - GamePanel.WIDTH / zoom));
        pos.y = Math.max(0, Math.min(pos.y, (Level.CELL_HEIGHT * (level.getGridData().length - 1)) - GamePanel.HEIGHT / zoom));
    }

    public Graphics2D applyTransformation(Graphics2D g2D) {
        g2D.scale(zoom, zoom);
        g2D.translate(-pos.x , -pos.y);
        return g2D;
    }
}
