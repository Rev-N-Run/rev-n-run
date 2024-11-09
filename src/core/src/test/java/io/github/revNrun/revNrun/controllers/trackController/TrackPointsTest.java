package io.github.revNrun.revNrun.controllers.trackController;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrackPointsTest {

    @Test
    public void testLimitValues() {
        TrackPoints a;
        TrackPoints b;
        TrackPoints c;
        TrackPoints d;
        TrackPoints e;

        // Everything should work, TrackPoints modifies numPoints and radius if they're outside limits
        try {
            a = new TrackPoints();
            b = new TrackPoints(0, 100);
            c = new TrackPoints(1000, 0);
            d = new TrackPoints(-1, 1000);
            e = new TrackPoints(100, -1);
        } catch (Exception ex) {
            fail("Something wrong here!");
        }

        int minPoints = TrackPoints.getMinPoints();
        int maxPoints = TrackPoints.getMaxPoints();
        int minRadius = TrackPoints.getMinRadius();
        int maxRadius = TrackPoints.getMaxRadius();

        assertTrue(a.getNumPoints() >= minPoints && a.getNumPoints() <= maxPoints && a.getRadius() >= minRadius && a.getRadius() <= maxRadius);
        assertTrue(b.getNumPoints() == minPoints && b.getRadius() == 100);
        assertTrue(c.getNumPoints() == maxPoints && c.getRadius() == minRadius);
        assertTrue(d.getNumPoints() == minPoints && d.getRadius() == maxRadius);
        assertTrue(e.getNumPoints() == 100 && e.getRadius() == minRadius);
    }

    @Test
    public void testCircuitClosed() {
        TrackPoints track = new TrackPoints();

        Vector2 firstPoint = track.get(0);
        Vector2 lastPoint = track.get(track.getNumPoints() - 1);

        // Circuit is closed if first and last point are near
        assertTrue(firstPoint.distance(lastPoint) < 2.0f);
    }

}
