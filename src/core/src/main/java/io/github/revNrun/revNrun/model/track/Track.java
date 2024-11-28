package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class Track {
    private final List<Vector2> trackPoints;
    private final List<Vector2> controlPoints;
    private final List<Vector2> leftBorder;
    private final List<Vector2> rightBorder;

    public Track() {
        RandomTrackPoints baseTrack = new RandomTrackPoints();
        trackPoints = baseTrack.getPoints();
        controlPoints = baseTrack.getBasePoints();
        leftBorder = BorderGenerator.generateLeftBorder(trackPoints, 30.0f);
        rightBorder = BorderGenerator.generateRightBorder(trackPoints, 30.0f);
    }

    public List<Vector2> getTrackPoints() {
        return trackPoints;
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
}
