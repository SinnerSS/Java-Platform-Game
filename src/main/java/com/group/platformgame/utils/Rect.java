package main.java.com.group.platformgame.utils;

public class Rect {
    public Vector2D pos;
    public Vector2D size;
    public Vector2D vel;

    public Rect[] contact = new Rect[4];

    public Rect() {
        super();
    }

    public Rect(Rect r) {
        pos = r.pos;
        size = r.size;
        vel = r.vel;
    }

    public Rect(double width, double length) {
        size = new Vector2D(width, length);
    }

    public Rect(double x, double y, double width, double length) {
        pos = new Vector2D(x, y);
        size = new Vector2D(width, length);
    }
    
    public boolean contain(Vector2D p) {
        return (p.x >= this.pos.x && p.y >= this.pos.y && p.x < this.pos.x + this.size.x && p.y < this.pos.y + this.size.y);
    }

    public boolean intersects(Rect r) {
        return (r.pos.x < this.pos.x + this.size.x && r.pos.x + r.size.x > this.pos.x && r.pos.y < this.pos.y + this.size.y && r.pos.y + r.size.y > this.pos.y);
    }

    public double getWidth() {
        return size.x;
    }

    public double getHeight() {
        return size.y;
    }

    public void setLocation(double x, double y) {
        pos.x = x;
        pos.y = y;
    }

    public void setBounds(double x, double y, double width, double height) {
        pos.x = x;
        pos.y = y;
        size.x = width; 
        size.y = height;
    }

    public void setBounds(Rect r) {
        pos.x = r.pos.x;
        pos.y = r.pos.y;
        size.x = r.size.x; 
        size.y = r.size.y;
    }

    public double getMiddleX() {
        return pos.x + size.x / 2;
    }

    public double getMiddleY() {
        return pos.y + size.y / 2;
    }
}

