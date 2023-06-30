package main.java.com.group.platformgame.utils;

public class Rect {
    public Vector2D pos;
    public Vector2D size;
    public Vector2D vel;

    public Rect[] contact = new Rect[4];

    public boolean contain(Vector2D p) {
        return (p.x >= this.pos.x && p.y >= this.pos.y && p.x < this.pos.x + this.size.x && p.y < this.pos.y + this.size.y);
    }

    public boolean intersects(Rect r) {
        return (r.pos.x < this.pos.x + this.size.x && r.pos.x + r.size.x > this.pos.x && r.pos.y < this.pos.y + this.size.y && r.pos.y + r.size.y > this.pos.y);
    }
}

