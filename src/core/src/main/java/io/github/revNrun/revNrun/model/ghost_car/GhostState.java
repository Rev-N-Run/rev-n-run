package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.model.vector.Vector2;

public class GhostState {
    private final Vector2 position;
    private final float angle;
    private final float timestamp;

    public GhostState(Vector2 position, float angle, float timestamp) {
        this.position = new Vector2(position);
        this.angle = angle;
        this.timestamp = timestamp;
    }

    public GhostState(GhostState that) {
        this.position = that.position;
        this.angle = that.angle;
        this.timestamp = that.timestamp;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }

    public float getAngle() {
        return angle;
    }

    public float getTimestamp() {
        return timestamp;
    }
}
