package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.java.com.group.platformgame.utils.PlayerHandle;

public class Player extends GameCharacter {

  private final PlayerHandle ph = new PlayerHandle();
  private int velocity = 3;
  private PlayerState state = PlayerState.IDLE;
  private boolean facingRight = true;

  public Player(int x, int y, Rectangle hitbox) {
    super(x, y, hitbox);
  }

  @Override
  public void update() {
    if(ph.leftPressed) {
      x -= velocity;
      facingRight = false;
    }
    if(ph.rightPressed) {
      x += velocity;
      facingRight = true;
    }
    if(ph.jumpPressed) {
      state = PlayerState.JUMP;
    }
    if(ph.attackPressed) {
      state = PlayerState.ATTACKS;
    }
    if(ph.downPressed) {
      y += velocity;
    }
    if(ph.leftPressed || ph.rightPressed) state = PlayerState.RUN;
    if(ph.noAction) state = PlayerState.IDLE;
<<<<<<< HEAD
    hitbox.setLocation(x, y);
=======
>>>>>>> e6296cd (add more action)
  }

  @Override
  public void render(Graphics2D g2D) {
    g2D.setColor(Color.RED);
    g2D.drawRect(x, y, (int) hitbox.getWidth(), (int) hitbox.getHeight());
    g2D.setColor(Color.WHITE);
    BufferedImage sprite = state.getSpriteAtIdx(animationTick);
    if (!facingRight) {
      AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
      tx.translate(-sprite.getWidth(null), 0);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      sprite = op.filter(sprite, null);
    }
    g2D.drawImage(sprite, x, y, null);
    animationTick++;
    animationTick %= state.getImgNum();
  }

  public PlayerHandle getPh() {
    return ph;
  }
}
