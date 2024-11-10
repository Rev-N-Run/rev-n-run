package io.github.revNrun.revNrun.controllers.vectorController;

public class Vector2 {
    private float x;
    private float y;

    public Vector2() {
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double distance(Vector2 v) {
        double dx = v.getX() - this.getX();
        double dy = v.getY() - this.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
