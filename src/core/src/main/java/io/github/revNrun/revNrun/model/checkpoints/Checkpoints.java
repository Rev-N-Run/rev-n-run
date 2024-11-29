package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.*;

public class Checkpoints {
    private final float width;
    private final List<Vector2> checkPoints;
    private int skippedLapCheckPoints;
    private List<Vector2> progress;
    private final float minPercentOfRequiredCheckPoints;

    public Checkpoints(List<Vector2> controlPoints, float width) {
        if (controlPoints == null || controlPoints.isEmpty() || width <= 0) {
            throw new IllegalArgumentException("Control points must not be null or empty," +
                "or width must not be zero or negative");
        }

        this.checkPoints = new ArrayList<>(controlPoints);

        // Delete the last control point
        if (!checkPoints.isEmpty() && checkPoints.get(0).equals(checkPoints.getLast())) {
            this.checkPoints.remove(checkPoints.size() - 1);
        }

        this.width = width * 0.5f;
        this.skippedLapCheckPoints = 0;
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

        skippedLapCheckPoints += countSkippedCheckPoints();
    }

    private int countSkippedCheckPoints() {
        if (progress.size() < 2) {
            return 0; // No se pueden saltar números con menos de 2 elementos en B.
        }

        Vector2 lastAdded = progress.get(progress.size() - 1); // Último número añadido a B.
        Vector2 previousAdded = progress.get(progress.size() - 2); // Número añadido previamente a B.

        // Encuentra los índices de estos números en A.
        int indexLast = checkPoints.indexOf(lastAdded);
        int indexPrevious = checkPoints.indexOf(previousAdded);

        if (indexPrevious == -1 || indexLast == -1) {
            throw new IllegalArgumentException("Los elementos de B deben estar en A");
        }

        // Calcula cuántos números se han saltado en A.
        if (indexLast > indexPrevious) {
            return indexLast - indexPrevious - 1;
        } else {
            // Si el circuito es cíclico, considera los elementos al final de A.
            return (checkPoints.size() - indexPrevious - 1) + indexLast;
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
        if (remainingLife() > 0 && isProgressOrdered()) {
            if (progress.getLast().equals(checkPoints.getLast())) {
                return LapStatus.COMPLETE;
            }
            return LapStatus.GOOD;
        }
        if (progress.getLast().equals(checkPoints.getLast())) {
            return LapStatus.INCOMPLETE;
        }
        return LapStatus.WRONG;
    }

    public int remainingLife() {
        int maxSkippedCheckPoints = checkPoints.size() -
            (int) Math.floor((minPercentOfRequiredCheckPoints * checkPoints.size()));
        return (maxSkippedCheckPoints - skippedLapCheckPoints + 1) /
            maxSkippedCheckPoints * 100;
    }

    public void resetProgress() {
        progress = new ArrayList<>();
    }
}
