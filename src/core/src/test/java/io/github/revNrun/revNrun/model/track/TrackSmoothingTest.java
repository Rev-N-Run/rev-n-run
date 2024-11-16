package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackSmoothingTest {

    @Test
    void testComputeCatmullRom_TwoControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(6, 4)
        ));

        int interpolatedDistance = 2;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false));
    }

    @Test
    void testComputeCatmullRom_SingleControlPoint() {
        List<Vector2> controlPoints = new ArrayList<>(Collections.singletonList(new Vector2(2, 4)));
        int interpolatedDistance = 10;

        // Should return an argument error
        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, true));
    }

    @Test
    void testComputeCatmullRom_EmptyControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>();
        int interpolatedDistance = 10;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false));
    }

    // This test focuses in validating that there are not interpolated points of the first and last pair of points
    @Test
    void testPointsAreBetweenCorrespondingControlPoints() {
        List<Vector2> controlPoints1 = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 3),
            new Vector2(2, 4),
            new Vector2(4, 2)
        ));

        List<Vector2> controlPoints2 = new ArrayList<>(Arrays.asList(
            new Vector2(1, 3),
            new Vector2(2, 4),
            new Vector2(4, 2),
            new Vector2(3, 6)
        ));

        List<Vector2> controlPoints3 = new ArrayList<>(Arrays.asList(
            new Vector2(-1, -2),
            new Vector2(0, 0),
            new Vector2(1, 3),
            new Vector2(2, 4)
        ));

        int interpolatedDistance = 11;

        List<Vector2> smoothedPoints1 = TrackSmoothing.computeCatmullRom(controlPoints1, interpolatedDistance, false);
        List<Vector2> smoothedPoints2 = TrackSmoothing.computeCatmullRom(controlPoints2, interpolatedDistance, false);
        List<Vector2> smoothedPoints3 = TrackSmoothing.computeCatmullRom(controlPoints3, interpolatedDistance, false);

        for(Vector2 point1 : smoothedPoints1){
            for(Vector2 point2 : smoothedPoints2){
                assertNotEquals(point1, point2);
            }
        }

        for(Vector2 point1 : smoothedPoints1){
            for(Vector2 point3 : smoothedPoints3){
                assertNotEquals(point1, point3);
            }
        }
    }

    @Test
    void testComputeCatmullRomSpline_NegativeInterpolatedDistance() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 4),
            new Vector2(4, 0),
            new Vector2(6, 4)
        ));
        int interpolatedDistance = -10;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, true));
    }

    @Test
    void testComputeCatmullRomSpline_ZeroInterpolatedDistance() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 4),
            new Vector2(4, 0),
            new Vector2(6, 4)
        ));
        int interpolatedDistance = 0;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false));
    }

    @Test
    public void testComputeCatmullRomPoint() {
        Vector2 p0 = new Vector2(0, 0);
        Vector2 p1 = new Vector2(1, 1);
        Vector2 p2 = new Vector2(2, 1);
        Vector2 p3 = new Vector2(3, 0);

        Vector2 result = TrackSmoothing.testComputeCatmullRomPoint(p0, p1, p2, p3, 0.5f);

        // Expected values based on manual calculations
        float expectedX = 1.5f;
        float expectedY = 1.125f;

        // 0.01 error margin is accepted
        assertEquals(expectedX, result.getX(), 0.01);
        assertEquals(expectedY, result.getY(), 0.01);
    }

    @Test
    void testNoInterpolationWhenDistanceIsLessThanInterpolatedDistance() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0),  // Distance = 1
            new Vector2(2, 0),  // Distance = 1
            new Vector2(3, 0)
        ));

        int interpolatedDistance = 2;
        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false);

        // There should be no interpolated points since all distances are less than interpolatedDistance
        assertEquals(0, smoothedPoints.size());
    }

    @Test
    void testMixedDistancesInterpolation() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0),  // Distance = 1
            new Vector2(4, 0),  // Distance = 3
            new Vector2(5, 0)
        ));

        int interpolatedDistance = 2;
        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false);

        // Should only have interpolated points in the segment with distance 3
        int expectedPoints = Math.round(3.0f / interpolatedDistance) - 1;
        assertEquals(expectedPoints, smoothedPoints.size());
    }

    @Test
    void testDistancesBetweenPoints() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 0),
            new Vector2(6, 0),  // Straight segment of 4 units
            new Vector2(8, 0)
        ));

        int interpolatedDistance = 2;
        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, true);

        // Verify that distances between consecutive points don't significantly exceed
        // the interpolatedDistance
        Vector2 prevPoint = controlPoints.get(1); // First control point
        for (Vector2 point : smoothedPoints) {
            float distance = prevPoint.distance(point);
            assertTrue(distance <= interpolatedDistance * 1.5f,
                "Distance between points (" + distance + ") is much larger than interpolatedDistance (" + interpolatedDistance + ")");
            prevPoint = point;
        }
    }

    @Test
    void testCurveRatioEffect() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 0),   // Straight segment
            new Vector2(4, 4),   // Sharp curve
            new Vector2(6, 0)
        ));

        int interpolatedDistance = 2;

        // Get points for both straight and curved segments
        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false);

        // Verify that there are more points in the curved section
        float straightDistance = new Vector2(2, 0).distance(new Vector2(4, 4));
        float minExpectedPoints = Math.round(straightDistance / interpolatedDistance) - 1;
        assertTrue(smoothedPoints.size() >= minExpectedPoints,
            "Should have more points due to curve ratio factor");
    }

    @Test
    void testControlPointsAddition() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 0),
            new Vector2(4, 0),
            new Vector2(6, 0)
        ));

        int interpolatedDistance = 3;

        // Test with and without control points
        List<Vector2> withControlPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, true);
        List<Vector2> withoutControlPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance, false);

        // Difference should be the number of internal control points (n-2 for n points)
        assertEquals(withoutControlPoints.size() + 2, withControlPoints.size());
    }
}
