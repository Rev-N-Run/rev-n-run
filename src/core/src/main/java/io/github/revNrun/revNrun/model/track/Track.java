package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class Track {
    private final List<Vector2> controlPoints;
    private List<Vector2> leftBorder;
    private List<Vector2> rightBorder;

    public Track() {
        RandomTrackPoints baseTrack = new RandomTrackPoints();
        // The next line is only for test purposes, should be removed and replaced for:
        // List<Vector2> trackPoints = baseTrack.getPoints();
        controlPoints = baseTrack.getPoints();
        generateBorders();
    }

    public Track(List<Vector2> controlPoints) {
        this.controlPoints = controlPoints;
        generateBorders();
    }

    private void generateBorders() {
        leftBorder = BorderGenerator.generateLeftBorder(controlPoints, TrackUtils.WIDTH * 0.5f);
        rightBorder = BorderGenerator.generateRightBorder(controlPoints, TrackUtils.WIDTH * 0.5f);
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

    // TEST METHODS
    public List<Vector2> getTrackPointsTest() {
        return controlPoints;
    }
}
