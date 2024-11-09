package io.github.revNrun.revNrun.controllers.trackController;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;

import java.util.List;

public class TrackPoints {
    private static final int MIN_NUM_POINTS = 50;
    private static final int MAX_NUM_POINTS = 200;
    private static final int MIN_RADIUS = 100;
    private static final int MAX_RADIUS = 300;

    private final int numPoints;
    private final int radius;
    private List<Vector2> points;

    public TrackPoints() {}
    public TrackPoints(int numPoints, int radius){}

    public List<Vector2> getTrackPoints(){
        return points;
    }
}
