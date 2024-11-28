package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {
    @Test
    void testTrackInitialization() {
        // Act
        Track track = new Track();

        // Assert
        assertNotNull(track.getTrackPoints(), "Track points should not be null.");
        assertNotNull(track.getControlPoints(), "Control points should not be null.");
        assertNotNull(track.getLeftBorder(), "Left border should not be null.");
        assertNotNull(track.getRightBorder(), "Right border should not be null.");
        assertFalse(track.getTrackPoints().isEmpty(), "Track points should not be empty.");
        assertFalse(track.getLeftBorder().isEmpty(), "Left border should not be empty.");
        assertFalse(track.getRightBorder().isEmpty(), "Right border should not be empty.");
    }

    @Test
    void testTrackBordersHaveSameSizeAsTrackPoints() {
        // Act
        Track track = new Track();

        // Assert
        assertEquals(track.getTrackPoints().size(), track.getLeftBorder().size(),
            "Left border should have the same number of points as track points.");
        assertEquals(track.getTrackPoints().size(), track.getRightBorder().size(),
            "Right border should have the same number of points as track points.");
    }

    @Test
    void testControlPointsAreSubsetOfTrackPoints() {
        // Act
        Track track = new Track();
        List<Vector2> trackPoints = track.getTrackPoints();
        List<Vector2> controlPoints = track.getControlPoints();

        // Assert
        assertTrue(trackPoints.containsAll(controlPoints),
            "All control points should be part of the track points.");
    }
}
