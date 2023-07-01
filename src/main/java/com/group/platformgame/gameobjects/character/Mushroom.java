package main.java.com.group.platformgame.gameobjects.character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import main.java.com.group.platformgame.utils.Rect;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public class Mushroom extends Enemy {

    private double stunTimer = 0;
    private MushroomState previousState = MushroomState.RUN;
    private MushroomState state = MushroomState.RUN;
    private Player player;

    public Mushroom(double x, double y, Rect hitbox, double limitX, double limitY, double xMax, double xMin, Player player) {
        super(x, y, hitbox, limitX, limitY, xMax, xMin, MUSHROOM);
        this.player = player;
    }
    @Override
    public void hurt(int damage) {
        super.hurt(damage);
        stunTimer = 0.5;
        state = MushroomState.HURT;
    }
    @Override
    public void update(double delta) {
      System.out.println(player.getX() + " " + player.getY());
        if(health <= 0) { 
            animationTick = 0;
            state = MushroomState.DEATH;
            return;
        }
        if(state != previousState) animationTick = 0;
        if (stunTimer > 0) {
            stunTimer -= delta;
            vel.x = 0; 
            if (stunTimer <= 0) {
                vel.x = 200;
                stunTimer = 0;
            }
        }
        if (Math.abs(player.getHitbox().pos.x - hitbox.pos.x) <= limitX && Math.abs(player.getHitbox().pos.y - hitbox.pos.y) <= limitY) {
            // # attackMode
            if (player.getHitbox().pos.x <= hitbox.pos.x)
                isLeft = true;
            else
                isLeft = false;
            if (isLeft) {
                if (hitbox.intersects(player.getHitbox())) {
                    initAttackbox();
                    state = MushroomState.ATTACK;

                } else {
                    state = MushroomState.RUN;
                    setX(pos.x - vel.x * delta);
                }
            } else {
                if (hitbox.intersects(player.getHitbox())) {
                    initAttackbox();
                    state = MushroomState.ATTACK;
                } else {
                    state = MushroomState.RUN;
                    setX(pos.x + vel.x * delta);
                }
            }
        } else {
            // # basicMode
            state = MushroomState.RUN;
            if (isLeft)
                setX(pos.x - vel.x * delta);
            else
                setX(pos.x + vel.x * delta);

            if (pos.x >= xMax)
                isLeft = true;
            else if (pos.x <= xMin)
                isLeft = false;
        }
            // if (hitbox.intersects(player.getAttackbox())) {
            // state = MushroomState.HURT;
            // currHealth -= player.getDamage();
            // }
        // if (state != MushroomState.ATTACK)
        //     attackbox.setBounds(0, 0, 0, 0);
    }
    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.drawRect((int) hitbox.pos.x, (int) hitbox.pos.y, (int) hitbox.getWidth(), (int) hitbox.getHeight());
        // g2D.drawRect((int) attackbox.pos.x, (int) attackbox.pos.y, (int) attackbox.getWidth(), (int) attackbox.getHeight());
        g2D.setColor(Color.WHITE);
        BufferedImage sprite = state.getSpriteAtIdx(animationTick / 8);
        if (isLeft) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-sprite.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            sprite = op.filter(sprite, null);
        }
        g2D.drawImage(sprite, (int) pos.x, (int) pos.y, 25, 37, null);
        animationTick++;
        animationTick %= state.getImgNum() * 8;
    }
}