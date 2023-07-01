package main.java.com.group.platformgame.gameobjects.character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import main.java.com.group.platformgame.utils.Rect;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public class Flying_eye extends Enemy {

    private Flying_eyeState state = Flying_eyeState.RUN;
    private Player player;

    public Flying_eye(int x, int y, Rect hitbox, int limitX, int limitY, int xMax, int xMin, Player player) {
        super(x, y, hitbox, limitX, limitY, xMax, xMin, FLYING_EYE);
        this.player = player;
    }

    public void update() {
        if (currHealth > 0) {
            if (Math.abs(player.getHitbox().pos.x - hitbox.pos.x) <= limitX
                    && Math.abs(player.getHitbox().pos.y - hitbox.pos.y) <= limitY) {
                // # attackMode
                if (player.getHitbox().pos.x <= hitbox.pos.x)
                    isLeft = true;
                else
                    isLeft = false;
                if (isLeft) {
                    if (hitbox.intersects(player.getHitbox())) {
                        initAttackbox();
                        state = Flying_eyeState.ATTACK;

                    } else {
                        state = Flying_eyeState.RUN;
                        pos.x -= velocity;
                    }
                } else {
                    if (hitbox.intersects(player.getHitbox())) {
                        initAttackbox();
                        state = Flying_eyeState.ATTACK;
                    } else {
                        state = Flying_eyeState.RUN;
                        pos.x += velocity;
                    }
                }
            } else {
                // # basicMode
                state = Flying_eyeState.RUN;
                if (isLeft)
                    pos.x -= velocity;
                else
                    pos.x += velocity;

                if (pos.x >= xMax)
                    isLeft = true;
                else if (pos.x <= xMin)
                    isLeft = false;
            }
            // if (hitbox.intersects(player.getAttackbox())) {
            // state = Flying_eyeState.HURT;
            // currHealth -= player.getDamage();
            // }
        } else
            state = Flying_eyeState.DEATH;
        updateHitbox(xDelta, yDelta);
        if (state != Flying_eyeState.ATTACK)
            attackbox.setBounds(0, 0, 0, 0);
    }

    public void render(Graphics g2D) {
        g2D.setColor(Color.RED);
        g2D.drawRect((int) hitbox.pos.x, (int) hitbox.pos.y, (int) hitbox.getWidth(), (int) hitbox.getHeight());
        g2D.drawRect((int) attackbox.pos.x, (int) attackbox.pos.y, (int) attackbox.getWidth(),
                (int) attackbox.getHeight());
        g2D.setColor(Color.WHITE);
        BufferedImage sprite = state.getSpriteAtIdx(animationTick / 8);
        if (isLeft) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-sprite.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            sprite = op.filter(sprite, null);
        }
        g2D.drawImage(sprite, (int) pos.x, (int) pos.y, 150, 150, null);
        animationTick++;
        animationTick %= state.getImgNum() * 8;
    }
}