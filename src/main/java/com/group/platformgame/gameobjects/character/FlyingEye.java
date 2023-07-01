package main.java.com.group.platformgame.gameobjects.character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import main.java.com.group.platformgame.utils.Rect;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public class FlyingEye extends Enemy {
    private FlyingEyeState previousState = FlyingEyeState.RUN;
    private FlyingEyeState state = FlyingEyeState.RUN;
    private Player player;
    private double stunTimer = 0;

    public FlyingEye(double x, double y, Rect hitbox, int limitX, int limitY, int xMax, int xMin, Player player) {
        super(x, y, hitbox, limitX, limitY, xMax, xMin, FLYING_EYE);
        this.player = player;
    }
    @Override
    public void hurt(int damage) {
        super.hurt(damage);
        health -= damage;
        stunTimer = 0.5;
        state = FlyingEyeState.HURT;
    }
    @Override
    public void update(double delta) {
        if(health <= 0) {
            animationTick = 0;
            state = FlyingEyeState.DEATH;
            return;
        }
        if(state != previousState) animationTick = 0;
        if (stunTimer > 0) {
            stunTimer -= delta;
            vel.x = 0; 
            if (stunTimer <= 0) {
                vel.x = 200;
                stunTimer = 0;
                stun = false;
            }
        }
        if(attack != null) {
            stun = true;
            attack.setduration(attack.getduration() - delta);
            if(attack.getduration() <= 0) {
                stun = false;
                attack = null;
            }
        }
        if(stun) return;
        if (Math.abs(player.getHitbox().pos.x - pos.x) <= limitX && Math.abs(player.getHitbox().pos.y - pos.y) <= limitY) {
            // # attackMode
            if (player.getHitbox().pos.x <= hitbox.pos.x)
                isLeft = true;
            else
                isLeft = false;
            if (isLeft) {
                setX(pos.x - vel.x * delta);
            } else {
                    setX(pos.x + vel.x * delta);
                }
            if (hitbox.intersects(player.getHitbox())) {
                attack = new Attack(new Rect(hitbox.getWidth(), hitbox.getHeight()), 10, 0.5, 0.05);
                attack.pos = pos;
            }
        } else {
                // # basicMode
                if (isLeft)
                    setX(pos.x - vel.x * delta);
                else
                    setX(pos.x + vel.x * delta);

                if (pos.x >= xMax)
                    isLeft = true;
                else if (pos.x <= xMin)
                    isLeft = false;
        }
        manageState();
    }
    private void manageState() {
        if(health <= 0) state = FlyingEyeState.DEATH;
        else if(attack != null) state = FlyingEyeState.ATTACK;
        else if(stunTimer <= 0) { 
            state = FlyingEyeState.RUN;
            vel.x = 200;
        }
        if (state != previousState) {
        animationTick = 0; 
        previousState = state;
        }
    }
    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.setColor(Color.WHITE);
        BufferedImage sprite = state.getSpriteAtIdx(animationTick / 8);
        if (isLeft) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-sprite.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            sprite = op.filter(sprite, null);
        }
        g2D.drawString(String.valueOf(health) , (int) hitbox.getMiddleX() - 5, (int) hitbox.pos.y - 5);
        g2D.drawImage(sprite, (int) pos.x, (int) pos.y, 28, 22, null);
        animationTick++;
        animationTick %= state.getImgNum() * 8;
    }
}