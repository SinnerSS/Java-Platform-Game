package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.utils.Rect;

public class Player extends GameCharacter implements KeyListener {
  private int animationTiming = 0;
  private PlayerState state = PlayerState.IDLE;
  private boolean facingRight = true;

  public Player(double x, double y, Rect hitbox) {
    super(x, y, hitbox);
  }

  @Override
  public void update(double delta) {
    vel.y = 300;
    if(vel.x == 0 && vel.y == 0) state = PlayerState.IDLE;
    setX(pos.x + vel.x * delta);
    setY(pos.y + vel.y * delta);
    hitbox.pos = pos;
    hitbox.vel = vel;
  }

  @Override
  public void render(Graphics2D g2D) {
    g2D.setColor(Color.RED);
    g2D.drawRect((int) hitbox.pos.x, (int) hitbox.pos.y, (int) hitbox.getWidth(), (int) hitbox.getHeight());
    g2D.setColor(Color.WHITE);
    BufferedImage sprite = state.getSpriteAtIdx(animationTick);
    if (!facingRight) {
      AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
      tx.translate(-sprite.getWidth(null), 0);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      sprite = op.filter(sprite, null);
    }
    g2D.drawImage(sprite, (int) pos.x, (int) pos.y, null);
    animationTiming += 1;
    if(animationTiming >= 4) {
      animationTiming = 0;
      animationTick++;
      animationTick %= state.getImgNum();
    }
  }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
        case KeyEvent.VK_A -> {
          vel.x = -200;
          facingRight = false;
          state = PlayerState.RUN;
        }
        case KeyEvent.VK_D -> {
          vel.x = 200;
          facingRight = true;
          state = PlayerState.RUN;
        }
        case KeyEvent.VK_W -> {
          vel.y = -200;
          state = PlayerState.JUMP;
        }
        case KeyEvent.VK_S -> {
          vel.y = 200;
        }
      }
    }

  @Override
  public void keyReleased(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KeyEvent.VK_A -> {
        if(vel.x < 0) vel.x = 0;
      }
      case KeyEvent.VK_D -> {
        if(vel.x > 0) vel.x = 0;
      }
      case KeyEvent.VK_W, KeyEvent.VK_S-> {
        vel.y = 0;
      }
    }
  }
}
