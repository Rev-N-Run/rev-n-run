package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

public class TrackSmoothing {
    public static List<Vector2> computeCatmullRom(List<Vector2> controlPoints, int numSamples) {
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
                Vector2 point = computePoint(p0, p1, p2, p3, t);
                smoothedPoints.add(point);
            }

            // Add the last point of the actual segment (Catmull-Rom does not add the control points, just the interpolated)
            smoothedPoints.add(controlPoints.get(i + 1));
        }

        return smoothedPoints;
    }

    private static Vector2 computePoint(Vector2 point0, Vector2 point1, Vector2 point2, Vector2 point3, float t) {
        return null;
    }

    // Test getters
    public static Vector2 testComputePoint(Vector2 point0, Vector2 point1, Vector2 point2, Vector2 point3, float t) {
        return computePoint(point0, point1, point2, point3, t);
    }
}
