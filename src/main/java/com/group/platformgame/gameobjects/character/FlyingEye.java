package main.java.com.group.platformgame.gameobjects.character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import main.java.com.group.platformgame.utils.Rect;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public class FlyingEye extends Enemy {

    private FlyingEyeState state = FlyingEyeState.RUN;
    private Player player;

    public FlyingEye(int x, int y, Rect hitbox, int limitX, int limitY, int xMax, int xMin, Player player) {
        super(x, y, hitbox, limitX, limitY, xMax, xMin, FLYING_EYE);
        this.player = player;
    }
    @Override
    public void update(double delta) {
        if (currHealth > 0) {
            if (Math.abs(player.getHitbox().pos.x - hitbox.pos.x) <= limitX && Math.abs(player.getHitbox().pos.y - hitbox.pos.y) <= limitY) {
                // # attackMode
                if (player.getHitbox().pos.x <= hitbox.pos.x)
                    isLeft = true;
                else
                    isLeft = false;
                if (isLeft) {
                    if (hitbox.intersects(player.getHitbox())) {
                        initAttackbox();
                        state = FlyingEyeState.ATTACK;

                    } else {
                        state = FlyingEyeState.RUN;
                        setX(pos.x - velocity.x * delta);
                    }
                } else {
                    if (hitbox.intersects(player.getHitbox())) {
                        initAttackbox();
                        state = FlyingEyeState.ATTACK;
                    } else {
                        state = FlyingEyeState.RUN;
                        setX(pos.x + velocity.x * delta);
                    }
                }
            } else {
                // # basicMode
                state = FlyingEyeState.RUN;
                if (isLeft)
                    setX(pos.x - velocity.x * delta);
                else
                    setX(pos.x + velocity.x * delta);

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
            state = FlyingEyeState.DEATH;
        if (state != FlyingEyeState.ATTACK)
            attackbox.setBounds(0, 0, 0, 0);

        
    }
    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.drawRect((int) hitbox.pos.x, (int) hitbox.pos.y, (int) hitbox.getWidth(), (int) hitbox.getHeight());
        g2D.drawRect((int) attackbox.pos.x, (int) attackbox.pos.y, (int) attackbox.getWidth(), (int) attackbox.getHeight());
        g2D.setColor(Color.WHITE);
        BufferedImage sprite = state.getSpriteAtIdx(animationTick / 8);
        if (isLeft) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-sprite.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            sprite = op.filter(sprite, null);
        }
        System.out.println("Width: " + sprite.getWidth());
        System.out.println("Height: " + sprite.getHeight());
        g2D.drawImage(sprite, (int) pos.x, (int) pos.y, 28, 22, null);
        animationTick++;
        animationTick %= state.getImgNum() * 8;
    }
}