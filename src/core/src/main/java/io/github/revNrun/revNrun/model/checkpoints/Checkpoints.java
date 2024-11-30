package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.track.TrackUtils;
import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.*;

/**
 * The class manages checkpoints for the game circuit.
 * It provides functionality to check if a point is inside the circuit,
 * track progress through checkpoints, determine lap status, and handle skipped checkpoints.
 */
public class Checkpoints {
    private float width;
    private final List<Vector2> checkPoints;
    private int skippedLapCheckPoints;
    private List<Vector2> progress;
    private final float minPercentOfRequiredCheckPoints;

    /**
     * Constructs a Checkpoints object with the given control points and width.
     *
     * @param controlPoints the list of control points defining the checkpoints.
     * @throws IllegalArgumentException if {@code controlPoints} is null, empty,
     *                                  or if {@code width} is zero or negative.
     */
    public Checkpoints(List<Vector2> controlPoints) {
        if (controlPoints == null || controlPoints.isEmpty()) {
            throw new IllegalArgumentException("Control points must not be null or empty.");
        }

        this.checkPoints = new ArrayList<>(controlPoints);

        // Delete the last control point
        if (!checkPoints.isEmpty() && checkPoints.get(0).equals(checkPoints.getLast())) {
            this.checkPoints.remove(checkPoints.size() - 1);
        }

        this.width = TrackUtils.WIDTH * 0.5f;
        this.skippedLapCheckPoints = 0;
        this.progress = new ArrayList<>();
        this.minPercentOfRequiredCheckPoints = 0.9f;
    }

    /**
     * Returns the starting point of the checkpoints.
     *
     * @return the starting Vector2 of the checkpoints.
     */
    public Vector2 getStartPoint() {
        return checkPoints.get(0);
    }

    /**
     * Determines if a given point is inside the circuit based on the checkpoint boundaries.
     *
     * @param point the point to check.
     * @return true if the point is inside the circuit, false otherwise.
     */
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

    /**
     * Records the progress through a checkpoint.
     *
     * @param checkPoint the checkpoint to record progress for.
     */
    private void recordProgress(Vector2 checkPoint) {
        if (!progress.contains(checkPoint)) {
            progress.add(checkPoint);
        }

        skippedLapCheckPoints += countSkippedCheckPoints();
    }

    /**
     * Counts the number of checkpoints skipped since the last recorded progress.
     *
     * @return the number of skipped checkpoints.
     */
    private int countSkippedCheckPoints() {
        if (progress.size() < 2) {
            return 0;
        }

        Vector2 lastAdded = progress.get(progress.size() - 1);
        Vector2 previousAdded = progress.get(progress.size() - 2);

        int indexLast = checkPoints.indexOf(lastAdded);
        int indexPrevious = checkPoints.indexOf(previousAdded);

        if (indexPrevious == -1 || indexLast == -1) {
            throw new IllegalArgumentException("Progress points have to be in CheckPoints");
        }

        if (indexLast > indexPrevious) {
            return indexLast - indexPrevious - 1;
        } else {
            return (checkPoints.size() - indexPrevious - 1) + indexLast;
        }
    }

    /**
     * Checks if the progress through checkpoints is in the correct order.
     *
     * @return true if the progress is ordered, false otherwise.
     */
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

    /**
     * Determines the lap status based on progress and skipped checkpoints.
     *
     * @return the LapStatus indicating the current lap's status.
     */
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

    /**
     * Calculates the remaining life percentage based on the number of skipped checkpoints.
     *
     * @return the remaining life percentage (0-100).
     */
    public int remainingLife() {
        int maxSkippedCheckPoints = checkPoints.size() -
            (int) Math.floor((minPercentOfRequiredCheckPoints * checkPoints.size()));
        return (maxSkippedCheckPoints - skippedLapCheckPoints + 1) /
            maxSkippedCheckPoints * 100;
    }

    /**
     * Resets the progress through checkpoints.
     */
    public void resetProgress() {
        progress = new ArrayList<>();
    }

    // TEST METHODS

    public Checkpoints(List<Vector2> controlPoints, float width){
        this(controlPoints);
        this.width = width * 0.5f;
    }
}
