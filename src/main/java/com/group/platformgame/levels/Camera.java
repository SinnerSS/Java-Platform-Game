package main.java.com.group.platformgame.levels;

import java.awt.Graphics2D;

public class Camera {
    private int x;
    private int y;
    private float zoom = 2;
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

    public Graphics2D applyTransformation(Graphics2D g2D) {
        g2D.scale(zoom, zoom);
        g2D.translate(-x, -y);

        return g2D;
    }
}
