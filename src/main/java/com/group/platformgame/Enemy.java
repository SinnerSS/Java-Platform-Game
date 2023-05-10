import java.awt.Rectangle;

public class Enemy {
    private int health;
    private int x;
    private int y;
    private int xMin;   // tọa độ x nhỏ nhất mà enemy di chuyển khi không tấn công
    private int xMax;   // tọa độ x lớn nhất mà enemy di chuyển khi không tấn công
    private int limitX; // giới hạn để enemy tấn công theo x
    private int limitY; // giới hạn để enemy tấn công theo y
    private int speed;  
    private int height;
    private int width;
    private int lag; // độ lag vị trí của enemy
    private boolean isMovingLeft = true; // xu hướng chuyển động của enemy
    private boolean isAttacking = false; // trạng thái tấn công

    public Enemy(int x, int y, int xMax, int xMin, int limitX, int limitY, int health, int speed, 
                 int height, int width, int lag, boolean isMovingLeft, boolean isAttacking) {
        this.x = x;
        this.y = y;
        this.xMax = xMax;
        this.xMin = xMin;
        this.limitX = limitX;
        this.limitY = limitY;
        this.health = health;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.lag = lag;
        this.isMovingLeft = isMovingLeft;
        this.isAttacking = isAttacking;
    }

    public void updateState(Player player){
    // cập nhật trạng thái của enemy    
        
        // khi enemy hết máu thì enemy biến mất
        while (health > 0) {
            Rectangle shape = new Rectangle();
            if ((Math.abs(player.getX() - x) <= limitX) && (Math.abs(player.getY() - y) <= limitY)) {
            // enemy duy chuyển và tấn công

                if(player.getX() <= x) isMovingLeft = true;
                else isMovingLeft = false;
                if (isMovingLeft) x -= speed;
                else x += speed;
                isAttacking = true;
                shape.setBounds(x - width/2, y - height/2, width, height); // hình dáng của enemy 
            }
            else if ((Math.abs(player.getX() - x) > limitX) || (Math.abs(player.getY() - y) > limitY)) {
            // enemy duy chuyển nhưng không tấn công

                if (isMovingLeft) x -= speed;
                else x += speed;
                // enemy đổi chiều di chuyển khi tới giới hạn duy chuyển
                    if (x >= xMax) isMovingLeft = true;
                    else if (x <= xMin) isMovingLeft = false;
                    isAttacking = false;
                    shape.setBounds(x - width/2, y - height/2, width, height); // hình dáng của enemy 
            }
            Thread.sleep(lag); // cho lag vị trí
            shape.setBounds(0, 0, 0, 0); // xóa hình dáng enemy ở vị trí cũ
        }
    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealth(){
        return health;
    }
    public boolean getIsMovingLeft() {
        return isMovingLeft;
    }
    public boolean getIsAttacking() {
        return isAttacking;
    }
}