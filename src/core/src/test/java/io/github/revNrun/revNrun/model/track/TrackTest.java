package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {
    @Test
    void testTrackInitialization() {
        // Act
        io.github.revNrun.revNrun.model.track.Track track = new io.github.revNrun.revNrun.model.track.Track();

        // Assert
        assertNotNull(track.getTrackPointsTest(), "Track points should not be null.");
        assertNotNull(track.getControlPoints(), "Control points should not be null.");
        assertNotNull(track.getLeftBorder(), "Left border should not be null.");
        assertNotNull(track.getRightBorder(), "Right border should not be null.");
        assertFalse(track.getTrackPointsTest().isEmpty(), "Track points should not be empty.");
        assertFalse(track.getLeftBorder().isEmpty(), "Left border should not be empty.");
        assertFalse(track.getRightBorder().isEmpty(), "Right border should not be empty.");
    }

    @Test
    void testTrackBordersHaveSameSizeAsTrackPoints() {
        // Act
        io.github.revNrun.revNrun.model.track.Track track = new io.github.revNrun.revNrun.model.track.Track();

        // Assert
        assertEquals(track.getTrackPointsTest().size(), track.getLeftBorder().size(),
            "Left border should have the same number of points as track points.");
        assertEquals(track.getTrackPointsTest().size(), track.getRightBorder().size(),
            "Right border should have the same number of points as track points.");
    }

    @Test
    void testControlPointsAreSubsetOfTrackPoints() {
        // Act
        io.github.revNrun.revNrun.model.track.Track track = new io.github.revNrun.revNrun.model.track.Track();
        List<Vector2> trackPoints = track.getTrackPointsTest();
        List<Vector2> controlPoints = track.getControlPoints();

        // Assert
        assertTrue(trackPoints.containsAll(controlPoints),
            "All control points should be part of the track points.");
    }

    // LOOP TESTING

    // Outer loop testing

    @Test
    void testOuterLoopZeroIterations() {
        // Control points less than 2, outer loop won't execute
        List<Vector2> controlPoints = new ArrayList<>(); // Empty list
        controlPoints.add(new Vector2(0, 0)); // Add only one point

        assertThrows(IllegalArgumentException.class, () -> new Track(controlPoints));
    }

    @Test
    void testOuterLoopSingleIteration() {
        // Control points where outer loop runs once
        List<Vector2> controlPoints = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(3, 4)
        );

        Track instance = new Track(controlPoints);
        float maxRadius = instance.getTrackRadius();

        assertEquals(5.0, maxRadius, "Outer loop single iteration failed.");
    }

    @Test
    void testOuterLoopMultipleIterations() {
        // Control points where outer loop runs multiple times
        List<Vector2> controlPoints = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(3, 4),
            new Vector2(6, 8)
        );

        Track instance = new Track(controlPoints);
        float maxRadius = instance.getTrackRadius();

        assertEquals(10.0, maxRadius, "Outer loop multiple iterations failed.");
    }

    // Nested loop testing

    @Test
    void testInnerLoopZeroIterations() {
        // Test case where inner loop won't execute (empty inner loop case)
        List<Vector2> controlPoints = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(0, 0)
        );

        Track instance = new Track(controlPoints);
        float maxRadius = instance.getTrackRadius();

        assertEquals(0.0, maxRadius, "Inner loop zero iterations failed.");
    }

    @Test
    void testInnerLoopSingleIteration() {
        // Inner loop runs once
        List<Vector2> controlPoints = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(3, 4),
            new Vector2(6, 8)
        );

        Track instance = new Track(controlPoints);
        float maxRadius = instance.getTrackRadius();

        assertEquals(10.0, maxRadius, "Inner loop single iteration failed.");
    }

    @Test
    void testInnerLoopMultipleIterations() {
        // Inner loop runs multiple times
        List<Vector2> controlPoints = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(3, 4),
            new Vector2(6, 8),
            new Vector2(9, 12)
        );

        Track instance = new Track(controlPoints);
        float maxRadius = instance.getTrackRadius();

        assertEquals(15.0, maxRadius, "Inner loop multiple iterations failed.");
    }

    @Test
    void testBothLoopsBoundaryCondition() {
        // Outer and inner loops interact at boundary conditions
        List<Vector2> controlPoints = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(3, 4),
            new Vector2(6, 8),
            new Vector2(0, 5)
        );

        Track instance = new Track(controlPoints);
        float maxRadius = instance.getTrackRadius();

        assertEquals(10.0, maxRadius, "Boundary condition for both loops failed.");
    }

    @Test
    void testOuterLoopWithEmptyControlPoints() {
        // Control points completely empty
        List<Vector2> controlPoints = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> new Track(controlPoints));
    }
}
