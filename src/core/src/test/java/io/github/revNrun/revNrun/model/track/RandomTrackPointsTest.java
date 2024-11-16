package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomTrackPointsTest {

    private static List<Vector2> initialPoints;
    private static List<Vector2> basePoints;
    private static List<Vector2> points;

    @BeforeEach
    public void setUp() {
        RandomTrackPoints track = new RandomTrackPoints();
        initialPoints = track.getInitialPoints();
        basePoints = track.getBasePoints();
        points = track.getPoints();
    }


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
        assertFalse(initialPoints.isEmpty());
    }

    @Test
    public void testGeneratedBasePointsNotEmpty() {
        assertFalse(basePoints.isEmpty());
    }

    @Test
    public void testGeneratedPointsNotEmpty() {
        assertFalse(points.isEmpty());
    }

    @Test
    public void testInitialPointsValuesNotNull() {
        for (Vector2 point : initialPoints) {
            assertNotNull(point);
        }
    }

    @Test
    public void testBasePointsValuesNotNull() {
        for (Vector2 point : basePoints) {
            assertNotNull(point);
        }
    }

    @Test
    public void testPointsValuesNotNull() {
        for (Vector2 point : points) {
            assertNotNull(point);
        }
    }

    @Test
    public void testInitialCircuitClosed() {
        Vector2 firstPoint = initialPoints.get(0);
        Vector2 lastPoint = initialPoints.get(initialPoints.size() - 1);

        assertEquals(firstPoint, lastPoint);
    }

    @Test
    public void testBaseCircuitClosed() {
        Vector2 firstPoint = basePoints.get(0);
        Vector2 lastPoint = basePoints.get(basePoints.size() - 1);

        assertEquals(firstPoint, lastPoint);
    }

    @Test
    public void testCircuitClosed() {
        Vector2 firstPoint = points.get(0);
        Vector2 lastPoint = points.get(points.size() - 1);

        assertEquals(firstPoint, lastPoint);
    }

    @Test
    public void testLastInitialPointNotDuplicated() {
        assertNotEquals(initialPoints.get(initialPoints.size() - 1), initialPoints.get(initialPoints.size() - 2));
    }

    @Test
    public void testLastBasePointNotDuplicated() {
        assertNotEquals(basePoints.get(basePoints.size() - 1), basePoints.get(basePoints.size() - 2));
    }

    @Test
    public void testLastPointNotDuplicated() {
        assertNotEquals(points.get(points.size() - 1), points.get(points.size() - 2));
    }

    @Test
    public void testInitialPointsAreAtSameDistanceAndNoDuplicates() {
        // Duplicates are also tested in here because if two consecutive points are the same one, their distance wouldn't equal to distance
        float distance = initialPoints.get(0).distance(initialPoints.get(1));
        for(int i = 0; i < initialPoints.size() - 1; i++) {
            assertEquals(distance, initialPoints.get(i).distance(initialPoints.get(i+1)), 0.01);
        }

        // Therefore, initial points can't only not be a copy of a consecutive points, but of any point (except of the first and last points)
        int j;
        for(int i = 0; i < initialPoints.size() - 2; i++) {
            for(j = i + 1; j < initialPoints.size() - 1; j++) {
                if (i == 0 && j == initialPoints.size() - 1) {
                    assertEquals(initialPoints.get(i), initialPoints.get(j));
                }
                assertNotEquals(initialPoints.get(i), initialPoints.get(j));
            }
        }
    }

    @Test
    public void testPointsAreAtSameDistanceAndNoDuplicates() {
        // Duplicates are also tested in here because if two consecutive points the same one, the distance wouldn't equal to distance
        // Among the final points duplicates are accepted, but not as consecutive points
        float distance = points.get(0).distance(points.get(1));
        for(int i = 0; i < points.size() - 1; i++) {
            assertEquals(distance, points.get(i).distance(points.get(i + 1)), 0.01, "theoric distance is " + distance + " and real distance is " + points.get(i).distance(points.get(i + 1)));
            //assertEquals(distance, points.get(i).distance(points.get(i+1)), 0.01);
        }
    }
}
