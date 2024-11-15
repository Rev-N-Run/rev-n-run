package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO test there are not two duplicated points, a part del últim i primer
class RandomTrackPointsTest {

    private static RandomTrackPoints track;
    private static List<Vector2> initialPoints;
    private static List<Vector2> basePoints;
    private static List<Vector2> points;

    @BeforeEach
    public void setUp() {
        track = new RandomTrackPoints();
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
    public void testInitialPointsAreClose() {
        // To be near means that the distance between two points is the 4x of the perimeter divided by the number of points
        float close = (float) ((2 * Math.PI * track.getRadius()) / initialPoints.size()) * 4;

        for(int i = 0; i < initialPoints.size() - 1; i++) {
            assertTrue(initialPoints.get(i).distance(initialPoints.get(i+1)) < close);
        }
    }

    @Test
    public void testPointsAreClose() {
        // To be near means that the distance between two points is the 4x of the perimeter divided by the number of points
        float close = (float) ((2 * Math.PI * track.getRadius()) / points.size()) * 4;

        // i iterates till size()-2 bcs last point has been forced to be the first point
        for(int i = 0; i < points.size() - 2; i++) {
            assertTrue(points.get(i).distance(points.get(i+1)) < close);
        }
    }

    @Test
    public void testInitialPointsAreAtSameDistance() {
        float distance = initialPoints.get(0).distance(initialPoints.get(1));
        for(int i = 0; i < initialPoints.size() - 1; i++) {
            assertEquals(distance, initialPoints.get(i).distance(initialPoints.get(i+1)), 0.01);
        }
    }

    @Test
    public void testPointsAreAtSameDistance() {
        float distance = points.get(0).distance(points.get(1));
        for(int i = 0; i < points.size() - 1; i++) {
            assertEquals(distance, points.get(i).distance(points.get(i+1)), 0.01);
        }
    }
}
