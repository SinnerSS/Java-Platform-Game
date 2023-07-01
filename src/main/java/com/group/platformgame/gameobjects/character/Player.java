package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.utils.Rect;

public class Player extends GameCharacter implements KeyListener {
  private int animationTiming = 0;
  private PlayerState previousState = PlayerState.IDLE;
  private PlayerState state = PlayerState.IDLE;
  private boolean facingRight = true;
  private Attack attack;
  public boolean isJumping = false;
  public int health = 100;
  private boolean stun = false;
  private double stunTimer = 0;

  public Player(double x, double y, Rect hitbox) {
    super(x, y, hitbox);
  }
  @Override
  public void hurt(int damage) {
    super.hurt(damage);
    health -= damage;
    stunTimer = 0.1;
  }

  @Override
  public void update(double delta) {
    if(state == PlayerState.DEATH) return;
    if (stunTimer > 0) {
      stunTimer -= delta;
      stun = true;

      if (stunTimer <= 0) {
        stun = false;
        stunTimer = 0;
      }
    }
    if (attack != null) {
      stun = true;
      attack.setduration(attack.getduration() - delta);
      if (attack.getduration() <= 0) {
        stun = false;
        attack = null;
      }
    }
    if(vel.y < 250) vel.y += 15;
    else if(vel.y > 250) vel.y = 250;
    if(!stun) {
      setX(pos.x + vel.x * delta);
      setY(pos.y + vel.y * delta);
    }
    hitbox.vel = vel;
    manageState();
  }

  private void manageState() {
    if(health <= 0) state = PlayerState.DEATH;
    else if(isJumping) state =  PlayerState.JUMP;
    else if(attack != null) state = PlayerState.ATTACKS;
    else if(vel.x != 0) state = PlayerState.RUN;
    else if(stunTimer > 0) state = PlayerState.HURT;
    else state = PlayerState.IDLE;
    if (state != previousState) {
      animationTick = 0; 
      previousState = state;
    }
  }

  public Attack getAttack() {
    return attack;
  }

  @Override
  public void render(Graphics2D g2D) {
    // g2D.setColor(Color.RED);
    // g2D.drawRect((int) hitbox.pos.x, (int) hitbox.pos.y, (int) hitbox.getWidth(), (int) hitbox.getHeight());
    // g2D.setColor(Color.WHITE);
    if(animationTiming >= 4) {
      animationTiming = 0;
      animationTick++;
      animationTick %= state.getImgNum();
    }
    BufferedImage sprite = state.getSpriteAtIdx(animationTick);
    if (!facingRight) {
      AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
      tx.translate(-sprite.getWidth(null), 0);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      sprite = op.filter(sprite, null);
    }
    g2D.drawString(String.valueOf(health) , (int) hitbox.getMiddleX() - 10, (int) hitbox.pos.y - 5);
    g2D.drawImage(sprite, (int) pos.x, (int) pos.y, null);
    animationTiming += 1;
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
        }
        case KeyEvent.VK_D -> {
          vel.x = 200;
          facingRight = true;
        }
        case KeyEvent.VK_W -> {
          if(!isJumping) {
            isJumping = true;
            vel.y = -500;
          }
        }
        // case KeyEvent.VK_S -> {
        //   vel.y = 200;
        // }
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
      // case KeyEvent.VK_S-> {
      //   vel.y = 0;
      // }
      case KeyEvent.VK_SPACE -> {
        if(isJumping) return;
        if(attack == null) {
          attack = new Attack(new Rect(hitbox.getWidth(), hitbox.getHeight()), 75, 0.5);
          attack.pos = pos;
          return;
        }
      }
    }
  }
}