package io.github.revNrun.revNrun.controllers.trackController;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomTrackPointsTest {

    @Test
    public void testValuesInRange() {
        RandomTrackPoints a = new RandomTrackPoints();
        RandomTrackPoints b = new RandomTrackPoints();
        RandomTrackPoints c = new RandomTrackPoints();
        RandomTrackPoints d = new RandomTrackPoints();
        RandomTrackPoints e = new RandomTrackPoints();

        int minPoints = RandomTrackPoints.getMinNumPoints();
        int maxPoints = RandomTrackPoints.getMaxNumPoints();
        float minRadius = RandomTrackPoints.getMinRadius();
        float maxRadius = RandomTrackPoints.getMaxRadius();

        assertTrue(a.getNumPoints() >= minPoints && a.getNumPoints() <= maxPoints && a.getRadius() >= minRadius && a.getRadius() <= maxRadius);
        assertTrue(b.getNumPoints() >= minPoints && b.getNumPoints() <= maxPoints && b.getRadius() >= minRadius && b.getRadius() <= maxRadius);
        assertTrue(c.getNumPoints() >= minPoints && c.getNumPoints() <= maxPoints && c.getRadius() >= minRadius && c.getRadius() <= maxRadius);
        assertTrue(d.getNumPoints() >= minPoints && d.getNumPoints() <= maxPoints && d.getRadius() >= minRadius && d.getRadius() <= maxRadius);
        assertTrue(e.getNumPoints() >= minPoints && e.getNumPoints() <= maxPoints && e.getRadius() >= minRadius && e.getRadius() <= maxRadius);
    }

    @Test
    public void testGetPointOutOfBounds() {
        RandomTrackPoints track = new RandomTrackPoints();
        assertThrows(IndexOutOfBoundsException.class, () -> track.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> track.get(track.getNumPoints()));
    }

    @Test
    public void testGetPointAtPosition() {
        RandomTrackPoints track = new RandomTrackPoints();
        int numPoints = track.getNumPoints();
        for (int i = 0; i < numPoints; i++) {
            assertNotNull(track.get(i));
        }
    }

    @Test
    public void testGeneratedPointsNotEmpty() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getTrackPoints();
        assertFalse(points.isEmpty());
        assertEquals(track.getNumPoints(), points.size());
    }

    @Test
    public void testPointsValuesNotNull() {
        RandomTrackPoints track = new RandomTrackPoints();
        for (Vector2 point : track.getTrackPoints()) {
            assertNotNull(point);
        }
    }

    @Test
    public void testCircuitClosed() {
        RandomTrackPoints track = new RandomTrackPoints();

        Vector2 firstPoint = track.get(0);
        Vector2 lastPoint = track.get(track.getNumPoints() - 1);

        // Circuit is closed if first and last point are near
        // Segment is the 4x distance between two points given the circle orignal perimeter and the number of points
        float segment = (float) (2 * Math.PI * track.getRadius()) / (track.getNumPoints() / 4.0f);
        assertTrue(firstPoint.distance(lastPoint) < segment, segment + " is less than " + firstPoint.distance(lastPoint));
    }
}
