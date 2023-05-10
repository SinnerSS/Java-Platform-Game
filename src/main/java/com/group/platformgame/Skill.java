import java.awt.Rectangle;
import java.util.Random;

public class Skill {
    private int x;
    private int y;
    private int damage;
    private int speed;
    private int height;
    private int width;
    private int time; // thời gian duy trì trạng thái

    public Skill(int x, int y, int damage, int speed, int height, int width, int time) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.time = time;
    }
    
    public void Shooting(Player player, Enemy enemy) {
    // mô hình kỹ năng bắn

        if (enemy.getIsAttacking() && enemy.getHealth() > 0) {
            boolean leftDirection; // hướng tại thời điểm tung đòn bắn

            if (enemy.getIsMovingLeft()) leftDirection = true;
            else leftDirection = false;

            While (/* điều kiện để đòn bắn biến mất (đợi tùy chỉnh) */) {

                // quỹ đạo bay của đòn bắn là đường thẳng nối từ enemy tới player
                if (leftDirection) {
                    x -= speed;
                }
                else {
                    x += speed;
                }
                if (enemy.getX() != player.getX()) {
                    y += speed*((player.getY() - enemy.getY()) / Math.abs(player.getX() - enemy.getX()));
                }

                // tạo vùng sát thương
                Rectangle areaAtacking = new Rectangle(x - width/2, y - width/2, width, height);

                Thread.sleep(time); // thời gian duy trì đòn bắn ở vị trí cũ

                if ((player.getX() >= areaAtacking.getMinX() && player.getX() <= areaAtacking.getMaxX()) 
                && (player.getY() <= areaAtacking.getMaxY() && player.getY() >= areaAtacking.getMinY())) {
                    
                    player.takeDamage(damage);
                    areaAtacking.setBounds(0, 0, 0, 0); // xóa vùng sát thương cũ
                    break; // kỹ năng trúng mục tiêu, đòn bắn biến mất
                }
                else areaAtacking.setBounds(0, 0, 0, 0); // xóa vùng sát thương cũ
            }
        }
    }
    
        public void Hitting(Enemy enemy) {
    // mô hình kỹ năng cận chiến

        if (enemy.getIsAttacking() && enemy.getHealth() > 0) {
            if (enemy.getIsMovingLeft()) {
                
                // vùng sát thương khi hướng đánh là bên trái
                Rectangle areaAtacking = new Rectangle(enemy.getX() + (enemy.getWidth()/2), enemy.getY() - height/2, -width, height);
                
                if ((player.getX() <= areaAtacking.getMinX() && player.getX() >= areaAtacking.getMaxX()) 
                && (player.getY() <= areaAtacking.getMaxY() && player.getY() >= areaAtacking.getMinY())) {
                   
                    player.takeDamage(damage);
                    
                    Thread.sleep(time); // thời gian duy trì vùng đánh
                    areaAtacking.setBounds(0,0,0,0); // xóa vùng đánh cũ
                }
            }
            else {

                // vùng sát thương khi hướng đánh là bên phải
                Rectangle areaAtacking = new Rectangle(enemy.getX() - (enemy.getWidth()/2), enemy.getY() - height/2 , width, height);

                if ((player.getX() >= areaAtacking.getMinX() && player.getX() <= areaAtacking.getMaxX()) 
                && (player.getY() <= areaAtacking.getMaxY() && player.getY() >= areaAtacking.getMinY())) {
                    
                    player.takeDamage(damage);

                    Thread.sleep(time); // thời gian duy trì vùng đánh
                    areaAtacking.setBounds(0,0,0,0); // xóa vùng đánh cũ
                }
            }
        }
    }

    public void SpecialSkill(Player player, Enemy enemy) {
        // mô hình kỹ năng đặc biệt, gây sát thương lên 1 vùng không gian ngẫu nhiên giữa enemy và player
        if (enemy.getIsAttacking() && enemy.getHealth() > 0) {
            int deltaX = player.getX() - enemy.getX(); // khoảng cách giữa enemy và player theo phương x
            Random random = new Random();
            Rectangle areaAtacking  = new Rectangle();

            // vùng sát thương
            if (deltaX >= 0) areaAtacking.setBounds(random.nextInt(deltaX) + enemy.getX() - width/2, y - height/2, width, height);
            else areaAtacking.setBounds(-random.nextInt(deltaX) + enemy.getX() - width/2, y - height/2, width, height);

            // gây damage 
            if ((player.getX() >= areaAtacking.getMinX() && player.getX() <= areaAtacking.getMaxX()) 
            && (player.getY() <= areaAtacking.getMaxY() && player.getY() >= areaAtacking.getMinY())) {
                player.takeDamage(damage);
            Thread.sleep(time); // thời gian duy trì vùng đánh
            areaAtacking.setBounds(0, 0, 0, 0); // xóa vùng sát thương
            }
        }
    }
}
