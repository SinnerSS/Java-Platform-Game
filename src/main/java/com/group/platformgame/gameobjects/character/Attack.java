package main.java.com.group.platformgame.gameobjects.character;

import java.util.ArrayList;
import java.util.List;

import main.java.com.group.platformgame.utils.Rect;

public class Attack extends Rect{
  public static int id = 0;
  private int damage;
  private List<GameCharacter> enemiesHit = new ArrayList<>();

  public Attack(Rect hitbox, int damage) {
    super(hitbox);
    this.damage = damage;
    id += 1;
  }

  public int getDamage() {
    return damage;
  }

  public boolean hit(GameCharacter character) {
    if (enemiesHit.contains(character)) {
      return false; 
    } else {
      enemiesHit.add(character);
      character.hurt(damage);
      return true; 
    }
  }
}
