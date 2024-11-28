package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;

public class GhostState {
    private final Vector2 position;
    private final float angle;
    private final float timestamp;

    public GhostState(Vector2 position, float angle, float timestamp) {
        this.position = position;
        this.angle = angle;
        this.timestamp = timestamp;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getAngle() {
        return angle;
    }

    public float getTimestamp() {
        return timestamp;
    }
}
