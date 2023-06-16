package main.java.com.group.platformgame.levels;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    public Point applyTransformation(int x, int y) {
        int translatedX = x - this.x;
        int translatedY = y - this.y;

        return new Point(translatedX, translatedY);
    }
}
