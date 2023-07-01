package main.java.com.group.platformgame.utils;

public class Data {
    public static class EnemyData {
        // # enemyType
        public static final int FLYING_EYE = 0;
        public static final int GOBLIN = 1;
        public static final int MUSHROOM = 2;
        public static final int UNDEAD = 3;
        public static final int SUMMON_OBJECT = 4;

        // # enemyState
        public static final int RUN = 0;
        public static final int ATTACK = 1;
        public static final int HURT = 2;
        public static final int DEATH = 3;
        public static final int IDLE = 4;
        public static final int SKILL = 5;
        public static final int SUMMON = 6;
        public static final int SUMMON_APPEAR = 7;

        // # enemyHeight
        public static int getHeight(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 30;
                case GOBLIN:
                    return 35;
                case MUSHROOM:
                    return 38;
                case UNDEAD:
                    return 63;
                case SUMMON_OBJECT:
                    return 20;
                default:
                    return 0;
            }
        }

        // # enemyWidth
        public static int getWidth(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 40;
                case GOBLIN:
                    return 35;
                case MUSHROOM:
                    return 23;
                case UNDEAD:
                    return 30;
                case SUMMON_OBJECT:
                    return 10;
                default:
                    return 0;
            }
        }

        // # (X_enemyLocation - X_hitboxLocation)
        public static int getHitboxDeltaX(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 55;
                case GOBLIN:
                    return 60;
                case MUSHROOM:
                    return 63;
                case UNDEAD:
                    return 34;
                case SUMMON_OBJECT:
                    return 19;
                default:
                    return 0;
            }
        }

        // # (Y_enemyLocation - Y_hitboxLocation)
        public static int getHitboxDeltaY(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 60;
                case GOBLIN:
                    return 65;
                case MUSHROOM:
                    return 63;
                case UNDEAD:
                    return 28;
                case SUMMON_OBJECT:
                    return 18;
                default:
                    return 0;
            }
        }

        // # enemyVelocity
        public static int getVelocity(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 2;
                case GOBLIN:
                    return 3;
                case MUSHROOM:
                    return 2;
                case UNDEAD:
                    return 0;
                case SUMMON_OBJECT:
                    return 0;
                default:
                    return 0;
            }
        }

        // # enemyDamage
        public static int getDamage(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 15;
                case GOBLIN:
                    return 20;
                case MUSHROOM:
                    return 15;
                case UNDEAD:
                    return 40;
                case SUMMON_OBJECT:
                    return 10;
                default:
                    return 0;
            }
        }

        // # enemyAttackDistance
        public static int getAttackDistance(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 10;
                case GOBLIN:
                    return 35;
                case MUSHROOM:
                    return 15;
                case UNDEAD:
                    return 35;
                case SUMMON_OBJECT:
                    return 0;
                default:
                    return 0;
            }
        }

        // # enemyHealth
        public static int getMaxHeath(int enemyType) {
            switch (enemyType) {
                case FLYING_EYE:
                    return 100;
                case GOBLIN:
                    return 200;
                case MUSHROOM:
                    return 150;
                case UNDEAD:
                    return 400;
                case SUMMON_OBJECT:
                    return 1;
                default:
                    return 0;
            }
        }

        // # nameFile
        public static String getFileName(int enemyState) {
            switch (enemyState) {
                case RUN:
                    return "Run.png";
                case ATTACK:
                    return "Attack.png";
                case DEATH:
                    return "Death.png";
                case IDLE:
                    return "Idle.png";
                case HURT:
                    return "Hurt.png";
                case SKILL:
                    return "Skill.png";
                case SUMMON:
                    return "Summon.png";
                case SUMMON_APPEAR:
                    return "SummonAppear.png";
                default:
                    return null;
            }
        }

        // # path
        public static String getPath(int enemyType) {
            String prePath = "/resources/assets/images/enemies";
            switch (enemyType) {
                case FLYING_EYE:
                    return prePath + "/Flying eye/";
                case GOBLIN:
                    return prePath + "/Goblin/";
                case MUSHROOM:
                    return prePath + "/Mushroom/";
                case UNDEAD:
                    return prePath + "/Undead/";
                case SUMMON_OBJECT:
                    return prePath + "/Summon_object/";
                default:
                    return null;
            }
        }
    }
}
