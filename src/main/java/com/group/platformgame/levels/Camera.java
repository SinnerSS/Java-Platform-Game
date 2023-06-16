package main.java.com.group.platformgame.levels;

import java.awt.Graphics2D;

import main.java.com.group.platformgame.core.GamePanel;
import main.java.com.group.platformgame.gameobjects.character.Player;

public class Camera {
    private int x;
    private int y;
    private float zoom = 3;
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void update(Player player) {
        x = player.getX() - GamePanel.WIDTH / 2;
        y = player.getY() - GamePanel.HEIGHT / 2;
    }

    public Graphics2D applyTransformation(Graphics2D g2D) {
        g2D.scale(zoom, zoom);
        g2D.translate(-x, -y);

        return g2D;
    }
}
