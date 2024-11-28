package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class Checkpoints {
    private final float width;
    private final List<Vector2> controlPoints;
    private List<Vector2> progress;

    public Checkpoints(List<Vector2> controlPoints, float width) {
        if (controlPoints == null || controlPoints.isEmpty() || width <= 0) {
            throw new IllegalArgumentException("Control points must not be null or empty," +
                "or width must not be zero or negative");
        }

        this.width = width;
        this.controlPoints = controlPoints;
    }

    public Vector2 getStartPoint() {
        return controlPoints.getFirst();
    }

    public boolean isInsideCircuit(Vector2 point) {
        for (Vector2 p : controlPoints) {
            if (p.distance(point) <= width) {
                return true;
            }
        }
        return false;
    }

    private void recordProgress() {

    }

    public boolean hasPassedCheckPoints() {
        return false;
    }
}
