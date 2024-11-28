package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.*;

public class Checkpoints {
    private final float width;
    private final List<Vector2> controlPoints;
    private final List<Vector2> progress;
    private boolean hasPassedEveryCheckPointInOrder;
    private int pointer;

    public Checkpoints(List<Vector2> controlPoints, float width) {
        if (controlPoints == null || controlPoints.isEmpty() || width <= 0) {
            throw new IllegalArgumentException("Control points must not be null or empty," +
                "or width must not be zero or negative");
        }

        this.width = width;
        this.controlPoints = controlPoints;
        this.progress = new ArrayList<>();
        this.hasPassedEveryCheckPointInOrder = true;
        this.pointer = 0;
    }

    public Vector2 getStartPoint() {
        return controlPoints.getFirst();
    }

    public boolean isInsideCircuit(Vector2 point) {
        for (Vector2 p : controlPoints) {
            if (p.distance(point) <= width) {
                recordProgress(p);
                return true;
            }
        }
        return false;
    }

    private void recordProgress(Vector2 checkPoint) {
        Set<Vector2> progressSet = new HashSet<>(progress);
        if (!progressSet.contains(checkPoint)
            && pointer < controlPoints.size()
            && checkPoint.equals(controlPoints.get(pointer))) {
            progress.add(checkPoint);
            pointer++;
        } else {
            hasPassedEveryCheckPointInOrder = false;
        }
    }

    public boolean hasPassedCheckPoints() {
        return hasPassedEveryCheckPointInOrder;
    }
}
