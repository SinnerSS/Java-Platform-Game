package main.java.com.group.platformgame.gameobjects.character;

import java.awt.Graphics2D;

import main.java.com.group.platformgame.utils.PlayerHandle;

public class Player extends GameCharacter {

  private final PlayerHandle ph = new PlayerHandle();
  private int x;
  private int y;
  private int velocity = 200;
  private PlayerState state = PlayerState.IDLE;

  public Player(int x, int y) {
    super(x, y);
  }

  public void update() {
    if(ph.leftPressed) x -= velocity;
    if(ph.rightPressed) x += velocity;
    if(ph.leftPressed || ph.rightPressed) state = PlayerState.RUN;
  }

  public void render(Graphics2D g2D) {
    g2D.drawImage(state.getSpriteAtIdx(animationTick), x, y, null);
    animationTick++;
    animationTick %= state.getImgNum();
  }

  public PlayerHandle getPh() {
    return ph;
  }
}
