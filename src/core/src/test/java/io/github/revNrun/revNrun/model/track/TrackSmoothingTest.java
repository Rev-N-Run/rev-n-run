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
    void testComputeCatmullRom_MultipleControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 4),
            new Vector2(4, 0),
            new Vector2(6, 4),
            new Vector2(8, 2),
            new Vector2(6, 3),
            new Vector2(7, 0),
            new Vector2(5, 2)
        ));

        int interpolatedDistance = 1;
        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance);

        // ComputeCatmullRom should have created numSamples points for each given pair of points, except the first and last pair
        int numSamples = (Math.round(new Vector2(2, 4).distance(new Vector2(4, 0))) / interpolatedDistance - 1) +
            (Math.round(new Vector2(4, 0).distance(new Vector2(6, 4))) / interpolatedDistance - 1) +
            (Math.round(new Vector2(6, 4).distance(new Vector2(8, 2))) / interpolatedDistance - 1) +
            (Math.round(new Vector2(8, 2).distance(new Vector2(6, 3))) / interpolatedDistance - 1) +
            (Math.round(new Vector2(6, 3).distance(new Vector2(7, 0))) / interpolatedDistance - 1) ;
        assertEquals(numSamples, smoothedPoints.size());

        // Interpolated points should not be the same as control points
        for(Vector2 controlPoint : controlPoints){
            for (Vector2 smoothedPoint : smoothedPoints){
                assertNotEquals(controlPoint, smoothedPoint);
            }
        }
    }

    @Test
    void testComputeCatmullRom_FourControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(6, 4),
            new Vector2(1, 14),
            new Vector2(5, 4)
        ));

        int interpolatedDistance = 2;

        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance);

        // ComputeCatmullRom should have created numSamples points for each given pair of points, except the first and last pair, so just numSample points
        int numSamples = Math.round(new Vector2(6, 4).distance(new Vector2(1, 14))) / interpolatedDistance - 1;
        assertEquals(numSamples, smoothedPoints.size());

        // Interpolated points should not be the same as control points
        for(Vector2 controlPoint : controlPoints){
            for (Vector2 smoothedPoint : smoothedPoints){
                assertNotEquals(controlPoint, smoothedPoint);
            }
        }
    }

    @Test
    void testComputeCatmullRom_TwoControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(6, 4)
        ));

        int interpolatedDistance = 2;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance));
    }

    @Test
    void testComputeCatmullRom_SingleControlPoint() {
        List<Vector2> controlPoints = new ArrayList<>(Collections.singletonList(new Vector2(2, 4)));
        int interpolatedDistance = 10;

        // Should return an argument error
        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance));
    }

    @Test
    void testComputeCatmullRom_EmptyControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>();
        int interpolatedDistance = 10;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance));
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

        List<Vector2> smoothedPoints1 = TrackSmoothing.computeCatmullRom(controlPoints1, interpolatedDistance);
        List<Vector2> smoothedPoints2 = TrackSmoothing.computeCatmullRom(controlPoints2, interpolatedDistance);
        List<Vector2> smoothedPoints3 = TrackSmoothing.computeCatmullRom(controlPoints3, interpolatedDistance);

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

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance));
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

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, interpolatedDistance));
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
}
