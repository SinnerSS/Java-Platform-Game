package main.java.com.group.platformgame.levels;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Camera implements KeyListener, MouseListener {
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
    public int xRelative(int x) {
        return x - this.x;
    }
    public int yRelative(int y) {
        return y - this.y;
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w') y -= 32;
        if(e.getKeyChar() == 's') y += 32;
        if(e.getKeyChar() == 'd') x += 32;
        if(e.getKeyChar() == 'a') x -= 32;
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
