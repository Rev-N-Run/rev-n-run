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
    void testComputeCatmullRom_SimpleCase() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 4),
            new Vector2(4, 0),
            new Vector2(6, 4)
        ));

        int numSamples = 10;

        List<Vector2> smoothedPoints = TrackSmoothing.computeCatmullRom(controlPoints, numSamples);

        // ComputeCatmullRom should have created numSamples points for each given pair of points
        assertEquals(numSamples * (controlPoints.size() - 1), smoothedPoints.size());

        // Interpolated points should not be the same as control points
        for (int i = 0; i < smoothedPoints.size(); i++) {
            assertNotEquals(controlPoints.get(i / numSamples), smoothedPoints.get(i));
        }
    }

    @Test
    void testComputeCatmullRom_EmptyControlPoints() {
        List<Vector2> controlPoints = new ArrayList<>();
        int numSamples = 10;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, numSamples));
    }

    @Test
    void testComputeCatmullRom_SingleControlPoint() {
        List<Vector2> controlPoints = new ArrayList<>(Collections.singletonList(new Vector2(2, 4)));
        int numSamples = 10;

        // Should return an argument error
        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, numSamples));
    }

    @Test
    void testComputeCatmullRomSpline_NegativeNumSamples() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 4),
            new Vector2(4, 0),
            new Vector2(6, 4)
        ));
        int numSamples = -10;

        assertThrows(IllegalArgumentException.class, () -> TrackSmoothing.computeCatmullRom(controlPoints, numSamples));
    }

    @Test
    void testComputeCatmullRomSpline_ZeroNumSamples() {
        List<Vector2> controlPoints = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(2, 4),
            new Vector2(4, 0),
            new Vector2(6, 4)
        ));
        int numSamples = 0;

        // Should return an empty list, as there are no possible interpolations
        assertTrue(TrackSmoothing.computeCatmullRom(controlPoints, numSamples).isEmpty());
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
