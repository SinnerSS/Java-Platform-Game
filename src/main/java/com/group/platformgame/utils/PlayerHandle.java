package main.java.com.group.platformgame.utils;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerHandle implements KeyListener {
    public boolean jumpPressed, downPressed, leftPressed, rightPressed, attackPressed, noAction;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            jumpPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            attackPressed = true;
        }
        noAction = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            jumpPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            attackPressed = false;
        }
        if (!(jumpPressed && downPressed && leftPressed && rightPressed && attackPressed)) {
            noAction = true;
        }
    }
}
