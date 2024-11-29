package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.*;

public class Checkpoints {
    private final float width;
    private final List<Vector2> checkPoints;
    private List<Vector2> remainingLapCheckPoints;
    private List<Vector2> progress;
    private final float minPercentOfRequiredCheckPoints;

    public Checkpoints(List<Vector2> controlPoints, float width) {
        if (controlPoints == null || controlPoints.isEmpty() || width <= 0) {
            throw new IllegalArgumentException("Control points must not be null or empty," +
                "or width must not be zero or negative");
        }

        // Crea una copia de la lista original
        this.checkPoints = new ArrayList<>(controlPoints);

        // Elimina el último punto si es idéntico al primero
        if (!checkPoints.isEmpty() && checkPoints.get(0).equals(checkPoints.getLast())) {
            this.checkPoints.remove(checkPoints.size() - 1);
        }

        this.width = width * 0.5f;
        this.remainingLapCheckPoints = checkPoints;
        this.progress = new ArrayList<>();
        this.minPercentOfRequiredCheckPoints = 0.9f;
    }

    public Vector2 getStartPoint() {
        return checkPoints.get(0);
    }

    public boolean isInsideCircuit(Vector2 point) {
        Vector2 closestCheckPoint = null;
        float minDistance = Float.MAX_VALUE;

        for (Vector2 p : checkPoints) {
            float distance = p.distance(point);
            if (distance <= width && distance < minDistance) {
                minDistance = distance;
                closestCheckPoint = p;
            }
        }

        if (closestCheckPoint != null) {
            recordProgress(closestCheckPoint);
            return true;
        }

        return false;
    }

    private void recordProgress(Vector2 checkPoint) {
        if (!progress.contains(checkPoint)) {
            progress.add(checkPoint);
        }
    }

    private boolean isProgressOrdered() {
        int indexA = 0;
        int indexB = 0;

        while (indexA < progress.size() && indexB < checkPoints.size()) {
            if (progress.get(indexA).equals(checkPoints.get(indexB))) {
                indexA++;
            }
            indexB++;
        }

        return indexA == progress.size();
    }

    public LapStatus lapStatus() {
        if (progress.getLast().equals(checkPoints.getLast())) {
            if (progress.size() >= Math.floor(checkPoints.size() * minPercentOfRequiredCheckPoints)
                && isProgressOrdered()) {
                return LapStatus.COMPLETE;
            }
            return LapStatus.INCOMPLETE;
        }
        if (isProgressOrdered()) {
            return LapStatus.GOOD;
        }
        return LapStatus.WRONG;
    }

    public void resetProgress() {
        progress = new ArrayList<>();
    }
}
