package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class Checkpoints {
    private final float width;
    private List<Vector2> controlPoints;
    private List<Vector2> progress;

    public Checkpoints(List<Vector2> controlPoints, float width) {
        this.width = width;
        this.controlPoints = controlPoints;
    }

    public Vector2 getStartPoint() {
        return controlPoints.getFirst();
    }

    public boolean isInsideCircuit(Vector2 point) {
        return false;
    }

    private void recordProgress() {

    }

    public boolean hasPassedCheckPoints() {
        return false;
    }
}
