package main.java.com.group.platformgame.gameobjects.character;

import main.java.com.group.platformgame.utils.Loader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public enum MushroomState {
    RUN("Run.png", 8),
    ATTACK("Attack.png", 8),
    HURT("Hurt.png", 4),
    DEATH("Death.png", 4);

    private String animationPath = "/resources/assets/images/characters/enemies/mushroom/";

    private final String fileName;
    private BufferedImage spriteSheet;
    private List<BufferedImage> lstImg = new ArrayList<>();
    private int imgNum;

    MushroomState(String fileName, int imgNum) {
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
        int col = spriteSheet.getWidth() / 150;
        int row = spriteSheet.getHeight() / 150;
        Outer: for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (count >= imgNum) break Outer;
                BufferedImage rawSprite = spriteSheet.getSubimage(j * 150, i * 150, 150, 150);
                lstImg.add(removeInvisiblePixels(rawSprite));
                count++;
            }
        }
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

    public BufferedImage getSpriteAtIdx(int idx) {
        return lstImg.get(idx);
    }

    public int getImgNum() {
        return imgNum;
    }

}