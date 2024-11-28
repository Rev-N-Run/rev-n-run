package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Removes unwanted base points (too near points, intersections of segments, points too near to a segment...)
 * from a given Vector2 list.
 * This method MUST be applied after assigning the first point as last point too to the list.
 */
class AdjustPoints {

    /**
     * Check the base control points to not have a near point too close. If so, remove it from the list.
     * i iterates from size()-2 cause size()-1 is also the first point. If the first point is too close
     * to the last point, the last point should be the removed one, that's what happens when j == 0,
     * so it's unnecessary to check the first and last point distance twice.
     * @param points List of points to adjust
     * @param minDistance Minimum distance permitted between points.
     * @return List of adjusted points.
     */
    static List<Vector2> adjustNearPoints(List<Vector2> points, int minDistance) {
        List<Vector2> adjustedPoints = new ArrayList<>(points);

        for (int i = adjustedPoints.size() - 2; i >= 1; i--) {
            Vector2 a = adjustedPoints.get(i);
            for (int j = i - 1; j >= 0; j--) {
                Vector2 b = adjustedPoints.get(j);
                if (a.distance(b) < minDistance) {
                    adjustedPoints.remove(i);
                    break;
                }
            }
        }

        return adjustedPoints;
    }

    /**
     * Check the points to not intersect. If so, remove segments that create an intersection.
     * @param points List of points to adjust.
     * @param minDistance Minimum distance permitted from a point to a vector.
     * @return List of adjusted points.
     */
    static List<Vector2> adjustIntersections(List<Vector2> points, int minDistance) {
        List<Vector2> adjustedPoints = new ArrayList<>(points);

        for (int i = 0; i < points.size() - 1; i++) {
            Vector2 a = adjustedPoints.get(i);
            Vector2 b = adjustedPoints.get(i + 1);

            for (int j = i + 2; j < adjustedPoints.size() - 2; j++) {
                Vector2 c = adjustedPoints.get(j);
                Vector2 d = adjustedPoints.get(j + 1);
                if (Vector2.doIntersect(a, b, c, d, minDistance)) {
                    adjustedPoints.remove(j + 1);
                    adjustedPoints.remove(j);
                    i = -1;
                    break;
                }
            }
        }

        return adjustedPoints;
    }
}
