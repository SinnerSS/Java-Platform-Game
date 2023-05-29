package main.java.com.group.platformgame.levels;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {
    private int x;
    private int y;
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int xRelative(int x) {
        return x - this.x;
    }
    public int yRelative(int y) {
        return y - this.y;
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w') y -= 10;
        if(e.getKeyChar() == 's') y += 10;
        if(e.getKeyChar() == 'd') x += 10;
        if(e.getKeyChar() == 'a') x -= 10;
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
