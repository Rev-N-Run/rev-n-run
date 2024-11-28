package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * TrackSmoothing is a package-private class that handles the smoothing methods used to create tracks.
 * The current implementation uses the Catmull-Rom algorithm. Interpolated points aren't generated
 * for every pair of control points. Instead, it uses the first and last segments (pair
 * of consecutive control points) just as a guidance of direction. With this customization,
 * it's easier to smooth the track by segments without getting vertexes, and it's not
 * necessary to smooth the whole track at once to get cool curves.
 */
class TrackSmoothing {

    /**
     * @param controlPoints        A Vector2 List containing at least 4 control points, being the first and last
     *                             just direction guides for the start and end of the path.
     * @param interpolatedDistance Determines the approx (+-1 error) distance between consecutive interpolated points.
     * @param addControlPoints     Boolean to determine if input control pointed should be returned too.
     * @return A Vector2 List with only the new interpolated points.
     */
    static List<Vector2> computeCatmullRom(List<Vector2> controlPoints, int interpolatedDistance, boolean addControlPoints) {

        // Handle exceptions
        if (controlPoints.size() < 4)
            throw new IllegalArgumentException("At lest 4 control points are needed to generate interpolated points");

        if (interpolatedDistance < 1)
            throw new IllegalArgumentException("interpolatedDistance must be equal or greater than 1");

        List<Vector2> smoothedPoints = new ArrayList<>();

        // Number of segments of controlPoints (number of pairs of consecutive points)
        int numSegments = controlPoints.size() - 1;

        Vector2 p0, p1, p2 = null, p3;
        float distance, curveRatio;
        int numSegmentsNeeded, numSamples;

        for (int i = 1; i < numSegments - 1; i++) {
            // We use the first and last points to set the start and end direction of the curve that goes from p1 to p2
            p0 = controlPoints.get(i - 1);
            p1 = controlPoints.get(i);
            p2 = controlPoints.get(i + 1);
            p3 = controlPoints.get(i + 2);

            // Add the control point of the start of the curve of every segment (the end of the curve of the past segment)
            if (addControlPoints) smoothedPoints.add(p1);

            // Calculate the distance between the control points
            distance = p1.distance(p2);

            // If the distance is less or equal than interpolatedDistance, no need of interpolation, we can skip the rest of the iteration
            if (distance <= interpolatedDistance) continue;

            // Calculate the number of necessary segments
            numSegmentsNeeded = (int)Math.ceil(distance / interpolatedDistance);
            numSamples = numSegmentsNeeded - 1;

            // Curve factor to adjust the number of points on heavy curves
            curveRatio = calculateCurveRatio(p0, p1, p2, p3);
            numSamples = Math.round(numSamples * curveRatio);


            for (int j = 1; j <= numSamples; j++) {
                float t = (float) j / (float) (numSamples + 1);
                Vector2 point = computeCatmullRomPoint(p0, p1, p2, p3, t);
                smoothedPoints.add(point);
            }
        }

        // Add the control point of the end of the curve of the final segment
        if (addControlPoints) smoothedPoints.add(p2);

        return smoothedPoints;
    }

    /**
     * This method applies the Catmull-Rom algorithm to generate an interpolated point between p1 and p2 based on
     * the four control points, t, and a fixed smoothing factor that can go from 0 to 1.
     *
     * @param p0 Vector2 that influence the start direction of the curve
     * @param p1 Vector2 where starts the curve
     * @param p2 Vector2 where ends the curve
     * @param p3 Vector2 that influence the end direction of the curve
     * @param t  Represents the position on the curve between p1 and p2
     * @return Interpolated point between p1 and p2
     */
    private static Vector2 computeCatmullRomPoint(Vector2 p0, Vector2 p1, Vector2 p2, Vector2 p3, float t) {
        float smoothingFactor = 0.5f; // Values between 0 and 1 are accepted, 0 returns a more precise point, 1 a smoother point, but both go away from control points. 0.5 is the one we want bcs the final track must include the control points

        float tt = t * t;
        float ttt = tt * t;

        float x = smoothingFactor * ((2 * p1.getX()) + (-p0.getX() + p2.getX()) * t + (2 * p0.getX() - 5 * p1.getX() + 4 * p2.getX() - p3.getX()) * tt + (-p0.getX() + 3 * p1.getX() - 3 * p2.getX() + p3.getX()) * ttt);
        float y = smoothingFactor * ((2 * p1.getY()) + (-p0.getY() + p2.getY()) * t + (2 * p0.getY() - 5 * p1.getY() + 4 * p2.getY() - p3.getY()) * tt + (-p0.getY() + 3 * p1.getY() - 3 * p2.getY() + p3.getY()) * ttt);

        return new Vector2(x, y);
    }

    /**
     * Calculate a curvature factor based on the angles between the control points.
     * A value close to 1 indicates a smooth curve, while higher values indicate
     * sharper curves that will require more interpolation points.
     */
    private static float calculateCurveRatio(Vector2 p0, Vector2 p1, Vector2 p2, Vector2 p3) {
        float angle1 = Math.abs(p0.angleBetween(p1) - p1.angleBetween(p2));
        float angle2 = Math.abs(p1.angleBetween(p2) - p2.angleBetween(p3));

        float averageAngle = (angle1 + angle2) / 2.0f;
        float curveRatio = 1.0f + (averageAngle / (float)Math.PI);

        return Math.max(1.0f, curveRatio);
    }

    // TEST METHODS
    static Vector2 testComputeCatmullRomPoint(Vector2 point0, Vector2 point1, Vector2 point2, Vector2 point3, float t) {
        return computeCatmullRomPoint(point0, point1, point2, point3, t);
    }
}
