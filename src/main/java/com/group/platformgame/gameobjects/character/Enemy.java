package main.java.com.group.platformgame.gameobjects.character;

import main.java.com.group.platformgame.utils.Data;
import main.java.com.group.platformgame.utils.Rect;

import static main.java.com.group.platformgame.utils.Data.EnemyData.*;

public abstract class Enemy extends GameCharacter {

    protected int aniIndex, enemyType;
    protected int aniSpeed = 20;
    protected int width, height;
    protected double limitX;
    protected double limitY;
    protected double xMax;
    protected double xMin;
    protected int maxHealth;
    protected Attack attack;
    public int health;
    protected boolean stun = false;
    protected boolean isLeft = false;
    protected Player player;
    protected int damage;

    public Enemy(double x, double y, Rect hitbox, double limitX, double limitY, double xMax, double xMin, int enemyType) {
        super(x, y, hitbox);
        this.limitX = limitX;
        this.limitY = limitY;
        this.xMax = xMax;
        this.xMin = xMin;
        this.enemyType = enemyType;
        this.maxHealth = getMaxHeath(enemyType);
        this.health = maxHealth;
        this.hitbox = hitbox;
        height = getHeight(enemyType);
        vel = getVelocity(enemyType);
        damage = Data.EnemyData.getDamage(enemyType);
    }


    @Override
    public void hurt(int damge) {
        super.hurt(damge);
        health -= damage;
        attack = null;
    }

    public Attack getAttack() {
        return attack;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getDamage() {
        return damage;
    }

}