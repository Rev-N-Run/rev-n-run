package io.github.revNrun.revNrun.model.vector;

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

    public float distance(Vector2 v) {
        float dx = v.getX() - this.getX();
        float dy = v.getY() - this.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
