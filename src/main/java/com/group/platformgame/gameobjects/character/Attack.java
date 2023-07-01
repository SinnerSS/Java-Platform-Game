package main.java.com.group.platformgame.gameobjects.character;

import java.util.ArrayList;
import java.util.List;

import main.java.com.group.platformgame.utils.Rect;

public class Attack extends Rect{
  public static int id = 0;
  private double duration;
  private double delay;
  private int damage;
  private List<GameCharacter> enemiesHit = new ArrayList<>();

  public Attack(Rect hitbox, int damage, double duration) {
    this(hitbox, damage, duration, duration);
  }

  public Attack(Rect hitbox, int damage, double duration, double delay) {
    super(hitbox);
    this.damage = damage;
    this.duration = duration;
    this.delay = delay;
    id += 1;
  }
  

  public double getduration() {
    return duration;
  }



  public void setduration(double duration) {
    this.duration = duration;
  }



  public int getDamage() {
    return damage;
  }

  public boolean hit(GameCharacter character) {
    if(duration < delay) {
      if (enemiesHit.contains(character)) {
        return false; 
      } else {
        enemiesHit.add(character);
        character.hurt(damage);
        return true; 
      }
    }
    return false;
  }
}
