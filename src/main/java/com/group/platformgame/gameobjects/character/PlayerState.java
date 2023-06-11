package main.java.com.group.platformgame.gameobjects.character;


import main.java.com.group.platformgame.utils.Loader;

import java.awt.image.BufferedImage;


public enum PlayerState {
    ATTACK_FROM_AIR("attack_from_air.png", 7),
    ATTACKS("Attacks.png", 7),
    CLIMB("Climb.png", 6),
    CROUCH_ATTACKS("crouch_attacks.png", 7),
    CROUCH_IDLE("crouch_idle.png", 8),
    DEATH("Death.png", 4),
    HANGING("Hanging.png", 8),
    HEALTH("Health.png", 8),
    HURT("Hurt.png", 3),
    IDLE("Idle.png", 8),
    JUMP("Jump.png", 8),
    PRAY("Pray.png", 12),
    ROLL("Roll.png", 4),
    RUN("Run.png", 8),
    SLIDE("Slide.png", 10);

    private static String animationPath = "/resources/assets/images/characters/player";

    private final String fileName;
    private BufferedImage spriteSheet;
    private BufferedImage[] lstImg;
    private int imgNum;

    PlayerState(String fileName, int imgNum) {
        this.fileName = fileName;
        this.imgNum = imgNum;
        loadSpriteSheet();
        splitImg();
    }


    private void loadSpriteSheet() {
        String path = animationPath + fileName;
        spriteSheet = Loader.loadBufferedImage(path);
    }


    public void splitImg() {
        int count = 0;
        int col = spriteSheet.getWidth() / 128;
        int row = spriteSheet.getHeight() / 64;
        Outer:
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (count >= this.imgNum) break Outer;
                this.lstImg[count] = spriteSheet.getSubimage(j * 128, i * 64, 128, 64);
                count++;
            }
        }
    }

    public BufferedImage[] getLstImg() {
        return lstImg;
    }
}
