package main.java.com.group.platformgame.gameobjects.character;

import main.java.com.group.platformgame.utils.Data;
import main.java.com.group.platformgame.utils.Rect;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public abstract class Enemy extends GameCharacter {

    protected int aniIndex, enemyType;
    protected int aniSpeed = 20;
    protected int width, height;
    protected int velocity;
    protected double limitX;
    protected double limitY;
    protected double xMax;
    protected double xMin;
    protected int maxHealth;
    public int currHealth;
    protected int xDelta;
    protected int yDelta;
    protected int AttackDistance;
    protected boolean isLeft = false;
    protected Player player;
    protected int damage;
    protected Rect attackbox;

    public Enemy(double x, double y, Rect hitbox, double limitX, double limitY, double xMax, double xMin,
            int enemyType) {
        super(x, y, hitbox);
        this.limitX = limitX;
        this.limitY = limitY;
        this.xMax = xMax;
        this.xMin = xMin;
        this.enemyType = enemyType;
        this.maxHealth = getMaxHeath(enemyType);
        this.currHealth = maxHealth;
        width = getWidth(enemyType);
        height = getHeight(enemyType);
        velocity = getVelocity(enemyType);
        xDelta = getHitboxDeltaX(enemyType);
        yDelta = getHitboxDeltaX(enemyType);
        AttackDistance = getAttackDistance(enemyType);
        damage = Data.EnemyData.getDamage(enemyType);
        initHitbox();
        initAttackbox();
    }

    protected void initHitbox() {
        hitbox = new Rect(pos.x + xDelta, pos.y + yDelta, width, height);
    }

    protected void initAttackbox() {
        if (!isLeft)
            attackbox = new Rect(pos.x + xDelta, pos.y + yDelta, width + AttackDistance, height);
        else
            attackbox = new Rect(pos.x + xDelta - AttackDistance, pos.y + yDelta, width + AttackDistance, height);
    }

    protected void updateHitbox(int xDelta, int yDelta) {
        hitbox.pos.x = pos.x + xDelta;
        hitbox.pos.y = pos.y + yDelta;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public Rect getAttackbox() {
        return attackbox;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getDamage() {
        return damage;
    }

}
