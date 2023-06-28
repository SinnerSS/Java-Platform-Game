package main.java.com.group.platformgame.gameobjects.character;


import main.java.com.group.platformgame.utils.Loader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


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

    private String animationPath = "/resources/assets/images/characters/player/";

    private final String fileName;
    private BufferedImage spriteSheet;
    private List<BufferedImage> lstImg = new ArrayList<>();
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
                if (count >= imgNum) break Outer;
                BufferedImage rawSprite = spriteSheet.getSubimage(j * 128, i * 64, 128, 64);
                lstImg.add(removeInvisiblePixels(rawSprite));
                count++;
            }
        }
    }

    public BufferedImage getSpriteAtIdx(int idx) {
        return lstImg.get(idx);
    }


    public int getImgNum() {
        return imgNum;
    }

    private BufferedImage removeInvisiblePixels(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int minX = width, minY = height, maxX = 0, maxY = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;

                if (alpha > 0) {
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        int newWidth = maxX - minX + 1;
        int newHeight = maxY - minY + 1;

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                int pixel = image.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;

                if (alpha > 0) {
                    newImage.setRGB(x - minX, y - minY, pixel);
                }
            }
        }

        g2d.dispose();

        return newImage;
    }
}
