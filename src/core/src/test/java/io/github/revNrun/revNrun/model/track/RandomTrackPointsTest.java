package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
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

        List<Vector2> aList = a.getInitialPoints();
        List<Vector2> bList = b.getInitialPoints();
        List<Vector2> cList = c.getInitialPoints();
        List<Vector2> dList = d.getInitialPoints();
        List<Vector2> eList = e.getInitialPoints();

        int minPoints = RandomTrackPoints.getMinNumInitialPoints();
        int maxPoints = RandomTrackPoints.getMaxNumInitialPoints();
        float minRadius = RandomTrackPoints.getMinRadius();
        float maxRadius = RandomTrackPoints.getMaxRadius();

        assertTrue(aList.size() >= minPoints && aList.size() <= maxPoints && a.getRadius() >= minRadius && a.getRadius() <= maxRadius);
        assertTrue(bList.size() >= minPoints && bList.size() <= maxPoints && b.getRadius() >= minRadius && b.getRadius() <= maxRadius);
        assertTrue(cList.size() >= minPoints && cList.size() <= maxPoints && c.getRadius() >= minRadius && c.getRadius() <= maxRadius);
        assertTrue(dList.size() >= minPoints && dList.size() <= maxPoints && d.getRadius() >= minRadius && d.getRadius() <= maxRadius);
        assertTrue(eList.size() >= minPoints && eList.size() <= maxPoints && e.getRadius() >= minRadius && e.getRadius() <= maxRadius);
    }

    @Test
    public void testGeneratedInitialPointsNotEmpty() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getInitialPoints();
        assertFalse(points.isEmpty());
    }

    @Test
    public void testGeneratedBasePointsNotEmpty() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getBasePoints();
        assertFalse(points.isEmpty());
    }

    @Test
    public void testGeneratedPointsNotEmpty() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getPoints();
        assertFalse(points.isEmpty());
    }

    @Test
    public void testInitialPointsValuesNotNull() {
        RandomTrackPoints track = new RandomTrackPoints();
        for (Vector2 point : track.getInitialTrackPoints()) {
            assertNotNull(point);
        }
    }

    @Test
    public void testBasePointsValuesNotNull() {
        RandomTrackPoints track = new RandomTrackPoints();
        for (Vector2 point : track.getBasePoints()) {
            assertNotNull(point);
        }
    }

    @Test
    public void testPointsValuesNotNull() {
        RandomTrackPoints track = new RandomTrackPoints();
        for (Vector2 point : track.getPoints()) {
            assertNotNull(point);
        }
    }

    @Test
    public void testInitialCircuitClosed() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getInitialPoints();
        Vector2 firstPoint = points.get(0);
        Vector2 lastPoint = points.get(points.size() - 1);

        assertEquals(firstPoint, lastPoint);
    }

    @Test
    public void testBaseCircuitClosed() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getBasePoints();
        Vector2 firstPoint = points.get(0);
        Vector2 lastPoint = points.get(points.size() - 1);

        assertEquals(firstPoint, lastPoint);
    }

    @Test
    public void testCircuitClosed() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getPoints();
        Vector2 firstPoint = points.get(0);
        Vector2 lastPoint = points.get(points.size() - 1);

        assertEquals(firstPoint, lastPoint);
    }

    @Test
    public void testInitialPointsAreClose() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getInitialPoints();

        // To be near means that the distance between two points is the 4x of the perimeter divided by the number of points
        float close = (float) ((2 * Math.PI * track.getRadius()) / points.size()) * 4;

        for(int i = 0; i < points.size() - 1; i++) {
            assertTrue(points.get(i).distance(points.get(i+1)) < close);
        }
    }

    @Test
    public void testBasePointsAreClose() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getBasePoints();

        // To be near means that the distance between two points is the 4x of the perimeter divided by the number of points
        float close = (float) ((2 * Math.PI * track.getRadius()) / points.size()) * 4;

        for(int i = 0; i < points.size() - 1; i++) {
            assertTrue(points.get(i).distance(points.get(i+1)) < close);
        }
    }

    @Test
    public void testPointsAreClose() {
        RandomTrackPoints track = new RandomTrackPoints();
        List<Vector2> points = track.getPoints();

        // To be near means that the distance between two points is the 4x of the perimeter divided by the number of points
        float close = (float) ((2 * Math.PI * track.getRadius()) / points.size()) * 4;

        for(int i = 0; i < points.size() - 1; i++) {
            assertTrue(points.get(i).distance(points.get(i+1)) < close);
        }
    }
}
