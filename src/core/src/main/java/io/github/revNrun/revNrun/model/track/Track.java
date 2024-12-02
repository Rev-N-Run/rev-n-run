package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class Track {
    private final List<Vector2> controlPoints;
    private List<Vector2> leftBorder;
    private List<Vector2> rightBorder;
    private float radius;

    public Track() {
        RandomTrackPoints baseTrack = new RandomTrackPoints();
        // The next line is only for test purposes, should be removed and replaced for:
        // List<Vector2> trackPoints = baseTrack.getPoints();
        controlPoints = baseTrack.getPoints();
        radius = calculateRadius();
        generateBorders();
    }

    public Track(List<Vector2> controlPoints) {
        if (controlPoints == null || controlPoints.size() < 2) {
            throw new IllegalArgumentException("controlPoints is null or has less than two points");
        }

        this.controlPoints = controlPoints;
        radius = calculateRadius();
        generateBorders();
    }

    private void generateBorders() {
        leftBorder = BorderGenerator.generateLeftBorder(controlPoints, TrackUtils.WIDTH * 0.5f);
        rightBorder = BorderGenerator.generateRightBorder(controlPoints, TrackUtils.WIDTH * 0.5f);

        if (leftBorder == null || leftBorder.isEmpty()) {
            throw new Error("controlPoints and leftBorder and rightBorder are null or empty");
        }
    }

    public List<Vector2> getControlPoints() {
        return controlPoints;
    }

    public List<Vector2> getLeftBorder() {
        return leftBorder;
    }

    public List<Vector2> getRightBorder() {
        return rightBorder;
    }

    private float calculateRadius() {
        if (controlPoints == null || controlPoints.size() < 2) {
            throw new IllegalArgumentException("At least two points are required.");
        }

        float maxDistance = 0;

        for (int i = 0; i < controlPoints.size(); i++) {
            for (int j = i + 1; j < controlPoints.size(); j++) {
                float distance = controlPoints.get(i).distance(controlPoints.get(j));
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }

        return maxDistance;
    }

    public float getTrackRadius() {
        return radius;
    }

    // TEST METHODS
    public List<Vector2> getTrackPointsTest() {
        return controlPoints;
    }
}
