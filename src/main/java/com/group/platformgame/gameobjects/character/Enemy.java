package main.java.com.group.platformgame.gameobjects.character;

import main.java.com.group.platformgame.utils.Data;
import main.java.com.group.platformgame.utils.Rect;
import main.java.com.group.platformgame.utils.Vector2D;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public abstract class Enemy extends GameCharacter {

    protected int aniIndex, enemyType;
    protected int aniSpeed = 20;
    protected int width, height;
    protected Vector2D velocity;
    protected double limitX;
    protected double limitY;
    protected double xMax;
    protected double xMin;
    protected int maxHealth;
    public int currHealth;
    protected int AttackDistance;
    protected boolean isLeft = false;
    protected Player player;
    protected int damage;
    protected Rect attackbox;

    public Enemy(double x, double y, Rect hitbox, double limitX, double limitY, double xMax, double xMin, int enemyType) {
        super(x, y, hitbox);
        this.limitX = limitX;
        this.limitY = limitY;
        this.xMax = xMax;
        this.xMin = xMin;
        this.enemyType = enemyType;
        this.maxHealth = getMaxHeath(enemyType);
        this.currHealth = maxHealth;
        this.hitbox = hitbox;
        height = getHeight(enemyType);
        velocity = getVelocity(enemyType);
        AttackDistance = getAttackDistance(enemyType);
        damage = Data.EnemyData.getDamage(enemyType);
        initAttackbox();
    }

    protected void initAttackbox() {
        if (!isLeft)
            attackbox = new Rect(pos.x, pos.y, width + AttackDistance, height);
        else
            attackbox = new Rect(pos.x - AttackDistance, pos.y, width + AttackDistance, height);
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