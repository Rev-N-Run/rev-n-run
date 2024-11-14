package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

//TODO javadocs comments explicant perque es fan servir els 4 punts
public class TrackSmoothing {
    public static List<Vector2> computeCatmullRom(List<Vector2> controlPoints, int numSamples) {

        if(controlPoints.isEmpty()) throw new IllegalArgumentException("controlPoints is empty");
        if(controlPoints.size() == 1) return controlPoints;

        List<Vector2> smoothedPoints = new ArrayList<>();

        // Add the first point (Catmull-Rom does not add the control points, just the interpolated)
        smoothedPoints.add(controlPoints.get(0));

        // Number of segments of controlPoints (number of pairs of points)
        int numSegments = controlPoints.size() - 1;

        for (int i = 0; i < numSegments; i++) {
            Vector2 p0 = i > 0 ? controlPoints.get(i - 1) : controlPoints.get(0);
            Vector2 p1 = controlPoints.get(i);
            Vector2 p2 = controlPoints.get(i + 1);
            Vector2 p3 = i < numSegments - 1 ? controlPoints.get(i + 2) : p2;

            for (int j = 0; j < numSamples; j++) {
                float t = (float) j / (float) numSamples;
                Vector2 point = computeCatmullRomPoint(p0, p1, p2, p3, t);
                smoothedPoints.add(point);
            }

            // Add the last point of the actual segment (Catmull-Rom does not add the control points, just the interpolated)
            smoothedPoints.add(controlPoints.get(i + 1));
        }

        return smoothedPoints;
    }

    private static Vector2 computeCatmullRomPoint(Vector2 p0, Vector2 p1, Vector2 p2, Vector2 p3, float t) {
        float smoothingFactor = 0.5f; // Values between 0 and 1 are accepted, 0 returns a more precise point, 1 a smoother point.

        float tt = t * t;
        float ttt = tt * t;

        float x = smoothingFactor * ((2 * p1.getX()) + (-p0.getX() + p2.getX()) * t + (2 * p0.getX() - 5 * p1.getX() + 4 * p2.getX() - p3.getX()) * tt + (-p0.getX() + 3 * p1.getX() - 3 * p2.getX() + p3.getX()) * ttt);
        float y = smoothingFactor * ((2 * p1.getY()) + (-p0.getY() + p2.getY()) * t + (2 * p0.getY() - 5 * p1.getY() + 4 * p2.getY() - p3.getY()) * tt + (-p0.getY() + 3 * p1.getY() - 3 * p2.getY() + p3.getY()) * ttt);

        return new Vector2(x, y);
    }

    // TEST GETTERS
    public static Vector2 testComputeCatmullRomPoint(Vector2 point0, Vector2 point1, Vector2 point2, Vector2 point3, float t) {
        return computeCatmullRomPoint(point0, point1, point2, point3, t);
    }
}
