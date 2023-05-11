package main.java.com.group.platformgame.gameobjects;

import java.lang.Math;
import java.io.File;

public class Enemy extends GameCharacter {
    private int x;
    private int y;
    private int xMin;   // tọa độ x nhỏ nhất mà enemy di chuyển khi không bám đuôi
    private int xMax;   // tọa độ x lớn nhất mà enemy di chuyển khi không bám đuôi
    private int yMin;   // tọa độ y nhỏ nhất mà enemy di chuyển khi không bám đuôi   
    private int yMax;   // tọa độ y lớn nhất mà enemy di chuyển khi không bám đuôi
    private int limit;  // bán kính phạm tầm đánh của enemy 
    private int speed;  // tốc độ di chuyển
    private boolean isFollowing; // trạng thái bám đuôi player
    private boolean isAttacking; // trạng thái tấn công

    public Enemy(int x, int y, File spriteFile, int xMin, int xMax, int yMax, int yMin, int limit, int speed, boolean isAttacking) {
        super(x, y, spriteFile);
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.speed = speed;
        this.limit = limit;
    }

    // method này chỉ dùng khi enemy chưa bám đuôi, khi vào trạng thái bám đuôi thì enemy sẽ bám đuôi tới chết
    public boolean StateMove(Player player) {
        if (Math.sqrt((player.getX() - x)^2 + (player.getY() - y)^2) <= limit) isFollowing = true;
        else isFollowing = false;
        return isFollowing;
    }

    // kiểm tra xem player có trong tầm đánh hay không
    public boolean StateAttack(Player player) {
        if (Math.sqrt((player.getX() - x)^2 + (player.getY() - y)^2) <= limit) isAttacking = true;
        else isAttacking = false;
        return isAttacking;
    }
    
    public void Moving(Player player) {
        if (isFollowing) {
            double distance = Math.sqrt((player.getX() - x)^2 + (player.getY() - y)^2); // khoảng cách giữa enemy và player
            x += speed * (player.getX() - x) / distance;
            y += speed * (player.getY() - y) / distance;
        }

        else {
            // khi không bám đuôi thì enemy sẽ di chuyển trên quỹ đạo đồng hồ cát đặt nằm
            double delta = Math.sqrt((xMax - xMin)^2 + (yMax - yMin)^2);
            if (x == xMin && (y >= yMin || y < yMax)) {
                y += speed;
            }
            else if ((double)(x - xMin)/(xMax - xMin) == (double)(y - yMax)/(yMin - yMax) && (y <= yMax && y > yMin)) {
                x += speed * (xMax - xMin) / delta;
                y += speed * (yMax - yMin) / delta;
            }
            else if (x == xMax && (y >= yMin && y < yMax)) {
                y += speed;
            }
            else if ((double)(x - xMax)/(xMin - xMax) == (double)(y - yMax)/(yMin - yMax) && (y <= yMax && y > yMin)) {
                x += speed * (xMin - xMax) / delta;
                y += speed * (yMin - yMax) / delta;
            }
        }
    }
}