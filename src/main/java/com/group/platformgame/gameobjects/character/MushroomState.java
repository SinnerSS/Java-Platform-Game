package main.java.com.group.platformgame.gameobjects.character;

import main.java.com.group.platformgame.utils.Loader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public enum MushroomState {
    RUN("Run.png", 8),
    ATTACK("Attack.png", 8),
    HURT("Hurt.png", 4),
    DEATH("Death.png", 4);

    private String animationPath = "/assets/images/characters/enemies/Mushroom/";

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
                if (count >= imgNum)
                    break Outer;
                lstImg.add(spriteSheet.getSubimage(j * 150, i * 150, 150, 150));
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

}